package bpmis.pxc.system.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bpmis.pxc.system.dao.ICommonDao;
import bpmis.pxc.system.service.CommonService;



@Transactional
public class CommonServiceImpl implements CommonService {
	@Resource
	public ICommonDao commonDao;
	
	@Override
	public <T> void delete(T entity) {
		commonDao.delete(entity);
		
	}

	@Override
	public <T> void deleteAllEntitie(Collection<T> entities) {
		commonDao.deleteAllEntitie(entities);
		
	}

	@Override
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		commonDao.deleteEntityById(entityName, id);
		
	}

	@Override
	public <T> T get(Class<T> class1, Serializable id) {
		// TODO Auto-generated method stub
		return commonDao.get(class1, id);
	}

	@Override
	public <T> void save(T entity) {
		commonDao.save(entity);
		
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);
		
	}
//	public List<DBTable> getAllDbTableName()
//	{
//		return commonDao.getAllDbTableName();
//	}
//	public Integer getAllDbTableSize() {
//		return commonDao.getAllDbTableSize();
//	}

//
//	@Override
//	public <T> void save(T entity) {
//		commonDao.save(entity);
//	}
//
//	@Override
//	public <T> void saveOrUpdate(T entity) {
//		commonDao.saveOrUpdate(entity);
//
//	}
//
//	@Override
//	public <T> void delete(T entity) {
//		commonDao.delete(entity);
//
//	}
//
//	/**
//	 * 删除实体集合
//	 * 
//	 * @param <T>
//	 * @param entities
//	 */
//	public <T> void deleteAllEntitie(Collection<T> entities) {
//		commonDao.deleteAllEntitie(entities);
//	}
//
//	/**
//	 * 根据实体名获取对象
//	 */
//	public <T> T get(Class<T> class1, Serializable id) {
//		return commonDao.get(class1, id);
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
//	public <T> List<T> getList(Class clas) {
//		return commonDao.loadAll(clas);
//	}
//
//	/**
//	 * 根据实体名获取对象
//	 */
//	public <T> T getEntity(Class entityName, Serializable id) {
//		return commonDao.getEntity(entityName, id);
//	}
//
//	/**
//	 * 根据实体名称和字段名称和字段值获取唯一记录
//	 * 
//	 * @param <T>
//	 * @param entityClass
//	 * @param propertyName
//	 * @param value
//	 * @return
//	 */
//	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
//		return commonDao.findUniqueByProperty(entityClass, propertyName, value);
//	}
//
//	/**
//	 * 按属性查找对象列表.
//	 */
//	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
//
//		return commonDao.findByProperty(entityClass, propertyName, value);
//	}
//
//	/**
//	 * 加载全部实体
//	 * 
//	 * @param <T>
//	 * @param entityClass
//	 * @return
//	 */
//	public <T> List<T> loadAll(final Class<T> entityClass) {
//		return commonDao.loadAll(entityClass);
//	}
//	
//	public <T> T singleResult(String hql)
//	{
//		return commonDao.singleResult(hql);
//	}
//
//	/**
//	 * 删除实体主键ID删除对象
//	 * 
//	 * @param <T>
//	 * @param entities
//	 */
//	public <T> void deleteEntityById(Class entityName, Serializable id) {
//		commonDao.deleteEntityById(entityName, id);
//	}
//
//	/**
//	 * 更新指定的实体
//	 * 
//	 * @param <T>
//	 * @param pojo
//	 */
//	public <T> void updateEntitie(T pojo) {
//		commonDao.updateEntitie(pojo);
//
//	}
//
//	/**
//	 * 通过hql 查询语句查找对象
//	 * 
//	 * @param <T>
//	 * @param query
//	 * @return
//	 */
//	public <T> List<T> findByQueryString(String hql) {
//		return commonDao.findByQueryString(hql);
//	}
//
//	/**
//	 * 根据sql更新
//	 * 
//	 * @param query
//	 * @return
//	 */
//	public int updateBySqlString(String sql) {
//		return commonDao.updateBySqlString(sql);
//	}
//
//	/**
//	 * 根据sql查找List
//	 * 
//	 * @param <T>
//	 * @param query
//	 * @return
//	 */
//	public <T> List<T> findListbySql(String query) {
//		return commonDao.findListbySql(query);
//	}
//	

	
	
}
