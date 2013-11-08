<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/webpage/top_tld/topall_tld.jsp"%>
		<base href="<%=basePath%>" />
		<title>Sign in &middot; BPMIS</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />			
		<script src="<%=basePath%>js/jquery.custom.js" type="text/javascript">
		</script>
	</head>
	<body>
		<div id="login">
			<div id="login_header">
				<h1 class="login_logo">
					<a href="#"><img
							src="plugin/dwz/themes/default/images/logo.png" />
					</a>
				</h1>
				<div class="login_headerContent">
					<div class="navList">
						<ul>
							<li>
								<a href="#">设为首页</a>
							</li>
							<li>
								<a href="#fankui" role="button" data-toggle="modal">反馈</a>
							</li>
							<li>
								<a href="#">体验旧版</a>
							</li>
						</ul>
					</div>
					<h2 class="login_title">
						<img src="plugin/dwz/themes/default/images/login_title.png" />
					</h2>
				</div>
			</div>
			<div id="login_content">
				<div class="loginForm">			
					<form id="loginForm" action="" method="post">
			          <table style="width:250px; height:200px;"  >
			        <tr>
			              <td><label  for="">用户名：</label></td>
			              <td><input type="text" placeholder="用户名" class="input-medium " name="username" id="username" /></td>
			            </tr>
			        <tr>
			              <td><label  for="">密&nbsp;&nbsp;&nbsp;码：</label></td>
			              <td><input type="password" placeholder="密码" class="input-medium required" name="password" id="password" /></td>
			            </tr>
			        <tr>
			              <td><label  for="">验证码：</label></td>
			              <td><input type="text" placeholder="验证码" class="input-mini" name="checkcode" id="checkcode"/>
			            &nbsp;<span><img src="safecode" style="height:30px; width:80px;margin-bottom: 10px; border: 1px solid #ddd; cursor: pointer;" title="点击验证码刷新" onclick="this.src='safecode'"/></span></td>
			            </tr>
			        <tr>
			              <td colspan="2" style="text-align:center;"><button type="submit" class="btn btn-primary" id="btn-submit">登录</button>
			            &nbsp;&nbsp;&nbsp;
			            <button type="reset" class="btn">注册</button></td>
			            </tr>
			      </table>
			      </form>
				</div>
				<div class="login_banner">
					<img src="plugin/dwz/themes/default/images/login_banner2.png" />
				</div>
				<div class="login_main">
					<ul class="helpList">
						<li>
							<a href="#">忘记密码怎么办？</a>
						</li>
						<li>
							<a href="#">为什么登录失败？</a>
						</li>
					</ul>
					<div class="login_inner">
						<p>
							1、后台权限管理系统，其功能权限的管理技术，基于角色访问控制技术RBAC。
						</p>
						<p>
							2、采用双系统模块技术，其成功借鉴dwz框架核心架构。
						</p>
						<p>
							3、功能与之前Bootstrap模板核心完全兼容，但版本号有所提升。
							<a href="#">体验旧版</a>
						</p>
						<p>
							4、优化代码核心，加入拦截器功能，性能补充多方UTILS，性能提升20%。
						</p>
					</div>
				</div>
			</div>
			<div id="login_footer">
				Copyright © 2013-2014 BPMIS_CMS Powered By
				<a href="#myModal" role="button" data-toggle="modal">Panxiaochao</a>
				V1.5 All Rights Reserved.
			</div>
		</div>


		<script src="<%=basePath%>js/bootstrap.min.js"></script>
		<%@include file="/webpage/bootstrapModal/loginModal.jsp"%>
	</body>
</html>
