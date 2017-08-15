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


            <table class="table table-striped display" id="aptTypesDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="aptType.personNum"/></th>
                    <th><spring:message code="aptType.category"/></th>
                    <th><spring:message code="aptType.bedsArrangement"/></th>
                    <th><spring:message code="aptType.hotelsUsing"/></th>
                    <th><spring:message code="aptType.apartmentsAppliedTo"/></th>
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
                    <th><spring:message code="commod.inDate"/></th>
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


            <div class="modal fade" id="editRow">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h2 class="modal-title" id="modalTitle"></h2>
                        </div>
                        <div class="modal-body">
                            <form:form class="form-horizontal" id="detailsForm">
                            <input type="hidden" id="id" name="id">

                            <div class="form-group">
                                <label for="dateTime" class="control-label col-xs-3"><spring:message
                                        code="meal.dateTime"/></label>

                                <div class="col-xs-9">
                                    <input class="form-control" id="dateTime" name="dateTimeUI"
                                           placeholder="<spring:message code="meal.dateTime"/>">
                                </div>
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
            </div>

            <div class="modal fade" id="editRow">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h2 class="modal-title" id="modalTitle"></h2>
                        </div>
                        <div class="modal-body">
                            <form:form class="form-horizontal" id="detailsForm">
                            <input type="hidden" id="id" name="id">

                            <div class="form-group">
                                <label for="dateTime" class="control-label col-xs-3"><spring:message
                                        code="meal.dateTime"/></label>

                                <div class="col-xs-9">
                                    <input class="form-control" id="dateTime" name="dateTimeUI"
                                           placeholder="<spring:message code="meal.dateTime"/>">
                                </div>
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
            </div>

            <div class="modal fade" id="editRow">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h2 class="modal-title" id="modalTitle"></h2>
                        </div>
                        <div class="modal-body">
                            <form:form class="form-horizontal" id="detailsForm">
                            <input type="hidden" id="id" name="id">

                            <div class="form-group">
                                <label for="dateTime" class="control-label col-xs-3"><spring:message
                                        code="meal.dateTime"/></label>

                                <div class="col-xs-9">
                                    <input class="form-control" id="dateTime" name="dateTimeUI"
                                           placeholder="<spring:message code="meal.dateTime"/>">
                                </div>
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
            </div>

            <div class="modal fade" id="editRow">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h2 class="modal-title" id="modalTitle"></h2>
                        </div>
                        <div class="modal-body">
                            <form:form class="form-horizontal" id="detailsForm">
                            <input type="hidden" id="id" name="id">

                            <div class="form-group">
                                <label for="dateTime" class="control-label col-xs-3"><spring:message
                                        code="meal.dateTime"/></label>

                                <div class="col-xs-9">
                                    <input class="form-control" id="dateTime" name="dateTimeUI"
                                           placeholder="<spring:message code="meal.dateTime"/>">
                                </div>
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
            </div>

            <div class="modal fade" id="editRow">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h2 class="modal-title" id="modalTitle"></h2>
                        </div>
                        <div class="modal-body">
                            <form:form class="form-horizontal" id="detailsForm">
                            <input type="hidden" id="id" name="id">

                            <div class="form-group">
                                <label for="dateTime" class="control-label col-xs-3"><spring:message
                                        code="meal.dateTime"/></label>

                                <div class="col-xs-9">
                                    <input class="form-control" id="dateTime" name="dateTimeUI"
                                           placeholder="<spring:message code="meal.dateTime"/>">
                                </div>
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
            </div>

            <div class="modal fade" id="editRow">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h2 class="modal-title" id="modalTitle"></h2>
                        </div>
                        <div class="modal-body">
                            <form:form class="form-horizontal" id="detailsForm">
                            <input type="hidden" id="id" name="id">

                            <div class="form-group">
                                <label for="dateTime" class="control-label col-xs-3"><spring:message
                                        code="meal.dateTime"/></label>

                                <div class="col-xs-9">
                                    <input class="form-control" id="dateTime" name="dateTimeUI"
                                           placeholder="<spring:message code="meal.dateTime"/>">
                                </div>
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
            </div>

        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

