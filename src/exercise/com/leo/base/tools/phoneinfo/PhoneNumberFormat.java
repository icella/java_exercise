package exercise.com.leo.base.tools.phoneinfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


/**
 * 电话号码格式化
 * */
public final class PhoneNumberFormat {

	private static final Pattern PHONE_PATTERN = Pattern.compile("\\d*1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$");
	
	/**
	 * 格式化号码
	 * */
	public static final String format(final String number) throws Exception {
		if(StringUtils.isEmpty(number)) {
			return null;
		}
		String tagNumber = number.trim();
		tagNumber = tagNumber.replace("+", "").trim();
		tagNumber = tagNumber.replace("-", "").trim();
		tagNumber = tagNumber.replace("(", "").trim();
		tagNumber = tagNumber.replace(")", "").trim();
		tagNumber = tagNumber.replaceFirst(":", "").trim();
        tagNumber = tagNumber.replaceFirst("：", "").trim();
		if(!Pattern.matches("^\\d+\\d$", tagNumber)) {
			throw new Exception("无效号码[" + number + "]");
		}
		Matcher matcher = PHONE_PATTERN.matcher(tagNumber);
		if(matcher.find()) {
			return tagNumber.substring(tagNumber.length() - 11);
		}
		if(tagNumber.startsWith("00")) {
			return tagNumber.substring(1);
		}
		if(tagNumber.startsWith("400")) {
			return tagNumber;
		}
		if(tagNumber.length() > 8 && !tagNumber.startsWith("0")) {
			return "0" + tagNumber;
		}
		return tagNumber;
	}
	
	public static final boolean isPhoneNumber(final String phone) throws Exception {
		if(StringUtils.isEmpty(phone)) {
			throw new Exception("手机号不能为空");
		}
		String tagPhone = format(phone);
		Matcher matcher = PHONE_PATTERN.matcher(tagPhone);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(format("+(86)18616626650"));
		System.out.println(format("(021)20675501"));
		System.out.println(isPhoneNumber(format("18616626650")));
	}
}