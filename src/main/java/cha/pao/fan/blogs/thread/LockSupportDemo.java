package cha.pao.fan.blogs.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    private static Thread parkedThread= new Thread(new LockSupportDemo.Wait(), "Wait Thread");;



    private static class Wait implements Runnable {
        @Override
        public void run() {
                System.out.println(parkedThread+" 开始等待");
                LockSupport.park();
                System.out.println(parkedThread+" 被解锁解锁");
            }
        }


    private static class Notify implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000); // Thread.sleep() 不会释放锁
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Notify"+"线程开始被解锁"+parkedThread);
            LockSupport.unpark(parkedThread);//唤醒线程parkedThread

        }
    }


    /**
     * LockSupport很类似于二元信号量(只有1个许可证可供使用)，如果这个许可还没有被占用，当前线程获取许可并继续执行；如果许可已经被占用，当前线程阻塞，等待获取许可。
     */
    public static void test1(){
        Thread thread = Thread.currentThread();
        LockSupport.unpark(thread);//释放许可
//        LockSupport.unpark(thread);//释放许可
        LockSupport.park();// 获取许可
//        LockSupport.park();// 获取许可
        System.out.println("b");
    }


        public static void main(String[] args) throws Exception {

        test1();

        //
        new Thread(){
            @Override
            public void run() {
                synchronized (parkedThread){
                    System.out.println("得到锁"+parkedThread);
                    try {
                        Thread.sleep(100000); // Thread.sleep() 不会释放锁
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }.start();

        parkedThread.start();
        Thread.sleep(1000);
        Thread notifyThread = new Thread(new Notify(), "Notify Thread");
        notifyThread.start();
        }

}
