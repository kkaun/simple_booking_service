<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container">

        <div class="row">
            <div class="col-md-12">


            </div>
        </div>

    <div class="row">
            <%--<div class="col-md-12">--%>
                <%--<div class="well">--%>
                    <%--<table class="table table-responsive">--%>
                        <%--<caption>Summary</caption>--%>
                        <%--<tr>--%>
                            <%--<td>${hotel.name}</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>${hotel.address}</td>--%>
                        <%--</tr>--%>
                        <%--<c:if test="${not empty hotel.phone}">--%>
                            <%--<tr>--%>
                                <%--<td>${hotel.phone}</td>--%>
                            <%--</tr>--%>
                        <%--</c:if>--%>
                        <%--<tr>--%>
                            <%--<td>No. of person</td>--%>
                            <%--<td>${placementPersonNum}</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>No. of rooms</td>--%>
                            <%--<td>${placementApartmentNum}</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>Check-in</td>--%>
                            <%--<td>${placementInDate}</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>Check-out</td>--%>
                            <%--<td>${placementOutDate}</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>Total sum</td>--%>
                            <%--<td>${placementSum}</td>--%>
                        <%--</tr>--%>
                    <%--</table>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>

</div>
</body>
</html>

<jsp:include page="fragments/footer.jsp"/>

