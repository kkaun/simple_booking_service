<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://kirak.com.functions" prefix="f" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>


<div class="container">
    <div class="row" style="margin-bottom: 10px">

        <div class="col-md-6">
        <h3>Book/Manage fake apartments around the world!</h3>
        </div>

        <div class="col-md-6">
            <div id="custom-search-input">
                <div class="input-group col-md-12">
                    <form method = "get" id="search_form" action="search">
                        <input type="text" class="search-query form-control"
                               name="region" placeholder="Search hotels by Country, City or Region" />
                    </form>
                    <span class="input-group-btn">
                        <button class="btn btn-danger" form="search_form" type="submit">
                            <span class="glyphicon glyphicon-search"></span>
                        </button>
                    </span>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#collapse1">Collapsible panel</a>
                        </h4>
                    </div>
                    <div id="collapse1" class="panel-collapse collapse">
                        <div class="panel-body">
                            <jsp:include page="fragments/searchfilter.jsp"/>
                        </div>
                        <div class="panel-footer"></div>
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

                <div class="carousel-inner">
                <c:forEach items="${citiesFive}" var="city">
                    <jsp:useBean id="city" scope="page" type="com.kirak.model.City"/>
                    <div class="item">
                        <a href="get_by_city?city=${city.id}"><img src="http://placehold.it/1200x400"/></a>
                        <div class="carousel-caption">
                            <h1 class="carousel-caption-header">${city.name}</h1>
                            <p class="carousel-caption-text hidden-sm hidden-xs">
                                    ${city.country.name}
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

    <div class="row">
        <div class="well">
            <h1 class="text-center">Inspect the most rated objects:</h1>
            <hr>
            <div class="list-group">
                <c:forEach items="${hotelsFive}" var="hotel">
                <jsp:useBean id="hotel" scope="page" type="com.kirak.to.HotelTo"/>
                    <a href="inspect_hotel?${hotel.id}" class="list-group-item">
                        <div class="media col-md-3">
                            <figure class="pull-left">
                                <img class="media-object img-rounded img-responsive" src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                            </figure>
                        </div>
                        <div class="col-md-6">
                            <h4 class="list-group-item-heading"> ${hotel.name} </h4>
                            <p class="list-group-item-text"> ${hotel.description}
                            </p>
                        </div>
                        <div class="col-md-3 text-center">
                            <div class="stars">
                                <c:if test="${hotel.stars > 0}">
                                    <c:forEach begin="0" end="${hotel.stars - 1}" varStatus="loop">
                                        <span class="glyphicon glyphicon-star"></span>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty hotel.stars || hotel.stars == 0}">
                                    <p> No stars yet </p>
                                </c:if>
                            </div>
                            <c:if test="${hotel.votesNum > 0}">
                                <h3> Average ${hotel.rating} <small> / </small> 10 </h3>
                                <h4> ${hotel.votesNum} <small> votes </small></h4>
                            </c:if>
                            <c:if test="${empty hotel.votesNum || hotel.votesNum == 0}">
                                <h4> No votes yet </h4>
                            </c:if>
                            <button type="button" href="inspect_hotel?${hotel.id}" class="btn btn-default btn-lg btn-block"> Book Now </button>
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