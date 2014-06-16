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
<script>
		function refreshFrame() {
			location.href = '<%=basePath%>employee-manage/employee-manage!findAllEmployee';
	}
</script>
</head>
<body>

	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">客户管理</h3>
			<div class="clear"></div>
		</div>

		<div class="searchgrid">
			<form method="post" action="employee-manage!findAllEmployee"
				name="Form" id="Form1">
				<label>客户账号：</label><input id="account" style="width:90px"
					name="account" type="text" value="${account}"> <label>联系人名称：</label><input
					id="realname" style="width:90px" name="linkman" type="text"
					value="${linkman}"> <label>客户手机：</label><input id="mobile"
					style="width:90px" name="mobile" type="text" value="${mobile}">
				<label>客户类型：</label><select name="userType" id="suisuse"
					style="line-height:30px;height:24px;padding-top:3px;padding-bottom:3px;">
					<option <c:if test="${userType==null}" >selected="selected"</c:if>
						value="">全部</option>
					<option <c:if test="${userType=='0'}" >selected="selected"</c:if>
						value="0">管理员</option>
					<option <c:if test="${userType=='1'}" >selected="selected"</c:if>
						value="1">代理商</option>
					<option <c:if test="${userType=='2'}" >selected="selected"</c:if>
						value="2">网络用户</option>
						<option <c:if test="${userType=='3'}" >selected="selected"</c:if>
						value="2">终端用户</option>
				</select> <input class="button" value="查询" type="submit">
			</form>

		</div>

		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab">

				<table>
					<thead>
						<tr>
							<th style="padding-left:5px;border-left:1px solid #C1DAD7">客户名称</th>
							<th>客户类型</th>
							<th>电话</th>
							<th>手机</th>
							<th>QQ</th>
							<th>邮箱</th>
							<th>账号</th>
							<th>余额</th>
							<th>单价(分)</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${totalCount > 0}">
							<c:forEach var="employee" items="${allEmployee}"
								varStatus="status">
								<tr class="alt-row">
									<td style="padding-left:5px;">${employee.linkman}</td>
									<c:if test="${employee.userType==0}">
										<td>管理员</td>
									</c:if>
									<c:if test="${employee.userType==1}">
										<td>代理商</td>
									</c:if>
									<c:if test="${employee.userType==2}">
										<td>普通客户</td>
									</c:if>
									<c:if test="${employee.userType==3}">
										<td>终端用户</td>
									</c:if>
									<td>${employee.phoneNumber}</td>
									<td>${employee.mobile}</td>
									<td>${employee.qq}</td>
									<td>${employee.email}</td>
									<td>${employee.account}</td>
									<td>${employee.balance}</td>
									<td>${employee.price}</td>
									<td><a href="#"
										onClick="recharge('为${employee.account}充值','<%=basePath%>financial-manage/financial-manage!recharge?account=${employee.account}&actType=0&actMethod=0');">充值</a>
										| <a href="#"
										onclick="recharge('为${employee.account}扣费','<%=basePath%>financial-manage/financial-manage!recharge?account=${employee.account}&actType=0&actMethod=2');">扣费</a>
										| <a
										href="employee-manage!addEmployee?flag=1&employeeId=${employee.id}&isCut=${isCut}">编辑</a>
										| <a href="#"
										onclick="return delEmployee('employee-manage!deleteEmployee?employeeId=${employee.id}');">删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="13">
								<div class="pagination">
									<form action="employee-manage!findAllEmployee" name="formpage"
										method="post">
										<a>共有 <b>${totalCount}条/${pageCount}页</b> </a> <a
											class="number"
											<c:if test="${currentPage > 1}">href="employee-manage!findAllEmployee?currentPage=${currentPage-1}&account=${account}&linkman=${linkman}&mobile=${mobile}&userType=${userType}"</c:if>>上一页</a>
										<c:forEach var="pagenum" items="${pageNum}" varStatus="status">
											<c:if test="${pagenum==currentPage}">
												<a class="number current">${pagenum}</a>
											</c:if>
											<c:if test="${pagenum!=currentPage}">
												<a class="number"
													href="employee-manage!findAllEmployee?currentPage=${pagenum}&account=${account}&linkman=${linkman}&mobile=${mobile}&userType=${userType}">${pagenum}</a>
											</c:if>
										</c:forEach>
										<a class="number"
											<c:if test="${currentPage < pageCount}">href="employee-manage!findAllEmployee?currentPage=${currentPage+1}&account=${account}&linkman=${linkman}&mobile=${mobile}&userType=${userType}"</c:if>>下一页</a>
										<input name="" value="" type="hidden"> <input
											name="page" value="${currentPage}" size="3"
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
	<br> <br> <br> <br> <br> <br> <br> <br>

									<script type="text/javascript">
										function recharge(title, url) {
											JqueryDialog.Open(title, url, 350,
													120);
										}

										function delEmployee(url) {
											var flag = window
													.confirm('是否删除该用户?');
											if (flag) {
												window.location.href = url;
											}
										}
									</script>
</body>
</html>
