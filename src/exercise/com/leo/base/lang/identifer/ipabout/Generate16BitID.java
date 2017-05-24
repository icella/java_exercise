package exercise.com.leo.base.lang.identifer.ipabout;

import java.util.concurrent.atomic.AtomicInteger;

public class Generate16BitID {
  //本应用内部自增id
  private static AtomicInteger lastId = new AtomicInteger();
  
  //本应用启动时的时间戳
  private static long startTimeStamp = System.currentTimeMillis();
  
  //本机ip
  private static final String ip = LocalIpAddressUtil.resolveLocalAddress().getHostAddress();

  public static void main(String[] args) {
//    System.out.println(hexIp(ip));
    System.out.println(startTimeStamp);
    System.out.println(Long.toString(startTimeStamp, Character.MAX_RADIX));
    System.out.println(Long.toString(startTimeStamp, 16));
//    System.out.println(lastId.incrementAndGet());
  
      /*for (int i = 0; i < 1000; i++) {
        Thread t = new Thread(new Runnable() {
  
          @Override
          public void run() {
            System.err.println(resolveReqId());
          }
        });
        t.start();
      }*/
  }

  private static String seqNum(){
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
  
  private static String resolveReqId() {
    // 规则： hexIp(ip)base36(timestamp)seq
    return hexIp(ip) + Long.toString(System.currentTimeMillis(), 16) + seqNum();
  }

  // 将ip转换为定长8个字符的16进制表示形式：255.255.255.255 -> FFFFFFFF
  private static String hexIp(String ip) {
    StringBuilder sb = new StringBuilder();
    for (String seg : ip.split("\\.")) {
      String h = Integer.toHexString(Integer.parseInt(seg));
      if (h.length() == 1)
        sb.append("0");
      sb.append(h);
    }
    return sb.toString();
  }

}
