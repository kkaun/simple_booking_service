<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="head_tags/jspHeadTag.jsp"/>
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
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                            <c:if test="${requestScope['javax.servlet.forward.request_uri'] ne '/admin'}">
                                <li style="margin-left: 10px;">
                                    <form>
                                        <a class="btn navbar-btn btn-danger" href="administrate" style="border-radius: 15px">
                                            <spring:message code="common.admin"/></a>
                                    </form>
                                </li>
                            </c:if>
                        </sec:authorize>
                        <sec:authorize access="hasAuthority('ROLE_MANAGER')">
                            <c:if test="${requestScope['javax.servlet.forward.request_uri'] ne '/objects'}">
                                <li style="margin-left: 10px;">
                                    <form>
                                        <a class="btn navbar-btn btn-danger" href="manage" style="border-radius: 15px">
                                            <spring:message code="common.manager"/></a>
                                    </form>
                                </li>
                            </c:if>
                        </sec:authorize>
                        <sec:authorize access="hasAuthority('ROLE_USER')">
                            <c:if test="${requestScope['javax.servlet.forward.request_uri'] ne '/user'}">
                                <li style="margin-left: 10px;">
                                    <form>
                                        <a class="btn navbar-btn btn-success" href="manage" style="border-radius: 15px">
                                            <spring:message code="common.user"/></a>
                                    </form>
                                </li>
                            </c:if>
                        </sec:authorize>
                    </sec:authorize>
                    <li style="margin-left: 10px;">
                        <form:form class="navbar-form" action="logout" method="post">
                            <sec:authorize access="isAuthenticated()">
                                <c:if test="${requestScope['javax.servlet.forward.request_uri'] ne '/profile'}">
                                    <a class="btn btn-info" href="profile" style="border-bottom-left-radius: 15px; border-top-left-radius: 15px;">
                                        <sec:authentication property="principal.userTo.name"/>
                                        <spring:message code="app.profile"/></a>
                                </c:if>
                                <button class="btn btn-default" type="submit">
                                    <spring:message code="common.logout"/>
                                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                </button>
                            </sec:authorize>
                        </form:form>
                    </li>
                </ul>

                <sec:authorize access="isAnonymous()">
                    <ul class="nav navbar-nav navbar-right">
                        <li style="margin-left: 10px;">
                            <form>
                                <a href="list_object" class="btn btn-warning navbar-btn" style="border-radius: 15px">
                                    <spring:message code="common.listobject"/></a>
                            </form>
                        </li>
                        <li style="margin-left: 10px;">
                            <form>
                                <a href="register" class="btn btn-success navbar-btn" style="border-radius: 15px">
                                    <spring:message code="common.register"/></a>
                            </form>
                        <li>
                        <li style="margin-left: 10px;">
                            <form>
                                <a href="login" class="btn btn-primary navbar-btn" style="border-radius: 15px">
                                    <spring:message code="common.login_as"/></a>
                            </form>
                        </li>
                    </ul>
                </sec:authorize>

            </div>
        </nav>

    </div>
</div>

</body>
</html>
