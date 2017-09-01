<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

<div class="sidebar-nav-fixed affix" style="margin-left: -15px">
    <div class="well">
        <ul class="nav navbar restNavbar">
            <li class="menu-head text-center">
                <spring:message code="common.user_panel"/>
            </li>
            <li><hr></li>
            <li>
                <a class="restNavLi" href="user/show_bookings">
                    <div class="row">
                        <div class="col-md-2">
                            <i class="fa fa-circle-o fa-fw fa-pull-left" aria-hidden="true"></i>&nbsp;
                        </div>
                        <div class="col-md-10">
                            <h5><spring:message code="common.my_bookings"/></h5>
                        </div>
                    </div>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="user/show_hotels">
                    <i class="fa fa-circle-o fa-fw fa-pull-left" aria-hidden="true"></i>&nbsp;
                    <h5><spring:message code="common.visited_hotels"/></h5>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="user/show_votes">
                    <i class="fa fa-circle-o fa-fw fa-pull-left" aria-hidden="true"></i>&nbsp;
                    <h5><spring:message code="common.my_feedback"/></h5>
                </a>
            </li>
            <li><hr></li>
            <li><hr></li>
        </ul>
    </div>
</div>

</body>
</html>
