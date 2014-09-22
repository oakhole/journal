<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<div class="main wd">
    <div class="path b_size">
        <ul>
            <li class="home" style="z-index:100;"><a href="${ctx}/">短信管理</a></li>
            <li class="home" style="z-index:100;"><a href="${ctx}/smsTask/create">发送短信</a></li>
        </ul>
    </div>
    <div class="content">
        <div class="left-column left">
            <div class="dash">
                <form id="inputForm" class="form-horizontal sms_form" role="form" method="post" action="${ctx}/smsTask/create">
                    <legend></legend>
                    <div class="form-group">
                        <label class="control-label col-sm-2">短信内容</label>
                        <div class="col-sm-10">
                            <textarea id="content" class="form-control" name="content" rows="4" required maxlength="500"></textarea>
                            <span class="pull-right">共<span id="badge_content" class="badge">0</span>字</span>
                        </div>
                    </div>

                    <input type="hidden" id="content_count" name="content_count" value=""/>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <a href="#" class="btn btn-default btn-sm" data-toggle="modal"
                            data-target="#txt_import">txt导入号码</a>
                            <a href="#" class="btn btn-default btn-sm" data-toggle="modal"
                            data-target="#excel_import">excel导入号码</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">发送号码</label>
                        <div class="col-sm-10">
                            <textarea id="phoneNumbers" class="form-control" name="phoneNumbers" rows="8"
                            required></textarea>
                            <span class="pull-right">共<span id="badge_phone" class="badge">0</span>个有效号码
                        </div>
                    </div>

                    <input type="hidden" id="phone_count" name="phone_count" value="" required/>

                    <div class="form-group">
                        <label class="control-label col-sm-2">发送时间</label>
                        <div class="col-sm-5">
                            <div class="input-group date">
                                <input type="text" class="form-control input-sm form-datetime" readonly name="planTime"
                                placeholder="默认为空，即立刻发送" value=""/>
                                <span class="input-group-addon"><i id="datetime-remove" class="glyphicon
                                glyphicon-remove"></i></span>
                            </div>
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

<!-- 上传文件modal -->
<div id="txt_import" class="modal fade">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">✗</button>
            </div>
            <div class="modal-body">
                <form id="import-txt" class="form" enctype="multipart/form-data">
                    <input type="file" name="fileupload"/>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
                <button id="btn-submit-txt" type="button" class="btn btn-primary btn-sm" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>

<div id="excel_import" class="modal fade">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
               <button class="close" data-dismiss="modal">✗</button>
            </div>
            <div class="modal-body">
                <form id="import-excel" class="form" enctype="multipart/form-data">
                    <input type="file" name="fileupload"/>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
                <button id="btn-submit-excel" type="button" class="btn btn-primary btn-sm" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

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

    // 字数统计及内容校验
    $("#content").focus();

    function calculate_content(){
        len = $("#content").val().length;
        $("#badge_content").html(len);
        $("#content_count").val(len);

        // 检查屏蔽词
        if(len > 350){
        }
    }

    $("#content").keyup(calculate_content);
    $("#content").mousemove(calculate_content);


    // 号码个数统计

    function calculate_phoneNumbers(){
        phones = $("#phoneNumbers").val().trim();
        len = 0;
        if(phones.length > 0){
            phoneArray = phones.split("\n");
            var regPartton=/^1[3-8]+\d{9}$/;
            for(var phone in phoneArray){
                if(regPartton.test(phoneArray[phone])){
                    if(phoneArray[phone].length == 11){
                        len = len + 1;
                    }
                }
            }
        }

        $("#badge_phone").html(len);
        $("#phone_count").val(len);
    }

    $("#phoneNumbers").keyup(calculate_phoneNumbers);
    $("#phoneNumbers").mousemove(calculate_phoneNumbers);

    $("#inputForm").on("submit",function(){
        phone_count = $("#phone_count").val();
        content_count = $("#content_count").val();
        if(phone_count < 1){
            alert("有效号码个数为0");
            return false;
        }

        if(content_count > 350){
            alert("当前短信内容字数为：" + content_count + " ,字数最大为：350");
            return false;
        }

        charge_count = Math.ceil(content_count / 70)  * phone_count;

        if(window.confirm("计费条数为：" + charge_count + " , 确认提交？")){
            return true;
        }else{
            return false;
        }
    });


    // 文件上传
    $("#btn-submit-txt").on("click",function(){
        $.get("${ctx}/smsTask/uploadPhoneFile","",function(data){
        });
    });

    $("#btn-submit-excel").on("click",function(){
        alert("btn-submit-txt");
    });
</script>