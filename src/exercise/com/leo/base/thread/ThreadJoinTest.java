package exercise.com.leo.base.thread;

import java.util.Random;

public class ThreadJoinTest {
	private final static int COUNTER = 100000000;

	static class Computer extends Thread{
		private int start;
		private int end;
		private int result;
		private int[] array;
		public Computer(int[] array, int start, int end) {
			super();
			this.start = start;
			this.end = end;
			this.array = array;
		}
		public int getResult() {
			return result;
		}
		@Override
		public void run() {
			for (int i = start; i < end; i++) {
				result += array[i];
				if(result < 0) result &= Integer.MAX_VALUE;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		int[] array = new int[COUNTER];
		Random random = new Random();
		for (int i = 0; i < COUNTER; i++) {
			array[i] = Math.abs(random.nextInt());
		}
		
		long start = System.currentTimeMillis();
		Computer computer1 = new Computer(array, 0, COUNTER/4);
		Computer computer2 = new Computer(array, COUNTER/4, COUNTER/2);
		Computer computer3 = new Computer(array, COUNTER/2, COUNTER*3/4);
		Computer computer4 = new Computer(array, COUNTER*3/4, COUNTER);
		computer1.start();
		computer2.start();
		computer3.start();
		computer4.start();
		computer1.join();
		computer2.join();
		computer3.join();
		computer4.join();
		
		System.out.println(System.currentTimeMillis() - start);
	}
}
