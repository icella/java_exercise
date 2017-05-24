package exercise.com.leo.base.tools;

import java.security.MessageDigest;

public class SecureUtil {
  public static String md5(String str) throws Exception {
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    md5.update(str.toString().getBytes());
    return byte2Hex(md5.digest());
  }

  public static String byte2Hex(byte[] bytes) throws Exception {
    final String HEX = "0123456789abcdef";

    String result = "";
    for (int i = 0; i < bytes.length; i++) {
      result += HEX.charAt(bytes[i] >> 4 & 0x0F);
      result += HEX.charAt(bytes[i] & 0x0F);
    }

    return new String(result);
  }
}
