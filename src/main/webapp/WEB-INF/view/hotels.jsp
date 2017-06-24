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
                <th>Name</th>
                <th>Rating</th>
                <th>Stars</th>
                <th>Description</th>
                <th>Votes number</th>
            </tr>
            </thead>
            <c:forEach items="${hotels}" var="hotel">
            <jsp:useBean id="hotel" scope="page" type="com.kirak.to.HotelTo"/>
            <td>${hotel.name}</td>
            <td>${hotel.rating}</td>
            <td>${hotel.stars}</td>
            <td>${hotel.description}</td>
            <td>${hotel.votes.size}</td>
            <td><a href="hotels/update?id=${meal.id}">Update</a></td>
            <td><a href="hotels/delete?id=${meal.id}">Delete</a></td>

        </table>

        </div>

        <div class="col-md-1"></div>
    </div>
</div>
</body>
</html>