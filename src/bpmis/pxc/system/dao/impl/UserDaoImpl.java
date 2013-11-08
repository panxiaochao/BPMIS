package bpmis.pxc.system.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.validator.constraints.Range;
import org.pxcbpmisframework.core.common.hibernate.qbc.CriteriaQuery;
import org.pxcbpmisframework.core.common.hibernate.qbc.HqlQuery;
import org.pxcbpmisframework.core.page.Page;
import org.springframework.stereotype.Repository;

import bpmis.pxc.system.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends BaseCommonDaoImpl implements UserDao {
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
//	@Resource
//	public BaseCommonDaoImpl baseCommonDaoImpl;	
//	public BaseCommonDaoImpl getBaseCommonDaoImpl() {
//		return baseCommonDaoImpl;
//	}
//	public void setBaseCommonDaoImpl(BaseCommonDaoImpl baseCommonDaoImpl) {
//		this.baseCommonDaoImpl = baseCommonDaoImpl;
//	}
	

	/**
	 * 规则：map.put("参数名","值");
	 */

	private Map<String, String> parms = new HashMap<String, String>(); // 參數
	private Map<String, String> mohuparms = new HashMap<String, String>(); // 模糊查询条件参数

	public Map<String, String> getParms() {
		return parms;
	}

	public void setParms(Map<String, String> parms) {
		this.parms = parms;
	}

	public Map<String, String> getMohuparms() {
		return mohuparms;
	}

	public void setMohuparms(Map<String, String> mohuparms) {
		this.mohuparms = mohuparms;
	}

	/**
	 * 《----------------方法------------------》
	 */
	@SuppressWarnings({ "unchecked" })
	public int checkUserExits(String username, String passwordMd5) {
//		parms.put("account", username);
//		parms.put("pwd", passwordMd5);
//		System.out.println(new HqlQuery().getHqlEntity("TBUser", parms));
		List list = getSession().createQuery("from TBUser where account = '"+username+"' and pwd = '"+passwordMd5+"'").list();
		if (list.size()>0)
			return 1;
		return 0;
	}

	public void test() {
		// TODO Auto-generated method stub
		System.out.println("----------->UserDaoImpl");
		// TBUser
		// list=(TBUser)this.baseCommonDaoImpl.findClassById(TBUser.class, "2");
		// System.out.println(list.getAccount());
	}

	






	




	


}
