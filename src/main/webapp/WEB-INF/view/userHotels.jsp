<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notifications.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/userAjaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/userHotelsDatatable.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-responsive/2.1.1/js/dataTables.responsive.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <sec:authorize access="isAuthenticated()">
            <sec:authorize access="hasRole('ROLE_USER')">

                <div class="col-md-2">
                    <jsp:include page="fragments/sidebars/userSidebar.jsp"/>
                </div>

                <div class="col-md-10">

                    <div class="row" style="height: 70px">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-body" style="padding: 10px;">
                                    <h4 class="pull-left"><strong><spring:message
                                            code="common.booked_visited_hotels"/></strong></h4>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table responsive table-striped table-bordered display" id="userHotelsDatatable">
                                    <thead>
                                    <tr>
                                        <th></th>
                                        <th><spring:message code="common.image"/></th>
                                        <th><spring:message code="common.id"/></th>
                                        <th><spring:message code="common.name"/></th>
                                        <th><spring:message code="hotels.rating"/></th>
                                        <th><spring:message code="hotels.votesNum"/></th>
                                        <th><spring:message code="hotels.city"/></th>
                                        <th><spring:message code="common.country"/></th>
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


<div class="modal fade" id="userHotelVoteEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="userHotelVoteModalTitle"><spring:message code="common.edit_vote"/></h2>
            </div>
            <div class="modal-body">
                <h4><span><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span>
                    <spring:message code="common.all_active_inputs_required"/></h4>
                <hr>
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="dateAdded" name="dateAdded">
                    <input type="hidden" id="userName" name="userName">
                    <input type="hidden" id="hotelId" name="hotelId">

                    <div class="form-group">
                        <label for="hotelName" class="control-label col-xs-3"><spring:message
                                code="common.bookerName"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="hotelName" name="hotelName" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="voteRate" class="control-label col-xs-3"><spring:message
                                code="common.rate"/></label>
                        <div class="col-xs-3">
                            <input class="form-control" id="voteRate" name="rate" min="0" max="10">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="voteUserComment" class="control-label col-xs-3"><spring:message
                                code="common.comment"/></label>
                        <div class="col-xs-9">
                            <textarea class="form-control" id="voteUserComment" name="userComment">
                            </textarea>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <button class="btn btn-primary pull-right" type="button"
                                    onclick="createUserVoteByHotel()">
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