<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="row">
            <div class="col-md-12">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <h2><spring:message code="common.welcome_back"/> ${user.name}!</h2>
                        <hr>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-4">
            <div class="panel panel-green">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3><spring:message code="common.my_bookings"/></h3>
                        </div>
                    </div>
                </div>
                <a href="user/show_bookings">
                    <div class="panel-footer">
                        <span class="pull-left"><spring:message code="common.view_manage"/></span>
                        <span class="pull-right glyphicon glyphicon glyphicon-open"></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>

        <div class="col-lg-4 col-md-4">
            <div class="panel panel-lilac">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3><spring:message code="common.visited_hotels"/></h3>
                        </div>
                    </div>
                </div>
                <a href="user/show_hotels">
                    <div class="panel-footer">
                        <span class="pull-left"><spring:message code="common.view"/></span>
                        <span class="pull-right glyphicon glyphicon glyphicon-open"></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>

        <div class="col-lg-4 col-md-4">
            <div class="panel panel-cream">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3><spring:message code="common.my_feedback"/></h3>
                        </div>
                    </div>
                </div>
                <a href="user/show_votes">
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