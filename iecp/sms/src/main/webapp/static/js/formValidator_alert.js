FormValidator = function(id, formid) {
    if (!document.getElementById || !document.getElementsByTagName)
        return false;

    
    this.parentForm = document.getElementById(id);
    this.form = document.getElementById(formid);
}

FormValidator.prototype.validate = function()
{
    var _this = this;
    var ret = true;
    var inputs = _this.parentForm.getElementsByTagName("input");
    var selects = _this.parentForm.getElementsByTagName("select");
	var textareas=_this.parentForm.getElementsByTagName("textarea");
	
    for (var i = 0; i < inputs.length; i++)
    {
        if (inputs[i].type != "submit" && inputs[i].type != "button" && inputs[i].type != "reset")
        {
            if (!_this.onInputBlur(inputs[i]))
			{
                ret = false;
				return false;
			}
        }
    }

    for (var n = 0; n < selects.length; n++)
    {
        if (!_this.onSelectChange(selects[n]))
		{
            ret = false;
			return false
		}
    }
	for (var i = 0; i < textareas.length; i++)
    {
		if (!_this.onInputBlur(textareas[i]))
		{
			ret = false;
			return false;
		}
    }
    return ret;
}

FormValidator.prototype.clear = function()
{
    var _this = this;
    var inputs = _this.parentForm.getElementsByTagName("input");
    var selects = _this.parentForm.getElementsByTagName("select");
	var textareas=_this.parentForm.getElementsByTagName("textarea");
    for (var i = 0; i < inputs.length; i++)
    {
        if (inputs[i].type != "submit" && inputs[i].type != "button" && inputs[i].type != "reset")
        {
            if (inputs[i].getAttribute("nullable") == "false")
                inputs[i].className = "";
            else
                inputs[i].className = "";
        }
    }

    for (var n = 0; n < selects.length; n++)
    {
        selects[n].className = "";
    }
	for (var i = 0; i < textareas.length; i++)
    {
        if (textareas[i].getAttribute("nullable") == "false")
			textareas[i].className = "";
		else
			textareas[i].className = "";
    }
}

FormValidator.prototype.onInputBlur = function(e) {
    var required = (e.getAttribute("nullable") == "false");

    if (e.value != "" && !this.isValid(e)) {
        alert(e.getAttribute("errMsg") + errmsgstr);
        if (e.type != "hidden") {
            e.focus();
            e.select();
        }
        return false;
    }

    if (e.value == "" && required) {

        if (e.type != "hidden") {
            alert(e.getAttribute("errMsg") + '不能为空');
            e.focus();
            e.select();
        }
        else {
            alert('请给'+e.getAttribute("errMsg")+'打分');
        }
        return false;
    }
    return true;
}

FormValidator.prototype.onSelectChange = function(e) {
    var required = (e.getAttribute("nullable") == "false");
    if (required) {
        if (e.value == "") {

            if (e.type != "hidden") {
                alert(e.getAttribute("errMsg") + '不能为空');
                e.focus();
                //e.select();
            }
            else {
                alert('请给' + e.getAttribute("errMsg") + '打分');
            }
            return false;
        }
    }
    return true;
}
var l = 0,t = 0,w = 0;
var errmsgstr="";
function f_GetXY(e) {
    w = e.offsetWidth;
    l = e.offsetLeft;
    t = e.offsetTop;
    while (e = e.offsetParent) {
        l += e.offsetLeft;
        t += e.offsetTop;
    }
    l = parseInt(w) + parseInt(l) + 2;
}
function closeElem(e){
	e.style.display="none";
}
var GetRandomn = 1;
function GetRandom(n){
	GetRandomn=Math.floor(Math.random()*n+1)
}

FormValidator.prototype.initialize = function() {
    var _this = this;
    _this.parentForm.onsubmit = function() {
        return _this.validate();
    };
    _this.parentForm.onreset = function(e) {
        return _this.clear();
    };
    
    var inputs = _this.parentForm.getElementsByTagName("input");
    var selects = _this.parentForm.getElementsByTagName("select");
    var textareas = _this.parentForm.getElementsByTagName("textarea");
    
    for (var i = 0; i < textareas.length; i++) {
        if (textareas[i].getAttribute("nullable") == "false")
            textareas[i].className = "";
        textareas[i].onblur = function() {
                //_this.onInputBlur(this);
        }
        textareas[i].onkeyup = function() {
            //_this.onInputBlur(this);
        }
    }

    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type != "submit" && inputs[i].type != "button" && inputs[i].type != "reset") {
            if (inputs[i].getAttribute("nullable") == "false") {
                inputs[i].className = "";
            }
            inputs[i].onblur = function() {
                 //_this.onInputBlur(this);
            }
        }
    }

    for (var i = 0; i < selects.length; i++) {
        selects[i].onblur = function() {
            //_this.onSelectChange(this);
        }
    }
}

