<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript">
    var objectId = '${objectId}';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/datatablesUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/notifications.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_manager/managerObjectChildrenAjaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_manager/hotelBookingsDatatable.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-responsive/2.1.1/js/dataTables.responsive.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/managerSidebar.jsp"/>
        </div>

        <div class="col-md-10">

            <div class="row">

                <div class="col-sm-6">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="bookingsManagerDatesAddedFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="startDate">
                                        <spring:message code="super_bookings.fromDate"/>:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control start_date" name="startDate" id="startDate">
                                    </div>
                                    <label class="control-label col-sm-2" for="endDate">
                                        <spring:message code="super_bookings.toDate"/>:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control end_date" name="endDate" id="endDate">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearBookingDatesAddedManagerFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateManagerBookingTableByDatesAdded()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-3">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="bookingsManagerInDateFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="inDate">
                                        <spring:message code="super_bookings.inDate"/>:</label>
                                    <div class="col-sm-8">
                                        <input class="form-control in_date" name="inDate" id="inDate">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearBookingInDateManagerFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateManagerBookingTableByInDate()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-3">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="bookingsManagerOutDateFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="outDate">
                                        <spring:message code="super_bookings.outDate"/>:</label>
                                    <div class="col-sm-8">
                                        <input class="form-control out_date" name="outDate" id="outDate">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearBookingOutDateManagerFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateManagerBookingTableByOutDate()">
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
                            <h4 class="pull-left"><strong><spring:message code="common.obj_bookings"/></strong></h4>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table responsive table-striped table-bordered display" id="hotelBookingsDatatable">
                            <thead>
                            <tr>
                                <th></th>
                                <th><spring:message code="common.id"/><spring:message code="common.proceed"/></th>
                                <th><spring:message code="common.dateAdded"/></th>
                                <th><spring:message code="super_bookings.inDate"/></th>
                                <th><spring:message code="super_bookings.outDate"/></th>
                                <th><spring:message code="user.name"/></th>
                                <th><spring:message code="user.email"/></th>
                                <th><spring:message code="users.phone"/></th>
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



