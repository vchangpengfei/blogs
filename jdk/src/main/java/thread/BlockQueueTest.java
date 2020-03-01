package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * LinkedBlockingDeque take put的时候必须先获取锁
 * LinkedBlockingDeque 中维护两个condition（队列）
 * notEmpty take方法等待队列
 * notFull  put方法等待队列
 */
public class BlockQueueTest {
    public static void main(String args[]) throws InterruptedException {
        BlockingQueue<Object> blockingQueue=new LinkedBlockingDeque<>(2);

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    blockingQueue.put("adsf");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        System.out.println(blockingQueue.take());

    }
}
