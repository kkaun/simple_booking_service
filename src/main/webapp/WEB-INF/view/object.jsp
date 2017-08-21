<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restAdmHeadTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manager/managerHotelsDatatable.js"
        defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-3">
            <a href="object/show_chart">
                <div class="panel-default text-center">
                    <div class="panel-body">
                        <h2>Hotels</h2>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="object/show_super_bookings">
                <div class="panel-default text-center">
                    <div class="panel-body">
                        <h2>Hotels</h2>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="object/show_hotel_votes">
                <div class="panel-default text-center">
                    <div class="panel-body">
                        <h2>Hotels</h2>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="object/show_apartments">
                <div class="panel-default text-center">
                    <div class="panel-body">
                        <h2>Hotels</h2>
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


