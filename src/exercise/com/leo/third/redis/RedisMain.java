package exercise.com.leo.third.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisMain {

  public static void main(String[] args) {
    // test connection
    Jedis jedis = new Jedis("127.0.0.1");
    System.out.println(jedis.ping());


    // test Strings
    jedis.set("name", "zzh");
    System.out.println(jedis.get("name"));

    // test list
    jedis.lpush("nblist", "jj");
    jedis.lpush("nblist", "jj");
    jedis.lpush("nblist", "yy");
    jedis.lpush("nblist", "qq");
    List<String> list = jedis.lrange("nblist", 0, -1);
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }

    // test set
    Set<String> set = jedis.keys("*");
    for (String key : set) {
      System.out.println(key);
    }
  }

}
