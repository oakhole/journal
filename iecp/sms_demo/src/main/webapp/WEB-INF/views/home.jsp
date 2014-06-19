<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<title>home</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen">	
<style>
.kjmenu{BORDER:#c0dcf2 1px solid; PADDING-BOTTOM:9px; MARGIN:0px 5px 2px 0px; PADDING-LEFT:11%; PADDING-RIGHT:0px; HEIGHT:373px !important;PADDING-TOP: 9px}
.newtitle{BORDER:#c0dcf2 1px solid;PADDING-BOTTOM:4px; background:url(images/maintitle.jpg); MARGIN:0px 5px 2px 0px; PADDING-LEFT:15px; PADDING-RIGHT:0px; HEIGHT:20px !important;PADDING-TOP: 4px;font-size:14px;line-height:20px;}
.newborder{BORDER:#c0dcf2 1px solid;PADDING-BOTTOM:9px; MARGIN:0px 5px 2px 0px; PADDING-LEFT:9px; PADDING-RIGHT:0px; HEIGHT:373px !important;PADDING-TOP: 9px}
#tishi_ p{margin-bottom:0px;margin-top:0px;line-height:28px;padding:0px;}
.newborder p{margin-bottom:0px;margin-top:0px;line-height:28px;padding:0px;}
.content-box-header h3{padding:2px 15px 2px;line-height:30px;margin:0;}
body { 
     overflow-x : hidden;
     overflow-y : hidden;
}
</style>
</head>
<body>
<div style="margin:0 auto; width:97%;">
  <img src="images/welecom.jpg">
</div>
<script language="javascript">
    function callmsg(id) {

        var obj = document.getElementById(id);

        if (obj.style.display == "") {
            obj.style.display = "none";
        }
        else {
            obj.style.display = "";
            parent.SetCwinHeight('uBox');
        }
    }
</script>

</body></html>