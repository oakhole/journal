<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>User Login</title>
		<meata charset="utf-8"/>
		<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="${ctx}/assets/css/main.css"/>
		<link rel="stylesheet" href="${ctx}/assets/css/login.css">
		<!--[if IE]>
		    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
	</head>
	<body>
		<header class="wd">
			<div class="logo left">
				<img src="${ctx}/assets/img/logo.png" class="img-responsive">
			</div>
		</header>
		<div class="wd">
			<form role="form" id="login_form" class="form-horziontal login-form" method="post" action="/login">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-user">
						</div>
						<input id="username" name="username" type="text" class="form-control required " placeholder="Username"/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<input id="password" name="password" type="password" class="form-control" placeholder="Password" required/>
					</div>
				</div>
				<div class="form-group">
					<button class="btn btn-primary btn-block">Login</button>
				</div>
			</form>
		</div>
		<div class="clearfix"></div>
		<footer class="text-center">
			<p><a href="#">用户手册</a> | <a href="#">帮助文档</a> | <a href="#">客服中心</a> | <a href="#">意见反馈</a></p>
			<p class="text-muted">&copy; 2012 - 2014 Oakhole Inc. All Rights Reserved</p>
		</footer>

		<!--Scripts Reference -->
		<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
		<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.js"></script>
		<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.js"></script>
		<script src="${ctx}/assets/scripts/messages_cn.js"></script>
		<script src="${ctx}/assets/scripts/login.js"></script>
	</body>
</html>

