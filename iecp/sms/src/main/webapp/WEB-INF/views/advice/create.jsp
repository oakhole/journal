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
        <div class="left-column left">
            <div class="dash">
                <form class="form-horizontal sms_form" role="form" method="post" action="${ctx}/advice/create">
                    <legend></legend>
                    <div class="form-group">
                        <label class="control-label col-sm-2">公告标题</label>
                        <div class="col-sm-10">
                            <textarea id="title" class="form-control" name="title" rows="1" required></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">公告内容</label>
                        <div class="col-sm-10">
                            <textarea id="content" class="form-control" name="content" rows="8"
                            required></textarea>
                        </div>
                    </div>
                    <legend></legend>
                    <div class="col-sm-offset-2">
                        <button type="reset" class="btn btn-default btn-sm">重置</button>
                        <button type="submit" class="btn btn-primary btn-sm">提交</button>
                    </div>
                </form>
            </div>
        </div>
        <aside class="right-column left">

        </aside>
        <div class="clearfix"></div>
        </div>
    </div>
</div>
