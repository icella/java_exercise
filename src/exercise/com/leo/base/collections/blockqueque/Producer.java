package exercise.com.leo.base.collections.blockqueque;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	protected BlockingQueue<String> queque = null;
	
	
	public Producer(BlockingQueue<String> queque) {
		this.queque = queque;
	}


	@Override
	public void run() {
		try {
			queque.put("1");
			Thread.sleep(1000);
			queque.put("2");
			Thread.sleep(1000);
			queque.put("3");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
