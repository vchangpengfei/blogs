package cha.pao.fan.blogs.jdk;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.*;

public class ByteBufferTest {

    @Test
    public void test(){
        ByteBuffer buffer=ByteBuffer.allocate(88);
        String value="Netty权威指南";
        buffer.put(value.getBytes());
        buffer.flip();
        byte[] vArray=new byte[buffer.remaining()];
        buffer.get(vArray);
        String decodeValue=new String(vArray);
        assertEquals(value,decodeValue);
    }

    @Test
    public void test1(){
        ByteBuffer buffer=ByteBuffer.allocate(88);
        String value="Netty权威指南";
        buffer.put(value.getBytes());
        buffer.flip();
        ByteBuffer buffer1=buffer.slice();
        byte[] vArray=new byte[buffer.remaining()];
        buffer.get(vArray);
        String decodeValue=new String(vArray);
        assertEquals(value,decodeValue);
    }

}
