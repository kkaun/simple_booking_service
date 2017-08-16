<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<body>

<div class="container">
    <div class="row">

        <nav class="navbar navbar-default mainNavbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index"><spring:message code="app.title"/></a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <jsp:include page="langSwitch.jsp"/>

                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <form:form class="navbar-form" action="logout" method="post">
                            <sec:authorize access="isAuthenticated()">
                                <sec:authorize access="hasRole('ROLE_SYSTEM_ADMIN')">
                                    <a class="btn btn-info" href="admin"><spring:message code="common.admin"/></a>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_HOTEL_MANAGER')">
                                    <a class="btn btn-info" href="manager"><spring:message code="common.manager"/></a>
                                </sec:authorize>

                                <a class="btn btn-info" href="profile"><sec:authentication
                                        property="principal.userTo.name"/>
                                    <spring:message code="app.profile"/></a>
                                <button class="btn btn-primary" type="submit">
                                    <spring:message code="common.logout"/>
                                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                </button>
                            </sec:authorize>
                        </form:form>
                    </li>
                </ul>


                <ul class="nav navbar-nav navbar-right">
                    <li class="headBtnHolder"><button type="button" href="newobject" class="btn btn-default btn-md btn-block headBtn">
                        <spring:message code="common.listobject"/></button></li>
                    <li class="headBtnHolder"><button type="button" href="register" class="btn btn-default btn-md btn-block headBtn">
                        <spring:message code="common.register"/></button></li>
                    <li class="headBtnHolder"><button type="button" href="" class="btn btn-default btn-md btn-block headBtn">
                        <spring:message code="common.login_as"/></button></li>
                </ul>
            </div>
        </nav>

    </div>
</div>

</body>
</html>
