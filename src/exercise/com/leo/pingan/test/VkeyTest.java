package exercise.com.leo.pingan.test;

import exercise.com.leo.base.tools.SecureUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;

public class VkeyTest {
    static String suanhua_key = "0a149278bd69b6e0ab21924a5194b613";
    static String zhangzhong_key = "849eac68f1f78ffaac34abb005cc8c8b";
    static String pingan_key = "423874edff0a575142fb86b7fa82e311";
    static String qianlong_key = "f7026aa74a5afc4fb2ac0f20611c04a9";

    //old df55eed575c049e65fbb56390c627278
    //new 32334eb304cb3986860acbd840da99df
    static String test_key = "32334eb304cb3986860acbd840da99df";
    static String zpkj_key = "7a9d29bc38d81ce559740790dc84468d";
    static String testP_key = "c37c675a5df3eb3819aea46b519fafe1";

    static String temp_key = "05e8ee02ef3ea19ce5a922182f24344c";
    static String tongbanjie_key = "58b7988a5d95e255bffb417dbc1c1ca8";
    //120161013001
    static String pinniu_key = "29f68a2c3b8d76d2d9886347ec49b819";

    // 120161017001 02c11d006cb2ce2e44597d6c7922f1d0
    //120161013001  29f68a2c3b8d76d2d9886347ec49b819
    //120170221001 1dadc4951575fa4a6387d21e6dc326ad

    static String ssString = "120170221001";
    static String ssSsstring = "1dadc4951575fa4a6387d21e6dc326ad";

    static String cailutong = "120160108001";
    static String cailutont_sec = "ef88891b4a25182036745fc9583bb5a1";

    static String xcreditKey = "120161216001";
    static String xcreditSecrct = "2ddcfc90883ff8afdd380a6ab8cce314";


    static String getmd5Str(String key, String time) {
        return key + "_" + time + "_" + key;
    }


    @Test public void getMd5Key() throws Exception {
        //	  generateSalt();
        long time = System.currentTimeMillis();
        System.out.println("Ptime : " + time);
        System.out.println("Formated Ptime : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));

        String keyStr = getmd5Str(xcreditSecrct, String.valueOf(time));
        System.out.println("Original key : " + keyStr);
        System.out.println("MD5 KEY : " + SecureUtil.md5(keyStr));

        System.out.print("----------------------------");
        System.out.println("\u7f57\u5251");

        //1452738336
    /*Date date = new Date(1453270281760L);
		DateFormat formatter = new SimpleDateFormat(TimeUtil.FORMAT_DETAIL);
		String dateFormatted = formatter.format(date);
		System.out.println(dateFormatted);
		
		System.out.println(SecureUtil.md5("610202196706282000"));*/
    }
}
