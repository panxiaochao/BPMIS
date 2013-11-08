<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!--easyui-->
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/easyui/themes/icon.css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>plugin/easyui/jquery.easyui.min.js"></script>
<!-- 引入EasyUI Portal插件 -->
<link rel="stylesheet" href="<%=basePath %>plugin/easyui/plugins/portal.css" type="text/css" />
<script type="text/javascript" src="<%=basePath %>plugin/easyui/plugins/jquery.portal.js"></script>
<script type="text/javascript" src="<%=basePath %>plugin/easyui/plugins/extEasyUI.js"></script>
<script type="text/javascript" src="<%=basePath %>/plugin/easyui/locale/easyui-lang-zh_CN.js"></script>