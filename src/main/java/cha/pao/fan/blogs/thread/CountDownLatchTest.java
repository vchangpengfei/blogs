package cha.pao.fan.blogs.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * aqs 共享锁实现
 */
public class CountDownLatchTest {
	static CountDownLatch c = new CountDownLatch(2);
	static ExecutorService pool = Executors.newFixedThreadPool(2);

	public static void main(String agrs[]) {

		pool.execute(new Runnable() {
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("This is A");
					c.countDown();
			}
		});

		pool.execute(new Runnable() {
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("This is B");
				c.countDown();
			}
		});

		try {
			c.await();
		} catch (InterruptedException e) {

		}
		pool.shutdown();
		System.out.println("This is main");
	}
}
