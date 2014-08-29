<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<div class="main wd">
    <div class="path b_size">
        <ul>
            <li class="home" style="z-index:100;"><a href="${ctx}/">短信管理</a></li>
            <li><a href="${ctx}/smsReceive">已发送</a></li>
        </ul>
    </div>
    <div class="content">
        <form class="form-inline toolbar" role="form" action="${ctx}/smsReceive/" method="post">
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
                    <th>状态</th>
                    <th>内容</th>
                    <th>时间</th>
                </tr>
            </thead>
            <tbody style="table-layout:fixed; width:890px;">
                <tr>
                    <td><span class="label label-default">default</span></td>
                    <td>公告内容</td>
                    <td>2014-08-08 12:12:12</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3">
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
</div>

<script type="text/javascript">
    $('.input-daterange').datepicker({
        language: "zh-CN",
        autoclose: true,
        format: "yyyy-mm-dd",
        todayBtn: "linked",
        todayHighLight: true
    });
</script>
