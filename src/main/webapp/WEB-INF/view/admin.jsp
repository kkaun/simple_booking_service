<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container">
    <div class="row" style="text-align: center">

        <div class="col-md-5">
            <div class="well">
                <h3>Users</h3>
            </div>
            <div class="well">
                <h3>Bookings</h3>
            </div>
            <div class="well">
                <h3>Votes</h3>
            </div>
        </div>

        <div class="col-md-2"></div>

        <div class="col-md-5">
            <a href="cities">
                <div class="well">
                    <h3>Cities</h3>
                </div>
            </a>
            <div class="well">
                <h3>Hotels</h3>
            </div>
            <div class="well">
                <h3>Apt. Types</h3>
            </div>
        </div>

    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

