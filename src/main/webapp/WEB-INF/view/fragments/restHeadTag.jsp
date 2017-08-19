<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://kirak.com.functions" prefix="f" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title><spring:message code="app.title"/></title>
    <base href="${pageContext.request.contextPath}/"/>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">

    <link rel="stylesheet" href="webjars/jquery-ui/1.9.2/css/smoothness/jquery-ui-1.9.2.custom.min.css">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.15/media/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datetimepicker/2.5.4/jquery.datetimepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/gantt/css/style.css">

    <!--http://stackoverflow.com/a/24070373/548473-->
    <script type="text/javascript" src="webjars/jquery/3.2.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/jquery-ui/1.9.2/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="webjars/datatables/1.10.15/media/js/jquery.dataTables.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.15/media/js/dataTables.bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/noty/2.4.1/js/noty/packaged/jquery.noty.packaged.min.js" defer></script>
    <script type="text/javascript" src="webjars/datetimepicker/2.5.4/build/jquery.datetimepicker.full.min.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datepicker.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datetimepicker.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scripts.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/gantt/js/dataDays.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/gantt/js/dataDaysEnh.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/gantt/js/dataHours.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/gantt/js/jquery.cookie.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/gantt/js/jquery.fn.gantt.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/aptTypesDatatable.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/hotelsDatatable.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/regionsDatatable.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/superBookingsDatatable.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/usersDatatable.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/votesDatatable.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manager/apartmentsDatatable.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manager/hotelChart.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manager/hotelSuperBookingsDatatable.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manager/hotelVotesDatatable.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/userHotelsDatatable.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/userSuperBookingsDatatable.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/userVotesDatatable.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cross_role/bookingsDatatable.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajaxUtil.js" defer></script>

</head>