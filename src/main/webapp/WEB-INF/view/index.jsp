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
        <h2>Hotel booking service demo</h2>
    </div>

    <div class="row">
        <!-- The carousel -->
            <div id="transition-timer-carousel" class="carousel slide transition-timer-carousel" data-ride="carousel">

                <!-- Indicators -->
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
                            <a href="hotels/by_city?${city.id}"><img src="http://placehold.it/1200x400"/></a>
                            <div class="carousel-caption">
                                <h1 class="carousel-caption-header">${city.name}</h1>
                                <p class="carousel-caption-text hidden-sm hidden-xs">
                                        ${city.country.name}
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                    </div>


                <!-- Controls -->
                <a class="left carousel-control" href="#transition-timer-carousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#transition-timer-carousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>

                <!-- Timer "progress bar" -->
                <hr class="transition-timer-carousel-progress-bar animate" />
            </div>
    </div>

    <div class="row">
        <div class="well">
            <h1 class="text-center">Vote for your favorite</h1>
            <div class="list-group">

                <c:forEach items="${hotelsFive}" var="hotel">
                <jsp:useBean id="hotel" scope="page" type="com.kirak.model.Hotel"/>
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
                            <h2> ${hotel.votes.size()} <small> votes </small></h2>
                            <p> Average  <small> / </small> 5 </p>
                            <button type="button" class="btn btn-default btn-lg btn-block"> Book Now </button>

                            <c:if test="${not empty hotel.stars}">
                                <c:forEach begin="0" end="${hotel.stars}" varStatus="loop">
                                    <div class="stars">
                                        <span class="glyphicon glyphicon-star"></span>
                                    </div>
                                </c:forEach>
                                <c:forEach begin="0" end="${5 - hotel.stars}" varStatus="loop">
                                    <div class="stars">
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                    </div>
                                </c:forEach>
                            </c:if>
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
            interval: 4000
        });
        $('#cityCarousel').carousel('cycle');
    });
</script>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>