<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container-fluid">
    <div class="row">

        <div class="col-md-3">
            <jsp:include page="fragments/managerSidebar.jsp"/>
        </div>

        <div class="col-md-9">
            <jsp:include page="fragments/managerFilters.jsp"/>

            <c:if test="${not empty apartmentsAddBtn}">
                <a class="btn btn-primary" onclick="addApartment()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <spring:message code="common.add"/>
                </a>
            </c:if>


            <table class="table table-striped display" id="apartmentsDatatable">
                <thead>
                <tr>
                    <th><spring:message code="apt_type.personNum"/></th>
                    <th><spring:message code="apt_type.category"/></th>
                    <th><spring:message code="apt_type.bedsArrangement"/></th>
                    <th><spring:message code="common.pricePerNight"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <table class="table table-striped display" id="hotelSuperBookingsDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="common.dateAdded"/></th>
                    <th><spring:message code="super_bookings.inDate"/></th>
                    <th><spring:message code="super_bookings.outDate"/></th>
                    <th><spring:message code="user.name"/></th>
                    <th><spring:message code="user.email"/></th>
                    <th><spring:message code="user.phone"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <table class="table table-striped display" id="hotelVotesDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.dateAdded"/></th>
                    <th><spring:message code="common.rate"/></th>
                    <th><spring:message code="common.comment"/></th>
                    <th><spring:message code="common.bookerName"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <jsp:include page="fragments/managerEditModals.jsp"/>
            <jsp:include page="fragments/superBookingEditModal.jsp"/>

        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>


