<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<%@ include file="taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <title>综合短信管理系统</title>

        <%@ include file="meta.jsp"%>

        <%@ include file="css.jsp"%>

        <!--[if IE]>
            <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <sitemesh:head/>

        <%@ include file="scripts.jsp"%>
    </head>

    <body>

        <%@ include file="header.jsp"%>

        <%@ include file="nav.jsp"%>

        <sitemesh:body/>

        <%@ include file="footer.jsp"%>

    </body>
</html>