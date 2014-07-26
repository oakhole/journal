<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>短信综合信息管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url();
	background-color: #39609B;
}

.STYLE1 {
	font-family: "宋体";
	font-size: 12px;
	color: #666666;
}
-->
</style>
<link href="${ctx}/static/images/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/js/jquery-1.js" type="text/javascript"></script>
<script>
	function changeCode() {
		now = new Date();
		$("#yzmimg").attr("src",
				"system-manage/login-manage!validateCode?" + now);
	}
</script>
</head>

<body leftmargin="0" topmargin="0">
    <%
        String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
    %>
	<form width="100%" height="100%" border="0" align="center"
		cellpadding="0" cellspacing="0"
		action="${ctx}/login" method="post" id="form"
		name="form">
		<tr>
			<td align="center" valign="middle"><table width="100%"
					height="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="345" background="${ctx}/static/images/1_01.jpg"><table
								width="1015" height="260" border="0" align="center"
								cellpadding="0" cellspacing="0" bgcolor="#CBDDF3">
								<tr>
									<td height="229" colspan="3" align="center" valign="middle"
										background="${ctx}/static/images/1_02.jpg">&nbsp;</td>
								</tr>
								<tr>
									<td width="592" align="center" valign="middle"
										background="${ctx}/static/images/1_031.jpg" bgcolor="#CADCF2">&nbsp;</td>
									<td width="279" align="center" valign="middle"
										background="${ctx}/static/images/2.jpg" bgcolor="#CBDDF3"><table
											width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="5%" height="95" rowspan="3">&nbsp;</td>
												<td width="21%"><span class="STYLE1">用户名：</span>
												</td>
												<td height="32" colspan="2"><label> <input
														name="username" type="text" class="form" /> </label>
												</td>
											</tr>
											<tr>
												<td width="21%" class="STYLE1">密&nbsp;&nbsp;码：</td>
												<td height="32" colspan="2"><label><input
														name="password" type="password" class="form" /> </label>
												</td>
											</tr>
										</table> <a href="#" onclick="document.form.submit();"><img
											src="${ctx}/static/images/3.jpg" width="70" height="30" border="0" /> </a>
									</td>
									<td width="149" align="center" valign="middle"><img
										src="${ctx}/static/images/1_05.jpg" width="149" height="140" />
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center" valign="middle"><img
										src="${ctx}/static/images/1_06.jpg" width="1020" height="231" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</form>
	<c:if test="${loginErrMsg!=null}">
		<script language="javascript">alert("${loginErrMsg}");</script>
</c:if>
</body>
</html>

