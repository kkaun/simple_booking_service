<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <c:if test="${not empty placement}">

    <div class="row">
        <div class="col-md-12 text-center">
            <h2>Reservation page</h2>
            <br>
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
                            <div class="col-md-12">
                                <div class="col-md-1"></div>
                                <div class="col-md-10">
                                    <form class="form-horizontal" method="post" action="confirm_anonymous_booking">
                                        <fieldset>
                                            <input type="hidden" name="bookingHotelId" value="${hotel.id}">
                                            <input type="hidden" name="bookingPlacementId" value="${placement.id}">
                                            <input type="hidden" name="bookingSum" value="${placementSum}">
                                            <input type="hidden" name="bookingPersonNum" value="${placementPersonNum}">
                                            <input type="hidden" name="bookingApartmentNum"
                                                   value="${placementApartmentNum}">
                                            <input type="hidden" name="bookingInDate" value="${placementInDate}">
                                            <input type="hidden" name="bookingOutDate" value="${placementOutDate}">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <input id="user_name" type="text" class="form-control"
                                                           name="userName" value=""
                                                           placeholder="Name">
                                                    <span class="input-group-addon"><i
                                                            class="glyphicon glyphicon-user"></i></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <input id="user_phone" type="text" class="form-control"
                                                           name="userPhone"
                                                           value="" placeholder="Phone">
                                                    <span class="input-group-addon"><i
                                                            class="glyphicon glyphicon-earphone"></i></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <input id="user_email" type="email" class="form-control"
                                                           name="userEmail"
                                                           value="" placeholder="Email">
                                                    <span class="input-group-addon"><i
                                                            class="glyphicon glyphicon-envelope"></i></span>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="col-md-3"></div>
                                                <div class="col-md-6">
                                                    <button type="submit" class="btn btn-success btn-lg btn-primary">
                                                        Confirm Booking
                                                    </button>
                                                </div>
                                                <div class="col-md-3"></div>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                                <div class="col-md-1"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3>Summary</h3>
                        </div>
                        <div class="panel-body">
                            <h4>${hotel.name}</h4>
                            <br>
                            <table class="table table-responsive">
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
                    </div>
                </div>

                <div class="row" style="text-align: center">
                    <c:if test="${not empty options}">
                        <c:forEach items="${options}" var="optionList">
                            <c:forEach items="${optionList}" var="option">
                                <div class="row>">
                                    <div class="row">
                                        <div class="col-md-12 text-center">
                                            <h4>Your apartments:</h4>
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
                                                                     src="http://placehold.it/350x250"
                                                                     alt="placehold.it/350x250">
                                                            </figure>
                                                        </div>
                                                        <div class="col-md-7">
                                                            <h5>
                                                                <c:out value="${option.type.personNum}"/>-person
                                                                <c:out value="${option.type.category}"/> with <c:out
                                                                    value="${option.type.bedsArrangement}"/>
                                                            </h5>
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
                                                            <td class="text-center">x<strong><c:out
                                                                    value="${fn:length(optionList)}"/></strong>
                                                                <br>
                                                                <br></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="text-center">RUB <strong><c:out
                                                                    value="${option.price}"/></strong> /
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
        </div>
        </c:if>
    </div>
</div>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>
