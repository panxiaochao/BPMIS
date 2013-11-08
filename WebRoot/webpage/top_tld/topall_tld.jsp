<%@ page pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath %>js/jquery-1.8.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>plugin/nice-validator/jquery.validator.js"></script>
<script type="text/javascript" src="<%=basePath %>plugin/nice-validator/local/zh_CN.js"></script>
<link rel="stylesheet" href="<%=basePath %>plugin/nice-validator/jquery.validator.css">
<link href="<%=basePath%>plugin/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
<!-- Le styles -->
<link href="<%=basePath %>plugin/bootstrap/assets/css/bootstrap.css" rel="stylesheet" />
<link href="<%=basePath %>plugin/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet" />
<!-- jbox-->
<link href="<%=basePath %>plugin/jBox/Skins2/Blue/jbox.css" rel="stylesheet" />
<script src="<%=basePath %>plugin/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="<%=basePath %>plugin/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
<script src="<%=basePath %>js/plugin.js" type="text/javascript"></script>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
     <script src="<%=basePath %>plugin/bootstrap/assets/js/html5shiv.js"></script>
<![endif]-->