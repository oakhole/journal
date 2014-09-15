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
            <a href="${ctx}/advice/create" class="right btn btn-default btn-sm"><span class="glyphicon glyphicon-plus text-muted"></span> 发布公告</a>
        </form>
        <table class="table table-hover list-table">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody style="table-layout:fixed; width:890px;">
                <tr>

                    <c:forEach items="${advices.content}" var="advice">
                        <tr id="${advice.id}">
                            <td colspan=3><span class="h5">
                                <span class="toolbar"><span class="glyphicon glyphicon-share-alt"></span></span>
                                <span class="h5">${advice.title} <span class="h6 text-muted">${advice.content}</span></span>
                            </td>
                            <td class="text-center">
                                <span>${advice.publishTime}</span>
                            </td>
                            <td class="text-center">
                                <span class="badge badge-default">${advice.readTimes}</span>
                            </td>
                        </tr>
                    </c:forEach>

                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="5">
                        <c:if test="${advices.totalPages == 0}">
                            没有查询到任何相关数据.
                        </c:if>
                        <c:if test="${advices.totalPages > 0}">
                            <ul class="pager">
                                <c:if test="${advices.number > 0}">
                                    <li><a href="${ctx}/advice?pageNumber=${advices
                                    .number-1}&pageSize=${pageSize}&sortDirection=${sortDirection}&sortBy=${sortBy
                                    }&${searchParams}">上一页</a></li>
                                </c:if>
                                <c:if test="${advices.number < advices.totalPages-1}">
                                    <li><a href="${ctx}/advice?pageNumber=${advices
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
</div>

<script>
    $("tbody tr").on("click",function(){
        location.href = "${ctx}/advice/show/" + this.id;
    });
</script>