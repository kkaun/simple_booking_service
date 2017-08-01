<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container">

    <div class="row">

        <div class="col-md-1"></div>

        <div class="col-md-10">

            <h3>Countries</h3>

            <table class="table-responsive table-bordered">
                <thead>
                <tr>
                    <th>Country or City</th>
                </tr>
                </thead>

                <c:forEach items="${regions}" var="country">
                    <jsp:useBean id="country" scope="page" type="com.kirak.model.Country"/>
                    <tr>
                        <td><a href="regions/country_id?id=${country.id}">${country.name}</a></td>
                    </tr>
                </c:forEach>

                <c:forEach items="${cities}" var="city">
                    <jsp:useBean id="city" scope="page" type="com.kirak.model.City"/>
                    <tr>
                        <td><a href="regions/city_id?id=${city.id}">${city.name}</a></td>
                    </tr>
                </c:forEach>
            </table>

        </div>

        <div class="col-md-1"></div>
    </div>
</div>

</body>
</html>
