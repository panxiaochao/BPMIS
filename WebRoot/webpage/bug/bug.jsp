<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/webpage/top_tld/core_tld.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
	<head>
		<title></title>
		<%@include file="/webpage/top_tld/bootstrap_tld.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10">
		<script type="text/javascript">
		function page(n, s) {
			if(n == ""){
				jinfo("请输入页数");
				return false;
			}else{	
				jtip("读取中......", 'loading');
				$("#pageNo").val(n);
				//$("#pageSize").val(s);
				$("#searchForm").attr("action","<%=basePath %>systemController.do?getBug");
				$("#searchForm").submit();
			}
	    	return false;
		}
		function openinfo(info){
			jinfo(info,"BUG内容");	
		}
		</script>
		
	</head>
	<body>
	<!-- 面包屑 -->
    <ul class="breadcrumb">
      <li>系统设置 <span class="divider">/</span></li>
      <li class="active">BUG管理</li>     
    </ul>
		<table id="contentTable"
			class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th width="35">编号</th>
					<th width="60">操作人</th>
					<th width="70">BUG级别</th>
					<th width="120">BUG类型</th>
					<th width="">BUG内容</th>
					<th width="80">浏览器类型</th>
					<th width="150">操作时间</th>
					<th width="100">记录IP</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.list}" var="st">	
				<!-- kuai-->
				<tr>
					<td>${st.id}</td>
					<td>${st.account}</td>
					<td>${st.loglevel}</td>
					<td>${st.operatetype}</td>
					<td title="${st.logcontent}">${fn:substring(st.logcontent,"0","80")}......  <a href="#" onclick="openinfo('${st.logcontent}')">详情</a></td>
					<td>${st.broswertype}</td>
					<td>${st.operatetime}</td>
					<td>${st.adrip}</td>
				</tr>
				<!-- //kuai-->
			</c:forEach>
			</tbody>
		</table>
		<!-- 分页-->
		${requestScope.pagehtml}
		<!-- //分页-->
		<script type="text/javascript">
		function openinfo(info){
			jinfo(info,"BUG内容");	
		}
		</script>
	</body>
</html>