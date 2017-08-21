<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restAdmHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-4">
            <a href="admin/show_hotels">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Hotels</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-4">
            <a href="admin/show_users">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Users</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-4">
            <a href="admin/show_super_bookings">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Bookings</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-4">
            <a href="admin/show_votes">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Feedback</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-4">
            <a href="admin/show_apt_types">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Apt. Types</h2>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-4">
            <a href="admin/show_regions">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <div class="panel-body">
                            <h2>Regions</h2>
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

