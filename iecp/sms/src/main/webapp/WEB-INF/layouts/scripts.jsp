<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="taglibs.jsp"%>

 <!-- Logout Modal -->
<div id="logoutModal" class="modal fade">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">✗</button>
            </div>
            <div class="modal-body">
                是否退出？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
                    <a href="${ctx}/logout" class="btn btn-primary">是</a>
            </div>
        </div>
    </div>
</div>
<!--Scripts Reference -->
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.js"></script>
<script src="${ctx}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/plugins/zTree/jquery.ztree.all-3.5.min.js"></script>
<script src="${ctx}/assets/scripts/left_nav_tree.js"></script>
<script src="${ctx}/assets/scripts/messages_cn.js"></script>
<script src="${ctx}/assets/scripts/list_table.js"></script>
<script src="${ctx}/assets/scripts/form.js"></script>
<script src="${ctx}/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="${ctx}/assets/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
