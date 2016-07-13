package exercise.com.leo.base.tools.phoneinfo;

/**
 * imei由15位数字组成， 前6位(TAC)是型号核准号码，代表手机类型。 接着2位(FAC)是最后装配号，代表产地。
 * 后6位(SNR)是串号，代表生产顺序号。 最后1位 (SP)是检验码。
 * 
 * 检验码计算： (1).将偶数位数字分别乘以2，分别计算个位数和十位数之和 (2).将奇数位数字相加，再加上上一步算得的值
 * (3).如果得出的数个位是0则校验位为0，否则为10减去个位数
 * 
 */
public class ImeiUtils {
	/**
	 * 获取手机类型
	 * 
	 * @return
	 */
	public static String getMobileType(String imei15) {
		if (15 != imei15.length()) {
			return null;
		}
		return imei15.substring(0, 6);
	}

	/**
	 * 获取手机产地
	 * 
	 * @param args
	 */
	public static String getMobileHome(String imei15) {
		if (15 != imei15.length()) {
			return null;
		}
		return imei15.substring(6, 8);
	}

	/**
	 * 获取生产顺序号
	 * 
	 * @param args
	 */
	public static String getMobileSeq(String imei15) {
		if (15 != imei15.length()) {
			return null;
		}
		return imei15.substring(8, 14);
	}

	/**
	 * 获取Imei字符串
	 * 
	 * @param args
	 */
	public static String getImeiStr(String imei15) {
		if (15 != imei15.length()) {
			return null;
		}
		return imei15.substring(0, 14);
	}
	
	/**
	 * 获取检验码
	 * 
	 * @param args
	 */
	public static String getImeiBit(String imei15) {
		if (15 != imei15.length()) {
			return null;
		}
		return imei15.substring(14, 15);
	}

	/**
	 * 校验检验码
	 * 
	 * @param args
	 */
	public static int getImeiBitBy14(String imei14) {
		if (14 != imei14.length()) {
			return -1;
		}
		// 校验检验码
		char[] imeiChar = imei14.toCharArray();
		int resultInt = 0;
		for (int i = 0; i < imeiChar.length; i++) {
			int a = Integer.parseInt(String.valueOf(imeiChar[i]));
			i++;
			final int temp = Integer.parseInt(String.valueOf(imeiChar[i])) * 2;
			final int b = temp < 10 ? temp : temp - 9;
			resultInt += a + b;
		}
		resultInt %= 10;
		resultInt = resultInt == 0 ? 0 : 10 - resultInt;

		return resultInt;
	}

	/**
	 * 校验imei号码
	 * @param imei15
	 * @return
	 */
	public static boolean validateImei(String imei15){
		if (15 != imei15.length()) {
			return false;
		}
		int imei15_flag = Integer.parseInt(getImeiBit(imei15));
		int check_flag = getImeiBitBy14(getImeiStr(imei15));
		if(imei15_flag == check_flag){
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		String imei = "004999010640000";
		if(validateImei(imei)){
			System.out.println("手机类型：" + getMobileType(imei));
			System.out.println("手机产地：" + getMobileHome(imei));
			System.out.println("手机顺序：" + getMobileSeq(imei));
			System.out.println("手机检验码：" + getImeiBit(imei));	
		} else {
			System.out.println("Imei不合规定！");
		}
			

	}
}
