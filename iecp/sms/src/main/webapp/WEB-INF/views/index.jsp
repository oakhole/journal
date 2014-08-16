<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Index Page</title>
	</head>
	<body>
		<div class="main wd">
			<div class="content">
				<div class="left-column left">
					<div class="dash">
						<h1 class="welcome">欢迎您，<shiro:principal property="loginName"/></h1>
						<div class="advice">
							<p class="text-muted">以下为近期公告，请留意最新动态信息</p>
							<p class="advice-info"><a href="#">该公司决定从12月12日开始涨价</a></p>
							<p class="advice-info"><a href="#">此处最多可显示三条公告</a></p>
							<p class="advice-info"><a href="#">更多公告信息请移步公告管理部分</a></p>
						</div>
						<div class="board text-center">
							<div class="col-xs-4">
								<a href="${ctx}/smsTask/create" class="col-xs-12"><span class="glyphicon
								glyphicon-phone"></span>
								发送短信</a>
							</div>
							<div class="col-xs-4">
								<a href="${ctx}/smsReceive/" class="col-xs-12"><span class="glyphicon
								glyphicon-envelope"></span>
								收件箱</a>
							</div>
							<div class="col-xs-4">
								<a href="${ctx}/smsTask" class="col-xs-12"><span class="glyphicon
								glyphicon-send"></span>
								已发送</a>
							</div>
							<div class="col-xs-4">
								<a href="${ctx}/advice" class="col-xs-12"><span class="glyphicon
								glyphicon-bell"></span> 近期公告</a>
							</div>
							<div class="col-xs-4">
								<a href="${ctx}/syslog" class="col-xs-12"><span class="glyphicon
								glyphicon-calendar"></span>
								日志</a>
							</div>
							<div class="col-xs-4">
								<a href="${ctx}/statistics" class="col-xs-12"><span class="glyphicon
								glyphicon-stats"></span>
								统计报表</a>
							</div>
						</div>
					</div>
				</div>
				<aside class="right-column left">
					<section>
						<h4>合作伙伴</h4>
						<img src="${ctx}/assets/img/operator/logo_cmcc.png" class="img-rounded">
					</section>
					<section>
						<h4>合作伙伴</h4>
						<img src="${ctx}/assets/img/operator/logo_cucc.png" class="img-rounded">
					</section>
					<section>
						<h4>合作伙伴</h4>
						<img src="${ctx}/assets/img/operator/logo_ctcc.png" class="img-rounded">
					</section>
				</aside>
				<div class="clearfix"></div>
			</div>
		</div>
	</body>
</html>