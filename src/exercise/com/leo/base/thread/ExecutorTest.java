package exercise.com.leo.base.thread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

public class ExecutorTest {

	static class MyRunnable implements Runnable{
		private final long countUntil;
		
		public MyRunnable(long countUnil) {
			this.countUntil = countUnil;
		}
		
		@Override
		public void run() {
			long sum = 0;
			for (int i = 0; i < countUntil; i++) {
				sum += i;
			}
			System.out.println(sum);
		}
	}
	
	static class StrRunnable implements Runnable{
		private final String name;
		public StrRunnable(String name) {
			this.name = name;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				System.out.println(name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	static class CallCallable implements Callable<Long> {
		@Override
		public Long call() throws Exception {
			long sum = 0;
			for (long i = 0; i <= 50; i++) {
				sum += i;
			}
			System.out.println(sum);
			return sum;
		}
	}
	
	static class Call2Callable implements Callable<Long> {
		@Override
		public Long call() throws Exception {
			long sum = 0;
			for (long i = 51; i <= 100; i++) {
				sum += i;
			}
			System.out.println(sum);
			return sum;
		}
	}
	
	private static final int NTHREDS  = 10;
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		for (int i = 0; i < 15; i++) {
			Runnable worker = new MyRunnable(i + 1);
			StrRunnable strWorder = new StrRunnable(i + "s");
			executor.submit(worker);
			executor.submit(strWorder);
		}
		
		List<Callable<Long>> list = Lists.newArrayList();
		list.add(new CallCallable());
		list.add(new Call2Callable());
		List<Future<Long>> result = executor.invokeAll(list);
		long sum = 0;
		for (Future<Long> future : result) {
			try {
				sum += future.get();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sum);
		
		//This will make the executor accept no new threads
		//and finish all existing threads in the queue.
		executor.shutdown();
		//Wait until all threads are finish.
		executor.awaitTermination(100L, TimeUnit.SECONDS);
		System.out.println("Finished all threads.");
	}

}
