package exercise.com.leo.third.redis.script;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisClient {
    private static JedisPool jedisPool; //非切片连接池

    static {
        init();
    }

    /**
     * 初始化非切片池
     */
    private static void init() {
        //池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(10001);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config, "127.0.0.1", 6379);
    }

    public static void set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        }
    }

    public static void set(String key, String value, int second) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.setex(key, second, value);
        }
    }

    public static String get(String key, String defaultValue) {
        String value = defaultValue;
        try (Jedis jedis = jedisPool.getResource()) {
            String temp = jedis.get(key);
            if (temp != null) {
                value = temp;
            }
        }
        return value;
    }

    public static void incrBy(String key, long count) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.incrBy(key, count);
        }
    }
    
  public static boolean acquire(String pname, long timestamp, int limitNumber, Rate rate) throws Exception {
    String prefix = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
    prefix=prefix.replace("file:", "").replace("classes\\", ""); 
    prefix = prefix + "script/";
    ///home/lla/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/upc_portal/WEB-INF/classe
    String luaScript = FileUtils.readFileToString(new File("/home/lla/workspace/upc_portal/WebRoot/script/limit.lua"));
    String key = pname/* + timestamp / 1000*/; // 此处将当前时间戳取秒数
    
    int clock = 1;
    if (rate == Rate.SECOND) {
        clock = clock * 1;  //  定时1秒钟
    } else if (rate == Rate.MINUTE) {
        clock = clock * 60;  //  定时一分钟
    } else if (rate == Rate.HOUR) {
        clock = clock * 60 * 60;  //  定时一小时
    } else if (rate == Rate.DAY) {
        clock = clock * 60 * 60 * 24;  //   定时一天
    }
    
    try (Jedis jedis = jedisPool.getResource()) {
      Object obj = jedis.eval(luaScript, Lists.newArrayList(pname), Lists.newArrayList(String.valueOf(limitNumber), String.valueOf(clock)));
      return (Long)obj == 1;
    }
  }
}
