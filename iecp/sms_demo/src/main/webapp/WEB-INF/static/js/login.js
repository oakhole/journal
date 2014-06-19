var jQ=jQuery.noConflict();
function init(_1){
var _2,i,_3;
_2=["uid","password","dynamicPwd","verifyCode"];
for(i=0;i<_2.length;i++){
_3=document.getElementById(_2[i])||document.getElementsByName(_2[i])[0];
if(_3==null){
continue;
}
if(_3.type=="text"){
_3.onfocus=function(_4){
var e=_4||window.event;
var _5=e.target||e.srcElement;
_5.className="input focus";
if(this.name=="verifyCode"){
var _6=document.getElementById("vcImageTR");
if(_6){
_6.style.display="";
}
}
};
_3.onblur=function(_7){
var e=_7||window.event;
var _8=e.target||e.srcElement;
_8.className="input";
};
}
if(_3.name=="uid"||_3.name=="password"){
_3.onkeyup=function(){
uidPasswordChanged();
};
}
if(!_3.value){
var _9=getCookie(_3.name);
if(_9){
_3.value=_9;
}
}
if(_3.name==_1){
_3.focus();
}
}
(document.getElementById("vcImage")||{}).onclick=function(){
this.src=this.src.replace(/&rand=[\w\-\.]+/,"&rand="+Math.random());
};
(document.getElementById("vcHint")||{}).href="javascript:document.getElementById('vcImage').onclick();";
var _a=document.getElementById("homepage");
if(_a){
if(document.all){
_a.href="javascript:void(0);";
_a.style.behavior="url(#default#homepage)";
_a.onclick=function(){
this.setHomePage(document.location);
};
}else{
_a.style.display="none";
}
}
uidPasswordChanged();
};
function getCookie(_b){
var _c=new RegExp("(^| )"+_b+"=([^;]*)(;|$)","gi");
var _d=_c.exec(document.cookie);
return unescape((_d||[])[2]||"");
};
function setCookie(_e,_f){
document.cookie=_e+"="+escape(_f)+";expires="+(new Date(1990,1,1)).toGMTString();
document.cookie=_e+"="+escape(_f)+";path=/"+";expires="+(new Date(2099,12,31)).toGMTString();
};
function loginSubmit(_10,_11){
if((document.getElementById("saveUsername")||{checked:true}).checked){
setCookie("uid",document.getElementById("uid").value);
if(document.getElementById("domain")){
setCookie("domain_name",jQ("#domain").val());
}
}
if(document.getElementById("locale")){
setCookie("locale",document.getElementById("locale").value);
}
var _12=document.getElementById("speedtest");
if(_12){
var _10=document.getElementById("loginForm");
var _13=_10.action;
var _14=getCookie("netSpeedServerHost");
if(_14){
var _15=location.protocol+"//"+_14;
if(location.pathname){
_15+=location.pathname;
}
_10.action=_15;
}
}
var _16=(document.getElementsByName("useSSL")[0]||{}).checked;
if(typeof (_16)=="boolean"){
var _17=_16?"http://":"https://";
var _18=_16?"https://":"http://";
_10.action=(function translateProtocol(url){
if(url.charAt(0)=="/"){
if(location.protocol+"//"!=_18){
return _18+location.hostname+url;
}
return url;
}
if(url.substring(0,_17.length).toLowerCase()==_17){
var _19=url.indexOf("/",_17.length);
var _1a=url.lastIndexOf(":",_19);
if(_19>0&&_1a>0&&url.substring(_1a+1,_19).match(/^\d+$/)){
return _18+url.substring(_17.length,_1a)+url.substring(_19);
}else{
return _18+url.substring(_17.length);
}
}
return url;
})(_10.action);
}
if((document.getElementById("face_classic_cgi")||{}).selected){
if(document.all){
_11.returnValue=false;
}
document.getElementById("classic_cgi_form").elements["user"].value=_10.elements["uid"].value;
document.getElementById("classic_cgi_form").elements["pass"].value=_10.elements["password"].value;
document.getElementById("classic_cgi_form").submit();
return false;
}
return true;
};
function recoverPwd(_1b){
_1b.href+="?uid="+document.getElementById("uid").value;
};
function bookmarkMe(){
try{
window.external.AddFavorite(location.href,document.title);
}
catch(e){
alert(markme_msg);
}
};
function uidPasswordChanged(){
var _1c=document.getElementById("verifyCellCode");
var _1d=document.getElementById("sendVerifyCellCodeLink");
if(_1d==null||_1d==null){
return;
}
var _1e=["uid","password"];
for(var i=0,len=_1e.length;i<len;i++){
var _1f=document.getElementById(_1e[i])||document.getElementsByName(_1e[i])[0];
if(_1f.value==""){
_1c.disabled=true;
_1d.onclick=null;
return;
}
}
_1c.disabled=false;
_1d.onclick=submitSendVerifyCellCode;
};
function submitSendVerifyCellCode(){
var _20=document.getElementById("loginForm");
var _21=document.getElementsByName("action:sendVerifyCellCode")[0];
if(_21){
_21.disabled=false;
document.getElementById("verifyCellCode").value="";
_20.submit();
}
};

