package bpmis.pxc.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.pxcbpmisframework.core.common.hibernate.qbc.CriteriaQuery;
import org.pxcbpmisframework.core.page.DwzPage;
import org.pxcbpmisframework.core.page.Page;

public interface UserDao {
	public <T> void save(T entity);
	public <T> void saveAll(List<T> list);
	public <T> void saveOrUpdata(T entity);
	public void test();
	public int checkUserExits(String username, String passwordMd5);
	public <T> void delete(T entity);
	public  <T> void deleteID(Class<T> entityName, String id);
	public  <T> void deleteID(Class<T> entityName, Integer id);
	public <T> void deleteAllEntitie(List<T> list);	
	public <T> List<T> findByQueryString(String hql);
	public Object findClassById(Class<?> clazz, String id);
	public <T> void deleteAllEntitieById(Class<T> entityName, String id);
	public Map<?,?> getPageList(Class<?> clazz, CriteriaQuery cq, Page page);
	public Map<?, ?> getPageList_dwz(Class<?> clazz, boolean isAsc,DwzPage dwzpage);
	public Map<?, ?> getPageList_EasyUI(Class<?> clazz, CriteriaQuery cq,Page page);
	public List<?> ByCrifindQuery(Class<?> clazz);
	public List<?> ByCrifindQuery(Class<?> clazz, boolean isAsc, String ordername);
	public List<?> ByCrifindQuery(Class<?> entityClass, Map<String, Integer> parms);

}
