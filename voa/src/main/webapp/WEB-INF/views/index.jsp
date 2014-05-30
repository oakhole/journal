<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Index</title>
	</head>
	<body>
	    <%@ include file="/WEB-INF/layouts/header.jsp"%>
	    <div class="content-box column-left">
            <div class="content-box-header">
                <h3>Content box left</h3>
            </div> <!-- End .content-box-header -->
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <h4>Maecenas dignissim</h4>
                    <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in porta lectus. Maecenas dignissim enim quis ipsum mattis aliquet. Maecenas id velit et elit gravida bibendum. Duis nec rutrum lorem. Donec egestas metus a risus euismod ultricies. Maecenas lacinia orci at neque commodo commodo.
                    </p>
                </div> <!-- End #tab3 -->
            </div> <!-- End .content-box-content -->
        </div> <!-- End .content-box -->
        <div class="content-box column-right closed-box">
            <div class="content-box-header"> <!-- Add the class "closed" to the Content box header to have it closed by default -->
                <h3>Content box right</h3>
            </div> <!-- End .content-box-header -->
            <div class="content-box-content">
                <div class="tab-content default-tab">
                    <h4>This box is closed by default</h4>
                    <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in porta lectus. Maecenas dignissim enim quis ipsum mattis aliquet. Maecenas id velit et elit gravida bibendum. Duis nec rutrum lorem. Donec egestas metus a risus euismod ultricies. Maecenas lacinia orci at neque commodo commodo.
                    </p>
                </div> <!-- End #tab3 -->
            </div> <!-- End .content-box-content -->
        </div> <!-- End .content-box -->
        <div class="clear"/>
        <div id="messages" style="display: none"> <!-- Messages are shown when a link with these attributes are clicked: href="#messages" rel="modal"  -->
            <h3>3 Messages</h3>
            <ul id="treeDemo" class="ztree"></ul>
        </div> <!-- End #messages -->
	</body>
</html>
