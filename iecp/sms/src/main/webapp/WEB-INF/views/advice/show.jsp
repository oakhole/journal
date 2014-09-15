<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title></title>
	</head>
	<body>
		<div class="main wd">
			<div class="path b_size">
                <ul>
                    <li class="home" style="z-index:100;"><a href="${ctx}/toolbox">工具箱</a></li>
                    <li><a href="${ctx}/advice">公告管理</a></li>
                </ul>
            </div>
            <h2>${advice.title}</h2>
            <span>${advice.content}</span>
		</div>
	</body>
</html>