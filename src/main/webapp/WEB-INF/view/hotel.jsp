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
    <jsp:useBean id="hotel" scope="page" type="com.kirak.to.HotelTo"/>

    <div class="row">
        <c:if test="${not empty availableApartment}">
            <div class="col-md-12">
                <div class="alert alert-success">
                    <h4>
                        Nice catch! ${availableApartment.type.personNum}-person ${availableApartment.type.category} with
                        ${availableApartment.type.bedsArrangement} is available for ${inDate} - ${outDate}.
                        <br>
                        <br>
                        <c:if test="${availableApartmentCount > 1}">
                            By the way, ${hotel.name} has ${availableApartmentCount} similar apartments available for requested period.
                            Choosing "Book Now", you can book even more numbers like this at a time.
                        </c:if>
                        <br>
                        <br>
                    </h4>

                    <form class="form-horizontal" method="post" action="check_booking">
                        <fieldset>
                            <input type="hidden" name="hotelId" value="${hotel.id}">
                            <input type="hidden" name="bookingAptCategory" value="${availableApartment.id}">
                            <input type="hidden" name="bookingPersonNum" value="${personNum}">
                            <input type="hidden" name="hotelId" value="${inDate}">
                            <input type="hidden" name="hotelId" value="${outDate}">
                            <button type="submit" class="btn btn-default btn-lg btn-primary"> Book Now </button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </c:if>
    </div>

    <div class="row">
        <div class="col-md-4">
            <div class="well">

                <div class="list-group">
                    <div class="media col-md-3">
                        <figure class="pull-left">
                            <img class="media-object img-rounded img-responsive"  src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                            <img class="media-object img-rounded img-responsive"  src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                            <img class="media-object img-rounded img-responsive"  src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                        </figure>
                    </div>
                    <div class="col-md-4">
                        <h4 class="list-group-item-heading"> ${hotel.name} </h4>
                        <p class="list-group-item-text"> ${hotel.description}
                        </p>
                    </div>
                    <div class="col-md-5 text-center">
                        <div class="stars">
                            <c:if test="${not empty hotel.stars}">
                                <c:forEach begin="0" end="${hotel.stars - 1}" varStatus="loop">
                                    <span class="glyphicon glyphicon-star"></span>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty hotel.stars}">
                                <p> No stars yet </p>
                            </c:if>
                        </div>
                        <h3> Average ${hotel.rating} <small> / </small> 10 </h3>
                        <h4> ${hotel.votesNum} <small> votes </small></h4>
                    </div>
                </div>
            </div>

            <hr>
            <jsp:include page="fragments/searchfilter.jsp"/>
        </div>


        <div class="col-md-8">
            <div class="well">
                <form class="form-horizontal" method="get" action="check_hotel_overall">
                    <fieldset>
                        <legend>Check availability of this object's apartments</legend>
                        <h5>Note that we are not supporting single bookings with duration of more than 30 nights</h5>
                        <input type="hidden" name="hotelId" value="${hotel.id}">
                        <div class="form-group">
                            <label for="person_num" class="control-label">No. of Persons</label>
                            <select class="form-control" name="personNum" id="person_num">
                                <c:forEach items="${uniquePersonNums}" var="personNum">
                                    <option value="${param.personNum}">${personNum}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="category" class="control-label">Category</label>
                            <select class="form-control" name="category" id="category">
                                <c:forEach items="${uniqueCategories}" var="category">
                                    <option value="${param.category}">${category}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="in_date" class="control-label">From Date</label>
                            <div class="input-group">
                                <input type="date" class="form-control" id="in_date" name="inDate" value="${param.inDate}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="out_date" class="control-label">To Date</label>
                            <div class="input-group">
                                <input type="date" class="form-control" id="out_date" name="outDate" value="${param.outDate}">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-default btn-lg btn-block"> Check </button>
                    </fieldset>
                </form>
            </div>

            <hr>
            <c:forEach items="${apartments}" var="apartment" varStatus="vs">
                <div class="well">
                    <div class="list-group">
                        <div class="col-md-6">
                            <h4 class="list-group-item-heading"> ${apartment.type.category} </h4>
                            <p class="list-group-item-text"> ${apartment.type.personNum}
                            </p>
                            <p class="list-group-item-text"> ${apartment.type.bedsArrangement}
                            </p>
                            <br>

                            <button type="button" class="btn btn-info btn-lg" data-toggle="modal"
                                    data-target="#myModal${vs.index}" id="viewDetailButton${vs.index}"> Check Availability </button>

                            <!-- Modal -->
                            <div class="modal fade" id="myModal${vs.index}" role="dialog">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title"> Please, specify enlisted parameters </h4>
                                            <h6>Note that we are not supporting single bookings with duration of more than 30 nights</h6>
                                        </div>

                                        <div class="modal-body">
                                            <form class="form-horizontal" method="get" action="check_hotel_apt">
                                                <input type="hidden" name="apartmentId" value="${apartment.id}">
                                                <input type="hidden" name="hotelId" value="${hotel.id}">
                                                <div class="form-group">
                                                    <label for="apt_in_date" class="control-label">From Date</label>
                                                    <div class="input-group">
                                                        <input type="date" class="form-control" id="apt_in_date" name="aptInDate" value="${param.aptInDate}">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="apt_out_date" class="control-label">To Date</label>
                                                    <div class="input-group">
                                                        <input type="date" class="form-control" id="apt_out_date" name="aptOutDate" value="${param.aptOutDate}">
                                                    </div>
                                                </div>
                                                <button type="submit" class="btn btn-default btn-lg btn-block"> Check </button>
                                            </form>
                                        </div>

                                        <div class="modal-footer">
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>
                    <div class="media col-md-6">
                        <figure class="pull-left">
                            <img class="media-object img-rounded img-responsive"  src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                            <img class="media-object img-rounded img-responsive"  src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                            <img class="media-object img-rounded img-responsive"  src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                            <img class="media-object img-rounded img-responsive"  src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                        </figure>
                    </div>
                </div>

            </c:forEach>
        </div>
    </div>

</div>

</c:if>

</div>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>
