<%@page import="com.oakhole.systemManage.model.Channel"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<%
	List<Channel> allChannel = (List<Channel>)request.getAttribute("allChannel");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统首页</title>
<link rel="stylesheet" href="<%=basePath%>/css/reset.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/style.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/invalid.css"
	type="text/css" media="screen">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/simpla.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/jquery_dialog.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery_dialog.js"></script>
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
</head>
<body>

	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">通道管理</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div class="default-tab">
				<table>
					<thead>
						<tr>
							<th style="padding-left:5px;border-left:1px solid #C1DAD7">编号</th>
							<th>名称</th>
							<th>余额</th>
							<th>分包</th>
							<th>类型</th>
							<th>运营商</th>
							<th>日流量限制</th>
							<th>状态</th>
							<th>描述</th>
							<th>回复</th>
							<th>长短信</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<%
							if(allChannel != null && allChannel.size() > 0){
																																																															for(Channel channel : allChannel){
						%>
						<tr class="alt-row">
							<td style="padding-left:5px;"><%=channel.getId()%></td>
							<td><%=channel.getName()%></td>
							<td><%=channel.getBalance()%></td>
							<td><%=channel.getPerpack()%></td>
							<td>
								<%
									if(channel.getChannelType()==0){
								%>短信<%
									}else{
								%>彩信<%
									}
								%>
							</td>
							<td>
								<%
									if(channel.getOperators()==0){
								%> 全网 <%
									}else if(channel.getOperators() ==1){
								%> 移动 <%
									}else if(channel.getOperators() ==2){
								%> 联通 <%
									}else if(channel.getOperators() ==3){
								%> 电信 <%
									}
								%>
							</td>
							<td><%=channel.getDayFlow()==0?"未启用":channel.getDayFlow()%></td>
							<%
								if(channel.getStatus()==0) {
							%><td><img src="<%=basePath%>/images/red.gif" alt="链路故障"
								title="链路故障" border="0" width="16" height="16">
							</td>
							<%
								}
							%>
							<%
								if(channel.getStatus()==1) {
							%><td><img src="<%=basePath%>/images/yellow.gif" alt="余额告警"
								title="余额告警" border="0" width="16" height="16">
							</td>
							<%
								}
							%>
							<%
								if(channel.getStatus()==2) {
							%><td><img src="<%=basePath%>/images/green.gif" alt="正常"
								title="正常" border="0" width="16" height="16">
							</td>
							<%
								}
							%>
							<%
								if(channel.getStatus()==3) {
							%><td><img src="<%=basePath%>/images/black.gif" alt="人工停用"
								title="人工停用" border="0" width="16" height="16">
							</td>
							<%
								}
							%>
							<td><%=channel.getDescription()%></td>
							<%
								if(channel.getCallback() == 1){
							%>
							<td>支持</td>
							<%
								}else{
							%>
							<td>不支持</td>
							<%
								}
							%>
							<%
								if(channel.getLongSms() == 1){
							%>
							<td>支持</td>
							<%
								}else{
							%>
							<td>不支持</td>
							<%
								}
							%>
							<td><a
								href="channel-manage!channelControl?channelId=<%=channel.getId()%>&status=3">停用</a>
								|<a
								href="channel-manage!channelControl?channelId=<%=channel.getId()%>&status=2">正常</a>
								|<a
								href="channel-manage!channelControl?channelId=<%=channel.getId()%>&status=0">故障</a>
								|<a
								href="channel-manage!channelControl?channelId=<%=channel.getId()%>&status=1">繁忙</a>
								|<a
								href="channel-manage!addChannel?flag=1&channelId=<%=channel.getId()%>">编辑</a>
								|<a href="#" onclick="deleteinterface(<%=channel.getId()%>)">删除</a>
								| <input name="s2" class="button" value="测试通道"
								onclick="send('<%=channel.getName()%>测试','<%=channel.getId()%>','<%=channel.getName() %>','${sessionScope.account }','${sessionScope.password }','<%=channel.getStatus() %>','<%=channel.getChannelType() %>')"
								type="button">
							</td>
						</tr>
						<%
							}}else{
						%>
						<tr class="alt-row">
							<td style="padding-left:5px;" colspan="14">暂无通道</td>
						</tr>
						<%
							}
						%>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="14">
								<div id="pages"
									style="text-align:center;margin-top:10px; margin-bottom:5px;">
									<img src="<%=basePath%>/images/red.gif" alt="链路故障" title="链路故障"
										border="0" width="16" height="16">链路故障 &nbsp;&nbsp;<img
										src="<%=basePath%>/images/yellow.gif" alt="余额告警" title="余额告警"
										border="0" width="16" height="16">余额告警 &nbsp;&nbsp;<img
										src="<%=basePath%>/images/green.gif" alt="正常" title="正常"
										border="0" width="16" height="16">通道正常 &nbsp;&nbsp;<img
										src="<%=basePath%>/images/black.gif" alt="人工停用" title="人工停用"
										border="0" width="16" height="16">人工停用 &nbsp;&nbsp;<span
										style="color:green">只有通道正常（绿色）时，才能发送短信或彩信</span>
								</div></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<script>
	parent.SetCwinHeight('uBox');
	function tip() {
		var r = confirm("你确认现在要切换模式么？在切换前请先检查任务列表！");
		if (r == true) {
			return true;
		}
		else {
			return false;
		}
	}
	function deleteinterface(interfaceid){
	    var flag = window.confirm("是否删除该通道？如果有待发送内容使用该通道，将受到影响");
	    if(flag){
	        window.location.href="channel-manage!delChannel?channelId="+interfaceid;
	    }
	}
	function send(title, id,interfacename, username, password, status,interfacetype) {
		if (status == '2') {
		    JqueryDialog.Open(title, 'testSend.aspx?interfaceid=' + id + '&interfacename=' + interfacename + '&username=' + username + '&password=' + password + '&interfacetype=' + interfacetype, 500, 290);
		}
		else {
			alert("停用的通道不能测试");
		}
    }
    function setpriority(title, interfaceid) {
        JqueryDialog.Open(title, 'setinterfacepriority.aspx?interfaceid=' + interfaceid, 300, 80);
    }
</script>
</body>
</html>