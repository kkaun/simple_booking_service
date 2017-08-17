<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>

<div class="jumbotron">
    <div class="container">
        <div class="row">
            <c:if test="${param.error}">
                <div class="error">
                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </div>
            </c:if>
            <c:if test="${not empty param.message}">
                <div class="message">
                    <spring:message code="${param.message}"/>
                </div>
            </c:if>
            <br/>
            <p>
                <a class="btn btn-lg btn-success" href="register">
                    <spring:message code="app.register"/> &raquo;
                </a>
                <button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('user1@yandex.ru', 'password1')">
                    <spring:message code="app.enter"/> User
                </button>
                <button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('admin@gmail.com', 'admin')">
                    <spring:message code="app.enter"/> Admin
                </button>
                <button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('manager@gmail.com', 'manager')">
                    <spring:message code="app.enter"/> Manager
                </button>
            </p>
        </div>

        <div class="row">

            <form:form class="navbar-form" role="form" action="spring_security_check" method="post">
                <div class="form-group">
                    <input type="text" placeholder="Email" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control" name="password">
                </div>
                <button type="submit" class="btn btn-success">
                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                </button>
            </form:form>

        </div>
    </div>
</div>


<script type="text/javascript">
    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "");
    </c:if>
    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>

</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>