package bpmis.pxc.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.pxcbpmisframework.core.common.hibernate.qbc.CriteriaQuery;
import org.pxcbpmisframework.core.common.hibernate.qbc.HibernateUtils;
import org.pxcbpmisframework.core.exception.BusinessException;
import org.pxcbpmisframework.core.page.DwzPage;
import org.pxcbpmisframework.core.page.Page;
import org.pxcbpmisframework.core.page.PageHtmlUtils;

public class BaseCommonDaoImpl {

	private static final Logger logger = Logger
			.getLogger(BaseCommonDaoImpl.class);

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	public Session getCurrentSession() {
		return HibernateUtils.currentSession();
	}

	/**
	 * @Title: save
	 */
	@SuppressWarnings("hiding")
	public <T> void save(T entity) {
		// TODO Auto-generated method stub
		try {
			getSession().save(entity);
			getSession().flush();
			// tx.commit();
		} catch (RuntimeException e) {
			logger.error(e);
			throw new BusinessException(e);
		} finally {
			// HibernateUtils.endTransaction();
		}

	}

	/**
	 * @Title: saveOrUpdata
	 */
	@SuppressWarnings("hiding")
	public <T> void saveOrUpdata(T entity) {
		// TODO Auto-generated method stub
		try {
			getSession().saveOrUpdate(entity);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error(e);
			throw new BusinessException(e);
		} finally {
			// s.close();
		}
	}

