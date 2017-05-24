package exercise.com.leo.third.redis.springredis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lla on 17-4-13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-redis.xml"})
public class RedisDataUtilTest {
    @Autowired
    RedisDataUtil util;

    @Test
    public void testListClients(){
        util.listClients();
    }

    @Test
    public void testSetKey() throws InterruptedException {
        String key = "phoneinfo:id";
        util.setKey(key, "2");

        Assert.assertEquals("2", util.getKey(key));

        util.setKeyExpire(key, 10000);
        Thread.sleep(6000);

        Assert.assertEquals("1", util.getKey(key));

      /*  util.delKey(key);
        Assert.assertEquals("1", util.getKey(key));*/
    }
}
