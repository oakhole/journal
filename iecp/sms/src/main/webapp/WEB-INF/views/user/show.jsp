<%@ page contentType="text/html;charset=UTF-8"%>

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
        <div class="btn-toolbar">
            <div class="btn-group btn-group-sm">
                <a href="javascript:history.back();" class="btn btn-default"><span class="glyphicon glyphicon-arrow-left"></span> 返回</a>
            </div>
            <div class="btn-group btn-group-sm">
                <a href="${ctx}/user/update/${user.id}" class="btn btn-default">编辑</a>
                <a href="#" class="btn btn-default">禁用</a>
                <a href="${ctx}/user/delete?ids=${user.id}," class="btn btn-default">删除</a>
            </div>
            <div class="btn-group btn-group-sm">
                <a href="#" class="btn btn-default">查看日志</a>
            </div>
        </div>
        <div class="col-sm-8 text-left">
            <form class="form-horizontal" method="post" action="#">
                <fieldset>
                    <legend></legend>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-5">
                            <p class="form-control-static">${user.name}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">性别</label>
                        <div class="col-sm-5">
                            <p class="form-control-static">男</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">
                            手机
                        </label>
                        <div class="col-sm-5">
                            <p class="form-control-static">1380000000</p>
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                生日
                            </label>
                            <div class="col-sm-5">
                                <p class="form-control-static">1991-11-07</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                编号
                            </label>
                            <div class="col-sm-5">
                                <p class="form-control-static">JSB-018-039</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                联系电话
                            </label>
                            <div class="col-sm-5">
                                <p class="form-control-static">035-88888888-03</p>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend class="text-info"></legend>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">帐号</label>
                        <div class="col-sm-5">
                            <p class="form-control-static"><a href="mailto:#">${user.email}</a></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">
                            职务
                        </label>
                        <div class="col-sm-5">
                            <p class="form-control-static">${user.name}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">所属部门</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">总经办;技术部/测试;客服部;</p>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="col-sm-3 col-sm-offset-1 text-left">
            <img class="avatar img-thumbnail" src="${ctx}/assets/img/avatar.gif" alt="avatar"/>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
<div class="clearfix"></div>
