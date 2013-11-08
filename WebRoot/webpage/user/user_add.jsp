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
			"account": {
				rule: "账号:required;account",
				//target:".error"
			},					
			"password": {
				rule: "密码:required;password;strength"
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
	var roleid ="";
	$("ul li.result-selected").each(function(){	
		roleid += $(this).attr("data-id") + ",";
	});
	if(roleid == "")
		window.parent.$.messager.alert('信息', "至少选择一个角色！");
	else{
		document.getElementById("roleid").value=roleid;
		//
		var forms = document.validateForm;
		forms.action = "<%=basePath %>userController.do?useradd";
		forms.submit();
		//操作父页面tab重新刷一次
		window.parent.$('#winForm').window('close');
		var tab = window.parent.$('#maintabs');
		var index = tab.tabs('getTabIndex', tab.tabs('getSelected'));
		tab.tabs('getTab', index).panel('refresh');
		window.parent.$.messager.alert('信息', "添加成功！");
	}		
}
</script>
</head>

<body>

<div class="grid_4">
  <div class="da-panel">
    <div class="da-panel-content">
    <div id="da-ex-val1-error" class="da-message info" style="">至少选择一个角色</div>
      <form class="da-form" action="" method="post" name="validateForm" id="validateForm">
        <div class="da-form-inline">
          <div class="da-form-row">
            <label>账号：<span class="required">*</span></label>
            <div class="da-form-col-3-8">
              <input type="text" class="padding" name="account" type="text" id="account" style="width:60%"/>
            
            </div>
            
            <label>密码：<span class="required">*</span></label>
            <div class="da-form-col-3-8">
              <input type="password" class="padding" name="password" placeholder="密码" id="password" style="width:60%"/>
            </div>
          </div>
         
          <div class="da-form-row">
            <label>用户名：<span class="required">*</span></label>
            <div class="da-form-col-3-8">
              <input type="text" class="padding" name="username"  id="username" style="width:60%"/>
            </div>
            <label>性别：<span class="required">*</span></label>
            <div class="da-form-col-3-8">
              <ul class="da-form-list inline">
                <li>
                  <input type="radio" name="sex" value="0" checked="checked" />
                  <label>男</label>
                </li>
                <li>
                  <input type="radio" name="sex" value="1" />
                  <label>女</label>
                </li>
              </ul>
            </div>
          </div>
          <div class="da-form-row">
          	<input type="hidden" name="roleid" id="roleid" value=""/>
            <label>角色：<span class="required">*</span></label>
            <div class="da-form-col-4-8">
              <select class="chzn-select" size="5" multiple="multiple">
              <c:forEach items="${requestScope.list}" var="st">	
                <option value="${st.id}">${st.rolename}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="da-form-row">
            <label>Email：</label>
            <div class="da-form-col-3-8">
              <input type="text" class="padding" name="email" style="width:60%"/>
            </div>
            <label>xxxxx：</label>
            <div class="da-form-col-3-8">
              <input type="text" class="padding" disabled="disabled" />
            </div>
          </div>
          <div class="da-form-row">
            <label>Tel：</label>
            <div class="da-form-col-3-8">
              <input type="text" class="padding" name="tel" style="width:60%"/>
            </div>
            <label>QQ：</label>
            <div class="da-form-col-3-8">
              <input type="text" class="padding" name="qq" style="width:60%"/>
            </div>
          </div>
         
          <div class="da-form-row">
            <label>备注：</label>
            <div class="da-form-col-3-8">
              <textarea rows="auto" class="padding" cols="auto" name="remark"></textarea>
            </div>
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
