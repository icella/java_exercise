package exercise.com.leo.sms;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * 过滤短信原文中的手机号码，去掉86,+86
 * @author lla
 *
 */
public class ParsePhoneNumber {
  private final static Logger logger = LoggerFactory.getLogger(ParsePhoneNumber.class);
  static final String MOBILE_REG_EXP  = "(?:(\\(\\+?86\\))((13[0-9]{1})|(14[57]{1})|(15[0-3,5-9]{1})|(17[01678]{1})|(18[0-9]{1}))+\\d{8})|" +     
      "(?:86-?((13[0-9]{1})|(14[57]{1})|(15[0-3,5-9]{1})|(17[01678]{1})|(18[0-9]{1}))+\\d{8})|" +
      "(?:((13[0-9]{1})|(14[57]{1})|(15[0-3,5-9]{1})|(17[01678]{1})|(18[0-9]{1}))+\\d{8})";
  static final String SMS_PARENT_PATH = "/home/lla/Documents/test_daily/2016/10/28/sms_content/";
  static  String SMS_CONTENT_PATH = SMS_PARENT_PATH + "yes_yes";
  static  String SMS_CONTENT_PATH2 = SMS_PARENT_PATH + "result";
  private static final String DECOLLATOR = "\t";
  
  public static void main(String[] args) {
    try {
      List<String> lines = FileUtils.readLines(new File(SMS_CONTENT_PATH));
      
      int over3Count = 0;
      int over3PhoneCount = 0;
      int totalPhoneCounter = 0;
      List<String> newLines = Lists.newArrayList();
      
      List<String> over3Lines = Lists.newArrayList();
      for (String line : lines) {
        try {
          String[] lineSplit = line.split("\t", -1);
          String sender = lineSplit[0];
          String phone = lineSplit[1];
          String org = lineSplit[2];
          String content = lineSplit[3];
          
          Map<String, String> matchMap = Maps.newHashMap();
          Pattern pattern = Pattern.compile(MOBILE_REG_EXP);
          Matcher matcher = pattern.matcher(content);
          int tempInt = 0;
          while(matcher.find()){
            matchMap.put(String.valueOf(tempInt++), matcher.group());
          }
          
          tempInt = 0;
          totalPhoneCounter += matchMap.size();
          if(matchMap.size() > 3){
            ++over3Count;
            over3PhoneCount += matchMap.size();
            
            for (Entry<String, String> entry : matchMap.entrySet()) {
              logger.info(entry.getKey() + " :: " + entry.getValue());
              over3Lines.add(line);
            }
          } else {
            String phone1 = "";
            String phone2 = "";
             if(matchMap.size() == 1){
               phone1 = matchMap.get("0");
             } else if(matchMap.size() == 2){
               phone1 = matchMap.get("0");
               phone2 = matchMap.get("1");
             } else if(matchMap.size() == 3){
               phone1 = matchMap.get("0");
               phone2 = matchMap.get("2");
             }
            
            StringBuilder stringBuilder = new StringBuilder(sender);
            stringBuilder.append(DECOLLATOR)
            .append(phone).append(DECOLLATOR)
            .append(org).append(DECOLLATOR)
            .append(content).append(DECOLLATOR)
            .append(phone1).append(DECOLLATOR)
            .append(phone2);
            newLines.add(stringBuilder.toString());
//            logger.info(stringBuilder.toString());
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          continue;
        }
      }
      logger.info("文本总共包含电话号码的个数: " + String.valueOf(totalPhoneCounter));
      logger.info("单条文本包含电话号码超过3个的条数: " + String.valueOf(over3Count) + " \t 共包含的电话号码个数: " + String.valueOf(over3PhoneCount));
      
      FileUtils.writeLines(new File(SMS_CONTENT_PATH2), newLines);
      
      FileUtils.writeLines(new File(SMS_PARENT_PATH + "over3"), over3Lines);
    } catch (IOException e) {
      logger.error("读取文件失败 : " + SMS_CONTENT_PATH);
      e.printStackTrace();
    }
  }

}
