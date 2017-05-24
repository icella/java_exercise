package exercise.com.leo.base.guava.util;

import org.junit.Test;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {

//  @Before
//  public void setUp() throws Exception {}

  @Test
  public void testWithRateLimiter() {
    Long start = System.currentTimeMillis();
    RateLimiter limiter = RateLimiter.create(1.0);
    
    for (int i = 0; i < 10; i++) {
      limiter.acquire();
      Long end = System.currentTimeMillis();
      System.out.println(end - start);
      System.out.println("call execute" + i);
    }
    
    Long end = System.currentTimeMillis();
    System.out.println(end - start);
  }
  
  

}
