package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 配合Lock  实现线程的等待 与通知
 */
public class LockConditionTest{
    public static ReentrantLock lock=new ReentrantLock();
    //ConditionObject 维护自己的一个阻塞线程队列 ReentrantLock中阻塞队列通过aqs中队列维护
    public static Condition condition =lock.newCondition();
    public static void main(String[] args) throws InterruptedException {

//        condition.await();//condition方法必须再lock跟unlock间使用

        new Thread(){
            @Override
            public void run() {
                lock.lock();//请求锁
                try{
                    System.out.println(Thread.currentThread().getName()+"==》进入等待");
                    condition.await();//设置当前线程进入等待 释放当前锁
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    lock.unlock();//释放锁
                }
                System.out.println(Thread.currentThread().getName()+"==》继续执行");
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                lock.lock();//请求锁
                try{
                    System.out.println(Thread.currentThread().getName()+"=》进入");
                    Thread.sleep(2000);//休息2秒
                    condition.signal();//随机唤醒等待队列中的一个线程 锁仍然没有释放 只是
                    System.out.println(Thread.currentThread().getName()+"休息结束");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    lock.unlock();//释放锁
                }
            }
        }.start();
    }
}

