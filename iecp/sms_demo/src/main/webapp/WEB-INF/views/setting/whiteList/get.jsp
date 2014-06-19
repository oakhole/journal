<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<link rel="stylesheet" href="<%=basePath%>/css/mmsreset.css"
		type="text/css" media="screen">
		<link rel="stylesheet" href="<%=basePath%>/css/style.css"
			type="text/css" media="screen">
			<link rel="stylesheet" href="<%=basePath%>/css/invalid.css"
				type="text/css" media="screen">
				<script type="text/javascript" src="<%=basePath%>/js/jquery-1.js"></script>
				<script type="text/javascript" src="<%=basePath%>/js/simpla.js"></script>

				<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
				<script type="text/javascript"
					src="<%=basePath%>/js/formValidator_alert.js"></script>
				<link rel="stylesheet" type="text/css"
					href="<%=basePath%>/css/jquery_dialog.css">
					<script type="text/javascript"
						src="<%=basePath%>/js/jquery_dialog.js"></script>
					<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
					<!-- Internet Explorer .png-fix -->
					<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
</head>
<body>
	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">
				查看白名单&nbsp;&nbsp;[<a href="system-manage!whiteList">白名单</a>]
			</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab"
				id="form">
				<form method="post" action="system-manage!saveWhiteList"
					name="clientForm" id="clientForm">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr class="alt-row">
								<th>
									<div style="margin-left:20px; margin-top:10px;">手机号：</div> <textarea
										name="mobile" class="textarea"
										style="width:700px;height:250px;margin:20px;margin-top:3px;">${mobile}</textarea>
								</th>

							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
