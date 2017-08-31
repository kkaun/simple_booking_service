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
                <spring:message code="manager_panel"/>
            </li>
            <li><hr></li>
            <li>
                <a class="restNavLi" href="hotel_manager/object/show_chart">
                    <h4><spring:message code="common.calendar"/>/
                        <br>
                        <spring:message code="chart_only"/></h4>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="hotel_manager/object/show_bookings">
                    <h4><spring:message code="common.manage_bookings"/></h4>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="hotel_manager/object/show_hotel_votes">
                    <h4><spring:message code="common.view_feedback"/></h4>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="hotel_manager/object/show_apartments">
                    <h4><spring:message code="common.manage_apts"/></h4>
                </a>
            </li>
            <li><hr></li>
            <li><hr></li>
        </ul>
    </div>
</div>

</body>
</html>
