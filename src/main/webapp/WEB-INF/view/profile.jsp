<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/head_tags/jspHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="panel panel-default text-center">
                <div class="panel-heading">
                    <h2><spring:message code="${register ? 'app.register' : 'app.profile'}"/> ${userTo.name}</h2>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="userTo" class="form-horizontal" method="post"
                               action="${register ? 'register' : 'profile'}"
                               charset="utf-8" accept-charset="UTF-8">

                        <spring:message code="user.name" var="userName"/>
                        <custom:inputField label='${userName}' name="name"/>

                        <spring:message code="user.email" var="userEmail"/>
                        <custom:inputField label='${userEmail}' name="email"/>

                        <spring:message code="users.phone" var="userPhone"/>
                        <custom:inputField label='${userPhone}' name="phone" inputType="text"/>

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
        </div>
        <div class="col-md-2"></div>
    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>