FormValidator.prototype.isValid = function(e)
{
    switch (e.getAttribute("valueType"))
            {
        case 'DIGIT' : return FormValidator.isDigit(e.value);
        case 'INTEGER' : return FormValidator.isInteger(e.value);
        case 'FLOAT' : return FormValidator.isFloat(e.value);
        case 'CURRENCY' : return FormValidator.isCurrency(e.value);
        case 'CPFCNPJ' : return FormValidator.isCPFCNPJ(e.value);
        case 'WORD' : return FormValidator.isWord(e.value);
        case 'EMAIL' : return FormValidator.isEmail(e.value);
        //case 'URL' : return FormValidator.isURL(e.value);
        case 'DATE' : return FormValidator.isDate(e.value);
        case 'TIME' : return FormValidator.isTime(e.value, false);
        case 'TIME-AMPM' : return FormValidator.isTime(e.value, true);
        case 'STRING': return FormValidator.isValidString(e);
        case 'PASS': return  FormValidator.isPass(e);
        case 'USER': return  FormValidator.isUser(e);
		case 'EmailQq': return FormValidator.isEmailQq(e);
		case 'Qq': return FormValidator.isQq(e);
        case 'MOBILE':return  FormValidator.isMobile(e);
        case 'TELEPHONE':return  FormValidator.isTelephone(e);
        case 'IP':return FormValidator.test(e, (/^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$/));
        default :
            var m = [];
            if (m = /FLOAT(\-([1-9][0-9]*)\:([1-9][0-9]*))?/.exec(e.getAttribute("valueType")))
                return (m[1] ? FormValidator.isFloat(v, m[2], m[3]) : FormValidator.isFloat(e.value));
            else if (m = /ZIP\-?([1-9])\:?([1-9])/.exec(e.getAttribute("valueType")))
                return FormValidator.isZIP(e.getAttribute("valueType"), m[1], m[2]);
            else
                return true;
    }
    return true;
};

FormValidator.isQq = function(e)
{
    var v = e.value;
	if (FormValidator.test(e, (/^\d{5,12}$/)))
    {
		return true;
	}else{
		errmsgstr="qq格式错误";
		return false;
	}
}
FormValidator.isEmailQq = function(e)
{
    var v = e.value;
	if (FormValidator.isQq(e) || FormValidator.isEmail(e))
    {
		return true;
	}else{
		errmsgstr="邮箱或qq格式错误!";
		return false;
	}
}
FormValidator.isPass = function(e)
{
    var v = e.value;
    
	if (FormValidator.test(e, (/^(\w){6,20}$/)))
    {
		return true;
	}else{
		errmsgstr="长度在6至20";
		return false;
	}
}


FormValidator.isValidString = function(e) {
    var v = e.value;
    var minLength = parseInt(e.getAttribute("minLength"), 10);
    if (e.getAttribute("minLength") == undefined)
        minLength = 0;
    var maxLength = parseInt(e.getAttribute("maxLength"), 10);
    if (e.getAttribute("maxLength") == undefined)
        maxLength = 0;
    if (e.getAttribute("nullable") != "false") {
        return true;
    } else {
        if (e.value.length >= minLength && e.value.length <= maxLength)
            return true;
        if (maxLength <= minLength && e.value.length >= minLength)
            return true;
        if (maxLength <= minLength && e.value.length < minLength) {
            errmsgstr = "长度至少为" + minLength;
            return false;
        }
        //errmsgstr="长度在"+minLength+"至"+"maxLength";
        return false;
    }
}


FormValidator.isUser = function(e)
{
    var v = e.value;
	if (FormValidator.test(e, (/^[a-zA-Z]{1}([a-zA-Z0-9_]){4,19}$/)))
    {
		return true;
	}else{
		errmsgstr="格式错误,字母开头且以字母、数字或下划线组成";
		return false;
	}
}


FormValidator.isMobile = function(e)
{
    var v = e.value;
	if (FormValidator.test(e, (/^1[3|5|8]\d{9}$/)))
    {
		return true;
	}else{
		errmsgstr="格式错误";
		return false;
	}
}

