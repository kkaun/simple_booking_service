<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/jspHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row text-center">

        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="panel panel-default text-center">
                <div class="panel-heading">
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
                    <c:if test="${not empty enterAsManager}">
                        <div>
                            <spring:message code="common.enter_as"/>&nbsp;<spring:message code="app.manager"/>
                        </div>
                    </c:if>
                    <br/>
                    <p>
                        <button type="submit" class="btn btn-lg btn-primary"
                                onclick="setCredentials('user1@yandex.ru', 'password1')">
                            <spring:message code="app.enter"/> <spring:message code="app.user"/>
                        </button>
                        <button type="submit" class="btn btn-lg btn-primary"
                                onclick="setCredentials('manager@gmail.com', 'manager')">
                            <spring:message code="app.enter"/> <spring:message code="app.manager"/>
                        </button>
                        <button type="submit" class="btn btn-lg btn-primary"
                                onclick="setCredentials('admin@gmail.com', 'admin')">
                            <spring:message code="app.enter"/> <spring:message code="app.admin"/>
                        </button>
                    </p>
                </div>

                <div class="row text-center" style="padding-top: 20px">

                    <form:form class="navbar-form" role="form" action="spring_security_check" method="post">
                        <div class="form-group">
                            <input type="text" placeholder="Email" class="form-control" name="username">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" class="form-control" name="password">
                        </div>
                        <button type="submit" class="btn btn btn-success" style="height: 35px;">Enter&nbsp;&nbsp;
                            <span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span>
                        </button>
                    </form:form>

                    <br>
                    <br>
                </div>
                <div class="panel-footer">
                    <a class="btn btn-lg btn-info" href="register_user">
                        <spring:message code="app.register"/> <spring:message code="common.as_user"/> &raquo;
                    </a>
                    <a class="btn btn-lg btn-info" href="register_manager">
                        <spring:message code="app.register"/> <spring:message code="common.as_manager"/> &raquo;
                    </a>
                </div>

            </div>
        </div>
        <div class="col-md-1"></div>
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
</html>
<jsp:include page="fragments/footer.jsp"/>