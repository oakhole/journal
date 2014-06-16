<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404 - oakhole.com</title>

<style type="text/css">
@import url("<%=basePath%>css/stylesheet.css");
</style>
<link href="<%=basePath%>css/blue.css" rel="stylesheet" type="text/css" />

</head>
<body>

	<!-- Warp around everything -->
	<div id="warp">


		<!-- Header top -->
		<div id="header_top"></div>
		<!-- End header top -->


		<!-- Header -->
		<div id="header">
			<h2>没有找到该页面</h2>
			<h5>当您看到这个页面时,请不要着急,我们技术正在处理……</h5>
		</div>
		<!-- End Header -->


		<!-- The content div -->
		<div id="content">

			<!-- text -->
			<div id="text">
				<!-- The info text -->
				<p>Sorry, Evidently the document you were looking for has either
					been moved or no longer exists. Please use the navigational links
					to the right to locate additional resources and information.</p>
				<br />
				<h3>Lost? We suggest...</h3>
				<!-- End info text -->

				<!-- Page links -->
				<ul>
					<li><a href="#">&raquo; Home</a>
					</li>
					<li><a href="#">&raquo; Portfolio</a>
					</li>
					<li><a href="#">&raquo; About</a>
					</li>
					<li><a href="#">&raquo; Contact</a>
					</li>
				</ul>
				<!-- End page links -->
			</div>
			<!-- End info text -->


			<!-- Book icon -->
			<img id="book" src="<%=basePath%>images/img-01.png" alt="Book iCon" />
			<!-- End Book icon -->

			<div style="clear:both;"></div>
		</div>
		<!-- End Content -->


		<!-- Footer bottom -->
		<div id="footer_bottom"></div>
		<!-- End Footer bottom -->


		<div style="clear:both;"></div>


	</div>
	<!-- End Warp around everything -->

</body>
</html>