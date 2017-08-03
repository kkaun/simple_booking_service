<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container">
    <div class="row" style="text-align: center">

        <c:forEach items="${managableObjects}" var="object">
            <div class="col-md-12">
            <div class="well">

            </div>
            </div>
        </c:forEach>

    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>


