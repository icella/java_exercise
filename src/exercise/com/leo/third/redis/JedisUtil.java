package exercise.com.leo.third.redis;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
  private Logger logger = LoggerFactory.getLogger(JedisUtil.class);
  
  private JedisUtil(){}
  private static class JedisUtilHolder {
    private static final JedisUtil instance = new JedisUtil();
  }
  
  public static JedisUtil getInstance(){
    return JedisUtilHolder.instance;
  }
  
  private static Map<String, JedisPool> maps = Maps.newHashMap();
  
  private static JedisPool getPool(String ip, int port) {
    String key = ip + ":" + port;
    JedisPool pool = null;
    if(!maps.containsKey(key)){
      JedisPoolConfig config = new JedisPoolConfig();
      config.setMaxIdle(RedisConfig.MAX_IDLE);
      config.setMaxWaitMillis(RedisConfig.MAX_WAIT);
      config.setTestOnBorrow(true);
      config.setTestOnReturn(true);
      
      pool = new JedisPool(config, ip, port, RedisConfig.TIMEOUT);
      maps.put(key, pool);
    } else {
      maps.get(key);
    }
    
    return pool;
  }
  
  public Jedis getJedis(String ip, int port){
    Jedis jedis = null;
    int count = 0;
    do{
      try {
        jedis = getPool(ip, port).getResource();
        count++;
      } catch (Exception e) {
        logger.error("get redis master1 failed!", e);
        getPool(ip, port).returnBrokenResource(jedis);
      }
    }while(jedis == null && count < RedisConfig.RETRY_NUM);
    
    return jedis;
  }
  
  public void colseJedis(Jedis jedis, String ip, int port){
    if(jedis != null){
      getPool(ip, port).returnResource(jedis);
    }
  }
}
