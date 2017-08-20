<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/head_tags/restUserHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
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
    </div>

</div>


<jsp:include page="fragments/superBookingEditModal.jsp"/>


<div class="modal fade" id="userVoteEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="userVoteModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="voteDateAdded" class="control-label col-xs-3"><spring:message
                                code="common.dateAdded"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="voteDateAdded" name="dateAdded"
                                   placeholder="<spring:message code="common.dateAdded"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="voteRate" class="control-label col-xs-3"><spring:message
                                code="common.rate"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="voteRate" name="rate"
                                   placeholder="<spring:message code="common.rate"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="voteUserComment" class="control-label col-xs-3"><spring:message
                                code="common.comment"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="voteUserComment" name="userComment"
                                   placeholder="<spring:message code="common.comment"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="voteUName" class="control-label col-xs-3"><spring:message
                                code="common.bookerName"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="voteUName" name="user.name"
                                   placeholder="<spring:message code="common.bookerName"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button"
                                    onclick="saveUserVote()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>