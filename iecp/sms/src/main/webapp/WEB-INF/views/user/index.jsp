<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<div class="main wd">
    <div class="path b_size">
        <ul>
            <li class="home" style="z-index:100;"><a href="${ctx}/toolbox">工具箱</a></li>
            <li><a href="${ctx}/user">用户管理</a></li>
        </ul>
    </div>
    <div class="left-nav left">
        <ul class="nav nav-tabs">
            <li class="active">
                <a href="#groups" data-toggle="tab"><span class="glyphicon glyphicon-user"> 组织架构</a>
            </li>
            <li>
                <a href="#roles" data-toggle="tab"><span class="glyphicon glyphicon-cog"> 角色权限</a>
            </li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="groups">
                <div id="treeDemo" class="ztree">
                </div>
            </div>
            <div class="tab-pane" id="roles">
                <div class="input-group">
                    <input type="text" placeholder="input group name" class="form-control"/>
                    <div class="input-group-addon">
                        <a href="#"><span class="glyphicon glyphicon-search"></span></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="right-info left">
        <section>
            <h4>总经办</h4>
            <p><a href="#" data-toggle="modal" data-target="#renameGroups">修改名字</a> | <a href="#" data-toggle="modal" data-target="#newChildGroup">新建子部门</a> | <a href="#" data-toggle="modal" data-target="#assignRoles">角色分配</a></p>
            <p class="text-muted">成员${users.totalElements}个</p>
        </section>
        <c:if test="${not empty message}">
            <div class="alert alert-${returnStatus} alert-dismissible">
                <button class="close" data-dismiss="alert">✗</button>
                ${message}
            </div>
        </c:if>
        <table class="table table-hover list-table">
            <thead>
                <tr>
                    <th colspan="3">
                        <input id="check-all" type="checkbox"/>
                        <div class="btn-group btn-group-sm">
                            <a href="${ctx}/user/create" class="btn btn-default">新增成员</a>
                            <div class="btn-group btn-group-sm">
                                <a class="btn btn-default dropdown-toggle" data-toggle="dropdown">更多操作 <span class="caret"></span></a>
                                  <ul class="dropdown-menu">
                                      <li><a href="#">显示别名</a></li>
                                      <li><a href="#">显示子成员</a></li>
                                  </ul>
                            </div>
                        </div>
                        <div class="btn-group btn-group-sm">
                            <div class="btn-group btn-group-sm toolkit">
                                <a href="#" class="btn btn-default">设置所在部门</a>
                                <a href="#" class="btn btn-default" data-toggle="modal" data-target="#assignRoles">角色分配</a>
                                <a href="#" class="btn btn-default">禁用</a>
                                <a href="#" class="btn btn-default">启用</a>
                                <a href="#" class="btn btn-default">删除</a>
                            </div>
                        </div>
                    </th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="user" items="${users.content}">
                    <tr>
                        <td><input type="checkbox" value="${user.id}"/> <a href="${ctx}/user/show/${user.id}"><span
                        class="glyphicon
                        glyphicon-user"></span> ${user.username}</a></td>
                        <td><a href="${ctx}/user/show/${user.id}">${user.email}</a></td>
                        <td>${user.name}</td>
                    </tr>
                </c:forEach>

            </tbody>
            <c:if test="${users.totalPages > 1}">
                <tfoot>
                    <tr>
                        <td colspan="3">
                            <c:if test="${users.totalPages == 0}">
                                没有查询到任何相关数据.
                            </c:if>
                            <c:if test="${users.totalPages > 0}">
                                <ul class="pager">
                                    <c:if test="${users.number > 0}">
                                        <li><a href="${ctx}/user?pageNumber=${users
                                        .number-1}&pageSize=${pageSize}&sortDirection=${sortDirection}&sortBy=${sortBy
                                        }&${searchParams}">上一页</a></li>
                                    </c:if>
                                    <c:if test="${users.number < users.totalPages-1}">
                                        <li><a href="${ctx}/user?pageNumber=${users
                                         .number+1}&pageSize=${pageSize}&sortDirection=${sortDirection}&sortBy=${sortBy
                                         }&${searchParams}">下一页</a></li>
                                    </c:if>
                                </ul>
                            </c:if>
                        </td>
                    </tr>
                </tfoot>
            </c:if>
        </table>
    </div>
    <div class="clearfix"></div>
</div>
<div class="clearfix"></div>

<!-- Modal -->
<div id="renameGroups" class="modal fade">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">✗</button>
                <h4>组织名称修改</h4>
            </div>
            <div class="modal-body">
                ....
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<!-- 新增子部门 -->
<div id="newChildGroup" class="modal fade">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">✗</button>
                <h4>新增子部门</h4>
            </div>
            <div class="modal-body">
                ....
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Assign Roles -->
<div id="assignRoles" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">✗</button>
                <h4>角色分配</h4>
            </div>
            <div class="modal-body">
                <div id="modalTree" class="ztree">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save Chaneges</button>
            </div>
        </div>
    </div>
</div>
