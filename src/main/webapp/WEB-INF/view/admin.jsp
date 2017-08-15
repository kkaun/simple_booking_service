<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container-fluid">
    <div class="row">

        <div class="col-md-3">
            <jsp:include page="fragments/adminSidebar.jsp"/>
        </div>

        <div class="col-md-9">
            <jsp:include page="fragments/adminFilters.jsp"/>

            <c:if test="${not empty aptTypeAddBtn}">
            <a class="btn btn-primary" onclick="addAptType()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                <spring:message code="common.add"/>
            </a>
            </c:if>

            <c:if test="${not empty hotelAddBtn}">
            <a class="btn btn-primary" onclick="addHotel()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                <spring:message code="common.add"/>
            </a>
            </c:if>

            <c:if test="${not empty regionAddBtn}">
            <a class="btn btn-primary" onclick="addRegion()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                <spring:message code="common.add"/>
            </a>
            </c:if>

            <c:if test="${not empty userAddBtn}">
            <a class="btn btn-primary" onclick="addUser()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                <spring:message code="common.add"/>
            </a>
            </c:if>


            <table class="table table-striped display" id="aptTypesDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="apt_type.personNum"/></th>
                    <th><spring:message code="apt_type.category"/></th>
                    <th><spring:message code="apt_type.bedsArrangement"/></th>
                    <th><spring:message code="apt_type.hotelsUsing"/></th>
                    <th><spring:message code="apt_type.apartmentsAppliedTo"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <table class="table table-striped display" id="hotelsDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="common.name"/></th>
                    <th><spring:message code="hotels.rating"/></th>
                    <th><spring:message code="hotels.stars"/></th>
                    <th><spring:message code="common.description"/></th>
                    <th><spring:message code="hotels.votesNum"/></th>
                    <th><spring:message code="hotels.checkIn"/></th>
                    <th><spring:message code="hotels.checkOut"/></th>
                    <th><spring:message code="hotels.city"/></th>
                    <th><spring:message code="common.country"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <table class="table table-striped display" id="regionsDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="common.name"/></th>
                    <th><spring:message code="common.country"/></th>
                    <th><spring:message code="common.description"/></th>
                    <th><spring:message code="regions.hotelNum"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <table class="table table-striped display" id="superBookingsDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="common.dateAdded"/></th>
                    <th><spring:message code="common.inDate"/></th>
                    <th><spring:message code="common.outDate"/></th>
                    <th><spring:message code="super_bookings.hotelId"/></th>
                    <th><spring:message code="super_bookings.hotelName"/></th>
                    <th><spring:message code="super_bookings.userId"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <table class="table table-striped display" id="usersDatatable">
                <thead>
                <tr>
                    <th><spring:message code="user.name"/></th>
                    <th><spring:message code="user.email"/></th>
                    <th><spring:message code="user.phone"/></th>
                    <th><spring:message code="user.roles"/></th>
                    <th><spring:message code="user.active"/></th>
                    <th><spring:message code="user.registered"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <table class="table table-striped display" id="votesDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="common.dateAdded"/></th>
                    <th><spring:message code="common.rate"/></th>
                    <th><spring:message code="common.comment"/></th>
                    <th><spring:message code="common.bookerName"/></th>
                    <th><spring:message code="common.userId"/></th>
                    <th><spring:message code="common.hotelId"/></th>
                    <th><spring:message code="common.hotelName"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

        </div>
    </div>
</div>


<jsp:include page="fragments/footer.jsp"/>

