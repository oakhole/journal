// JScript 文件
function LoadAjaxIco(text)
    {
    try{
        if(text == null || text =="")
            text = "正在加载";
        var element = document.createElement("span");
        var img = document.createElement("img");
        var spn = document.createElement("span");
        spn.innerHTML = " &nbsp;" + text + "...";
        spn.style.color="#000000";
        img.setAttribute("src","images/btn_lookup_resolving.gif");
        img.setAttribute("align","middle");
        img.setAttribute("border","0px");
        element.setAttribute("id","AjaxIcoDiv");
        element.style.position = "absolute";
        element.style.textAlign = "left";
        element.style.padding = "4px 4px 4px 4px";
        element.style.top = document.body.scrollTop + "px"; 
        element.style.width = "200";
        element.style.height = "26px";
        element.style.backgroundColor= "#FFCC00";
        //element.style.left = (document.body.scrollWidth - 195) + "px";//这里还需处理.
        element.style.left = "0px";//这里还需处理.

        element.appendChild(img);
        element.appendChild(spn);
        document.body.appendChild(element);
    }
    catch(e){}
    }

    //卸载AJAX提示
    function UnLoadAjaxIco()
    {
    try{
    document.body.removeChild(document.getElementById("AjaxIcoDiv"));
    }
    catch(e){}
    }
