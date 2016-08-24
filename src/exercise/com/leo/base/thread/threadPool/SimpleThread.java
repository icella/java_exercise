package exercise.com.leo.base.thread.threadPool;

import org.apache.commons.pool2.impl.GenericObjectPool;

public class SimpleThread extends Thread{
	private boolean isRunning;
	private GenericObjectPool pool;
	
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
		
		if(isRunning)
			this.notify();
	}

	public void setPool(GenericObjectPool pool) {
		this.pool = pool;
	}

	public SimpleThread() {
		this.isRunning = false;
	}

	@Override
	public synchronized void run() {
		try {
			while(true){
				if(!isRunning){
					this.wait();
				} else{
					System.out.println(this.getName() + " beginning... ");
					sleep(5000);
					System.out.println(this.getName() + " finished.");
					setRunning(false);
					try {
						pool.returnObject(this);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
			
		} catch (InterruptedException e) {
			System.out.println("Be interrupted, this thread was closed.");
			return;
		}
	}

	@Override
	public void destroy() {
		System.out.println("Destroying");
		this.interrupt();
	}
}
