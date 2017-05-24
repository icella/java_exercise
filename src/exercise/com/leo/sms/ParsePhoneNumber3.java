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
 * 原文分隔符zz
 * 结果分固话，手机号分别输出
 * @author lla
 *
 */
public class ParsePhoneNumber3 {
  private final static Logger logger = LoggerFactory.getLogger(ParsePhoneNumber3.class);
  static final String MOBILE_REG_EXP ="[\\d()-]{7,12}";
  static final String IDCARD_REG_EXP = "(\\d(\\d|\\*){16}(\\d|X|x|\\*))|(\\d(\\d|\\*){14})";
  static final String SMS_PARENT_PATH = "/home/lla/Documents/test_daily/2016/12/8/";
  static String SMS_CONTENT_PATH = SMS_PARENT_PATH + "ff_out";
  static final String SPLITTOR = "zz";
  private static final String DECOLLATOR = "_";

  public static void main(String[] args) {
    try {
      Set<String> fixedNumberLists = Sets.newHashSet();
      Set<String> mobileNumberLists = Sets.newHashSet();
      LineIterator it = FileUtils.lineIterator(new File(SMS_CONTENT_PATH));
      int counter  = 0;
      try {
        while (it.hasNext()) {
          String line = it.nextLine();
          // sms_sender,sample_content
          String sender;
          String rawContent;
          String content;
          try {
            String[] lineSplit = line.split(SPLITTOR, -1);
            sender = lineSplit[1];
            rawContent = lineSplit[2];
            content = rawContent.replaceAll("\\pP|\\pS", "");
            content = rawContent.replaceAll(IDCARD_REG_EXP, "");
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
//            logger.info(line);
            String resultLine = sender + "\t" + rawContent;
//            fixedNumberLists.add(resultLine);
//            mobileNumberLists.add(resultLine);
            
            continue;
          }
          
//          StringBuilder sb = new StringBuilder();
          StringBuilder fixedSb = new StringBuilder();
          StringBuilder mobileSb = new StringBuilder();
          for (String pString : matchList) {
            String number = PhoneNumberFormat.format(pString);
            if(PhoneNumberFormat.isPhoneNumber(number)){
//              mobileNumberLists.add(number);
              mobileSb.append(number).append(DECOLLATOR);
            } else {
//              fixedNumberLists.add(number);
              if(number.startsWith("0")){
                fixedSb.append(number).append(DECOLLATOR);
              }
            }
            
//            sb.append(number).append(DECOLLATOR);
          }
          
          String rawConent = sender + "\t" + rawContent + "\t";
          
          
          String fixedStr = fixedSb.toString();
          if(fixedStr.length() > 1){
            String fixedResult = rawConent + fixedStr.substring(0, fixedStr.length() -1);
            fixedNumberLists.add(fixedResult);
            logger.info(fixedResult);
          }
          
          String mobileStr = mobileSb.toString();
          if(mobileStr.length() > 1){
            String mobileResult = rawConent + mobileStr.substring(0, mobileStr.length() - 2);
            mobileNumberLists.add(mobileResult);
            logger.info(mobileResult);
          }
          
//          String sbStr = sb.toString();
//          String phoneNumber = sbStr.substring(0, sbStr.length() -2);
//          String resultStr = sender + "\t" + rawContent + "\t" + phoneNumber;
//          logger.info(counter + " : " + resultStr);
          
//          fixedNumberLists .add(resultStr);
//          mobileNumberLists.add(resultStr);
        }
      } catch (Exception e1) {
        e1.printStackTrace();
      } finally {
        LineIterator.closeQuietly(it);
      }

      FileUtils.writeLines(new File(SMS_CONTENT_PATH + "_fixed"), fixedNumberLists);
      FileUtils.writeLines(new File(SMS_CONTENT_PATH + "_mobile"), mobileNumberLists);
    } catch (IOException e) {
      logger.error("读取文件失败 : " + SMS_CONTENT_PATH);
      e.printStackTrace();
    }
  }

}
