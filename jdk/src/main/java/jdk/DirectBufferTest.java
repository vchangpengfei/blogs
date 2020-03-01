package jdk;


import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * https://blog.csdn.net/zhxdick/article/details/81084672
 */
public class DirectBufferTest {

    @Test
    public void test(){
        //堆外内存
        ByteBuffer buffer=ByteBuffer.allocateDirect(1024);
    }

    @Test
    public void test1(){
//        Unsafe UNSAFE=Unsafe.getUnsafe();
//        long address=UNSAFE.allocateMemory(1024);
//        Constructor<?>[]constructors=ByteBuffer.allocateDirect(1).getClass().getConstructors();
//        ByteBuffer buffer=(ByteBuffer)
//        .newInstance(address,1024);
    }

}
