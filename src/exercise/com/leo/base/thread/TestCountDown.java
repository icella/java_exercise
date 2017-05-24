package exercise.com.leo.base.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestCountDown {
  private final static int GROUP_SIZE = 5;
  private final static Random RANDOM = new Random();
  
  public static void main(String[] args) throws InterruptedException{
    processOneGroup("Group1");
    processOneGroup("Group2");
  }

  private static void processOneGroup(final String groupName) throws InterruptedException{
    final CountDownLatch preCountDown = new CountDownLatch(GROUP_SIZE);
    final CountDownLatch startCountDown = new CountDownLatch(1);
    final CountDownLatch endCountDown = new CountDownLatch(GROUP_SIZE);
    
    System.out.println("====>\n Grouping: " + groupName + "Start competing: ");
    
    for (int i = 0; i < GROUP_SIZE; i++) {
      new Thread(String.valueOf(i)){

        @Override
        public void run() {
          preCountDown.countDown();
          System.out.println("I'm threadGroup: [" + groupName + "], the " + this.getName() + " thread, I'm ready!");
          
          try {
            startCountDown.countDown();
            startCountDown.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          
          try {
            Thread.sleep(Math.abs(RANDOM.nextInt() % 1000));
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          
          System.out.println("I'm threadGroup: [" + groupName + "], the " + this.getName() + " thread, I'm finished!");
          endCountDown.countDown();
        }
        
      }.start();
      
      preCountDown.await();
      System.out.println("Ready, Go!");
      startCountDown.countDown();
      try {
        endCountDown.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(groupName + "Compete ending!");
    }
  }

}
