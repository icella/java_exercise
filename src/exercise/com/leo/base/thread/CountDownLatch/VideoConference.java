package exercise.com.leo.base.thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lla on 17-2-13.
 */
public class VideoConference implements  Runnable{
    private final CountDownLatch controller;

    public VideoConference(CountDownLatch controller) {
        this.controller = controller;
    }

    public void arrive(String name){
        System.out.printf("%s has arrived.\n", name);
        controller.countDown();
        System.out.printf("看房: Waiting for %d participants. \n", controller.getCount());
    }

    @Override public void run() {
        System.out.printf("看房: Initialization: %d participants. \n", controller.getCount());
        try{
            controller.await();
            System.out.printf("看房: All participants have com;\n");
            System.out.printf("看房: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
