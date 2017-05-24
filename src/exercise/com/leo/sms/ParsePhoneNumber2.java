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


/**
 * 过滤短信原文中的手机号码，去掉86,+86
 * 
 * @author lla
 *
 */
public class ParsePhoneNumber2 {
  private final static Logger logger = LoggerFactory.getLogger(ParsePhoneNumber2.class);
  static final String MOBILE_REG_EXP =
      "(?:(\\(\\+?86\\))((13[0-9]{1})|(14[57]{1})|(15[0-3,5-9]{1})|(17[01678]{1})|(18[0-9]{1}))+\\d{8})|"
          + "(?:86-?((13[0-9]{1})|(14[57]{1})|(15[0-3,5-9]{1})|(17[01678]{1})|(18[0-9]{1}))+\\d{8})|"
          + "(?:((13[0-9]{1})|(14[57]{1})|(15[0-3,5-9]{1})|(17[01678]{1})|(18[0-9]{1}))+\\d{8})";
//          + "(?:(\\+\\d{2,3})?(00(10)|(2[0-9])|(3[13579][0-9])|(4[1-8][0-9])|(5[1235-9][0-9])|(6[36][0-9])|(7[0-9][0-9])|(8[1-357-9][0-9])|(9[0-57-9][0-9])}-?)?\\d{7,8})";
//          + "(?:(0(10)|(2[0-9])|(3[13579][0-9])|(4[1-8][0-9])|(5[1235-9][0-9])|(6[36][0-9])|(7[0-9][0-9])|(8[1-357-9][0-9])|(9[0-57-9][0-9]))?-?\\d{7,8})";
//       + "(?:(\\+\\d{2,3})?(0\\d{2,3}-?)?\\d{7,8})";
  static final String SMS_PARENT_PATH = "/home/lla/Documents/test_daily/2016/11/28/";
  static String SMS_CONTENT_PATH = SMS_PARENT_PATH + "for_lilu_1122";
  static String SMS_CONTENT_PATH2 = SMS_PARENT_PATH + "for_lilu_1122_phone1";
  private static final String DECOLLATOR = "\t";

  public static void main(String[] args) {
    try {
      Set<String> newLines = Sets.newHashSet();
      LineIterator it = FileUtils.lineIterator(new File(SMS_CONTENT_PATH));
      int counter  = 0;
      try {
        while (it.hasNext()) {
          String line = it.nextLine();
          // sms_sender,sample_content,dun_tag,dun_word,not_dun_word,pt_dt
          String sender;
          String content;
          String dunTag;
          String dunWord;
          String notDunWord;
          String ptDt;
          try {
            String[] lineSplit = line.split("\t", -1);
            sender = lineSplit[0];
            content = lineSplit[1];
            dunTag = lineSplit[2];
            dunWord = lineSplit[3];
            notDunWord = lineSplit[4];
            ptDt = lineSplit[5];
            
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
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(DECOLLATOR).append(sender).append(DECOLLATOR).append(content)
                .append(DECOLLATOR).append(dunTag).append(DECOLLATOR).append(dunWord)
                .append(DECOLLATOR).append(notDunWord).append(DECOLLATOR).append(ptDt)
                .append(DECOLLATOR).append("");
//            logger.info(counter + " : " + stringBuilder.toString());
//            newLines.add(stringBuilder.toString());
            continue;
          }

          /*StringBuilder resultPhone = new StringBuilder();
          for (String string : matchList) {
//            resultPhone.append(string).append("_");
            resultPhone.append(string).append("\t");
          }
          String phone1 = resultPhone.toString();
          phone1 = phone1.substring(0, phone1.length() -1).replaceAll("-", "");
          
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(DECOLLATOR).append(sender).append(DECOLLATOR).append(content)
              .append(DECOLLATOR).append(dunTag).append(DECOLLATOR).append(dunWord)
              .append(DECOLLATOR).append(notDunWord).append(DECOLLATOR).append(ptDt)
              .append(DECOLLATOR).append(phone1);*/
          
//          logger.info(counter + " : " + stringBuilder.toString());
//          newLines.add(stringBuilder.toString());
          for (String pString : matchList) {
            logger.info(counter + " : " + pString);
            newLines.add(pString);
          }
        }
      } catch (Exception e1) {
        e1.printStackTrace();
      } finally {
        LineIterator.closeQuietly(it);
      }

      // int totalPhoneCounter = 0;
      // logger.info("文本总共包含电话号码的个数: " + String.valueOf(totalPhoneCounter));

      FileUtils.writeLines(new File(SMS_CONTENT_PATH2), newLines);

    } catch (IOException e) {
      logger.error("读取文件失败 : " + SMS_CONTENT_PATH);
      e.printStackTrace();
    }
  }

}
