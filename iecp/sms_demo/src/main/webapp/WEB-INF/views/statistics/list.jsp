<%@page import="com.oakhole.smsManage.model.Sms"%>
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
<link rel="stylesheet" href="<%=basePath%>/css/reset.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/style.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/invalid.css"
	type="text/css" media="screen">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/simpla.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/jquery_dialog.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery_dialog.js"></script>
<script language="JavaScript" type="text/javascript"
	src="<%=basePath%>/DateCtrol/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>/css/WdatePicker.css">
<link href="<%=basePath%>/css/WdatePicker.css" rel="stylesheet"
	type="text/css">
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
</head>

<body>
	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">短信发送统计</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="searchgrid">
				<form method="post" action="sms-statistics!findAllSmsStatistics"
					name="Form" id="Form1">
					<%-- <c:if test="${sessionScope.GLOBALAPPUSER.userType==0}">
						<label>客户类型：</label>
						<select name="userType" id="userType"
							style="width:90px;line-height:30px;height:24px;padding-top:1px;padding-bottom:3px;">
							<option <c:if test="${userType == ""}">selected="selected"</c:if>
								value="">全部客户</option>
							<option <c:if test="${userType == "1"}">selected="selected"</c:if>
								value="1">代理商</option>
							<option <c:if test="${userType == "2"}">selected="selected"</c:if>
								value="2">普通客户</option>
						</select>
					</c:if> --%>
					<%-- <label>发送通道：</label>
						<select name="channelId" id="channelId"
							style="width:150px;line-height:30px;height:24px;padding-top:1px;padding-bottom:3px;">
							<option selected="selected" value="">全部</option>
							<c:forEach var="channel" items="${allChannel}" varStatus="status">
								<option
									<c:if test="${channelId == channel.id}">selected="selected"</c:if>
									value="${channel.id}">${channel.name}</option>
							</c:forEach>
						</select> --%>
					<c:if test="${sessionScope.GLOBALAPPUSER.userType != 2}">
						<label>客户账号：</label>
						<input id="account" style="width:80px" name="account" type="text"
							value="${account}">
					</c:if>
					<label>起始时间：</label> <input id="startTime" style="width:85px"
						name="startTime"
						onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						class="Wdate" type="text" value="${startTime}"> <label>结束时间：</label><input
						id="endTime" style="width:85px" name="endTime"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						class="Wdate" type="text" value="${endTime}"><input
						class="button" value="查询" type="submit"> <input
						class="button" onclick="excel();" value=" 导出 " type="button">
				</form>
			</div>
			<div style="display: block;" class="tab-content default-tab">
				<table width="100%">
					<thead>
						<tr>
							<th style="padding-left:5px;border-left:1px solid #C1DAD7">客户账号</th>
							<th>联系人</th>
							<th>提交数</th>
							<th>计费数</th>
							<th>发送成功</th>
							<th>发送失败</th>
							<th>未知数</th>
							<c:if test="${session.GLOBALAPPUSER.userType!=2}">
								<th>扣量数量</th>
								<th>操作</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:if test="${page.totalCount > 0}">
							<c:forEach var="smsStatistics" items="${page.result}"
								varStatus="status">
								<tr class="alt-row">
									<td style="padding-left:5px;">${smsStatistics.account}</td>
									<td>${smsStatistics.linkman}</td>
									<td>${smsStatistics.sendPhoneCount}</td>
									<td>${smsStatistics.chargeCount}</td>
									<td>${smsStatistics.sendSuccess}</td>
									<td>${smsStatistics.sendFailure}</td>
									<td>${smsStatistics.sendUnknown}</td>
									<c:if test="${session.GLOBALAPPUSER.userType!=2}">
										<td>${smsStatistics.cutCount}</td>
										<td><a
											href="sms-statistics!findAllSmsStatistics?account=${smsStatistics.account}">详情</a>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${page.totalCount < 1}">
							<tr class="alt-row">
								<td colspan="9">暂无任何记录</td>
							</tr>
						</c:if>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="13">
								<div class="pagination">
									<form action="?" name="formpage" method="post">
										<a>共有 <b>${page.totalCount}/${page.pageCount}页</b> </a> <a
											class="number"
											<c:if test="${page.currentPage > 1}">href="sms-statistics!findAllSmsStatistics?currentPage=${page.currentPage-1}&account=${account}&startTime=${startTIme}&endTime=${endTime}"</c:if>>上一页</a>
										<c:forEach var="pagenum" items="${page.pageNum}">
											<c:if test="${pagenum==page.currentPage}">
												<a class="number current">${pagenum}</a>
											</c:if>
											<c:if test="${pagenum!=page.currentPage}">
												<a class="number"
													href="sms-statistics!findAllSmsStatistics?currentPage=${pagenum}&account=${account}&startTime=${startTIme}&endTime=${endTime}">${pagenum}</a>
											</c:if>
										</c:forEach>
										<a
											<c:if test="${page.currentPage < page.pageCount}">href="sms-statistics!findAllSmsStatistics?currentPage=${page.currentPage+1}&account=${account}&startTime=${startTIme}&endTime=${endTime}"</c:if>
											class="number">下一页</a><input name="account"
											value="${account}" type="hidden"><input type="hidden"
											name="startTime" value="${startTime}" /><input
											name="endTime" type="hidden" value="${endTime}" /><input
											name="currentPage" value="${page.currentPage}" size="3"
											style="height:21px;line-height:21px;" type="text"> <input
											value="跳 转" class="button" type="submit">
									</form>
								</div></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<div
		style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;">
		<iframe style="width: 186px; height: 197px;"
			src="<%=basePath%>/DateCtrol/DateCtrol.htm" border="0" scrolling="no"
			frameborder="0"></iframe>
	</div>
</body>
</html>
