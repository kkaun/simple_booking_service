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

            <h3>Object # </h3>

            <table class="table-responsive table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>ID</th>
                    <th>ID</th>
                    <th>ID</th>
                </tr>
                </thead>
                <c:forEach items="${bookings}" var="booking">
                    <jsp:useBean id="booking" scope="page" type="com.kirak.model.Booking"/>
                    <td>${hotel.name}</td>
                    <td><a href="hotels/update?id=${booking.id}">Update</a></td>
                    <td><a href="hotels/delete?id=${booking.id}">Delete</a></td>
                </c:forEach>
            </table>

        </div>

        <div class="col-md-1"></div>
    </div>
</div>

</body>
</html>