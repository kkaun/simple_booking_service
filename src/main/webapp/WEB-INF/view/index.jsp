<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://kirak.com.functions" prefix="f" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/citiesDataLoad.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <div class="row" style="margin-bottom: 10px">

        <div class="col-md-6">
            <h4 style="margin-top: 8px; font-family: 'Comfortaa', cursive;">
                <strong><spring:message code="app.moto"/></strong></h4>
        </div>

        <div class="col-md-6">
            <div class="row">
                <div id="custom-search-input">
                    <div class="col-md-10">
                        <form:form method="get" role="form" id="search_form" action="search">
                            <input type="text" class="index-cities-input"
                                   name="region" placeholder="<spring:message code="app.search_fast"/>"/>
                        </form:form>
                    </div>
                    <div class="col-md-1">
                        <button class="btn btn-default glyphicon glyphicon-search" form="search_form" type="submit"
                        style="height: 31px"></button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading filterHeading">
                        <div class="panel-title">
                            <a data-toggle="collapse" href="#collapse1">
                                <h4 class="text-center">
                                    <span style="font-family: 'Poiret One', cursive; font-weight: 900">
                                        <spring:message code="app.advanced_search"/>
                                    </span>
                                </h4>
                            </a>
                        </div>
                    </div>
                    <div id="collapse1" class="panel-collapse collapse">
                        <div class="panel-body" style="padding: 5px">
                            <jsp:include page="fragments/filters/primarySearchFilter.jsp"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div id="transition-timer-carousel" class="carousel slide transition-timer-carousel" data-ride="carousel">

            <ol class="carousel-indicators">
                <li data-target="#transition-timer-carousel" data-slide-to="0" class="active"></li>
                <li data-target="#transition-timer-carousel" data-slide-to="1"></li>
                <li data-target="#transition-timer-carousel" data-slide-to="2"></li>
                <li data-target="#transition-timer-carousel" data-slide-to="3"></li>
                <li data-target="#transition-timer-carousel" data-slide-to="4"></li>
            </ol>

                <div class="carousel-inner" style="border-radius: 5px;">
                <c:forEach items="${citiesFive}" var="city">
                    <jsp:useBean id="city" scope="page" type="com.kirak.model.City"/>
                    <div class="item" style="border-radius: 5px;">
                        <a href="get_by_city?city=${city.id}" style="border-radius: 5px;">
                            <div class="citiesCarousel" style="height: 400px; width: 1200px; border-radius: 5px">
                                <c:if test="${not empty city.imgPath && city.imgPath.length() >= 1}">
                                    <img class="media-object img-rounded img-responsive"
                                         src="${city.imgPath}" alt="" style="height: 400px; width: 1200px">
                                </c:if>
                                <c:if test="${empty city.imgPath || city.imgPath.length() < 1}">
                                    <img class="media-object img-rounded img-responsive"
                                         src="http://placehold.it/1200x400">
                                </c:if>
                            </div>
                        </a>
                        <div class="carousel-caption">
                            <h1 class="carousel-caption-header"><c:out value="${city.name}"/></h1>
                            <p class="carousel-caption-text hidden-sm hidden-xs">
                                <c:out value="${city.country.name}"/>
                            </p>
                        </div>
                    </div>
                </c:forEach>
                </div>

            <a class="left carousel-control" href="#transition-timer-carousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#transition-timer-carousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>

            <hr class="transition-timer-carousel-progress-bar animate" />
        </div>
    </div>

    <br>

    <div class="row">
        <div class="well" style="background-color: #dfd7ad">
            <h3 class="text-center" style="font-family: 'Poiret One', cursive; font-weight: 500">
                <strong><spring:message code="app.inspect_top_5"/></strong></h3>
            <hr>
            <div class="list-group" style="background-color: #dfd7ad">
                <c:forEach items="${hotelsFive}" var="hotel">
                <jsp:useBean id="hotel" scope="page" type="com.kirak.to.HotelTo"/>
                    <a href="inspect_hotel?id=${hotel.id}" class="list-group-item listItem">
                        <div class="media col-md-3" style="margin-top: 10px">
                            <figure class="pull-left">
                                <c:if test="${not empty hotel.imgPath && hotel.imgPath.length() >= 1}">
                                    <img class="media-object img-rounded img-responsive"
                                         src="${hotel.imgPath}" alt="">
                                </c:if>
                                <c:if test="${empty hotel.imgPath || hotel.imgPath.length() < 1}">
                                    <img class="media-object img-rounded img-responsive"
                                         src="http://placehold.it/350x250">
                                </c:if>
                            </figure>
                        </div>
                        <div class="col-md-5" style="margin-top: 10px">
                            <h4 class="list-group-item-heading"> <c:out value="${hotel.name}"/> </h4>
                            <p class="list-group-item-text"> <c:out value="${hotel.description}"/>
                            </p>
                        </div>
                        <div class="col-md-4 text-center" style="margin-top: 5px">
                            <div class="stars">
                                <c:if test="${not empty hotel.stars && hotel.stars > 0}">
                                    <c:forEach begin="0" end="${hotel.stars - 1}" varStatus="loop">
                                        <span class="glyphicon glyphicon-star"></span>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty hotel.stars || hotel.stars == 0}">
                                    <p> <spring:message code="common.no_stars"/> </p>
                                </c:if>
                            </div>
                            <c:if test="${hotel.votesNum > 0}">
                                <h3> <spring:message code="common.average"/> <c:out value="${hotel.rating}"/><small> /10</small></h3>
                                <h4><small><spring:message code="common.votes_num"/></small>
                                    <c:out value="${hotel.votesNum}"/></h4>
                            </c:if>
                            <c:if test="${empty hotel.votesNum || hotel.votesNum == 0}">
                                <h4> <spring:message code="common.no_votes"/> </h4>
                            </c:if>
                            <button type="button" href="inspect_hotel?id=${hotel.id}" class="btn btn-info btn-lg btn-block">
                                <spring:message code="app.view_and_book"/></button>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('.carousel-inner .item:first').addClass('active');
        $('#cityCarousel').carousel({
            interval: 2000
        });
        $('#cityCarousel').carousel('cycle');
    });

</script>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>