<%@page import="com.oakhole.smsManage.model.Sms"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<script src="<%=basePath%>/js/layerDialog.js" type="text/javascript"
	language="JavaScript"></script>
<link href="<%=basePath%>/css/member.css" type="text/css"
	rel="stylesheet">
<title>inner2</title>
<style type="text/css">
BODY {
	background: none;
}
</style>
</head>

<body style="margin-top:28px;">
	<form enctype="multipart/form-data" id="clientForm"
		action="sms-send-manage!uploadExcel" method="post" name="clientForm">

		<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody>
				<tr>
					<td align="right" height="25" style="width:70px;">上传号码：</td>
					<td><input type="file" style="width:200px" id="uploadFile"
						name="upload"> &nbsp;每行一个号码</td>
				</tr>
			</tbody>
		</table>
	</form>
	<c:if test="${uploadErr != null}">
		<script>
			alert("${uploadErr}");
			parent.setPhone("${mobile}");
		</script>
	</c:if>
	<script type="text/javascript" language="javascript">
		function Ok() {
			document.getElementById("clientForm").submit();
		}
	</script>

</body>
</html>
