<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restHeadTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/gantt/js/dataDays.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/gantt/js/dataDaysEnh.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/gantt/js/dataHours.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/gantt/js/jquery.cookie.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/gantt/js/jquery.fn.gantt.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/managerSidebar.jsp"/>
        </div>

        <div class="col-md-10">
            <div class="row" style="height: 70px">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong>Object's Bookings Chart</strong></h4>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table ganttTable" id="ganttTable">

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



