<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>

<div class="container">

    <div class="row">

        <div class="col-md-1"></div>

        <div class="col-md-10">

            <h3>Hotels</h3>

            <table class="table-responsive table-bordered">
                <thead>
                <tr>
                    <th>Country</th>
                </tr>
                </thead>

                <c:forEach items="${countries}" var="country">
                    <jsp:useBean id="country" scope="page" type="com.kirak.model.Country"/>
                    <tr>
                        <td>${country.name}</td>
                    </tr>
                </c:forEach>

            </table>

        </div>

        <div class="col-md-1"></div>
    </div>
</div>

</body>
</html>