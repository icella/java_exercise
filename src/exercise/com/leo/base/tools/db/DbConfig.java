package exercise.com.leo.base.tools.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取数据库配置文件
 * @author lla
 *
 */
public class DbConfig {
  protected static Logger log = LoggerFactory.getLogger(DbConfig.class);
  
  public static Properties getProperties() {
//    String path = DbConfig.class.getResource("dbconfig.properties").getPath();
    String path = "target/classes/dbconfig.properties";
    System.out.println("Begin load database dbconfig.properties");
    try {
      System.out.println(path);
      FileInputStream fis = new FileInputStream(path);
      Properties p = new Properties();
      p.load(fis);
      System.out.println("Done!");
      return p;
    } catch (FileNotFoundException e) {
      log.error("该  \"" + path + " \" 路径下的文件没有找到!");
      e.printStackTrace();
    } catch (IOException e) {
      log.error("该  \"" + path + " \" 路径中的文件读取失败!");
      e.printStackTrace();
    }
    return null;
  }
}
