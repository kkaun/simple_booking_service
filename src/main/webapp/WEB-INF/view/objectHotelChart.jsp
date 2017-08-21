<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restObjectHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-3">
            <jsp:include page="fragments/sidebars/managerSidebar.jsp"/>
        </div>

        <div class="col-md-9">

            <table class="table ganttTable" id="ganttTable">

            </table>

        </div>
    </div>
</div>
</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>



