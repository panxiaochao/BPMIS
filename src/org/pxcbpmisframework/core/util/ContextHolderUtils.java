package org.pxcbpmisframework.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.pxcbpmisframework.core.constant.Globals;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bpmis.pxc.system.pojo.TBUser;

/**
 * @ClassName: ContextHolderUtils
 * @Description: TODO(上下文工具类)
 * @author panxiaochao
 * @date 2013-05-26 下午11:27:39
 * 
 */
public class ContextHolderUtils {
	/**
	 * Spring下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;

	}

	/**
	 * Spring下获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;

	}

	/**
	 * 通过session得到TBUser
	 * @Title: getSessionUser         
	 * @return TBUser    
	 */
	public static TBUser getSessionUser() {
		HttpSession session = ContextHolderUtils.getSession();
		session.setMaxInactiveInterval(-1);//session 永远不会失效
		if (session.getAttributeNames().hasMoreElements()) {
			TBUser user = (TBUser) session.getAttribute(Globals.USER_SESSION);
			if (user != null) {
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
