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
<link rel="stylesheet" href="${ctx}/static/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/static/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/static/css/invalid.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/static/zTree_v3/css/zTreeStyle.css" type="text/css" media="screen"/>

<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/zTree_v3/js/jquery.ztree.all-3.5.min.js"></script>
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
<script>
    $(function(){

        $("#trigger_profile").click(function(e){
            e.preventDefault();
            $("#profile").trigger("click");
        });

        $("#trigger_setting").click(function(e){
            e.preventDefault();
            $("#user").trigger("click");
        });

        $.get("${ctx}/menu/current",function(data){
           initMenu(data,data.length-1,0);
           $("#main-nav").append(menuHtml);
           $("#main-nav li ul").hide();
           //设定当前选中菜单
           $("#main-nav li a.nav-top-item").removeClass("current");
           $("#user").addClass("current");
           $("#user").parent().parent().parent().find("a.nav-top-item").addClass("current");
           $("#user").parent().parent().toggle();

           $("#main-nav li a.nav-top-item").on("click",function () {
                $(this).parent().siblings().find("a").removeClass('current');
                $(this).addClass("current");
                $(this).next().removeClass("current");
                $(this).parent().siblings().find("ul").slideUp("normal"); // Slide up all sub menus except the one clicked
                $(this).next().slideToggle("normal"); // Slide down the clicked sub menu
                return false;
            }
        );
        $("#main-nav li .nav-top-item").hover(
                    function () {
                        $(this).stop().animate({ paddingRight: "25px" }, 200);
                    },
                    function () {
                        $(this).stop().animate({ paddingRight: "15px" });
                    }
        );
        });
    });
    var menuHtml = "";
    //初始化菜单
    function initMenu(nodes,length,pid){
        //递归终止
        if(length == -1){
            return false;
        }
        if(nodes[length]["pId"] == pid){
            if(nodes[length]["hasChild"]){
                //判断是否有子节点,有则进入递归,退出循环
                menuHtml = menuHtml + "<li><a class='nav-top-item' href='#'>" + nodes[length]["name"] + "</a>";
                menuHtml = menuHtml + "<ul>";
                initMenu(nodes,nodes.length-1,nodes[length]["id"]);
                menuHtml = menuHtml + "</ul>";
            }else{
                menuHtml = menuHtml + "<li><a href='${ctx}/"+ nodes[length]["url"] +"' id="+ nodes[length]["url"] +">" + nodes[length]["name"] + "</a>";
            }
            menuHtml = menuHtml + "</li>";
        }
        initMenu(nodes,length-1,pid);
    }
</script>
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
<div id="messages" style="display: none">
            <h3>3 Messages</h3>
        </div> <!-- End #messages -->
</body>
</html>