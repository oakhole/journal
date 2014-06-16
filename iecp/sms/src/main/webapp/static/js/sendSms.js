$(function() {
	$("#importFromTxt").click(importFromTxt);
	$("#importFromExcel").click(importFromExcel);
	$("#importFromContact").click(importFromContact);

	$("#content").change(contentChange);
	$("#content").keyup(contentChange);
	$("#content").keydown(contentChange);

	$("#mobile").change(mobileChange);
	$("#mobile").keyup(mobileChange);
	$("#mobile").keydown(mobileChange);

	$("#checkErr").click(checkErr);
	$("#checkRep").click(checkRep);
	$("#checkBadwords").click(checkBadwords);
	$("#useSign").click(useSign);
	$("#sendSms").click(sub);
	$("#btnReset").click(clearAll);
	$("#clearPhone").click(clearPhone);
});

function importFromTxt() {
	JqueryDialog.Open1('TXT导入号码', 'sms-manage/sms-send-manage!importFromTxt',
			360, 80, true, true, true, 'no');
}

function importFromContact() {
	JqueryDialog.Open2('导入通讯录', 'sms-manage/sms-send-manage!importFromContact',
			366, 300, false, false, true);
}

function importFromExcel() {
	JqueryDialog.Open1('EXCEL导入号码',
			'sms-manage/sms-send-manage!importFromExcel', 366, 80, true, true,
			true, 'no');
}

function setPhone(mobile) {
	str = $("#mobile").val();
	tmp = mobile.replace(/,/ig, "\n");
	if ("" == str) {
		$("#mobile").val(tmp);
	} else {
		$("#mobile").val(str + '\r\n' + tmp);
	}
	getPhoneCount();
}

function contentChange() {

	maxSmsWords = $("#maxSmsWords").html();
	perSmsWords = $("#perSmsWords").html();
	chargeCount = 0;

	content = $("#content").val();
	count = content.length;
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

function mobileChange(e) {
	getPhoneCount();
	return true;
}

totalCount = 0;
function getPhoneCount() {
	var tmp1, tmp2, i, len, count;
	tmp1 = $("#mobile").val();
	tmp2 = tmp1.split("\n");
	len = tmp2.length;
	count = 0;
	for (i = 0; i < len; i++) {
		if (tmp2[i].length > 0)
			count++;
	}
	$("#totalCount").html(count);
	totalCount = count;
	if (count > 50000) {
		alert("您输入号码过多,请使用大数据量提交方式,直接导入文件发送");
		$("#mobile").val("");
	}
}

function useSign() {
	str = $("#content").val();
	$("#content").val(str + "【】");
	contentChange();
}
function checkErr() {
	var tmp1, tmp2, i, len, count, list;
	var head = ",134,135,136,137,138,139,147,150,151,152,157,158,159,182,183,187,188,130,131,132,155,156,185,186,133,153,180,189,";
	tmp1 = $("#mobile").val();
	tmp1 = tmp1.replace(/[^\r\n\d]/g, '');
	tmp2 = tmp1.split("\n");
	len = tmp2.length;
	count = 0;
	list = "";
	for (i = 0; i < len; i++) {
		if (tmp2[i].length > 0) {
			if (tmp2[i].length == 11 && tmp2[i].charAt(0) == '1') {
				if (head.indexOf(tmp2[i].substring(0, 3), 0) > 0) {
					count++;
					list += tmp2[i] + '\r\n';
				}
			}

			else if ((tmp2[i].length == 11 || tmp2[i].length == 12)
					&& tmp2[i].charAt(0) == '0') {
				count++;
				list += tmp2[i] + '\r\n';
			}

		}
	}
	if (list.lastIndexOf('\r\n') >= 0) {
		list = list.substr(0, list.length - 2);// 去除最后的回车
	}
	totalCount = count;
	$("#totalCount").html(count);
	$("#mobile").val(list);
}
function checkRep() {
	var tmp1, tmp2, i, j, len, count, list;
	tmp1 = $("#mobile").val();
	tmp2 = tmp1.split("\n");
	len = tmp2.length;
	count = 0;
	list = "";
	for (i = 0; i < len; i++) {
		if (tmp2[i].length > 0) {
			for (j = i + 1; j < len; j++) {
				if (tmp2[i] == tmp2[j]) {
					tmp2[j] = "";
				}
			}
			count++;
			list += tmp2[i] + '\r\n';
		}
	}
	if (list.lastIndexOf('\r\n') >= 0) {
		list = list.substr(0, list.length - 2);// 去除最后的回车
	}
	$("#totalCount").html(count);
	totalCount = count;
	$("#mobile").val(list);
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

	$.blockUI({
		message : "<h3><img src='../images/wait.gif'/>正在发送,请稍候...</h3>",
	});

	messageCount = $("#chargeCount").html();

	mobileCount = $("#totalCount").html();

	flag = window.confirm('当前发送条数为 ' + mobileCount * messageCount + ',是否确定提交?');

	if (!flag) {
		$.unblockUI();
		return false;
	} else {

		planTime = $("#planTime").val();
		content = $("#content").val();
		mobile = $("#mobile").val();

		filename = $("#mobileFile").val();

		if (content == "") {
			alert("短信内容不能为空");
			$.unblockUI();
			return false;
		}
		flag = $("#type1").attr("checked");
		if (flag) {
			if (filename == "") {
				alert("上传文件不能为空");
				$.unblockUI();
				return false;
			}
		} else {
			if (mobile == "") {
				alert("手机号码不能为空");
				$.unblockUI();
				return false;
			}
		}
	}
	return true;
}
function clearAll() {
	$("#content").val("");
	$("#planTime").val("");
	$("#mobileFile").val("");
	contentChange();
	getPhoneCount();
}

function clearPhone() {
	$("#mobile").val("");
}