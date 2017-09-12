<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript">
    var objectId = '${objectId}';
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/extensions/gantt/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/extensions/gantt/js/dataDays.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/extensions/gantt/js/dataDaysEnh.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/extensions/gantt/js/dataHours.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/extensions/gantt/js/jquery.cookie.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/extensions/gantt/js/jquery.fn.gantt.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_manager/hotelChart.js" defer></script>
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
                            <h4 class="pull-left"><strong><spring:message code="common.obj_chart"/> #${objectId}:</strong></h4>
                        </div>
                    </div>
                </div>
            </div>

            <div class="gantt"></div>

        </div>
    </div>
</div>

</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>



