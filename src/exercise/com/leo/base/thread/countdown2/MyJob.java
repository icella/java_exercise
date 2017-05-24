package exercise.com.leo.base.thread.countdown2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyJob implements Callable<Map<String, Object>> {

	private final int id;
	private final CountDownLatch latch;
	private final AtomicBoolean over;

	public MyJob(int id, CountDownLatch latch, AtomicBoolean over) {
		this.id = id;
		this.latch = latch;
		this.over = over;
	}

	public Map<String, Object> call() throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			long interval = randomLong(0, 10000); // 执行本次任务0 秒 ~ 10 秒
			// 随机, 比如模拟执行db查询
			try {
				Thread.sleep(interval);
			} catch (Exception e) {
			}
			String name = "工人_" + id;
			String prefix;

			if (over.get()) {
				prefix = "[超时完成] " + name + "\t" + interval;
			} else {
				prefix = "[按时完成] " + name;
			}

			map.put(name, interval);
			String msg = prefix + " finished.";
			System.out.println(msg);

			return map;
		} finally {
			latch.countDown();
		}
	}

	public static long randomLong(long from, long to) {
		return (long) Math.floor(rn.nextDouble() * (to - from) + from);
	}

	private static final Random rn = new Random(System.currentTimeMillis());

}
