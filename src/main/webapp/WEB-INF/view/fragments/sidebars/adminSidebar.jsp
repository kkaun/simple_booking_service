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
                ADMIN MENU
            </li>
            <li>
                <hr>
                <a onclick="showAdminHotels()">Hotels
                </a>
            </li>
            <li>
                <a onclick="showAdminUsers()">Users
                </a>
            </li>
            <li>
                <a onclick="showAdminSuperBookings()">Bookings
                </a>
            </li>
            <li>
                <a onclick="showAdminVotes()">Feedback
                </a>
            </li>
            <li>
                <a onclick="showAdminAptTypes()">Apt. Types
                </a>
            </li>
            <li>
                <a onclick="showAdminRegions()">Regions
                </a>
            </li>
        </ul>
    </div>
</div>

</body>
</html>