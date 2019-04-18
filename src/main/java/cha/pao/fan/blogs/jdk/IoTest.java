package cha.pao.fan.blogs.jdk;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;

public class IoTest {


    @Test
    public void runCheckedOutStream() throws IOException {
        FileOutputStream out = new FileOutputStream("lucene_index/testfile.txt");
        CheckedOutputStream checked = new CheckedOutputStream(out, new Adler32());
        BufferedOutputStream stream=new BufferedOutputStream(checked,10);
        stream.write("你好test".getBytes());
        stream.flush();
        out.close();
        checked.close();
        System.out.println("checksum: " + checked.getChecksum().getValue());
    }


    @Test
    public void runCheckedInputStream() throws IOException {

        FileInputStream in = new FileInputStream("lucene_index/testfile.txt");
        CheckedInputStream checked = new CheckedInputStream(in, new Adler32());
        byte[] b = new byte["你好test".getBytes().length];
        while ((checked.read(b)) != -1) {
        }
        System.out.println(new String(b));
        in.close();
        checked.close();
        System.out.println("checksum: " + checked.getChecksum().getValue());
    }

    @Test
    public void testFiles() throws IOException {
        OutputStream outputStream=Files.newOutputStream(new File("test.txt").toPath(),
                StandardOpenOption.CREATE,StandardOpenOption.CREATE_NEW,StandardOpenOption.DELETE_ON_CLOSE,StandardOpenOption.TRUNCATE_EXISTING);
        outputStream.write("你好test".getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
