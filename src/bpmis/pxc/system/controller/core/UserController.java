package bpmis.pxc.system.controller.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.pxcbpmisframework.core.common.hibernate.qbc.CriteriaQuery;
import org.pxcbpmisframework.core.common.hibernate.qbc.HqlQuery;
import org.pxcbpmisframework.core.constant.Globals;
import org.pxcbpmisframework.core.json.AjaxJson;
import org.pxcbpmisframework.core.json.ZTreeEntity;
import org.pxcbpmisframework.core.page.DwzPage;
import org.pxcbpmisframework.core.page.Page;
import org.pxcbpmisframework.core.util.ContextHolderUtils;
import org.pxcbpmisframework.core.util.DataToolsUtils;
import org.pxcbpmisframework.core.util.ExceptionUtil;
import org.pxcbpmisframework.core.util.ImportUtil;
import org.pxcbpmisframework.core.util.JSONHelper;
import org.pxcbpmisframework.core.util.PasswordUtil;
import org.pxcbpmisframework.core.util.StringUtil;
import org.pxcbpmisframework.core.util.oConvertUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bpmis.pxc.system.pojo.Role;
import bpmis.pxc.system.pojo.TBUser;
import bpmis.pxc.system.pojo.User_Role;
import bpmis.pxc.system.service.UserService;
import bpmis.pxc.system.templetepojo.TempUser_Role;

@Controller("userController")
@RequestMapping("/userController.do")
public class UserController implements BaseController {
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
	 * 对于首页验证通过转后台页面
	 */
	@RequestMapping(params = "loginbpmis")
	public ModelAndView loginbpmis(HttpServletRequest request) {
		System.out.println("----------->进来了！");
		return new ModelAndView("mianFram");
	}

