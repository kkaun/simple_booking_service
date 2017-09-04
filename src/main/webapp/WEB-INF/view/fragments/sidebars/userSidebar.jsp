<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

<div class="sidebar">
    <div class="mini-submenu">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </div>
    <div class="list-group">
        <div href="#" class="list-group-item">
            <div class="row">
                <div class="col-md-9">
                    <spring:message code="common.user_panel"/>
                </div>
                <div class="col-md-3" style="padding-top: 8px">
            <span class="pull-right" id="slide-submenu">
                <i class="fa fa-times"></i>
            </span>
                </div>
            </div>
        </div>
        <a href="user/show_bookings" class="list-group-item">
            <strong><spring:message code="side.my_bookings"/></strong><i class="fa fa-ticket fa-pull-right" aria-hidden="true"></i>
        </a>
        <a href="user/show_hotels" class="list-group-item">
            <strong><spring:message code="side.hotels"/></strong><i class="fa fa-building fa-pull-right" aria-hidden="true"></i>
        </a>
        <a href="user/show_votes" class="list-group-item">
            <strong><spring:message code="side.my_feedback"/></strong><i class="fa fa-comments-o fa-pull-right" aria-hidden="true"></i>
        </a>
    </div>
</div>

</body>
</html>
