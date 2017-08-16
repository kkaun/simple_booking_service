<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-default">
                    <h2>${userTo.name}'s <spring:message code="${register ? 'app.register' : 'app.profile'}"/></h2>

                    <form:form modelAttribute="userTo" class="form-horizontal" method="post"
                               action="${register ? 'register' : 'profile'}"
                               charset="utf-8" accept-charset="UTF-8">

                        <spring:message code="user.name" var="userName"/>
                        <custom:inputField label='${userName}' name="name"/>

                        <spring:message code="user.email" var="userEmail"/>
                        <custom:inputField label='${userEmail}' name="email"/>

                        <spring:message code="users.password" var="userPhone"/>
                        <custom:inputField label='${userPhone}' name="password" inputType="password"/>

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


            <sec:authorize access="hasRole('ROLE_USER')">

                <div class="col-md-6 pull-right">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3>Your Bookings</h3></div>
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
                        </tr>
                        </thead>
                    </table>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="row">

                        <div class="col-md-6 pull-left">
                            <div class="panel panel-default">
                                <div class="panel-heading"><h4>Visited Hotels</h4></div>
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
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>

                        <div class="col-md-6 pull-right">
                            <div class="panel panel-default">
                                <div class="panel-heading"><h4>Your Feedback</h4></div>
                                <table class="table table-striped display" id="userVotesDatatable">
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

                        <jsp:include page="fragments/userEditModals.jsp"/>
                        <jsp:include page="fragments/superBookingEditModal.jsp"/>

                    </div>
                </div>

            </sec:authorize>


            <sec:authorize access="hasRole('ROLE_HOTEL_MANAGER')">
                <div class="col-md-6 pull-left">
                    <div class="panel panel-default">
                        <form:form class="form-horizontal" id="hotelEditForm" method="post"
                                   action="editHotelByManager">
                            <input type="hidden" id="id" name="id">
                            <div class="form-group">
                                <label for="hotelName" class="control-label col-xs-3"><spring:message
                                        code="common.hotelName"/></label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="hotelName" name="name"
                                           placeholder="<spring:message code="common.hotelName"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="stars" class="control-label col-xs-3"><spring:message
                                        code="common.hotelStars"/></label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="stars" type="number" name="stars"
                                           placeholder="<spring:message code="common.hotelStars"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="hotelDescription" class="control-label col-xs-3"><spring:message
                                        code="hotels.description"/></label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="hotelDescription" name="description"
                                           placeholder="<spring:message code="hotels.description"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="checkIn" class="control-label col-xs-3"><spring:message
                                        code="hotels.checkIn"/></label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="checkIn" name="checkIn"
                                           placeholder="<spring:message code="hotels.checkIn"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="checkOut" class="control-label col-xs-3"><spring:message
                                        code="hotels.checkOut"/></label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="checkOut" name="checkOut"
                                           placeholder="<spring:message code="hotels.checkOut"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address" class="control-label col-xs-3"><spring:message
                                        code="hotels.address"/></label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="address" name="address"
                                           placeholder="<spring:message code="hotels.address"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="hotelPhone" class="control-label col-xs-3"><spring:message
                                        code="hotels.phone"/></label>

                                <div class="col-xs-9">
                                    <input class="form-control" id="hotelPhone" name="phone"
                                           placeholder="<spring:message code="hotels.phone"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-offset-3 col-xs-9">
                                    <button class="btn btn-primary" type="button" onclick="save()">
                                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                    </button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </sec:authorize>

        </div>
    </div>
</div>

</body>
</html>
<jsp:include page="fragments/footer.jsp"/>
