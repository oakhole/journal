var setting = {
	check: {
		enable: false
	},
	view: {
		showIcon: false
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};

var zNodes =[
	{ id:1, pId:0, name:" 总部 ", open:true},
	{ id:2, pId:1, name:" 企划部 ", open:true},
	{ id:3, pId:1, name:" 销售部 ", open:true},
	{ id:4, pId:1, name:" 技术部 ",open:true},
	{ id:5, pId:2, name:" 秘书组 "},
	{ id:6, pId:2, name:" 策划组 "},
	{ id:7, pId:2, name:" 投标组 "},
	{ id:8, pId:3, name:" 终端组 "},
	{ id:9, pId:3, name:" 网络组 "},
	{ id:10, pId:4, name:" 开发组 "},
	{ id:11, pId:4, name:" 测试组 "}
	];

$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	setting.check.enable=true;
	$.fn.zTree.init($("#modalTree"), setting, zNodes);
});