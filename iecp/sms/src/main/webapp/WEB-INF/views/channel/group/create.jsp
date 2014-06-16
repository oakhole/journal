<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="<%=basePath%>css/mmsreset.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="<%=basePath%>css/invalid.css"
	type="text/css" media="screen">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/simpla.js"></script>

<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/formValidator_alert.js"></script>
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
				添加通道组&nbsp;&nbsp;[<a href="channel-manage!findAllChannelGroup">通道组管理</a>]
			</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab"
				id="form">
				<form method="post" action="channel-manage!saveChannelGroup"
					name="clientForm" id="clientForm" onsubmit="return checksub();">
					<input name="flag" value="0" type="hidden" />
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr class="alt-row">
								<td class="left" align="right" height="25" width="30%">
									通道组名称 ：</td>
								<td align="left" height="25" width="*"><input class=""
									id="interfaceGroupName" style="width:125px"
									name="channelGroupName" maxlength="30" minlength="1"
									nullable="false" errmsg="通道组名称" valuetype="STRING" type="text"><font
									color="red">*</font></td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">移动通道:
									：</td>
								<td align="left" width="*" height="25"><select
									name="yidongChannelId" id="usertype">
										<option value="0">请选择通道</option>
										<c:forEach var="yidongChannel" items="${yidongChannels}"
											varStatus="status">
											<option value="${yidongChannel.id}">${yidongChannel.name}</option>
										</c:forEach>
								</select>
								</td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">联通通道:
									：</td>
								<td align="left" width="*" height="25"><select
									name="liantongChannelId" id="usertype">
										<option value="0">请选择通道</option>
										<c:forEach var="liantongChannel" items="${liantongChannels}"
											varStatus="status">
											<option value="${liantongChannel.id}">${liantongChannel.name}</option>
										</c:forEach>
								</select>
								</td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">电信通道:
									：</td>
								<td align="left" width="*" height="25"><select
									name="dianxinChannelId" id="usertype">
										<option value="0">请选择通道</option>
										<c:forEach var="dianxinChannel" items="${dianxinChannels}"
											varStatus="status">
											<option value="${dianxinChannel.id}">${dianxinChannel.name}</option>
										</c:forEach>
								</select>
								</td>
							</tr>
							<tr>
								<td class="left" align="right" height="25" width="30%">备注 ：</td>
								<td align="left" height="25" width="*"><input id="remark"
									style="width:125px" name="memo" maxlength="200" minlength="0"
									nullable="true" errmsg="备注" valuetype="STRING" type="text">
								</td>
							</tr>
							<tr class="alt-row">
								<td align="right" height="25" width="30%"></td>
								<td height="25"><div align="left">
										<input name="btnAdd" id="btnAdd" class="button" value=" 提 交 "
											type="submit"> <input name="reset" id="Submit1"
											class="button" value=" 重 置 " type="reset"> <input
											name="back" id="back" onclick="history.back();"
											class="button" value=" 返 回 " type="button">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="right" height="100" width="30%"></td>
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
	<script>
		window.onload = function() {
			FormValidator.attach("form", "clientForm");
		};
		function checksub() {
			var interfaceGroupName = document
					.getElementById("interfaceGroupName").value;
			if (interfaceGroupName == "") {
				alert("名称不能为空！");
				return false;
			}
			return true;

		}
		function showres(flag) {
			if (flag == 1) {
				document.getElementById("isshow").style.display = "";
			} else {
				document.getElementById("isshow").style.display = "none";
				document.getElementsByName("isrestore")[0].checked = false;
			}
		}
		function showaud(flag) {
			if (flag == 1) {
				document.getElementById("showaudit").style.display = "";
				document.getElementById("auditnum").value = 50;
			} else {
				document.getElementById("showaudit").style.display = "none";
				document.getElementById("auditnum").value = 50;
			}
		}
		function showaccu(flag) {
			if (flag == 1) {
				document.getElementById("showacc").style.display = "";
				document.getElementById("auditnum").value = 50;
			} else {
				document.getElementById("showacc").style.display = "none";
				document.getElementById("accumulate").value = 50;
			}
		}
	</script>
</body>
</html>