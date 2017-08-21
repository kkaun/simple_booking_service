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
                MANAGER MENU
            </li>
            <li>
                <a onclick="showHotelChart()">Calendar/Chart
                </a>
            </li>
            <li>
                <a onclick="showManagerSuperBookings()">Manage Bookings
                </a>
            </li>
            <li>
                <a onclick="showManagerHotelVotes()">View Feedback
                </a>
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
