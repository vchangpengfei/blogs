package cha.pao.fan.blogs.lucene;


import org.junit.Test;

import java.util.PriorityQueue;

public class PriorityQueueTest {


    @Test
    public void testJDK(){
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        queue.add(3);
        queue.add(9);
        queue.add(6);
        queue.add(8);
        queue.add(4);
        queue.add(5);
        Integer i=null;
        while((i=queue.poll())!=null){
            System.out.println(i);
        }
    }



    public static void main(String args[])
    {
        org.apache.lucene.util.PriorityQueue<Integer> queue=new org.apache.lucene.util.PriorityQueue<Integer>(6) {
            @Override
            protected boolean lessThan(Integer a, Integer b) {
                return a>b;
            }
        };


        queue.add(3);
        queue.add(9);
        queue.add(6);
        queue.add(8);
        queue.add(4);
        queue.add(5);
        Integer i=null;
        while((i=queue.pop())!=null){
            System.out.println(i);
        }

    }


}
