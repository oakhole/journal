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
			<h3 style="cursor: s-resize;">屏蔽词</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab">
				<form method="post" action="badwords-manage!saveBadwords"
					name="clientForm" id="clientForm">
					<table>
						<tbody>
							<tr class="alt-row">
								<th>
									<div style="margin-left:20px; margin-top:10px;">屏蔽词所属通道：</div>
									<span style="margin:20px;margin-top:3px;"><font
										color="red">所有通道屏蔽词(针对所有通道屏蔽)</font> </span> <br>
									<div style="margin-left:20px; margin-top:10px;">普通屏蔽词：</div> <textarea
										name="badwords" class="textarea"
										style="width:700px;height:150px;margin:20px;margin-top:3px;">${badwords.badwords}</textarea>
									<br> <br> <input type="hidden" name="flag" value="1" />
									<input type="hidden" name="channelGroupId"
									value="${badwords.channelGroupId}" /><input type="hidden"
									name="channelGroupName" value="${badwords.channelGroupName}" />
									<input type="hidden" name="badwordsId" value="${badwords.id}" />
									<br> <input value=" 返回 " name="submit" class="button"
									style="margin-left:20px; margin-bottom:10px;" type="submit">
								</th>
							</tr>
						</tbody>
					</table>
				</form>
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
	<script>
		function imp() {
			JqueryDialog.Open2('导入屏蔽词', 'importkeyword.aspx', 360, 80, true,
					true, false);
		}
	</script>
</body>
</html>