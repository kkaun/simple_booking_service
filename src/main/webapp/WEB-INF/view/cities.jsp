<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>


<div class="row">
    <div class="well">
        <h1 class="text-center">Create and edit cities here:</h1>
        <hr>
        <div class="list-group">
            <c:forEach items="${cities}" var="hotel">
                <jsp:useBean id="hotel" scope="page" type="com.kirak.to.HotelTo"/>
                <a href="#" class="list-group-item">
                    <div class="media col-md-3">
                        <figure class="pull-left">
                            <img class="media-object img-rounded img-responsive"  src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                        </figure>
                    </div>
                    <div class="col-md-6">
                        <h4 class="list-group-item-heading"> ${hotel.name} </h4>
                        <p class="list-group-item-text"> ${hotel.description}
                        </p>
                    </div>
                    <div class="col-md-3 text-center">
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
                        <button type="button" class="btn btn-default btn-lg btn-block"> Book Now </button>
                    </div>
                </a>
            </c:forEach>

        </div>
    </div>
</div>


<jsp:include page="fragments/footer.jsp"/>