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
				$("#searchForm").attr("action","<%=basePath %>systemController.do?getFeed");
				$("#searchForm").submit();
			}
	    	return false;
		}
		
		</script>
		<script src="<%=basePath%>js/jquery.bcustom.js" type="text/javascript" ></script>
	</head>
	<body>
<!-- 面包屑 -->
    <ul class="breadcrumb">
      <li>系统设置 <span class="divider">/</span></li>
      <li class="active">反馈管理</li>     
    </ul>
		<table id="contentTable"
			class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th width="190">反馈邮箱</th>
					<th>反馈内容</th>
					<th width="150">提交时间</th>
					<th width="80">回馈状态</th>
					<th>操作</th>
	
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.list}" var="st">	
				<!-- kuai-->
				<tr>
					<td>${st.feedemail}</td>
					<td>${st.feedcontent}</td>
					<td>${st.feedtime}</td>
					<td><c:if test="${st.feedstate==0}"><span class="text-error">未回馈</span></c:if><c:if test="${st.feedstate==1}"><span class="text-success">已回馈</span></c:if></td>
					<td><a href="javascript:feed('${st.id}')">回执</a></td>

				</tr>
				<!-- //kuai-->
			</c:forEach>
			</tbody>
		</table>
		<!-- 分页-->
		${requestScope.pagehtml}
		<!-- //分页-->
		<script src="<%=basePath%>js/bootstrap.min.js"></script>
		<%@include file="/webpage/bootstrapModal/feedModal.jsp"%>
	</body>	
</html>