<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统首页</title>
<link rel="stylesheet" href="<%=basePath%>/css/mmsreset.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/style.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/invalid.css"
	type="text/css" media="screen">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/simpla.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/formValidator_alert.js"></script>
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
<style>
.tip {
	color: #BBB;
}
</style>
</head>
<body>
	<div class="content-box role">
		<div class="content-box-header">
			<h3 style="cursor: s-resize;">
				新增通道&nbsp;&nbsp;[<a href="channel-manage!findAllChannel">返回列表</a>]
			</h3>
			<div class="clear"></div>
		</div>
		<div class="content-box-content">
			<div style="display: block;" class="tab-content default-tab"
				id="form">
				<form method="post" action="channel-manage!saveChannel?flag=0"
					name="clientForm" onsubmit="return checksub();" id="clientForm">
					<table border="1" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr class="alt-row">
								<td class="left" align="right" width="20%" height="25">
									通道名称 ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									id="interfaceName" style="width:200px" name="name"
									maxlength="30" minlength="2" nullable="false" errmsg="通道名称"
									valuetype="STRING" type="text"><font color="red">*</font>
								</td>

							</tr>
							<tr>
								<td class="left" align="right" width="20%" height="25">总额 ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									id="point" style="width:125px" name="balance" value="0"
									maxlength="10" minlength="1" nullable="true" errmsg="总额"
									valuetype="INTEGER" type="text"> <span class="tip">可不配置，仅仅用做显示</span>
								</td>
							</tr>

							<tr class="alt-row">
								<td class="left" align="right" width="20%" height="25">
									通道类型 ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									name="channelType" value="0" checked="checked" type="radio">短信

									<input type="radio" name="channelType" value="1">彩信</td>
							</tr>
							<tr>
								<td class="left" align="right" width="20%" height="25">
									运营商类型 ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									name="operators" value="0" checked="checked" type="radio">全网
									<input name="operators" value="1" type="radio">移动 <input
									name="operators" value="2" type="radio">联通 <input
									name="operators" value="3" type="radio">电信CDMA</td>
							</tr>
							<!-- <tr class="alt-row">
								<td class="left" align="right" width="20%" height="25">
									付费方式 ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									name="payinfo" value="0" checked="checked" type="radio">预付费
									<input name="payinfo" value="1" type="radio">后付费</td>
							</tr> -->
							<tr>
								<td class="left" align="right" width="20%" height="25">单价 ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									id="price" style="width:50px" name="price" value="1"
									maxlength="10" minlength="1" nullable="false" errmsg="单价"
									valuetype="FLOAT" type="text">分 <span class="tip">通道余额返回值为条数的时候，这里请设置为1分，否则设置为实际单价</span>
								</td>
							</tr>
							<tr>
								<td class="left" align="right" width="20%" height="25">
									计费字数 ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									id="losm" style="width:50px" name="perSmsWords" value="70"
									maxlength="3" minlength="1" nullable="false" errmsg="计费字数"
									valuetype="INTEGER" type="text"><font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td class="left" align="right" width="20%" height="25">
									最大字数 ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									id="losm" style="width:50px" name="maxSmsWords" value="70"
									maxlength="3" minlength="1" nullable="false" errmsg="最大字数"
									valuetype="INTEGER" type="text"><font color="red">*</font>
								</td>
							</tr>
							<tr class="alt-row">
								<td class="left" align="right" width="20%" height="25">长短信
									：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									name="longsms" value="1" checked="checked" type="checkbox">支持
								</td>
							</tr>

							<tr>
								<td class="left" align="right" width="20%" height="25">
									回复(上行) ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									id="checkbox" name="callback" value="1" checked="checked"
									type="checkbox">接收</td>
							</tr>

							<tr class="alt-row">
								<td class="left" align="right" width="20%" height="25">分包量
									：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									id="maximum" style="width:50px" name="perpack" maxlength="10"
									minlength="1" nullable="true" errmsg="流量" valuetype="INTEGER"
									type="text" value="5000"><font color="red">*</font>条/包
									<br>每个包的数据量</td>
							</tr>
							<tr>
								<td class="left" align="right" width="20%" height="25">
									日流量限制 ：</td>
								<td colspan="2" align="left" width="*" height="25"><input
									id="DailyThreshold" style="width:80px" name="dayFlow" value="0"
									maxlength="10" minlength="1" nullable="true" errmsg="日流量限制"
									valuetype="INTEGER" type="text" value="5000">条 <br>为0时不启用日流量限制。非0时，当日发送量达到该数值，则通道将暂停，不再提交短信


								</td>
							</tr>

							<tr class="alt-row">
								<td class="left" align="right" valign="top" width="20%">
									通道模板 ：</td>
								<td style="line-height:25px;" align="left" width="*" height="25">
									<select name="interfaceId" id="template"
									onchange="gettemplate(this.value);"
									style="line-height:30px;height:24px;padding-top:3px;padding-bottom:3px;">
										<option selected="selected" value="0">请选择通道模板</option>
										<c:forEach var="inter" items="${allInterface}">
											<option value="${inter.id}">${inter.name}</option>
										</c:forEach>
								</select>
								</td>
							</tr>

							<tr class="alt-row">
								<td class="left" align="right" width="20%" height="25">接口参数
									：</td>
								<td align="left" width="*" height="25"><input id="remark"
									style="width:400px" name="interfaceParameters" maxlength="1000"
									minlength="0" nullable="true" errmsg="备注" valuetype="STRING"
									type="text"><font color="red">*</font>参数后用 " , "逗号隔开 
								</td>
							</tr>

							<tr class="alt-row">
								<td class="left" align="right" width="20%" height="25">备注 ：</td>
								<td align="left" width="*" height="25"><input id="remark"
									style="width:200px" name="description" maxlength="1000"
									minlength="0" nullable="true" errmsg="备注" valuetype="STRING"
									type="text">
								</td>
							</tr>
							<tr>
								<td align="right" width="20%" height="25"></td>
								<td colspan="2" height="25"><div align="left">
										<input name="btnAdd" id="btnAdd" class="button" value=" 提 交 "
											type="submit"> <input name="reset" id="Submit1"
											class="button" value=" 重 置 " onclick="resetalls();"
											type="reset"> <input name="back" id="back"
												onclick="history.back();" class="button" value=" 返 回 "
												type="button">
									</div></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<br> <br> <br> <br> <br> <br> <br> <br>
									<script type="text/javascript">
										function resetalls() {
											document.getElementById(
													"clientForm").reset();
										}

										function checksub() {
											var interfaceName = document
													.getElementById("interfaceName").value;
											if (interfaceName == "") {
												alert("接口名称不能为空！");
												return false;
											}
											var price = document
													.getElementById("price").value;
											if (price != "") {
												if (isNaN(price)) {
													alert("单价必须为数字！");
													return false;
												}
											}

											var losm = document
													.getElementById("losm").value;
											if (losm == "") {
												alert("通道计费字数不能为空！");
												return false;
											}

											if (isNaN(losm)) {
												alert("通道计费字数必须为数字！");
												return false;
											}

											var maximum = document
													.getElementById("maximum").value;
											if (maximum == "") {
												alert("每次最大门限不能为空！");
												return false;
											}

											if (isNaN(maximum)) {
												alert("每次最大门限必须为数字！");
												return false;
											}

											var Threshold = document
													.getElementById("Threshold").value;
											if (Threshold == "") {
												alert("每秒最大门限不能为空！");
												return false;
											}

											if (isNaN(Threshold)) {
												alert("每秒最大门限必须为数字！");
												return false;
											}

											var DailyThreshold = document
													.getElementById("DailyThreshold").value;
											if (DailyThreshold == "") {
												alert("日流量限制不能为空！");
												return false;
											}

											if (isNaN(DailyThreshold)) {
												alert("日流量限制必须为数字！不启用请设置为0");
												return false;
											}

											var template = document
													.getElementById("template").value;
											if (template == "") {
												alert("通道模板不能为空！");
												return false;
											}

											return true;

										}
										var http_requestres;
										function gettemplate(val) {
											http_requestres = false;
											if (window.XMLHttpRequest) {
												http_requestres = new XMLHttpRequest();
												if (http_requestres.overrideMimeType) {
													http_requestres
															.overrideMimeType('text/xml');
												}
											} else if (window.ActiveXObject) {
												try {
													http_requestres = new ActiveXObject(
															'Msxml2.XMLHTTP');
												} catch (e) {
													try {
														http_requestres = new ActiveXObject(
																'Microsoft.XMLHTTP');
													} catch (e) {
													}
												}
											}

											http_requestres.onreadystatechange = alertContentsRes;
											http_requestres.open('GET',
													'http://112.2.34.104:8001/getinterfacetemplate.aspx?template='
															+ val, true);
											http_requestres.send(null);
										}
										function alertContentsRes() {
											if (http_requestres.readyState == 4) {
												if (http_requestres.status == 200) {
													try {
														//document.getElementById("showtemplate").innerHTML = http_requestres.responseText;
														//eval(http_requestres.responseText);
														eval(http_requestres.responseText);
														//alert(http_requestres.responseText);
														parent
																.SetCwinHeight('uBox');
													} catch (e) {
														try {
															//alert(http_requestres.responseText);
															alert(e.description);
															//document.getElementById("")
														} catch (ex) {
														}
													}
												} else {
												}
												http_request2 = false;
											}
										}
									</script>
</body>
</html>
