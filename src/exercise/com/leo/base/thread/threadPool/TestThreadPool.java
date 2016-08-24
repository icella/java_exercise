package exercise.com.leo.base.thread.threadPool;

import org.apache.commons.pool2.impl.GenericObjectPool;

public class TestThreadPool {
	public static void main(String[] args){
		GenericObjectPool<SimpleThread> pool = new GenericObjectPool<SimpleThread>(new MyPoolableObjectFactory());
		pool.setMaxTotal(20);
		pool.setMaxIdle(20);
		pool.setMaxWaitMillis(100);
		pool.setTimeBetweenEvictionRunsMillis(600000);
		pool.setNumTestsPerEvictionRun(-1);
		pool.setMinEvictableIdleTimeMillis(3000);
		
		for (int i = 0; i < 20; i++) {
			try {
				SimpleThread simpleThread = pool.borrowObject();
				simpleThread.setPool(pool);
				simpleThread.setRunning(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("--------------------------");
		for (int i = 0; i < 10; i++) {
			try {
				SimpleThread simpleThread = pool.borrowObject();
				simpleThread.setPool(pool);
				simpleThread.setRunning(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
