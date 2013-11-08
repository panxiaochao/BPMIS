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
var dataGrid;
$(function() {
	dataGrid = $('#tt').datagrid({  
		url:'<%=basePath %>userController.do?getUserInfo', 
		border : false,	
		method: 'post',
		singleSelect:true, //单选	
		loadMsg:'数据加载中......',
		fit: true,
		fitColumns:true,
		nowrap: false, 
		sortName: 'user.id', //排序的列  
        sortOrder: 'desc', //倒序  
		idField : 'id',
		striped:true, //斑马线
		pagination:true,//分页控件  
        rownumbers:true,//行号
		toolbar  : '#tb'		   
	}); 
	//设置分页控件  
    var p = $('#tt').datagrid('getPager');  
    $(p).pagination({  
        pageSize: 10,
        pageList: [10,20,30,40,50],
        onBeforeRefresh:function(){ 
            $(this).pagination('loading'); 
            alert('before refresh'); 
            $(this).pagination('loaded'); 
        } 
    }); 
});
</script>
</head>
<body>
<table id="tt" style="">
  <thead>
    <tr>
      <th data-options="field:'ck',checkbox:true"></th>
      <th data-options="field:'bpmisid',width:100">BPMIN_ID</th>
      <th data-options="field:'account',width:100">用户账号</th>
      <th data-options="field:'username',width:70">用户名</th>
      <th data-options="field:'rolename',width:180">角色</th>
      <th data-options="field:'sex',width:30,align:'center'">性别</th>
      <th data-options="field:'birthday',width:80,align:'center'">出生日期</th>
      <th data-options="field:'email',width:160">邮箱</th>
      <th data-options="field:'tel',width:100">电话</th>
      <th data-options="field:'regtime',width:150">注册时间</th>
    </tr>
  </thead>
</table>
<div id="tb" style="padding:5px;height:auto">
  <div style="margin-bottom:5px"> 
  <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="add()">添加</a> 
  <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="edit()">编辑</a> 
  <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a> 
  <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true">剪切</a> 
  <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="deleteobj()">移除</a> 
  </div>
</div>
<script type="text/javascript">
parent.document.getElementById("pframe").src="";
//add
function add(){	
	parent.document.getElementById("pframe").src="<%=basePath %>userController.do?useraddjsp"; 
	parent.$('#winForm').window({  
		width:900,  
		height:575, 
		title:'添加用户',
		collapsible:false,
		minimizable:false,
		draggable:false,
		resizable:false, 
		modal:true  
	});	
}
//edit
function edit(){
	var row = $('#tt').datagrid('getSelected');
	if (row){
		parent.document.getElementById("pframe").src="<%=basePath %>userController.do?singleObj&id="+row.id; 
		parent.$('#winForm').window({  
			width:900,  
			height:575, 
			title:'编辑用户',
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
	var url = "<%=basePath %>userController.do?deleteObj_";	
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
							dataGrid.datagrid('reload');
						}						
					}, 'json');
				}
			})
	}else{
		$.messager.alert('提示', "请选择一条数据！");
	}	
}
</script>
</body>
</html>