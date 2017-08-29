<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<jsp:include page="fragments/head_tags/restHeadTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cross_role/bookingsDatatable.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <sec:authorize access="isAuthenticated()">
                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                        <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
                </sec:authorize>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <sec:authorize access="hasAuthority('ROLE_MANAGER')">
                    <jsp:include page="fragments/sidebars/managerSidebar.jsp"/>
                </sec:authorize>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <sec:authorize access="hasAuthority('ROLE_USER')">
                    <jsp:include page="fragments/sidebars/userSidebar.jsp"/>
                </sec:authorize>
            </sec:authorize>
        </div>

        <div class="col-md-10">
            <div class="row" style="height: 70px">
                <div class="col-md-4">
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                            <a id="backBtn" class="btn btn-lg btn-primary pull-left" href="admin/show_super_bookings"
                               style="margin: 0 auto;">
                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>&nbsp;&nbsp;
                                <spring:message code="common.submit_return"/>
                            </a>
                        </sec:authorize>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasAuthority('ROLE_MANAGER')">
                            <a id="backBtn" class="btn btn-lg btn-primary pull-left" href="hotel_manager/object/show_super_bookings"
                               style="margin: 0 auto;">
                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>&nbsp;&nbsp;
                                <spring:message code="common.submit_return"/>
                            </a>
                        </sec:authorize>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasAuthority('ROLE_USER')">
                            <a id="backBtn" class="btn btn-lg btn-primary pull-left" href="user/show_bookings"
                               style="margin: 0 auto;">
                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>&nbsp;&nbsp;
                                <spring:message code="common.submit_return"/>
                            </a>
                        </sec:authorize>
                    </sec:authorize>
                </div>
                <div class="col-md-8">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong><spring:message code="common.sub_bookings_info"/></strong></h4>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered display" id="bookingsDatatable">
                            <thead>
                            <tr>
                                <th><spring:message code="common.id"/></th>
                                <th><spring:message code="common.inDate"/></th>
                                <th><spring:message code="common.outDate"/></th>
                                <th><spring:message code="bookings.sum"/></th>
                                <th><spring:message code="apt_types.personNum"/>,
                                    <spring:message code="apt_types.category"/>,
                                    <spring:message code="apt_types.bedsArrangement"/></th>
                                <th></th>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<div class="modal fade" id="bookingEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h2 class="modal-title" id="bookingModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="bookingInDate" class="control-label col-xs-3"><spring:message
                                code="common.inDate"/></label>
                        <div class="col-xs-9">
                            <input class="form-control in_date" id="bookingInDate" name="aptInDate"
                                   placeholder="<spring:message code="common.inDate"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bookingOutDate" class="control-label col-xs-3"><spring:message
                                code="common.outDate"/></label>
                        <div class="col-xs-9">
                            <input class="form-control out_date" id="bookingOutDate" name="aptOutDate"
                                   placeholder="<spring:message code="common.outDate"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <c:if test="${not empty objectApartments && fn:length(objectApartments) >= 2}">
                            <label for="bookingApt" class="control-label col-xs-3">
                                <spring:message code="apt_types.personNum"/>,
                                <spring:message code="apt_types.category"/>,
                                <spring:message code="apt_types.bedsArrangement"/></label>
                            <div class="col-xs-9">
                                <select class="form-control" name="stringAptType" id="bookingApt">
                                    <c:forEach items="${objectApartments}" var="objectApartment">
                                        <option value="${objectApartment.stringAptType}" name="stringAptType">
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                        <c:if test="${empty objectApartments || fn:length(objectApartments) < 2}">
                            <label for="existingBookingApt" class="control-label col-xs-3">
                                <spring:message code="apt_types.personNum"/>,
                                <spring:message code="apt_types.category"/>,
                                <spring:message code="apt_types.bedsArrangement"/></label>
                            <div class="col-xs-9">
                                <input class="form-control" id="existingBookingApt" name="stringAptType"
                                       placeholder="<spring:message code="bookings.apartment"/>" readonly>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="bookingAptPrice" class="control-label col-xs-3"><spring:message
                                code="apartments.price"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="bookingAptPrice" name="aptPrice"
                                   placeholder="<spring:message code="apartments.price"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bookingSum" class="control-label col-xs-3"><spring:message
                                code="bookings.sum"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="bookingSum" name="sum"
                                   placeholder="<spring:message code="bookings.sum"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button"
                                    onclick="saveBooking()"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
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

