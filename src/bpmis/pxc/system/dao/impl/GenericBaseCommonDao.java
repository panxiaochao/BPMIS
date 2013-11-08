package bpmis.pxc.system.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pxcbpmisframework.core.common.hibernate.qbc.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import bpmis.pxc.system.dao.IGenericBaseCommonDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * 类描述： DAO层泛型基类
 * 
 * @author: panxiaochao
 * @date： 日期：2013-05-28 
 * @version 1.0
 */

@SuppressWarnings("hiding")
public abstract class GenericBaseCommonDao implements IGenericBaseCommonDao {
	
	
	/**
	 * 初始化Log4j的一个实例
	 */
	private static final Logger logger = Logger.getLogger(GenericBaseCommonDao.class);
	/**
	 * 注入一个sessionFactory属性,并注入到父类(HibernateDaoSupport)
	 * **/
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 根据传入的实体持久化对象
	 */
	public <T> void save(T entity) {
		System.out.println("进来了");
		try {
			System.out.println("进来了");
			//Session s=HibernateUtils.getSession();
			//s.save(entity);
			getSession().save(entity);
			System.out.println("进来了");
			getSession().flush();
			
			if (logger.isDebugEnabled()) {
				logger.debug("保存实体成功," + entity.getClass().getName());
			}
			System.out.println("成功了！");
		} catch (RuntimeException e) {
			logger.error("保存实体异常", e);
			throw e;
		}

	}
//
//	/**
//	 * 获得该类的属性和类型
//	 * 
//	 * @param entityName
//	 *            注解的实体类
//	 */
//	private <T> void getProperty(Class entityName) {
//		ClassMetadata cm = sessionFactory.getClassMetadata(entityName);
//		String[] str = cm.getPropertyNames(); // 获得该类所有的属性名称
//		for (int i = 0; i < str.length; i++) {
//			String property = str[i];
//			String type = cm.getPropertyType(property).getName(); // 获得该名称的类型
//			System.out.println(property + "---&gt;" + type);
//		}
//	}
//	/**
//	 * 获取所有数据表
//	 * @return
//	 */
//	public List<DBTable> getAllDbTableName() {
//		List<DBTable> resultList = new ArrayList<DBTable>();
//		SessionFactory factory = getSession().getSessionFactory();
//		Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
//		for (String key : (Set<String>) metaMap.keySet()) {
//			DBTable dbTable=new DBTable();
//			AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap.get(key); 
//			dbTable.setTableName(classMetadata.getTableName());
//			dbTable.setEntityName(classMetadata.getEntityName());
//			dbTable.setTableTitle(classMetadata.getTableName());
//			resultList.add(dbTable);
//		}
//		return resultList;
//	}
//	/**
//	 * 获取所有数据表
//	 * @return
//	 */
//	public Integer getAllDbTableSize() {
//		SessionFactory factory = getSession().getSessionFactory();
//		Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
//		return metaMap.size();
//	}
//
//	/**
//	 * 根据实体名字获取唯一记录
//	 * 
//	 * @param propertyName
//	 * @param value
//	 * @return
//	 */
//	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
//		Assert.hasText(propertyName);
//		return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
//	}
//
//	/**
//	 * 按属性查找对象列表.
//	 */
//	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
//		Assert.hasText(propertyName);
//		return (List<T>) createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
//	}
//

//
//	/**
//	 * 根据传入的实体添加或更新对象
//	 * 
//	 * @param <T>
//	 * 
//	 * @param entity
//	 */
//
//	public <T> void saveOrUpdate(T entity) {
//		try {
//			getSession().saveOrUpdate(entity);
//			getSession().flush();
//			if (logger.isDebugEnabled()) {
//				logger.debug("添加或更新成功," + entity.getClass().getName());
//			}
//		} catch (RuntimeException e) {
//			logger.error("添加或更新异常", e);
//			throw e;
//		}
//	}
//
//	/**
//	 * 根据传入的实体删除对象
//	 */
//	public <T> void delete(T entity) {
//		try {
//			getSession().delete(entity);
//			getSession().flush();
//			if (logger.isDebugEnabled()) {
//				logger.debug("删除成功," + entity.getClass().getName());
//			}
//		} catch (RuntimeException e) {
//			logger.error("删除异常", e);
//			throw e;
//		}
//	}
//
//	/**
//	 * 根据主键删除指定的实体
//	 * 
//	 * @param <T>
//	 * @param pojo
//	 */
//	public <T> void deleteEntityById(Class entityName, Serializable id) {
//		delete(get(entityName, id));
//		getSession().flush();
//	}
//
//	/**
//	 * 删除全部的实体
//	 * 
//	 * @param <T>
//	 * 
//	 * @param entitys
//	 */
//	public <T> void deleteAllEntitie(Collection<T> entitys) {
//		for (Object entity : entitys) {
//			getSession().delete(entity);
//			getSession().flush();
//		}
//	}
//
//	/**
//	 * 根据Id获取对象。
//	 */
//	public <T> T get(Class<T> entityClass, final Serializable id) {
//
//		return (T) getSession().get(entityClass, id);
//
//	}
//
//	/**
//	 * 根据主键获取实体并加锁。
//	 * 
//	 * @param <T>
//	 * @param entityName
//	 * @param id
//	 * @param lock
//	 * @return
//	 */
//	public <T> T getEntity(Class entityName, Serializable id) {
//
//		T t = (T) getSession().get(entityName, id);
//		if (t != null) {
//			getSession().flush();
//		}
//		return t;
//	}
//
//	/**
//	 * 更新指定的实体
//	 * 
//	 * @param <T>
//	 * @param pojo
//	 */
//	public <T> void updateEntitie(T pojo) {
//		getSession().update(pojo);
//		getSession().flush();
//	}
//
//	/**
//	 * 更新指定的实体
//	 * 
//	 * @param <T>
//	 * @param pojo
//	 */
//	public <T> void updateEntitie(String className, Object id) {
//		getSession().update(className, id);
//		getSession().flush();
//	}
//
//	/**
//	 * 根据主键更新实体
//	 */
//	public <T> void updateEntityById(Class entityName, Serializable id) {
//		updateEntitie(get(entityName, id));
//	}
//
//	/**
//	 * 通过hql 查询语句查找对象
//	 * 
//	 * @param <T>
//	 * @param query
//	 * @return
//	 */
//	public List<T> findByQueryString(final String query) {
//
//		Query queryObject = getSession().createQuery(query);
//		List<T> list = queryObject.list();
//		if (list.size() > 0) {
//			getSession().flush();
//		}
//		return list;
//
//	}
//
//	/**
//	 * 通过hql查询唯一对象
//	 * 
//	 * @param <T>
//	 * @param query
//	 * @return
//	 */
//	public <T> T singleResult(String hql) {
//		T t = null;
//		Query queryObject = getSession().createQuery(hql);
//		List<T> list = queryObject.list();
//		if (list.size() == 1) {
//			getSession().flush();
//			t = list.get(0);
//		} else if (list.size() > 0) {
//			throw new BusinessException("查询结果数:" + list.size() + "大于1");
//		}
//		return t;
//	}
//
//	/**
//	 * 通过hql 查询语句查找HashMap对象
//	 * 
//	 * @param <T>
//	 * @param query
//	 * @return
//	 */
//	public Map<Object, Object> getHashMapbyQuery(String hql) {
//
//		Query query = getSession().createQuery(hql);
//		List list = query.list();
//		Map<Object, Object> map = new HashMap<Object, Object>();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Object[] tm = (Object[]) iterator.next();
//			map.put(tm[0].toString(), tm[1].toString());
//		}
//		return map;
//
//	}
//
//	/**
//	 * 通过sql更新记录
//	 * 
//	 * @param <T>
//	 * @param query
//	 * @return
//	 */
//	public int updateBySqlString(final String query) {
//
//		Query querys = getSession().createSQLQuery(query);
//		return querys.executeUpdate();
//	}
//
//	/**
//	 * 通过sql查询语句查找对象
//	 * 
//	 * @param <T>
//	 * @param query
//	 * @return
//	 */
//	public List<T> findListbySql(final String sql) {
//		Query querys = getSession().createSQLQuery(sql);
//		return querys.list();
//	}
//
//	/**
//	 * 创建Criteria对象，有排序功能。
//	 * 
//	 * @param <T>
//	 * @param entityClass
//	 * @param orderBy
//	 * @param isAsc
//	 * @param criterions
//	 * @return
//	 */
//	private <T> Criteria createCriteria(Class<T> entityClass, boolean isAsc, Criterion... criterions) {
//		Criteria criteria = createCriteria(entityClass, criterions);
//		if (isAsc) {
//			criteria.addOrder(Order.asc("asc"));
//		} else {
//			criteria.addOrder(Order.desc("desc"));
//		}
//		return criteria;
//	}
//
//	/**
//	 * 创建Criteria对象带属性比较
//	 * 
//	 * @param <T>
//	 * @param entityClass
//	 * @param criterions
//	 * @return
//	 */
//	private <T> Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
//		Criteria criteria = getSession().createCriteria(entityClass);
//		for (Criterion c : criterions) {
//			criteria.add(c);
//		}
//		return criteria;
//	}
//
//	public <T> List<T> loadAll(final Class<T> entityClass) {
//		Criteria criteria = createCriteria(entityClass);
//		return criteria.list();
//	}
//
//	/**
//	 * 创建单一Criteria对象
//	 * 
//	 * @param <T>
//	 * @param entityClass
//	 * @param criterions
//	 * @return
//	 */
//	private <T> Criteria createCriteria(Class<T> entityClass) {
//		Criteria criteria = getSession().createCriteria(entityClass);
//		return criteria;
//	}
//
//	/**
//	 * 根据属性名和属性值查询. 有排序
//	 * 
//	 * @param <T>
//	 * @param entityClass
//	 * @param propertyName
//	 * @param value
//	 * @param orderBy
//	 * @param isAsc
//	 * @return
//	 */
//	public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc) {
//		Assert.hasText(propertyName);
//		return createCriteria(entityClass, isAsc, Restrictions.eq(propertyName, value)).list();
//	}
//
//	/**
//	 * 根据属性名和属性值 查询 且要求对象唯一.
//	 * 
//	 * @return 符合条件的唯一对象.
//	 */
//	public <T> T findUniqueBy(Class<T> entityClass, String propertyName, Object value) {
//		Assert.hasText(propertyName);
//		return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
//	}
//
//	/**
//	 * 根据查询条件与参数列表创建Query对象
//	 * 
//	 * @param session
//	 *            Hibernate会话
//	 * @param hql
//	 *            HQL语句
//	 * @param objects
//	 *            参数列表
//	 * @return Query对象
//	 */
//	public Query createQuery(Session session, String hql, Object... objects) {
//		Query query = session.createQuery(hql);
//		if (objects != null) {
//			for (int i = 0; i < objects.length; i++) {
//				query.setParameter(i, objects[i]);
//			}
//		}
//		return query;
//	}
//
//	/**
//	 * 批量插入实体
//	 * 
//	 * @param clas
//	 * @param values
//	 * @return
//	 */
//	public <T> int batchInsertsEntitie(List<T> entityList) {
//		int num = 0;
//		for (int i = 0; i < entityList.size(); i++) {
//			save(entityList.get(i));
//			num++;
//		}
//		return num;
//	}
//
//	/**
//	 * 根据实体名返回全部对象
//	 * 
//	 * @param <T>
//	 * @param hql
//	 * @param size
//	 * @return
//	 */
//	/**
//	 * 使用占位符的方式填充值 请注意：like对应的值格式："%"+username+"%" Hibernate Query
//	 * 
//	 * @param hibernateTemplate
//	 * @param hql
//	 * @param valus
//	 *            占位符号?对应的值，顺序必须一一对应 可以为空对象数组，但是不能为null
//	 * @return 2008-07-19 add by liuyang
//	 */
//	public List<T> executeQuery(final String hql, final Object[] values) {
//		Query query = getSession().createQuery(hql);
//		// query.setCacheable(true);
//		for (int i = 0; values != null && i < values.length; i++) {
//			query.setParameter(i, values[i]);
//		}
//
//		return query.list();
//
//	}
//
//	/**
//	 * 根据实体模版查找
//	 * 
//	 * @param entityName
//	 * @param exampleEntity
//	 * @return
//	 */
//
//	public List findByExample(final String entityName, final Object exampleEntity) {
//		Assert.notNull(exampleEntity, "Example entity must not be null");
//		Criteria executableCriteria = (entityName != null ? getSession().createCriteria(entityName) : getSession().createCriteria(exampleEntity.getClass()));
//		executableCriteria.add(Example.create(exampleEntity));
//		return executableCriteria.list();
//	}
//
//	// 使用指定的检索标准获取满足标准的记录数
//	public Integer getRowCount(DetachedCriteria criteria) {
//		return oConvertUtils.getInt(((Criteria) criteria.setProjection(Projections.rowCount())).uniqueResult(), 0);
//	}
//
//	/**
//	 * 调用存储过程
//	 */
//	public void callableStatementByName(String proc) {
//	}
//
//	/**
//	 * 查询指定实体的总记录数
//	 * 
//	 * @param clazz
//	 * @return
//	 */
//	public int getCount(Class<T> clazz) {
//
//		int count = DataAccessUtils.intResult(getSession().createQuery("select count(*) from " + clazz.getName()).list());
//		return count;
//	}
//
//	/**
//	 * 获取分页记录CriteriaQuery 老方法final int allCounts = oConvertUtils.getInt(criteria .setProjection(Projections.rowCount()).uniqueResult(), 0);
//	 * 
//	 * @param cq
//	 * @param isOffset
//	 * @return
//	 */
//	public PageList getPageList(final CriteriaQuery cq, final boolean isOffset) {
//
//		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
//		CriteriaImpl impl = (CriteriaImpl) criteria;
//		// 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
//		Projection projection = impl.getProjection();
//		final int allCounts = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//		criteria.setProjection(projection);
//		if (projection == null) {
//			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
//		}
//
//		// 判断是否有排序字段
//		if (cq.getOrdermap() != null) {
//			cq.setOrder(cq.getOrdermap());
//		}
//		int pageSize = cq.getPageSize();// 每页显示数
//		int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage(), pageSize);// 当前页
//		int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
//		String toolBar = "";
//		if (isOffset) {// 是否分页
//			criteria.setFirstResult(offset);
//			criteria.setMaxResults(cq.getPageSize());
//			if (cq.getIsUseimage() == 1) {
//				toolBar = PagerUtil.getBar(cq.getMyAction(), cq.getMyForm(), allCounts, curPageNO, pageSize, cq.getMap());
//			} else {
//				toolBar = PagerUtil.getBar(cq.getMyAction(), allCounts, curPageNO, pageSize, cq.getMap());
//			}
//		} else {
//			pageSize = allCounts;
//		}
//		return new PageList(criteria.list(), toolBar, offset, curPageNO, allCounts);
//	}
//
//	/**
//	 * 返回JQUERY datatables DataTableReturn模型对象
//	 */
//	public DataTableReturn getDataTableReturn(final CriteriaQuery cq, final boolean isOffset) {
//
//		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
//		CriteriaImpl impl = (CriteriaImpl) criteria;
//		// 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
//		Projection projection = impl.getProjection();
//		final int allCounts = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//		criteria.setProjection(projection);
//		if (projection == null) {
//			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
//		}
//
//		// 判断是否有排序字段
//		if (cq.getOrdermap() != null) {
//			cq.setOrder(cq.getOrdermap());
//		}
//		int pageSize = cq.getPageSize();// 每页显示数
//		int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage(), pageSize);// 当前页
//		int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
//		if (isOffset) {// 是否分页
//			criteria.setFirstResult(offset);
//			criteria.setMaxResults(cq.getPageSize());
//		} else {
//			pageSize = allCounts;
//		}
//		DetachedCriteriaUtil.selectColumn(cq.getDetachedCriteria(), cq.getField().split(","), cq.getEntityClass(), false);
//		return new DataTableReturn(allCounts, allCounts, cq.getDataTables().getEcho(), criteria.list());
//	}
//
//	/**
//	 * 返回easyui datagrid DataGridReturn模型对象
//	 */
//	public DataGridReturn getDataGridReturn(final CriteriaQuery cq, final boolean isOffset) {
//		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
//		CriteriaImpl impl = (CriteriaImpl) criteria;
//		// 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
//		Projection projection = impl.getProjection();
//		final int allCounts = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//		criteria.setProjection(projection);
//		if (projection == null) {
//			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
//		}
//		//-----update-begin---author:zhangdaihao date:20130206 for:排序字段---
//		if (StringUtils.isNotBlank(cq.getDataGrid().getSort())) {
//		//-----update-begin---author:zhangdaihao date:20130206 for:排序字段---
//			cq.addOrder(cq.getDataGrid().getSort(), cq.getDataGrid().getOrder());
//		}
//
//		// 判断是否有排序字段
//		if (!cq.getOrdermap().isEmpty()) {
//			cq.setOrder(cq.getOrdermap());
//		}
//		int pageSize = cq.getPageSize();// 每页显示数
//		int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage(), pageSize);// 当前页
//		int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
//		if (isOffset) {// 是否分页
//			criteria.setFirstResult(offset);
//			criteria.setMaxResults(cq.getPageSize());
//		} else {
//			pageSize = allCounts;
//		}
//		// DetachedCriteriaUtil.selectColumn(cq.getDetachedCriteria(),
//		// cq.getField().split(","), cq.getClass1(), false);
//		List list = criteria.list();
//		cq.getDataGrid().setReaults(list);
//		cq.getDataGrid().setTotal(allCounts);
//		return new DataGridReturn(allCounts, list);
//	}
//
//	/**
//	 * 获取分页记录SqlQuery
//	 * 
//	 * @param cq
//	 * @param isOffset
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public PageList getPageListBySql(final HqlQuery hqlQuery, final boolean isToEntity) {
//
//		Query query = getSession().createSQLQuery(hqlQuery.getQueryString());
//
//		// query.setParameters(hqlQuery.getParam(), (Type[])
//		// hqlQuery.getTypes());
//		int allCounts = query.list().size();
//		int curPageNO = hqlQuery.getCurPage();
//		int offset = PagerUtil.getOffset(allCounts, curPageNO, hqlQuery.getPageSize());
//		query.setFirstResult(offset);
//		query.setMaxResults(hqlQuery.getPageSize());
//		List list = null;
//		if (isToEntity) {
//			list = ToEntityUtil.toEntityList(query.list(), hqlQuery.getClass1(), hqlQuery.getDataGrid().getField().split(","));
//		} else {
//			list = query.list();
//		}
//		return new PageList(hqlQuery, list, offset, curPageNO, allCounts);
//	}
//
//	/**
//	 * 获取分页记录HqlQuery
//	 * 
//	 * @param cq
//	 * @param isOffset
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public PageList getPageList(final HqlQuery hqlQuery, final boolean needParameter) {
//
//		Query query = getSession().createQuery(hqlQuery.getQueryString());
//		if (needParameter) {
//			query.setParameters(hqlQuery.getParam(), (Type[]) hqlQuery.getTypes());
//		}
//		int allCounts = query.list().size();
//		int curPageNO = hqlQuery.getCurPage();
//		int offset = PagerUtil.getOffset(allCounts, curPageNO, hqlQuery.getPageSize());
//		String toolBar = PagerUtil.getBar(hqlQuery.getMyaction(), allCounts, curPageNO, hqlQuery.getPageSize(), hqlQuery.getMap());
//		query.setFirstResult(offset);
//		query.setMaxResults(hqlQuery.getPageSize());
//		return new PageList(query.list(), toolBar, offset, curPageNO, allCounts);
//	}
//
//	/**
//	 * 根据CriteriaQuery获取List
//	 * 
//	 * @param cq
//	 * @param isOffset
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public List<T> getListByCriteriaQuery(final CriteriaQuery cq, Boolean ispage) {
//		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
//		// 判断是否有排序字段
//		if (cq.getOrdermap() != null) {
//			cq.setOrder(cq.getOrdermap());
//		}
//		if (ispage)
//			criteria.setMaxResults(cq.getPageSize());
//		return criteria.list();
//
//	}
}
