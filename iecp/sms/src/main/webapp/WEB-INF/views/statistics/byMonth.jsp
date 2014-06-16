<%@page import="com.oakhole.smsManage.model.Sms"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<link rel="stylesheet" href="<%=basePath%>/css/reset.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/style.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="<%=basePath%>/css/invalid.css" type="text/css"
	media="screen">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/simpla.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery_dialog.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery_dialog.js"></script>
<script language="JavaScript" type="text/javascript"
	src="<%=basePath%>/js/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/WdatePicker.css">
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
		var startyear = document.getElementById("startyear").value;
		var startmonth = document.getElementById("startmonth").value;
		var endyear = document.getElementById("endyear").value;
		var endmonth = document.getElementById("endmonth").value;
		location.href = "excel/taskstatexcel.aspx?action=downsmsmonth&company="
				+ company + "&accountType=" + accountType + "&interfaceid="
				+ interfaceid + "&account=" + account + "&startmonth="
				+ startmonth + "&startyear=" + startyear + "&endmonth="
				+ endmonth + "&endyear=" + endyear;
	}
</script>
<style type="text/css" charset="utf-8">/* See license.txt for terms of usage */

/** reset styling **/

.firebugResetStyles {

    z-index: 2147483646 !important;

    top: 0 !important;

    left: 0 !important;

    display: block !important;

    border: 0 none !important;

    margin: 0 !important;

    padding: 0 !important;

    outline: 0 !important;

    min-width: 0 !important;

    max-width: none !important;

    min-height: 0 !important;

    max-height: none !important;

    position: fixed !important;

    transform: rotate(0deg) !important;

    transform-origin: 50% 50% !important;

    border-radius: 0 !important;

    box-shadow: none !important;

    background: transparent none !important;

    pointer-events: none !important;

    white-space: normal !important;

}



.firebugBlockBackgroundColor {

    background-color: transparent !important;

}



.firebugResetStyles:before, .firebugResetStyles:after {

    content: "" !important;

}

/**actual styling to be modified by firebug theme**/

.firebugCanvas {

    display: none !important;

}



/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

.firebugLayoutBox {

    width: auto !important;

    position: static !important;

}



.firebugLayoutBoxOffset {

    opacity: 0.8 !important;

    position: fixed !important;

}



.firebugLayoutLine {

    opacity: 0.4 !important;

    background-color: #000000 !important;

}



.firebugLayoutLineLeft, .firebugLayoutLineRight {

    width: 1px !important;

    height: 100% !important;

}



.firebugLayoutLineTop, .firebugLayoutLineBottom {

    width: 100% !important;

    height: 1px !important;

}



.firebugLayoutLineTop {

    margin-top: -1px !important;

    border-top: 1px solid #999999 !important;

}



.firebugLayoutLineRight {

    border-right: 1px solid #999999 !important;

}



.firebugLayoutLineBottom {

    border-bottom: 1px solid #999999 !important;

}



.firebugLayoutLineLeft {

    margin-left: -1px !important;

    border-left: 1px solid #999999 !important;

}



/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

.firebugLayoutBoxParent {

    border-top: 0 none !important;

    border-right: 1px dashed #E00 !important;

    border-bottom: 1px dashed #E00 !important;

    border-left: 0 none !important;

    position: fixed !important;

    width: auto !important;

}



.firebugRuler{

    position: absolute !important;

}



