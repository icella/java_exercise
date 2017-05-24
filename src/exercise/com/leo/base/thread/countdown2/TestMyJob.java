package exercise.com.leo.base.thread.countdown2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestMyJob {

	public static void main(String[] args) throws Exception {
      // 主线程
		ExecutorService executor = Executors.newFixedThreadPool(5);// Pool
		int jobCount = 5;
		// 比如: 我要执行10个任务
		CountDownLatch latch = new CountDownLatch(5);
		AtomicBoolean over = new AtomicBoolean(false);
		List<Future<Map<String, Object>>> futures = new ArrayList<Future<Map<String, Object>>>();

		long start = System.currentTimeMillis();
		for (int i = 1; i <= jobCount; i++) {
			futures.add(executor.submit(new MyJob(i, latch, over)));
		}

		System.out.println("我只给这些任务5秒钟去执行,5秒钟之后我来检查...");

		latch.await(5, TimeUnit.SECONDS);

		over.set(true);
		System.out.println("好了,时间到,我来看结果: ");

		for (Future<Map<String, Object>> future : futures) {
//			System.out.println("结果:" + future.get(0,TimeUnit.SECONDS));
			try {
				System.out.println("结果:" + future.get(0, TimeUnit.SECONDS));
//				System.out.println("结果:" + future.get());
			} catch (Exception e) {
				future.cancel(false);
			}
		}

		// System.in.read();
		executor.shutdown();

		System.out.println("Game Over. " + (System.currentTimeMillis() - start) + " ms.");
	}

}
