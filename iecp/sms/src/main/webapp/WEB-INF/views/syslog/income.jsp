<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<div class="main wd">
    <aside>
        <div class="left-nav left">
            <ul class="nav nav-statcked">
                <li>
                    <a href="${ctx}/syslog/"><span class="glyphicon glyphicon-tag"> 登陆日志</a>
                </li>
                <li>
                    <a href="${ctx}/syslog/income" class="active"><span class="glyphicon glyphicon-tag"> 充值日志</a>
                     <span class="arrow_right"></span>
                </li>
                <li>
                    <a href="${ctx}/syslog/withdraw"><span class="glyphicon glyphicon-tag"> 消费日志</a>
                </li>
                <li>
                    <a href="${ctx}/syslog/interface"><span class="glyphicon glyphicon-tag"> 接口调用日志</a>
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
                <tr>
                    <td><input type="checkbox"/> <a href="show.html"><span class="glyphicon glyphicon-user"></span> admin</a></td>
                    <td>2014-08-08 10:10:10</td>
                    <td>192.168.0.1</td>
                    <td>网页</td>
                </tr>
                <tr>
                    <td><input type="checkbox"/> <a href="show.html"><span class="glyphicon glyphicon-user"></span> agent</a></td>
                    <td>2014-08-08 10:10:10</td>
                    <td>192.168.0.1</td>
                    <td>网页</td>
                </tr>
                <tr>
                    <td><input type="checkbox"/> <a href="show.html"><span class="glyphicon glyphicon-user"></span> user</a></td>
                    <td>2014-08-08 10:10:10</td>
                    <td>192.168.0.1</td>
                    <td>接口</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="4">
                        <ul class="pagination pagination-sm right">
                            <li><a href="#">&laquo;</a></li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#">&raquo;</a></li>
                        </ul>
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