FormValidator.isTelephone = function(e)
{
    var v = e.value;
	if (FormValidator.test(e, (/(^[0-9]{3,4}\-[0-9]{3,8}$)/)))
    {
		return true;
	}else{
		errmsgstr="格式错误";
		return false;
	}
}

FormValidator.test = function(e, expression)
{
    var val = e.value;

    if (!(expression.test(val)))
        return false;

    return true;
}

/**
 * Digit mask validation routine
 * @param {String} val Value to validate
 * @type Boolean
 */
FormValidator.isDigit = function(val) {
    if (val == "")
        return true;
    if (!(/^[0-9]+$/.test(val)))
	{
		errmsgstr="只能为数字";
        return false;
	}
    var intval = parseInt(val, 10);
	if (intval == 0 || intval){
		return true;
	}else{
		errmsgstr="只能为数字";
		return false;
	}
};

/**
 * Integer numbers validation routine
 * @param {String} val Value to validate
 * @type Boolean
 */
FormValidator.isInteger = function(val) {
    if (val == "")
        return false;
    if (!(/^(\+|\-)?\d+$/.test(val)))
	{	
		errmsgstr="只能为整数";
        return false;
	}
    var intval = parseInt(val, 10);
	if (intval == 0 || intval){
		return true;
	}else{
		errmsgstr="只能为整数";
		return false;
	}
};

/**
 * Floating point numbers validation routine
 * @param {String} val Value to validate
 * @param {Number} intPart Integer part size
 * @param {Number} decPart Decimal part size
 * @type Boolean
 */
FormValidator.isFloat = function(val, intPart, decPart) {
    if (val == "")
        return true;
    var floatval = parseFloat(val, 10);
    if (floatval == 0 || floatval) {
        intPart = intPart || "";
        decPart = decPart || "";
        return val.match(new RegExp("^\-?\\d{1," + intPart + "}(\\.\\d{1," + decPart + "})?$"));
    }
	errmsgstr="只能为数字加-或.";
    return false;
};

/**
 * Currency validation routine
 * @param {String} val Value to validate
 * @type Boolean
 */
FormValidator.isCurrency = function(val) {
    if (val == "")
        return true;
    var size = (val.charAt(0) == '-' ? val.length - 1 : val.length);
    var mod = (size - 3) % 4;
    var groups = (size - mod - 3) / 4;
    var re = new RegExp("^\-?\\d{" + mod + "}(\\.\\d{3}){" + groups + "},\\d{2}$");
    return re.test(val);
};

/**
 * Validates CPF/CNPJ document numbers
 * @param {String} val Value to validate
 * @type Boolean
 */
FormValidator.isCPFCNPJ = function(val) {
    if (val == "")
        return true;
    var len = val.length;
    var sum1, sum2, rest, d1, d2;
    if (val.length == 14) {
        sum1 = (val.charAt(0) * 5) + (val.charAt(1) * 4) + (val.charAt(2) * 3) + (val.charAt(3) * 2) + (val.charAt(4) * 9) + (val.charAt(5) * 8) + (val.charAt(6) * 7) + (val.charAt(7) * 6) + (val.charAt(8) * 5) + (val.charAt(9) * 4) + (val.charAt(10) * 3) + (val.charAt(11) * 2);
        rest = sum1 % 11,d1 = rest < 2 ? 0 : 11 - rest;
        sum2 = (val.charAt(0) * 6) + (val.charAt(1) * 5) + (val.charAt(2) * 4) + (val.charAt(3) * 3) + (val.charAt(4) * 2) + (val.charAt(5) * 9) + (val.charAt(6) * 8) + (val.charAt(7) * 7) + (val.charAt(8) * 6) + (val.charAt(9) * 5) + (val.charAt(10) * 4) + (val.charAt(11) * 3) + (val.charAt(12) * 2);
        rest = sum2 % 11,d2 = rest < 2 ? 0 : 11 - rest;
        return ((val.charAt(12) == d1) && (val.charAt(13) == d2));
    } else if (val.length == 11) {
        sum1 = (val.charAt(0) * 10) + (val.charAt(1) * 9) + (val.charAt(2) * 8) + (val.charAt(3) * 7) + (val.charAt(4) * 6) + (val.charAt(5) * 5) + (val.charAt(6) * 4) + (val.charAt(7) * 3) + (val.charAt(8) * 2);
        rest = sum1 % 11,d1 = rest < 2 ? 0 : 11 - rest;
        sum2 = (val.charAt(0) * 11) + (val.charAt(1) * 10) + (val.charAt(2) * 9) + (val.charAt(3) * 8) + (val.charAt(4) * 7) + (val.charAt(5) * 6) + (val.charAt(6) * 5) + (val.charAt(7) * 4) + (val.charAt(8) * 3) + (val.charAt(9) * 2);
        rest = sum2 % 11,d2 = rest < 2 ? 0 : 11 - rest;
        return ((val.charAt(9) == d1) && (val.charAt(10) == d2));
    } else {
        return false;
    }
};

