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
                    <li class="home" style="z-index:100;"><a href="${ctx}/">短信管理</a></li>
                    <li><a href="${ctx}/smsTask/create">发送短信</a></li>
                </ul>
            </div>
		</div>
	</body>
</html>