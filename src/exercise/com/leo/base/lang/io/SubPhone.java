package exercise.com.leo.base.lang.io;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;

public class SubPhone {

  public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub
    subPhone();
  }
  
  public static void subPhone() throws Exception{
    List<String> lines = FileUtils.readLines(new File("/home/lla/Documents/test_daily/2017/1/zhongtengxin_duntel/haomamingdan"));

    List<String> result = Lists.newArrayList();
    
    for(String line : lines){
        String phone = line.trim();
        if(phone.startsWith("0")){
            if(phone.startsWith("021") || phone.startsWith("010")){
               result.add(phone.substring(3));
            } else{
              result.add(phone.substring(4));
            }
        } else {
            result.add(phone);
        }
    }

    FileUtils.writeLines(new File("/home/lla/Documents/test_daily/2017/1/zhongtengxin_duntel/result"), result);
}


}
