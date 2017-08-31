<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">

    <div class="row">
        <div class="col-md-12">
            <div class="panel-group">
                <div class="panel panel-default text-center">
                    <h2><spring:message code="common.object"/> <strong>#${objectId}</strong> <spring:message code="manager_panel"/> </h2>
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
                            <h3><spring:message code="common.chart"/> </h3>
                        </div>
                    </div>
                </div>
                <a href="hotel_manager/object/show_chart">
                    <div class="panel-footer">
                        <span class="pull-left"><spring:message code="common.view"/> </span>
                        <span class="pull-right glyphicon glyphicon glyphicon-open"></span>
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
                            <h3><spring:message code="common.subBookings"/></h3>
                        </div>
                    </div>
                </div>
                <a href="hotel_manager/object/show_bookings">
                    <div class="panel-footer">
                        <span class="pull-left"><spring:message code="common.view_manage"/> </span>
                        <span class="pull-right glyphicon glyphicon glyphicon-open"></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-lg-6 col-md-6">
            <div class="panel panel-yellow">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3><spring:message code="common.votes"/></h3>
                        </div>
                    </div>
                </div>
                <a href="hotel_manager/object/show_hotel_votes">
                    <div class="panel-footer">
                        <span class="pull-left"><spring:message code="common.view"/></span>
                        <span class="pull-right glyphicon glyphicon glyphicon-open"></span>
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
                            <h3><spring:message code="common.apartments"/></h3>
                        </div>
                    </div>
                </div>
                <a href="hotel_manager/object/show_apartments">
                    <div class="panel-footer">
                        <span class="pull-left"><spring:message code="common.view_manage"/></span>
                        <span class="pull-right glyphicon glyphicon glyphicon-open"></span>
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


