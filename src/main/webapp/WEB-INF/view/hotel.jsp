<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">

</style>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <div class="row">

        <c:if test="${not empty hotel}">
        <div class="col-md-4">
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
                                    <h3> Average <c:out value="${hotel.rating}"/>
                                        <small> /</small>
                                        10
                                    </h3>
                                    <h4> <c:out value="${hotel.votesNum}"/>
                                        <small> votes</small>
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <jsp:include page="fragments/sideSearchFilter.jsp"/>
        </div>
        </c:if>


        <div class="col-md-8">

            <c:if test="${not empty notAvailableApartment}">
                <div class="alert-warning">
                    Unfortunately, <c:out value="${notAvailableApartment.type.personNum}"/>-person
                    <c:out value="${notAvailableApartment.type.category}"/>
                    with <c:out value="${notAvailableApartment.type.bedsArrangement}"/> is not available for requested dates.

                    ---------------- Link to search apartments of similar type in other hotels in this region --------------
                </div>
            </c:if>
            <c:if test="${not empty notAvailablePlacement}">
                <div class="alert-warning">
                    <c:out value="${notAvailablePlacement}"/>
                    <br>
                    ---------------- Link to search apartments of similar type in other hotels in this region --------------
                </div>
            </c:if>
            <c:if test="${not empty placement}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="alert alert-success">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <h4>
                                Nice catch! <strong><c:out value="${placementPersonNum}"/></strong>-person placement solution in
                                <strong><c:out value="${hotel.name}"/></strong> is available for the period between
                                <strong><c:out value="${placementInDate}"/></strong> and <strong><c:out value="${placementOutDate}"/></strong>.
                                Feel free to check it below and book it immediately.
                            </h4>
                            <c:if test="${availableAptNum > 1}">
                                <h5>
                                    By the way, <c:out value="${hotel.name}"/> has
                                    <strong><c:out value="${availableAptNum - 1}"/></strong> more
                                    similar apartments available for the requested period.
                                </h5>
                            </c:if>
                        </div>
                        <hr>
                        <%--<h3 class="text-center">Optimal <strong><c:out value="${placementPersonNum}"/></strong>-person placement solution--%>
                            <%--in <strong><c:out value="${hotel.name}"/></strong>--%>
                            <%--<br>--%>
                             <%--between <strong><c:out value="${placementInDate}"/></strong>--%>
                            <%--and <strong><c:out value="${placementOutDate}"/></strong>:--%>
                        <%--</h3>--%>
                    </div>
                </div>

            <div class="well">
                <c:if test="${not empty options}">
                    <c:forEach items="${options}" var="optionList">
                        <c:forEach items="${optionList}" var="option">
                            <div class="row>">
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
                <hr>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-group">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="col-md-8">
                                            <table class="table-responsive">
                                                <tr>
                                                    <td><h4>Total Price:</h4></td>
                                                    <td><h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        RUB <strong><c:out value="${placementSum}"/>0</strong></h4></td>
                                                </tr>
                                                <tr>
                                                    <td><h4>Total No. of Rooms:</h4></td>
                                                    <td><h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <strong><c:out value="${placementApartmentNum}"/></strong></h4></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="col-md-4" style="padding-top: 15px;">
                                            <form class="form-horizontal" method="post" action="check_booking">
                                                <fieldset>
                                                    <input type="hidden" name="bookingHotelId" value="${hotel.id}">
                                                    <input type="hidden" name="bookingPlacementId"
                                                           value="${placement.id}">
                                                    <input type="hidden" name="bookingSum" value="${placementSum}">
                                                    <input type="hidden" name="bookingPersonNum"
                                                           value="${placementPersonNum}">
                                                    <input type="hidden" name="bookingApartmentNum"
                                                           value="${placementApartmentNum}">
                                                    <input type="hidden" name="bookingInDate"
                                                           value="${placementInDate}">
                                                    <input type="hidden" name="bookingOutDate"
                                                           value="${placementOutDate}">
                                                    <button type="submit" style="padding-left: 40px; padding-right: 40px;"
                                                            class="btn btn-default btn-lg btn-success">
                                                        Book Now
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
            </c:if>

            <c:if test="${not empty hotel && not empty apartments}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <a data-toggle="collapse" href="#collapse1"><h4 class="text-center">
                                            Check availability of this object's apartments</h4></a>
                                    </h3>
                                </div>
                                <div id="collapse1" class="panel-collapse collapse">
                                    <div class="panel-body" style="padding: 5px">
                                        <form class="form-horizontal" method="get" action="check_hotel_overall">
                                            <h5 class="text-center">Note that system doesn't provide single bookings with duration of
                                                more than 30 nights in a row</h5>
                                            <fieldset>
                                                <input type="hidden" name="hotelId" value="${hotel.id}">
                                                <div class="col-md-12">
                                                    <div class="col-md-6">
                                                        <div class="col-md-3">
                                                            <div class="form-group pull-left">
                                                                <label for="person_num"
                                                                       class="control-label">Persons</label>
                                                                <br>
                                                                <select class="input-sm" name="personNum"
                                                                        id="person_num">
                                                                    <c:forEach items="${uniquePersonNums}" var="personNum">
                                                                        <option value="${personNum}"><c:out value="${personNum}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group pull-left">
                                                                <label for="apartment_num"
                                                                       class="control-label">Rooms</label>
                                                                <br>
                                                                <select class="input-sm" name="apartmentNum"
                                                                        id="apartment_num">
                                                                    <c:forEach items="${uniqueAptNums}" var="apartmentNum">
                                                                        <option value="${apartmentNum}"><c:out value="${apartmentNum}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-group pull-left">
                                                                <label for="category"
                                                                       class="control-label">Category</label>
                                                                <select class="input-sm" name="category" id="category">
                                                                    <c:forEach items="${uniqueCategories}" var="category">
                                                                        <option value="${category}"><c:out value="${category}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="col-md-5">
                                                            <div class="form-group">
                                                                <label for="hotel_in_date" class="control-label text-center">From
                                                                    Date</label>
                                                                <div class="input-group">
                                                                    <input class="form-control in_date" id="hotel_in_date"
                                                                           name="inDate">
                                                                    <span class="input-group-addon">
                                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-1"></div>
                                                        <div class="col-md-5">
                                                            <div class="form-group">
                                                                <label for="hotel_out_date" class="control-label text-center">To
                                                                    Date</label>
                                                                <div class="input-group">
                                                                    <input class="form-control out_date" id="hotel_out_date"
                                                                           name="outDate">
                                                                    <span class="input-group-addon">
                                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-1"></div>
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
            </c:if>
            <hr>
            <div class="well">
                <c:if test="${not empty hotel && empty apartments}">
                    <h4 class="text-center">Unfortunately, this object does'nt have any registered apartments yet</h4>
                </c:if>
                <c:if test="${not empty hotel && not empty apartments}">
                    <h3 class="text-center">Inspect all apartments of this object:</h3>
                    <br>

                        <c:forEach items="${apartments}" var="apartment" varStatus="vs">
                            <div class="list-group">
                            <a class="list-group-item apartmentListItem">
                                <div class="row" style="padding-bottom: 10px;">
                                    <div class="col-md-7">
                                        <h4 class="list-group-item-text">
                                             <c:out value="${apartment.type.personNum}"/>-person
                                             <c:out value="${apartment.type.category}"/> with <c:out value="${apartment.type.bedsArrangement}"/>
                                        </h4>
                                        <br>
                                        <br>
                                        <br>
                                        <br>
                                        <button class="btn btn-info btn-lg" type="button" data-toggle="collapse"
                                                data-target="#checkAvCollapseId${vs.index}"
                                                aria-expanded="false" style="padding-left: 40px; padding-right: 40px;
                                                 margin-bottom: 0; margin-left: 5px;"> Check Availability </button>
                                    </div>
                                    <div class="media col-md-5">
                                        <figure class="pull-left">
                                            <img class="media-object img-rounded img-responsive"
                                                 src="http://placehold.it/350x250" alt="placehold.it/350x250">
                                        </figure>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="collapse" id="checkAvCollapseId${vs.index}" style="margin-top: 10px; margin-left: 5px; margin-right: 10px">
                                            <div class="well">
                                                <form class="form-horizontal" method="get" action="check_hotel_apt">
                                                    <input type="hidden" name="apartmentId" value="${apartment.id}">
                                                    <input type="hidden" name="hotelId" value="${hotel.id}">
                                                    <div class="col-md-12">
                                                        <div class="col-md-5">
                                                            <div class="form-group">
                                                                <label for="apt_in_date${vs.index}" class="control-label">From
                                                                    Date</label>
                                                                <div class="input-group">
                                                                    <input class="form-control in_date"
                                                                           id="apt_in_date${vs.index}"
                                                                           name="aptInDate">
                                                                    <span class="input-group-addon">
                                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-5">
                                                            <div class="form-group">
                                                                <label for="apt_out_date${vs.index}" class="control-label">To
                                                                    Date</label>
                                                                <div class="input-group">
                                                                    <input class="form-control out_date"
                                                                           id="apt_out_date${vs.index}"
                                                                           name="aptOutDate">
                                                                    <span class="input-group-addon">
                                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <button type="submit" class="btn btn-default btn-lg btn-block">
                                                        Check
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                            </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>

</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>

<jsp:include page="fragments/footer.jsp"/>
