package cha.pao.fan.blogs.thread;

import java.util.concurrent.*;

public class ThreadPoolTest {
    // 核心线程数量
    private static int corePoolSize = 2;
    // 最大线程数量
    private static int maxPoolSize = 3;
    // 线程存活时间：当线程数量超过corePoolSize时，10秒钟空闲即关闭线程
    private static int keepAliveTime = 10;
    // 缓冲队列
    private static BlockingQueue<Runnable> workQueue  = new LinkedBlockingQueue<Runnable>(5);
    // 线程池
    private static ThreadPoolExecutor threadPoolExecutor = null;

    private static ThreadFactory threadFactory=new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,r+"--chang");
        }
    };
    private static RejectedExecutionHandler rejectedExecutionHandler=new ThreadPoolExecutor.AbortPolicy();



    static {
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
                workQueue,threadFactory,rejectedExecutionHandler){
            @Override
            protected void terminated() {
                System.out.println("=====================is over==============");
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 20; i++) {
                try{
                    threadPoolExecutor.execute(new MyTask());
                    System.out.println("线程池中正在执行的线程数量：" + threadPoolExecutor.getPoolSize());
                    System.out.println("线程池缓存的任务队列数量：" + threadPoolExecutor.getQueue().size());
                }catch (Throwable e){

                }

            }
        threadPoolExecutor.shutdown();
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println(this.toString());
    }
}