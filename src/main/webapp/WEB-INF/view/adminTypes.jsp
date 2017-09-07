<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notifications.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/adminAjaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/aptTypesDatatable.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-responsive/2.1.1/js/dataTables.responsive.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
        </div>

        <div class="col-md-10">

            <div class="row" style="height: 70px">
                <div class="col-md-10">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong><spring:message code="common.apt_types_list"/></strong></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                            style="margin: 0 auto;" onclick="addAptType()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table responsive table-striped table-bordered display" id="aptTypesDatatable">
                            <thead>
                            <tr>
                                <th></th>
                                <th><spring:message code="common.id"/></th>
                                <th><spring:message code="apt_types.personNum"/></th>
                                <th><spring:message code="apt_types.category"/></th>
                                <th><spring:message code="apt_types.bedsArrangement"/></th>
                                <th><spring:message code="apt_types.hotelsUsing"/></th>
                                <th><spring:message code="apt_types.apartmentsAppliedTo"/></th>
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


<div class="modal fade" id="aptTypeEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="aptTypeModalTitle"><spring:message code="common.edit_apt_type"/></h2>
            </div>
            <div class="modal-body">
                <h4><span><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span>
                    <spring:message code="common.all_active_inputs_required"/></h4>
                <hr>
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="personNum" class="control-label col-xs-3"><spring:message
                                code="apt_types.personNum"/></label>
                        <div class="col-xs-3">
                            <input class="form-control" id="personNum" name="personNum">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="category" class="control-label col-xs-3"><spring:message
                                code="apt_types.category"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="category" name="category">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bedsArrangement" class="control-label col-xs-3"><spring:message
                                code="apt_types.bedsArrangement"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="bedsArrangement" name="bedsArrangement">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <button class="btn btn-primary pull-right" type="button" onclick="saveAptType()">
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

