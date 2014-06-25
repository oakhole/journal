<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>短信运营管理平台</title>
<link rel="stylesheet" href="${ctx}/static/css/reset.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="${ctx}/static/css/index.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="${ctx}/static/css/invalid.css" type="text/css"
	media="screen">
<script type="text/javascript" src="${ctx}/static/js/jquery-1.js"></script>
<script language="Javascript" type="text/javascript"
	src="${ctx}/static//js/jquery.blockUI.js"></script>
<script type="text/javascript" src="${ctx}/static/js/simpla.js"></script>

<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/jquery_dialog.css">
<script type="text/javascript" src="js/jquery_dialog.js"></script>
<!--[if IE]><script type="text/javascript" src="${ctx}/static/scripts/jquery.bgiframe.js"></script><![endif]-->
<!--[if IE 6]>
    <script type="text/javascript" src="${ctx}/static/scripts/DD_belatedPNG_0.0.7a.js"></script>
<![endif]-->
<script>
	function logoutsys() { //用户退出登录
		if (!confirm('您确实要退出系统吗？')) {
			return;
		}
		location.href = '${ctx}/logout';
	}
</script>
</head>

<body>
	<div class="body_header">
		<div class="header_left">
			<h1 class="header_logo">
				<a href="#"><img id="logo" src="${ctx}/static/images/logo.png" alt="短信通平台">
				</a>
			</h1>
		</div>
		<ul class="shortcut-buttons-set">
			<li><a class="shortcut-button" href="${ctx}/dashboard"
				target="leftFrame"> <img src="${ctx}/static/images/pencil_48.png" alt="系统首页"
					title="系统首页"> </a>
			</li>
			<li><a class="shortcut-button"
				href="system-manage/system-manage!editPass" target="leftFrame">
					<img src="${ctx}/static/images/paper_content_pencil_48.png" alt="修改密码"
					title="修改密码"> </a>
			</li>
			<li><a class="shortcut-button"
				href="system-manage/advice-manage!findAllAdvice" target="leftFrame">
					<img src="${ctx}/static/images/image_add_48.png" alt="通知信息" title="通知信息"> </a>
			</li>
			<li><a class="shortcut-button" href="${ctx}/"> <img
					src="${ctx}/static/images/clock_48.png" alt="刷新系统" title="刷新系统"> </a>
			</li>
			<li><a class="shortcut-button" onclick="javascript:logoutsys();"
				rel="modal"> <img src="${ctx}/static/images/comment_48.png" alt="退出系统"
					title="退出系统"> </a>
			</li>
		</ul>
		<div id="profile-links">
			欢迎<a target="leftFrame"
				href="system-manage/system-manage!updateMe?appUserId="><shiro:principal property="name"/></a>
				使用本系统！
				当前余额<font id="balance">1000</font>条
		</div>
	</div>

	<div id="body-wrapper">
		<div id="sidebar">
			<div id="sidebar-wrapper">
				<ul id="main-nav">

					<!-- 终端用户登陆 -->
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
									href="system-manage/system-manage!updateMe?appUserId=">修改信息</a>
								</li>
								<li><a
									href="system-manage/system-log-manage!findAllSystemLog"
									target="leftFrame">日志管理</a></li>
								<li><a target="_self"
									href="system-manage/login-manage!logout">安全退出</a>
								</li>
							</ul></li>


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
			<iframe name="leftFrame" src="${ctx}/dashboard"
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