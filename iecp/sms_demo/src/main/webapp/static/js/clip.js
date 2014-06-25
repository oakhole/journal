<script>
function copy(content) {
	var cp = document.getElementById(content);
	if (document.all) { // IE
		window.clipboardData.setData("Text", content);
		alert('您已经成功复制内容！');
	} else { // FIREFOX
		alert('firefox下不支持剪贴板,请用鼠标复制');
	}
}
</script>