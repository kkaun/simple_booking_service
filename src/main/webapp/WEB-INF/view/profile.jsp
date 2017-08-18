<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/restHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="panel panel-default text-center">
                <div class="panel-heading">
                    <h2><spring:message code="${register ? 'app.register' : 'app.profile'}"/> ${userTo.name}</h2>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="userTo" class="form-horizontal" method="post"
                               action="${register ? 'register' : 'profile'}"
                               charset="utf-8" accept-charset="UTF-8">

                        <spring:message code="user.name" var="userName"/>
                        <custom:inputField label='${userName}' name="name"/>

                        <spring:message code="user.email" var="userEmail"/>
                        <custom:inputField label='${userEmail}' name="email"/>

                        <spring:message code="users.phone" var="userPhone"/>
                        <custom:inputField label='${userPhone}' name="phone" inputType="text"/>

                        <spring:message code="users.password" var="userPassword"/>
                        <custom:inputField label='${userPassword}' name="password" inputType="password"/>

                        <div class="form-group">
                            <div class="col-xs-offset-2 col-xs-10">
                                <button type="submit" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>

    <div class="row">

        <sec:authorize access="isAuthenticated()">
            <sec:authorize access="hasRole('ROLE_USER')">

                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3>Your Bookings</h3></div>
                        <div class="panel-body">
                            <table class="table table-striped display" id="userSuperBookingsDatatable">
                                <thead>
                                <tr>
                                    <th><spring:message code="common.id"/></th>
                                    <th><spring:message code="common.dateAdded"/></th>
                                    <th><spring:message code="common.inDate"/></th>
                                    <th><spring:message code="common.outDate"/></th>
                                    <th><spring:message code="super_bookings.hotelId"/></th>
                                    <th><spring:message code="super_bookings.hotelName"/></th>
                                    <th><spring:message code="super_bookings.apartmentsNum"/></th>
                                    <th></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h4>Visited Hotels</h4></div>
                        <div class="panel-body">
                            <table class="table table-striped display" id="userHotelsDatatable">
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
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h4>Your Feedback &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4>

                        <a class="btn btn-primary" onclick="addUserVote()">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            <spring:message code="common.add"/>
                        </a>

                        </div>
                        <div class="panel-body">
                            <table class="table table-striped display" id="userVotesDatatable">
                                <thead>
                                <tr>
                                    <th><spring:message code="common.id"/></th>
                                    <th><spring:message code="common.dateAdded"/></th>
                                    <th><spring:message code="common.rate"/></th>
                                    <th><spring:message code="common.comment"/></th>
                                    <th><spring:message code="common.bookerName"/></th>
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

            </sec:authorize>
        </sec:authorize>

        <jsp:include page="fragments/userEditModals.jsp"/>
        <jsp:include page="fragments/superBookingEditModal.jsp"/>

    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>
