<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="pagerForm" method="post" action="userController.do?getUserInfo">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="10" />
	<input type="hidden" name="orderField" value="" />
    <input type="hidden" name="orderDirection" value="asc" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
	<div class="searchBar">
		<!--<ul class="searchContent">
			<li>
				<label>我的客户：</label>
				<input type="text"/>
			</li>
			<li>
			<select class="combox" name="province">
				<option value="">所有省市</option>
				<option value="北京">北京</option>
				<option value="上海">上海</option>
				<option value="天津">天津</option>
				<option value="重庆">重庆</option>
				<option value="广东">广东</option>
			</select>
			</li>
		</ul>
		-->
		<table class="searchContent">
			<tr>
				<td>
					
				</td>
				<td>
				
				</td>
				<td>
			
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="demo_page4.html" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="demo_page4.html?uid={sid_user}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="20" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100" >用户编号</th>
				<th width="100" >用户账号</th>
				<th width="100" >用户名</th>
				<th width="150" >角色</th>
				<th width="20" >性别</th>
				<th width="80" >出生日期</th>
				<th width="120" >邮箱</th>
				<th width="100" >电话</th>
				<th width="120" >注册时间</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach items="${requestScope.list}" var="st">
			<tr target="sid_user" rel="${st.id}">
				<td><input name="ids" value="${st.id}" type="checkbox"></td>
				<td>${st.bpmisid}</td>
				<td>${st.account}</td>
				<td>${st.username}</td>
				<td>${st.rolename}</td>
				<td>${st.sex}</td>
				<td>${st.birthday}</td>
				<td>${st.email}</td>
				<td>${st.tel}</td>
				<td>${st.regtime}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${requestScope.page.totalRecords}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${requestScope.page.totalRecords}" numPerPage="${requestScope.page.numPerPage}" pageNumShown="10" currentPage="${requestScope.page.pageNum}"></div>

	</div>
</div>
