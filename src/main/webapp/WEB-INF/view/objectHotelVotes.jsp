<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manager/hotelVotesDatatable.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/managerSidebar.jsp"/>
        </div>

        <div class="col-md-10">
            <div class="row" style="height: 70px">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong><spring:message code="common.obj_votes_list"/></strong></h4>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered display" id="hotelVotesDatatable">
                            <thead>
                            <tr>
                                <th><spring:message code="common.dateAdded"/></th>
                                <th><spring:message code="common.rate"/></th>
                                <th><spring:message code="common.comment"/></th>
                                <th><spring:message code="common.bookerName"/></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="voteShowRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="voteShowModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="voteDateAdded" class="control-label col-xs-3"><spring:message
                                code="common.dateAdded"/></label>
                        <div class="col-xs-5">
                            <input class="form-control" id="voteDateAdded" name="dateAdded"
                                   placeholder="<spring:message code="common.dateAdded"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="hotelId" class="control-label col-xs-3"><spring:message
                                code="common.rate"/></label>
                        <div class="col-xs-5">
                            <input class="form-control" id="hotelId" name="hotelId"
                                   placeholder="<spring:message code="common.rate"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="hotelName" class="control-label col-xs-3"><spring:message
                                code="common.rate"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="hotelName" name="hotelName"
                                   placeholder="<spring:message code="common.rate"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="voteRate" class="control-label col-xs-3"><spring:message
                                code="common.rate"/></label>
                        <div class="col-xs-3">
                            <input class="form-control" id="voteRate" name="rate"
                                   placeholder="<spring:message code="common.rate"/>" readonly>
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
                        <label for="voteUserComment" class="control-label col-xs-3"><spring:message
                                code="common.comment"/></label>
                        <div class="col-xs-9">
                            <textarea class="form-control" id="voteUserComment" name="userComment"
                                      placeholder="<spring:message code="common.comment"/>" readonly>
                            </textarea>
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



