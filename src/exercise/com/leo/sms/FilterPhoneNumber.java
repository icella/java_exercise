package exercise.com.leo.sms;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;


/**
 * 从短信原文中提取前后两个手机号
 * @author lla
 *
 */
public class FilterPhoneNumber {
  private final static Logger logger = LoggerFactory.getLogger(FilterPhoneNumber.class);
  static final String MOBILE_REG_EXP  = "(?:(\\(\\+?86\\))((13[0-9]{1})|(14[57]{1})|(15[0-3,5-9]{1})|(17[01678]{1})|(18[0-9]{1}))+\\d{8})|" +     
      "(?:86-?((13[0-9]{1})|(14[57]{1})|(15[0-3,5-9]{1})|(17[01678]{1})|(18[0-9]{1}))+\\d{8})|" +
      "(?:((13[0-9]{1})|(14[57]{1})|(15[0-3,5-9]{1})|(17[01678]{1})|(18[0-9]{1}))+\\d{8})";
  static final String SMS_PARENT_PATH = "/home/lla/Documents/test_daily/2016/11/4/";
  static  String SMS_CONTENT_PATH = SMS_PARENT_PATH + "phoneNumber";
  static  String SMS_CONTENT_PATH2 = SMS_PARENT_PATH + "result";
  private static final String DECOLLATOR = "\t";
  
  public static void main(String[] args) {
    try {
      List<String> lines = FileUtils.readLines(new File(SMS_CONTENT_PATH));
      
      int totalPhoneCounter = 0;
      List<String> newLines = Lists.newArrayList();
      for (String line : lines) {
        try {
          String phone = line.trim();
          
          Pattern pattern = Pattern.compile(MOBILE_REG_EXP);
          Matcher matcher = pattern.matcher(phone);
          while(matcher.find()){
            String phoneRaw = matcher.group();
            if(phoneRaw.startsWith("86")){
              newLines.add(phoneRaw.substring(2));
            } else {
              newLines.add(phoneRaw);
            }
            
            totalPhoneCounter++;
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          continue;
        }
      }
      logger.info("文本总共包含电话号码的个数: " + String.valueOf(totalPhoneCounter));
      
      FileUtils.writeLines(new File(SMS_CONTENT_PATH2), newLines);
    } catch (IOException e) {
      logger.error("读取文件失败 : " + SMS_CONTENT_PATH);
      e.printStackTrace();
    }
  }

}