	/**
	 * @Title: saveAll
	 */
	@SuppressWarnings("hiding")
	public <T> void saveAll(List<T> list) {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < list.size(); i++) {
				getSession().save(list.get(i));
				getSession().flush();
			}
		} catch (RuntimeException e) {
			logger.error(e);
			throw new BusinessException(e);
		}
	}

	/**
	 * @Title: update
	 */
	@SuppressWarnings("hiding")
	public <T> void update(T entity) {
		// TODO Auto-generated method stub
		try {
			getSession().update(entity);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error(e);
			throw new BusinessException(e);
		} finally {
			// s.close();
		}
	}

	/**
	 * @Title: delete
	 */
	@SuppressWarnings("hiding")
	public <T> void delete(T entity) {
		// TODO Auto-generated method stub

		try {
			getSession().delete(entity);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error(e);
			throw new BusinessException(e);
		} finally {
			// s.close();
		}
	}

	/**
	 * @Title: deleteIntID
	 */
	@SuppressWarnings("hiding")
	public <T> void deleteID(Class<T> entity, Integer id) {
		// TODO Auto-generated method stub
		try {
			Object object = getSession().load(entity, id);
			getSession().delete(object);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error(e);
			throw new BusinessException(e);
		} finally {
			// s.close();
		}
	}

	/**
	 * @Title: deleteStringID
	 */
	@SuppressWarnings("hiding")
	public <T> void deleteID(Class<T> entity, String id) {
		// TODO Auto-generated method stub
		try {
			Object object = getSession().load(entity, id);
			getSession().delete(object);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error(e);
			throw new BusinessException(e);
		} finally {
			// s.close();
		}
	}

	/**
	 * @Title: deleteAllEntitieById
	 */
	@SuppressWarnings("hiding")
	public <T> void deleteAllEntitieById(Class<T> entityName, String id) {
		// TODO Auto-generated method stub
		List<?> list = findByQueryString("from " + entityName + " where id='"
				+ id + "'");
		if (list != null && list.size() > 0)
			deleteAllEntitie(list);
	}

	/**
	 * @Title: deleteAllEntitie
	 */
	@SuppressWarnings("hiding")
	public <T> void deleteAllEntitie(List<T> list) {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < list.size(); i++) {
				getSession().delete(list.get(i));
				getSession().flush();
			}
		} catch (RuntimeException e) {
			logger.error(e);
			throw new BusinessException(e);
		} finally {
			// s.close();
		}
	}

	/**
	 * 获得该类的属性和类型
	 * 
	 * @param entityName
	 */

	@SuppressWarnings("hiding")
	public <T> void getProperty(Class<T> entityName) {
		ClassMetadata cm = HibernateUtils.getSessionFactory().getClassMetadata(
				entityName);
		String[] str = cm.getPropertyNames(); // 获得该类所有的属性名称
		for (int i = 0; i < str.length; i++) {
			String property = str[i];
			String type = cm.getPropertyType(property).getName(); // 获得该名称的类型
			System.out.println(property + "---&gt;" + type);
		}
	}

	/**
	 * 获取所有数据表
	 * 
	 * @return
	 */
	public List<T> getAllDbTableName() {
		List<T> resultList = new ArrayList<T>();
		Map<String, ClassMetadata> metaMap = HibernateUtils.getSessionFactory()
				.getAllClassMetadata();
		for (String key : (Set<String>) metaMap.keySet()) {
			AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap
					.get(key);
			System.out.println(classMetadata.getTableName() + "--"
					+ classMetadata.getEntityName() + "--"
					+ classMetadata.getTableName());
			// resultList.add(dbTable);
		}
		return resultList;
	}

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByQueryString(String query) {
		List list = getSession().createQuery(query).list();
		if (list.size() > 0) {
			getSession().flush();
		}
		return list;
	}

	/**
	 * 通过id加载实体
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public Object findClassById(Class<?> clazz, String id) {
		Object obj = null;
		try {
			obj = getSession().load(clazz, Integer.parseInt(id));
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		} finally {
			// s.close();
		}
		return obj;
	}

	// ******************************************************************
	// ---------------------下面采用Criteria方法--------------------------
	// ******************************************************************
	/**
	 * Restrictions.eq --> equal,等于.
	 * 
	 * Restrictions.allEq -->
	 * 参数为Map对象,使用key/value进行多个等于的比对,相当于多个Restrictions.eq的效果
	 * 
	 * Restrictions.gt --> great-than > 大于
	 * 
	 * Restrictions.ge --> great-equal >= 大于等于
	 * 
	 * Restrictions.lt --> less-than, < 小于
	 * 
	 * Restrictions.le --> less-equal <= 小于等于
	 * 
	 * Restrictions.between --> 对应SQL的between子句
	 * 
	 * Restrictions.like --> 对应SQL的LIKE子句
	 * 
	 * Restrictions.in --> 对应SQL的in子句
	 * 
	 * Restrictions.and --> and 关系
	 * 
	 * Restrictions.or --> or 关系
	 * 
	 * Restrictions.isNull --> 判断属性是否为空,为空则返回true
	 * 
	 * Restrictions.isNotNull --> 与isNull相反
	 * 
	 * Restrictions.sqlRestriction --> SQL限定的查询
	 * 
	 * Order.asc --> 根据传入的字段进行升序排序
	 * 
	 * Order.desc --> 根据传入的字段进行降序排序
	 * 
	 * MatchMode.EXACT --> 字符串精确匹配.相当于"like 'value'"
	 * 
	 * MatchMode.ANYWHERE --> 字符串在中间匹配.相当于"like '%value%'"
	 * 
	 * MatchMode.START --> 字符串在最前面的位置.相当于"like 'value%'"
	 * 
	 * MatchMode.END --> 字符串在最后面的位置.相当于"like '%value'"
	 */
	private DetachedCriteria detachedCriteria;

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	/**
	 * 根据classname 获取本表的list 代替queryString ：from ***
	 */
	@SuppressWarnings("unchecked")
	public List<T> ByCrifindQuery(Class<?> clazz) {
		return getCriteria(clazz).list();
	}

	/**
	 * 带有排序
	 * 
	 * @Title: ByCrifindQuery
	 */
	@SuppressWarnings("unchecked")
	public List<T> ByCrifindQuery(Class<?> clazz, boolean isAsc,
			String ordername) {
		Criteria criteria = getCriteria(clazz);
		if (isAsc)
			criteria.addOrder(Order.asc(ordername));
		else
			criteria.addOrder(Order.desc(ordername));
		return criteria.list();
	}

	/**
	 * 分页,返回pageHtml
	 */

	@SuppressWarnings( { "static-access", "unchecked" })
	public Map<?, ?> getPageList(Class<?> clazz, CriteriaQuery cq, Page page) {
		PageHtmlUtils pageHtml = new PageHtmlUtils();
		Map map = new HashMap();
		//
		Criteria criteria = getCriteria(clazz);
		int totalRecords = ((Long) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue();
		page.setTotalRecords(totalRecords);
		if (cq.isAsc()) {
			if (!"".equals(cq.getFiled()) && cq.getFiled() != null)
				criteria.addOrder(Order.asc(cq.getFiled()));
		} else {
			if (!"".equals(cq.getFiled()) && cq.getFiled() != null)
				criteria.addOrder(Order.desc(cq.getFiled()));
		}
		int currentPage = page.getCurrentPage();
		int pageSize = page.getPageSize();
		criteria.setProjection(null);// 清空projection，以便取得记录
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);// 设置查询结果为实体对象，
		criteria.setFirstResult((currentPage - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		String pagehtml = pageHtml.getDefault(page);

		map.put("list", criteria.list());
		map.put("pagehtml", pagehtml);
		return map;
	}

	/**
	 * 分页,返回pageHtml
	 */

	@SuppressWarnings( { "unchecked" })
	public Map<?, ?> getPageList_EasyUI(Class<?> clazz, CriteriaQuery cq,
			Page page) {
		Map map = new HashMap();
		//
		Criteria criteria = getCriteria(clazz);
		int totalRecords = ((Long) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue();
		page.setTotalRecords(totalRecords);
		if (cq.isAsc()) {
			if (!"".equals(cq.getFiled()) && null != cq.getFiled())
				criteria.addOrder(Order.asc(cq.getFiled()));
		} else {
			if (!"".equals(cq.getFiled()) && null != cq.getFiled())
				criteria.addOrder(Order.desc(cq.getFiled()));
		}
		int currentPage = page.getCurrentPage();
		int pageSize = page.getPageSize();
		criteria.setProjection(null);// 清空projection，以便取得记录
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);// 设置查询结果为实体对象，
		criteria.setFirstResult((currentPage - 1) * pageSize);
		criteria.setMaxResults(pageSize);

		map.put("list", criteria.list());
		map.put("page", page);
		return map;
	}

	/**
	 * 扩展，用于DWZ分页
	 */

	@SuppressWarnings( { "unchecked" })
	public Map<?, ?> getPageList_dwz(Class<?> clazz, boolean isAsc,
			DwzPage dwzpage) {
		Map map = new HashMap();
		//
		Criteria criteria = getCriteria(clazz);
		// if(isAsc)
		// criteria.addOrder(Order.asc("asc"));
		// else
		// criteria.addOrder(Order.desc("desc"));
		int totalRecords = ((Long) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue();
		dwzpage.setTotalRecords(totalRecords);
		int currentPage = dwzpage.getPageNum();
		int pageSize = dwzpage.getNumPerPage();
		// 查询记录
		criteria.setProjection(null);// 清空projection，以便取得记录
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);// 设置查询结果为实体对象，
		criteria.setFirstResult((currentPage - 1) * pageSize); // 问题出现在这
		criteria.setMaxResults(pageSize);

		map.put("list", criteria.list());
		map.put("dwzpage", dwzpage);
		return map;
	}

	/**
	 * 通过Criteria传值Map得到list
	 * 
	 * @Title: ByCrifindQuery
	 * @param @param entityClass
	 * @param @param parms
	 * @throws
	 */
	public List<?> ByCrifindQuery(Class<?> entityClass,
			Map<String, Integer> parms) {
		Criteria criteria = getSession().createCriteria(entityClass);
		Set<String> setkey = parms.keySet();
		for (Object key : setkey) {
			Integer value = parms.get(key);
			criteria.add(Restrictions.eq(key.toString(), value));
		}
		return criteria.list();
	}

	/**
	 * 得到Criteria
	 * 
	 * @Title: getCriteria
	 * @throws
	 */
	public Criteria getCriteria(Class<?> clazz) {
		return getSession().createCriteria(clazz);
	}

}
