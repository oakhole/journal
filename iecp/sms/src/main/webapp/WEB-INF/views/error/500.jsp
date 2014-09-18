<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>

<%
    //设置返回码200，避免浏览器自带的错误页面
    response.setStatus(200);
    //记录日志
    Logger logger = LoggerFactory.getLogger("500.jsp");
    logger.error(exception.getMessage(), exception);
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>500 错误</title>
	</head>
	<body>
		<div class="main wd">
			<div class="page-header text-center text-muted">
			    <h2><span class="h1 text-danger">500</span> - 系统发生内部错误.</h2>
			</div>
		</div>
	</body>
</html>