package exercise.com.leo.base.tools;

import java.util.concurrent.atomic.AtomicInteger;

public class GenerateTradeNo {
  //本应用内部自增id
  private static AtomicInteger lastId = new AtomicInteger();
  
  public static GenerateTradeNo getInstance() {
    return Generate16BitIDHolder.instance;
  }

  private static class Generate16BitIDHolder {
    private static GenerateTradeNo instance = new GenerateTradeNo();
  }
  
  // 规则： hexIp(ip)base16(timestamp)seq
  public String generateId() {
    return Long.toString(System.currentTimeMillis(), 16) + seqNum();
  }

  // 将ip转换为定长8个字符的16进制表示形式：255.255.255.255 -> FFFFFFFF
  private String hexIp(String ip) {
    StringBuilder sb = new StringBuilder();
    for (String seg : ip.split("\\.")) {
      String h = Integer.toHexString(Integer.parseInt(seg));
      if (h.length() == 1)
        sb.append("0");
      sb.append(h);
    }
    return sb.toString();
  }
  
  //0...9999
  private String seqNum(){
    if(lastId.intValue() == 999)
      lastId.set(1);
    
    String index = String.valueOf(lastId.incrementAndGet());
    int size = index.length();
    if (size < 4) {
      int prefixLen = 4 - size;
      for (int i = 0; i < prefixLen; i++) {
        index = "0" + index;
      }
    }
    
    return index;
  }

}
