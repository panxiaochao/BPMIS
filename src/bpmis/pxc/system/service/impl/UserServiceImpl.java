package bpmis.pxc.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.pxcbpmisframework.core.common.hibernate.qbc.CriteriaQuery;
import org.pxcbpmisframework.core.page.DwzPage;
import org.pxcbpmisframework.core.page.Page;
import org.pxcbpmisframework.core.util.BrowserUtils;
import org.pxcbpmisframework.core.util.ContextHolderUtils;
import org.pxcbpmisframework.core.util.DataToolsUtils;
import org.pxcbpmisframework.core.util.IpUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bpmis.pxc.system.dao.UserDao;
import bpmis.pxc.system.pojo.TBLogger;
import bpmis.pxc.system.pojo.TBUser;
import bpmis.pxc.system.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	public UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 《--------------------方法------------------》
	 */

	public int checkUserExits(String username, String passwordMd5) {
		return userDao.checkUserExits(username, passwordMd5);
	}

	@Override
	public <T> void save(T entity) {
		// TODO Auto-generated method stub
		userDao.save(entity);
	}

	public void test() {
		System.out.println("----------->go UserServiceImpl");
		userDao.test();
		System.out.println("----------->end UserServiceImpl");
	}

	@Override
	public <T> void delete(T entity) {
		// TODO Auto-generated method stub
		userDao.delete(entity);
	}

	@Override
	public <T> void deleteEntityById(Class<T> entityName, String id) {
		// TODO Auto-generated method stub
		userDao.deleteID(entityName, id);
	}

	@Override
	public Object getClass(Class<?> clazz, String id) {
		// TODO Auto-generated method stub
		return userDao.findClassById(clazz, id);
	}

	@Override
	public <T> List<T> findByQueryString(String hql) {
		// TODO Auto-generated method stub
		return userDao.findByQueryString(hql);
	}

	public Object findClassById(Class<?> clazz, String id) {
		return userDao.findClassById(clazz, id);
	}

	@Override
	public <T> void saveOrUpdata(T entity) {
		// TODO Auto-generated method stub
		userDao.saveOrUpdata(entity);
	}

	@Override
	public <T> List<T> getUser(String trim) {
		// TODO Auto-generated method stub
		return userDao.findByQueryString("from TBUser where account = '" + trim
				+ "'");
	}

	@Override
	public <T> void saveAll(List<T> list) {
		// TODO Auto-generated method stub
		userDao.saveAll(list);
	}

	@Override
	public <T> void deleteAllEntitieById(Class<T> entityName, String id) {
		// TODO Auto-generated method stub
		userDao.deleteAllEntitieById(entityName, id);
	}

	@Override
	public Map<?, ?> getPageList(Class<?> clazz, CriteriaQuery cq, Page page) {
		// TODO Auto-generated method stub
		return userDao.getPageList(clazz, cq, page);
	}

	@Override
	public Map<?, ?> getPageList_dwz(Class<?> clazz, boolean isAsc,
			DwzPage dwzpage) {
		// TODO Auto-generated method stub
		return userDao.getPageList_dwz(clazz, isAsc, dwzpage);
	}

	@Override
	public void deleteEntityById(Class<?> classname, Integer parseInt) {
		// TODO Auto-generated method stub
		userDao.deleteID(classname, parseInt);
	}

	@Override
	public List<?> ByCrifindQuery(Class<?> clazz) {
		// TODO Auto-generated method stub
		return userDao.ByCrifindQuery(clazz);
	}

	@Override
	public List<?> ByCrifindQuery(Class<?> clazz, boolean isAsc,
			String ordername) {
		// TODO Auto-generated method stub
		return userDao.ByCrifindQuery(clazz, isAsc, ordername);
	}

	@Override
	public List<?> ByCrifindQuery(Class<?> entityClass,
			Map<String, Integer> parms) {
		// TODO Auto-generated method stub
		return userDao.ByCrifindQuery(entityClass, parms);
	}

	@Override
	public void addLogger(String logcontent, String loglevel, String operatetype) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ContextHolderUtils.getRequest();
		String broswer = BrowserUtils.checkBrowse(request);
		TBLogger logger = new TBLogger();
		TBUser user = new TBUser();
		if (ContextHolderUtils.getSessionUser() != null) {
			user = ContextHolderUtils.getSessionUser();
			logger.setTbuser(user);
			logger.setAccount(user.getAccount());
		} else {
			user.setId(0);// 不是在登录的情况下出错记录
			logger.setTbuser(user);
		}
		logger.setLoglevel(loglevel);
		logger.setLogcontent(logcontent);
		logger.setBroswertype(broswer);
		logger.setOperatetime(DataToolsUtils.getSimpleDateFormat());
		logger.setOperatetype(operatetype);
		logger.setAdrip(IpUtils.getIp());
		userDao.save(logger);
	}
	// public String getUserRole(TBUser user){
	// return this.commonDao.getUserRole(user);
	// }

	@Override
	public Map<?, ?> getPageList_EasyUI(Class<?> clazz, CriteriaQuery cq,
			Page page) {
		// TODO Auto-generated method stub
		return userDao.getPageList_EasyUI(clazz, cq, page);
	}
}
