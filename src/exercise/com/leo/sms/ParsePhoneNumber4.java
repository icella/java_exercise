package exercise.com.leo.sms;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import exercise.com.leo.base.tools.phoneinfo.PhoneNumberFormat;


/**
 * 过滤短信原文中的手机号，固话
 * 
 * @author lla
 *
 */
public class ParsePhoneNumber4 {
  private final static Logger logger = LoggerFactory.getLogger(ParsePhoneNumber4.class);
  static final String MOBILE_REG_EXP ="[\\d()-]{5,15}";
  static final String SMS_PARENT_PATH = "/home/lla/Documents/test_daily/2016/12/13/";
  static String SMS_CONTENT_PATH = SMS_PARENT_PATH + "data_1213_1";
  private static final String DECOLLATOR = "_";

  public static void main(String[] args) {
    try {
      Set<String> newLines = Sets.newHashSet();
      LineIterator it = FileUtils.lineIterator(new File(SMS_CONTENT_PATH));
      int counter  = 0;
      try {
        while (it.hasNext()) {
          String line = it.nextLine();
          // sms_sender,sample_content
          String sender;
          String content;
          try {
            String[] lineSplit = line.split("\t", -1);
            sender = lineSplit[0];
            content = lineSplit[1];
          } catch (ArrayIndexOutOfBoundsException e) {
            continue;
          }

          Set<String> matchList = Sets.newHashSet();
          Pattern pattern = Pattern.compile(MOBILE_REG_EXP);
          Matcher matcher = pattern.matcher(content);
          while (matcher.find()) {
            matchList.add(matcher.group());
          }
          counter++;
          if (matchList.size() == 0) {
            logger.info(line);
            newLines.add(line);
            continue;
          }
          
          StringBuilder sb = new StringBuilder();
          for (String pString : matchList) {
            String number = PhoneNumberFormat.format(pString);
//            PhoneNumberFormat.isPhoneNumber(number);
            sb.append(number).append(DECOLLATOR);
          }
          
          String reString = sb.toString();
          String result = reString.substring(0, reString.length() -2);
          logger.info(counter + " : " + line + "\t" + result);
          newLines.add(line + "\t" + result);
        }
      } catch (Exception e1) {
        e1.printStackTrace();
      } finally {
        LineIterator.closeQuietly(it);
      }

      FileUtils.writeLines(new File(SMS_CONTENT_PATH + "_result"), newLines);

    } catch (IOException e) {
      logger.error("读取文件失败 : " + SMS_CONTENT_PATH);
      e.printStackTrace();
    }
  }

}
