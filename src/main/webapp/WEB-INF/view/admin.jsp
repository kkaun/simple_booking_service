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

        <div class="row">
            <div class="col-md-12">
                <div class="panel-group">
                    <div class="panel panel-default text-center">
                        <h2>Welcome back to Admin Panel</h2>
                        <hr>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3>Hotels</h3>
                        </div>
                    </div>
                </div>
                <a href="admin/show_hotels">
                    <div class="panel-footer">
                        <span class="pull-left">View/Manage</span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>

        <div class="col-lg-4 col-md-4">
            <div class="panel panel-purple">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3>Apartment Types</h3>
                        </div>
                    </div>
                </div>
                <a href="admin/show_apt_types">
                    <div class="panel-footer">
                        <span class="pull-left">View/Manage</span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>

        <div class="col-lg-4 col-md-4">
            <div class="panel panel-grey">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3>Regions/Cities</h3>
                        </div>
                    </div>
                </div>
                <a href="admin/show_regions">
                    <div class="panel-footer">
                        <span class="pull-left">View/Manage</span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>

        <div class="col-lg-4 col-md-4">
            <div class="panel panel-green">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3>Users</h3>
                        </div>
                    </div>
                </div>
                <a href="admin/show_users">
                    <div class="panel-footer">
                        <span class="pull-left">View/Manage</span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>

        <div class="col-lg-4 col-md-4">
            <div class="panel panel-red">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3>Bookings</h3>
                        </div>
                    </div>
                </div>
                <a href="admin/show_super_bookings">
                    <div class="panel-footer">
                        <span class="pull-left">View/Manage</span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>

        <div class="col-lg-4 col-md-4">
            <div class="panel panel-yellow">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <h3>Booker Votes</h3>
                        </div>
                    </div>
                </div>
                <a href="admin/show_votes">
                    <div class="panel-footer">
                        <span class="pull-left">View</span>
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

