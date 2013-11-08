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
          <h1>404   <small>Page Not Found ( 页面不存在 )</small></h1>
        </div>
        <p style="text-indent:2em;"><strong>We couldn't find that page. It appears that you are lost.The page you are looking for is not found. This could be for several reasons：</strong> ( 我们无法找到该网页，看来你是迷路了，看来没有找到您所寻找的网页，这可能有以下几个原因： )</p>
        
        <p>
        <ul class="nav nav-list">
          <li class="nav-header" style="margin-top:0px;"><i class="icon-check"></i> It never existed. ( 一直不存在 )</li>
          <li class="nav-header" style="margin-top:0px;"><i class="icon-check"></i> It got deleted for some reason. ( 由于某些原因删除了 )</li>
          <li class="nav-header" style="margin-top:0px;"><i class="icon-check"></i> You were looking for something that is not here. ( 你正在寻找的东西不在这里。 )</li>
          <li class="nav-header" style="margin-top:0px;"><i class="icon-check"></i> You like this page. ( 你喜欢这张页面 )</li>
        </ul>
        
        </p>
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
