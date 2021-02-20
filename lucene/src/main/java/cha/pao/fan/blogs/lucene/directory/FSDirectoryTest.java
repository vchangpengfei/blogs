package cha.pao.fan.blogs.lucene.directory;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.store.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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

}
