<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<%=basePath %>plugin/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
      }
    </style>
    <link href="<%=basePath %>plugin/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
  </head>

  <body>
    <div class="container" >

      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        <div class="page-header" style="border-bottom: 1px solid #ccc;">
          <h1>500   <small>Internal Server Error ( 内部服务器错误 )</small></h1>
        </div>
        <p style="text-indent:2em;"><strong>The server encountered an internal error and was unable to complete your request.Please contact the server administrator <a href="#">panxiaochao602@qq.com</a> and informed them of the time the error occurred.
More information about this error may be available in the server error log.</strong></p>
<p style="text-indent:2em;">服务器遇到一个内部错误，无法完成您的请求。请联系服务器管理员 <a href="#">panxiaochao602@qq.com</a>， 并通知他们错误发生的时间。关于这个错误的更多信息请参阅服务器错误日志。</p>
        
     <!--
        <p class=" text-center"><a href="#" class="btn btn-primary btn-large">返回</a></p>-->
      </div>

     

    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=basePath %>plugin/bootstrap/assets/js/jquery.js"></script>
    <script src="<%=basePath %>plugin/bootstrap/assets/js/bootstrap-transition.js"></script>


</body>
</html>
