<%@page import="com.oakhole.smsManage.model.Sms"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统首页</title>
<link rel="stylesheet" href="<%=basePath%>/css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/invalid.css" type="text/css" media="screen">	
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/simpla.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery_dialog.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery_dialog.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=basePath%>/js/WdatePicker.js"></script><link type="text/css" rel="stylesheet" href="<%=basePath%>/css/WdatePicker.css">
<link href="<%=basePath%>/css/WdatePicker.css" rel="stylesheet" type="text/css">
<!--[if IE]><script type="text/javascript" src="scripts/jquery.bgiframe.js"></script><![endif]-->
<!-- Internet Explorer .png-fix -->
<!--[if IE 6]>
    <script type="text/javascript" src="scripts/DD_belatedPNG_0.0.7a.js"></script>
    
<![endif]-->
<script language="javascript">
    function excel() {
        var company = document.getElementById("company").value;
        var accountType = document.getElementById("accountType").value;
        var interfaceid = document.getElementById("interfaceid").value;
        var account = document.getElementById("account").value;
        var starttime = document.getElementById("starttime").value;
        var endtime = document.getElementById("endtime").value;
        location.href = "excel/taskstatexcel.aspx?action=downsmsday&company=" + company + "&accountType=" + accountType + "&interfaceid=" + interfaceid + "&account=" + account + "&starttime=" + starttime + "&endtime=" + endtime;
    }
</script></head>

