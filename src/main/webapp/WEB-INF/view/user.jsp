<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/head_tags/jspHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-4">
            <a href="user/show_bookings">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>My Bookings</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-4">
            <a href="user/show_hotels">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Visited Hotels</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-4">
            <a href="user/show_votes">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>My Feedback</h2>
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