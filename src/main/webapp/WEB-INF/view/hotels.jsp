<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/jspHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container">

    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-8">
            <c:if test="${not empty region}">
                <h2>We found some objects in ${region}:</h2>
            </c:if>
            <c:if test="${not empty city}">
                <h3>${city.name}.
                    <br>${city.description}</h3>
            </c:if>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <jsp:include page="fragments/filters/sideSearchFilter.jsp"/>
        </div>

        <div class="col-md-8">
            <div class="well">
                <a class="list-group">
                    <c:if test="${not empty badRegion && empty hotels}">
                        <div class="list-group-item">
                            <h3>
                                Unfortunately, searching by request "${badRegion}" brought no results.
                                <br>
                                Maybe there is a shortage of hotels in this region
                                or they haven't been included yet. You might try to make more specific request in Search Filter.
                                <br>
                                Also you can check list of cities or create your own in Admin mode.
                            </h3>
                        </div>
                    </c:if>
                    <c:if test="${not empty notAvailablePlacementList && empty hotels}">
                        <h3><c:out value="${notAvailablePlacementList}"/></h3>
                        <br>
                        <h5>Maybe there is a shortage of available placement options/hotels in this region right now
                            or they haven't been included yet. You might try to make more specific request in Search Filter.
                            <br>
                            Also you can check list of cities or create your own in Admin mode.
                            ------------------------------- Link to Cities ---------------------------
                        </h5>
                    </c:if>
                    <c:if test="${not empty hotels}">
                        <c:forEach items="${hotels}" var="hotel">
                            <jsp:useBean id="hotel" scope="page" type="com.kirak.to.HotelTo"/>
                            <a class="list-group-item" href="inspect_hotel?id=${hotel.id}" style="padding-top: 20px;">
                                <div class="media col-md-3">
                                    <figure class="pull-left">
                                        <img class="media-object img-rounded img-responsive"
                                             src="${hotel.imgPath}" alt="Sorry, no hotel image is available at the time">
                                    </figure>
                                </div>
                                <div class="col-md-4">
                                    <h4 class="list-group-item-heading"> ${hotel.name} </h4>
                                    <p class="list-group-item-text"> ${hotel.description}
                                    </p>
                                </div>
                                <div class="col-md-5 text-center">
                                    <div class="stars">
                                        <c:if test="${not empty hotel.stars && hotel.stars > 0}">
                                            <c:forEach begin="0" end="${hotel.stars - 1}" varStatus="loop">
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${empty hotel.stars || hotel.stars == 0}">
                                            <p> No stars yet </p>
                                        </c:if>
                                    </div>
                                    <c:if test="${hotel.votesNum > 0}">
                                        <h3> Average ${hotel.rating}
                                            <small> /</small>
                                            10
                                        </h3>
                                        <h4> ${hotel.votesNum}
                                            <small> votes</small>
                                        </h4>
                                    </c:if>
                                    <c:if test="${empty hotel.votesNum || hotel.votesNum == 0}">
                                        <h4> No votes yet </h4>
                                    </c:if>
                                    <button type="button" href="inspect_hotel?id=${hotel.id}"
                                            class="btn btn-default btn-lg btn-block"> View & Book
                                    </button>
                                </div>
                            </a>
                        </c:forEach>
                    </c:if>

                    <c:if test="${not empty placements && empty hotels}">
                        <c:forEach items="${placements}" var="placement">
                            <jsp:useBean id="placement" scope="page" type="com.kirak.to.Placement"/>
                            <a class="list-group-item" style="padding-top: 20px; padding-bottom: 10px;"
                               href="inspect_placement?id=${placement.id}&personNum=${placementPersonNum}&apartmentNum=${placementApartmentNum}&inDate=${placementInDate}&outDate=${placementOutDate}">
                                <div class="media col-md-3">
                                    <figure class="pull-left">
                                        <img class="media-object img-rounded img-responsive"
                                             src="${placement.hotel.imgPath}" alt="Sorry, no hotel image is available at the time">
                                    </figure>
                                </div>
                                <div class="col-md-6">
                                    <div class="col-md-12">
                                        <h4 class="list-group-item-heading"> ${placement.hotel.name} </h4>
                                        <hr>
                                    </div>
                                    <div class="col-md-12">
                                        <h5 style="margin-top: 7px;"><strong> Optimal placement solution: </strong></h5>
                                        <div class="well" style="background-color: lightgreen; padding-top: 5px; padding-bottom: 5px;">
                                            <table class="table-responsive">
                                                <c:forEach items="${placement.option}" var="option">
                                                    <tr>
                                                        <td><h6><strong>${option.key.category} with ${option.key.bedsArrangement}</strong></h6></td>
                                                        <td><h6>&nbsp;&nbsp;&nbsp;x<strong>${fn:length(option.value)}</strong></h6></td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3 text-center">
                                    <div class="stars">
                                        <c:if test="${placement.hotel.stars > 0}">
                                            <c:forEach begin="0" end="${placement.hotel.stars - 1}"
                                                       varStatus="loop">
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${empty placement.hotel.stars || placement.hotel.stars == 0}">
                                            <p> No stars yet </p>
                                        </c:if>
                                    </div>
                                    <c:if test="${placement.hotel.votesNum > 0}">
                                        <h3> Average ${hotel.rating}
                                            <small> /</small>
                                            10
                                        </h3>
                                        <h4> ${placement.hotel.votesNum}
                                            <small> votes</small>
                                        </h4>
                                    </c:if>
                                    <c:if test="${empty placement.hotel.votesNum || placement.hotel.votesNum == 0}">
                                        <h4> No votes yet </h4>
                                    </c:if>
                                    <button type="button"
                                            href="inspect_placement?id=${placement.id}&personNum=${placementPersonNum}&
                                        apartmentNum=${placementApartmentNum}&inDate=${placementInDate}&outDate=${placementOutDate}"
                                            class="btn btn-default btn-lg btn-block"> Book Now
                                    </button>
                                </div>
                            </a>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>