.firebugRulerH {

    top: -15px !important;

    left: 0 !important;

    width: 100% !important;

    height: 14px !important;

    background: url("data:image/png,%89PNG%0D%0A%1A%0A%00%00%00%0DIHDR%00%00%13%88%00%00%00%0E%08%02%00%00%00L%25a%0A%00%00%00%04gAMA%00%00%D6%D8%D4OX2%00%00%00%19tEXtSoftware%00Adobe%20ImageReadyq%C9e%3C%00%00%04%F8IDATx%DA%EC%DD%D1n%E2%3A%00E%D1%80%F8%FF%EF%E2%AF2%95%D0D4%0E%C1%14%B0%8Fa-%E9%3E%CC%9C%87n%B9%81%A6W0%1C%A6i%9A%E7y%0As8%1CT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AATE9%FE%FCw%3E%9F%AF%2B%2F%BA%97%FDT%1D~K(%5C%9D%D5%EA%1B%5C%86%B5%A9%BDU%B5y%80%ED%AB*%03%FAV9%AB%E1%CEj%E7%82%EF%FB%18%BC%AEJ8%AB%FA'%D2%BEU9%D7U%ECc0%E1%A2r%5DynwVi%CFW%7F%BB%17%7Dy%EACU%CD%0E%F0%FA%3BX%FEbV%FEM%9B%2B%AD%BE%AA%E5%95v%AB%AA%E3E5%DCu%15rV9%07%B5%7F%B5w%FCm%BA%BE%AA%FBY%3D%14%F0%EE%C7%60%0EU%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5JU%88%D3%F5%1F%AE%DF%3B%1B%F2%3E%DAUCNa%F92%D02%AC%7Dm%F9%3A%D4%F2%8B6%AE*%BF%5C%C2Ym~9g5%D0Y%95%17%7C%C8c%B0%7C%18%26%9CU%CD%13i%F7%AA%90%B3Z%7D%95%B4%C7%60%E6E%B5%BC%05%B4%FBY%95U%9E%DB%FD%1C%FC%E0%9F%83%7F%BE%17%7DkjMU%E3%03%AC%7CWj%DF%83%9An%BCG%AE%F1%95%96yQ%0Dq%5Dy%00%3Et%B5'%FC6%5DS%95pV%95%01%81%FF'%07%00%00%00%00%00%00%00%00%00%F8x%C7%F0%BE%9COp%5D%C9%7C%AD%E7%E6%EBV%FB%1E%E0(%07%E5%AC%C6%3A%ABi%9C%8F%C6%0E9%AB%C0'%D2%8E%9F%F99%D0E%B5%99%14%F5%0D%CD%7F%24%C6%DEH%B8%E9rV%DFs%DB%D0%F7%00k%FE%1D%84%84%83J%B8%E3%BA%FB%EF%20%84%1C%D7%AD%B0%8E%D7U%C8Y%05%1E%D4t%EF%AD%95Q%BF8w%BF%E9%0A%BF%EB%03%00%00%00%00%00%00%00%00%00%B8vJ%8E%BB%F5%B1u%8Cx%80%E1o%5E%CA9%AB%CB%CB%8E%03%DF%1D%B7T%25%9C%D5(%EFJM8%AB%CC'%D2%B2*%A4s%E7c6%FB%3E%FA%A2%1E%80~%0E%3E%DA%10x%5D%95Uig%15u%15%ED%7C%14%B6%87%A1%3B%FCo8%A8%D8o%D3%ADO%01%EDx%83%1A~%1B%9FpP%A3%DC%C6'%9C%95gK%00%00%00%00%00%00%00%00%00%20%D9%C9%11%D0%C0%40%AF%3F%EE%EE%92%94%D6%16X%B5%BCMH%15%2F%BF%D4%A7%C87%F1%8E%F2%81%AE%AAvzr%DA2%ABV%17%7C%E63%83%E7I%DC%C6%0Bs%1B%EF6%1E%00%00%00%00%00%00%00%00%00%80cr%9CW%FF%7F%C6%01%0E%F1%CE%A5%84%B3%CA%BC%E0%CB%AA%84%CE%F9%BF)%EC%13%08WU%AE%AB%B1%AE%2BO%EC%8E%CBYe%FE%8CN%ABr%5Dy%60~%CFA%0D%F4%AE%D4%BE%C75%CA%EDVB%EA(%B7%F1%09g%E5%D9%12%00%00%00%00%00%00%00%00%00H%F6%EB%13S%E7y%5E%5E%FB%98%F0%22%D1%B2'%A7%F0%92%B1%BC%24z3%AC%7Dm%60%D5%92%B4%7CEUO%5E%F0%AA*%3BU%B9%AE%3E%A0j%94%07%A0%C7%A0%AB%FD%B5%3F%A0%F7%03T%3Dy%D7%F7%D6%D4%C0%AAU%D2%E6%DFt%3F%A8%CC%AA%F2%86%B9%D7%F5%1F%18%E6%01%F8%CC%D5%9E%F0%F3z%88%AA%90%EF%20%00%00%00%00%00%00%00%00%00%C0%A6%D3%EA%CFi%AFb%2C%7BB%0A%2B%C3%1A%D7%06V%D5%07%A8r%5D%3D%D9%A6%CAu%F5%25%CF%A2%99%97zNX%60%95%AB%5DUZ%D5%FBR%03%AB%1C%D4k%9F%3F%BB%5C%FF%81a%AE%AB'%7F%F3%EA%FE%F3z%94%AA%D8%DF%5B%01%00%00%00%00%00%00%00%00%00%8E%FB%F3%F2%B1%1B%8DWU%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*UiU%C7%BBe%E7%F3%B9%CB%AAJ%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5*%AAj%FD%C6%D4%5Eo%90%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5%86%AF%1B%9F%98%DA%EBm%BBV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%AD%D6%E4%F58%01%00%00%00%00%00%00%00%00%00%00%00%00%00%40%85%7F%02%0C%008%C2%D0H%16j%8FX%00%00%00%00IEND%AEB%60%82") repeat-x !important;

    border-top: 1px solid #BBBBBB !important;

    border-right: 1px dashed #BBBBBB !important;

    border-bottom: 1px solid #000000 !important;

}



