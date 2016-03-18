package exercise.com.leo.base.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCyclicBarrier {
	// 徒步需要的时间： Shenzhen, Guangzhou, Shaoguan, Changsha, Wuhan
	private static int[] timeWalk = { 5, 8, 15, 15, 10 };

	// 自驾游
	private static int[] timeSelf = { 1, 3, 4, 4, 5 };

	// 旅游大巴
	private static int[] timeBus = { 2, 4, 6, 6, 7 };

	static String now() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date()) + ":";
	}

	static class Tour implements Runnable {
		private int[] times;
		private CyclicBarrier barrier;
		private String tourName;

		public Tour(int[] times, CyclicBarrier barrier, String tourName) {
			this.times = times;
			this.barrier = barrier;
			this.tourName = tourName;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(times[0] * 1000);
				System.out.println(now() + tourName + " Reached Shenzhen");
				//当所有线程都调用了await()后，就表示这些线程都可以继续执行，否则就会等待。 
				barrier.await();

				Thread.sleep(times[1] * 1000);
				System.out.println(now() + tourName + " Reached Guangzhou");
				barrier.await();

				Thread.sleep(times[2] * 1000);
				System.out.println(now() + tourName + " Reached Shaoguan");
				barrier.await();

				Thread.sleep(times[3] * 1000);
				System.out.println(now() + tourName + " Reached Changsha");
				barrier.await();

				Thread.sleep(times[4] * 1000);
				System.out.println(now() + tourName + " Reached Wuhan");
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3);
		ExecutorService exec = Executors.newFixedThreadPool(3);
		exec.submit(new Tour(timeWalk, barrier, "WalkTour"));
		exec.submit(new Tour(timeSelf, barrier, "SelfTour"));
		exec.submit(new Tour(timeBus, barrier, "BusTour"));
		exec.shutdown();
	}

}
