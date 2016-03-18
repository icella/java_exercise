package exercise.com.leo.base.thread;

public class ThreadNew {

	public static void main(String[] args) {
		Thread threadNew = new Thread(){

			@Override
			public void run() {
				System.out.println(
						"我是被创建的线程，我执行了..." + Thread.currentThread().getName() + "\t" + Thread.currentThread().getId());

			}

		};
		threadNew.start();
		System.out.println(
				"main process end..." + Thread.currentThread().getName() + "\t" + Thread.currentThread().getId());
	}

}
