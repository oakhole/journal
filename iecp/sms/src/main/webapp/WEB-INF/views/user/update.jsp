<%@page import="com.oakhole.common.util.constants.GlobalConstants"%>
<%@page import="com.oakhole.systemManage.model.AppUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String flag = request.getParameter("flag");
	String saveEmpErr = (String) request.getAttribute("saveEmpErr");
	String isCut = (String) request.getAttribute("isCut");
	if (saveEmpErr != null && !"".equals(saveEmpErr)) {

		if ("success".equals(saveEmpErr)) {

			saveEmpErr = "更新成功";

			out.println("<script>alert('"
					+ saveEmpErr
					+ "');parent.locationframehref('"
					+ basePath
					+ "/system-manage/employee-manage!findAllEmployee');</script>");
		} else {
			out.println("<script>alert('" + saveEmpErr + "');</script>");
		}
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
			<h3 style="cursor: s-resize;">
				更新客户&nbsp;&nbsp;[<a href="employee-manage!findAllEmployee">客户管理</a>]
			</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab"
				id="form">
				<form method="post" action="employee-manage!saveEmployee"
					name="clientForm" id="clientForm" onsubmit="return checksub();">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<input type="hidden" name="flag" value="1" />
							<input type="hidden" name="employeeId" value="${appUser.id}" />
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">账号 ：</td>
								<td align="left" width="*" height="25"><input class=""
									id="account" style="width:125px" name="account" maxlength="30"
									minlength="6" nullable="false" errmsg="账号" type="text"
									value="${appUser.account}" readonly="readonly"><!-- <font
									color="red">*用户名不能更改</font> --></td>
							</tr>
							<tr>
								<td class="left" align="right" width="30%" height="25">密码 ：</td>
								<td align="left" width="*" height="25"><input class=""
									id="password" style="width:125px" name="password"
									maxlength="30" minlength="2" nullable="false" errmsg="密码"
									type="text" value="${appUser.password}"><font
									color="red">*</font> <!-- <br>为了系统安全，密码长度必须六位以上，至少要包含字母和数字，尽量包含大小写字母和数字组合 -->
								</td>
							</tr>
							<c:if test="${sessionScope.GLOBALAPPUSER.userType==1}">
								<tr class="alt-row" style="display:none;">
							</c:if>
							<c:if test="${sessionScope.GLOBALAPPUSER.userType==0}">
								<tr class="alt-row">
							</c:if>
							<td class="left" align="right" width="30%" height="25">账户类型
								：</td>
							<td align="left" width="*" height="25"><select
								name="userType" id="usertype">
									<option
										<c:if test="${appUser.userType==0}">selected="selected"</c:if>
										value="0">管理员</option>
									<option
										<c:if test="${appUser.userType==1}">selected="selected"</c:if>
										value="1">代理商</option>
									<option
										<c:if test="${appUser.userType==2}">selected="selected"</c:if>
										value="2">网络用户</option>
									<option
										<c:if test="${appUser.userType==3}">selected="selected"</c:if>
										value="3">终端用户</option>
							</select>
							</td>
							</tr>
							<tr>
								<td class="left" align="right" width="30%" height="25">联系人
									：</td>
								<td align="left" width="*" height="25"><input class=""
									id="realname" style="width:125px" name="linkman" maxlength="30"
									minlength="2" nullable="false" errmsg="联系人" valuetype="STRING"
									type="text" value="${appUser.linkman}"><!-- <font
									color="red">*联系人不可更改</font> -->
								</td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">
									联系电话 ：</td>
								<td align="left" width="*" height="25"><input
									id="telephone" style="width:125px" name="phoneNumber"
									maxlength="30" minlength="2" nullable="true" errmsg="联系电话"
									valuetype="TELEPHONE" type="text"
									value="${appUser.phoneNumber}"></td>
							</tr>
							<tr>
								<td class="left" align="right" width="30%" height="25">
									联系手机 ：</td>
								<td align="left" width="*" height="25"><input id="mobile"
									style="width:125px" name="mobile" maxlength="11" minlength="11"
									nullable="true" errmsg="联系手机" valuetype="MOBILE" type="text"
									value="${appUser.mobile}"></td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">
									腾讯QQ ：</td>
								<td align="left" width="*" height="25"><input id="qq"
									style="width:125px" name="qq" maxlength="15" minlength="5"
									nullable="true" errmsg="腾讯QQ" valuetype="INTEGER" type="text"
									value="${appUser.qq}"></td>
							</tr>
							<tr>
								<td class="left" align="right" width="30%" height="25">
									电子邮件 ：</td>
								<td align="left" width="*" height="25"><input id="email"
									style="width:125px" name="email" maxlength="50" minlength="5"
									nullable="true" errmsg="电子邮件" valuetype="EMAIL" type="text"
									value="${appUser.email}"></td>
							</tr>
							<tr>
								<td class="left" align="right" width="30%" height="25">
									计费方式：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									name="chargeType" value="0" type="radio"
									<c:if test="${appUser.chargeType==0}"> checked="checked"</c:if>>提交计费
									<input type="radio" name="chargeType" value="1"
									<c:if test="${appUser.chargeType==1}"> checked="checked"</c:if>>成功计费
								</td>
							</tr>
							<c:if test="${sessionScope.GLOBALAPPUSER.userType == 0}">
								<tr>
									<td class="left" align="right" width="30%" height="25">是否审核
										：</td>
									<td colspan="2" align="left" width="*" height="25"><input
										name="isAudit" value="1" type="radio"
										<c:if test="${appUser.isAudit==1}"> checked="checked"</c:if>>审核
										<input type="radio" name="isAudit" value="0"
										<c:if test="${appUser.isAudit==0}"> checked="checked"</c:if>>不审核</td>
								</tr>
								<tr id="showAudit">
									<td class="left" align="right" width="30%" height="25">
										审核条件：</td>
									<td align="left" width="*" height="25"><input
										id="auditCondition" style="width:125px" name="auditCondition"
										maxlength="50" minlength="5" nullable="true" errmsg="审核条件"
										valuetype="INTEGER" type="text"
										value="${appUser.auditCondition}"></td>
								</tr>
							</c:if>
							<tr>
								<td class="left" align="right" width="30%" height="25">
									是否支持状态回执 ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									name="isStatus" value="1" type="radio"
									<c:if test="${appUser.isStatus==1}"> checked="checked"</c:if>>支持
									<input type="radio" name="isStatus" value="0"
									<c:if test="${appUser.isStatus==0}"> checked="checked"</c:if>>不支持

								</td>
							</tr>
							<c:if test="${sessionScope.GLOBALAPPUSER.userType==0}">
								<tr>
									<td class="left" align="right" width="30%" height="25">
										是否扣量 ：</td>
									<td colspan="2" align="left" width="*" height="25"><input
										name="isCut" value="1" type="radio"
										<c:if test="${appUser.isCut==1}"> checked="checked"</c:if>>扣量
										<input type="radio" name="isCut" value="0"
										<c:if test="${appUser.isCut==0}"> checked="checked"</c:if>>不扣量</td>
								</tr>

								<tr id="cutCondition">
									<td class="left" align="right" width="30%" height="25">
										扣量起始条件：</td>
									<td align="left" width="*" height="25"><input
										id="cutCondition" style="width:125px" name="cutCondition"
										maxlength="50" minlength="5" nullable="true" errmsg="扣量起始条件"
										valuetype="INTEGER" type="text"
										value="${appUser.cutCondition}"></td>
								</tr>

								<tr id="showCut2">
									<td class="left" align="right" width="30%" height="25">
										扣量比例：</td>
									<td align="left" width="*" height="25"><select
										name="cutPercent">
											<c:forEach var="i" begin="0" end="${100}">
												<option value="${i}"
													<c:if test="${appUser.cutPercent eq i}">selected="selected"</c:if>>${i}%</option>
											</c:forEach>
									</select>
									</td>
								</tr>
							</c:if>
							<c:if test="${sessionScope.GLOBALAPPUSER.userType == 0}">

								<!-- 暂时不需要 -->

								<%-- <tr id="cutCondition1">
									<td class="left" align="right" width="30%" height="25">
										系统扣量起始条件：</td>
									<td align="left" width="*" height="25"><input
										id="cutCondition" style="width:125px" name="sysCutCondition"
										maxlength="50" minlength="5" nullable="true" errmsg="扣量起始条件"
										valuetype="INTEGER" type="text"
										value="${appUser.sysCutCondition}"></td>
								</tr>

								<tr id="showCut1">
									<td class="left" align="right" width="30%" height="25">
										系统扣量比例：</td>
									<td align="left" width="*" height="25"><select
										name="sysCutPercent">
											<c:forEach var="i" begin="0" end="${100}">
												<option value="${i}"
													<c:if test="${appUser.sysCutPercent eq i}">selected="selected"</c:if>>${i}%</option>
											</c:forEach>
									</select></td>
								</tr> --%>

								<tr>
									<td class="left" align="right" width="30%" height="25">
										短信通道：</td>
									<td align="left" width="*" height="25"><select
										name="channelGroupId">
											<c:forEach var="channelGroup" items="${allChannelGroup}"
												varStatus="status">
												<option value="${channelGroup.id}"
													<c:if test="${appUser.channelGroupId==channelGroup.id}"> selected="selected"</c:if>>${channelGroup.name}</option>
											</c:forEach>
									</select>
									</td>
								</tr>
							</c:if>
							<c:if test="${sessionScope.GLOBALAPPUSER.userType==1}">
								<input type="hidden" name="channelGroupId"
									value="${sessionScope.GLOBALAPPUSER.channelGroupId}" />
								<input type="hidden" name="cutPercent"
									value="${appUser.cutPercent}" />
								<input type="hidden" name="isCut" value="${appUser.isCut}" />
								<input type="hidden" name="isAudit" value="${appUser.isAudit}" />
								<input type="hidden" name="auditCondition"
									value="${appUser.auditCondition}" />
							</c:if>
							<tr>
								<td class="left" align="right" width="30%" height="25">
									充值条数：</td>
								<td align="left" width="*" height="25"><input id="balance"
									style="width:125px" name="balance" maxlength="50" minlength="5"
									nullable="true" errmsg="充值条数" valuetype="INTEGER" type="text"
									value="${appUser.balance}" readonly="readonly"></td>
							</tr>
							<tr id="showAudit">
								<td class="left" align="right" width="30%" height="25">
									产品单价：</td>
								<td align="left" width="*" height="25"><input id="price"
									style="width:125px" name="price" maxlength="50" minlength="5"
									nullable="false" errmsg="产品单价" valuetype="FLOAT" type="text"
									value="${appUser.price}">分,&nbsp;此操作仅为只读，便于后期进行统筹</td>
								<font color="red">*</font>
							</tr>
							<tr class="alt-row">
								<td align="right" width="30%" height="25"></td>
								<td height="25"><div align="left">
										<input name="action" value="add" type="hidden"> <input
											name="btnAdd" id="btnAdd" class="button" value="提 交 "
											type="submit"> <input name="reset" id="Submit1"
											class="button" value="重 置 " type="reset"> <input
											name="back" id="back" onclick="history.back();"
											class="button" value=" 返 回 " type="button">
									</div>
								</td>
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

		function showAudit(flag) {
			if (flag == 1) {
				document.getElementById("showAudit").style.display = "";
				//document.getElementById("auditnum").value = 50;
			} else {
				document.getElementById("showAudit").style.display = "none";
				//document.getElementById("auditnum").value = 50;
			}
		}

		function showCut(flag) {
			if (flag == 1) {
				document.getElementById("showCut").style.display = "";
				//document.getElementById("auditnum").value = 50;
			} else {
				document.getElementById("showCut").style.display = "none";
				//document.getElementById("auditnum").value = 50;
			}
		}

		function checksub() {
			//var test_user = /^[a-zA-Z0-9]{1}([a-zA-Z0-9_]){3,19}$/;
			var test_pass = /^(\w){4,20}$/;
			var account = document.getElementById("account").value;
			if (account == "") {
				alert("账号不能为空！");
				return false;
			}
			//if (!test_user.test(account)) {
			//    alert("账号只能由5-30个字母、数字组成!");
			//    return false;
			//}

			var password = document.getElementById("password").value;
			if (password == "") {
				alert("密码不能为空！");
				return false;
			} else if (password.length < 2) {
				alert("密码长度必须2位以上！");
				return false;
			}/*  else if (isNumAndStr(password) == false) {
				alert("密码必须包含数字和字母！");
				return false;
			} */

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