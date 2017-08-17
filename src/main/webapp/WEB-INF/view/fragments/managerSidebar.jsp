<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body>

<div class="sidebar-nav-fixed affix adminSideBar">
    <div class="well">
        <ul class="nav ">
            <li class="menu-head">
                MANAGER MENU <a href="#" class="push_menu"><span
                    class="glyphicon glyphicon-align-justify pull-right"></span></a>
            </li>
            <li>
                <a onclick="showHotelChart()">Calendar/Chart
                </a>
            </li>
            <li>
                <a onclick="showManagerSuperBookings()">Bookings
                </a>
            </li>
            <li>
                <a onclick="showManagerHotelVotes()">Feedback
                </a>
            </li>
            <li>
                <hr>
                <a href="<c:url value='/object'/>">Manage Object Info</a>
            </li>
            <li>
                <hr>
                <a onclick="showManagerApartments()">Manage Apartments</a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
