<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
			<h3 style="cursor: s-resize;">白名单</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">

			<div class="searchgrid">
				<form method="post" action="system-manage!whiteList" name="Form"
					id="Form1">

					<label>客户账号：</label> <input id="account" style="width:145px"
						name="account" type="text"> <input class="button"
						value="查询" type="submit">
				</form>
			</div>
			<div style="display: block;" class="tab-content default-tab">
				<table>
					<thead>
						<tr>
							<th style="padding-left:5px;border-left:1px solid #C1DAD7">账号</th>
							<th>白名单路径</th>
							<th>号码个数</th>
							<th style="padding-left:10px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${totalCount > 0}">
							<c:forEach var="employee" items="${allEmployee}"
								varStatus="status">
								<tr class="alt-row">
									<td style="padding-left:5px;">${employee.account}</td>
									<td style="width:780px;padding-left:10px;"><div
											style="width:780px;word-break:break-all;line-height:20px;word-wrap:break-word;"
											title="">${fn:substring(employee.whiteListPath,11,30)}</div>
									</td>
									<td style="width:60px;padding-left:10px;">${employee.whitePhoneCount}</td>
									<td style="width:60px;padding-left:10px;"><a
										href="system-manage!updateWhiteList?employeeId=${employee.id}&whiteListPath=${employee.whiteListPath}">编辑</a>
										| <a href="#" onClick="clearWhiteList(${employee.id});">清空</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${totalCount < 1}">
							<tr class="alt-row">
								<td>暂无任何白名单</td>
							</tr>
						</c:if>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="13">
								<div class="pagination">
									<form action="?" name="formpage" method="post">
										<a>共有 <b>${totalCount}条/${pageCount}页</b> </a> <a
											class="number"
											<c:if test="${currentPage>1}">
												href="system-manage!whiteList?currentPage=${currentPage-1}" </c:if>>上一页</a>
										<c:forEach var="pagenum" items="${pageNum}" varStatus="status">
											<c:if test="${pagenum==currentPage}">
												<a class="number current">${pagenum}</a>
											</c:if>
											<c:if test="${pagenum!=currentPage}">
												<a class="number"
													href="system-manage!whiteList?currentPage=${pagenum}">${pagenum}</a>
											</c:if>
										</c:forEach>
										<a class="number"
											<c:if test="${currentPage<pageCount}">
												href="system-manage!whiteList?currentPage=${currentPage+1}"</c:if>>下一页</a>
										<input name="account" value="${account }" type="hidden">
											<input name="currentPage" value="${currentPage}" size="3"
											style="height:21px;line-height:21px;" type="text"> <input
												value="跳 转" class="button" type="submit">
									</form>
								</div></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<script>
		function clearWhiteList(id) {
			var flag = window.confirm("是否清空该白名单?");
			if (flag) {
				location.href = "system-manage!clearWhiteList?employeeId=" + id;
			}
		}
	</script>



</body>
</html>