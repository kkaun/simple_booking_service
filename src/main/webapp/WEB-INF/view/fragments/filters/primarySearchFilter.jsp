<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

<div class="well">
    <div class="row" style="margin-bottom: 40px">
        <div class="col-md-12">
            <form:form class="form-horizontal" role="form" method="get" action="parametric_search">
                <div class="col-md-12">
                    <div class="form-group row">
                        <div class="col-md-3">
                            <label for="primary-cities-input" class="control-label" style="border-radius: 3px">
                                <spring:message code="common.city"/><spring:message code="common.in_latin"/></label>
                            <div class="input-group">
                                <input type="text" name="location" class="primary-cities-input"
                                       id="primary-cities-input" value="${param.city}">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-globe"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <label for="in_date" class="control-label"><spring:message code="hotels.checkIn"/></label>
                            <div class="input-group">
                                <input class="form-control in_date" name="inDate" id="in_date" value="${param.inDate}">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <label for="out_date" class="control-label"><spring:message code="hotels.checkOut"/></label>
                            <div class="input-group">
                                <input class="form-control out_date" name="outDate" id="out_date" value="${param.outDate}">
                                        <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                            </div>
                        </div>
                        <div class="col-md-1">
                            <label for="person_num" class="control-label"><spring:message code="common.persons"/></label>
                            <div class="input-group">
                                <select class="input-sm" name="personNum" id="person_num">
                                    <c:forEach items="${personNums}" var="personNum">
                                        <option value="${personNum}">${personNum}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-1">
                            <label for="apartment_num" class="control-label"><spring:message code="common.rooms"/></label>
                            <div class="input-group">
                                <select class="input-sm" name="apartmentNum" id="apartment_num">
                                    <c:forEach items="${apartmentNums}" var="apartmentNum">
                                        <option value="${apartmentNum}">${apartmentNum}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <label for="category" class="control-label"><spring:message code="common.room_type"/></label>
                            <div class="input-group">
                                <select class="input-sm" name="category" id="category">
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category}">${category}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-1">
                            <button type="submit" class="btn btn-danger glyphicon glyphicon-search"
                                    style="margin-top:22px; padding-left: 30px; padding-right: 30px"></button>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
