<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restHeadTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/votesDatatable.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
        </div>


        <div class="col-md-10">
            <div class="row" style="height: 70px">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong><spring:message code="common.votes_list"/></strong></h4>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered display" id="votesDatatable">
                            <thead>
                            <tr>
                                <th><spring:message code="common.id"/></th>
                                <th><spring:message code="common.dateAdded"/></th>
                                <th><spring:message code="common.rate"/></th>
                                <th><spring:message code="common.comment"/></th>
                                <th><spring:message code="common.bookerName"/></th>
                                <th><spring:message code="common.userId"/></th>
                                <th><spring:message code="common.hotelId"/></th>
                                <th><spring:message code="common.hotelName"/></th>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>

<jsp:include page="fragments/footer.jsp"/>

