<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!--easyui-->
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/easyui/themes/icon.css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>plugin/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
<!--custom-->
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/custom.css">
<script type="text/javascript">
var treeGrid;
$(function() {
	treeGrid=$('#tt').treegrid({  
		url:'<%=basePath %>functionController.do?getListInfo', 
		method: 'post',
		rownumbers: true,
		//fitColumns:true,
		fit:true,
		border:false,
		loadMsg:'数据加载中......',
		striped:true, //斑马线
		idField: 'id',
		animate: true,
		treeField: 'nodename',
		toolbar  : '#tb',
		onLoadSuccess : function() {
			//$(this).treegrid('tooltip');
		}
		
	}); 
});


</script>
</head>
<body class="easyui-layout">
<div data-options="region:'east',split:true,border:false" title="按钮权限" style="width:250px;padding:10px;">按钮权限</div>
<div data-options="region:'center',border:false">

    <table id="tt" style="">
      <thead>
        <tr>
          <th data-options="field:'nodename'" width="200">菜单名</th>
          <th data-options="field:'iconsname'" width="50">图标</th>
          <th data-options="field:'nodeurl'" width="200">URL地址</th>
          <th data-options="field:'nodeview'" width="40">状态</th>
          <th data-options="field:'nodelevel'" width="100">菜单级数</th>
          <th data-options="field:'remark'" width="300">备注</th>            
        </tr>
      </thead>
    </table>
</div>
<div id="tb" style="padding:5px;height:auto">
  <div style="margin-bottom:5px"> <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="add()">添加</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="edit()">修改</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="deleteobj()">删除</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onClick="reload()">刷新</a>
  </div>
  
</div>
<script type="text/javascript">
document.getElementById("pframe").src="";
//add
function add(){
	document.getElementById("pframe").src="function_add.jsp"; 
	$('#winForm').window({  
		width:900,  
		height:425, 
		title:'添加菜单',
		collapsible:false,
		minimizable:false,
		draggable:false,
		resizable:false, 
		modal:true  
	});
}
//edit
function edit(){
	var row = treeGrid.treegrid('getSelected');
	if (row){
		document.getElementById("pframe").src="<%=basePath %>functionController.do?singleObj&id="+row.id; 
		$('#winForm').window({  
			width:900,  
			height:425, 
			title:'编辑菜单',
			collapsible:false,
			minimizable:false,
			draggable:false,
			resizable:false, 
			modal:true  
		});	
	}else{
		$.messager.alert('提示', "请选择一条数据！");
	}	
}
//删除
function deleteobj() {
	var url = "<%=basePath %>functionController.do?deleteObj_";	
	var row = $('#tt').datagrid('getSelected');
	if (row){
		$.messager.confirm('删除', '您是否要删除当前数据？', function(b) {
				if (b) {
					$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					
					$.post( url, {id : row.id}, function(data) {
						if (data.success) {
							$.messager.progress('close');
							$.messager.alert('提示', data.msg, 'info');
							treeGrid.treegrid('reload');
						}						
					}, 'json');
				}
			})
	}else{
		$.messager.alert('提示', "请选择一条数据！");
	}	
}
function reload(){
	treeGrid.treegrid('reload');
}
</script>
<div id="winForm" style="overflow:hidden;">  
<iframe scrolling="yes" id="pframe" frameborder="0" src="" style="width:100%;height:100%;"></iframe>   
</div>
</body>
</html>