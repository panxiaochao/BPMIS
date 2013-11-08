package bpmis.pxc.system.controller.core;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tools.tar.TarBuffer;
import org.pxcbpmisframework.core.common.hibernate.qbc.CriteriaQuery;
import org.pxcbpmisframework.core.constant.Globals;
import org.pxcbpmisframework.core.javamail.FastEmailSendUtils;
import org.pxcbpmisframework.core.json.AjaxJson;
import org.pxcbpmisframework.core.page.Page;
import org.pxcbpmisframework.core.util.DataToolsUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bpmis.pxc.system.pojo.TBFeedBack;
import bpmis.pxc.system.pojo.TBLogger;
import bpmis.pxc.system.pojo.TBUser;
import bpmis.pxc.system.service.UserService;

/**
 * 系统杂类
 * 
 * @author panxiaochao
 * @ClassName SystemController
 * @Description TODO
 * @date 2013-8-10
 */
@Controller("systemController")
@RequestMapping("/systemController.do")
public class SystemController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	/**
	 * 《--------------------方法------------------》
	 */
	@RequestMapping(params = "feedback")
	@ResponseBody
	public AjaxJson feedback(String email, String sug) {
		AjaxJson ajx = new AjaxJson();
		try {
			TBFeedBack feba = new TBFeedBack();
			feba.setFeedemail(email);
			feba.setFeedcontent(sug);
			feba.setFeedtime(DataToolsUtils.getSimpleDateFormat());
			feba.setFeedstate("0");// 未回馈
			userService.save(feba);
			ajx.setMsg("谢谢您宝贵建议，我们会及时给您回复！");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
			userService.addLogger(e.getMessage(), Globals.Logger_Leavel_ERROR,
					Globals.Logger_Type_FeedBack);
		}
		return ajx;
	}

	/**
	 * 得到BUG记录
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "getBug")
	public ModelAndView getBug(ModelMap map, Page page) {
		CriteriaQuery cq = new CriteriaQuery("operatetime");
		Map<?, ?> mapPage = userService.getPageList(TBLogger.class, cq, page);
		map.put("list", mapPage.get("list"));
		map.put("pagehtml", mapPage.get("pagehtml"));
		return new ModelAndView("bug/bug", map);
	}

	/**
	 * 得到回馈记录
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "getFeed")
	public ModelAndView getFeed(ModelMap map, Page page) {
		// page.setPageSize(7);
		CriteriaQuery cq = new CriteriaQuery("feedtime");
		Map<?, ?> mapPage = userService.getPageList(TBFeedBack.class, cq, page);
		map.put("list", mapPage.get("list"));
		map.put("pagehtml", mapPage.get("pagehtml"));
		return new ModelAndView("feedback/feedback", map);
	}

	/**
	 * 发送回馈记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "sendFeed")
	public ModelAndView sendFeed(HttpServletRequest request) {
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		TBFeedBack feedback = (TBFeedBack) userService.getClass(
				TBFeedBack.class, id);
		if (feedback.getFeedstate().equals("1")) {
			FastEmailSendUtils.FeedBackInfo(feedback, content);
		} else {
			feedback.setFeedstate("1");
			userService.saveOrUpdata(feedback);
			FastEmailSendUtils.FeedBackInfo(feedback, content);
		}
		return new ModelAndView("redirect:/systemController.do?getFeed");
	}
}
