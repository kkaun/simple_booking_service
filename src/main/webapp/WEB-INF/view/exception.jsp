<%@ page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-2"></div>

        <div class="col-md-8">
            <div class="panel panel-warning text-center">
                <div class="panel-body">
                    <c:if test="${not empty exception}">
                        <br>
                        <h4>Application error: </h4>
                        <br>
                        <h2>${exception.message}</h2>
                        <br>
                        <c:forEach items="${exception.stackTrace}" var="stackTrace">
                            ${stackTrace}
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="col-md-2"></div>
    </div>
</div>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>
