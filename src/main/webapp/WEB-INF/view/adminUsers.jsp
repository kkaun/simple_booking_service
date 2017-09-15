<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/datatablesUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/notifications.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_admin/adminAjaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_admin/usersDatatable.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-responsive/2.1.1/js/dataTables.responsive.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2" style="max-height: 400px;">
            <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
        </div>

        <div class="col-md-10">
            <div class="row" style="height: 70px">
                <div class="col-md-10">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong><spring:message code="common.users_list"/></strong></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                            style="margin: 0 auto;" onclick="addUser()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                    <table class="table responsive table-striped table-bordered display" id="usersDatatable">
                        <thead>
                        <tr>
                            <th></th>
                            <th><spring:message code="common.id"/></th>
                            <th><spring:message code="user.name"/></th>
                            <th><spring:message code="user.email"/></th>
                            <th><spring:message code="users.phone"/></th>
                            <th><spring:message code="users.role"/></th>
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
                <h2 class="modal-title" id="voteShowModalTitle"><spring:message code="common.edit_user"/></h2>
            </div>
            <div class="modal-body">
                <h4><span><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span>
                    <spring:message code="common.all_active_inputs_required"/></h4>
                <hr>
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="enabled" name="enabled">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="user.name"/></label>
                        <div class="col-xs-7">
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message code="user.email"/></label>
                        <div class="col-xs-7">
                            <input type="email" class="form-control" id="email" name="email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="control-label col-xs-3"><spring:message code="users.phone"/></label>
                        <div class="col-xs-7">
                            <input type="text" class="form-control" id="phone" name="phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><spring:message
                                code="users.password"/></label>
                        <div class="col-xs-7">
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                    </div>
                    <div class="form-group userRoleInput">
                        <label for="role" class="control-label col-xs-3"><spring:message code="users.role"/></label>
                        <c:if test="${not empty roles}">
                            <div class="col-xs-7">
                                <select class="form-control" name="role" id="role">
                                    <c:forEach items="${roles}" var="role">
                                        <option name="role" value="${role}">${role}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </div>
                    <hr>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <button class="btn btn-primary pull-right" type="button" onclick="saveUser()" class="btn btn-primary">
                                <spring:message code="common.save"/>&nbsp;&nbsp;
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <div class="load-bar"></div>
            </div>
        </div>
    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>

<jsp:include page="fragments/footer.jsp"/>

