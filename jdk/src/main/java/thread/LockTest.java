package thread;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String args[])
    {
        ReentrantLock lock = new ReentrantLock();

        new Thread("thread"){
            @Override
            public void run() {
                lock.lock();
//                lock.lock();//锁+1 会导致main线程永远得不到锁
                try {
                    System.out.println("thread线程得到锁");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();

            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        lock.lock();
        System.out.println("main线程得到锁");
        lock.unlock();
        System.out.println("========执行========");
    }
}
