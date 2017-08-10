<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container">

    <c:if test="${not empty hotel}">
    <div class="row">

        <div class="col-md-4">
            <div class="well">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="list-group">
                                        <div class="col-md-7">
                                            <h4 class="list-group-item-heading"> ${hotel.name} </h4>
                                        </div>
                                        <div class="col-md-5">
                                            <c:if test="${not empty hotel.stars}">
                                                <c:forEach begin="0" end="${hotel.stars - 1}" varStatus="loop">
                                                    <span class="glyphicon glyphicon-star"></span>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty hotel.stars}">
                                                <p> No stars yet </p>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="media col-md-6">
                                        <figure class="pull-left">
                                            <img class="media-object img-rounded img-responsive"
                                                 src="http://placehold.it/350x250" alt="placehold.it/350x250">
                                        </figure>
                                    </div>
                                    <div class="col-md-6 text-center">
                                        <h3> Average ${hotel.rating}
                                            <small> /</small>
                                            10
                                        </h3>
                                        <h4> ${hotel.votesNum}
                                            <small> votes</small>
                                        </h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <jsp:include page="fragments/sidesearchfilter.jsp"/>
        </div>


        <div class="col-md-8">
            <c:if test="${not empty notAvailableApartment}">
                <div class="alert-warning">
                    Unfortunately, ${notAvailableApartment.type.personNum}-person ${notAvailableApartment.type.category}
                    with ${notAvailableApartment.type.bedsArrangement} is not available for ${inDate} - ${outDate}.

                    ---------------- Link to search apartments of similar type in other hotels in this region --------------
                </div>
            </c:if>
            <c:if test="${not empty notAvailablePlacement}">
                <div class="alert-warning">
                    ${notAvailablePlacement}
                    <br>
                    ---------------- Link to search apartments of similar type in other hotels in this region --------------
                </div>
            </c:if>
            <c:if test="${not empty placement}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="well">
                            <div class="alert alert-success">
                                <h4>
                                    Nice catch! <strong>${placementPersonNum}</strong>-person placement solution in
                                    <strong>${hotel.name}</strong> is available for the period between
                                    <strong>${placementInDate}</strong> - <strong>${placementOutDate}</strong>.
                                    Feel free to check it below and book it immediately.
                                </h4>
                                <br>
                                <c:if test="${availableAptNum > 1}">
                                    <h5>
                                        By the way, ${hotel.name} has <strong>${availableAptNum - 1}</strong> more
                                        similar apartments available for the requested period.
                                    </h5>
                                </c:if>
                                <br>
                            </div>
                        </div>
                        <br>
                        <hr>
                        <h3>Check optimal <strong>${placementPersonNum}</strong>-person placement in
                            <strong>${hotel.name}</strong>
                            between <strong>${placementInDate}</strong> and <strong>${placementOutDate}</strong></h3>
                        <br>
                    </div>
                </div>
                <div class="row>">
                    <c:forEach items="${placement.option}" var="apartment">
                        <div class="col-md-10">
                            <div class="well">
                                <div class="row">
                                    <div class="media col-md-5">
                                        <figure class="pull-left">
                                            <img class="media-object img-rounded img-responsive"
                                                 src="http://placehold.it/350x250" alt="placehold.it/350x250">
                                        </figure>
                                    </div>
                                    <div class="col-md-7">
                                        <h4>
                                                ${apartment.key.personNum}-person
                                                ${apartment.key.category} with ${apartment.key.bedsArrangement}
                                        </h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="well">
                                <table class="table-responsive">
                                    <tr>
                                        <td>x<strong>${fn:length(apartment.value.size)}</strong></td>
                                    </tr>
                                    <tr>
                                        <td>RUB <strong>${apartment.value[0].price}</strong> / night</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <hr>
                <div class="row">
                    <div class="well">
                        <div class="col-md-6">
                            <table class="table-responsive">
                                <tr>
                                    <td>Total Price:</td>
                                    <td>RUB <strong>${placementSum}</strong></td>
                                </tr>
                                <tr>
                                    <td>Total No. of Rooms:</td>
                                    <td><strong>${placementApartmentNum}</strong></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <form class="form-horizontal" method="post" action="check_booking">
                                <fieldset>
                                    <input type="hidden" name="bookingHotelId" value="${hotel.id}">
                                    <input type="hidden" name="bookingPlacementId" value="${placement.id}">
                                    <input type="hidden" name="bookingSum" value="${placementSum}">
                                    <input type="hidden" name="bookingPersonNum" value="${placementPersonNum}">
                                    <input type="hidden" name="bookingApartmentNum" value="${placementApartmentNum}">
                                    <input type="hidden" name="bookingInDate" value="${placementInDate}">
                                    <input type="hidden" name="bookingOutDate" value="${placementOutDate}">
                                    <button type="submit" class="btn btn-default btn-lg btn-primary"> Book Now</button>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
                <br>
                <hr>
                <hr>
                <br>
            </c:if>

            <div class="well">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" href="#collapse1"><h4 class="text-center">
                                            Check availability of this object's apartments</h4></a>
                                    </h4>
                                </div>
                                <div id="collapse1" class="panel-collapse collapse">
                                    <div class="panel-body" style="padding: 5px">
                                        <form class="form-horizontal" method="get" action="check_hotel_overall">
                                            <fieldset>
                                                <h5>Note that system doesn't provide single bookings with duration of
                                                    more than 30 nights in a row</h5>
                                                <input type="hidden" name="hotelId" value="${hotel.id}">
                                                <div class="col-md-12">
                                                    <div class="col-md-1">
                                                        <div class="form-group pull-left">
                                                            <label for="person_num"
                                                                   class="control-label">Persons</label>
                                                            <br>
                                                            <select class="input-sm" name="personNum" id="person_num">
                                                                <c:forEach items="${uniquePersonNums}" var="personNum">
                                                                    <option value="${param.personNum}">${personNum}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-1">
                                                        <div class="form-group pull-left">
                                                            <label for="apartment_num"
                                                                   class="control-label">Rooms</label>
                                                            <br>
                                                            <select class="input-sm" name="apartmentNum" id="apartment_num">
                                                                <c:forEach items="${apartmentNums}" var="apartmentNum">
                                                                    <option value="${param.apartmentNum}">${apartmentNum}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2 text-center">
                                                        <div class="form-group pull-right">
                                                            <label for="category" class="control-label">Category</label>
                                                            <select class="input-sm" name="category" id="category">
                                                                <c:forEach items="${uniqueCategories}" var="category">
                                                                    <option value="${param.category}">${category}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group pull-right">
                                                            <label for="in_date" class="control-label text-center">From
                                                                Date</label>
                                                            <div class="input-group">
                                                                <input type="date" class="input-sm" id="in_date"
                                                                       name="inDate" value="${param.inDate}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group pull-right">
                                                            <label for="out_date" class="control-label text-center">To
                                                                Date</label>
                                                            <div class="input-group">
                                                                <input type="date" class="input-sm" id="out_date"
                                                                       name="outDate" value="${param.outDate}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <button type="submit" class="btn btn-default btn-lg btn-block"> Check
                                                </button>
                                            </fieldset>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <hr>
            <div class="well">
                <div class="list-group">
                    <c:forEach items="${apartments}" var="apartment" varStatus="vs">
                        <a class="list-group-item">
                            <div class="col-md-7">
                                <h4 class="list-group-item-text">
                                        ${apartment.type.personNum}-person
                                        ${apartment.type.category} with ${apartment.type.bedsArrangement}
                                </h4>
                                <br>

                                <button type="button" class="btn btn-info btn-lg" data-toggle="modal"
                                        data-target="#myModal${vs.index}" id="viewDetailButton${vs.index}"> Check
                                    Availability
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="myModal${vs.index}" role="dialog">
                                    <div class="modal-dialog">
                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;
                                                </button>
                                                <h4 class="modal-title"> Please, specify enlisted parameters </h4>
                                                <h6>Note that we are not supporting single bookings with duration of
                                                    more than 30 nights</h6>
                                            </div>

                                            <div class="modal-body">
                                                <form class="form-horizontal" method="get" action="check_hotel_apt">
                                                    <input type="hidden" name="apartmentId" value="${apartment.id}">
                                                    <input type="hidden" name="hotelId" value="${hotel.id}">
                                                    <div class="col-md-12">
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <label for="apt_in_date" class="control-label">From
                                                                    Date</label>
                                                                <div class="input-group">
                                                                    <input type="date" class="form-control"
                                                                           id="apt_in_date"
                                                                           name="aptInDate" value="${param.aptInDate}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <label for="apt_out_date" class="control-label">To
                                                                    Date</label>
                                                                <div class="input-group">
                                                                    <input type="date" class="form-control"
                                                                           id="apt_out_date"
                                                                           name="aptOutDate"
                                                                           value="${param.aptOutDate}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <button type="submit" class="btn btn-default btn-lg btn-block">
                                                        Check
                                                    </button>
                                                </form>
                                            </div>

                                            <div class="modal-footer">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="media col-md-5">
                                <figure class="pull-left">
                                    <img class="media-object img-rounded img-responsive"
                                         src="http://placehold.it/350x250" alt="placehold.it/350x250">
                                </figure>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
        </c:if>
    </div>
</div>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>
