package exercise.com.leo.base.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadCreate {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("runnable1 running.");
			}
		});
		
		try {
			Future<String> future1 = executor.submit(new Callable<String>() {
				
				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					return "result.";
				}
			});
			System.out.println("task1:" + future1.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} 
	}

}
