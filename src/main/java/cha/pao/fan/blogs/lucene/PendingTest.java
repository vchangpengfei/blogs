package cha.pao.fan.blogs.lucene;

import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.store.DataInput;
import org.apache.lucene.store.DataOutput;
import org.apache.lucene.util.*;
import org.apache.lucene.util.packed.PackedInts;
import org.apache.lucene.util.packed.PackedLongValues;
import org.junit.Test;

import java.io.IOException;


/**
 *  创建索引时存储类
 */
public class PendingTest {


    @Test
    public void test(){
        PackedLongValues.Builder pending=PackedLongValues.deltaPackedBuilder(PackedInts.COMPACT);
        pending.add(1000);
        System.out.println(pending);
        PackedLongValues values=pending.build();
        System.out.println(values.get(0));
    }

    @Test
    public void test1() throws IOException {
        DocsWithFieldSet docsWithField = new DocsWithFieldSet();
        docsWithField.add(1000);
        DocIdSetIterator iterator= docsWithField.iterator();
        System.out.println(iterator.nextDoc());
    }

    @Test
    public void test3(){
        Counter bytesUsed = Counter.newCounter();
        ByteBlockPool pool = new ByteBlockPool(new ByteBlockPool.DirectTrackingAllocator(bytesUsed));
        byte[] b="你好test".getBytes();
        BytesRef ref=new BytesRef(b);
        pool.append(ref);
        BytesRef refc=new BytesRef(b.length);
        pool.readBytes(0,refc.bytes,0,ref.length);

        System.out.println(new String(refc.bytes));
    }


    @Test
    public void test4() throws IOException {
        PagedBytes bytes = new PagedBytes(15);
        DataOutput bytesOut = bytes.getDataOutput();
        bytesOut.writeInt(100);
//        DataInput byteInput=new DataInput();

    }



    @Test
    public void test5(){
        BytesRefHash refHash=new BytesRefHash();
        BytesRefBuilder refBuilder = new BytesRefBuilder();
        refBuilder.copyChars("你好test");
        byte[] b="你好test".getBytes();
//        BytesRef ref=new BytesRef(b);
        int termid=refHash.add(refBuilder.get());
        refBuilder.clear();
        refBuilder.copyChars("lalal");
        termid=refHash.add(refBuilder.get());
//        refHash.byteStart(termid);
        System.out.println(refHash.find(refBuilder.get())==termid);;
        BytesRef ref1=new BytesRef();
        refHash.get(termid,ref1);
        System.out.println(new String (ref1.bytes));
        System.out.println(new String (refBuilder.get().bytes));

    }



}
