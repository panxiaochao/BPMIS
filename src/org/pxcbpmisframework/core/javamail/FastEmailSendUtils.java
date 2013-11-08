package org.pxcbpmisframework.core.javamail;

import bpmis.pxc.system.pojo.TBFeedBack;
import bpmis.pxc.system.pojo.TBUser;

/**
 * 快速发送邮件，用于注册或发送一些信息等等
 * 
 * @author panxiaochao
 * @version 2013.06.13
 */
public class FastEmailSendUtils {
	/**
	 * @Title: RegSuccess
	 * @Description: TODO(主要是针对注册成功调用的默认发送邮件)
	 * @param
	 * @return void
	 * @throws
	 */
	public static void RegSuccess(TBUser entity) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(entity.getEmail());
		mailInfo.setSubject("BPMIS_CMS 注册或修改-Email 验证");
		String mailcontent = MailMessages.RegSuccess_Content.replaceAll(
				"#account#", entity.getAccount());
		mailInfo.setContent(mailcontent);
		SimpleMailSender sms = new SimpleMailSender();
		if (sms.sendHtmlMail(mailInfo))
			System.out.println("<----------email success------->");
		else
			System.out.println("<----------email fail------->");
	}

	/**
	 * 更新信息
	 * 
	 * @param entity
	 */
	public static void UpdateInfo(TBUser entity) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(entity.getEmail());
		mailInfo.setSubject("BPMIS_CMS 信息中心");
		String mailcontent = MailMessages.Update_Info.replaceAll("#account#",
				entity.getAccount());
		mailInfo.setContent(mailcontent);
		SimpleMailSender sms = new SimpleMailSender();
		if (sms.sendHtmlMail(mailInfo))
			System.out.println("<----------email success------->");
		else
			System.out.println("<----------email fail------->");
	}

	/**
	 * 反馈内容
	 * 
	 * @param entity
	 * @param content
	 */
	public static void FeedBackInfo(TBFeedBack entity, String content) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(entity.getFeedemail());
		mailInfo.setSubject("BPMIS_CMS 信息中心");
		String mailcontent = MailMessages.FeedBack_Info.replaceAll("#account#",
				entity.getFeedemail()).replaceAll("#content#", content);
		mailInfo.setContent(mailcontent);
		SimpleMailSender sms = new SimpleMailSender();
		if (sms.sendHtmlMail(mailInfo))
			System.out.println("<----------email success------->");
		else
			System.out.println("<----------email fail------->");
	}

	public static void main(String[] args) {
		// TBUser user = new TBUser();
		// user.setEmail("panxiaochao602@qq.com");
		// user.setAccount("panxiaochao");
		// FastEmailSendUtils.RegInfo(user);
		System.out.println(MailMessages.RegSuccess_Content);
	}

}
