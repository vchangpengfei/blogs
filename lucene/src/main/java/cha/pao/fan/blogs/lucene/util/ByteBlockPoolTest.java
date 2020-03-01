package cha.pao.fan.blogs.lucene.util;

import org.apache.lucene.util.ByteBlockPool;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Counter;
import org.junit.Test;

public class ByteBlockPoolTest {

	@Test
    public void test1(){
        Counter bytesUsed = Counter.newCounter();
        ByteBlockPool pool = new ByteBlockPool(new ByteBlockPool.DirectTrackingAllocator(bytesUsed));
        
        
        for(int i=0;i<100000;i++)
        {
        	byte[] b="你好test".getBytes();
            BytesRef ref=new BytesRef(b);
            pool.append(ref);
        }
        
        BytesRef refc=new BytesRef("你好test".getBytes());
        pool.readBytes(0,refc.bytes,0,refc.length);

        System.out.println(new String(refc.bytes));

    }
	
	
	@Test
    public void test2(){
        Counter bytesUsed = Counter.newCounter();
        ByteBlockPool pool = new ByteBlockPool(new ByteBlockPool.DirectTrackingAllocator(bytesUsed));
        pool.newSlice(32768);
        byte[] b="你好test".getBytes();
        BytesRef ref=new BytesRef(b);
        pool.append(ref);
        
        BytesRef refc=new BytesRef("你好test".getBytes());
        pool.readBytes(32768,refc.bytes,0,10);

        System.out.println(new String(refc.bytes));

        
        System.out.println(pool);
    }
	
	
}
