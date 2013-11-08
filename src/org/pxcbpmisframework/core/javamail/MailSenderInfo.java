package org.pxcbpmisframework.core.javamail;

/** 
 * 发送邮件需要使用的基本信息 
 */
import java.util.Properties;

public class MailSenderInfo {

	private String mailServerSmtp = "smtp.qq.com"; // 默認 发送邮件的服务器的IP和端口
	private String mailServerPort = "25"; //默認
	private String nike = "BPMIS_CMS 技术中心"; //默认
	private String fromAddress = "284199540@qq.com"; //默認 邮件发送者的地址
	private String toAddress; // 邮件接收者的地址
	private String userName = "284199540@qq.com"; //默認 登陆邮件发送服务器的用户名和密码
	private String password = "asdf927927"; //默認
	private boolean validate = true; //默認  是否需要身份验证
	private String subject; // 邮件主题
	private String content; // 邮件的文本内容
	private String[] attachFileNames; // 邮件附件的文件名
	

	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerSmtp);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	

	public String getMailServerSmtp() {
		return mailServerSmtp;
	}

	public void setMailServerSmtp(String mailServerSmtp) {
		this.mailServerSmtp = mailServerSmtp;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getNike() {
		return nike;
	}

	public void setNike(String nike) {
		this.nike = nike;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}
}