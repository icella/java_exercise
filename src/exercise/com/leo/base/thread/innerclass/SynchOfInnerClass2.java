package exercise.com.leo.base.thread.innerclass;

public class SynchOfInnerClass2 {

	class InnerClass{
		private int id = 0;
		private Object object = new Object();
		
		public void service1() throws InterruptedException{
			synchronized(object){
				for(int i = 0; i < 5; i++){
					System.out.println(Thread.currentThread().getName() + " id:" + id++);
					Thread.sleep(50);
				}
			}
		}
		
		public synchronized void service2() throws InterruptedException{
			synchronized(object){
				for(int i = 0; i < 5; i++){
					System.out.println(Thread.currentThread().getName() + " id:" + id++);
					Thread.sleep(100);
				}
			}
		}
	}
	
	public InnerClass getInnerClass(){
		return new InnerClass();
	}
	
	public static void main(String[] args) {
		SynchOfInnerClass2 synchOfInnerClass = new SynchOfInnerClass2();
		final InnerClass innerClass = synchOfInnerClass.getInnerClass();
		
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					innerClass.service1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		
		Thread thread2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					innerClass.service2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread2.start();
	}

}
