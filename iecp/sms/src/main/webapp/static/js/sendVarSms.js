$(function() {

	$("#content").change(contentChange);
	$("#content").keyup(contentChange);
	$("#content").keydown(contentChange);
	$("#checkBadwords").click(checkBadwords);
	$("#useSign").click(useSign);
	$("#btnReset").click(clearAll);
	$("#showDemo").click(showDemo);
});

function contentChange() {

	maxSmsWords = $("#maxSmsWords").html();
	perSmsWords = $("#perSmsWords").html();
	chargeCount = 0;

	count = $("#content").val().length;
	if (count > maxSmsWords) {
		alert("输入字数已经超出短信允许的最大字数，请尽量优化一下");
		return;
	}
	$("#writenCount").html(count);
	$("#restCount").html(maxSmsWords - count);
	if (count < perSmsWords) {
		$("#chargeCount").html(1);
	} else {
		chargeCount = (count - perSmsWords - 1) / (perSmsWords - 7);
		chargeCount = Math.floor(chargeCount) + 2;
		$("#chargeCount").html(chargeCount);
	}
}

function useSign() {
	str = $("#content").val();
	$("#content").val(str + "【】");
	contentChange();
}

function checkBadwords() {
	content = $("#content").val();
	if (content == "") {
		alert("短信内容为空,无法完成检测");
		return false;
	}
	url = "sms-send-manage!checkBadwords";
	data = {
		content : content
	};
	$.get(url, data, function(data) {
		flag = data.split(",")[0];
		msg = data.split(",")[1];
		if (flag == "fail") {
			alert(msg);
			return false;
		}
		alert(msg);
		return true;
	});
}
function sub() {
	content = $("#content").val();
	$.blockUI({
		message : "<h3><img src='../images/wait.gif'/>正在发送,请稍候...</h3>",
	});
	if (content == "") {
		alert("短信内容不能为空");
		$.unblockUI();
		return false;
	}
	$.unblockUI();
	return true;
}
function clearAll() {
	$("#content").val("");
	$("#planTime").val("");
	contentChange();
}

function showDemo() {
	$("#demo").html("<img src='../images/demo.jpg'></img>");
}