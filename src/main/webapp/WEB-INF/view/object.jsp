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
                    <h1>Object <strong>${objectId}</strong> manager's page</h1>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <a href="hotel_manager/object/show_chart">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Calendar/Chart</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="hotel_manager/object/show_super_bookings">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Bookings</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="hotel_manager/object/show_hotel_votes">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Feedback</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="hotel_manager/object/show_apartments">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Apartments</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>


