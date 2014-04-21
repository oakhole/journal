<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div id="sidebar"><div id="sidebar-wrapper"> <!-- Sidebar with logo and menu -->

			<h1 id="sidebar-title"><a href="#">Simpla Admin</a></h1>

			<!-- Logo (221px wide) -->
			<a href="#"><img id="logo" src="${ctx}/static/images/logo.png" alt="Simpla Admin logo" /></a>

			<!-- Sidebar Profile links -->
			<div id="profile-links">
				你好, <a href="${ctx}/profile" title="个人信息"><shiro:principal property="name"/></a>, 你有 <a href="#messages" rel="modal" title="${message} 条信息">3 条信息</a><br />
				<br />
				<a href="${ctx}/profile" title="设置">设置</a> | <a href="${ctx}/logout" title="退出">退出</a>
			</div>
			<ul id="main-nav">  <!-- Accordion Menu -->
				<li>
					<a class="nav-top-item no-submenu" href="${ctx}/"> <!-- Add the class "no-submenu" to menu items with no sub menu -->
						首页
					</a>
				</li>
				<li>
					<a href="#" class="nav-top-item current" id="articles"> <!-- Add the class "current" to current menu item -->
					用户管理
					</a>
					<ul>
						<li><a href="${ctx}/register" id="articles">添加用户</a></li>
						<li><a href="${ctx}/user" id="manage_article" class="current">用户列表</a></li> <!-- Add class "current" to sub menu items also -->
						<li><a href="${ctx}/role">角色管理</a></li>
					</ul>
				</li>
				<li>
					<a href="#" class="nav-top-item">
						系统设置
					</a>
					<ul>
						<li><a href="#">通用设置</a></li>
						<li><a href="#">系统信息</a></li>
						<li><a href="${ctx}/profile">个人信息</a></li>
						<li><a href="#">日志记录</a></li>
					</ul>
				</li>
			</ul> <!-- End #main-nav -->
		</div></div> <!-- End #sidebar -->
