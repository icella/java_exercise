package exercise.com.leo.base.thread;

public class ThreadLocalExample {
	
	public static class MyRunnable implements Runnable{
		private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
		
		@Override
		public void run() {
			threadLocal.set((int)(Math.random() * 1000));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadLocal.get());
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		MyRunnable shareRunnableInstance = new MyRunnable();
		
		Thread thread1 = new Thread(shareRunnableInstance);
		Thread thread2 = new Thread(shareRunnableInstance);
		
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();
	}
}
