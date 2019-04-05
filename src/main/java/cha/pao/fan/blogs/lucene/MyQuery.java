package cha.pao.fan.blogs.lucene;

import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Set;

public class MyQuery extends Query {

    String field[];

    public MyQuery(String ... field) {
        this.field=field;
    }

    @Override
    public Weight createWeight(IndexSearcher searcher, boolean needsScores, float boost) throws IOException {
        return new Weight(this) {
            @Override
            public void extractTerms(Set<Term> terms) {
                System.out.println(terms);
            }

            @Override
            public Explanation explain(LeafReaderContext context, int doc) throws IOException {

                return Explanation.noMatch("test donot match");
            }

            @Override
            public Scorer scorer(LeafReaderContext context) throws IOException {

                BitSet set=new BitSet();
                {
                    set.set(9);
                }
                DocIdSetIterator iterator=new DocIdSetIterator() {
                    int doc = -1;
                    //这里只设置一个元素，laices
                    int maxDoc=set.length();

                    @Override
                    public int docID() {
                        return doc;
                    }

                    @Override
                    public int nextDoc() throws IOException {
                        return advance(doc + 1);
                    }

                    @Override
                    public int advance(int target) throws IOException {
                        doc = target;
                        if (doc >= maxDoc) {
                            doc = NO_MORE_DOCS;
                        }
                        return doc;
                    }

                    @Override
                    public long cost() {
                        return set.cardinality();
                    }
                };


                return new Scorer(this){

                    @Override
                    public int docID() {
                        return iterator.docID();
                    }

                    @Override
                    public float score() throws IOException {
                        return 0;//字段返回的分数
                    }

                    @Override
                    public DocIdSetIterator iterator() {
                        return iterator;
                    }
                };
            }

            @Override
            public boolean isCacheable(LeafReaderContext ctx) {
                return false;
            }
        };
    }

    @Override
    public String toString(String field) {
        StringBuilder builder=new StringBuilder();
        builder.append("myquery[" );
        builder.append(field+']');
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
