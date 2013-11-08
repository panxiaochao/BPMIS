<%@ page pageEncoding="utf-8"%>
<%
String rpath = request.getContextPath();
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+rpath+"/";
%>
<script type="text/javascript">
//修改
function changeobj(str, url) {
	var url = <%=rootPath %>+url;
	document.getElementById("sid").value = str;
	form(url);
}
//删除
function deleteobj(str, url) {
	var url = <%=rootPath %>+url;
	var submit = function(v, h, f) {
		if (v == 'ok') {
			document.getElementById("sid").value = str;
			form(url);
		}
		return true; //close
	};
	jconfirm("确定要删除数据吗？", "提示", submit);

}
function form(url) {
	jtip("loading......", "loading");
	var form = document.dech;
	form.action = url;
	form.submit();
}
</script>
