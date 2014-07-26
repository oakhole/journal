<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	String sendErr = (String) request.getAttribute("sendErr");
	if (sendErr != null && !"".equals(sendErr)) {
		String msg = sendErr.split(",")[1];
		if ("0".equals(sendErr.split(",")[0])) {
			msg = "提交成功,正在准备发送……";
		}
		out.println("<script>alert('"
				+ msg
				+ "');parent.locationframehref('"
				+ basePath
				+ "/sms-manage/sms-send-manage!sendSms?isVar=1');</script>");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统首页</title>
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
<script language="JavaScript" type="text/javascript"
	src="<%=basePath%>/DateCtrol/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>/DateCtrol/skin/WdatePicker.css">
<script language="JavaScript" type="text/javascript"
	src="<%=basePath%>/js/LoadAjaxIco.js"></script>
<script language="Javascript" type="text/javascript"
	src="<%=basePath%>/js/jquery.blockUI.js"></script>
<script language="javascript" type="text/javascript"
	src="<%=basePath%>js/sendVarSms.js"></script>

<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
<![endif]-->
</head>

<body>
	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">变量短信</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab"
				id="form">
				<form method="post" enctype="multipart/form-data"
					action="<%=basePath%>sms-manage/sms-send-manage!varSmsSave"
					name="clientForm" id="clientForm" onsubmit="return sub();">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<input type="hidden" name="isVarSms" value="1" />
							<tr class="alt-row">
								<td class="left" style="line-height:30px;" align="right"
									valign="top" width="13%">
									<div style="line-height:30px;margin-bottom:60px;">短信内容：</div>
								</td>
								<td style="line-height:40px;padding-top:6px;" align="left"
									width="400"><textarea name="content"
										style="border:1px solid #6E9FDE;width:380px;height:85px;"
										id="content" rows="5"></textarea>
									<div
										style="line-height:20px;clear:both;margin-top:5px;width:380px;">
										<span id="message">已输入<font color="red"
											id="writenCount">0</font>个字，还剩<font color="red"
											id="restCount">300</font>字，最多<font color="red"
											id="maxSmsWords">300</font>个字，计费条数:<font color="red"
											id="chargeCount">0</font>条 
									</div>
									<div
										style="line-height:20px;clear:both;margin-top:5px;width:380px;">
										移动：
										<c:if test="${yidongChannel.status==0}">
											<img src="<%=basePath%>/images/red.gif" alt="链路故障"
												title="链路故障" border="0" width="16" height="16">
										</c:if>
										<c:if test="${yidongChannel.status==1}">
											<img src="<%=basePath%>/images/yellow.gif" alt="通道繁忙"
												title="通道繁忙" border="0" width="16" height="16">
										</c:if>
										<c:if test="${yidongChannel.status==2}">
											<img src="<%=basePath%>/images/green.gif" alt="通道正常"
												title="通道正常" border="0" width="16" height="16">
										</c:if>
										<c:if test="${yidongChannel.status==3}">
											<img src="<%=basePath%>/images/black.gif" alt="停用" title="停用"
												border="0" width="16" height="16">
										</c:if>
										&nbsp;&nbsp; 联通：
										<c:if test="${liantongChannel.status==0}">
											<img src="<%=basePath%>/images/red.gif" alt="链路故障"
												title="链路故障" border="0" width="16" height="16">
										</c:if>
										<c:if test="${liantongChannel.status==1}">
											<img src="<%=basePath%>/images/yellow.gif" alt="通道繁忙"
												title="通道繁忙" border="0" width="16" height="16">
										</c:if>
										<c:if test="${liantongChannel.status==2}">
											<img src="<%=basePath%>/images/green.gif" alt="通道正常"
												title="通道正常" border="0" width="16" height="16">
										</c:if>
										<c:if test="${liantongChannel.status==3}">
											<img src="<%=basePath%>/images/black.gif" alt="停用" title="停用"
												border="0" width="16" height="16">
										</c:if>
										&nbsp;&nbsp; 电信：
										<c:if test="${dianxinChannel.status==0}">
											<img src="<%=basePath%>/images/red.gif" alt="链路故障"
												title="链路故障" border="0" width="16" height="16">
										</c:if>
										<c:if test="${dianxinChannel.status==1}">
											<img src="<%=basePath%>/images/yellow.gif" alt="通道繁忙"
												title="通道繁忙" border="0" width="16" height="16">
										</c:if>
										<c:if test="${dianxinChannel.status==2}">
											<img src="<%=basePath%>/images/green.gif" alt="通道正常"
												title="通道正常" border="0" width="16" height="16">
										</c:if>
										<c:if test="${dianxinChannel.status==3}">
											<img src="<%=basePath%>/images/black.gif" alt="停用" title="停用"
												border="0" width="16" height="16">
										</c:if>
									</div>
									<div style="width:280px;clear:both;">
										<input name="btnAdd" id="checkBadwords" class="button"
											value="检查屏蔽词 " type="button"> <input name="btnAdd"
											id="useSign" class="button" value="使用签名 " type="button">
									</div></td>
								<td rowspan="5"
									style="border-left:#E6E6E6 solid 1px;line-height:28px;color:#FF6600; padding-left:8px;padding-top:8px;"
									valign="top" width="390" id="demo"><strong>注意：</strong> <br>
									1、单次提交不超过5w个号码。 <br> 2、所有号码需写入本地的文件中然后导入。 <br>
									3、号码文件只支持TXT、EXCLE文件格式。<br> 4、请使用CTRL+V粘贴短信内容。<br>
									5、汉字、数字和英文都表示1个长度<br> 6、发手机一条为<font id="perSmsWords">70</font>个字。长短信在70个字的基础上每加63个字算加一条。<br>
									7、短信内容实际长度=短信签名+短信内容</td>
							</tr>
							<tr>
								<td style="line-height:34px;" class="left" align="right"
									width="13%" height="34">预定发送时间：</td>
								<td style="height:34px;line-height:34px;" align="left"
									height="34"><span><input id="planTime"
										style="width:135px" name="planTime"
										onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										class="Wdate" type="text"> </span> <span
									style="line-height:34px;">若为空，表示立即发送。</span>
								</td>
							</tr>
							<tr class="alt-row">
								<td style="line-height:28px;" class="left" align="right"
									width="13%" height="28">手机号码：</td>
								<td style="padding-top:8px;" valign="top"><input
									style="border:1px solid #6E9FDE;margin-bottom:5px;" type="file"
									style="width:200px" id="uploadFile" name="upload">
									&nbsp;第一列为发送号码，其他列为参数<br /> <a href="#" id="showDemo">查看示例</a>
								</td>
							</tr>
							<tr class="alt-row">
								<td align="right" width="13%" height="25"></td>
								<td align="left" height="25"><div
										style="padding-bottom:4px;padding-top:4px;" align="left">
										<input name="btnAdd" id="sendSms" class="button"
											value="发送短信  " type="submit"> <input
											style="margin-left:10px;" class="button" name="btnReset"
											id="btnReset" value="  清除信息  " type="button">
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
	<div
		style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;">
		<iframe style="width: 186px; height: 197px;"
			src="<%=basePath%>/DateCtrol/DateCtrol.htm" border="0" scrolling="no"
			frameborder="0"></iframe>
	</div>
</body>
</html>