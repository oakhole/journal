<%@page import="com.oakhole.common.util.Paginator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oakhole.systemManage.model.SystemLog"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<SystemLog> allSysLog = (List<SystemLog>) request
	.getAttribute("allSysLog");
	int pageCount = (Integer) request.getAttribute("pageCount");
	int currentPage = (Integer) request.getAttribute("currentPage");
	int totalCount = (Integer) request.getAttribute("totalCount");
	List<Integer> pageNum = (List<Integer>) request
	.getAttribute("pageNum");

	String content = request.getAttribute("content") == null ? ""
	: (String) request.getAttribute("content");
	String actor = request.getAttribute("actor") == null ? "" :(String) request.getAttribute("actor");
	String actorName = request.getAttribute("actorName") == null ? "" : (String) request.getAttribute("actorName");
	String startTime = request.getAttribute("startTime") == null ? "" : (String) request.getAttribute("startTime");
	String endTime = request.getAttribute("endTime") == null ? "" : (String) request.getAttribute("endTime");
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
			<h3 style="cursor: s-resize;">日志管理</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="searchgrid">
				<form method="post" action="system-log-manage!findAllSystemLog"
					name="Form" id="Form1">
					<c:if test="${sessionScope.GLOBALAPPUSER.userType != 2}">
						<label>客户帐号：</label>
						<input id="account" style="width:145px" name="account" type="text"
							value="<%=actor%>"> <label>客户名称：</label> <input
							id="realname" style="width:145px" name="realname" type="text"
							value="<%=actorName%>">
					</c:if>
					<label>日志内容：</label> <input id="content" style="width:145px"
						name="content" type="text" value="<%=content%>"> <label>起始时间：</label><input
						id="starttime" style="width:85px" name="starttime"
						onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						class="Wdate" type="text" value="<%=startTime%>"> <label>结束时间：</label><input
							id="endtime" style="width:85px" name="endtime"
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
							class="Wdate" type="text" value="<%=endTime%>"> <input
								class="button" value="查询" type="submit"><input
									name="currentPage" value="1" type="hidden">
				</form>
			</div>
			<div style="display: block;" class="tab-content default-tab">
				<table>
					<thead>
						<tr>
							<th style="padding-left:5px;border-left:1px solid #C1DAD7">日志内容</th>
							<th>操作帐号</th>
							<th>IP</th>
							<th>操作时间</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (allSysLog == null || allSysLog.size() == 0) {
						%>
						<tr class="alt-row">
							<td style="padding-left:5px;" colspan="12">暂无日志</td>
						</tr>
					</tbody>
					<%
						} else {
						for (SystemLog sysLog : allSysLog) {
					%>
					<tr class="alt-row">
						<td style="padding-left:5px;width:60%"><%=sysLog.getAction()%></td>
						<td><%=sysLog.getActor()%></td>
						<td><%=sysLog.getActIP()%></td>
						<td><%=sdf.format(sysLog.getActionTime())%></td>
					</tr>
					<%
						}
					%>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="13">
								<div class="pagination">
									<form action="system-log-manage!findAllSystemLog"
										name="formpage" method="post">
										<a>共有 <b><%=totalCount%></>条/<%=pageCount%>页</b> </a> <a
											class="number" <%if (currentPage > 1) {%>
											href="system-log-manage!findAllSystemLog?currentPage=<%=currentPage - 1%>&realname=<%=actorName%>&starttime=<%=startTime%>&endtime=<%=endTime%>&account=<%=actor%>&action=<%=content%>"
											<%}%>>上一页</a>
										<%
											for (int i = 0; i < pageNum.size(); i++) {
										%>
										<%
											if (pageNum.get(i) == currentPage) {
										%>
										<a class="number current"><%=currentPage%></a>
										<%
											} else {
										%>
										<a class="number"
											href="system-log-manage!findAllSystemLog?currentPage=<%=pageNum.get(i)%>&realname=<%=actorName%>&starttime=<%=startTime%>&endtime=<%=endTime%>&account=<%=actor%>&action=<%=content%>"><%=pageNum.get(i)%></a>
										<%
											}
											}
										%>
										<a <%if (currentPage < pageCount) {%>
											href="system-log-manage!findAllSystemLog?currentPage=<%=currentPage + 1%>&realname=<%=actorName%>&starttime=<%=startTime%>&endtime=<%=endTime%>&account=<%=actor%>&action=<%=content%>"
											<%}%> class="number">下一页</a> <input name="realname"
											value="<%=actorName%>" type="hidden"> <input
											name="starttime" value="<%=startTime%>" type="hidden">
												<input name="endtime" value="<%=endTime%>" type="hidden">
													<input name="content" type="hidden" value="<%=content%>" /><input
													name="account" value="<%=actor%>" type="hidden"> <input
														name="currentPage" value="<%=currentPage%>" size="3"
														style="height:21px;line-height:21px;" type="text">
															<input id="turnTo" value="跳 转" class="button"
															type="submit">
									</form>
								</div>
							</td>
						</tr>
					</tfoot>
					<%
						}
					%>
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