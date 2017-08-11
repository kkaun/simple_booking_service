<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="well">

                <div class="col-md-12">
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-body">

                                <h2>Booking is done</h2>
                                <hr>
                                <h4>Your trip ID is : ${superBooking.id}. All fake details will (not) be sent
                                    to ${user.email}</h4>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Summary
                            </div>
                            <div class="panel-body text-center">
                                <table class="table table-responsive" style="margin: 0 auto">
                                    <caption>Summary</caption>
                                    <tr>
                                        <td>${hotel.name}</td>
                                    </tr>
                                    <tr>
                                        <td>${hotel.address}</td>
                                    </tr>
                                    <tr>
                                        <td>${hotel.phone}</td>
                                    </tr>
                                    <hr>
                                    <tr>
                                        <td>No. of person</td>
                                        <td>${placementPersonNum}</td>
                                    </tr>
                                    <tr>
                                        <td>No. of rooms</td>
                                        <td>${placementApartmentNum}</td>
                                    </tr>
                                    <tr>
                                        <td><strong>List of booked rooms(numbers):</strong></td>
                                    </tr>
                                    <c:forEach items="${placement.option}" var="apartment">
                                        <tr>
                                            <td>
                                                    ${apartment.key.personNum}-person
                                                    ${apartment.key.category} with ${apartment.key.bedsArrangement}
                                            </td>
                                            <td>x<strong>${fn:length(apartment.value)}</strong></td>
                                            <td>RUB <strong>${apartment.value[0].price}</strong> / night</td>
                                        </tr>
                                    </c:forEach>
                                    <hr>
                                    <tr>
                                        <td>Check-in</td>
                                        <td>${placementInDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${hotel.checkIn}</td>
                                    </tr>
                                    <tr>
                                        <td>Check-out</td>
                                        <td>${placementOutDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${hotel.checkOut}</td>
                                    </tr>
                                    <hr>
                                    <tr>
                                        <td>Total sum</td>
                                        <td>${placementSum}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-body">

                                <hr>
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