.firebugRulerV {

    top: 0 !important;

    left: -15px !important;

    width: 14px !important;

    height: 100% !important;

    background: url("data:image/png,%89PNG%0D%0A%1A%0A%00%00%00%0DIHDR%00%00%00%0E%00%00%13%88%08%02%00%00%00%0E%F5%CB%10%00%00%00%04gAMA%00%00%D6%D8%D4OX2%00%00%00%19tEXtSoftware%00Adobe%20ImageReadyq%C9e%3C%00%00%06~IDATx%DA%EC%DD%D1v%A20%14%40Qt%F1%FF%FF%E4%97%D9%07%3BT%19%92%DC%40(%90%EEy%9A5%CB%B6%E8%F6%9Ac%A4%CC0%84%FF%DC%9E%CF%E7%E3%F1%88%DE4%F8%5D%C7%9F%2F%BA%DD%5E%7FI%7D%F18%DDn%BA%C5%FB%DF%97%BFk%F2%10%FF%FD%B4%F2M%A7%FB%FD%FD%B3%22%07p%8F%3F%AE%E3%F4S%8A%8F%40%EEq%9D%BE8D%F0%0EY%A1Uq%B7%EA%1F%81%88V%E8X%3F%B4%CEy%B7h%D1%A2E%EBohU%FC%D9%AF2fO%8BBeD%BE%F7X%0C%97%A4%D6b7%2Ck%A5%12%E3%9B%60v%B7r%C7%1AI%8C%BD%2B%23r%00c0%B2v%9B%AD%CA%26%0C%1Ek%05A%FD%93%D0%2B%A1u%8B%16-%95q%5Ce%DCSO%8E%E4M%23%8B%F7%C2%FE%40%BB%BD%8C%FC%8A%B5V%EBu%40%F9%3B%A72%FA%AE%8C%D4%01%CC%B5%DA%13%9CB%AB%E2I%18%24%B0n%A9%0CZ*Ce%9C%A22%8E%D8NJ%1E%EB%FF%8F%AE%CAP%19*%C3%BAEKe%AC%D1%AAX%8C*%DEH%8F%C5W%A1e%AD%D4%B7%5C%5B%19%C5%DB%0D%EF%9F%19%1D%7B%5E%86%BD%0C%95%A12%AC%5B*%83%96%CAP%19%F62T%86%CAP%19*%83%96%CA%B8Xe%BC%FE)T%19%A1%17xg%7F%DA%CBP%19*%C3%BA%A52T%86%CAP%19%F62T%86%CA%B0n%A9%0CZ%1DV%C6%3D%F3%FCH%DE%B4%B8~%7F%5CZc%F1%D6%1F%AF%84%F9%0F6%E6%EBVt9%0E~%BEr%AF%23%B0%97%A12T%86%CAP%19%B4T%86%CA%B8Re%D8%CBP%19*%C3%BA%A52huX%19%AE%CA%E5%BC%0C%7B%19*CeX%B7h%A9%0C%95%E1%BC%0C%7B%19*CeX%B7T%06%AD%CB%5E%95%2B%BF.%8F%C5%97%D5%E4%7B%EE%82%D6%FB%CF-%9C%FD%B9%CF%3By%7B%19%F62T%86%CA%B0n%D1R%19*%A3%D3%CA%B0%97%A12T%86uKe%D0%EA%B02*%3F1%99%5DB%2B%A4%B5%F8%3A%7C%BA%2B%8Co%7D%5C%EDe%A8%0C%95a%DDR%19%B4T%C66%82fA%B2%ED%DA%9FC%FC%17GZ%06%C9%E1%B3%E5%2C%1A%9FoiB%EB%96%CA%A0%D5qe4%7B%7D%FD%85%F7%5B%ED_%E0s%07%F0k%951%ECr%0D%B5C%D7-g%D1%A8%0C%EB%96%CA%A0%A52T%C6)*%C3%5E%86%CAP%19%D6-%95A%EB*%95q%F8%BB%E3%F9%AB%F6%E21%ACZ%B7%22%B7%9B%3F%02%85%CB%A2%5B%B7%BA%5E%B7%9C%97%E1%BC%0C%EB%16-%95%A12z%AC%0C%BFc%A22T%86uKe%D0%EA%B02V%DD%AD%8A%2B%8CWhe%5E%AF%CF%F5%3B%26%CE%CBh%5C%19%CE%CB%B0%F3%A4%095%A1%CAP%19*Ce%A8%0C%3BO*Ce%A8%0C%95%A12%3A%AD%8C%0A%82%7B%F0v%1F%2FD%A9%5B%9F%EE%EA%26%AF%03%CA%DF9%7B%19*Ce%A8%0C%95%A12T%86%CA%B8Ze%D8%CBP%19*Ce%A8%0C%95%D1ae%EC%F7%89I%E1%B4%D7M%D7P%8BjU%5C%BB%3E%F2%20%D8%CBP%19*Ce%A8%0C%95%A12T%C6%D5*%C3%5E%86%CAP%19*Ce%B4O%07%7B%F0W%7Bw%1C%7C%1A%8C%B3%3B%D1%EE%AA%5C%D6-%EBV%83%80%5E%D0%CA%10%5CU%2BD%E07YU%86%CAP%19*%E3%9A%95%91%D9%A0%C8%AD%5B%EDv%9E%82%FFKOee%E4%8FUe%A8%0C%95%A12T%C6%1F%A9%8C%C8%3D%5B%A5%15%FD%14%22r%E7B%9F%17l%F8%BF%ED%EAf%2B%7F%CF%ECe%D8%CBP%19*Ce%A8%0C%95%E1%93~%7B%19%F62T%86%CAP%19*Ce%A8%0C%E7%13%DA%CBP%19*Ce%A8%0CZf%8B%16-Z%B4h%D1R%19f%8B%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1%A2%A52%CC%16-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2EKe%98-Z%B4h%D1%A2EKe%D02%5B%B4h%D1%A2EKe%D02%5B%B4h%D1%A2E%8B%96%CA0%5B%B4h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%16-%95a%B6h%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-Z*%C3l%D1%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z%B4T%86%D9%A2E%8B%16-Z%B4T%06-%B3E%8B%16-Z%B4T%06-%B3E%8B%16-Z%B4h%A9%0C%B3E%8B%16-Z%B4h%A9%0CZf%8B%16-Z%B4h%A9%0CZf%8B%16-Z%B4h%D1R%19f%8B%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1%A2%A52%CC%16-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2EKe%98-Z%B4h%D1%A2EKe%D02%5B%B4h%D1%A2EKe%D02%5B%B4h%D1%A2E%8B%96%CA0%5B%B4h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%16-%95a%B6h%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-Z*%C3l%D1%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z%B4T%86%D9%A2E%8B%16-Z%B4T%06-%B3E%8B%16-Z%B4T%06-%B3E%8B%16-Z%B4h%A9%0C%B3E%8B%16-Z%B4h%A9%0CZf%8B%16-Z%B4h%A9%0CZf%8B%16-Z%B4h%D1R%19f%8B%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1%A2%A52%CC%16-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2EKe%98-Z%B4h%D1%A2EKe%D02%5B%B4h%D1%A2EKe%D02%5B%B4h%D1%A2E%8B%96%CA0%5B%B4h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%16-%95a%B6h%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-Z*%C3l%D1%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z%B4T%86%D9%A2E%8B%16-Z%B4T%06-%B3E%8B%16-Z%B4%AE%A4%F5%25%C0%00%DE%BF%5C'%0F%DA%B8q%00%00%00%00IEND%AEB%60%82") repeat-y !important;

    border-left: 1px solid #BBBBBB !important;

    border-right: 1px solid #000000 !important;

    border-bottom: 1px dashed #BBBBBB !important;

}



