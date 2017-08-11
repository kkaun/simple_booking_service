<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container">
    <c:if test="${not empty placement}">

        <div class="row">
            <div class="col-md-12 text-center">
                <h3>Reservation page</h3>
            </div>
        </div>

        <div class="well">
            <div class="row">
                <div class="col-md-6">
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>Fake Booker Info</h4>
                            </div>
                            <div class="panel-body">
                                <h5>Please fill in your own fake information needed for fake booking confirmation
                                    and being informed about any (im)possible changes</h5>
                                <hr>
                                <form class="form-horizontal" method="post" action="confirm_anonymous_booking">
                                    <fieldset>
                                        <input type="hidden" name="bookingHotelId" value="${hotel.id}">
                                        <input type="hidden" name="bookingPlacementId" value="${placement.id}">
                                        <input type="hidden" name="bookingSum" value="${placementSum}">
                                        <input type="hidden" name="bookingPersonNum" value="${placementPersonNum}">
                                        <input type="hidden" name="bookingApartmentNum" value="${placementApartmentNum}">
                                        <input type="hidden" name="bookingInDate" value="${placementInDate}">
                                        <input type="hidden" name="bookingOutDate" value="${placementOutDate}">
                                        <div class="form-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                            <input id="user_name" type="text" class="form-control" name="userName" value="" placeholder="Name">
                                        </div>
                                        <div class="form-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                            <input id="user_phone" type="text" class="form-control" name="userPhone" value="" placeholder="Phone">
                                        </div>
                                        <div class="form-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                            <input id="user_email" type="text" class="form-control" name="userEmail" value="" placeholder="Email">
                                        </div>
                                        <button type="submit" class="btn btn-success btn-lg btn-primary"> Confirm Booking </button>
                                    </fieldset>
                                </form>
                            </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table class="table table-responsive">
                                <caption>Summary</caption>
                                <tr>
                                    <td>${hotel.name}</td>
                                </tr>
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
                                    <td>${placementInDate}</td>
                                </tr>
                                <tr>
                                    <td>Check-out</td>
                                    <td>${placementOutDate}</td>
                                </tr>
                                <tr>
                                    <td>Total sum</td>
                                    <td>${placementSum}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" style="text-align: center">
                <c:if test="${not empty options}">
                    <c:forEach items="${options}" var="optionList">
                        <c:forEach items="${optionList}" var="option">
                            <div class="row>">
                                <div class="row">
                                    <div class="col-md-12 text-center">
                                        <h4>Apartments to book:</h4>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <div class="panel-group">
                                        <div class="panel panel-default">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="media col-md-5">
                                                        <figure class="pull-left">
                                                            <img class="media-object img-rounded img-responsive"
                                                                 src="http://placehold.it/350x250" alt="placehold.it/350x250">
                                                        </figure>
                                                    </div>
                                                    <div class="col-md-7">
                                                        <h4>
                                                            <c:out value="${option.type.personNum}"/>-person
                                                            <c:out value="${option.type.category}"/> with <c:out value="${option.type.bedsArrangement}"/>
                                                        </h4>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="panel-group">
                                        <div class="panel panel-default">
                                            <div class="panel-body">
                                                <table class="table-responsive" style="margin: 0 auto">
                                                    <tr>
                                                        <td class="text-center">x<strong><c:out value="${fn:length(optionList)}"/></strong>
                                                            <br>
                                                            <br></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-center">RUB <strong><c:out value="${option.price}"/></strong> /
                                                            night
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:forEach>
                </c:if>

            </div>
        </div>
    </c:if>
</div>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>
