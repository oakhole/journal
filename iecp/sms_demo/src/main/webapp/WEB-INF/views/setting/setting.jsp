<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

				<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
				<!-- Internet Explorer .png-fix -->
				<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
</head>
<body>
	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">系统参数设置</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab"
				id="form">
				<form method="post" action="system-manage!updateSystemSetting"
					onsubmit="return tip();" name="Form" id="Form1">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<input type="hidden" value="${setting.id}" name="settingId" />
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25"><b>提交号码数量下限(用于防止钓鱼和非法信息滥用)：</b>
								</td>
								<td align="left" width="*" height="25">&nbsp; <input
									id="sendLimitDown" style="width:125px" name="sendLimitDown"
									value="${setting.sendLimitDown}" maxlength="10" minlength="01"
									nullable="false" errmsg="" valuetype="INTEGER" type="text">
								</td>
							</tr>

							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25"><b>提交号码数量上限(仅限于接口提交的控制)：</b>
								</td>
								<td align="left" width="*" height="25">&nbsp; <input
									id="sendLimitUp" style="width:125px" name="sendLimitUp"
									value="${setting.sendLimitUp}" maxlength="10" minlength="10"
									nullable="false" errmsg="" valuetype="INTEGER" type="text">
								</td>
							</tr>

							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25"><b>多少条以上开始扣量：</b>
								</td>
								<td align="left" width="*" height="25">&nbsp; <input
									id="cutCondition" style="width:125px" name="cutCondition"
									value="${setting.cutCondition}" maxlength="10" minlength="10"
									nullable="false" errmsg="" valuetype="INTEGER" type="text">
								</td>
							</tr>
							
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25"><b>全局扣量比例：</b>
								</td>
								<td align="left" width="*" height="25">&nbsp; <input
									id="cutPercent" style="width:125px" name="cutPercent"
									value="${setting.cutPercent}" maxlength="10" minlength="10"
									nullable="false" errmsg="" valuetype="INTEGER" type="text">
								</td>
							</tr>
							
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25"><b>单次任务低于多少条则收集为白名单：</b>
								</td>
								<td align="left" width="*" height="25">&nbsp; <input
									id="maxWhiteNum" style="width:125px" name="maxWhiteNum"
									value="${setting.maxWhiteNum}" maxlength="10" minlength="1"
									nullable="false" errmsg="单次任务低于多少条则收集为白名单" valuetype="INTEGER"
									type="text">(必须为正整数，且小于100) 
								</td>
							</tr>

							<tr>
								<td class="left" align="right" width="30%" height="25"><b>当天号码多次发送是否收集为白名单：</b>
								</td>
								<td align="left" width="*" height="25"><input value="0"
									<c:if test="${setting.isCollectWhiteList == 0}">checked="checked"</c:if>
									name="isCollectWhiteList" type="radio">不收集 &nbsp; <input
									value="1"
									<c:if test="${setting.isCollectWhiteList == 1}">checked="checked"</c:if>
									name="isCollectWhiteList" type="radio">收集 (每天晚上11点自动处理)



								
								</td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25"><b>每天发送多少次的号码才收集为白名单：</b>
								</td>
								<td align="left" width="*" height="25">&nbsp; <input
									id="collectionWhiteListCondition" style="width:125px"
									name="collectWhiteListCondition"
									value="${setting.collectWhiteListCondition}" maxlength="10"
									minlength="1" nullable="false" errmsg="每天发送多少次的号码才收集为白名单"
									valuetype="INTEGER" type="text">(不得小于2) 
								</td>
							</tr>

							<tr>
								<td></td>
								<td><font color="red">*</font>过多的白名单号码会导致分包变慢，请定期清理无效的白名单</td>
							</tr>
							<tr class="alt-row">
								<td class="left"></td>
								<td>&nbsp;<span><input class="button" value=" 设置 "
										name="submit" type="submit">
								</span></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>

	<c:if test="${updateMsg != null}">
		<script>
			alert("${updateMsg}");
		</script>
	</c:if>

	<script>
		function tip() {

			var r = confirm("你确认现在要切换系统模式么？")
			if (r == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>

</body>
</html>