<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

<div class="modal fade" id="superBookingEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h2 class="modal-title" id="superBookingModalTitle"></h2>
            </div>
            <div class="modal-body">

                <%--<a class="btn btn-primary" onclick="viewBookings()">--%>
                    <%--<span class="" aria-hidden="true"></span>--%>
                    <%--<spring:message code="bookings.view"/>--%>
                <%--</a>--%>

                <table class="table table-striped display" id="bookingsDatatable">
                    <thead>
                    <tr>
                        <th><spring:message code="common.id"/></th>
                        <th><spring:message code="common.inDate"/></th>
                        <th><spring:message code="common.outDate"/></th>
                        <th><spring:message code="common.sum"/></th>
                        <th><spring:message code="apt_types.personNum"/></th>
                        <th><spring:message code="apt_types.category"/></th>
                        <th><spring:message code="apt_types.bedsArrangement"/></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>

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
                                            <input class="form-control in_date" id="bookingInDate" name="inDate"
                                                   placeholder="<spring:message code="common.inDate"/>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="bookingOutDate" class="control-label col-xs-3"><spring:message
                                                code="common.outDate"/></label>
                                        <div class="col-xs-9">
                                            <input class="form-control out_date" id="bookingOutDate" name="outDate"
                                                   placeholder="<spring:message code="common.outDate"/>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <c:if test="${not empty objectApartments}">
                                            <label for="bookingApt" class="control-label col-xs-3"><spring:message
                                                code="apt_types.personNum"/></label>
                                            <div class="col-xs-9">
                                                <select class="form-control" name="aptId" id="bookingApt">
                                                    <c:forEach items="${objectApartments}" var="objectApartment">
                                                        <option value="${objectApartment.id}">${objectApartment.stringAptType}
                                                            ; ${objectApartment.price}0 $ / night
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </c:if>
                                        <c:if test="${empty objectApartments}">
                                            <label for="existingBookingApt" class="control-label col-xs-3"><spring:message
                                                    code="bookings.apartment"/></label>
                                            <div class="col-xs-9">
                                                <input class="form-control out_date" id="existingBookingApt" name="aptId"
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

                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="sbDateAdded" class="control-label col-xs-3"><spring:message
                                code="common.dateAdded"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="sbDateAdded" name="dateAdded"
                                   placeholder="<spring:message code="common.dateAdded"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sbInDate" class="control-label col-xs-3"><spring:message
                                code="common.inDate"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="sbInDate" name="inDate"
                                   placeholder="<spring:message code="common.inDate"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sbOutDate" class="control-label col-xs-3"><spring:message
                                code="common.outDate"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="sbOutDate" name="outDate"
                                   placeholder="<spring:message code="common.outDate"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sbAptNum" class="control-label col-xs-3"><spring:message
                                code="super_bookings.apartmentsNum"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="sbAptNum" name="apartmentsNum"
                                   placeholder="<spring:message code="super_bookings.apartmentsNum"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sbUserName" class="control-label col-xs-3"><spring:message
                                code="booker.name"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="sbUserName" name="userName"
                                   placeholder="<spring:message code="booker.name"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sbUserEmail" class="control-label col-xs-3"><spring:message
                                code="booker.email"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="sbUserEmail" name="userEmail"
                                   placeholder="<spring:message code="booker.email"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sbUserPhone" class="control-label col-xs-3"><spring:message
                                code="booker.phone"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="sbUserPhone" name="userPhone"
                                   placeholder="<spring:message code="booker.phone"/>" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveSuperBooking()">
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
<jsp:include page="i18nUtil.jsp"/>
</html>