package exercise.com.leo.base.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableTest {
	
	static class TaxCalculator implements Callable<Integer>{
		private int seedMoney;
		public TaxCalculator(int _seedMoney) {
			seedMoney = _seedMoney;
		}
		
		@Override
		public Integer call() throws Exception {
			TimeUnit.MILLISECONDS.sleep(1000);
			System.out.println(seedMoney);
			return seedMoney / 10;
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(4);
		Future<Integer> future = es.submit(new TaxCalculator(100));
		while(!future.isDone()){
			TimeUnit.MILLISECONDS.sleep(200);
			System.out.print("#");
		}
		System.out.println("\n Complete at:" + future.get() + "yuan");
		es.shutdown();
	}

}
