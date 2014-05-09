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
				你好, <a href="#" title="个人信息" id="trigger_profile"><shiro:principal property="name"/></a>, 你有 <a href="#messages" rel="modal" title="${message} 条信息">3 条信息</a><br />
				<br />
				<a href="#" title="设置" id="trigger_setting">设置</a> | <a href="${ctx}/logout" title="退出">退出</a>
			</div>
			<ul id="main-nav">  <!-- Accordion Menu -->
				<li>
                    <a class="nav-top-item no-submenu current" href="${ctx}/"> <!-- Add the class "no-submenu" to menu items with no sub menu -->
                        首页
                    </a>
                </li>
				<c:forEach var="menu" items="${menuList}">
                    <c:if test="${menu.parent == null}">
                        <li>
                            <c:if test="${fn:length(menu.childList) > 0}">
                                <a class="nav-top-item" href="#">
                            </c:if>
                            <c:if test="${fn:length(menu.childList) <= 0}">
                                <a class="nav-top-item no-submenu" href="${ctx}/${menu.url}">
                            </c:if>
                                ${menu.name}
                            </a>
                            <c:if test="${fn:length(menu.childList) > 0}">
                            <ul>
                                <c:forEach var="subMenu" items="${menu.childList}">
                                <li>
                                    <a href="${ctx}/${subMenu.url}" id="${subMenu.url}">
                                        ${subMenu.name}
                                    </a>
                                </li>
                                </c:forEach>
                                </ul>
                            </c:if>
                        </li>
                    </c:if>
				</c:forEach>
			</ul> <!-- End #main-nav -->
		</div></div> <!-- End #sidebar -->
