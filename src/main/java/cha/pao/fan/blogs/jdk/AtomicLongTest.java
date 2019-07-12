package cha.pao.fan.blogs.jdk;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

public class AtomicLongTest {

    @Test
    public void test(){
        AtomicLong atomicLong=new AtomicLong();
        assertEquals(atomicLong.addAndGet(1024),1024l);
    }

}
