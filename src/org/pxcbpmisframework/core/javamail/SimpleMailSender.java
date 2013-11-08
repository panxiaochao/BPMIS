package org.pxcbpmisframework.core.javamail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.pxcbpmisframework.core.exception.BusinessException;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * 简单邮件（不带附件的邮件）发送器
 * @author panxiaochao
 * @date 2013.06.13
 */
public class SimpleMailSender {
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 * @throws UnsupportedEncodingException
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) {

		MyAuthenticator authenticator = null;// 判断是否需要身份认证
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());// 如果需要身份认证，则创建一个密码验证器
		}
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		try {
			String nike = "";
			try {
				nike = MimeUtility.encodeText(mailInfo.getNike());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Message mailMessage = new MimeMessage(sendMailSession);// 根据session创建一个邮件消息
			mailMessage.setFrom(new InternetAddress(nike + " <"
					+ mailInfo.getFromAddress() + ">"));// 设置邮件消息的发送者
			mailMessage.setRecipient(Message.RecipientType.TO,
					new InternetAddress(mailInfo.getToAddress()));// 创建邮件的接收者地址，并设置到邮件消息中
			mailMessage.setSubject(mailInfo.getSubject());// 设置邮件消息的主题
			mailMessage.setText(mailInfo.getContent());// 设置邮件消息的主要内容
			mailMessage.setSentDate(new Date());// 设置邮件消息发送的时间
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			new BusinessException("邮件发送失败！", ex);
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public boolean sendHtmlMail(MailSenderInfo mailInfo) {

		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();

		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			String nike = "";
			try {
				nike = MimeUtility.encodeText(mailInfo.getNike());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Message mailMessage = new MimeMessage(sendMailSession);
			mailMessage.setFrom(new InternetAddress(nike + " <"
					+ mailInfo.getFromAddress() + ">"));
			mailMessage.setRecipient(Message.RecipientType.TO,
					new InternetAddress(mailInfo.getToAddress()));
			mailMessage.setSubject(mailInfo.getSubject());
			mailMessage.setSentDate(new Date());
			Multipart mainPart = new MimeMultipart();// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			BodyPart html = new MimeBodyPart();// 创建一个包含HTML内容的MimeBodyPart
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");// 设置HTML内容
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);// 将MiniMultipart对象设置为邮件内容
			Transport.send(mailMessage);// 发送邮件
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			new BusinessException("邮件发送失败！", ex);
		}
		return false;
	}
}
