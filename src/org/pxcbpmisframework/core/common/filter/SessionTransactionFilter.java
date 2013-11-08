package org.pxcbpmisframework.core.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pxcbpmisframework.core.common.hibernate.qbc.HibernateUtils;

/**
 * 
 * @ClassName: SessionTransactionFilter
 * @Description: TODO(事务管理)
 * @author Mr_Pxc
 * @date 2013-6-27 上午09:30:22
 * @project_name：BPMIS_1
 * @change
 * @remark
 * @version 1.0
 */
public class SessionTransactionFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(SessionTransactionFilter.class);

	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		try {
			HttpServletRequest hrequest = (HttpServletRequest) arg0;
			String uri = hrequest.getRequestURI().toLowerCase();
			if (uri.endsWith(".jsp") || uri.endsWith(".gif")
					|| uri.endsWith(".jpg") || uri.endsWith(".htm")
					|| uri.endsWith(".html") || uri.endsWith(".css")
					|| uri.endsWith(".js") || uri.endsWith(".png")) {
				// 过虑事务处理
				arg2.doFilter(arg0, arg1);
				HibernateUtils.closeSession();
			} else {
				System.out
						.println("-----------------HibernateUtils.getSession() filter is begin!--------------------");
				Session s = HibernateUtils.currentSession();// 打开session
				System.out
						.println("-----------------HibernateUtils.beginTransaction() filter is begin!--------------------");
				Transaction trans = null;
				trans = s.beginTransaction();// 打开事务
				arg0.setAttribute("_transaction", trans);
				arg2.doFilter(arg0, arg1);
				if (arg0.getAttribute("_transcommit") == null) {
					if (trans != null) {
						if (s.isOpen()) {
							trans.commit();
							System.out
									.println("-----------------HibernateUtils.CommitTransaction() filter is begin!--------------------");
						}
					}
				}
				HibernateUtils.CommitTransaction();// 事务提交
			}
		} catch (Exception e) {
			HibernateUtils.RollbackTransaction();// 回滚事务
			logger.error(e);
		} finally {
			System.out
					.println("-----------------HibernateUtils.closeSession() filter is close!--------------------");
			HibernateUtils.closeSession();// 关闭session
		}
	}

}
