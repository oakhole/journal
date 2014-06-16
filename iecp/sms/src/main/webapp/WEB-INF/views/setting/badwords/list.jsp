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
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
</head>
<body>
	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">屏蔽词管理</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="default-tab">
				<table>
					<thead>
						<tr>
							<th style="padding-left:5px;border-left:1px solid #C1DAD7">通道名称</th>
							<th>屏蔽词</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${totalCount>0}">
							<c:forEach var="bad" items="${allBadwords}" varStatus="status">
								<tr class="alt-row">
									<c:if test="${bad.id==1}">
										<td style="padding-left:5px;"><font color="red">所有通道屏蔽词(针对所有通道屏蔽)</font>
										</td>
									</c:if>
									<c:if test="${bad.id!=1}">
										<td style="padding-left:5px;">${bad.channelGroupName}</td>
									</c:if>
									<td>${bad.badwords}</td>
									<td><a
										href="badwords-manage!addBadwords?flag=1&badwordsId=${bad.id}">编辑</a>
										| <a href="#"
										onclick="return clearBadwords('badwords-manage!saveBadwords?flag=1&badwordsId=${bad.id}&badwords=&channelGroupId=${bad.channelGroupId}&channelGroupName=${bad.channelGroupName}');">清除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${totalCount < 1}">
							<tr class="alt-row">
								<td style="padding-left:5px;" colspan="4">暂无通道屏蔽词</td>
							</tr>
						</c:if>
					</tbody>
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
	<script type="text/javascript" language="javascript">
		function clearBadwords(url) {
			var flag = window.confirm('是否清除该通道屏蔽词？');
			if (flag) {
				window.location.href = url;
			}
		}
	</script>
</body>
</html>