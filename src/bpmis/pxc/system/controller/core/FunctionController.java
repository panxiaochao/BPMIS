package bpmis.pxc.system.controller.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.pxcbpmisframework.core.common.hibernate.qbc.HqlQuery;
import org.pxcbpmisframework.core.json.AjaxJson;
import org.pxcbpmisframework.core.json.ComboTree;
import org.pxcbpmisframework.core.page.Page;
import org.pxcbpmisframework.core.util.JSONHelper;
import org.pxcbpmisframework.core.util.oConvertUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bpmis.pxc.system.pojo.Function;
import bpmis.pxc.system.pojo.ICons;
import bpmis.pxc.system.pojo.Role;
import bpmis.pxc.system.pojo.TBRoleFunction;
import bpmis.pxc.system.service.UserService;
import bpmis.pxc.system.templetepojo.TempFunction;
import bpmis.pxc.system.templetepojo.TempIconFunction;

@Controller("functionController")
@RequestMapping("/functionController.do")
public class FunctionController implements BaseController {
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
	 * 得到菜单列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "getListInfo")
	@ResponseBody
	public Map getListInfo(HttpServletRequest request) {
		List<?> list = userService.findByQueryString("from Function"); // 得到一级菜单
		List temp = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Function fun = (Function) list.get(i);
			TempIconFunction tempif = new TempIconFunction();
			tempif.setId(fun.getId());
			tempif.setNodename(fun.getNodename());
			tempif.setNodelevel(fun.getNodelevel());
			tempif.setIsnode(fun.getIsnode());
			tempif.setNodeurl(fun.getNodeurl());
			tempif.setNodeview(fun.getNodeview());
			tempif.setRemark(fun.getRemark());
			// if (fun.getIconsid() != null && !fun.equals("")) {
			// ICons icons = (ICons) userService.getClass(ICons.class,
			// oConvertUtils.getString(fun.getIconsid()));
			// tempif.setIconsname(icons.getImgclassname());
			// }
			if (!fun.getParentid().equals("root"))
				tempif.set_parentId(fun.getParentid());

			temp.add(tempif);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", temp);
		map.put("total", temp.size());
		// System.out.println(JSONHelper.map2json(map));
		return map;
	}

	/**
	 * 懒加载，加载下级菜单
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "funtreejson")
	@ResponseBody
	public List<?> funtreejson(HttpServletRequest request) {
		List<?> list = userService
				.findByQueryString("from Function where parentid = '"
						+ request.getParameter("id").toString() + "'");
		List temp = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Function fun = (Function) list.get(i);
			TempIconFunction tempif = new TempIconFunction();
			tempif.setId(fun.getId());
			tempif.setParentid(fun.getParentid());
			tempif.setNodename(fun.getNodename());
			tempif.setNodelevel(fun.getNodelevel());
			tempif.setIsnode(fun.getIsnode());
			tempif.setNodeurl(fun.getNodeurl());
			tempif.setNodeview(fun.getNodeview());
			if (fun.getIconsid() != null && !fun.equals("")) {
				ICons icons = (ICons) userService.getClass(ICons.class,
						oConvertUtils.getString(fun.getIconsid()));
				tempif.setIconsname(icons.getImgclassname());
			}
			temp.add(tempif);
		}
		return temp;
	}

	/**
	 * 添加下级菜单
	 * 
	 * @return
	 */
	@RequestMapping(params = "refunction")
	public ModelAndView refunction(HttpServletRequest request, ModelMap map,
			String parentid) {
		TempFunction tefun = new TempFunction();
		if (parentid.equals("root")) {// 根节点
			tefun.setParentid("root");
			tefun.setParentname("根节点");
			tefun.setNodelevel("1");
			tefun.setNodelevelname("一级菜单");
		} else {
			Function fun = (Function) userService.findClassById(Function.class,
					parentid);
			tefun.setParentid(oConvertUtils.getString(fun.getId()));
			tefun.setParentname(fun.getNodename());
			if (fun.getNodelevel() == 1) {
				tefun.setNodelevel("2");
				tefun.setNodelevelname("二级菜单");
			}
			if (fun.getNodelevel() == 2) {
				tefun.setNodelevel("3");
				tefun.setNodelevelname("三级菜单");
			}
		}
		List<?> icons = userService.ByCrifindQuery(ICons.class);
		map.put("tefun", tefun);
		map.put("list", icons);
		return new ModelAndView("function/functionadd", map);
	}

	/**
	 * 得到下级菜单
	 * 
	 * @return
	 */
	@RequestMapping(params = "getTree")
	@ResponseBody
	public List getTree() {
		List<?> list = userService
				.findByQueryString("from Function where parentid = 'root'"); // 得到一级菜单
		List<ComboTree> listtemp = new ArrayList<ComboTree>();
		ComboTree com = new ComboTree();
		com.setId("root");
		com.setText("根节点");
		listtemp.add(com);
		for (int i = 0; i < list.size(); i++) {
			Function fun = (Function) list.get(i);
			com = new ComboTree();
			com.setId(oConvertUtils.getString(fun.getId()));
			com.setText(fun.getNodename());
			listtemp.add(com);
		}		
		return listtemp;
	}

	/**
	 * 菜单添加
	 * 
	 * @return
	 */
	@RequestMapping(params = "saveadd")
	public ModelAndView saveadd(Function function) {
		if(function.getParentid().equals("root"))
			function.setNodelevel(1);
		else
			function.setNodelevel(2);
		userService.save(function);
		return new ModelAndView("success");
	}

	/**
	 * 跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "redirect")
	public String redirect() {
		// TODO Auto-generated method stub
		return "function/functionadd";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(params = "deleteObj")
	public ModelAndView deleteObj(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Function function = (Function) userService.getClass(Function.class,
				request.getParameter("id").toString());
		delRoleFunction(function);
		userService.delete(function);
		return new ModelAndView("redirect:/functionController.do?getListInfo");
	}
	
	@RequestMapping(params = "deleteObj_")
	@ResponseBody
	public AjaxJson deleteObj_(HttpServletRequest request) {
		// TODO Auto-generated method stub
		AjaxJson ajx = new AjaxJson();
		Function function = (Function) userService.getClass(Function.class,
				request.getParameter("id").toString());
		delRoleFunction(function);
		userService.delete(function);
		ajx.setMsg("删除成功！");
		return ajx;
	}

	public void delRoleFunction(Function function) {
		// 同步删除角色菜单关联表
		Map<String, Integer> map = HqlQuery.getParmsMaps();
		map.put("function.id", function.getId());
		List<?> roleUserList = userService.ByCrifindQuery(TBRoleFunction.class,
				map);
		if (roleUserList.size() >= 1) {
			for (int i = 0; i < roleUserList.size(); i++) {
				userService.delete(roleUserList.get(i));
			}
		}
	}

	/**
	 * 得到单实例
	 * 
	 * @return
	 */
	@RequestMapping(params = "singleObj")
	@Override
	public ModelAndView singleObj(HttpServletRequest request, ModelMap map) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id").toString();
		Function function = (Function) userService.findClassById(
				Function.class, id);
		map.put("pojo", function);
		return new ModelAndView("function/function_edit", map);
	}

	@Override
	public ModelAndView getListInfo(HttpServletRequest request, ModelMap map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 修改
	 * 
	 * @param obj
	 * @return
	 */
	@RequestMapping(params = "updateObj", method = RequestMethod.POST)
	public ModelAndView updateObj(Function obj) {
		// TODO Auto-generated method stub
		// System.out.println(obj);
		userService.saveOrUpdata(obj);
		return new ModelAndView("success");
	}

}
