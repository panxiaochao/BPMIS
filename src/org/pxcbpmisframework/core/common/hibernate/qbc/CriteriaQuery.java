package org.pxcbpmisframework.core.common.hibernate.qbc;

import java.util.List;

import org.pxcbpmisframework.core.page.Page;

/**
 * CriteriaQuery 集合类
 * 
 * @author panxiaochao
 * @ClassName CriteriaQuery
 * @Description TODO
 * @date 2013-8-15
 */
public class CriteriaQuery {
	private boolean isAsc = false;
	private String filed;// 排序字段
	private List reaults;// 结果集
	private Page page;

	public List getReaults() {
		return reaults;
	}

	public void setReaults(List reaults) {
		this.reaults = reaults;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public CriteriaQuery() {
	}

	public CriteriaQuery(boolean isAsc, String filed) {
		super();
		this.isAsc = isAsc;
		this.filed = filed;
	}
	public CriteriaQuery(String filed) {
		super();
		this.filed = filed;
	}
	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}

}
