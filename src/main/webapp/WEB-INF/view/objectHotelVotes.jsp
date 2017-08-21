<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restAdmHeadTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manager/hotelVotesDatatable.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/managerSidebar.jsp"/>
        </div>

        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading" style="padding: 10px;">
                    <h3>Object Feedback</h3>
                </div>

                <div class="panel-body">
                    <table class="table table-striped display" id="hotelVotesDatatable">
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

</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>



