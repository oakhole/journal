<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>Admin-<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link rel="stylesheet" href="${ctx}/static/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/static/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/static/css/invalid.css" type="text/css" media="screen" />

<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<!--<script type="text/javascript" src="${ctx}/static/scripts/jquery-1.3.2.min.js"></script>-->
<script type="text/javascript" src="${ctx}/static/scripts/facebox.js"></script>
<!--[if IE]><script type="text/javascript" src="${ctx}/static/scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="${ctx}/static/scripts/DD_belatedPNG_0.0.7a.js"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('.png_bg, img, li');
    </script>
<![endif]-->
<script type="text/javascript" src="${ctx}/static/scripts/simpla.jquery.configuration.js"></script>
<sitemesh:head/>
</head>
<body>
<div id="body-wrapper">
    <%@ include file="/WEB-INF/layouts/sider.jsp"%>
    <div id="main-content">
        <noscript> <!-- Show a notification if the user has disabled javascript -->
            <div class="notification error png_bg">
                <div>
                    Javascript is disabled or is not supported by your browser. Please <a href="http://browsehappy.com/" title="Upgrade to a better browser">upgrade</a> your browser or <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
                </div>
            </div>
        </noscript>
        <sitemesh:body/>
        <%@ include file="/WEB-INF/layouts/footer.jsp"%>
    </div>
</div>

</body>
</html>