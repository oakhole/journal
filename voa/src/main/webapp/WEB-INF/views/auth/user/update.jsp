<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户注册</title>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#loginName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					username: {
						remote: "${ctx}/register/checkUsername"
					}
				},
				messages: {
					username: {
						remote: "用户登录名已存在"
					}
				}
			});
		});
	</script>
</head>

<body>
<div class="content-box">

<div class="content-box-header">
    <h3>用户修改</h3>
</div>
<div class="clear"></div>
<div class="content-box-content">
	<form id="inputForm" action="${ctx}/register" method="post" class="form-horizontal">
	<p>
        <label for="loginName">登录名:</label>
        <input type="text" id="loginName" name="username" value="${user.username}" class="text-input small-input required" disabled="" minlength="3"/>
    </p>
    <p>
        <label for="name">用户名:</label>
        <input type="text" id="name" name="name" value="${user.name}" class="text-input small-input required"/>
    </p>
    <p>
        <label for="email">邮箱:</label>
        <input type="text" id="email" name="email" value="${user.email}" class="text-input small-input email required"/>
    </p>
    <p>
        <label for="plainPassword">密码:</label>
        <input type="password" id="plainPassword" name="plainPassword" value="" class="text-input small-input required"/>
    </p>
    <p>
        <label for="confirmPassword">确认密码:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" class="text-input small-input required" equalTo="#plainPassword"/>
    </p>
    <p>
        <input id="submit_btn" class="button" type="submit" value="提交"/>&nbsp;
        <input id="cancel_btn" class="back-button" type="button" value="返回" onclick="history.back()"/>
    </p>
	</form>
</div>
</div>
</body>
</html>
