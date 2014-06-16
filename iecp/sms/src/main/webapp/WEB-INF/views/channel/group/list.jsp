<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="<%=basePath%>css/reset.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="<%=basePath%>css/invalid.css"
	type="text/css" media="screen">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/simpla.js"></script>

<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/jquery_dialog.css">
<script type="text/javascript" src="<%=basePath%>js/jquery_dialog.js"></script>
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
</head>
<body>

	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">短信通道组管理</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="default-tab">

				<div class="top_dep">
					<a href="channel-manage!addChannelGroup?flag=0" class="add_dep">新增通道组</a>
				</div>
				<div style="display: block;" class="tab-content default-tab">
					<table>
						<thead>
							<tr>
								<th style="padding-left:5px;border-left:1px solid #C1DAD7">通道组ID</th>
								<th>通道组名称</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="channelGroup" items="${page.result}"
								varStatus="status">
								<tr class="alt-row">
									<td style="padding-left:5px;">${channelGroup.id}</td>
									<td>${channelGroup.name}</td>
									<td>${channelGroup.memo}</td>
									<td><a
										href="channel-manage!addChannelGroup?channelGroupId=${channelGroup.id}&flag=1">编辑</a>
										| <a
										href="channel-manage!deleteChannelGroup?channelGroupId=${channelGroup.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<script>
		function showInterface(id) {
			$("#" + id).toggle();
			parent.SetCwinHeight('uBox');
			//JqueryDialog.Open(title, url, 500, 300);
		}
		function optedit(url) {
			var id, m = 0;
			id = new Array();
			for ( var i = 0; i < document.getElementsByName("checkbox").length; i++) {
				if (document.getElementsByName("checkbox")[i].checked) {
					id[m] = document.getElementsByName("checkbox")[i].value;
					m = m + 1;
				}
			}
			if (m != 1) {
				alert("请选择一个通道组进行编辑");
				return;
			}

			location.href = url + id;
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
				alert("请选择一个通道组");
				return;
			}

			location.href = url + id;
		}

		function optRelation(title, url) {
			var id, m = 0;
			id = new Array();
			for ( var i = 0; i < document.getElementsByName("checkbox").length; i++) {
				if (document.getElementsByName("checkbox")[i].checked) {
					id[m] = document.getElementsByName("checkbox")[i].value;
					m = m + 1;
				}
			}
			if (m != 1) {
				alert("必须且只能选择一个需要设置的通道组");
				return;
			}
			JqueryDialog.Open(title, url + id, 300, 240);
		}

		function opttranstoRelationUrl(title, url) {

			JqueryDialog.Open(title, url, 300, 240);
		}

		function optRelationUrl(title, url) {
			JqueryDialog.Open(title, url, 300, 240);
		}

		function optdel(url) {
			var id, m = 0;
			id = new Array();
			for ( var i = 0; i < document.getElementsByName("checkbox").length; i++) {
				if (document.getElementsByName("checkbox")[i].checked) {
					id[m] = document.getElementsByName("checkbox")[i].value;
					m = m + 1;
				}
			}
			if (m < 1) {
				alert("请选择一个通道组");
				return;
			}
			if (window.confirm("确定要删除该通道组么？")) {
				location.href = url + id;
			}
		}

		function edituserGroupId(groupid) {
			JqueryDialog.Open("更换用户通道组", "interfacegroupchuser.aspx?IsMms="
					+ IsMms + "&interfaceGroupId=" + groupid, 500, 300);
		}
	</script>
</body>
</html>