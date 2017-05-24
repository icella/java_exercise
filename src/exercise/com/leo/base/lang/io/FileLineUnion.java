package exercise.com.leo.base.lang.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import exercise.com.leo.base.tools.TimeUtils;
import exercise.com.leo.base.tools.db.TbDunTelExecute;

/**
 * 向tb_dun_tel插入新号码
 * @author lla
 *
 */
public class FileLineUnion {
  protected static Logger log = LoggerFactory.getLogger(FileLineUnion.class);
  
  public static void main(String[] args) {
    fileLineUnion();
  }

  /**
   * 两个手机号码文件，求交集后，插入tb_dun_tel
   */
  private static void fileLineUnion() {
    String path = "/home/lla/Documents/test_daily/2016/12/15/";
    
    try {
     /* List<String> firstTest =
          FileUtils.readLines(new File(path + "number_1103"));
      Set<String> firstSet = Sets.newHashSet(firstTest);

      List<String> secondTest =
          FileUtils.readLines(new File(path + "number_1111"));
      Set<String> secondSet = Sets.newHashSet(secondTest);

      List<String> reList = union(Lists.newArrayList(firstSet), Lists.newArrayList(secondSet));

      log.info(String.valueOf(firstSet.size()));
      log.info(String.valueOf(secondSet.size()));
      log.info(String.valueOf("总的sender号码数： " + Sets.newHashSet(reList).size()));*/
      
      
      List<String> firstTest = FileUtils.readLines(new File(path + "contact_result.txt"));
      Set<String> firstSet = Sets.newHashSet(firstTest);
      log.info(String.valueOf(firstSet.size()));

      List<String> errorNumbers = Lists.newArrayList();
      String tbName = "tb_dun_tel";
      for (final String phoneNumber : firstTest) {
        try {
          TbDunTelExecute execute = new TbDunTelExecute();
          Map<String, String> existsPair = new HashMap<String, String>(){
            private static final long serialVersionUID = 1L;
            {
              put("tel", phoneNumber);
            }
          };
          
          boolean isExists = execute.isRecordExists(tbName, existsPair);
          if(isExists){
            log.info(phoneNumber + "已经存在.");
          } else {
            Map<String, String> insertPair = new HashMap<String, String>(){
              private static final long serialVersionUID = 1L;
              {
                put("tel", phoneNumber);
                put("adid", null);
                put("source", "25");
                put("ad_audit", "0");
                put("gather_time", "\"\"");
                put("last_gather_time", "\"\"");
                put("tagging_done", "1");
                put("auto_dial_done", "0");
                put("exist_barrage", "0");
                put("exist_record", "0");
                put("locked", "0");
                put("create_time", TimeUtils.dateToString(new Date()));
              }
            };
            
            execute.executeSql(tbName, insertPair, null , "insert");
            log.info(phoneNumber + "插入" + tbName);
          }
        } catch (Exception e) {
          e.printStackTrace();
          errorNumbers.add(phoneNumber);
          continue;
        }
      }

      FileUtils.writeLines(new File(path + "errorNumber"), errorNumbers);
      log.info("处理出错的文件保存在" + path + "errorNubmer");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static List<String> union(List<String> ls1, List<String> ls2) {
    List<String> list = copyList(ls1);
    list.addAll(ls2);

    return list;
  }

  private static List<String> copyList(List<String> ls) {
    List<String> list = new ArrayList<String>(ls);
    Collections.copy(list, ls);
    return list;
  }
}
