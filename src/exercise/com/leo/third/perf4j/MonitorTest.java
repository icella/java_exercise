package exercise.com.leo.third.perf4j;

import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lla on 17-4-6.
 */
public class MonitorTest {
    private static final Logger logger = LoggerFactory.getLogger(MonitorTest.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("start");
        contact("email");
        contact("phone");
//        method1();
//            method2();
//            method3();
        logger.info("stop");
    }

    private static void contact(String signal)  {
        StopWatch stopWatch = new LoggingStopWatch(signal);
        try {
            Thread.sleep((long) (Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(signal);
        stopWatch.stop();
    }

    private static void method1()  {
        StopWatch stopWatch = new LoggingStopWatch("method1");
        try {
            Thread.sleep((long) (Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
    }

    private static void method2() throws InterruptedException {
        StopWatch stopWatch = new LoggingStopWatch();
        Thread.sleep((long) (Math.random()*1000));
        stopWatch.lap("block1");
        Thread.sleep((long) (Math.random()*1000));
        stopWatch.lap("block2");
        Thread.sleep((long) (Math.random()*1000));
        stopWatch.lap("block3");
        Thread.sleep((long) (Math.random()*1000));
        stopWatch.lap("block4");
        Thread.sleep((long) (Math.random()*1000));
        stopWatch.stop("method2Stop");
    }

    private static void method3(){
        StopWatch stopWatch = new LoggingStopWatch();
        try {
            long sleepTime = (long)(Math.random() * 1000L);
            Thread.sleep(sleepTime);
            if (sleepTime > 500L) {
                throw new Exception("Throwing exception");
            }
            stopWatch.stop("codeBlock2.success", "Sleep time was < 500 ms");
        } catch (Exception e) {
            stopWatch.stop("codeBlock2.failure", "Exception was: " + e);

        }

    }
}
