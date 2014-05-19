<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>用户列表</title>
	<script>
	    $(function(){
	    //删除按钮监听
	        $("#btn_delete").click(function(){
                //遍历checkbox读取user.id
               var ids = "";
               $("input[type='checkbox'][name='user_id']:checked").each(function(){
                    ids = ids + $(this).val() + ",";
               });
               if(ids != ""){
                    location.href="${ctx}/user/delete?ids="+ids;
               }else{
                    alert("请先选中一项再进行删除操作.");
               }
                   return false;
	        });

            //为inputForm注册validate函数
            $("#inputForm").validate({
                rules: {
                    username: {
                        remote: "${ctx}/register/checkUsername"
                    }
                },
                messages: {
                    username: {
                        remote: "用户登录名已存在"
                    }
                }
            });
	    });
	</script>
</head>
<body>
	<div class="content-box">
                <div class="content-box-header">
                    <h3>用户管理</h3>
                    <ul class="content-box-tabs">
                        <li><a href="#tab1" class="default-tab">列表</a></li> <!-- href must be unique and match the id of target div -->
                        <li><a href="#tab2">添加</a></li>
                    </ul>
                    <div class="clear"></div>
                </div>
<div class="content-box-content">

    <c:if test="${not empty message}">
	    <div class="notification ${returnStatus} png_bg">
            <a href="#" class="close"><img src="${ctx}/static/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
            <div>${message}</div>
        </div>
    </c:if>

    <div class="tab-content default-tab" id="tab1">
		<div class="content-box-search">
			<form action="#">
			 	登录名：<input type="text" class="text-input normal-input" name="search_LIKE_username" value="${param.search_LIKE_username}">
			   &nbsp; 邮件名：<input type="text" class="text-input normal-input" name="search_EQ_email" value="${param.search_EQ_email}">
			   &nbsp; <input type="submit" class="button" value="查询"/> &nbsp;<input id="btn_delete" type="button" class="btn" value="删除"/>
		    </form>
	    </div>
    <table>
		<thead>
		<tr>
		    <th><input class="check-all" type="checkbox" /></th>
			<th>登录名</th>
			<th>姓名</th>
			<th>电邮</th>
			<th>角色</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		</thead>
		 <tfoot>
            <tr>
                <td colspan="7">
                    <div class="pagination">
                        <a href="#" title="First Page">&laquo; First</a><a href="#" title="Previous Page">&laquo; Previous</a>
                        <a href="#" class="number" title="1">1</a>
                        <a href="#" class="number" title="2">2</a>
                        <a href="#" class="number current" title="3">3</a>
                        <a href="#" class="number" title="4">4</a>
                        <a href="#" title="Next Page">Next &raquo;</a><a href="#" title="Last Page">Last &raquo;</a>
                    </div> <!-- End .pagination -->
                    <div class="clear"></div>
                </td>
            </tr>
        </tfoot>
		<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
			    <th><input type="checkbox" value="${user.id}" name="user_id"/></th>
				<td>${user.username}</td>
				<td>${user.name}</td>
				<td>${user.email}</td>
				<td>${user.roleNames}</td>
				<td>${allStatus[user.status]}</td>
				<td>
					<shiro:hasPermission name="user:edit">
						<a href="${ctx}/user/update/${user.id}" title="Edit"><img src="${ctx}/static/images/icons/pencil.png" alt="Edit" /></a>
                        <a href="${ctx}/user/delete?ids=${user.id}," title="Delete"><img src="${ctx}/static/images/icons/cross.png" alt="Delete" /></a>
                        <a href="${ctx}/menu/assign/${user.id}" title="Edit Meta"><img src="${ctx}/static/images/icons/hammer_screwdriver.png" alt="Edit Meta" /></a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>		
	</table>
	</div>
	<div class="tab-content" id="tab2">
        <form id="inputForm" action="${ctx}/register" method="post" class="form-horizontal">
        	<p>
                <label for="loginName">登录名:</label>
                <input type="text" id="loginName" name="username" class="text-input small-input required" minlength="3"/>
            </p>
            <p>
                <label for="name">用户名:</label>
                <input type="text" id="name" name="name" class="text-input small-input required"/>
            </p>
            <p>
                <label for="email">邮箱:</label>
                <input type="text" id="email" name="email" class="text-input small-input email required"/>
            </p>
            <p>
                <label for="plainPassword">密码:</label>
                <input type="password" id="plainPassword" name="plainPassword" class="text-input small-input required"/>
            </p>
            <p>
                <label for="confirmPassword">确认密码:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" class="text-input small-input required" equalTo="#plainPassword"/>
            </p>
            <p>
                <input id="submit_btn" class="button" type="submit" value="提交"/>&nbsp;
                <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
            </p>
        	</form>
	</div>
</div>
</div>
</body>
</html>
