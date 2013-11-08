package bpmis.pxc.system.controller.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.pxcbpmisframework.core.common.hibernate.qbc.CriteriaQuery;
import org.pxcbpmisframework.core.common.hibernate.qbc.HqlQuery;
import org.pxcbpmisframework.core.json.AjaxJson;
import org.pxcbpmisframework.core.json.ComboTree;
import org.pxcbpmisframework.core.json.ZTreeEntity;
import org.pxcbpmisframework.core.page.Page;
import org.pxcbpmisframework.core.util.ImportUtil;
import org.pxcbpmisframework.core.util.JSONHelper;
import org.pxcbpmisframework.core.util.StringUtil;
import org.pxcbpmisframework.core.util.oConvertUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bpmis.pxc.system.pojo.Function;
import bpmis.pxc.system.pojo.Role;
import bpmis.pxc.system.pojo.TBRoleFunction;
import bpmis.pxc.system.pojo.TBUser;
import bpmis.pxc.system.pojo.User_Role;
import bpmis.pxc.system.service.UserService;

@Controller("roleController")
@RequestMapping("/roleController.do")
public class RoleController implements BaseController {
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
	 * 得到角色管理
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "getRoleInfo")
	@ResponseBody
	public Map getRoleInfo(HttpServletRequest request) {

		// --------------------------------必须优化的代码
		String pageNum = request.getParameter("page");// 当前页
		String rows = request.getParameter("rows");// 每页数据
		CriteriaQuery cq = new CriteriaQuery();
		Page page = new Page();
		if (null != pageNum && null != rows) {
			page.setCurrentPage(pageNum);
			page.setPageSize(rows);
		}

		Map<?, ?> mapPage = userService
				.getPageList_EasyUI(Role.class, cq, page);
		List<?> list = (List) mapPage.get("list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", ((Page) mapPage.get("page")).getTotalRecords());
		return map;
	}

	/**
	 * 跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "roleaddjsp")
	public String roleaddjsp() {
		return "user_role/roleadd";
	}

	/**
	 * 角色添加
	 * 
	 * @return
	 */
	@RequestMapping(params = "roleadd")
	public String roleadd(Role role) {
		userService.save(role);
		return "success";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(params = "deleteObj")
	public ModelAndView deleteObj(HttpServletRequest request) {
		Role role = (Role) userService.findClassById(Role.class, request
				.getParameter("id").toString());
		delRoleFunction(role);// 删除角色前先删除关联表
		delUserRole(role);
		userService.delete(role);
		return new ModelAndView("redirect:/roleController.do?getRoleInfo");
	}

	@RequestMapping(params = "deleteObj_")
	@ResponseBody
	public AjaxJson deleteObj_(HttpServletRequest request) {
		AjaxJson ajx = new AjaxJson();
		Role role = (Role) userService.findClassById(Role.class, request
				.getParameter("id").toString());
		delRoleFunction(role);// 删除角色前先删除关联表
		delUserRole(role);
		userService.delete(role);
		ajx.setMsg("删除成功！");
		return ajx;
	}

	public void delUserRole(Role role) {
		// 同步删除用户角色关联表
		Map<String, Integer> map = HqlQuery.getParmsMaps();
		map.put("role.id", role.getId());
		List<?> roleUserList = userService.ByCrifindQuery(User_Role.class, map);
		if (roleUserList.size() >= 1) {
			for (int i = 0; i < roleUserList.size(); i++) {
				userService.delete(roleUserList.get(i));
			}
		}
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

	/**
	 * 得到单个实例
	 */
	@RequestMapping(params = "singleObj")
	public ModelAndView singleObj(HttpServletRequest request, ModelMap map) {
		// TODO Auto-generated method stub
		Role role = (Role) userService.findClassById(Role.class, request
				.getParameter("id").toString());
		map.put("pojo", role);
		return new ModelAndView("role/role_edit", map);
	}

	/**
	 * 修改
	 * 
	 * @param obj
	 * @return
	 */
	@RequestMapping(params = "updateObj", method = RequestMethod.POST)
	public ModelAndView updateObj(Role obj) {
		// TODO Auto-generated method stub
		// System.out.println(obj);
		userService.saveOrUpdata(obj);
		return new ModelAndView("success");
	}

	/**
	 * 得到角色设置权限
	 * 
	 * @Title: getRoleAuthority
	 * @param @param request
	 * @param @param map
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView getRoleAuthority(HttpServletRequest request,
			ModelMap map) {
		String roleid = request.getParameter("id");
		// Role role = (Role) userService.getClass(Role.class, id);
		List<?> list = userService.ByCrifindQuery(Function.class);
		Map<String, Integer> maptree = HqlQuery.getParmsMaps();
		maptree.put("role.id", oConvertUtils.getInt(roleid));
		List<?> roleUserList = userService.ByCrifindQuery(TBRoleFunction.class,
				maptree);
		List tree = new ArrayList();
		tree = getRoot();
		for (int i = 0; i < list.size(); i++) {
			Function function = (Function) list.get(i);
			ZTreeEntity ztree = new ZTreeEntity();
			// if (function.getNodelevel() == 1)//一级默认展开
			// ztree.setOpen(true);
			if (function.getIsnode().equals("1")) {
				ztree.setIsParent("true");
				ztree.setOpen(true);
			}
			ztree.setId(String.valueOf(function.getId()));
			ztree.setpId(function.getParentid());
			ztree.setName(function.getNodename());
			if (comparefun(function, roleUserList))
				ztree.setChecked(true);
			tree.add(ztree);
		}
		System.out.println(JSONHelper.toJSONString(tree));
		map.put("ztree", JSONHelper.toJSONString(tree));
		map.put("roleid", roleid);
		return new ModelAndView("user_role/functiontree", map);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "getRoleAuthority")
	@ResponseBody
	public List getRoleAuthority(HttpServletRequest request) {
		String roleid = request.getParameter("id");
		// Role role = (Role) userService.getClass(Role.class, id);
		List<?> list = userService
				.findByQueryString("from Function where parentid ='root'");
		Map<String, Integer> maptree = HqlQuery.getParmsMaps();
		maptree.put("role.id", oConvertUtils.getInt("20"));
		List<?> roleUserList = userService.ByCrifindQuery(TBRoleFunction.class,
				maptree);
		List tree = new ArrayList();

		for (int i = 0; i < list.size(); i++) {
			Function fun = (Function) list.get(i);
			ComboTree cbtree = new ComboTree();

			cbtree.setId(oConvertUtils.getString(fun.getId()));
			cbtree.setText(fun.getNodename());
			if (fun.getParentid().equals("root")) {
				List childrenlist = userService
						.findByQueryString("from Function where parentid ='"
								+ fun.getId() + "'");
				if (childrenlist != null && childrenlist.size() > 0) {
					List temp = new ArrayList();
					for (int j = 0; j < childrenlist.size(); j++) {
						Function chfun = (Function) childrenlist.get(j);
						ComboTree chtree = new ComboTree();
						chtree.setId(oConvertUtils.getString(chfun.getId()));
						chtree.setText(chfun.getNodename());
						temp.add(chtree);
					}
					cbtree.setChildren(temp);
				}
			}
			tree.add(cbtree);
		}
		return tree;
	}

	// 比较
	private boolean comparefun(Function function, List<?> roleUserList) {
		// TODO Auto-generated method stub
		if (roleUserList.size() >= 1) {
			for (int i = 0; i < roleUserList.size(); i++) {
				TBRoleFunction tbrolefun = (TBRoleFunction) roleUserList.get(i);
				Function fun = tbrolefun.getFunction();
				if (fun.getId().equals(function.getId()))
					return true;
			}
		}
		return false;
	}

	/**
	 * 置根节点
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getRoot() {
		List tree = new ArrayList();
		ZTreeEntity ztree = new ZTreeEntity();
		ztree.setId("root");
		ztree.setpId("0");
		ztree.setName("菜单列表");
		ztree.setOpen(true);
		ztree.setNocheck(true);
		tree.add(ztree);
		return tree;
	}

	/**
	 * 角色设置权限
	 * 
	 * @Title: setRoleAuthority
	 * @param @param request
	 * @param @param map
	 * @throws
	 */
	@RequestMapping(params = "setRoleAuthority")
	@ResponseBody
	public AjaxJson setRoleAuthority(HttpServletRequest request) {
		AjaxJson ajx = new AjaxJson();
		String id = request.getParameter("id");
		// |--2013.07.21 BUG:解决无选择状态下，不需要保存原数据，导致取不到Functionid！
		if (id.equals("")) {
			ajx.setMsg("这是原数据，无需保存！");
			return ajx;
		}
		// ---|
		String roleid = request.getParameter("roleid");
		Role role = (Role) userService.getClass(Role.class, roleid);
		// 先删除关联表，更新
		delRoleFunction(role);
		List<String> functionlist = StringUtil.parseString2ListByPattern(id);
		if (functionlist != null && functionlist.size() > 0) {
			saveRoleFunction(role, functionlist);
		}
		ajx.setMsg("设置成功");
		return ajx;
	}

	// |-----@ManyToOne<Role_Function>-------|
	protected void saveRoleFunction(Role role, List<String> functionlist) {
		for (int i = 0; i < functionlist.size(); i++) {
			TBRoleFunction rolefunction = new TBRoleFunction();
			Function function = (Function) userService.findClassById(
					Function.class, functionlist.get(i));
			rolefunction.setRole(role);
			rolefunction.setFunction(function);
			userService.save(rolefunction);
		}
	}

	public void delRoleFunction(Role role) {
		// 同步删除角色菜单关联表
		Map<String, Integer> map = HqlQuery.getParmsMaps();
		map.put("role.id", role.getId());
		List<?> roleUserList = userService.ByCrifindQuery(TBRoleFunction.class,
				map);
		if (roleUserList.size() >= 1) {
			for (int i = 0; i < roleUserList.size(); i++) {
				userService.delete(roleUserList.get(i));
			}
		}
	}

}
