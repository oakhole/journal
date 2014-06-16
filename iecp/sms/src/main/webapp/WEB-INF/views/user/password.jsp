<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String editPwdErr = (String) request.getAttribute("editPwdErr");
	if (editPwdErr != null && !"".equals(editPwdErr)) {
		out.println("<script>alert('" + editPwdErr + "');</script>");
	}
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
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->

</head>
<body>
	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">修改密码</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab"
				id="form">
				<form method="post" action="system-manage!editPass"
					name="clientForm" id="clientForm" onsubmit="return checksub();">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">

						<tbody>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">旧密码
									：</td>
								<td align="left" width="*" height="25"><input class=""
									id="oldpassword" style="width:250px" name="oldpassword"
									maxlength="30" minlength="6" nullable="false" errmsg="旧密码"
									valuetype="PASS" type="text"><font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td class="left" align="right" width="30%" height="25">新密码
									：</td>
								<td align="left" width="*" height="25"><input class=""
									id="password" style="width:250px" name="password"
									maxlength="60" minlength="2" nullable="false" errmsg="新密码"
									valuetype="PASS" type="text"><font color="red">*</font>
								</td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">
									确认密码 ：</td>
								<td align="left" width="*" height="25"><input class=""
									id="password1" style="width:250px" name="password1"
									maxlength="60" minlength="2" nullable="false" errmsg="确认密码"
									valuetype="PASS" type="text"><font color="red">*</font>
								</td>
							</tr>

							<tr>
								<td align="right" width="30%" height="25"></td>
								<td height="25"><div align="left">
										<input name="action" value="add" type="hidden"> <input
											name="btnAdd" id="btnAdd" class="button" value=" 提 交 "
											type="submit"> <input name="reset" id="Submit1"
												class="button" value=" 重 置 " type="reset"> <br>为了系统安全，密码长度必须八位以上，至少要包含字母和数字，尽量包含大小写字母和数字组合


												
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<script>
		window.onload = function() {
			FormValidator.attach("form", "clientForm");
		};
		function isNumAndStr(str) {
			var regexpUperStr = /[A-Z]+/;
			var reexpLowerStr = /[a-z]+/;
			var regexpNum = /\d+/;
			var uperStrFlag = regexpUperStr.test(str);
			var lowerStrFlag = reexpLowerStr.test(str);
			var numFlag = regexpNum.test(str);
			if ((uperStrFlag && lowerStrFlag) || (lowerStrFlag && numFlag)
					|| (uperStrFlag && numFlag))
				return true;
			return false;
		}
		function checksub() {

			var password = document.getElementById("password").value;
			if (password == "") {
				alert("密码不能为空！");
				return false;
			} else if (password.length < 8) {
				alert("密码长度必须八位以上！");
				return false;
			} else if (isNumAndStr(password) == false) {
				alert("密码必须包含数字和字母！");
				return false;
			}
			return true;

		}
	</script>

</body>
</html>