package exercise.com.leo.base.thread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

public class TreadPoolTest {

	static class MyRunnable implements Runnable {
		private final long countUntil;

		MyRunnable(long countUntil) {
			this.countUntil = countUntil;
		}

		@Override
		public void run() {
			long sum = 0;
			for (long i = 1; i < countUntil; i++) {
				sum += i;
			}
			System.out.println(sum);
		}
	}

	static class MyCallable implements Callable<Long> {
		@Override
		public Long call() throws Exception {
			long sum = 0;
			for (long i = 0; i <= 100; i++) {
				sum += i;
			}
			return sum;
		}

	}

	private static final int NTHREDS = 10;

	public static void main(String[] args) throws InterruptedException {
		useRunnable();
		
		useCallable();
	}

	private static void useCallable() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		List<Future<Long>> list = Lists.newArrayList();
		for (int i = 0; i < 20000; i++) {
			Callable<Long> worker = new MyCallable();
			Future<Long> submit = executor.submit(worker);
			list.add(submit);
		}
		
		long sum = 0;
		System.out.println(list.size());
		
		for (Future<Long> future : list) {
			try {
				sum += future.get();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sum);
		executor.shutdown();
	}

	private static void useRunnable() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		for (int i = 0; i < 5; i++) {
			Runnable worker = new MyRunnable(10000000L + i);
			executor.execute(worker);
		}
		executor.shutdown();
		executor.awaitTermination(5L, TimeUnit.MINUTES);
		System.out.println("Finished all threads");
	}

}
