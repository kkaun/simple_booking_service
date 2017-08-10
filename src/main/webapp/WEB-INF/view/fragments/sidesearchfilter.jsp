<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="well">
    <h3 align="center">Search Filter</h3>
    <form class="form-horizontal" method="post" action="parametric_search">
        <div class="form-group">
            <label for="location" class="control-label">Location(City)</label>
            <input type="text" class="form-control" name="location" id="location" value="${param.region}">
        </div>
        <div class="form-group">
            <label for="in_date" class="control-label">Check-in</label>
            <div class="input-group">
                <input type="date" class="form-control" id="in_date" name="inDate" value="${param.inDate}">
            </div>
        </div>
        <div class="form-group">
            <label for="out_date" class="control-label">Check-out</label>
            <div class="input-group">
                <input type="date" class="form-control" id="out_date" name="outDate" value="${param.outDate}">
            </div>
        </div>
        <div class="form-group">
            <label for="person_num" class="control-label">No. of Persons</label>
            <select class="form-control" name="personNum" id="person_num">
                <c:forEach items="${personNums}" var="personNum">
                    <option value="${personNum}">${personNum}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="apartment_num" class="control-label">Rooms (Apartments)</label>
            <select class="form-control" name="apartmentNum" id="apartment_num">
                <c:forEach items="${apartmentNums}" var="apartmentNum">
                    <option value="${apartmentNum}">${apartmentNum}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="category" class="control-label">Room Type</label>
            <select class="form-control" name="category" id="category">
                <c:forEach items="${categories}" var="category">
                    <option value="${category}">${category}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-danger glyphicon glyphicon-search"
                style="margin-top:21px; padding-left: 30px; padding-right: 30px"></button>
    </form>
</div>
</body>
</html>
