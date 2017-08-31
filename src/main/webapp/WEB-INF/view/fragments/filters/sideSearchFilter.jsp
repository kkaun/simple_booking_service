<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

<div class="well">
    <h3 align="center"><spring:message code="common.search_filter_title"/></h3>
    <hr>
    <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-10">
            <form:form class="form-horizontal" role="form" method="get" action="parametric_search">
                <div class="form-group">
                    <label for="location" class="control-label"><spring:message code="common.location_city"/></label>
                    <div class="input-group">
                        <input type="text" class="form-control" name="location" id="location" value="${param.region}">
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-globe"></span>
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="in_date" class="control-label"><spring:message code="hotels.checkIn"/></label>
                    <div class="input-group">
                        <input class="form-control in_date" name="inDate" id="in_date" value="${param.inDate}">
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="out_date" class="control-label"><spring:message code="hotels.checkOut"/></label>
                    <div class="input-group">
                        <input class="form-control out_date" name="outDate" id="out_date" value="${param.outDate}">
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="form-group">
                            <label for="person_num" class="control-label"><spring:message code="subBookings.personNum"/></label>
                            <select class="form-control" name="personNum" id="person_num">
                                <c:forEach items="${personNums}" var="personNum">
                                    <option value="${personNum}">${personNum}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-5">
                        <div class="form-group">
                            <label for="apartment_num" class="control-label"><spring:message code="super_bookings.apartmentsNum"/></label>
                            <select class="form-control" name="apartmentNum" id="apartment_num">
                                <c:forEach items="${apartmentNums}" var="apartmentNum">
                                    <option value="${apartmentNum}">${apartmentNum}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="category" class="control-label"><spring:message code="common.room_type"/></label>
                    <select class="form-control" name="category" id="category">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category}">${category}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row">
                    <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-lg btn-danger glyphicon glyphicon-search"
                                    style="margin-top:21px; padding-left: 30px; padding-right: 30px"></button>
                        </div>
                    <div class="col-md-4"></div>
                </div>
            </form:form>
        </div>
        <div class="col-md-1">
        </div>
    </div>
</div>

</body>
</html>
