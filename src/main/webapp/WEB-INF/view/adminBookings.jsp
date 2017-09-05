<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/bookingsDatatable.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-responsive/2.1.1/js/dataTables.responsive.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
        </div>

        <div class="col-md-10">

            <h3><spring:message code="super_bookings.title"/></h3>

            <div class="row">
                <div class="col-sm-6">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="bookingsAdminDatesAddedFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-3" for="startDate">
                                        <spring:message code="common.filter_title"/> <spring:message code="super_bookings.fromDate"/>:</label>
                                    <div class="col-sm-3">
                                        <input class="form-control start_date" name="startDate" id="startDate">
                                    </div>
                                    <label class="control-label col-sm-3" for="endDate">
                                        <spring:message code="common.filter_title"/> <spring:message code="super_bookings.toDate"/>:</label>
                                    <div class="col-sm-3">
                                        <input class="form-control end_date" name="endDate" id="endDate">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearSBDatesAddedAdminFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateAdminSBTableByDatesAdded()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-3">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="bookingsAdminUserIdFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="userId">
                                        <spring:message code="common.filter_title"/> <spring:message code="super_bookings.byUser"/>:</label>
                                    <div class="col-sm-7">
                                        <input class="form-control" name="userId" id="userId">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearSBUserIdAdminFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateAdminSBTableByUserId()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-3">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="bookingsAdminHotelIdFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="hotelId">
                                        <spring:message code="common.filter_title"/> <spring:message code="super_bookings.byHotel"/>:</label>
                                    <div class="col-sm-7">
                                        <input class="form-control" name="hotelId" id="hotelId">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearSBHotelIdAdminFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateAdminSBTableByHotelId()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-6">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="bookingsAdminInDateFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="inDate">
                                        <spring:message code="common.filter_title"/> <spring:message code="super_bookings.inDate"/>:</label>
                                    <div class="col-sm-7">
                                        <input class="form-control in_date" name="inDate" id="inDate">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearSBInDateAdminFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateAdminSBTableByInDate()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="bookingsAdminOutDateFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="outDate">
                                        <spring:message code="common.filter_title"/> <spring:message code="super_bookings.outDate"/>:</label>
                                    <div class="col-sm-7">
                                        <input class="form-control out_date" name="outDate" id="outDate">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearSBOutDateAdminFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateAdminSBTableByOutDate()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" style="height: 70px">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong><spring:message code="common.sb_list"/></strong></h4>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table responsive table-striped table-bordered display" id="bookingsDatatable">
                            <thead>
                            <tr>
                                <th><spring:message code="common.id"/><spring:message code="proceed"/></th>
                                <th><spring:message code="common.dateAdded"/></th>
                                <th><spring:message code="common.inDate"/></th>
                                <th><spring:message code="common.outDate"/></th>
                                <th><spring:message code="super_bookings.hotelId"/></th>
                                <th><spring:message code="super_bookings.hotelName"/></th>
                                <th><spring:message code="super_bookings.userId"/></th>
                                <th><spring:message code="bookings.active"/></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>

<jsp:include page="fragments/footer.jsp"/>

