package exercise.com.leo.base.thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;


public class MainApp {
    public static void main(String[] args){
        VideoConference conference = new VideoConference(new CountDownLatch(3));

        Thread threadConference = new Thread(conference);
        threadConference.start();

        /*for(int i = 0; i < 10; i++){
            Participants p = new Participants(conference, "Participant"+i);
            Thread t = new Thread(p);
            t.start();
        }*/

        Participants p = new Participants(conference, "地瓜");
        Thread t = new Thread(p);
        t.start();

        p = new Participants(conference, "小范");
        t = new Thread(p);
        t.start();

        p = new Participants(conference, "小唐");
        t = new Thread(p);
        t.start();
    }
}
