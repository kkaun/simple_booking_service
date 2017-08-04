<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

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
            <jsp:include page="fragments/searchfilter.jsp"/>
        </div>

        <div class="col-md-8">
            <div class="well">
                <div class="list-group">
                    <c:if test="${not empty badRegion && empty hotels}">
                        <h3>
                            Unfortunately, searching by request "${badRegion}" brought no results.
                            <br>
                            Maybe there is a shortage of hotels in this region
                            or we haven't include it yet.
                            <br>
                            You might try to specify your request in Search Filter.
                        </h3>
                    </c:if>
                    <c:if test="${not empty hotels}">
                        <c:forEach items="${hotels}" var="hotel">
                            <jsp:useBean id="hotel" scope="page" type="com.kirak.to.HotelTo"/>
                            <a href="inspect_hotel?${hotel.id}" class="list-group-item">
                                <div class="media col-md-3">
                                    <figure class="pull-left">
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
                                    <button type="button" href="inspect_hotel?${hotel.id}" class="btn btn-default btn-lg btn-block"> Book Now </button>
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