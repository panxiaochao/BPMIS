package bpmis.pxc.system.controller.core;

import javax.servlet.http.HttpServletRequest;

import org.pxcbpmisframework.core.json.AjaxJson;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public interface BaseController {
	static final String POJO_TYPE = "bpmis.pxc.system.pojo.";
	/**
	 * 得到List
	 * @param request
	 * @param map
	 * @return
	 */
	public ModelAndView getListInfo(HttpServletRequest request, ModelMap map);
	/**
	 * 得到单个实例
	 * @param request
	 * @return
	 */
	public ModelAndView singleObj(HttpServletRequest request, ModelMap map);
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	public ModelAndView deleteObj(HttpServletRequest request);
	/**
	 * 跳转
	 * @return
	 */
	public String redirect();

}
