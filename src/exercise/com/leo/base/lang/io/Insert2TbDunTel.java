package exercise.com.leo.base.lang.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import exercise.com.leo.base.tools.TimeUtils;
import exercise.com.leo.base.tools.db.TbDunTelExecute;

/**
 * 向tb_dun_tel插入新号码
 * @author lla
 *
 */
public class Insert2TbDunTel {
  protected static Logger log = LoggerFactory.getLogger(Insert2TbDunTel.class);
  
  public static void main(String[] args) throws IOException {
    fileLineUnion();
  }
  
  /**
   * 两个手机号码文件，求交集后，插入tb_dun_tel
   */
  private static void fileLineUnion() throws IOException {
    String path = "/home/lla/Documents/test_daily/2017/1/dun_tel_0116";
    
    Map<String, Integer> orgTypeMap = Maps.newHashMap();
    orgTypeMap.put("银行", 10);
    orgTypeMap.put("互联网金融", 15);
    orgTypeMap.put("消费金融", 17);
    orgTypeMap.put("催收公司", 20);
    orgTypeMap.put("律师事务所", 25);
    
    try {
      String tbName = "tb_dun_tel";
      TbDunTelExecute execute = new TbDunTelExecute();
      LineIterator it = FileUtils.lineIterator(new File(path));
      int counter  = 0;
      while (it.hasNext()) {
        String line = it.nextLine().trim();
        if(StringUtils.isEmpty(line))
          continue;
        
        String[] spliter = line.split("\t", -1);

        final String phoneNumber = spliter[0];
        if(!StringUtils.isNumeric(phoneNumber))
          continue;

        final String adid = StringUtils.isEmpty(spliter[1]) ? "80" : spliter[1];
        final String orgName = StringUtils.isEmpty(spliter[2]) ? null : spliter[2];
        String orgType = null;
        if(spliter.length == 4){
          orgType = spliter[3];
        }
        final String otid = orgType;

        Map<String, String> existsPair =  Maps.newHashMap();
        existsPair.put("tel", phoneNumber);
        
        boolean isExists = execute.isRecordExists(tbName, existsPair);
//        final String otid = StringUtils.isNotEmpty(orgType) ? String.valueOf(orgTypeMap.get(orgType)) : null;
        counter++;
        if(isExists){
          log.info(counter + " : " + phoneNumber + "已经存在.");
          if(StringUtils.isBlank(otid)){
            continue;
          }
            
          Map<String, String> wherePair = Maps.newHashMap();
          wherePair.put("tel", phoneNumber);
          
          Map<String, String> updatePair = Maps.newHashMap();
          updatePair.put("adid", adid);
          updatePair.put("otid", otid);
          updatePair.put("org_name", orgName);
          
          execute.executeSql(tbName, updatePair, wherePair , "update");
          log.info(counter + " : " + phoneNumber + "更新" + tbName);
        } else {
          Map<String, String> insertPair = new HashMap<String, String>(){
            private static final long serialVersionUID = 1L;
            {
              put("tel", phoneNumber);
              put("adid", adid);
              put("source", "25");
              put("ad_audit", "0");
              put("gather_time", "\"\"");
              put("last_gather_time", "\"\"");
              put("tagging_done", "1");
              put("auto_dial_done", "0");
              put("exist_barrage", "0");
              put("exist_record", "0");
              put("locked", "0");
              put("org_name", orgName);
              put("otid", otid);
              put("create_time", TimeUtils.dateToString(new Date()));
            }
          };
          
          execute.executeSql(tbName, insertPair, null , "insert");
          log.info(phoneNumber + "插入" + tbName);
        }
      }

//      FileUtils.writeLines(new File(path + "errorNumber"), errorNumbers);
//      log.info("处理出错的文件保存在" + path + "errorNubmer");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static List<String> union(List<String> ls1, List<String> ls2){
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
