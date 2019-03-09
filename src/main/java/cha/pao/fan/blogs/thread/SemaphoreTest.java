package cha.pao.fan.blogs.thread;

import java.util.concurrent.Semaphore;

/**
 * aqs共享锁实现
 */
public class SemaphoreTest {

	public static void main(String[] args) throws InterruptedException {
        int N = 8;            //工人数
        Semaphore semaphore = new Semaphore(5); //机器数目
        for(int i=0;i<8;i++){
            semaphore.acquire();
            new Worker(i,semaphore).start();
        }

    }

    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }
         
        @Override
        public void run() {
            try {
//                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();           
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
