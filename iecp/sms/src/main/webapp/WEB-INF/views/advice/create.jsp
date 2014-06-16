<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统首页</title>
<link rel="stylesheet" href="<%=basePath%>/css/mmsreset.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/style.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/invalid.css"
	type="text/css" media="screen">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/simpla.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/formValidator_alert.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/jquery_dialog.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery_dialog.js"></script>
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->

</head>
<body>
	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">
				添加公告&nbsp;&nbsp;[<a href="advice-manage!findAllAdvice">公告管理</a>]
			</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab"
				id="form">
				<form method="post" action="advice-manage!saveAdvice"
					name="clientForm" id="clientForm">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr class="alt-row">
								<input type="hidden" name="flag" value="0" />
								<td class="left" height="25" width="15%" align="right">标题 ：</td>
								<td height="25" width="*" align="left"><input class=""
									id="title" style="width:225px" name="title" maxlength="40"
									minlength="0" nullable="false" errmsg="标题" valuetype="STRING"
									type="text"><font color="red">*</font>
								</td>
							</tr>
							<tr class="alt-row">
								<td class="left" height="25" width="15%" align="right">内容 ：</td>
								<td height="25" width="*" align="left"><textarea
										name="content" class="textarea"
										style="width:600px;height:200px;margin-top:3px;"></textarea><font
									color="red">*</font>
								</td>
							</tr>

							<tr>
								<td height="25" width="15%" align="right"></td>
								<td height="25"><div align="left">
										<input name="action" value="add" type="hidden"> <input
											name="btnAdd" id="btnAdd" class="button" value=" 提 交 "
											type="submit"> <input name="reset" id="Submit1"
												class="button" value=" 重 置 " type="reset"> <input
													name="back" id="back" onclick="history.back();"
													class="button" value=" 返 回 " type="button">
									</div></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<br> <br> <br> <br> <br> <br> <br> <br>
									<script>
										window.onload = function() {
											FormValidator.attach("form",
													"clientForm");
										};
										function selected(title) {
											JqueryDialog.Open2(title,
													'selectuser.aspx', 300,
													290, true, true, true);
										}
										function selectUser(users) {
											document.getElementById("userid").value = users;
										}
									</script>
</body>
</html>