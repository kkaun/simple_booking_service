<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

    <h3><spring:message code="super_bookings.title"/></h3>

    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form:form class="form-horizontal filter" id="superBookingsAdminDatesAddedFilter">
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="startDate"><spring:message
                                    code="super_bookings.fromDate"/>:</label>
                            <div class="col-sm-3">
                                <input class="form-control in_date" name="startDate" id="startDate">
                            </div>
                            <label class="control-label col-sm-4" for="endDate"><spring:message
                                    code="super_bookings.toDate"/>:</label>
                            <div class="col-sm-3">
                                <input class="form-control out_date" name="endDate" id="endDate">
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="panel-footer text-right">
                    <a class="btn btn-danger" type="button" onclick="clearSBDatesAddedAdminFilter()">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                    <a class="btn btn-primary" type="button" onclick="updateAdminSBTableByDatesAdded()">
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form:form class="form-horizontal filter" id="superBookingsAdminInDateFilter">
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="inDate"><spring:message
                                    code="super_bookings.inDate"/>:</label>
                            <div class="col-sm-3">
                                <input class="form-control in_date" name="inDate" id="inDate">
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="panel-footer text-right">
                    <a class="btn btn-danger" type="button" onclick="clearSBInDateAdminFilter()">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                    <a class="btn btn-primary" type="button" onclick="updateAdminSBTableByInDate()">
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form:form class="form-horizontal filter" id="superBookingsAdminOutDateFilter">
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="outDate"><spring:message
                                    code="super_bookings.outDate"/>:</label>
                            <div class="col-sm-3">
                                <input class="form-control out_date" name="outDate" id="outDate">
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="panel-footer text-right">
                    <a class="btn btn-danger" type="button" onclick="clearSBOutDateAdminFilter()">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                    <a class="btn btn-primary" type="button" onclick="updateAdminSBTableByOutDate()">
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form:form class="form-horizontal filter" id="superBookingsAdminUserIdFilter">
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
                    <a class="btn btn-danger" type="button" onclick="clearSBUserIdAdminFilter()">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                    <a class="btn btn-primary" type="button" onclick="updateAdminSBTableByUserId()">
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form:form class="form-horizontal filter" id="superBookingsAdminHotelIdFilter">
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="hotelId"><spring:message
                                    code="super_bookings.byHotel"/>:</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="hotelId" id="hotelId">
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="panel-footer text-right">
                    <a class="btn btn-danger" type="button" onclick="clearSBHotelIdAdminFilter()">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                    <a class="btn btn-primary" type="button" onclick="updateAdminSBTableByHotelId()">
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <h3><spring:message code="hotels.title"/></h3>

    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form:form class="form-horizontal filter" id="hotelsByRatingAdminFilter">
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="minRating"><spring:message
                                    code="hotels.minRating"/>:</label>
                            <div class="col-sm-3">
                                <input class="form-control" name="minRating" id="minRating">
                            </div>
                            <label class="control-label col-sm-3" for="maxRating"><spring:message
                                    code="hotels.maxRating"/>:</label>

                            <div class="col-sm-2">
                                <input class="form-control" name="maxRating" id="maxRating">
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="panel-footer text-right">
                    <a class="btn btn-danger" type="button" onclick="clearHotelsByRatingFilter()">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                    <a class="btn btn-primary" type="button" onclick="updateHotelsTableByRating()">
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form:form class="form-horizontal filter" id="hotelsByCityAdminFilter">
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="cityName"><spring:message
                                    code="hotels.byCity"/>:</label>
                            <c:if test="${not empty cities}">
                                <div class="form-group">
                                    <div class="col-xs-9">
                                        <select class="form-control" name="cityName" id="cityName">
                                            <c:forEach items="${cities}" var="city">
                                                <option value="${city.name}">${city.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </form:form>
                </div>
                <div class="panel-footer text-right">
                    <a class="btn btn-danger" type="button" onclick="clearHotelsByCityFilter()">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                    <a class="btn btn-primary" type="button" onclick="updateHotelsTableByCity()">
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <h3><spring:message code="cities.title"/></h3>

    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form:form class="form-horizontal filter" id="citiesAdminFilter">
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="region"><spring:message
                                    code="city.byRegion"/>:</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="region" id="region">
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="panel-footer text-right">
                    <a class="btn btn-danger" type="button" onclick="clearPlacesFilter()">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                    <a class="btn btn-primary" type="button" onclick="updatePlacesTable()">
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
