<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h2>${userTo.name}'s <spring:message code="${register ? 'app.register' : 'app.profile'}"/></h2>

        <form:form modelAttribute="userTo" class="form-horizontal" method="post" action="${register ? 'register' : 'profile'}"
                   charset="utf-8" accept-charset="UTF-8">

            <spring:message code="user.name" var="userName"/>
            <custom:inputField label='${userName}' name="name"/>

            <spring:message code="user.email" var="userEmail"/>
            <custom:inputField label='${userEmail}' name="email"/>

            <spring:message code="users.password" var="userPhone"/>
            <custom:inputField label='${userPhone}' name="password" inputType="password"/>

            <spring:message code="users.password" var="userPassword"/>
            <custom:inputField label='${userPassword}' name="password" inputType="password"/>


            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                    </button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>