<body>
<div class="content-box role">
	<div class="content-box-header">
		<h3 style="cursor: s-resize;">短信发送统计日报</h3>
		<div class="clear"></div>
	</div>
	<div class="content-box-content">
		<div class="searchgrid">
			<form method="post" action="parenttasksmsday.aspx" name="Form" id="Form1">

			<input id="company" style="width:145px" name="company" value="" type="hidden"> 
			<label>客户类型：</label><select name="accountType" id="accountType" style="width:90px;line-height:30px;height:24px;padding-top:1px;padding-bottom:3px;">
			    <option selected="selected" value="">全部客户</option>
				<option value="4">直接客户</option>
				<option value="5">间接客户</option>
			</select>
			
			<label>发送接口：</label><select name="interfaceid" id="interfaceid" style="width:150px;line-height:30px;height:24px;padding-top:1px;padding-bottom:3px;">
			    <option selected="selected" value="">全部</option><option value="0">N/A</option><option value="1">黑名单专用</option><option value="6">江苏MAS</option><option value="7">上海联通</option><option value="8">电信虚拟</option>
			</select>
			
			<label>客户账号：</label><input id="account" style="width:80px" name="account" type="text">
			<br>
			
			<label>起始时间：</label><input id="starttime" style="width:80px" name="starttime" value="2013-03-19" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" class="Wdate" type="text"> 
            <label>结束时间：</label><input id="endtime" style="width:80px" name="endtime" value="2013-03-19" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" class="Wdate" type="text"> 
            
			<input class="button" value="查询" type="submit">
			<input class="button" onclick="excel();" value=" 导出 " type="button">
			
			</form>
		</div>
		<div style="display: block;" class="tab-content default-tab">
			<table width="100%">
				<thead>
					<tr>
					   <th style="padding-left:5px;border-left:1px solid #C1DAD7">统计日期</th><th>客户账号</th><th>联系人</th><th>提交数(条)</th><th>计费数(条)</th><th>未知数(条)</th><th>成功数(条)</th><th>提交失败(条)</th><th>发送失败(条)</th><th>其他数量(条)</th>
					</tr>
				</thead>
				<tbody>
					<tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>dongguanpengyou</td><td>dongguanpengyou</td><td>423</td><td>423</td><td>45</td><td>333</td><td>0</td><td>45</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>wulong</td><td>武龙</td><td>4</td><td>4</td><td>1</td><td>3</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>yaya</td><td>yaya</td><td>1371</td><td>1371</td><td>133</td><td>1173</td><td>0</td><td>65</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>hqhl</td><td>徐州杜</td><td>1033</td><td>1033</td><td>125</td><td>810</td><td>0</td><td>98</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>qifan</td><td>qifan</td><td>2</td><td>2</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>wangwu</td><td>王武</td><td>2</td><td>2</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>wuliju</td><td>吴立举</td><td>1</td><td>1</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>baixin</td><td>百信</td><td>11183</td><td>11281</td><td>1507</td><td>7473</td><td>450</td><td>1780</td><td>6</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>manli</td><td>曼利</td><td>15</td><td>15</td><td>0</td><td>15</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>zhangbin</td><td>刘敏</td><td>86</td><td>86</td><td>2</td><td>79</td><td>0</td><td>5</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>lianxunwangluo</td><td>刘敏</td><td>2001</td><td>2001</td><td>154</td><td>1534</td><td>0</td><td>313</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>李小一</td><td>雨花石</td><td>52</td><td>52</td><td>46</td><td>6</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>jiangyong</td><td>宋小歌</td><td>204</td><td>204</td><td>6</td><td>195</td><td>0</td><td>3</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>qiweijian</td><td>祁伟建</td><td>1</td><td>1</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>dingxin</td><td>dingxin</td><td>1</td><td>1</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>renbao</td><td>谷立旭</td><td>573</td><td>1146</td><td>116</td><td>534</td><td>0</td><td>22</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>jinglaoshi</td><td>15952030343</td><td>6</td><td>6</td><td>0</td><td>6</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>duoge</td><td>duoge</td><td>8</td><td>8</td><td>1</td><td>7</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>longshi</td><td>longshi</td><td>6231</td><td>6231</td><td>629</td><td>5146</td><td>0</td><td>456</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>杨涛</td><td>杨涛</td><td>9</td><td>9</td><td>0</td><td>9</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>明测试</td><td>闻昌萍</td><td>330</td><td>330</td><td>15</td><td>288</td><td>0</td><td>27</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>zhangjuan</td><td>张娟</td><td>2</td><td>2</td><td>2</td><td>0</td><td>0</td><td>0</td><td>2</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>dulingfan</td><td>杜令帆</td><td>1202</td><td>1202</td><td>85</td><td>851</td><td>0</td><td>266</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>yunan</td><td>yunan</td><td>25</td><td>25</td><td>0</td><td>13</td><td>0</td><td>12</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>彭波</td><td>彭波</td><td>10</td><td>10</td><td>2</td><td>8</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03-19</td><td>chongqingxiongdi</td><td>chongqingxiongdi</td><td>2815</td><td>2815</td><td>139</td><td>2477</td><td>0</td><td>199</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03-19</td><td>华众</td><td>华众</td><td>5</td><td>5</td><td>2</td><td>3</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;" colspan="2">总计</td><td></td><td>27595</td><td>28266</td><td>3010</td><td>20970</td><td>450</td><td>3291</td><td>8</td></tr>
				</tbody>
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
<br>



<div style="position: absolute; z-index: 19700; top: -1970px; left: -1970px;">
		<iframe style="width: 186px; height: 197px;"
			src="<%=basePath%>/DateCtrol/DateCtrol.htm" border="0" scrolling="no"
			frameborder="0"></iframe>
	</div><div style="left: 0px ! important; top: 0px ! important;" class="firebugResetStyles firebugBlockBackgroundColor firebugLayoutBox firebugLayoutBoxOffset"><div style="padding: 0px 0px 1px ! important; background-color: rgb(237, 255, 100) ! important;" class="firebugResetStyles firebugLayoutBox"><div style="padding: 0px ! important; background-color: rgb(68, 68, 68) ! important;" class="firebugResetStyles firebugLayoutBox"><div style="padding: 0px ! important; background-color: SlateBlue ! important;" class="firebugResetStyles firebugLayoutBox"><div style="width: 1366px ! important; height: 389px ! important; background-color: SkyBlue ! important;" class="firebugResetStyles firebugLayoutBox"></div></div></div></div></div></body></html>