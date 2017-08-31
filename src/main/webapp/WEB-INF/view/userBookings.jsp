<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/userBookingsDatatable.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <sec:authorize access="isAuthenticated()">
            <sec:authorize access="hasRole('ROLE_USER')">


            <div class="col-md-2">
                <jsp:include page="fragments/sidebars/userSidebar.jsp"/>
            </div>

            <div class="col-md-10">

                <div class="row" style="height: 70px">
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-body" style="padding: 10px;">
                                <h4 class="pull-left"><strong><spring:message code="common.my_bookings"/></strong></h4>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                                <table class="table table-striped table-bordered display" id="userSuperBookingsDatatable">
                                    <thead>
                                    <tr>
                                        <th><spring:message code="common.id"/></th>
                                        <th><spring:message code="common.dateAdded"/></th>
                                        <th><spring:message code="common.inDate"/></th>
                                        <th><spring:message code="common.outDate"/></th>
                                        <th><spring:message code="super_bookings.hotelId"/></th>
                                        <th><spring:message code="super_bookings.hotelName"/></th>
                                        <th><spring:message code="super_bookings.apartmentsNum"/></th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

        </sec:authorize>
        </sec:authorize>
    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>