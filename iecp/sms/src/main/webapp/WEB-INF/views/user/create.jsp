<%@ page contentType="text/html;charset=UTF-8"%>

<div class="main wd">
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
        </div>
        <form id="inputForm" class="form-horizontal" method="post" action="${ctx}/user/create">
            <fieldset>
                <legend class="text-info">成员信息</legend>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="text-danger">*</span> 姓名</label>
                    <div class="col-sm-5">
                        <input type="text" name="name" class="form-control input-sm required"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-5">
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="gender" value="male" checked/>
                                男
                            </label>
                        </div>
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="gender" value="female"/>
                                女
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">
                        职务
                    </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control input-sm" name="position"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">
                        手机
                    </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control input-sm" name="phone" number="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">
                        <a id="showMore">更多成员信息…</a>
                    </label>
                </div>
                <div id="moreInfo">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">
                            生日
                        </label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control input-sm" name="birth" value="${birth}" date="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">
                            编号
                        </label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control input-sm" name="workId"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">
                            联系电话
                        </label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control input-sm" name="telephone" number="true"/>
                        </div>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <legend class="text-info">帐号信息</legend>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="text-danger">*</span> 帐号</label>
                    <div class="col-sm-5">
                        <input type="text" name="username" class="form-control input-sm required"
                        maxlength="16"
                        minlength="3"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="text-danger">*</span> 密码</label>
                    <div class="col-sm-5">
                        <input id="plainPassword" type="password" name="plainPassword" class="form-control input-sm required"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="text-danger">*</span> 确认密码</label>
                    <div class="col-sm-5">
                        <input type="password" name="confirmPassword" class="form-control input-sm required" equalTo="#plainPassword"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="text-danger">*</span> 所属部门</label>
                    <div class="col-sm-5">
                        <input type="text" name="group" class="form-control input-sm required"/>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <div class="form-group">
                    <legend></legend>
                    <div class="col-sm-offset-2 text-left">
                        <input type="reset" class="btn btn-default btn-sm" value="重置"/>
                        <input type="submit" class="btn btn-primary btn-sm" value="确认"/>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="clearfix"></div>
</div>
<div class="clearfix"></div>

<script>
    $(document).ready(function() {
        //为inputForm注册validate函数
        $("#inputForm").validate({
            rules: {
                username: {
                    remote: "${ctx}/user/checkUsername"
                }
            },
            messages: {
                username: {
                    remote: "用户登录名已存在"
                }
            }
        });
    });
</script>
