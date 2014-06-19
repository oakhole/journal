<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			<h3 style="cursor: s-resize;">公告管理</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="default-tab">
				<c:if test="${sessionScope.GLOBALAPPUSER.userType!=2}">
					<div class="top_dep">
						<a href="advice-manage!addAdvice?flag=0" class="add_dep">新增公告
						</a>
					</div>
				</c:if>
				<div class="searchgrid">
					<form method="post" action="advice-manage!findAllAdvice"
						name="Form" id="Form1">

						<label>添加账号：</label><input id="author" style="width:100px"
							name="account" type="text"><input class="button"
							value="查询" type="submit">
					</form>
				</div>
				<table>
					<thead>
						<tr>
							<th style="padding-left:5px;border-left:1px solid #C1DAD7">公告标题
							</th>
							<th>添加账号</th>
							<th>查看次数</th>
							<th>创建时间</th>
							<th>近期更新时间</th>
							<c:if test="${sessionScope.GLOBALAPPUSER.userType==0}">
								<th>操作</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:if test="${totalCount < 1}">
							<tr class="alt-row">
								<td colspan="12" style="padding-left:5px;">暂无公告</td>
							</tr>
						</c:if>
						<c:if test="${totalCount > 0}">
							<c:forEach var="advice" items="${allAdvice}" varStatus="status">
								<tr class="alt-row">
									<td>${advice.title}</td>
									<td>${advice.author}</td>
									<td>${advice.viewCount}</td>
									<td><fmt:formatDate value="${advice.createTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${advice.updateTime}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<c:if test="${sessionScope.GLOBALAPPUSER.userType==0}">
										<td><a
											href="advice-manage!addAdvice?flag=1&adviceId=${advice.id}">编辑</a>
											| <a href="advice-manage!deleteAdvice?adviceId=${advice.id}">删除</a>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<tfoot>
						<c:if test="${totalCount < 1}">
							<tr>
								<td colspan="13">
									<div class="pagination"></div></td>
							</tr>
						</c:if>

						<c:if test="${totalCount > 0}">
							<td colspan="13">
								<div class="pagination">
									<form action="advice-manage!findAllAdvice" name="formpage"
										method="post">
										<a>共有 <b>${totalCount}条/${pageCount}页</b> </a> <a
											class="number"
											<c:if test="${currentPage > 1}">href="advice-manage!findAllAdvice?author=${author}&currentPage=${currentPage-1}"</c:if>>上一页</a>
										<c:forEach var="pagenum" items="${pageNum}" varStatus="status">
											<c:if test="${pagenum==currentPage}">
												<a class="number current">${pagenum}</a>
											</c:if>
											<c:if test="${pagenum!=currentPage}">
												<a class="number"
													href="advice-manage!findAllAdvice?author=${author}">${pagenum}</a>
											</c:if>
										</c:forEach>
										<a class="number"
											<c:if test="${page.currentPage < pageCount}">href="advice-manage!findAllAdvice?author=${author}&currentPage=${currentPage+1}"</c:if>>下一页</a>
										<input name="" value="" type="hidden"> <input
											name="currentPage" value="${currentPage}" size="3"
											style="height:21px;line-height:21px;" type="text"> <input
											value="跳 转" class="button" type="submit">
									</form>
								</div>
							</td>
						</c:if>
					</tfoot>
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
	<script>
		function delchecks(url) {
			var id = new Array();
			var m = 0;
			for ( var i = 0; i < document.getElementsByName("checkbox").length; i++) {
				if (document.getElementsByName("checkbox")[i].checked) {
					id[m] = document.getElementsByName("checkbox")[i].value;
					m = m + 1;
				}
			}
			if (m == 0) {
				alert("请选择一个需要删除的公告");
				return;
			}

			if (!confirm('是否删除选中公告')) {
				return;
			}
			location.href = url + id;
		}
	</script>
</body>
</html>