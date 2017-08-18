<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="jspHeadTag.jsp"/>
<body>

<div class="container">
    <div class="row">
        <nav class="navbar navbar-default mainNavbar" role="navigation" style="padding-top:12px !important;
        padding-bottom:1px !important;">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <a class="navbar-brand" href="index" style="font-family:Pacifico, serif;font-size: 30px; color: #555">
                    <spring:message code="app.title"/></a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <jsp:include page="langSwitch.jsp"/>

                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <sec:authorize access="isAuthenticated()">
                            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                                <a class="btn navbar-btn btn-danger" href="administrate"><spring:message code="common.admin"/></a>
                            </sec:authorize>
                            <sec:authorize access="hasAuthority('ROLE_MANAGER')">
                                <a class="btn navbar-btn btn-danger" href="manage"><spring:message code="common.manager"/></a>
                            </sec:authorize>
                        </sec:authorize>
                    </li>
                    <li>
                        <form:form class="navbar-form" action="logout" method="post">
                            <sec:authorize access="isAuthenticated()">
                                <a class="btn btn-info" href="profile"><sec:authentication
                                        property="principal.userTo.name"/>
                                    <spring:message code="app.profile"/></a>
                                <button class="btn btn-default" type="submit">
                                    <spring:message code="common.logout"/>
                                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                </button>
                            </sec:authorize>
                        </form:form>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li style="margin-left: 10px;">
                        <form>
                        <a href="new_object" class="btn btn-warning navbar-btn" style="border-radius: 12px">
                            <spring:message code="common.listobject"/></a>
                        </form>
                    </li>
                    <li style="margin-left: 10px;">
                        <form>
                        <a href="register" class="btn btn-success navbar-btn" style="border-radius: 12px">
                            <spring:message code="common.register"/></a>
                        </form>
                    <li>
                    <li style="margin-left: 10px;">
                        <form>
                        <a href="login" class="btn btn-primary navbar-btn" style="border-radius: 12px">
                            <spring:message code="common.login_as"/></a>
                        </form>
                    </li>
                </ul>

            </div>
        </nav>

    </div>
</div>

</body>
</html>
