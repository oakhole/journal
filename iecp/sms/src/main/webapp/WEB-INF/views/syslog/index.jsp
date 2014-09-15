<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<div class="main wd">
    <aside>
        <div class="left-nav left">
            <ul class="nav nav-statcked">
                <li>
                    <a href="${ctx}/syslog/" class="active"><span class="glyphicon glyphicon-tag"> 登陆日志</a>
                        <span class="arrow_right"></span>
                </li>
                <li>
                    <a href="${ctx}/syslog/income"><span class="glyphicon glyphicon-tag"> 充值日志</a>
                </li>
                <li>
                    <a href="${ctx}/syslog/withdraw"><span class="glyphicon glyphicon-tag"> 消费日志</a>
                </li>
            </ul>
        </div>
    </aside>
    <div class="right-info left">
        <section>
            <h4>登陆日志</h4>
            <p>可查询登陆时间、ip地址等信息</p>
        </section>

        <c:if test="${message != null}">
            <div class="alert alert-warning alert-dismissible">
                <button class="close" data-dismiss="alert">✗</button>
                ${message}
            </div>
        </c:if>

        <form class="form-inline toolbar" role="form" action="${ctx}/syslog/" method="post">
            <div class="form-group input-daterange">
                <label class="sr-only">开始日期</label>
                <input type="text" class="form-control input-sm" value="" readonly placeholder="开始日期" name="start">
                <label class="sr-only">结束日期</label>
                <input type="text" class="form-control input-sm" value="" readonly placeholder="结束日期" name="end">
            </div>
            <button class="btn btn-default btn-sm" type="submit">查询</button>
        </form>
        <table class="table table-hover list-table">
            <thead>
                <tr>
                    <th>用户名</th>
                    <th>登陆时间</th>
                    <th>ip地址</th>
                    <th>最后登陆方式</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach items="${signLogs.content}" var="signLog">
                    <tr>
                        <td><a href="#"><span class="glyphicon glyphicon-user"></span>
                        ${signLog.username}</a></td>
                        <td>${signLog.signTime}</td>
                        <td>${signLog.signIp}</td>
                        <td>${signTypes[signLog.signType]}</td>
                    </tr>
                </c:forEach>

            </tbody>
            <tfoot>
                <tr>
                    <td colspan="4">
                        <c:if test="${signLogs.totalPages == 0}">
                            没有查询到任何相关数据.
                        </c:if>
                        <c:if test="${signLogs.totalPages > 0}">
                            <ul class="pager">
                                <c:if test="${signLogs.number > 0}">
                                    <li><a href="${ctx}/syslog?pageNumber=${signLogs
                                    .number-1}&pageSize=${pageSize}&sortDirection=${sortDirection}&sortBy=${sortBy
                                    }&${searchParams}">上一页</a></li>
                                </c:if>
                                <c:if test="${signLogs.number < signLogs.totalPages-1}">
                                    <li><a href="${ctx}/syslog?pageNumber=${signLogs
                                     .number+1}&pageSize=${pageSize}&sortDirection=${sortDirection}&sortBy=${sortBy
                                     }&${searchParams}">下一页</a></li>
                                </c:if>
                            </ul>
                        </c:if>
                    </td>
                </tr>
            </tfoot>
        </table>
    </div>
    <div class="clearfix"></div>
</div>
    <div class="clearfix"></div>

<script type="text/javascript">
    $('.input-daterange').datepicker({
        language: "zh-CN",
        autoclose: true,
        format: "yyyy-mm-dd",
        todayBtn: "linked",
        todayHighLight: true
    });
</script>