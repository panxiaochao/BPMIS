<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/htmlcss/css/reset.css" media="screen" />
<!--  Fluid Grid System -->
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/htmlcss/css/fluid.css" media="screen" />
<!--  Main Stylesheet -->
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/htmlcss/css/dandelion.css" media="screen" />
<!--  Main Stylesheet -->
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/htmlcss/css/dandelion.css" media="screen" />
<!-- jQuery JavaScript File -->
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.2.min.js"></script>
<!-- Plugin Files -->
<!-- Chosen Plugin -->
<script type="text/javascript" src="<%=basePath %>plugin/htmlcss/plugins/chosen/chosen.jquery.js"></script>
<link rel="stylesheet" href="<%=basePath %>plugin/htmlcss/plugins/chosen/chosen.css" media="screen" />

<!-- Demo JavaScript Files -->
<script type="text/javascript" src="<%=basePath %>plugin/htmlcss/js/demo/demo.form.js"></script>
<!-- nice-validator -->
<script type="text/javascript" src="<%=basePath %>plugin/nice-validator/jquery.validator.js"></script>
<script type="text/javascript" src="<%=basePath %>plugin/nice-validator/local/zh_CN.js"></script>
<link rel="stylesheet" href="<%=basePath %>plugin/nice-validator/jquery.validator.css" />

<!--easyui-->
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/easyui/themes/icon.css" />
<script type="text/javascript" src="plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>plugin/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/plugin/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
$(document).ready(function() {	
	$('#cc').combotree({  
		url:'<%=basePath %>functionController.do?getTree',
		onBeforeLoad : function(node, param) {
			$.messager.progress({
				title : '提示',
				text : 'Loading....'
			});	
		},
		onLoadSuccess : function(node, data) {
			$.messager.progress('close');
		}
		 
	});		
	//验证初始化
	$('#validateForm').validator({
		theme:"yellow_right",
		fields: {
			"nodename": {
				rule: "菜单名称:required;",
				//target:".error"
			},					
			"nodeurl": {
				rule: "资源地址:required;"
			}
						
		},
		//验证成功
		valid: function(form) {
			//do something...
			//alert($(form).serialize())
			//login();
			save();
		},
		//验证失败
		invalid: function(form) {
			//console.info(form)

		}
	});
	
});
function save(){
	var t = $('#cc').combotree('tree'); // 得到树对象 
	var n = t.tree('getSelected'); // 得到选择的节点 
	document.getElementById("parentid").value=n.id;
	//
	var forms = document.validateForm;
	forms.action = "<%=basePath %>functionController.do?saveadd";
	forms.submit();
	//操作父页面tab重新刷一次
	window.parent.$('#winForm').window('close');
	window.parent.$.messager.alert('信息', "添加成功！");
}

</script>
<style type="text/css">

</style>
</head>

<body>
<div class="grid_4">
  <div class="da-panel">
    <div class="da-panel-content">
    <div id="da-ex-val1-error" class="da-message info" style=" display:none">至少选择一个角色，默认是管理员</div>
      <form class="da-form" id="validateForm" name="validateForm" action="" method="post">
        <div class="da-form-inline">
          <div class="da-form-row">
            <label>菜单名称：<span class="required">*</span></label>
            <div class="da-form-col-3-8">
              <input type="text" class="padding" id="nodename" name="nodename" style="width:60%"/>
              <span></span></div>
            <label>上级菜单：<span class="required">*</span></label>
            <div class="da-form-col-3-8">
            <input type="hidden" name="parentid" id="parentid" class="padding"/>
            <input id="cc" style="width:200px; height:26px; background:#666;" />             
            </div>
          </div>
          
          <div class="da-form-row">
            <label>资源URL：<span class="required">*</span></label>
            <div class="da-form-col-3-8">
              <input class="padding" name="nodeurl" id="nodeurl" type="text" style="width:60%;"/>
            </div>
            <label>图标：<span class="required">*</span></label>
            <div class="da-form-col-3-8">
              <input class="padding" type="text" disabled="disabled" style="width:60%;"/>
            </div>
          </div>
          
         
          <div class="da-form-row">
            <label>是否是启用：<span class="required">*</span></label>
            <div class="da-form-col-3-8">
              <ul class="da-form-list inline">
                <li>
                  <input type="radio" value="1" name="nodeview" checked="checked" />
                  <label>是</label>
                </li>
                <li>
                  <input type="radio" value="0" name="nodeview" />
                  <label>否</label>
                </li>
              </ul>
            </div>
            <label>权值：</label>
            <div class="da-form-col-3-8">
              <input class="padding" type="text"  value="10" style="width:60%;" />
            </div>
          </div>
          <div class="da-form-row">
            <label>备注：</label>
            <div class="da-form-col-3-8">
              <textarea class="padding" rows="auto" cols="auto" name="remark"></textarea>
            </div>
          </div>
          <div class="da-button-row">
            <input type="submit" value="保存" class="da-button blue" onclick="cl()" />
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
