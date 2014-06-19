<%@page import="com.oakhole.smsManage.model.Sms"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	href="<%=basePath%>/DateCtrol/skin/WdatePicker.css">
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
</head>
<body>

	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">充值记录</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="searchgrid">
				<form method="post" action="financial-manage!income" name="Form"
					id="Form1">
					<label>充值帐号：</label> <input id="account" style="width:85px"
						name="account" type="text" value="${account}">
					<!-- <label>消费类型：</label>
						<select name="actType" id="ismms"
						style="line-height:30px;height:24px;padding-top:3px;padding-bottom:3px;">
							<option selected="selected" value="">全部</option>
							<option value="0">短信</option>
							<option value="1">彩信</option>
					</select> -->
					<label>起始时间：</label><input id="starttime" style="width:150px"
						name="startTime" value="${startTime}"
						onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						class="Wdate" type="text"> <label>结束时间：</label><input
						id="endtime" style="width:150px" name="endTime" value="${endTime}"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						class="Wdate" type="text"> <input class="button"
						value="查询" type="submit">
				</form>
			</div>
			<div style="display: block;" class="tab-content default-tab">

				<table>
					<thead>
						<tr>
							<th style="padding-left:5px;border-left:1px solid #C1DAD7">充值帐号</th>
							<th>操作帐号</th>
							<th>充值数量</th>
							<th>充值日期</th>
							<!--<th>充值类型</th>-->
							<th>操作类型</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${page.totalCount > 0}">
							<c:forEach var="financial" items="${page.result}"
								varStatus="status">
								<tr class="alt-row">
									<td style="padding-left:5px;">${financial.account}</td>
									<td>${financial.actAccount}</td>
									<td>${financial.actCount}</td>
									<td><fmt:formatDate value="${financial.actTime}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<c:if test="${financial.actMethod == 0}">
										<td>短信充值</td>
									</c:if>
									<c:if test="${financial.actMethod == 3}">
										<td>失败返还</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${page.totalCount < 1}">
							<tr clas="alt-row">
								<td colspan="5">暂无充值记录</td>
							</tr>
						</c:if>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="13">
								<div class="pagination">
									<form action="?" name="formpage" method="post">
										<a>共有 <b>${page.totalCount}条/${page.pageCount}页</b> </a> <a
											class="number"
											<c:if test="${page.currentPage > 0}"> href="financial-manage!income?account=${account}&startTime=${startTIme}&endTime=${endTime}&currentPage=${page.currentPage-1}"</c:if>>上一页</a>
										<c:forEach var="pagenum" items="${page.pageNum}"
											varStatus="status">
											<c:if test="${pagenum == page.currentPage}">
												<a class="number current">${pagenum}</a>
											</c:if>
											<c:if test="${pagenum != page.currentPage}">
												<a class="number"
													href="financial-manage!income?account=${account}&startTime=${startTIme}&endTime=${endTime}&currentPage=${pagenum}">${pagenum}</a>
											</c:if>
										</c:forEach>
										<a class="number"
											<c:if test="${page.currentPage < page.pageCount}"> href="financial-manage!income?account=${account}&startTime=${startTIme}&endTime=${endTime}&currentPage=${page.currentPage+1}"</c:if>>下一页</a>
										<input name="startTime" value="${startTime}" type="hidden"><input
											name="endTime" value="${endTime}" type="hidden"><input
											name="account" value="${account}" type="hidden"><input
											name="vouchertype" value="" type="hidden"><input
											name="currentPage" value="${page.currentPage}" size="3"
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
	<br>
	<br>
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
