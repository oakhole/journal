<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<div class="main wd">
    <div class="path b_size">
        <ul>
            <li class="home" style="z-index:100;"><a href="${ctx}/">短信管理</a></li>
            <li class="home" style="z-index:100;"><a href="${ctx}/smsTask/update/${smsTask.id}">任务修改</a></li>
        </ul>
    </div>
    <div class="content">
        <div class="left-column left">
            <div class="dash">
                <form class="form-horizontal sms_form" role="form" method="post" action="${ctx}/smsTask/update">
                    <legend></legend>
                    <div class="form-group">
                        <label class="control-label col-sm-2">短信内容</label>
                        <div class="col-sm-10">
                            <textarea id="content" class="form-control" name="content" rows="4" required>${smsTask.content}</textarea>
                            <span class="pull-right">共<span id="badge_content" class="badge">0</span>字</span>
                        </div>
                    </div>
                    <input type="hidden" name="id" value="${smsTask.id}"/>
                    <input type="hidden" name="phoneNumbers" value=""/>
                    <div class="form-group">
                        <label class="control-label col-sm-2">发送时间</label>
                        <div class="col-sm-5">
                            <div class="input-group date">
                                <input type="text" class="form-control input-sm form-datetime" readonly name="sendTime"
                                placeholder="默认为空，即立刻发送" value="${smsTask.sendTime}"/>
                                <span class="input-group-addon"><i id="datetime-remove" class="glyphicon
                                glyphicon-remove"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">发送状态</label>
                        <div class="col-sm-5">
                            <select name="sendStatus" class="form-control input-sm">
                                <c:forEach items="${allSendStatus}" var="item">
                                    <c:if test="${item.key == smsTask.sendStatus}">
                                        <option value="${item.key}" selected>${item.value}</option>
                                    </c:if>
                                    <c:if test="${item.key != smsTask.sendStatus}">
                                        <option value="${item.key}">${item.value}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <legend></legend>
                    <div class="col-sm-offset-2">
                        <a class="btn btn-default btn-sm" href="javascript:history.back();">返回</a>
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

<!-- 上传文件modal -->
<div id="txt_import" class="modal fade">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">✗</button>
            </div>
            <div class="modal-body">
                xxxxxxxx
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a href="${ctx}/" class="btn btn-primary">确认</a>
            </div>
        </div>
    </div>
</div>

<div id="excel_import" class="modal fade">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">✗</button>
            </div>
            <div class="modal-body">
                xxxxxxxx
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a href="${ctx}/" class="btn btn-primary">确认</a>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(function(){
        calculate_content();
    });

    // 日期控件
    $('.form-datetime').datetimepicker({
        language: "zh-CN",
        autoclose: true,
        format: "yyyy-mm-dd hh:ii:00",
        todayBtn: "linked",
        todayHighLight: true
    });

    $('#datetime-remove').on('click',function(){
        $('.form-datetime').val("");
    });

    // 检查屏蔽词
    function checkBadwords(){
        var content = $("#content").val();
        if(content && content.length > 0){
            console.log(content);
        }
    }

    // 字数统计及内容校验
    $("#content").focus();

    function calculate_content(){
        len = $("#content").val().length;
        $("#badge_content").html(len);
    }

    $("#content").on("change",checkBadwords);

    $("#content").keyup(calculate_content);
    $("#content").mousemove(calculate_content);

</script>