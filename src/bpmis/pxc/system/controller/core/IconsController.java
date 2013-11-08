package bpmis.pxc.system.controller.core;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.pxcbpmisframework.core.common.hibernate.qbc.CriteriaQuery;
import org.pxcbpmisframework.core.exception.BusinessException;
import org.pxcbpmisframework.core.page.Page;
import org.pxcbpmisframework.core.util.ImportUtil;
import org.pxcbpmisframework.core.util.JSONHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bpmis.pxc.system.pojo.ICons;
import bpmis.pxc.system.pojo.Role;
import bpmis.pxc.system.service.UserService;

@Controller("iconsController")
@RequestMapping("/iconsController.do")
public class IconsController implements BaseController {
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
	/**
	 * 跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "iconsjsp")
	public String iconsjsp() {
		return "icons/icons";
	}

	/**
	 * 得到圖標管理
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "getIconsInfo")
	public ModelAndView getIconsInfo(HttpServletRequest request, ModelMap map,
			Page page) {
		page.setPageSize(5);
		CriteriaQuery cq = new CriteriaQuery();
		Map<?, ?> mapPage = userService.getPageList(ICons.class, cq, page);
		List<?> list = (List) mapPage.get("list");
		map.put("list", list);
		map.put("pagehtml", mapPage.get("pagehtml"));
		return new ModelAndView("icons/icons", map);
	}

	/**
	 * 图片添加
	 * 
	 * @return
	 */
	@RequestMapping(params = "iconsadd")
	public ModelAndView iconsadd(ICons icon) {
		userService.save(icon);
		return new ModelAndView("redirect:/iconsController.do?getIconsInfo");
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "deleteObj")
	public ModelAndView deleteObj(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Class classname = null;
		classname = ImportUtil.getEntityClassI(request.getParameter("class")
				.toString());
		userService.deleteEntityById(classname, Integer.parseInt(request
				.getParameter("id").toString()));
		return new ModelAndView("redirect:/iconsController.do?getIconsInfo");
	}

	@Override
	public ModelAndView getListInfo(HttpServletRequest request, ModelMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String redirect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView singleObj(HttpServletRequest request, ModelMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 得到单实例
	 * 
	 * @return
	 */
	@RequestMapping(params = "singleObj")
	@ResponseBody
	public String singleObj(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id").toString();
		ICons icons = (ICons) userService.findClassById(ICons.class, id);
		return JSONHelper.array2json(icons);
	}

	public ModelAndView updateObj(ICons obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
