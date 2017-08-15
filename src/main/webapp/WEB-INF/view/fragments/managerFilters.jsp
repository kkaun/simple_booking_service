<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body>

<h3><spring:message code="super_bookings.title"/></h3>

<div class="row">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form class="form-horizontal" id="filter">
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="startDate"><spring:message
                                code="super_bookings.fromDate"/>:</label>

                        <div class="col-sm-3">
                            <input class="form-control" name="startDate" id="startDate">
                        </div>

                        <label class="control-label col-sm-4" for="endDate"><spring:message
                                code="super_bookings.toDate"/>:</label>

                        <div class="col-sm-3">
                            <input class="form-control" name="endDate" id="endDate">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-3" for="inDate"><spring:message
                                code="super_bookings.inDate"/>:</label>

                        <div class="col-sm-3">
                            <input class="form-control" name="inDate" id="inDate">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="outDate"><spring:message
                                code="super_bookings.outDate"/>:</label>

                        <div class="col-sm-3">
                            <input class="form-control" name="outDate" id="outDate">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="userId"><spring:message
                                code="super_bookings.byUser"/>:</label>

                        <div class="col-sm-2">
                            <input class="form-control" name="userId" id="userId">
                        </div>
                    </div>

                </form:form>
            </div>
            <div class="panel-footer text-right">
                <a class="btn btn-danger" type="button" onclick="clearFilter()">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </a>
                <a class="btn btn-primary" type="button" onclick="updateTable()">
                    <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                </a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
