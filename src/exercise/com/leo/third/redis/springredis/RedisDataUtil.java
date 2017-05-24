package exercise.com.leo.third.redis.springredis;

import exercise.com.leo.third.redis.script.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lla on 17-4-13.
 */
@Service
public class RedisDataUtil {
    @Autowired
    public RedisTemplate template;

    public void listClients(){
        List<RedisClientInfo> clients = template.getClientList();
        for (RedisClientInfo client : clients) {
            System.out.println(client.toString());
        }
    }

    public void setKey(String key, String value){
        template.opsForValue().set(key, value);
    }

    public String getKey(String key){
        String value = String.valueOf(template.opsForValue().get(key));

        return value;
    }

    public void setKeyExpire(String key, long timeout){
        template.expire(key, timeout, TimeUnit.SECONDS);
    }

    public void delKey(String key){
        template.delete(key);
    }
}
