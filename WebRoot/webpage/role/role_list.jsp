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
		url:'<%=basePath %>roleController.do?getRoleInfo', 		                                    
		border : false,	
		method: 'post',
		singleSelect:true, //单选	
		loadMsg:'数据加载中......',
		fit: true,
		nowrap: true, 
		sortName: 'id', //排序的列  
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
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [10,20,30,40,50],//可以设置每页记录条数的列表   
        onBeforeRefresh:function(){ 
            $(this).pagination('loading'); 
            //alert('before refresh'); 
            $(this).pagination('loaded'); 
        } 
    }); 
});


</script>
</head>
<body id="layout" class="easyui-layout">
<div data-options="region:'east',split:true,border:false" title="菜单权限" style="width:200px;">
 <div id="trb" style="text-align:center;height:auto; background-color:rgb(245, 245, 245); padding:5px 0; display: none; ">
    <div> <a href="#" class="easyui-linkbutton" onClick="tijiao()" >保存</a> <a href="#" class="easyui-linkbutton" >重置</a></div>
  </div>
  <ul id="function_tree" class="easyui-tree" style="padding-left:10px;" data-options="animate:true,lines:true">
  </ul>
</div>
<div data-options="region:'center',border:false">
<table id="tt" style="" >
  <thead>
    <tr>
      <th data-options="field:'ck',checkbox:true"></th>
      <th data-options="field:'rolename',width:150">角色名</th>
      <th data-options="field:'remark',width:300">备注</th>
      <th data-options="field:'id',formatter:format">权限操作</th>
    </tr>
  </thead>
</table>
</div>

<div id="tb" style="padding:5px;height:auto">
  <div style="margin-bottom:5px"> 
  <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="add()">添加</a> 
  <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="edit()">编辑</a> 
  <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a> 
  <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onClick="reload()">刷新</a> 
  <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="deleteobj()">移除</a> 
  </div>
</div>

<div id="win" iconCls="icon-add" title=" 添加用户" style="overflow:hidden;">  
 <iframe scrolling="yes" frameborder="0"  src="" style="width:100%;height:100%;"></iframe>   
</div>  
<input type="hidden" id="selectValue" name="selectValue" value="" />
<script type="text/javascript">
document.getElementById("pframe").src="";
function reload(){
	dataGrid.datagrid('reload');
}
//add
function add(){	
	document.getElementById("pframe").src="role_add.jsp"; 
	$('#winForm').window({  
		width:500,  
		height:375, 
		title:'添加角色',
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
		document.getElementById("pframe").src="<%=basePath %>roleController.do?singleObj&id="+row.id; 
		$('#winForm').window({  
			width:500,  
			height:375, 
			title:'编辑角色',
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
	var url = "<%=basePath %>roleController.do?deleteObj_";	
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

//format
function format(val, row){
	return '[<span style=""><a href="javascript:getAuth('+val+')">权限选择</a></span>]';
}

function getAuth(id){
	var row = dataGrid.datagrid('getSelected');
	$("#selectValue").val(row.id);
	$('#trb').show();
	$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	$('#function_tree').tree({
		url : "<%=basePath %>roleController.do?getRoleAuthority&id="+id,	
		checkbox : true,		
		onLoadSuccess : function(node, data) {
			$.messager.progress('close');
		}
	});
}
function tijiao(){
	var selsctid = $("#selectValue").val();
	var node = $('#function_tree').tree('getChecked');  
    var nodes = "";  
    for(var i=0;i<node.length;i++){  
        // 树节点ID  
        nodes += node[i].id + ",";  
    }  
	if(nodes == ""){
		$.messager.alert('提示', "没有选择，不用提交！", 'info');
	}else{
		var url = "<%=basePath %>roleController.do?setRoleAuthority";	
		$.messager.confirm('提示', '您确定要保存当前资源？', function(b) {
				if (b) {
					$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post( url, {id: nodes, roleid: selsctid}, function(data) {
						if (data.success) {
							$.messager.progress('close');
							$.messager.alert('提示', data.msg, 'info');
						}						
					}, 'json');
				}
			})  
	}	
}
</script>
<div id="winForm" style="overflow:hidden;">  
<iframe scrolling="yes" id="pframe" frameborder="0" src="" style="width:100%;height:100%;"></iframe>   
</div>
</body>
</html>