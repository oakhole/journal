<%@page import="com.oakhole.smsManage.model.Sms"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<!-- Start Content-Box -->
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">发送失败</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="searchgrid">
				<form method="post" action="sms-send-manage!findAllSmsSend"
					name="Form" id="Form">
					<input type="hidden" value="3" name="sendStatus" /> <label>发送账号：</label><input
						id="account" style="width:60px" name="account" type="text"
						value="${account}"> <label>任务ID：</label><input id="taskid"
						style="width:55px" name="taskid" type="text" value="${taskId}">
					<label>发送内容：</label><input id="msgcontent" style="width:85px"
						name="content" type="text" value="${content}"><label>起始时间：</label><input
						id="starttime" style="width:85px" name="starttime"
						onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						class="Wdate" type="text" value="${startTime}"> <label>结束时间：</label><input
						id="endtime" style="width:85px" name="endtime"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						class="Wdate" type="text" value="${endTime}"><input
						class="button" value="查询" type="submit">
				</form>
			</div>
			<div style="display: block;" class="tab-content default-tab">
				<table width="100%">
					<thead>
						<tr>
							<th>ID</th>
							<th>发送者</th>
							<th>联系人</th>
							<th>总量</th>
							<th>计费数</th>
							<th>短信内容</th>
							<th>发送时间</th>
							<th>下载</th>
							<th>操作</th>
						</tr>
					</thead>

					<tbody>
						<c:if test="${page.totalCount > 0}">
							<c:forEach var="smsSend" items="${page.result}"
								varStatus="status">
								<tr class="alt-row">
									<td>${smsSend.id}</td>
									<td>${smsSend.account}</td>
									<td>${smsSend.linkman}</td>
									<td>${smsSend.sendPhoneCount}</td>
									<td>${smsSend.chargeCount}</td>
									<td><a href="#" title="点击复制"
										onclick="javascript:copy('${smsSend.content}');">${fn:substring(smsSend.content,0,20)}……</a>
									</td>
									<td><fmt:formatDate value="${smsSend.sendTime}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td><a
										href="sms-send-manage!downLoadPhoneNumber?sendFilePath=${smsSend.sendFilePath}">下载号码</a>
									</td>
									<td><a href="sms-send-manage!resend?sendId=${smsSend.id}">重新发送</a>
										| <a
										href="sms-send-manage!deleteSmsSend?smsSendIds=${smsSend.id}"><font
											color="red">删除</font> </a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${page.totalCount < 1}">
							<tr class="alt-row">
								<td colspan="12" style="padding-left:5px;">暂无记录</td>
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
											<c:if test="${currentPage > 1}">href="sms-send-manage!findAllSmsSend?sendStatus=3&account=${account}&content=${content}&starttime=${startTime}&endtime=${endTime}&taskId=${taskId}&currentPage=${page.currentPage-1}"</c:if>>上一页</a>
										<c:forEach var="pagenum" items="${page.pageNum}"
											varStatus="status">
											<c:if test="${pagenum==currentPage}">
												<a class="number current">${pagenum}</a>
											</c:if>
											<c:if test="${pagenum!=currentPage}">
												<a class="number"
													href="sms-send-manage!findAllSmsSend?sendStatus=3&account=${account}&content=${content}&starttime=${startTime}&endtime=${endTime}&taskId=${taskId}&currentPage=${pagenum}">${pagenum}</a>
											</c:if>
										</c:forEach>
										<a
											<c:if test="${currentPage < page.pageCount}">href="sms-send-manage!findAllSmsSend?sendStatus=3&account=${account}&content=${content}&starttime=${startTime}&endtime=${endTime}&taskId=${taskId}&currentPage=${page.currentPage+1}"</c:if>
											class="number">下一页</a> <input name="endtime"
											value="${endTime}" type="hidden"><input
											name="starttime" value="${startTime}" type="hidden"><input
											name="account" value="${account}" type="hidden"><input
											name="content" value="${content}" type="hidden"><input
											type="hidden" name="sendStatus" value="3" /><input
											name="taskid" value="${taskId}" type="hidden"><input
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
	<script>
		function copy(content) {
			var cp = document.getElementById(content);

			/* if (document.all) { // IE */
			window.clipboardData.setData("Text", content);
			alert('您已经成功复制内容！');
			/* } else { // FIREFOX
				alert('firefox下不支持剪贴板,请用鼠标复制');
			} */
		}
	</script>
</body>
</html>