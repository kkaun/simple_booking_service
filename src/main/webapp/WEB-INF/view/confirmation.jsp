<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<jsp:include page="fragments/head_tags/jspHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="background-color: lightgreen; margin-top: -30px; padding-top: 10px;">
    <div class="row">

            <div class="col-md-12">
                <div class="list-group">
                    <div class="col-md-12">
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-body text-center">
                                    <h2>Booking is done</h2>
                                    <hr>
                                    <h4>Your trip ID is : <strong>${superBooking.id}</strong>. All fake details will
                                        (not)
                                        be
                                        sent
                                        to ${user.email}</h4>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="panel-group text-center">
                            <div class="panel panel-default text-center">
                                <div class="panel-heading text-center">
                                    <h3>Summary</h3>
                                </div>
                                <div class="panel-body text-center">
                                    <div class="row">
                                        <div class="col-md-2"></div>
                                        <div class="col-md-8">
                                            <h4><strong>Hotel info:</strong></h4>
                                            <table class="table table-responsive" style="margin: 0 auto">
                                                <tr>
                                                    <td>${hotel.name}</td>
                                                </tr>
                                                <tr>
                                                    <td>${hotel.address}</td>
                                                </tr>
                                                <tr>
                                                    <td>${hotel.phone}</td>
                                                </tr>
                                            </table>
                                            <br>
                                            <br>
                                            <h4><strong>List of booked rooms(numbers):</strong></h4>
                                            <table class="table table-responsive" style="margin: 0 auto">
                                                <c:forEach items="${placement.option}" var="apartment">
                                                    <tr>
                                                        <td>
                                                                ${apartment.key.personNum}-person
                                                                ${apartment.key.category}
                                                            with ${apartment.key.bedsArrangement}
                                                        </td>
                                                        <td>x<strong>${fn:length(apartment.value)}</strong>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <i class="fa fa-btc"></i>
                                                            <strong>${apartment.value[0].price}</strong> / night
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                            <br>
                                            <br>
                                            <h4><strong>Booking info:</strong></h4>
                                            <table class="table table-responsive" style="margin: 0 auto">
                                                <tr>
                                                    <td>No. of person</td>
                                                    <td>${placementPersonNum}</td>
                                                </tr>
                                                <tr>
                                                    <td>No. of rooms</td>
                                                    <td>${placementApartmentNum}</td>
                                                </tr>
                                                <tr>
                                                    <td>Check-in</td>
                                                    <td>${placementInDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${hotel.checkIn}</td>
                                                </tr>
                                                <tr>
                                                    <td>Check-out</td>
                                                    <td>${placementOutDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${hotel.checkOut}</td>
                                                </tr>
                                                <tr>
                                                    <td>Total sum</td>
                                                    <td>${placementSum}</td>
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
                                        <div class="3"></div>
                                        <div class="col-md-6 text-center">
                                            <h3> Booker's info </h3>
                                            <br>
                                            <table class="table table-responsive">
                                                <tr>
                                                    <td>${user.name}</td>
                                                    <td>${user.email}</td>
                                                    <td>${user.phone}</td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="3"></div>
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

