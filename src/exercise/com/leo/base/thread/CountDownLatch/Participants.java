package exercise.com.leo.base.thread.CountDownLatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by lla on 17-2-13.
 */
public class Participants implements Runnable{
    private VideoConference conference;
    private String name;

    public Participants(VideoConference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    @Override public void run() {
        long duration = (long)(Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        conference.arrive(name);
    }
}
