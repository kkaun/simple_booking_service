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
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active">
                    <img src="http://placehold.it/1200x400" />
                    <div class="carousel-caption">
                        <h1 class="carousel-caption-header">Slide 1</h1>
                        <p class="carousel-caption-text hidden-sm hidden-xs">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse dignissim aliquet rutrum. Praesent vitae ante in nisi condimentum egestas. Aliquam.
                        </p>
                    </div>
                </div>

                <div class="item">
                    <img src="http://placehold.it/1200x400/AAAAAA/888888" />
                    <div class="carousel-caption">
                        <h1 class="carousel-caption-header">Slide 2</h1>
                        <p class="carousel-caption-text hidden-sm hidden-xs">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse dignissim aliquet rutrum. Praesent vitae ante in nisi condimentum egestas. Aliquam.
                        </p>
                    </div>
                </div>

                <div class="item">
                    <img src="http://placehold.it/1200x400/888888/555555" />
                    <div class="carousel-caption">
                        <h1 class="carousel-caption-header">Slide 3</h1>
                        <p class="carousel-caption-text hidden-sm hidden-xs">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse dignissim aliquet rutrum. Praesent vitae ante in nisi condimentum egestas. Aliquam.
                        </p>
                    </div>
                </div>
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
                <a href="#" class="list-group-item active">
                    <div class="media col-md-3">
                        <figure class="pull-left">
                            <img class="media-object img-rounded img-responsive"  src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                        </figure>
                    </div>
                    <div class="col-md-6">
                        <h4 class="list-group-item-heading"> List group heading </h4>
                        <p class="list-group-item-text"> Qui diam libris ei, vidisse incorrupte at mel. His euismod salutandi dissentiunt eu. Habeo offendit ea mea. Nostro blandit sea ea, viris timeam molestiae an has. At nisl platonem eum.
                            Vel et nonumy gubergren, ad has tota facilis probatus. Ea legere legimus tibique cum, sale tantas vim ea, eu vivendo expetendis vim. Voluptua vituperatoribus et mel, ius no elitr deserunt mediocrem. Mea facilisi torquatos ad.
                        </p>
                    </div>
                    <div class="col-md-3 text-center">
                        <h2> 14240 <small> votes </small></h2>
                        <button type="button" class="btn btn-default btn-lg btn-block"> Vote Now! </button>
                        <div class="stars">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                        </div>
                        <p> Average 4.5 <small> / </small> 5 </p>
                    </div>
                </a>
                <a href="#" class="list-group-item">
                    <div class="media col-md-3">
                        <figure class="pull-left">
                            <img class="media-object img-rounded img-responsive" src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                        </figure>
                    </div>
                    <div class="col-md-6">
                        <h4 class="list-group-item-heading"> List group heading </h4>
                        <p class="list-group-item-text"> Eu eum corpora torquatos, ne postea constituto mea, quo tale lorem facer no. Ut sed odio appetere partiendo, quo meliore salutandi ex. Vix an sanctus vivendo, sed vocibus accumsan petentium ea.
                            Sed integre saperet at, no nec debet erant, quo dico incorrupte comprehensam ut. Et minimum consulatu ius, an dolores iracundia est, oportere vituperata interpretaris sea an. Sed id error quando indoctum, mel suas saperet at.
                        </p>
                    </div>
                    <div class="col-md-3 text-center">
                        <h2> 12424 <small> votes </small></h2>
                        <button type="button" class="btn btn-primary btn-lg btn-block">Vote Now!</button>
                        <div class="stars">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                        </div>
                        <p> Average 3.9 <small> / </small> 5 </p>
                    </div>
                </a>
                <a href="#" class="list-group-item">
                    <div class="media col-md-3">
                        <figure class="pull-left">
                            <img class="media-object img-rounded img-responsive" src="http://placehold.it/350x250" alt="placehold.it/350x250">
                        </figure>
                    </div>
                    <div class="col-md-6">
                        <h4 class="list-group-item-heading"> List group heading </h4>
                        <p class="list-group-item-text"> Ut mea viris eripuit theophrastus, cu ponderum accusata consequuntur cum. Suas quaestio cotidieque pro ea. Nam nihil persecuti philosophia id, nam quot populo ea.
                            Falli urbanitas ei pri, eu est enim volumus, mei no volutpat periculis. Est errem iudicabit cu. At usu vocibus officiis, ad ius eros tibique appellantur.
                        </p>
                    </div>
                    <div class="col-md-3 text-center">
                        <h2> 13540 <small> votes </small></h2>
                        <button type="button" class="btn btn-primary btn-lg btn-block">Vote Now!</button>
                        <div class="stars">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                        </div>
                        <p> Average 4.1 <small> / </small> 5 </p>
                    </div>
                </a>
            </div>
        </div>
    </div>
    <div class="container text-center">
        <hr>
        <div class="row">
            <div class="col-lg-12">
                <ul class="nav nav-pills nav-justified">
                    <li><a href="/">© 2013 Company Name.</a></li>
                    <li><a href="#">Terms of Service</a></li>
                    <li><a href="#">Privacy</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>


</body>
</html>