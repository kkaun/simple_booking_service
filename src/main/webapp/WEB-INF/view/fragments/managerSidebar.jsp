<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<div class="sidebar-nav-fixed affix adminSideBar">
    <div class="well">
        <ul class="nav ">
            <li class="menu-head">
                MANAGER MENU <a href="#" class="push_menu"><span
                    class="glyphicon glyphicon-align-justify pull-right"></span></a>
            </li>

            <li>
                <a href="<c:url value='/manager/chart'/>">Calendar
                    <i class="fa fa-user-circle fa-pull-right" aria-hidden="true"></i>
                    <i class="fa fa-bell-o fa-pull-right" aria-hidden="true"></i>
                </a>
            </li>
            <li>
                <a href="<c:url value='/manager/super_bookings'/>">Bookings
                    <i class="fa fa-cube fa-pull-right" aria-hidden="true"></i>
                    <i class="fa fa-bell-o fa-pull-right" aria-hidden="true"></i>
                </a>
            </li>
            <li>
                <a href="<c:url value='/manager/hotel_votes'/>">Feedback
                    <i class="fa fa-envelope-open-o fa-pull-right" aria-hidden="true"></i>
                    <i class="fa fa-bell-o fa-pull-right" aria-hidden="true"></i>
                </a>
            </li>
            <li>
                <hr>
                <a href="<c:url value='/manager/object'/>">Manage Object Info
                    <i class="fa fa-commenting-o fa-pull-right" aria-hidden="true"></i></a>
            </li>
            <li>
                <hr>
                <a href="<c:url value='/manager/apartments'/>">Manage Apartments
                    <i class="fa fa-paper-plane-o fa-pull-right" aria-hidden="true"></i></a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
