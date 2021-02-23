package cha.pao.fan.blogs.lucene.directory;

import org.apache.lucene.codecs.StoredFieldsReader;
import org.apache.lucene.codecs.StoredFieldsWriter;
import org.apache.lucene.codecs.lucene87.Lucene87Codec;
import org.apache.lucene.codecs.lucene87.Lucene87StoredFieldsFormat;
import org.apache.lucene.document.DocumentStoredFieldVisitor;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.Sort;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by pfchang
 * on 2021/2/18.
 *
 * lucene文件结构
 * https://lucene.apache.org/core/8_8_0/core/org/apache/lucene/codecs/lucene87/package-summary.html#package.description
 *
 *
 */
public class FSDirectoryTest {


    @Test
    public void testFSDirectory() throws IOException {
        FSDirectory directory=FSDirectory.open(Paths.get("test"));
        directory.deleteFile("t1");
        IndexOutput indexOutput=directory.createOutput("t1",null);
        indexOutput.writeString("好");
        indexOutput.writeString("拉拉");
        indexOutput.close();

        ChecksumIndexInput indexInput=directory.openChecksumInput("t1",null);
        System.out.println(indexInput.getChecksum());
        System.out.println(indexInput.length());
        System.out.println(indexInput.readString());
        System.out.println(indexInput.getFilePointer());
        System.out.println(indexInput.readString());
//        indexInput.seek(0);//不可以随机读取
//        System.out.println(indexInput.readString());

        directory.close();


    }


    @Test
    public void testMMapDirectory() throws IOException {
        MMapDirectory directory=new MMapDirectory(Paths.get("test"), FSLockFactory.getDefault());
        directory.deleteFile("t1");
        IndexOutput indexOutput=directory.createOutput("t1",null);
        indexOutput.writeString("好");
        indexOutput.writeString("拉拉");
        indexOutput.close();

        IndexInput indexInput=directory.openInput("t1",null);
        System.out.println(indexInput.length());
        System.out.println(indexInput.readString());
        indexInput.seek(0);//可以随机读取
        System.out.println(indexInput.readString());
        System.out.println(indexInput.getFilePointer());
        System.out.println(indexInput.readString());
        directory.close();
    }


    @Test
    public void testNIOFSDirectory() throws IOException {
        NIOFSDirectory directory=new NIOFSDirectory(Paths.get("test"), FSLockFactory.getDefault());
        directory.deleteFile("t1");
        IndexOutput indexOutput=directory.createOutput("t1",null);
        indexOutput.writeString("好");
        indexOutput.writeString("拉拉");
        indexOutput.close();

        IndexInput indexInput=directory.openInput("t1",IOContext.READ);
        System.out.println(indexInput.length());
        System.out.println(indexInput.readString());
        indexInput.seek(0);//可以随机读取
        System.out.println(indexInput.readString());
        System.out.println(indexInput.getFilePointer());
        System.out.println(indexInput.readString());
        directory.close();
    }


    /**
     *
     * @throws IOException
     *
     * obtainLock 是创建一个锁文件  test/lock文件
     *
     * 底层调用api  lock 文件的FileChannel对象 tryLock()或者lock()  其他应用程序如果没有获得相应的锁 是操作不了这个文件的
     *
     * 同一个jvm中进程中 多个线程是都可以操作获得这个锁的文件的
     *
     *
     *
     */
    @Test
    public void testLock() throws IOException {
        MMapDirectory directory=new MMapDirectory(Paths.get("test"), FSLockFactory.getDefault());
        directory.obtainLock("lock");
        new Thread(){
            @Override
            public void run() {
                try {
                    Files.delete(Paths.get("test").resolve("lock"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        directory.close();
    }


    @Test
    public void testFdt() throws IOException {
        MMapDirectory directory=new MMapDirectory(Paths.get("test"), FSLockFactory.getDefault());
        Lucene87StoredFieldsFormat fieldsFormat=new Lucene87StoredFieldsFormat();
        SegmentInfo segmentInfo=new SegmentInfo(directory, Version.LUCENE_8_7_0,Version.LUCENE_8_7_0,"1",10,false,new Lucene87Codec(),new HashMap<>(),
                new byte[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},new HashMap<>(), Sort.INDEXORDER);
        StoredFieldsWriter writer=fieldsFormat.fieldsWriter(directory,segmentInfo,IOContext.DEFAULT);
        FieldInfo fieldInfo=new FieldInfo("name",1,false,true,false, IndexOptions.DOCS,DocValuesType.SORTED,0,new HashMap<>(),1,1,1,false);
        FieldInfos fn=new FieldInfos(new FieldInfo[]{fieldInfo});
        writer.startDocument();
        writer.writeField(fieldInfo,new StringField("name","testname", Field.Store.YES));
        writer.finishDocument();
        writer.finish(fn,1);//numDocs 必须跟写入数量一致
        writer.close();


        StoredFieldsReader reader=fieldsFormat.fieldsReader(directory,segmentInfo,fn,IOContext.DEFAULT);
        DocumentStoredFieldVisitor visitor=new DocumentStoredFieldVisitor();
        reader.visitDocument(0,visitor);
        System.out.println(visitor.getDocument().getField("name").stringValue());

        directory.close();
    }



}
