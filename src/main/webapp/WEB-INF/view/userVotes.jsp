<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/head_tags/restHeadTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/userVotesDatatable.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <div class="row">

        <sec:authorize access="isAuthenticated()">
            <sec:authorize access="hasRole('ROLE_USER')">

            <div class="col-md-2">
                <jsp:include page="fragments/sidebars/userSidebar.jsp"/>
            </div>

            <div class="col-md-10">

                <div class="row" style="height: 70px">
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-body" style="padding: 10px;">
                                <h4 class="pull-left"><strong>My Votes:</strong></h4>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                                style="margin: 0 auto;" onclick="addUserVote()">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        </button>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped display" id="userVotesDatatable">
                                <thead>
                                <tr>
                                    <th><spring:message code="common.dateAdded"/></th>
                                    <th><spring:message code="common.rate"/></th>
                                    <th><spring:message code="common.comment"/></th>
                                    <th><spring:message code="common.hotelId"/></th>
                                    <th><spring:message code="common.hotelName"/></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>


            </sec:authorize>
        </sec:authorize>
    </div>
</div>



<div class="modal fade" id="userVoteEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="userVoteModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="voteDateAdded" class="control-label col-xs-3"><spring:message
                                code="common.dateAdded"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="voteDateAdded" name="dateAdded"
                                   placeholder="<spring:message code="common.dateAdded"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="voteRate" class="control-label col-xs-3"><spring:message
                                code="common.rate"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="voteRate" name="rate"
                                   placeholder="<spring:message code="common.rate"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="voteUserComment" class="control-label col-xs-3"><spring:message
                                code="common.comment"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="voteUserComment" name="userComment"
                                   placeholder="<spring:message code="common.comment"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="voteUName" class="control-label col-xs-3"><spring:message
                                code="common.bookerName"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="voteUName" name="user.name"
                                   placeholder="<spring:message code="common.bookerName"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button"
                                    onclick="saveUserVote()">
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