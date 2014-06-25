<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
<link rel="stylesheet" href="${ctx}/static/plugins/zTree_v3/css/zTreeStyle.css" type="text/css" media="screen"/>
<link href="${ctx}/static/images/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/static/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/static/css/invalid.css" type="text/css" media="screen" />

<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/zTree_v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/scripts/facebox.js"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
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
     <sitemesh:body/>
    </div>
</div>
</body>
</html>