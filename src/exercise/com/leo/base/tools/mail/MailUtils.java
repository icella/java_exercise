package exercise.com.leo.base.tools.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	// 发送协议
	private static final String PROTOCOL = "smtp";
	// 发送服务器
	private static final String SMTP = "smtp.exmail.sina.com";
	// 发送时，进行发件人账户验证
	private static final Boolean AUTH = Boolean.TRUE;
	// 发件人邮箱地址
	private static final String FROM_ADDRESS = "upc@pingansec.com";
	// 发件人名称
	private static final String FROM_PERSONAL = "个人征信";
	// 发件人SMTP登录密码
	private static final String PASSWD = "upc@123";
	// 默认字符集
	private static final String DEFAULT_CHARSET = "utf-8";

	private static Session createSession() {
		Properties property = new Properties();
		property.setProperty("mail.transport.protocol", PROTOCOL);
		property.setProperty("mail.smtp.auth", AUTH.toString());
		Session session = Session.getInstance(property);
		session.setDebug(false);
		return session;
	}

	/**
	 * @param toAddressList
	 *            收件人集合，必填
	 * @param toAddressList
	 *            抄送人集合，可以为空
	 * @param subject
	 *            邮件标题
	 * @param context
	 *            邮件内容
	 * @param charset
	 *            邮件内容字符集，默认为utf8
	 */
	public static void sendMail(List<InternetAddress> toAddressList, List<InternetAddress> ccAddressList,
			String subject, String context, String charset) throws Exception {
		if (toAddressList == null || toAddressList.isEmpty()) {
			throw new Exception("邮件收件人不能为空");
		}
		Session session = createSession();
		MimeMessage msg = new MimeMessage(session);
		if (charset == null || charset.trim().length() == 0) {
			charset = DEFAULT_CHARSET;
		}
		msg.setFrom(new InternetAddress(FROM_ADDRESS, FROM_PERSONAL, charset));
		msg.setRecipients(Message.RecipientType.TO, toAddressList.toArray(new InternetAddress[] {}));
		if (ccAddressList != null && !ccAddressList.isEmpty()) {
			msg.setRecipients(Message.RecipientType.CC, ccAddressList.toArray(new InternetAddress[] {}));
		}
		msg.setSubject(subject, charset);
		msg.setContent(context, "text/html;charset=" + charset);
		msg.saveChanges();
		// 设置发件人使用的SMTP服务器、用户名、密码
		Transport transport = session.getTransport("smtp");
		transport.connect(SMTP, FROM_ADDRESS, PASSWD);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}

	/**
	 * @param toAddressList
	 *            收件人集合，必填
	 * @param toAddressList
	 *            抄送人集合，可以为空
	 * @param subject
	 *            邮件标题
	 * @param context
	 *            邮件内容
	 * @desc 该方法发送邮件，默认邮件内容的字符集为:utf8
	 */
	public static void sendMail(List<InternetAddress> toAddressList, List<InternetAddress> ccAddressList,
			String subject, String context) throws Exception {
		sendMail(toAddressList, ccAddressList, subject, context, DEFAULT_CHARSET);
	}

	// 测试生成邮件
	public static void main(String[] args) throws Exception {
		List<InternetAddress> toAddressList = new ArrayList<InternetAddress>();
		toAddressList.add(new InternetAddress("394439086@qq.com", "孙钊", "utf-8"));
		toAddressList.add(new InternetAddress("lila@pingansec.com", "孙钊", "utf-8"));

		List<InternetAddress> ccAddressList = new ArrayList<InternetAddress>();
		ccAddressList.add(new InternetAddress("lila@pingansec.com", "孙钊", "utf-8"));

		String context = "这是一个测试";

		sendMail(toAddressList, ccAddressList, "测试", context, null);
	}
}
