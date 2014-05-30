<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户注册</title>
    <script>
        $(function(){
            //初始化菜单树
             var setting = {
                        check: {
                            enable: true
                        },
                        data: {
                            simpleData: {
                                enable: true
                            }
                        },
                        callback:{
                            onClick:function(e){
                               e.preventDafault();
                            }
                        }
                    };
            $.get("${ctx}/menu",{"id":${id}},function(data){
                $.fn.zTree.init($("#treeDemo"), setting, data);
            });
            function onCheck(e,treeId,treeNode){
                if(treeNode.checked){
                    alert(treeNode.id + " checked");
                }else{
                    alert(treeNode.id + " uncheck");
                }
            }
        });
    </script>

</head>

<body>
<div class="content-box column-left">
    <div class="content-box-header">
        <h3>权限分配</h3>
    </div>
    <div class="clear"></div>
    <div class="content-box-content">
        <div class="tab-content default-tab">
            <div id="permission">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
    </div>
</div>
<div class="clear"></div>
</body>
</html>
