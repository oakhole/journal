<%@page import="com.oakhole.common.util.constants.GlobalConstants"%>
<%@page import="com.oakhole.systemManage.model.AppUser"%>
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
				编辑个人信息
			</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab"
				id="form">
				<form method="post" action="manageredit.aspx" name="clientForm"
					id="clientForm" onsubmit="return checksub();">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<input id="userid" value="10" name="userid" type="hidden">
						<tbody>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">账号 ：</td>
								<td align="left" width="*" height="25"><input class=""
									id="account" style="width:125px" value="${appUser.account}" name="account"
									maxlength="30" minlength="4" nullable="false" errmsg="账号"
									valuetype="USER" type="text"><font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td class="left" align="right" width="30%" height="25">密码 ：</td>
								<td align="left" width="*" height="25"><input class=""
									id="password" style="width:125px" value="${appUser.password}"
									name="password" maxlength="30" minlength="6" nullable="false"
									errmsg="密码" valuetype="PASS" type="text"><font
									color="red">*</font> <br>为了系统安全，密码长度必须八位以上，至少要包含字母和数字，尽量包含大小写字母和数字组合
								</td>
							</tr>

							<input id="usertype" value="0" name="usertype" type="hidden">


							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">联系人
									：</td>
								<td align="left" width="*" height="25"><input class=""
									id="realname" style="width:125px" value="${appUser.linkman}" name="realname"
									maxlength="30" minlength="2" nullable="false" errmsg="联系人"
									valuetype="STRING" type="text"><font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td class="left" align="right" width="30%" height="25">
									联系电话 ：</td>
								<td align="left" width="*" height="25"><input
									id="telephone" style="width:125px" name="telephone"
									maxlength="30" minlength="2" nullable="true" errmsg="联系电话"
									valuetype="TELEPHONE" type="text" value="${appUser.phoneNumber}"></td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">
									联系手机 ：</td>
								<td align="left" width="*" height="25"><input id="mobile"
									style="width:125px" name="mobile" maxlength="11" minlength="11"
									nullable="true" errmsg="联系手机" valuetype="MOBILE" type="text" value="${appUser.mobile}">
								</td>
							</tr>
							<tr>
								<td class="left" align="right" width="30%" height="25">
									腾讯QQ ：</td>
								<td align="left" width="*" height="25"><input id="qq"
									style="width:125px" name="qq" maxlength="15" minlength="5"
									nullable="true" errmsg="腾讯QQ" valuetype="INTEGER" type="text" value="${appUser.qq}">
								</td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">
									电子邮件 ：</td>
								<td align="left" width="*" height="25"><input id="email"
									style="width:125px" name="email" maxlength="50" minlength="5"
									nullable="true" errmsg="电子邮件" valuetype="EMAIL" type="text"
									value="${appUser.email}">
								</td>
							</tr>

							<tr>
								<td class="left" align="right" width="30%" height="25"></td>
								<td height="25"><div align="left">
										<input name="action" value="edit" type="hidden"> <input
											name="btnAdd" id="btnAdd" class="button" value=" 提 交 "
											type="submit"> <input name="btnedit" id="btnedit"
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
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
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
			var test_user = /^[a-zA-Z0-9]{1}([a-zA-Z0-9_]){3,19}$/;
			var test_pass = /^(\w){6,20}$/;
			var account = document.getElementById("account").value;
			if (account == "") {
				alert("账号不能为空！");
				return false;
			}
			/*
			if (!test_user.test(account)) {
			    alert("账号只能由5-30个字母、数字组成!");
			    return false;
			}
			 */

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

			var realname = document.getElementById("realname").value;
			if (realname == "") {
				alert("联系人不能为空！");
				return false;
			}

			return true;

		}
	</script>


</body>
</html>