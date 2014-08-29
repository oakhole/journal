<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<div class="main wd">
    <div class="path b_size">
        <ul>
            <li class="home" style="z-index:100;"><a href="${ctx}/toolbox">工具箱</a></li>
            <li><a href="${ctx}/advice">公告管理</a></li>
        </ul>
    </div>
    <div class="content">
        <form class="form-inline toolbar" role="form" action="${ctx}/advice/" method="post">
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
