package cha.pao.fan.blogs.lucene;

import cha.pao.fan.blogs.utils.IOUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queries.function.FunctionQuery;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.search.*;
import org.apache.lucene.util.DocIdSetBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 *
 * ConjunctionScorer.ConjunctionScorer(Weight weight, Collection<Scorer> required, Collection<Scorer> scorers)
 * this.disi = ConjunctionDISI.intersectScorers(required);
 * ConjunctionDISI这个类真正取做合并
 * 到排表合并 交集
 *
 *
 *
 *
 *
 *
 */

public class IndexSearchTest extends LuceneTestBase{


    @Before
    public void init()
    {
        File index_dir=new File(index_path);
        IOUtils.deleteDir(index_dir);
        try {
            addindex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void updateindex() throws IOException {

        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter writer=null;

        //创建IndexWriter
        writer=new IndexWriter(newDirectory(),indexWriterConfig);

        for (int i=0;i<5;i++) {
            Document document=new Document();

            Field id=new NumericDocValuesField("id",i);
            Field _id=new IntPoint("_id",i);
            Field name=new TextField("name",i+"name", Field.Store.YES);
            Field price=new NumericDocValuesField("price",10*i*10);
            document.add(_id);
            document.add(id);
            document.add(name);
            document.add(price);

            writer.updateDocument(new Term("name",i+"name"),document);
        }

        writer.deleteDocuments( new Term("name",5+"name"));
        writer.commit();
        writer.close();
    }

    @Test
    public  void addindex() throws IOException {

        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setRAMBufferSizeMB(10);
        indexWriterConfig.setMaxBufferedDocs(4);
        indexWriterConfig.setInfoStream(System.out);
        IndexWriter writer=null;


        //创建IndexWriter
        writer=new IndexWriter(newDirectory(),indexWriterConfig){
            @Override
            protected boolean isEnableTestPoints() {
                return true;
            }
        };


        for (int i=0;i<15;i++) {
            Document document=new Document();

//            Field id=new NumericDocValuesField("id",i);
            StringField _id=new StringField("_id",i+"_id",Field.Store.YES);


            Field name=null;
            if(i==3)
                name=new TextField("name",i+"name this field uses the standard codec as the standard xxx mmm", Field.Store.YES);
            else if(i==5)
                name=new TextField("name",i+"name this field standard the standard codec as standard test", Field.Store.YES);
            else if(i==7)
                name=new TextField("name",i+"name this field uses  the standard standard codec as the test", Field.Store.YES);
            else
                name=new TextField("name",i+"name this field  the  codec as  test", Field.Store.YES);




            Field price=new NumericDocValuesField("price",10*i);
            if(i==0)
            {
                price=new NumericDocValuesField("price",20);
            }

            if(i==1)
            {
                price=new NumericDocValuesField("price",10);
            }

            if(i==2)
            {
                price=new NumericDocValuesField("price",20);
            }

            if(i==3)
            {
                price=new NumericDocValuesField("price",20);
            }

            if(i==4)
            {
                price=new NumericDocValuesField("price",78);
            }

            if(i==5)
            {
                price=new NumericDocValuesField("price",54);
            }

            if(i==6)
            {
                price=new NumericDocValuesField("price",23);
            }

            if(i==7)
            {
                price=new NumericDocValuesField("price",8);
            }


            //支持多值返回
            SortedNumericDocValuesField sortedF=new SortedNumericDocValuesField("sortedint",i*3);
            SortedNumericDocValuesField sortedG=new SortedNumericDocValuesField("sortedint",i*3+1);

            //支持多值查询
            Field setFileda =new IntPoint("setFiled",i*3);
            Field setFiledb =new IntPoint("setFiled",i*3+1);


            document.add(_id);
//            document.add(id);
            document.add(name);
            document.add(price);
            document.add(sortedF);
            document.add(sortedG);
            document.add(setFileda);
            document.add(setFiledb);

            writer.updateDocument(new Term("_id","2_id"),document);

//            if(i==2)
//            {
//                writer.deleteDocuments(new Term("_id","2_id"));
//            }
        }
        writer.commit();
        writer.forceMerge(1);
        writer.close();
    }


    @Test
    public void stringSearch() throws IOException {
        IndexReader reader= DirectoryReader.open(newDirectory() );
        IndexSearcher searcher=new IndexSearcher(reader);
        Query query=new TermQuery(new Term("_id","5_id") );//多值查询
        TopDocs topDocs = searcher.search(query,10);
        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        //
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docId = scoreDoc.doc;
            Document document=searcher.doc(docId);
            System.out.println(document.get("name"));
            System.out.println(document.get("_id"));
        }
    }

    @Test
    public void docvaluesSearch() throws IOException {
        IndexReader reader= DirectoryReader.open(newDirectory() );
        IndexSearcher searcher=new IndexSearcher(reader);
        Query query=NumericDocValuesField.newSlowExactQuery("price",23);//多值查询
        TopDocs topDocs = searcher.search(query,10);
        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        NumericDocValues pricedocvaljues=MultiDocValues.getNumericValues(searcher.getIndexReader(),"price");
        SortedNumericDocValues sortedintdocvaljues=MultiDocValues.getSortedNumericValues(searcher.getIndexReader(),"sortedint");
        //
        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取document
            int docId = scoreDoc.doc;
            pricedocvaljues.advanceExact(docId);
            Document document=searcher.doc(docId);
            System.out.println(document.get("name"));
            System.out.println(pricedocvaljues.longValue());
        }
    }



