<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="taglibs.jsp"%>

<header class="wd">
    <div class="logo left">
        <img src="${ctx}/assets/img/logo.png" class="img-responsive">
    </div>
    <div class="setinfo right">
        您好，<shiro:principal property="loginName"/> | <a href="${ctx}/user/update/<shiro:principal
        property="id"/>">个人设置</a> |
         <a
        href="#">帮助中心</a> |
         <a href="#"
        data-toggle="modal"
        data-target="#logoutModal">退出</a>
    </div>
</header>