/**
 * Validates if a given value is word boundary
 * @param {String} val Value to validate
 * @type Boolean
 */
FormValidator.isWord = function(val) {
    return (val == "" || (/^\w+((-\w+)|(\.\w+))*$/.test(val)));
};

/**
 * E-mail validation routine
 * @param {String} val Value to validate
 * @type Boolean
 */
//FormValidator.isEmail = function(val) {
//    var rep = val.replace(/^[^0-9a-zA-Z_\[\]\.\-@]+$/, "");
//	
//	if (val == "" || (val == rep && (/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(val)))){
//		return true;
//	}else{
//		errmsgstr="邮件格式错误";
//		return false;
//	}
//};
FormValidator.isEmail = function(val) {
    val=val.value;
	var rep = val.replace(/^[^0-9a-zA-Z_\[\]\.\-@]+$/, "");
	if (val == "" || (val == rep && (/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(val)))){
		return true;
	}else{
		errmsgstr="邮件格式错误";
		return false;
	}
};


/**
 * Date validation routine
 * @param {String} val Value to validate
 * @param {String} fmt Date format (EURO, US)
 * @type Boolean
 */
FormValidator.isDate = function(val) {
    if (val == "")
        return true;
    var d, m, y, leap, m31, bm;
    var loc = Locale.date;
    var re = loc.regexp;
    if (mt = re.exec(val)) {
        d = parseInt(mt[loc.matches[0]], 10);
        m = parseInt(mt[loc.matches[1]], 10);
        y = parseInt(mt[loc.matches[2]], 10);
        leap = (y % 4 == 0) && (y % 100 != 0 || y % 400 == 0);
        m31 = 0xAD5,bm = (1 << (m - 1));
        if (y < 1000 || m < 1 || m > 12 || d < 1 || d > 31 || (d == 31 && (bm & m31) == 0) || (d == 30 && m == 2) || (d == 29 && m == 2 && !leap)) {
            return false;
        } else {
            return true;
        }
    }
    return false;
};

/**
 * Time validation routine
 * @param {String} val Value to validate
 * @param {Boolean} ampm Require AM/PM flag
 * @type Boolean
 */
FormValidator.isTime = function(val, ampm) {
    if (val == "")
        return true;
    ampm = !!ampm;
    var mt = val.match(new RegExp("^(\\d{1,2})\:(\\d{1,2})(\:(\d{1,2}))?" + (ampm ? "(?:a|p)" : "")));
    if (mt) {
        var h = parseInt(mt[1], 10);
        var m = parseInt(mt[2], 10);
        var s = (mt[4] ? parseInt(mt[4], 10) : 0);
        return (h >= 0 && h <= 23 && m >= 0 && m <= 59 && s >= 0 && s <= 59);
    }
    return false;
};

/**
 * ZIP code validation routine
 * @param {String} val Value to validate
 * @param {Number} left Left size
 * @param {Number} right Right size
 * @type Boolean
 */
FormValidator.isZIP = function(val, left, right) {
    if (val == "")
        return true;
    left = left || 5;
    right = right || 3;
    return val.match(new RegExp("^\\d{" + left + "}\-\\d{" + right + "}$"));
};

FormValidator.attach = function(id, formid) {

    var fv = new FormValidator(id, formid);

    fv.initialize();
}

function isMobile(evar) {
    if (testReg(evar, (/^1[3|5|8]\d{9}$/))) {
        return true;
    } else {
        return false;
    }
}

function testReg(evar, expression) {
    if (!(expression.test(evar))) {
        return false; 
    }
    return true;
}

function logout() { //用户退出登录
    if (!confirm('您确实要退出系统吗？')) {
        return;
    }
    location.href = 'logout.aspx';
}