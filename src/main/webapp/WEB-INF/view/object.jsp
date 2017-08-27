<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">

    <div class="row">
        <div class="col-md-12">
            <div class="panel-group">
                <div class="panel panel-default text-center">
                    <h2>Object <strong>#${objectId}</strong> Manager Panel</h2>
                    <hr>
                </div>
            </div>
        </div>
    </div>

    <!-- /.row -->
    <div class="row">
        <div class="col-lg-6 col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3>Calendar/Chart</h3>
                        </div>
                    </div>
                </div>
                <a href="hotel_manager/object/show_chart">
                    <div class="panel-footer">
                        <span class="pull-left">View Details</span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-lg-6 col-md-6">
            <div class="panel panel-green">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3>Bookings</h3>
                        </div>
                    </div>
                </div>
                <a href="hotel_manager/object/show_super_bookings">
                    <div class="panel-footer">
                        <span class="pull-left">View/Manage</span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-lg-6 col-md-6">
            <div class="panel panel-yellow">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-3">
                            <i class="fa fa-shopping-cart fa-5x"></i>
                        </div>
                        <div class="col-xs-12 text-center">
                            <h3>Votes</h3>
                        </div>
                    </div>
                </div>
                <a href="hotel_manager/object/show_hotel_votes">
                    <div class="panel-footer">
                        <span class="pull-left">View Details</span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-lg-6 col-md-6">
            <div class="panel panel-grey">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3>Apartments</h3>
                        </div>
                    </div>
                </div>
                <a href="hotel_manager/object/show_apartments">
                    <div class="panel-footer">
                        <span class="pull-left">Manage</span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
    </div>

</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>


