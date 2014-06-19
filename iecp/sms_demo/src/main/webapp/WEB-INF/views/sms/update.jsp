<%@page import="com.oakhole.common.util.constants.GlobalConstants"%>
<%@page import="com.oakhole.systemManage.model.AppUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<form method="post" action="sms-send-manage!updateSmsSend"
					name="clientForm" id="clientForm">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<input type="hidden" name="sendId" value="${smsSend.id}" />
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">发送条数
									：</td>
								<td align="left" width="*" height="25"><input class=""
									id="sendPhoneCount" style="width:125px" name="sendPhoneCount"
									maxlength="30" nullable="false" errmsg="发送条数" type="text"
									value="${smsSend.sendPhoneCount}" valueType="INTEGER">
								</td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="30%" height="25">
									发送时间 ：</td>
								<td align="left" width="*" height="25"><input id="sendTime"
									style="width:125px" name="sendTime" maxlength="30"
									minlength="2" nullable="false" errmsg="发送时间" type="text"
									value="<fmt:formatDate value="${smsSend.sendTime}"
											pattern="yyyy-MM-dd HH:mm:ss" />">*时间格式：
									yyyy-MM-dd HH:mm:ss</td>
							</tr>
							<tr>
								<td class="left" style="line-height:30px;" align="right"
									valign="top" width="13%">
									<div style="line-height:30px;margin-bottom:60px;">短信内容：</div></td>
								<td style="line-height:40px;padding-top:6px;" align="left"
									width="400"><textarea name="msgContent"
										style="border:1px solid #6E9FDE;width:380px;height:85px;"
										id="content" rows="5">${smsSend.content}</textarea>
								</td>
							</tr>
							<tr class="alt-row">
								<td align="right" width="30%" height="25"></td>
								<td height="25"><div align="left">
										<input name="btnAdd" id="btnAdd" class="button" value="提 交 "
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
</body>
</html>