    @Test
    public void search() throws IOException {
        //打开查询流564gv
        IndexReader reader= DirectoryReader.open(newDirectory());
        IndexSearcher searcher=new IndexSearcher(reader);
        //获取排序后的数据
//        Query query=new TermQuery(new Term("name","9name"));

//        Query query=IntPoint.newExactQuery("_id",1);
        Query query=IntPoint.newSetQuery("setFiled",8,8,8);//多值查询
        TopDocs topDocs = searcher.search(query,10);
        //总记录数
        long count=topDocs.totalHits.value;

        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        NumericDocValues pricedocvaljues=MultiDocValues.getNumericValues(searcher.getIndexReader(),"price");
        SortedNumericDocValues sortedintdocvaljues=MultiDocValues.getSortedNumericValues(searcher.getIndexReader(),"sortedint");

        //
        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取document
            int docId = scoreDoc.doc;
            pricedocvaljues.advanceExact(docId);
            Document document=searcher.doc(docId);
            System.out.println(document.get("name"));
            System.out.println(pricedocvaljues.longValue());
        }

        System.out.println("========================");

        pricedocvaljues.advanceExact(4);
        System.out.println(pricedocvaljues.docID()+":"+pricedocvaljues.longValue());

        sortedintdocvaljues.advanceExact(4);
        System.out.println(sortedintdocvaljues.docID()+":"+sortedintdocvaljues.docValueCount());
        System.out.println(sortedintdocvaljues.docID()+":"+sortedintdocvaljues.nextValue());
        System.out.println(sortedintdocvaljues.docID()+":"+sortedintdocvaljues.nextValue());


    }

    @Test
    public  void testFunctionSearch() throws IOException {
        //打开查询流
        IndexReader reader= DirectoryReader.open(newDirectory());
        IndexSearcher searcher=new IndexSearcher(reader);
        ValueSource vs=new TestFunction("id");
        Query query1 = new FunctionQuery(vs);//FunctionQuery.AllScorer 构造方法 DocIdSetIterator.all(context.reader().maxDoc()) 因为所有的doc是按顺序排的maxdoc 就是最大值 这样就表示所有的doc节点
        Query query2=IntPoint.newRangeQuery("_id",5,9);//PointRangeQuery.getInverseIntersectVisitor.visit(int docID, byte[] packedValue)这个地方查询doc


//        MyQuery myQuery=new MyQuery("id");

        BooleanQuery.Builder booleanQuery=new BooleanQuery.Builder();
        booleanQuery.add(query1, BooleanClause.Occur.MUST);
//        booleanQuery.add(myQuery, BooleanClause.Occur.MUST);
        booleanQuery.add(query2,BooleanClause.Occur.MUST);

        TopDocs topDocs = searcher.search(booleanQuery.build(),10);
        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        NumericDocValues iddocvaljues=MultiDocValues.getNumericValues(searcher.getIndexReader(),"id");
        NumericDocValues pricedocvaljues=MultiDocValues.getNumericValues(searcher.getIndexReader(),"price");

        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取document
            int docId = scoreDoc.doc;
            iddocvaljues.advanceExact(docId);
            pricedocvaljues.advanceExact(docId);
            Document document=searcher.doc(docId);
            System.out.println(iddocvaljues.longValue());
            System.out.println(document.get("name"));
            System.out.println(pricedocvaljues.longValue());
        }

    }


    /**
     * 会报错
     * @throws IOException
     * 因为docvalue 分成多片 读取的时候数组越界
     */
    @Test
    public  void testMultiDocvalue() throws IOException {
        updateindex();
        addindex();
        search();
    }

    @Test
    public  void testIntPointQuery() throws IOException {
        //打开查询流
        IndexReader reader= DirectoryReader.open(newDirectory());
        IndexSearcher searcher=new IndexSearcher(reader);
        List<LeafReaderContext> list = searcher.getIndexReader().leaves();
        for(LeafReaderContext context:list)
        {
            PointValues values=context.reader().getPointValues("_id");
            final DocIdSetBuilder result = new DocIdSetBuilder(reader.maxDoc(), values, "_id");
            values.intersect(new PointValues.IntersectVisitor(){

                DocIdSetBuilder.BulkAdder adder;
                @Override
                public void grow(int count) {
                    adder = result.grow(count);
                }

                @Override
                public void visit(int docID) throws IOException {
                    adder.add(docID);
                    System.out.println(docID);
                }

                @Override
                public void visit(int docID, byte[] packedValue) throws IOException {
                    System.out.println(docID);
                    adder.add(docID);
                }

                @Override
                public PointValues.Relation compare(byte[] minPackedValue, byte[] maxPackedValue) {
                    return PointValues.Relation.CELL_INSIDE_QUERY;
                }
            });
            DocIdSetIterator iterator=result.build().iterator();
            iterator.nextDoc();
            iterator.docID();
            iterator.nextDoc();
            iterator.docID();


        }

    }



    @Test
    public void testTermQuery() throws IOException {
        IndexReader reader= DirectoryReader.open(newDirectory());
        Query query=new TermQuery(new Term("name", "standard"));
//        query=new ConstantScoreQuery(query);
        IndexSearcher searcher=new IndexSearcher(reader);
        TopDocs docs=searcher.search(query,5);
        System.out.println(docs.totalHits);
        for(int i=0;i<docs.scoreDocs.length;i++)
        {
            System.out.println(docs.scoreDocs[i]);
        }



    }



    @Test
    public  void testCollector() throws IOException {
        //打开查询流564gv
        IndexReader reader= DirectoryReader.open(newDirectory());
        IndexSearcher searcher=new IndexSearcher(reader);
        //获取排序后的数据
//        Query query=new TermQuery(new Term("name","9name"));

//        Query query=IntPoint.newExactQuery("_id",1);
        Query query=IntPoint.newSetQuery("setFiled",8,8,8);//多值查询
        TopDocs topDocs = searcher.search(query,10);


    }


}
