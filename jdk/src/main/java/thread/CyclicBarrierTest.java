package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	public static void main(String[] args) {
        CyclicBarrier barrier  = new CyclicBarrier(4,new Runnable() {//
			
			@Override
			public void run() {
				System.out.println("---------到达屏障------------");
				
			}
		});
        System.out.println(barrier.getParties());
        System.out.println(barrier.getNumberWaiting());
        for(int i=0;i<4;i++){
            new Writer(barrier).start();
            
//            barrier.getNumberWaiting()
            
        }
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        
        private boolean isReset; 
        
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }
        
        public Writer(CyclicBarrier cyclicBarrier, boolean isReset) {
			this.cyclicBarrier = cyclicBarrier;
			this.isReset = isReset;
		}

		@Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                System.out.println("线程"+Thread.currentThread().getName()+cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
                if(isReset)
                	cyclicBarrier.reset();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }

}
