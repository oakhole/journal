<%@page import="com.oakhole.systemManage.model.AppUser"%>
<%@page import="com.oakhole.common.util.constants.GlobalConstants"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	AppUser appUser = (AppUser) request.getSession().getAttribute(
			GlobalConstants.GLOBALAPPUSER);

	if (appUser == null) {
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>短信运营管理平台</title>
<link rel="stylesheet" href="css/reset.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/index.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/invalid.css" type="text/css"
	media="screen">
<script type="text/javascript" src="js/jquery-1.js"></script>
<script language="Javascript" type="text/javascript"
	src="<%=basePath%>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/simpla.js"></script>

<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery_dialog.css">
<script type="text/javascript" src="js/jquery_dialog.js"></script>
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
<![endif]-->
<script>
	function logoutsys() { //用户退出登录
		if (!confirm('您确实要退出系统吗？')) {
			return;
		}
		location.href = 'system-manage/login-manage!logout';
	}
</script>
</head>

<body>
	<div class="body_header">
		<div class="header_left">
			<h1 class="header_logo">
				<a href="#"><img id="logo" src="images/logo.png" alt="短信通平台">
				</a>
			</h1>
		</div>
		<ul class="shortcut-buttons-set">
			<li><a class="shortcut-button" href="home.jsp"
				target="leftFrame"> <img src="images/pencil_48.png" alt="系统首页"
					title="系统首页"> </a>
			</li>
			<li><a class="shortcut-button"
				href="system-manage/system-manage!editPass" target="leftFrame">
					<img src="images/paper_content_pencil_48.png" alt="修改密码"
					title="修改密码"> </a>
			</li>
			<li><a class="shortcut-button"
				href="system-manage/advice-manage!findAllAdvice" target="leftFrame">
					<img src="images/image_add_48.png" alt="通知信息" title="通知信息"> </a>
			</li>
			<li><a class="shortcut-button" href="<%=basePath%>"> <img
					src="images/clock_48.png" alt="刷新系统" title="刷新系统"> </a>
			</li>
			<li><a class="shortcut-button" onclick="javascript:logoutsys();"
				rel="modal"> <img src="images/comment_48.png" alt="退出系统"
					title="退出系统"> </a>
			</li>
		</ul>
		<div id="profile-links">
			欢迎<a target="leftFrame"
				href="system-manage/system-manage!updateMe?appUserId=<%=appUser == null ? "" : appUser.getId()%>">${sessionScope.GLOBALAPPUSER.account}</a>使用本系统！
			<c:if test="${sessionScope.GLOBALAPPUSER.userType!=0}">
				当前余额<font id="balance">${sessionScope.GLOBALAPPUSER.balance}</font>条
			</c:if>
		</div>
	</div>

	<div id="body-wrapper">
		<div id="sidebar">
			<div id="sidebar-wrapper">
				<ul id="main-nav">

					<!-- 终端用户登陆 -->
					<c:if test="${sessionScope.GLOBALAPPUSER.userType==3}">
						<li><a target="leftFrame" class="nav-top-item"> 短信管理 </a>
							<ul style="display: none;">
								<li><a href="sms-manage/sms-send-manage!sendSms?isVar=0"
									target="leftFrame">发送短信</a>
								</li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=2"
									target="leftFrame">发送列表</a>
								</li>
								<li><a
									href="sms-manage/sms-receive-manage!findAllSmsReceive"
									target="leftFrame">收件箱</a>
								</li>
							</ul></li>

						<li><a class="nav-top-item"> 财务管理 </a>
							<ul style="display: none;">
								<li><a href="financial-manage/financial-manage!income"
									target="leftFrame">充值记录</a></li>
								<li><a href="financial-manage/financial-manage!withdraw"
									target="leftFrame">消费记录</a></li>
							</ul>
						</li>

						<li><a class="nav-top-item">系统管理 </a>
							<ul style="display: none;">
								<li><a target="leftFrame"
									href="system-manage/system-manage!editPass">修改密码</a>
								</li>
								<li><a target="leftFrame"
									href="system-manage/system-manage!updateMe?appUserId=<%=appUser == null ? "" : appUser.getId()%>">修改信息</a>
								</li>
								<li><a
									href="system-manage/system-log-manage!findAllSystemLog"
									target="leftFrame">日志管理</a></li>
								<li><a target="_self"
									href="system-manage/login-manage!logout">安全退出</a>
								</li>
							</ul></li>
					</c:if>

					<!-- 普通用户登陆 -->
					<c:if test="${sessionScope.GLOBALAPPUSER.userType==2}">
						<li><a target="leftFrame" class="nav-top-item"> 短信管理 </a>
							<ul style="display: none;">
								<li><a href="sms-manage/sms-send-manage!sendSms?isVar=0"
									target="leftFrame">发送短信</a>
								</li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=0"
									target="leftFrame">等待列表</a></li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=1"
									target="leftFrame">正在发送</a>
								</li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=3"
									target="leftFrame">失败驳回</a></li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=2"
									target="leftFrame">发送列表</a>
								</li>
								<li><a href="sms-manage/sms-send-manage!sendSms?isVar=1"
									target="leftFrame">变量短信</a>
								</li>
								<li><a
									href="sms-manage/sms-receive-manage!findAllSmsReceive"
									target="leftFrame">收件箱</a>
								</li>
							</ul></li>

						<li><a class="nav-top-item"> 财务管理 </a>
							<ul style="display: none;">
								<li><a href="financial-manage/financial-manage!income"
									target="leftFrame">充值记录</a></li>
								<li><a href="financial-manage/financial-manage!withdraw"
									target="leftFrame">消费记录</a></li>
							</ul>
						</li>

						<li><a class="nav-top-item">系统管理 </a>
							<ul style="display: none;">
								<li><a target="leftFrame"
									href="system-manage/system-manage!editPass">修改密码</a>
								</li>
								<li><a target="leftFrame"
									href="system-manage/system-manage!updateMe?appUserId=<%=appUser == null ? "" : appUser.getId()%>">修改信息</a>
								</li>
								<li><a
									href="system-manage/system-log-manage!findAllSystemLog"
									target="leftFrame">日志管理</a></li>
								<li><a target="_self"
									href="system-manage/login-manage!logout">安全退出</a>
								</li>
							</ul></li>
					</c:if>
					<!-- 代理商登陆 -->
					<c:if test="${sessionScope.GLOBALAPPUSER.userType==1}">
						<li><a target="leftFrame" class="nav-top-item"> 短信管理 </a>
							<ul style="display: none;">
								<li><a href="sms-manage/sms-send-manage!sendSms?isVar=0"
									target="leftFrame">发送短信</a>
								</li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=0"
									target="leftFrame">等待列表</a>
								</li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=1"
									target="leftFrame">正在发送</a>
								</li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=3"
									target="leftFrame">失败驳回</a></li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=2"
									target="leftFrame">发送列表</a>
								</li>
								<li><a href="sms-manage/sms-send-manage!sendSms?isVar=1"
									target="leftFrame">变量短信</a>
								</li>
								<li><a
									href="sms-manage/sms-receive-manage!findAllSmsReceive"
									target="leftFrame">收件箱</a>
								</li>
							</ul></li>


						<li><a class="nav-top-item"> 财务管理 </a>
							<ul style="display: none;">
								<li><a href="financial-manage/financial-manage!income"
									target="leftFrame">充值记录</a></li>
								<li><a href="financial-manage/financial-manage!withdraw"
									target="leftFrame">消费记录</a></li>
							</ul>
						</li>
						<!-- <li><a class="nav-top-item">数据分流</a>
							<ul style="display: none;">
								<li><a
									href="system-manage/employee-manage!findAllEmployee?isCut=1"
									target="leftFrame">用户列表</a>
								</li>
								<li><a
									href="send-statistics/sms-statistics!findAllSmsStatistics?isCut=1"
									target="leftFrame">数据统计</a>
								</li>
							</ul></li> -->


						<li><a class="nav-top-item">客户管理 </a>
							<ul style="display: none;">
								<li><a target="leftFrame"
									href="system-manage/employee-manage!addEmployee?flag=0">添加客户</a>
								</li>
								<li><a target="leftFrame"
									href="system-manage/employee-manage!findAllEmployee">客户列表</a></li>
								<li><a target="leftFrame"
									href="send-statistics/sms-statistics!findAllSmsStatistics">发送统计</a>
								</li>
								<li><a href="system-manage/system-manage!whiteList"
									target="leftFrame">白名单管理</a></li>
							</ul></li>

						<li><a class="nav-top-item">系统管理 </a>
							<ul style="display: none;">
								<li><a target="leftFrame"
									href="system-manage/system-manage!editPass">修改密码</a>
								</li>
								<li><a target="leftFrame"
									href="system-manage/system-manage!updateMe?appUserId=<%=appUser == null ? "" : appUser.getId()%>">修改信息</a>
								</li>
								<li><a href="system-manage/advice-manage!findAllAdvice"
									target="leftFrame">公告管理</a>
								</li>
								<li><a
									href="system-manage/system-log-manage!findAllSystemLog"
									target="leftFrame">日志管理</a></li>
								<li><a target="_self"
									href="system-manage/login-manage!logout">安全退出</a>
								</li>
							</ul></li>
					</c:if>
					<!-- 后台管理员登陆 -->
					<c:if test="${sessionScope.GLOBALAPPUSER.userType==0}">
						<li><a target="leftFrame" class="nav-top-item"> 短信管理 </a>


							<ul style="display: none;">
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=0"
									target="leftFrame">等待列表</a>
								</li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=1"
									target="leftFrame">正在发送</a>
								</li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=3"
									target="leftFrame">失败列表</a></li>
								<li><a
									href="sms-manage/sms-send-manage!findAllSmsSend?sendStatus=2"
									target="leftFrame">发送列表</a></li>
							</ul></li>

						<li><a class="nav-top-item">通道管理 </a>
							<ul style="display: none;">
								<li><a
									href="system-manage/channel-manage!addChannel?flag=0"
									target="leftFrame">新增通道</a></li>
								<li><a href="system-manage/channel-manage!findAllChannel"
									target="leftFrame">通道管理</a></li>
								<li><a
									href="system-manage/channel-manage!addChannelGroup?flag=0"
									target="leftFrame">新增通道组</a></li>
								<li><a
									href="system-manage/channel-manage!findAllChannelGroup"
									target="leftFrame">通道组管理</a></li>
							</ul>
						</li>

						<!-- <li><a class="nav-top-item">分流管理</a>
							<ul style="display: none;">
								<li><a href="system-manage/system-manage!systemSetting"
									target="leftFrame">分流规则</a></li>
								<li><a
									href="system-manage/employee-manage!findAllEmployee?isCut=1"
									target="leftFrame">用户列表</a>
								</li>
								<li><a
									href="send-statistics/sms-statistics!findAllSmsStatistics"
									target="leftFrame">数据统计</a></li>
							</ul>
						</li> -->


						<li><a class="nav-top-item">客户管理 </a>
							<ul style="display: none;">
								<li><a target="leftFrame"
									href="system-manage/employee-manage!addEmployee?flag=0">添加客户</a>
								</li>
								<li><a target="leftFrame"
									href="system-manage/employee-manage!findAllEmployee">客户列表</a></li>
								<li><a target="leftFrame"
									href="send-statistics/sms-statistics!findAllSmsStatistics">发送统计</a>
								</li>
								<li><a href="financial-manage/financial-manage!income"
									target="leftFrame">充值记录</a></li>
								<li><a href="financial-manage/financial-manage!withdraw"
									target="leftFrame">消费记录</a></li>
							</ul></li>

						<li><a class="nav-top-item">系统管理 </a>
							<ul style="display: none;">
								<li><a target="leftFrame"
									href="system-manage/system-manage!editPass">修改密码</a>
								</li>
								<li><a target="leftFrame"
									href="system-manage/system-manage!updateMe?appUserId=<%=appUser == null ? "" : appUser.getId()%>">修改信息</a>
								</li>
								<li><a href="system-manage/badwords-manage!findAllBadwords"
									target="leftFrame">屏蔽词管理</a>
								</li>
								<li><a href="system-manage/system-manage!whiteList"
									target="leftFrame">白名单管理</a>
								</li>
								<li><a href="system-manage/advice-manage!findAllAdvice"
									target="leftFrame">公告管理</a>
								</li>
								<li><a
									href="system-manage/system-log-manage!findAllSystemLog"
									target="leftFrame">日志管理</a></li>
								<li><a target="_self"
									href="system-manage/login-manage!logout">安全退出</a>
								</li>
							</ul></li>
					</c:if>
				</ul>
			</div>
		</div>
		<script type="text/javascript">
	    function SetCwinHeight(divid) {
	        var cwin = document.getElementById(divid);
	        if (document.getElementById) {
	            if (cwin && !window.opera) {
	                if (cwin.contentDocument && cwin.contentDocument.body.offsetHeight) {
	                    cwin.height = cwin.contentDocument.body.offsetHeight;
	                }
	                else if (cwin.Document && cwin.Document.body.scrollHeight) {
	                    cwin.height = cwin.Document.body.scrollHeight;
	                }
	            }
	        }
	    }
	</script>
		<div id="main-content">
			<div class="clear">
				<marquee id="scrollArea" height="20px" width="100%" loop="-1"
					scrollamount="3" scrolldelay="50" direction="left"
					onMouseOver=scrollArea.stop() onMouseOut=scrollArea.start()
					vspace="30px">
					<a href="#">${advice_content}</a>
				</marquee>
			</div>
			<iframe name="leftFrame" src="home.jsp"
				style="VISIBILITY: inherit; WIDTH: 100%; Z-INDEX: 5;border:none"
				id="uBox" onload="Javascript:SetCwinHeight('uBox')" scrolling="no"
				frameborder="0" height="97%"></iframe>
		</div>
		<script type="text/javascript">
		
	    function setPhone(phone) {
	        if (document.all) {//IE
	            document.frames["leftFrame"].setPhone(phone);
	        } else {//Firefox
	            document.getElementById("uBox").contentWindow.setPhone(phone);
	        }
	        JqueryDialog.Close();
	    }
	    
	    function selectUser(users) {
	        if (document.all) {//IE
	            document.frames["leftFrame"].selectUser(users);
	        } else {//Firefox
	            document.getElementById("uBox").contentWindow.selectUser(users);
	        }
	        JqueryDialog.Close();
	    }
	    function locationframehref(url) {
	        window.frames['leftFrame'].location = url;
	        JqueryDialog.Close();
	    }
	    function setMmsPic(picurl, id) {
	        if (document.all) {//IE
	            document.frames["leftFrame"].setPicUrl(picurl, id);
	        } else {//Firefox
	            document.getElementById("uBox").contentWindow.setPicUrl(picurl, id);
	        }
	        JqueryDialog.Close();
	        
	    }
	    function showupload(type,id, name, size) {
	        if (document.all) {//IE
	            document.frames["leftFrame"].showupload(type, id, name, size);
	        } else {//Firefox
	        document.getElementById("uBox").contentWindow.showupload(type, id, name, size);
	        }
	        JqueryDialog.Close();
	    }
	</script>
</body>
</html>