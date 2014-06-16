<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- Page Head -->
			<h2>Welcome John</h2>
			<p id="page-intro">What would you like to do?</p>

			<ul class="shortcut-buttons-set">

				<li><a class="shortcut-button" href="#"><span>
					<img src="static/images/icons/pencil_48.png" alt="icon" /><br />
					Write an Article
				</span></a></li>

				<li><a class="shortcut-button" href="#"><span>
					<img src="static/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
					Create a New Page
				</span></a></li>

				<li><a class="shortcut-button" href="#"><span>
					<img src="static/images/icons/image_add_48.png" alt="icon" /><br />
					Upload an Image
				</span></a></li>

				<li><a class="shortcut-button" href="#"><span>
					<img src="static/images/icons/clock_48.png" alt="icon" /><br />
					Add an Event
				</span></a></li>

				<li><a class="shortcut-button" href="#messages" rel="modal"><span>
					<img src="static/images/icons/comment_48.png" alt="icon" /><br />
					Open Modal
				</span></a></li>

			</ul><!-- End .shortcut-buttons-set -->

            <div class="clear"></div>
