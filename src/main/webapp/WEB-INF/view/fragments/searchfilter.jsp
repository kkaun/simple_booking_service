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
            <label for="person_num" class="control-label">No. of Persons</label>
            <select class="form-control" name="personNum" id="person_num">
                <c:forEach items="${personNums}" var="personNum">
                    <option value="${param.personNum}">${personNum}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="category" class="control-label">Room Type</label>
            <select class="form-control" name="category" id="category">
                <c:forEach items="${categories}" var="category">
                    <option value="${param.category}">${category}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="price_from" class="control-label">Min Price</label>
            <div class="input-group">
                <div class="input-group-addon" id="basic-addon1">$</div>
                <input type="text" class="form-control" name="priceFrom" id="price_from"
                       value="${param.priceFrom}" aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="form-group">
            <label for="price_to" class="control-label">Max Price</label>
            <div class="input-group">
                <div class="input-group-addon" id="basic-addon2">$</div>
                <input type="text" class="form-control" name="priceTo" id="price_to"
                       value="${param.priceTo}" aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="form-group">
            <label for="in_date" class="control-label">Max Price</label>
            <div class="input-group">
                <input type="date" class="form-control" id="in_date" name="inDate" value="${param.inDate}">
            </div>
        </div>
        <div class="form-group">
            <label for="out_date" class="control-label">Max Price</label>
            <div class="input-group">
                <input type="date" class="form-control" id="out_date" name="outDate" value="${param.outDate}">
            </div>
        </div>
        <p class="text-center"><a href="#" class="btn btn-danger glyphicon glyphicon-search" role="button"></a></p>
    </form>
</div>
</body>
</html>
