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
                USER MENU
            </li>
            <li><hr></li>
            <li>
                <a class="restNavLi" href="user/show_bookings">
                    <h4>My Bookings</h4>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="user/show_hotels">
                    <h4>Visited Objects</h4>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="user/show_votes">
                    <h4>My Votes</h4>
                </a>
            </li>
            <li><hr></li>
            <li><hr></li>
        </ul>
    </div>
</div>

</body>
</html>
