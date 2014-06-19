<%@page import="com.oakhole.smsManage.model.Sms"%>
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
<link rel="stylesheet" href="<%=basePath%>/css/reset.css"
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
<body style="background:none repeat scroll 0 0 #E8F4FE;">
	<div class="content-box role" style="border:none;">
		<div class="content-box-content">
			<div class="searchgrid">
				<form name="clientForm" method="post" action="import.aspx"
					id="clientForm">
					<div>
						<input name="__VIEWSTATE" id="__VIEWSTATE"
							value="/wEPDwUJOTg2ODYxNjM4DxYEHgVncm91cAUjPG9wdGlvbiB2YWx1ZT0nJz7or7fpgInmi6k8L29wdGlvbj4eBmNsaWVudAUtPFRSPjx0ZCBjb2xzcGFuPSc0Jz7mmoLml6DogZTns7vkuro8L3RkPjwvVFI+ZGSi+ZjF3oqwjHD+tjNCMmxWe4x8Dw=="
							type="hidden">
					</div>

					<label>客户：</label><select id="Select1" name="groupid"
						style="padding:2px;" nullable="false" errmsg="建立群组">
						<option selected="selected" value="">请选择</option>
					</select> <input name="btnAdd" id="submit2" class="button" value=" 查询 "
						type="submit"> &nbsp; <input name="btnAdd" id="Button2"
						class="button" onclick="OK()" value=" 导入 " type="button">
				</form>
			</div>
			<div style="display: block;" class="tab-content default-tab">
				<table width="100%">
					<thead>
						<tr>
							<th
								style="padding-left:5px;width:50px;border-left:1px solid #C1DAD7"
								width="40"><input class="check-all" type="checkbox">
							</th>
							<th>联系人</th>
							<th>归属组</th>
							<th>手机号码</th>
						</tr>
					</thead>


					<tbody>
						<tr class="alt-row">
							<td colspan="4">暂无联系人</td>
						</tr>
					</tbody>

				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function OK() {
			var m = 0, myarray;

			for ( var i = 0; i < document.getElementsByName("checkbox").length; i++) {
				if (document.getElementsByName("checkbox")[i].checked) {
					if (m == 0) {
						myarray = document.getElementsByName("checkbox")[i].value;
					} else {
						myarray = myarray
								+ ","
								+ document.getElementsByName("checkbox")[i].value;
					}
					m = m + 1;
				}
			}
			if (m == 0) {
				alert("请选择手机号码！");
				return;
			}
			parent.setPhone(myarray);
			alert('共计导入' + m + '接受号码');
			return false;
		}
	</script>

	<div
		style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;">
		<iframe style="width: 186px; height: 197px;"
			src="<%=basePath%>/DateCtrol/DateCtrol.htm" border="0"
			frameborder="0" scrolling="no"></iframe>
	</div>
</body>
</html>