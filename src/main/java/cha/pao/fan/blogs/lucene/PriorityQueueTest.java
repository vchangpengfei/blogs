package cha.pao.fan.blogs.lucene;

import org.apache.lucene.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String args[])
    {
        PriorityQueue<Integer> queue=new PriorityQueue<Integer>(10) {
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
