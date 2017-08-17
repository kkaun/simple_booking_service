<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-3">
            <jsp:include page="fragments/adminSidebar.jsp"/>
        </div>

        <div class="col-md-9">
            <jsp:include page="fragments/adminFilters.jsp"/>

            <a id="adminAptTypeAddBtn" class="btn btn-primary" onclick="addAptType()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"> Add Apartment Type </span>
                <spring:message code="common.add"/>
            </a>

            <a id="adminHotelAddBtn" class="btn btn-primary" onclick="addHotel()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"> dd hotel </span>
                <spring:message code="common.add"/>
            </a>

            <a id="adminRegionAddBtn" class="btn btn-primary" onclick="addRegion()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"> Add Region </span>
                <spring:message code="common.add"/>
            </a>

            <a id="adminUserAddBtn" class="btn btn-primary" onclick="addUser()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"> Add User </span>
                <spring:message code="common.add"/>
            </a>

            <table class="table table-striped display" id="aptTypesDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="apt_types.personNum"/></th>
                    <th><spring:message code="apt_types.category"/></th>
                    <th><spring:message code="apt_types.bedsArrangement"/></th>
                    <th><spring:message code="apt_types.hotelsUsing"/></th>
                    <th><spring:message code="apt_types.apartmentsAppliedTo"/></th>
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
                    <th><spring:message code="users.phone"/></th>
                    <th><spring:message code="users.roles"/></th>
                    <th><spring:message code="user.active"/></th>
                    <th><spring:message code="users.registered"/></th>
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
                </tr>
                </thead>
            </table>

            <jsp:include page="fragments/adminEditModals.jsp"/>
            <jsp:include page="fragments/superBookingEditModal.jsp"/>

        </div>
    </div>
</div>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>

