<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/webpage/top_tld/core_tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/htmlcss/css/reset.css" media="screen" />
<!--  Fluid Grid System -->
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/htmlcss/css/fluid.css" media="screen" />
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
<script type="text/javascript">
$(document).ready(function() {			
	//验证初始化
	$('#validateForm').validator({
		theme:"yellow_right",
		fields: {
			"rolename": {
				rule: "角色名:required;rolename",
				//target:".error"
			}		
		},
		//验证成功
		valid: function(form) {
			//do something...
			//alert($(form).serialize())
			save();
		},
		//验证失败
		invalid: function(form) {
			console.info(form)

		}
	});	
});
function save(){
	//alert(1);
	var forms = document.validateForm;
	forms.action = "<%=basePath %>roleController.do?roleadd";
	forms.submit();
	//操作父页面tab重新刷一次
	window.parent.$('#winForm').window('close');	
	//window.parent.$('#tt').datagrid('reload'); 
	window.parent.reload(); 
	window.parent.$.messager.alert('信息', "添加成功！");
	//window.parent.document.getElementById("pframe").src="";
}
</script>
</head>

<body>

<div class="grid_4">
  <div class="da-panel">
    <div class="da-panel-content">
    <div id="da-ex-val1-error" class="da-message info" style="display: none">至少选择一个角色</div>
      <form class="da-form" action="" method="post" name="validateForm" id="validateForm">
        <div class="da-form-inline">
          <div class="da-form-row">
            <label>角色名：<span class="required">*</span></label>           
              <input type="text" name="rolename" type="text" id="rolename" class="padding" style="width: 275px;" />                                            
          </div>
          <div class="da-form-row">
            <label>备注：</label>          
              <textarea rows="auto" cols="auto" name="remark" class="padding"></textarea>          
          </div>
          <div class="da-button-row">        
            <input type="submit" value="保存" class="da-button blue" />
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
