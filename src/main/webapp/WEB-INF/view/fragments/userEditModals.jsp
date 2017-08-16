<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body>

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
                                    onclick="saveUserVote()"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>