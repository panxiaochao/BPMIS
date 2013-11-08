package org.pxcbpmisframework.core.common.hibernate.qbc;

/**
 * @author panxiaochao
 * @date： 日期：2013.06.09
 */
public class SqlQuery implements Query {
	/**
	 * hql:select * from entityname
	 * 
	 * @param entityname
	 * @return
	 */
	public String getSQLList(String entityname) {
		return TBSELECT + "*" + TBFROM + entityname;
	}

}
