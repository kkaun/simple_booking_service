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
                    <spring:message code="side.admin"/>
                </div>
                <div class="col-md-3" style="padding-top: 8px">
                    <span class="pull-right" id="slide-submenu">
                        <i class="fa fa-times"></i>
                    </span>
                </div>
            </div>
        </div>
        <a href="admin/show_hotels" class="list-group-item">
            <strong><spring:message code="side.hotels"/></strong><i class="fa fa-building fa-pull-right" aria-hidden="true"></i>
        </a>
        <a href="admin/show_users" class="list-group-item">
            <strong><spring:message code="side.users"/></strong><i class="fa fa-users fa-pull-right" aria-hidden="true"></i>
        </a>
        <a href="admin/show_bookings" class="list-group-item">
            <strong><spring:message code="side.bookings"/></strong><i class="fa fa-ticket fa-pull-right" aria-hidden="true"></i>
        </a>
        <a href="admin/show_votes" class="list-group-item">
            <strong><spring:message code="side.votes"/></strong><i class="fa fa-comments-o fa-pull-right" aria-hidden="true"></i>
        </a>
        <a href="admin/show_apt_types" class="list-group-item">
            <strong><spring:message code="side.apt_types_short"/></strong><i class="fa fa-bed fa-pull-right" aria-hidden="true"></i>
        </a>
        <a href="admin/show_regions" class="list-group-item">
            <strong><spring:message code="side.regions"/></strong><i class="fa fa-globe fa-pull-right" aria-hidden="true"></i>
        </a>
    </div>
</div>

</body>
</html>