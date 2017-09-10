<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="background-color: #adfdbd; margin-top: -30px; padding-top: 10px;">
    <div class="row">

            <div class="col-md-12">
                <div class="list-group">
                    <div class="col-md-12">
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-body text-center">
                                    <h2><spring:message code="common.booking_done"/></h2>
                                    <hr>
                                    <h4><spring:message code="common.trip_id"/> <strong>${booking.id}</strong>
                                        . <spring:message code="common.booking_compl_add"/>
                                        <c:if test="${not empty anonEmail}"><c:out value="${anonEmail}"/></c:if>
                                        <c:if test="${empty anonEmail}"><c:out value="${user.email}"/></c:if>
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="panel-group text-center">
                            <div class="panel panel-default text-center">
                                <div class="panel-heading text-center">
                                    <h3><spring:message code="common.summary"/></h3>
                                </div>
                                <div class="panel-body text-center">
                                    <div class="row">
                                        <div class="col-md-2"></div>
                                        <div class="col-md-8">
                                            <h4><strong><spring:message code="common.hotel_info"/></strong></h4>
                                            <table class="table table-responsive" style="margin: 0 auto">
                                                <tr>
                                                    <td><c:out value="${hotel.name}"/></td>
                                                </tr>
                                                <tr>
                                                    <td><c:out value="${hotel.address}"/></td>
                                                </tr>
                                                <tr>
                                                    <td><c:out value="${hotel.phone}"/></td>
                                                </tr>
                                            </table>
                                            <br>
                                            <br>
                                            <h4><strong><spring:message code="common.summary_sub_1"/></strong></h4>
                                            <table class="table table-responsive" style="margin: 0 auto">
                                                <c:forEach items="${placement.option}" var="apartment">
                                                    <tr>
                                                        <td>
                                                            <c:out value="${apartment.key.personNum}"/>
                                                            <spring:message code="common._person"/>
                                                            <c:out value="${apartment.key.category}"/>
                                                            <spring:message code="common.with"/>
                                                            <c:out value="${apartment.key.bedsArrangement}"/>
                                                        </td>
                                                        <td>x<strong>${fn:length(apartment.value)}</strong>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <i class="fa fa-btc"></i>
                                                            <strong>${apartment.value[0].price}</strong> <spring:message code="common.per_night"/>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                            <br>
                                            <br>
                                            <h4><strong><spring:message code="common.booking_info"/></strong></h4>
                                            <table class="table table-responsive" style="margin: 0 auto">
                                                <tr>
                                                    <td><spring:message code="subBookings.personNum"/></td>
                                                    <td><c:out value="${placementPersonNum}"/></td>
                                                </tr>
                                                <tr>
                                                    <td><spring:message code="super_bookings.apartmentsNum"/></td>
                                                    <td><c:out value="${placementApartmentNum}"/></td>
                                                </tr>
                                                <tr>
                                                    <td><spring:message code="hotels.checkIn"/></td>
                                                    <td><c:out value="${placementInDate}"/>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${hotel.checkIn}"/></td>
                                                </tr>
                                                <tr>
                                                    <td><spring:message code="hotels.checkOut"/></td>
                                                    <td><c:out value="${placementOutDate}"/>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${hotel.checkOut}"/></td>
                                                </tr>
                                                <tr>
                                                    <td><spring:message code="common.total_sum"/></td>
                                                    <td><i class="fa fa-btc"></i> <c:out value="${placementSum}"/>0</td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="col-md-2"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="panel-group text-center">
                            <div class="panel panel-default text-center">
                                <div class="panel-body text-center">
                                    <div class="row">
                                        <div class="col-md-2"></div>
                                        <div class="col-md-8 text-center">
                                            <h3><spring:message code="common.booker_info"/></h3>
                                            <br>
                                            <table class="table table-responsive">
                                                <tr>
                                                    <td><c:out value="${user.name}"/></td>
                                                    <c:if test="${not empty anonEmail}">
                                                        <td><c:out value="${anonEmail}"/></td>
                                                    </c:if>
                                                    <c:if test="${empty anonEmail}">
                                                        <td><c:out value="${user.email}"/></td>
                                                    </c:if>
                                                    <td><c:out value="${user.phone}"/></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="col-md-2"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

    </div>
</div>


</body>
</html>

<jsp:include page="fragments/footer.jsp"/>

