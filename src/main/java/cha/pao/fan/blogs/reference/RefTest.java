package cha.pao.fan.blogs.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * jvm 参数   -Xmx1M -Xms1M
 * @author pfchang
 * 
 * 设置堆大小为1M=1024kb=1024*1024b
 * 
 * 如下代码，会新建 1024*2048b的对象，没有堆内存溢出
 * 
 * 
 * 
 */

public class RefTest {
	
	public static void test1(){

		ReferenceQueue queue=new ReferenceQueue();
		Map<Object, Object> map = new HashMap<>();
		
		Thread thread = new Thread(() -> {
		    try {
		        int cnt = 0;
		        WeakReference<byte[]> k;
		        while((k = (WeakReference) queue.remove()) != null) {//remove 后 weakReference中引用的对象被清楚 但是WeakReference的实例还存在，如果想同事清楚 可以用WeakHashMap
		            System.out.println((cnt++) + "回收了:" + k+"		;map.size->" + map.size());
		        }
		    } catch(InterruptedException e) {
		    }
		});
//		thread.setDaemon(true);
		thread.start();
		
		
		Object value = new Object();
		for(int i = 0;i < 2048;i++) {
		    byte[] bytes = new byte[1024*1];
		    WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, queue);
		    map.put(weakReference, value);
		}
		System.out.println("map.size->" + map.size());

	
	}
	
	
	public static void test3(){
		Map<Object, Object> map = new HashMap<>();
		ReferenceQueue queue=new ReferenceQueue();
		Object value = new Object();
		for(int i = 0;i < 2048;i++) {
		    byte[] bytes = new byte[1024*1];
		    WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, queue);
		    map.put(weakReference, value);
		}
		System.out.println("map.size->" + map.size());
	}
	
	
	
	/**
	 * OutOfMemoryError: Java heap space
	 */
	public static void test2(){
		Map<Object, Object> map = new HashMap<>();
		Object value = new Object();
		for(int i = 0;i < 2048;i++) {
		    byte[] bytes = new byte[1024*1];
		    map.put(bytes, value);
		}
		System.out.println("map.size->" + map.size());
	}
	
	

	public static void main(String[] args) throws InterruptedException {
//		test2();
//		test1();
		test3();
		
	}

}
