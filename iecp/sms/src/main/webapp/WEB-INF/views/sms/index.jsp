<%@page import="com.oakhole.smsManage.model.Sms"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	src="<%=basePath%>/js/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>/css/WdatePicker.css">
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
<script>
	function changeCut(smsTable,sendId,flag){
		
		url = "sms-manage!changeCut";
		data = {smsTable:smsTable,sendId:sendId,flag:flag};
	
		$.get(url,data,function(result){
			if(result == 'success'){
				alert("更改完成");
			}
		});
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
</head>
<body>
	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">详细列表</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="searchgrid">
				<form method="post" action="sms-manage!findAllSms" name="Form"
					id="Form1">
					<input type="hidden" name="sendId" value="${sendId}" /> <label>发送状态：</label>
					<select name="status" id="status"
						style="line-height:30px;height:24px;padding-top:3px;padding-bottom:3px;vertical-align:middle;">
						<option <c:if test="${status==null}">selected="selected"</c:if>
							value="">全部</option>
						<option <c:if test="${status=='1'}">selected="selected"</c:if>
							value="1">发送成功</option>
						<option <c:if test="${status=='2'}">selected="selected"</c:if>
							value="2">发送失败</option>
						<option <c:if test="${status=='0'}">selected="selected"</c:if>
							value="0">未知状态</option>
					</select>&nbsp;&nbsp;
					<c:if test="${sessionScope.GLOBALAPPUSER.userType==0}">
						<!-- <label>提交状态：</label>
						<select name="pushStatus" id="pushStatus"
							style="line-height:30px;height:24px;padding-top:3px;padding-bottom:3px;vertical-align:middle;">
							<option
								<c:if test="${pushStatus==null}">selected="selected"</c:if>
								value="">全部</option>
							<option <c:if test="${pushStatus==1}">selected="selected"</c:if>
								value="1">提交成功</option>
							<option <c:if test="${pushStatus==0}">selected="selected"</c:if>
								value="2">提交失败</option>
						</select>&nbsp;&nbsp;
					</c:if> -->
						<label>手机号码：</label>
						<input id="mobile" style="width:100px" name="mobile" type="text"
							value="${mobile}">&nbsp;&nbsp;<input class="button"
							value="查询" type="submit">&nbsp;&nbsp;<input
							class="button" value="导出 " type="button" id="export"
							onclick="window.location.href='sms-manage!exportStatus?sendId=${sendId}&status=${status}'">
						<c:if test="${session.GLOBALAPPUSER.userType==0}">
							&nbsp;&nbsp;<input class="button" value="扣量-->成功" type="button"
								onclick="changeCut('${smsSend.smsTable}',${smsSend.id},0);">&nbsp;&nbsp;
									<input class="button" value="扣量-->未知" type="button"
								onclick="changeCut('${smsSend.smsTable}',${smsSend.id},1);">
						</c:if>
						<h4 style="text-align:right;display:inline;margin-left:100px;">
							&nbsp;&nbsp;发送成功:${smsSend.sendSuccess}
							&nbsp;&nbsp;发送失败:${smsSend.sendFailure}
							&nbsp;&nbsp;发送未知:${smsSend.sendPhoneCount-smsSend.sendSuccess-smsSend.sendFailure}</h4>
				</form>
			</div>
			<div style="display: block;" class="tab-content default-tab">
				<table width="100%">
					<thead>
						<tr>
							<th style="padding-left:5px;">批次号</th>
							<th>客户账号</th>
							<th>联系人</th>
							<th>手机号码</th>
							<th>短信内容</th>
							<c:if test="${sessionScope.GLOBALAPPUSER.userType==0}">
								<th>提交状态</th>
								<th>提交时间</th>
							</c:if>
							<th>回执状态</th>
							<th>回执时间</th>
							<!-- <th>操作</th> -->
						</tr>
					</thead>

					<tbody>
						<c:if test="${page.totalCount > 0}">
							<c:forEach var="sms" items="${page.result}" varStatus="status">
								<tr class="alt-row">
									<td>${sms.sendId}</td>
									<td>${smsSend.account}</td>
									<td>${smsSend.linkman}</td>
									<td>${sms.mobile}</td>
									<td><a href="#" title="点击复制"
										onclick="javascript:copy('${smsSend.content}');">${fn:substring(smsSend.content,0,20)}……</a>
									</td>
									<c:if test="${sessionScope.GLOBALAPPUSER.userType==0}">
										<td><c:if test="${sms.pushStatus == 0}">提交失败</c:if> <c:if
												test="${sms.pushStatus == 1}">正在提交</c:if> <c:if
												test="${sms.pushStatus == 2}">提交成功</c:if> <c:if
												test="${sms.pushStatus == 3}">提交失败</c:if>
										</td>
										<td><fmt:formatDate value="${sms.pushTime}"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
									</c:if>
									<td>${sms.status}</td>
									<td><fmt:formatDate value="${sms.statusTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<!--<td><a href="#">补发</a></td>-->
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${page.totalCount < 1}">
							<tr class="alt-row">
								<td colspan="10" style="padding-left:5px;">暂无记录</td>
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
											<c:if test="${page.currentPage > 1}">href="sms-manage!findAllSms?currentPage=${page.currentPage-1}&sendId=${smsSend.id}&status=${status}&mobile=${mobile}"</c:if>>上一页</a>
										<c:forEach var="pagenum" items="${page.pageNum}">
											<c:if test="${pagenum==page.currentPage}">
												<a class="number current">${pagenum}</a>
											</c:if>
											<c:if test="${pagenum!=page.currentPage}">
												<a class="number"
													href="sms-manage!findAllSms?currentPage=${pagenum}&sendId=${smsSend.id}&status=${status}&mobile=${mobile}">${pagenum}</a>
											</c:if>
										</c:forEach>
										<a
											<c:if test="${page.currentPage < page.pageCount}">href="sms-manage!findAllSms?currentPage=${page.currentPage+1}&sendId=${smsSend.id}&status=${status}&mobile=${mobile}"</c:if>
											class="number">下一页</a><input type="hidden" name="status"
											value="${status}" /><input name="mobile" value="${mobile}"
											type="hidden"><input type="hidden" name="sendId"
											value="${smsSend.id}" /><input name="currentPage"
											value="${page.currentPage}" size="3"
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
	<div
		style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;">
		<iframe style="width: 186px; height: 197px;"
			src="<%=basePath%>/DateCtrol/DateCtrol.htm" border="0" scrolling="no"
			frameborder="0"></iframe>
	</div>
</body>
</html>
