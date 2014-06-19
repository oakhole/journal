<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<form id="clientForm" action="financial-manage!charge" method="post"
		name="clientForm">

		<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody>
				<tr class="alt-row">
					<c:if test="${actMethod==0}">
						<td align="right" width="68" height="35">充值条数:</td>
					</c:if>
					<c:if test="${actMethod==2}">
						<td align="right" width="68" height="35">扣费条数:</td>
					</c:if>
					<td valign="middle" colspan="3"><input type="text"
						value="${actCount}" name="actCount" maxlength="10"
						style="width:220px" id="actCount" title="必须是正整数">
					</td>
				</tr>
				<tr>
					<td align="right" width="68" height="35">备注：</td>
					<td valign="middle" colspan="3"><input type="text" name="memo"
						maxlength="200" style="width:220px" id="memo"
						title="如扣费操作,请填写备注信息">
					</td>
				</tr>

				<input type="hidden" name="actMethod" value="${actMethod}" />
				<input type="hidden" name="actType" value="0" />
				<input type="hidden" name="account" value="${account}" />
			</tbody>
		</table>
	</form>
	<c:if test="${chargeErr !=null}">
		<script>
			alert("${chargeErr}");
			parent.locationframehref("<%=basePath%>system-manage/employee-manage!findAllEmployee");
		</script>
	</c:if>
	<script type="text/javascript" language="javascript">
		function Ok() {
			document.getElementById("clientForm").submit();
		}
	</script>

</body>
</html>