	/**
	 * 首先检查用户名是否正确
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 */
	@RequestMapping(params = "checkuserlogin")
	@ResponseBody
	public AjaxJson checkuserlogin(String username, String pwd, String checkcode) {
		AjaxJson ajx = new AjaxJson();
		String safecode = ContextHolderUtils.getSession().getAttribute("code")
				.toString();
		try {
			if (safecode.toLowerCase().equals(checkcode.toLowerCase().trim())) {
				int log = userService.checkUserExits(username.trim(),
						PasswordUtil.PasswordMd5(pwd));
				if (log == 1) {
					TBUser user = (TBUser) userService.getUser(username.trim())
							.get(0);
					ContextHolderUtils.getSession().setAttribute(
							Globals.USER_SESSION, user);
					ajx.setMsg("登录成功！");
				} else {
					ajx.setMsg("请检查BPMIS专业U盾是否插入,或者用户名或密码错误！");
					ajx.setSuccess(false);
				}
			} else {
				ajx.setMsg("验证码不匹配，或点击刷新重填！");
				ajx.setSuccess(false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
			userService.addLogger(e.getMessage(), Globals.Logger_Leavel_ERROR,
					Globals.Logger_Type_LOGIN);
		}
		return ajx;
	}

	/**
	 * 对于首页验证通过转后台页面
	 */
	@RequestMapping(params = "loginout")
	public ModelAndView loginout(HttpServletRequest request) {
		ContextHolderUtils.getSession().removeAttribute(Globals.USER_SESSION);
		System.out.println("---->退出 ");
		return new ModelAndView("login/login");
	}

	/**
	 * 得到用户管理
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "getUserInfo")
	@ResponseBody
	public Map getUserInfo(HttpServletRequest request) {
		String pageNum = request.getParameter("page");// 当前页
		String rows = request.getParameter("rows");// 每页数据
		CriteriaQuery cq = new CriteriaQuery(false, "regtime");
		Page page = new Page();
		if (null != pageNum && null != rows) {
			page.setCurrentPage(pageNum);
			page.setPageSize(rows);
		}
		Map<?, ?> mapPage = userService.getPageList_EasyUI(TBUser.class, cq,
				page);
		List<?> temp = (List) mapPage.get("list");
		List list = new ArrayList();
		String rolename = "";
		for (int i = 0; i < temp.size(); i++) {
			TBUser user = (TBUser) temp.get(i);
			TempUser_Role tempuserrole = new TempUser_Role();
			rolename = getRolename(user);
			tempuserrole.setId(user.getId());
			tempuserrole.setBpmisid(user.getBpmisid());
			tempuserrole.setAccount(user.getAccount());
			tempuserrole.setUsername(user.getUsername());
			tempuserrole.setSex(user.getSex());
			tempuserrole.setEmail(user.getEmail());
			tempuserrole.setTel(user.getTel());
			tempuserrole.setRolename(rolename.substring(0,
					rolename.length() - 1));
			tempuserrole.setRegtime(user.getRegtime());
			list.add(tempuserrole);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", ((Page) mapPage.get("page")).getTotalRecords());
		return map;
	}

	// Why 这个取数据要独立出来？|------getRolename()
	public String getRolename(TBUser user) {
		Map<String, Integer> map = HqlQuery.getParmsMaps();
		map.put("tsuser.id", user.getId());
		String rolename = "";
		List<?> roleUserList = userService.ByCrifindQuery(User_Role.class, map);
		if (roleUserList.size() >= 1) {
			for (int i = 0; i < roleUserList.size(); i++) {
				User_Role usro = (User_Role) roleUserList.get(i);
				Role role = usro.getRole();
				rolename += role.getRolename() + ";";
			}
		} else {
			rolename = "无;";
		}
		return rolename;
	}

	/**
	 * 跳转页面---用户添加
	 * 
	 * @return
	 */
	@RequestMapping(params = "useraddjsp")
	public ModelAndView useraddjsp(ModelMap map) {
		List<?> list = userService.findByQueryString("from Role");
		map.put("list", list);
		return new ModelAndView("user/user_add", map);
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
		ztree.setId("1");
		ztree.setpId("0");
		ztree.setName("角色列表");
		ztree.setOpen(true);
		ztree.setNocheck(true);
		tree.add(ztree);
		return tree;
	}

	/**
	 * 用户添加(BUG：缺少检查用户是否存在)
	 * 
	 * @return
	 */
	@RequestMapping(params = "useradd", method = RequestMethod.POST)
	public ModelAndView useradd(TBUser user, String password, String sex,
			String roleid) {
		user.setPwd(PasswordUtil.PasswordMd5(password));
		if (sex.equals("0"))
			user.setSex("男");
		else
			user.setSex("女");
		user.setRegtime(DataToolsUtils.getSimpleDateFormat());
		// 设置用户状态
		// 设置默认角色
		// 设置默认权限
		/**
		 * 这里先存用户再存关联表，否则会报错。因为没有与之对应的外键
		 */
		userService.save(user);
		List<String> rolelist = StringUtil.parseString2ListByPattern(roleid);
		if (rolelist != null && rolelist.size() > 0) {
			saveRoleUser(user, rolelist);
		}
		return new ModelAndView("success");
	}

	// |-----@ManyToOne<User_Role>-------|
	protected void saveRoleUser(TBUser user, List<String> userrolelist) {
		for (int i = 0; i < userrolelist.size(); i++) {
			User_Role userrole = new User_Role();
			Role role = (Role) userService.findClassById(Role.class,
					userrolelist.get(i));
			userrole.setRole(role);
			userrole.setTsuser(user);
			userService.save(userrole);
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "deleteObj")
	public ModelAndView deleteObj(HttpServletRequest request) {
		Class classname = ImportUtil.getEntityClassI(request.getParameter(
				"class").toString());
		TBUser user = (TBUser) userService.findClassById(classname, request
				.getParameter("id").toString());
		delUserRole(user);// 删除用户时先删除用户和角色关系表
		userService.delete(user);
		return new ModelAndView("redirect:/userController.do?getUserInfo");
	}

	@RequestMapping(params = "deleteObj_")
	@ResponseBody
	public AjaxJson deleteObj_(HttpServletRequest request) {
		AjaxJson ajx = new AjaxJson();
		TBUser user = (TBUser) userService.findClassById(TBUser.class, request
				.getParameter("id").toString());
		delUserRole(user);// 删除用户时先删除用户和角色关系表
		userService.delete(user);
		ajx.setMsg("删除成功！");
		return ajx;
	}

	public void delUserRole(TBUser user) {
		// 同步删除用户角色关联表
		Map<String, Integer> map = HqlQuery.getParmsMaps();
		map.put("tsuser.id", user.getId());
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
	 * 
	 * @return
	 */
	@Override
	@RequestMapping(params = "singleObj")
	public ModelAndView singleObj(HttpServletRequest request, ModelMap map) {
		// TODO Auto-generated method stub
		TBUser user = (TBUser) userService.findClassById(TBUser.class, request
				.getParameter("id").toString());
		List<?> rolelist = userService.ByCrifindQuery(Role.class);
		Map<String, Integer> mappar = HqlQuery.getParmsMaps();
		mappar.put("tsuser.id", user.getId());
		List<?> roleUserList = userService.ByCrifindQuery(User_Role.class,
				mappar);

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < rolelist.size(); i++) {
			Role rolet = (Role) rolelist.get(i);
			boolean flag = false;
			for (int j = 0; j < roleUserList.size(); j++) {
				User_Role ur = (User_Role) roleUserList.get(j);
				Role role = ur.getRole();
				if (rolet.getRolename().equals(role.getRolename())) {
					flag = true;
					break;
				}
			}
			if(flag)
				list.add("<option value=\""+rolet.getId()+"\" selected=\"selected\" >"+rolet.getRolename()+"</option>");
			else
				list.add("<option value=\""+rolet.getId()+"\" >"+rolet.getRolename()+"</option>");
		}
		map.put("list", list);
		map.put("pojo", user);
		return new ModelAndView("user/user_edit", map);
	}
	/**
	 * 修改
	 * 
	 * @param obj
	 * @return
	 */
	@RequestMapping(params = "updateObj", method = RequestMethod.POST)
	public ModelAndView updateObj(TBUser obj) {
		// TODO Auto-generated method stub
		// System.out.println(obj);
		userService.saveOrUpdata(obj);
		return new ModelAndView("success");
	}
}
