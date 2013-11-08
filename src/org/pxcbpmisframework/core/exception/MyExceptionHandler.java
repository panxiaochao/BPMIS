package org.pxcbpmisframework.core.exception;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.pxcbpmisframework.core.constant.Globals;
import org.pxcbpmisframework.core.util.ExceptionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import bpmis.pxc.system.service.UserService;

/**
 * 异常捕获类
 * 
 * @author panxiaochao
 * @ClassName MyExceptionHandler
 * @Description TODO
 * @date 2013-7-8
 */
@Controller
public class MyExceptionHandler implements HandlerExceptionResolver {
	@Resource
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	private static final Logger logger = Logger
			.getLogger(MyExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String exceptionMessage = ExceptionUtil.getExceptionMessage(ex);
		logger.error(exceptionMessage);
		// Map<String, Object> model = new HashMap<String, Object>();
		// model.put("exceptionMessage", exceptionMessage);
		// model.put("ex", ex);
		userService.addLogger(ex.getMessage(), Globals.Logger_Leavel_ERROR,
				Globals.Logger_Type_500);

		return new ModelAndView("error/500");
	}

}
