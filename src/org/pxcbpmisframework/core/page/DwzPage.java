package org.pxcbpmisframework.core.page;

public class DwzPage {
	private int pageNum = 1;// 当前页
	private int numPerPage = 10; // 每页的数量
	private int totalRecords;// 总记录数
	private int totalPages;// 总页数

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		currentPageExceptionMsg(String.valueOf(pageNum));
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		pageSizeExceptionMsg(String.valueOf(numPerPage));
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public DwzPage() {

	}

	/**
	 * 带参的构造方法
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param totalRecords
	 */
	public DwzPage(int currentPage, int pageSize, int totalRecords) {
		pageSizeExceptionMsg(String.valueOf(pageSize));
		currentPageExceptionMsg(String.valueOf(currentPage));
		totalRecordExceptionMsg(String.valueOf(totalRecords));
	}

	/**
	 * 带参的构造方法
	 * 
	 * @param currentPageStr
	 * @param pageSizeStr
	 * @param totalRecordsStr
	 */
	public DwzPage(String currentPageStr, String pageSizeStr,
			String totalRecordsStr) {
		pageSizeExceptionMsg(pageSizeStr);
		currentPageExceptionMsg(currentPageStr);
		totalRecordExceptionMsg(totalRecordsStr);
	}

	/**
	 * 设置总记录
	 * 
	 * @param totalRecords
	 *            整型参数
	 */
	public final void setTotalRecords(int totalRecords) {
		totalRecordExceptionMsg(String.valueOf(totalRecords));
	}

	/**
	 * 设置总记录
	 * 
	 * @param totalRecords
	 *            整型参数
	 */
	public final void setTotalRecords(String totalRecordsStr) {
		totalRecordExceptionMsg(totalRecordsStr);
	}

	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public final int getTotalPages() {
		calculateTotalPages();
		return totalPages;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public final int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * 反馈当前页的异常
	 * 
	 * @param currentPageStr
	 */
	private void currentPageExceptionMsg(String currentPageStr) {
		try {
			pageNum = Integer.parseInt(String.valueOf(currentPageStr));
		} catch (NumberFormatException e) {
			throw new RuntimeException("当前页数字格式有误-->" + pageNum);
		}
		if (pageNum <= 0) {
			throw new RuntimeException("当前页小于或等于零-->" + pageNum);
		}
	}

	/**
	 * 反馈分页单位的异常
	 * 
	 * @param pageSizeStr
	 */
	private void pageSizeExceptionMsg(String pageSizeStr) {
		try {
			numPerPage = Integer.parseInt(String.valueOf(pageSizeStr));
		} catch (NumberFormatException e) {
			throw new RuntimeException("当前页数字格式有误-->" + numPerPage);
		}
		if (numPerPage <= 0) {
			throw new RuntimeException("分页单位小于或等于零-->" + numPerPage);
		}
	}

	/**
	 * 反馈总记录数的异常
	 * 
	 * @param totalRecordsStr
	 */
	private void totalRecordExceptionMsg(String totalRecordsStr) {
		try {
			totalRecords = Integer.parseInt(String.valueOf(totalRecordsStr));
		} catch (NumberFormatException e) {
			throw new RuntimeException("总记录数字格式有误-->" + totalRecordsStr);
		}
		if (totalRecords < 0) {
			throw new RuntimeException("总记录小于零-->" + totalRecordsStr);
		}
	}

	/**
	 * 计算总页数
	 */
	private void calculateTotalPages() {
		totalPages = (int) Math.ceil((double) this.totalRecords
				/ (double) numPerPage);
	}
}
