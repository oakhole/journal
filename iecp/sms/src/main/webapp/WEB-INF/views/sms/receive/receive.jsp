<%@page import="com.oakhole.smsManage.model.Sms"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
</head>
<body>
	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">收件箱</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="searchgrid">
				<form method="post" action="sms-receive-manage!findAllSmsReceive"
					name="Form" id="Form1">

					<label>起始时间：</label><input id="starttime" style="width:85px"
						name="starttime"
						onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						class="Wdate" type="text" value="${startTime}"> <label>结束时间：</label><input
						id="endtime" style="width:85px" name="endtime"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						class="Wdate" type="text" value="${endTime}"><label>客户账号：</label><input
						id="account" style="width:125px" name="account" type="text">
					<label>手机号码：</label><input id="mobile" style="width:125px"
						name="mobile" type="text"> <input class="button"
						value="查询" type="submit">
				</form>
			</div>
			<div style="display: block;" class="tab-content default-tab">
				<table width="100%">
					<thead>
						<tr>
							<th style="padding-left:5px;border-left:1px solid #C1DAD7">客户账号</th>
							<th>联系人</th>
							<th>手机号码</th>
							<th>回复内容</th>
							<th>接收时间</th>
							<th>操作</th>
						</tr>
					</thead>


					<tbody>
						<c:if test="${page.totalCount < 1}">
							<tr class="alt-row">
								<td colspan="7" style="padding-left:5px;">暂无任务</td>
							</tr>
						</c:if>

						<c:if test="${page.totalCount > 0}">
							<c:forEach var="smsReceive" items="${page.result}"
								varStatus="status">
								<tr class="alt-row">
									<td>${smsReceive.account}</td>
									<td>${smsReceive.linkman}</td>
									<td>${smsReceive.mobile}</td>
									<td>${smsReceive.content}</td>
									<td><fmt:formatDate value="${smsReceive.receiveTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><a href="#">删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="13">
								<div class="pagination">
									<form action="?" name="formpage" method="post">
										<a>共有 <b>${page.totalCount}/${page.pageCount}页</b> </a> <a
											class="number"
											<c:if test="${currentPage > 1}">href="sms-receive-manage!findAllSmsReceive?account=${account}&content=${content}&starttime=${startTime}&endtime=${endTime}&currenPage=${currentPage-1}"</c:if>>上一页</a>
										<c:forEach var="pagenum" items="${page.pageNum}"
											varStatus="status">
											<c:if test="${pageNum==currentPage}">
												<a class="number current">${pagenum}</a>
											</c:if>
											<c:if test="${pageNum!=currentPage}">
												<a class="number"
													href="sms-receive-manage!findAllSmsReceive?account=${account}&mobile=${mobile}&starttime=${startTime}&endtime=${endTime}&currenPage=${currentPage}">${pagenum}</a>
											</c:if>
										</c:forEach>
										<a
											<c:if test="${currentPage < page.pageCount}">href="sms-receive-manage!findAllSmsReceive?account=${account}&mobile=${mobile}&starttime=${startTime}&endtime=${endTime}&currenPage=${currentPage+1}"</c:if>
											class="number">下一页</a> <input name="endtime"
											value="${endTime}" type="hidden"><input
											name="starttime" value="${startTime}" type="hidden"><input
												name="account" value="${account}" type="hidden"><input
													name="mobile" value="${mobile}" type="hidden"><input
											name="page" value="${currentPage}" size="3"
											style="height:21px;line-height:21px;" type="text"> <input
											value="跳 转" class="button" type="submit">
									</form>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<br> <br> <br> <br> <br> <br> <br> <br>
	<div
		style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;">
		<iframe style="width: 186px; height: 197px;"
			src="<%=basePath%>/DateCtrol/DateCtrol.htm" border="0" scrolling="no"
			frameborder="0"></iframe>
	</div>
</body>
</html>
