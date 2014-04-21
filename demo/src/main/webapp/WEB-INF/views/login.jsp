<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Login</title>
    <link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${ctx}/static/css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="${ctx}/static/css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="${ctx}/static/css/invalid.css" type="text/css" media="screen"/>
    <link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />

    <script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/static/scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript" src="${ctx}/static/scripts/facebox.js"></script>
    <script type="text/javascript" src="${ctx}/static/scripts/jquery.wysiwyg.js"></script>
    <!--[if IE 6]>
        <script type="text/javascript" src="${ctx}/static/scripts/DD_belatedPNG_0.0.7a.js"></script>
        <script type="text/javascript">
            DD_belatedPNG.fix('.png_bg, img, li');
        </script>
    <![endif]-->
</head>

<body id="login">

<div id="login-wrapper" class="png_bg">
    <div id="login-top">

        <h1>Union Admin</h1>
        <!-- Logo (221px width) -->
        <img id="logo" src="${ctx}/static/images/logo.png" alt="Simpla Admin logo"/>
    </div>
    <!-- End #logn-top -->

    <div id="login-content">

        <form id="loginForm" action="${ctx}/login" method="post">

            <%
            String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
            if(error != null){
            %>
            <div class="notification error png_bg">
                <div>
                    Incorrect username or password .
                </div>
            </div>
            <%
            }
            %>
            <p>
                <label>Username</label>
                <input name="username" class="text-input input-medium required" type="text" value="${username}"/>
            </p>

            <div class="clear"></div>
            <p>
                <label>Password</label>
                <input name="password" class="text-input input-medium required" type="password"/>
            </p>

            <div class="clear"></div>
            <p id="remember-password">
                <input name="rememberMe" type="checkbox"/>Remember me
            </p>

            <div class="clear"></div>
            <p>
                <input class="button" type="submit" value="Sign In"/>
            </p>

        </form>
    </div>
    <!-- End #login-content -->

</div>
<!-- End #login-wrapper -->

<!-- script -->
<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
</script>

</body>

</html>
