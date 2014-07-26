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
			<h3 style="cursor: s-resize;">等待列表</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="searchgrid">
				<form method="post" action="sms-send-manage!findAllSmsSend"
					name="Form" id="Form">
					<input type="hidden" name="isCut" value="${isCut}" /> <input
						type="hidden" value="0" name="sendStatus" /> <label>发送账号：</label><input
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
							<th>定时时间</th>
							<th>下载</th>
							<th>白名单</th>
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
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${smsSend.planTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><a
										href="sms-send-manage!downLoadPhoneNumber?sendFilePath=${smsSend.sendFilePath}">下载号码</a>
									</td>
									<td><a
										href="../system-manage/system-manage!getWhiteList?employeeId=${smsSend.appUserId}"
										target="_blank">查看</a></td>
									<td><c:if
											test="${sessionScope.GLOBALAPPUSER.id != smsSend.appUserId}">
											<c:if test="${sessionScope.GLOBALAPPUSER.userType != 2}">
												<c:if test="${smsSend.auditStatus == 0}">
													<a
														href="sms-send-manage!passAudit?auditStatus=2&sendId=${smsSend.id}"><font
														color="green">通过</font> </a> | <a
														href="sms-send-manage!passAudit?auditStatus=3&sendId=${smsSend.id}"><font
														color="red">驳回</font> | </a>
												</c:if>
											</c:if>
										</c:if> <c:if test="${sessionScope.GLOBALAPPUSER.userType==0}">
											<a
												href="sms-send-manage!changeSendStatus?smsSendIds=${smsSend.id}&sendStatus=2"><font
												color="red">完成</font> </a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${page.totalCount < 1}">
							<tr class="alt-row">
								<td colspan="12" style="padding-left:5px;">暂无需要审核的记录</td>
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
											<c:if test="${page.currentPage > 1}">href="sms-send-manage!findAllSmsSend?sendStatus=0&account=${account}&content=${content}&starttime=${startTime}&endtime=${endTime}&taskId=${taskId}&currentPage=${page.currentPage-1}"</c:if>>上一页</a>
										<c:forEach var="pagenum" items="${page.pageNum}"
											varStatus="status">
											<c:if test="${pagenum==page.currentPage}">
												<a class="number current">${pagenum}</a>
											</c:if>
											<c:if test="${pagenum!=currentPage}">
												<a class="number"
													href="sms-send-manage!findAllSmsSend?sendStatus=0&account=${account}&content=${content}&starttime=${startTime}&endtime=${endTime}&taskId=${taskId}&currentPage=${pagenum}">${pagenum}</a>
											</c:if>
										</c:forEach>
										<a
											<c:if test="${page.currentPage < page.pageCount}">href="sms-send-manage!findAllSmsSend?sendStatus=0&account=${account}&content=${content}&starttime=${startTime}&endtime=${endTime}&taskId=${taskId}&currentPage=${page.currentPage+1}"</c:if>
											class="number">下一页</a> <input name="endtime"
											value="${endTime}" type="hidden"><input
											name="starttime" value="${startTime}" type="hidden"><input
											name="account" value="${account}" type="hidden"><input
											name="content" value="${content}" type="hidden"><input
											type="hidden" name="sendStatus" value="0" /><input
											name="taskid" value="${taskId}" type="hidden"><input
											name="currentPage" value="${page.currentPage}" size="3"
											style="height:21px;line-height:21px;" type="text"><input
											type="hidden" name="sendStatus" value="0" /> <input
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
	<script>
		function checkall(obj) {
			var objArr = document.getElementsByName("checkbox");
			// alert(objArr);
			if (obj.checked) {
				// alert(obj);
				for ( var i = 0; i < objArr.length; i++) {
					objArr[i].checked = true;
				}
			} else {
				for ( var i = 0; i < objArr.length; i++) {
					objArr[i].checked = false;
				}
			}
		}
		function getmobiles(title, url) {
			JqueryDialog.Open1(title, url, 265, 240, false, false, true, 'no');
		}

		function moredel() {
			var commres, m = 0, myarray = "";
			for ( var i = 0; i < document.getElementsByName("checkbox").length; i++) {
				if (document.getElementsByName("checkbox")[i].checked) {
					if (m == 0) {
						commres = document.getElementsByName("checkbox")[i].value;
						myarray = commres;
					} else {
						commres = document.getElementsByName("checkbox")[i].value;
						myarray = myarray + "|" + commres;
					}
					m = m + 1;
				}
			}
			if (m < 1) {
				alert('请选择需要删除的屏蔽词!');
				return;
			}

			window.location.href = 'keyword.aspx?action=moredel&myarray='
					+ myarray;
		}
		function opt(url) {
			var id, m = 0;
			id = new Array();
			for ( var i = 0; i < document.getElementsByName("checkbox").length; i++) {
				if (document.getElementsByName("checkbox")[i].checked) {
					id[m] = document.getElementsByName("checkbox")[i].value;
					m = m + 1;
				}
			}
			if (m < 1) {
				alert("请选择一个需要续发任务");
				return;
			}

			location.href = url + id;
		}

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
	<div
		style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;">
		<iframe style="width: 186px; height: 197px;"
			src="<%=basePath%>/DateCtrol/DateCtrol.htm" border="0" scrolling="no"
			frameborder="0"></iframe>
	</div>
</body>
</html>