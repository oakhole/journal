LayerDialog = function() {
	this.root = null;
	this.background = null;
	this.x0 = 0;
	this.y0 = 0;
	this.x1 = 0;
	this.y1 = 0;
	this.zindex = 999;
	this.moveable = false;
	this.containerW = window.top.document.body.clientWidth;
	this.containerH = window.top.document.body.clientHeight;
}
LayerDialog.prototype.close = function(rload) {

	window.top.document.body.removeChild(this.root);
	window.top.document.body.removeChild(this.background);
	this.background = null;
	this.root = null;
	if (rload)
		window.location.reload();
}

LayerDialog.prototype.open = function(title, w, h, e) {
	var _this = this;
	_this.background = window.top.document.createElement("div");
	_this.root = window.top.document.createElement("div");
	var content = window.top.document.createElement("div");
	var header = window.top.document.createElement("div");
	var table = window.top.document.createElement("table");
	var tbody = window.top.document.createElement("tbody");
	var tr = window.top.document.createElement("tr");
	var td1 = window.top.document.createElement("td");
	var td2 = window.top.document.createElement("td");
	var closer = window.top.document.createElement("div");

	content.className = "lycontent";
	header.className = "lyheader";
	closer.className = "lycloser";
	_this.background.className = "lybackground";
	_this.root.className = "layerDialog";

	closer.setAttribute("id", "closebutton")

	td1.innerHTML = title;
	td2.className = "lyralign";
	td2.appendChild(closer);

	tr.appendChild(td1);
	tr.appendChild(td2);
	tbody.appendChild(tr);
	table.appendChild(tbody);

	header.appendChild(table);

	content.appendChild(e);

	// content.style.height = h-100;
	// closer.
	closer.innerHTML = "X 关闭";
	_this.root.appendChild(header);
	_this.root.appendChild(content);

	window.top.document.body.appendChild(_this.background);
	window.top.document.body.appendChild(_this.root);

	e.style.height = h - header.offsetHeight;

	_this.root.style.left = _this.containerW / 2 - w / 2 + 'px';
	_this.root.style.top = _this.containerH / 2 - h / 2 + 'px';
	_this.root.style.width = w + 'px';
	_this.root.style.height = h + 'px';

	closer.onclick = function() {
		_this.close();
	}

	closer.onmouseover = function() {
		this.className = "lycloserOn";
	}
	closer.onmouseout = function() {
		this.className = "lycloser";
	}

	/*
	 * header.onmousedown = function() { if (window.top.event.button == 1) { var
	 * win = this.parentNode; this.setCapture(); _this.x0 =
	 * window.top.event.clientX; _this.y0 = window.top.event.clientY;
	 * //alert(win); //alert(win.style.left); _this.x1 =
	 * parseInt(win.style.left);
	 * 
	 * _this.y1 = parseInt(win.style.top); _this.moveable = true; } }
	 * 
	 * header.onmouseup = function() { if (_this.moveable) {
	 * this.releaseCapture(); _this.moveable = false; } }
	 * 
	 * header.onmousemove = function() {
	 * 
	 * if (_this.moveable) { var win = this.parentNode;
	 * 
	 * var x = _this.x1 + window.top.event.clientX - _this.x0; var y = _this.y1 +
	 * window.top.event.clientY - _this.y0; if (x < 0) { x = 0 } ; if (x >
	 * _this.containerW - 100) { x = _this.containerW - 100 } ; if (y < 0) { y =
	 * 0 } ; if (y > _this.containerH - 30)y = _this.containerH - 30;
	 * win.style.left = x; win.style.top = y; } }
	 */
}

LayerDialog.doModal = function(title, w, h, url, isno) {
	var dialog = new LayerDialog();
	var iframe = window.top.document.createElement("iframe");

	iframe.onreadystatechange = function() {
		if (this.readyState == "complete") {
			iframe.contentWindow.closeParent = function(v) {
				dialog.close(v);
			}
		}
	}

	iframe.scrolling = isno;
	iframe.frameBorder = 0;
	iframe.src = url;
	iframe.width = 360 + 'px';
	iframe.height = 102 + 'px';
	dialog.open(title, w, h, iframe);
	return dialog;
}
LayerDialog.alert = function(errorText, w, h, title, fn) {

	var dialog = new LayerDialog();

	var div = window.top.document.createElement("div");
	var div1 = window.top.document.createElement("div");
	var div2 = window.top.document.createElement("div");
	var div3 = window.top.document.createElement("div");
	var cbutton = window.top.document.createElement("input");
	var obutton = window.top.document.createElement("input");
	cbutton.setAttribute("type", "button");
	cbutton.setAttribute("value", "关闭");
	obutton.setAttribute("type", "button");
	obutton.setAttribute("value", "确认");

	cbutton.onclick = function() {
		if (fn)
			fn("close");
		dialog.close(false);
	}

	obutton.onclick = function() {
		if (fn)
			fn("ok");
		dialog.close(false);
	}

	div.className = "alert";
	div3.className = "footer";
	div2.className = "data";
	div1.className = "header";

	div3.appendChild(cbutton);

	div2.innerHTML = errorText;
	if (fn)
		div3.appendChild(obutton);
	div3.appendChild(cbutton);

	div.appendChild(div1);
	div.appendChild(div2);
	div.appendChild(div3);
	dialog.open(title, w, h, div);

	return dialog;
}

TableWrapper = function(id) {
	if (!document.getElementById || !document.getElementsByTagName)
		return false;

	this.table = document.getElementById(id);
}
TableWrapper.prototype.initialize = function() {
	var trs = this.table.getElementsByTagName("tr");

	for ( var x = 1; x < trs.length; x++) {

		trs[x].onmouseover = function() {
			this.className = "odd";
		}
		trs[x].onmouseout = function() {
			this.className = "";
		}

		var tds = trs[x].getElementsByTagName("td");

		for ( var n = 0; n < tds.length; n++) {
			if (tds[n].innerHTML == "") {
				tds[n].innerHTML = "&nbsp;";
			}
		}
	}
}

TableWrapper.attach = function(id) {
	var wrapper = new TableWrapper(id);
	wrapper.initialize();
}
function msg(tip, num, msg, str) {
	LayerDialog.alert(msg, 300, 140, tip, null);
}
