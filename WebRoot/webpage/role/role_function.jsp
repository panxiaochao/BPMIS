<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


%>


<script type="text/javascript">
	$(function() {
		layout_west_tree = $('#layout_west_tree').tree({
			url : "<%=basePath%>roleController.do?getRoleAuthority&id=20",
			checkbox : true,
			loadMsg:'数据加载中......',
			onClick : function(node) {
				
			},
			onDblClick : function(node) { //双击展开
				
			},
			onBeforeLoad : function(node, param) {
				
			},
			onLoadSuccess : function(node, data) {
				$.messager.progress('close');
			}
		});
		
	});

</script>
<ul id="layout_west_tree" class="easyui-tree" data-options="animate:true,lines:true"></ul>


