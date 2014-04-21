<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!Doctype html>
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
            <p>
                <strong>17th May 2009</strong> by Admin<br />
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue.
                <small><a href="#" class="remove-link" title="Remove message">Remove</a></small>
            </p>
            <p>
                <strong>2nd May 2009</strong> by Jane Doe<br />
                Ut a est eget ligula molestie gravida. Curabitur massa. Donec eleifend, libero at sagittis mollis, tellus est malesuada tellus, at luctus turpis elit sit amet quam. Vivamus pretium ornare est.
                <small><a href="#" class="remove-link" title="Remove message">Remove</a></small>
            </p>
            <p>
                <strong>25th April 2009</strong> by Admin<br />
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue.
                <small><a href="#" class="remove-link" title="Remove message">Remove</a></small>
            </p>
            <form action="" method="post">
                <h4>New Message</h4>
                <fieldset>
                    <textarea class="textarea" name="textfield" cols="79" rows="5"></textarea>
                </fieldset>
                <fieldset>
                    <select name="dropdown" class="small-input">
                        <option value="option1">Send to...</option>
                        <option value="option2">Everyone</option>
                        <option value="option3">Admin</option>
                        <option value="option4">Jane Doe</option>
                    </select>
                    <input class="button" type="submit" value="Send" />
                </fieldset>
            </form>
        </div> <!-- End #messages -->
	</body>
</html>
