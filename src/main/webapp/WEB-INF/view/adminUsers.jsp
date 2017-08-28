<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restHeadTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/usersDatatable.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
        </div>

        <div class="col-md-10">
            <div class="row" style="height: 70px">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong>Users Data:</strong></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                            style="margin: 0 auto;" onclick="addUser()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                    <table class="table table-striped table-bordered display" id="usersDatatable">
                        <thead>
                        <tr>
                            <th><spring:message code="common.id"/></th>
                            <th><spring:message code="user.name"/></th>
                            <th><spring:message code="user.email"/></th>
                            <th><spring:message code="users.phone"/></th>
                            <th><spring:message code="users.roles"/></th>
                            <th><spring:message code="user.active"/></th>
                            <th><spring:message code="users.registered"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>


<div class="modal fade" id="userEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h2 class="modal-title" id="userUpdateModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="id" name="active">
                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="user.name"/></label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="user.name"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message code="user.email"/></label>
                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="<spring:message code="user.email"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="control-label col-xs-3"><spring:message code="users.phone"/></label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="phone" name="phone" placeholder="<spring:message code="users.phone"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><spring:message
                                code="users.password"/></label>
                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="<spring:message code="users.password"/>">
                        </div>
                    </div>
                    <div class="form-group userRoleInput">
                        <label for="role" class="control-label col-xs-3"><spring:message code="users.roles"/></label>
                        <c:if test="${not empty roles}">
                            <div class="col-xs-9">
                                <select class="form-control" name="userRole" id="role">
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role}">${role}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" onclick="saveUser()" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>

<jsp:include page="fragments/footer.jsp"/>