.overflowRulerX > .firebugRulerV {

    left: 0 !important;

}



.overflowRulerY > .firebugRulerH {

    top: 0 !important;

}



/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

.fbProxyElement {

    position: fixed !important;

    pointer-events: auto !important;

}

</style></head>

<body>
<div class="content-box role">
	<div class="content-box-header">
		<h3 style="cursor: s-resize;">短信发送统计月报</h3>
		<div class="clear"></div>
	</div>
	<div class="content-box-content">
		<div class="searchgrid">
			<form method="post" action="parenttasksmsmonth.aspx" name="Form" id="Form1">

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
			
			<label>起始时间：</label>
			<select name="startyear" id="startyear" style="line-height:30px;height:24px;padding-top:3px;padding-bottom:3px;">
			     <option selected="selected" value="2013">2013年</option><option value="2012">2012年</option><option value="2011">2011年</option><option value="2010">2010年</option><option value="2009">2009年</option><option value="2008">2008年</option>
			</select>
			<select name="startmonth" id="startmonth" style="line-height:30px;height:24px;padding-top:3px;padding-bottom:3px;">
			     <option value="1">1月</option><option value="2">2月</option><option selected="selected" value="3">3月</option><option value="4">4月</option><option value="5">5月</option><option value="6">6月</option><option value="7">7月</option><option value="8">8月</option><option value="9">9月</option><option value="10">10月</option><option value="11">11月</option><option value="12">12月</option>
			</select>
            <label>结束时间：</label>
            <select name="endyear" id="endyear" style="line-height:30px;height:24px;padding-top:3px;padding-bottom:3px;">
			     <option selected="selected" value="2013">2013年</option><option value="2012">2012年</option><option value="2011">2011年</option><option value="2010">2010年</option><option value="2009">2009年</option><option value="2008">2008年</option>
			</select>
			<select name="endmonth" id="endmonth" style="line-height:30px;height:24px;padding-top:3px;padding-bottom:3px;">
			     <option value="1">1月</option><option value="2">2月</option><option selected="selected" value="3">3月</option><option value="4">4月</option><option value="5">5月</option><option value="6">6月</option><option value="7">7月</option><option value="8">8月</option><option value="9">9月</option><option value="10">10月</option><option value="11">11月</option><option value="12">12月</option>
			</select>
			
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
					<tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>admin</td><td>管理员</td><td>3</td><td>3</td><td>0</td><td>3</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>test</td><td>test</td><td>15</td><td>15</td><td>2</td><td>12</td><td>0</td><td>1</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>zhangwenzhong</td><td>张文忠</td><td>352</td><td>352</td><td>70</td><td>275</td><td>0</td><td>7</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>wangyongtong</td><td>王永通</td><td>4</td><td>4</td><td>0</td><td>4</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>dongguanpengyou</td><td>dongguanpengyou</td><td>2252</td><td>2252</td><td>351</td><td>1759</td><td>0</td><td>142</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>gujunliang</td><td>谷俊良</td><td>2</td><td>2</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>wulong</td><td>武龙</td><td>291</td><td>291</td><td>32</td><td>243</td><td>0</td><td>16</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>caishangjiaoyu</td><td>谷俊良</td><td>2509</td><td>2509</td><td>410</td><td>1847</td><td>0</td><td>252</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>yaya</td><td>yaya</td><td>1504</td><td>1504</td><td>151</td><td>1285</td><td>0</td><td>68</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>hqhl</td><td>徐州杜</td><td>12266</td><td>12266</td><td>732</td><td>10779</td><td>0</td><td>755</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>gulixu</td><td>谷立旭</td><td>2</td><td>2</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>qifan</td><td>qifan</td><td>2</td><td>2</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>chenzhengkai</td><td>陈正凯</td><td>4</td><td>4</td><td>0</td><td>4</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>yelongfei</td><td>叶龙飞</td><td>7</td><td>7</td><td>0</td><td>5</td><td>0</td><td>2</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>tianxing</td><td>tianxing</td><td>1903</td><td>1903</td><td>142</td><td>1622</td><td>0</td><td>139</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>wangwu</td><td>王武</td><td>4</td><td>10</td><td>0</td><td>4</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>wuliju</td><td>吴立举</td><td>3</td><td>3</td><td>0</td><td>3</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>baixin</td><td>百信</td><td>24720</td><td>24818</td><td>2914</td><td>18418</td><td>450</td><td>2965</td><td>6</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>manli</td><td>曼利</td><td>16</td><td>16</td><td>0</td><td>16</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>huise</td><td>huise</td><td>2</td><td>2</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>fanjinzhong</td><td>吴永强</td><td>2</td><td>2</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>zhangbin</td><td>刘敏</td><td>115</td><td>115</td><td>4</td><td>99</td><td>0</td><td>12</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>liumin</td><td>刘敏</td><td>2</td><td>2</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>lianxunwangluo</td><td>刘敏</td><td>2001</td><td>2001</td><td>154</td><td>1534</td><td>0</td><td>313</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>njwg</td><td>吴永强</td><td>4</td><td>4</td><td>0</td><td>4</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>李小一</td><td>雨花石</td><td>52</td><td>52</td><td>46</td><td>6</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>jiangyong</td><td>宋小歌</td><td>741</td><td>741</td><td>41</td><td>688</td><td>0</td><td>12</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>wengjianquan</td><td>翁剑权</td><td>3</td><td>3</td><td>2</td><td>1</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>qiweijian</td><td>祁伟建</td><td>2</td><td>2</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>dingxin</td><td>dingxin</td><td>1</td><td>1</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>shanghaihouai</td><td>梅</td><td>1</td><td>1</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>renbao</td><td>谷立旭</td><td>748</td><td>1496</td><td>155</td><td>692</td><td>0</td><td>28</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>renlian</td><td>1333</td><td>4</td><td>4</td><td>0</td><td>4</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>jinglaoshi</td><td>15952030343</td><td>13</td><td>13</td><td>4</td><td>9</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>duoge</td><td>duoge</td><td>21</td><td>21</td><td>1</td><td>20</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>lijingli</td><td>李经理</td><td>149</td><td>149</td><td>17</td><td>103</td><td>0</td><td>29</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>longshi</td><td>longshi</td><td>6241</td><td>6241</td><td>630</td><td>5155</td><td>0</td><td>456</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>杨涛</td><td>杨涛</td><td>9</td><td>9</td><td>0</td><td>9</td><td>0</td><td>0</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>明测试</td><td>闻昌萍</td><td>330</td><td>330</td><td>15</td><td>288</td><td>0</td><td>27</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>zhangjuan</td><td>张娟</td><td>2</td><td>2</td><td>2</td><td>0</td><td>0</td><td>0</td><td>2</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>dulingfan</td><td>杜令帆</td><td>1202</td><td>1202</td><td>85</td><td>851</td><td>0</td><td>266</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>yunan</td><td>yunan</td><td>25</td><td>25</td><td>0</td><td>13</td><td>0</td><td>12</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>彭波</td><td>彭波</td><td>10</td><td>10</td><td>2</td><td>8</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;">2013-03</td><td>chongqingxiongdi</td><td>chongqingxiongdi</td><td>2815</td><td>2815</td><td>139</td><td>2477</td><td>0</td><td>199</td><td>0</td></tr><tr class="alt-row"><td style="padding-left:5px;">2013-03</td><td>华众</td><td>华众</td><td>5</td><td>5</td><td>2</td><td>3</td><td>0</td><td>0</td><td>0</td></tr><tr><td style="padding-left:5px;" colspan="2">总计</td><td></td><td>60359</td><td>61211</td><td>6103</td><td>48259</td><td>450</td><td>5701</td><td>8</td></tr>
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
	</div></body></html>