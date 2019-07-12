package cha.pao.fan.blogs.jdk;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

    @Test
    public void test(){
        LongAdder adder=new LongAdder();
        Assert.assertEquals("",adder.longValue(),0l);
    }

    @Test
    public void test1(){
        LongAdder adder=new LongAdder();
        adder.add(1);
        Assert.assertEquals("",adder.longValue(),1l);
    }

    @Test
    public void test3(){
        LongAdder adder=new LongAdder();
        adder.add(1);
        adder.reset();
        Assert.assertEquals(adder.longValue(),0l);
    }

    @Test
    public void test4(){
        LongAdder adder=new LongAdder();
        adder.add(1);
        adder.decrement();
        Assert.assertEquals(adder.longValue(),0l);
    }

    @Test
    public void test5(){
        LongAdder adder=new LongAdder();
        adder.add(1);
        adder.increment();
        Assert.assertEquals(adder.longValue(),2l);
    }


}
