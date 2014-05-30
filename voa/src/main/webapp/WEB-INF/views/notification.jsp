<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div>
    <div class="notification attention png_bg">
        <a href="#" class="close"><img src="static/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
        <div>
            Attention notification. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate, sapien quis fermentum luctus, libero.
        </div>
    </div>

    <div class="notification information png_bg">
        <a href="#" class="close"><img src="static/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
        <div>
            Information notification. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate, sapien quis fermentum luctus, libero.
        </div>
    </div>

    <div class="notification success png_bg">
        <a href="#" class="close"><img src="static/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
        <div>
            Success notification. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate, sapien quis fermentum luctus, libero.
        </div>
    </div>

    <div class="notification error png_bg">
        <a href="#" class="close"><img src="static/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
        <div>
            Error notification. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate, sapien quis fermentum luctus, libero.
        </div>
    </div>
</div>