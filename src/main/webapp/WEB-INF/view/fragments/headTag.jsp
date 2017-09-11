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

    <link rel="shortcut icon" href='<c:url value="/resources/suppl/favicon.ico"/>' type="image/x-icon">
    <link rel="icon" href='<c:url value="/resources/suppl/favicon.ico"/>' type="image/x-icon">

    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">

    <link rel="stylesheet" href="webjars/jquery-ui/1.9.2/css/smoothness/jquery-ui-1.9.2.custom.min.css">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.15/media/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datetimepicker/2.5.4/jquery.datetimepicker.css">
    <link rel="stylesheet" href="webjars/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="webjars/datatables-responsive/2.1.1/css/responsive.dataTables.scss">
    <link rel="stylesheet" href="webjars/datatables-responsive/2.1.1/css/responsive.jqueryui.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/extensions/autocomplete/easy-autocomplete.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/extensions/autocomplete/easy-autocomplete.themes.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <script type="text/javascript" src="webjars/jquery/3.2.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/jquery-ui/1.9.2/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="webjars/parsleyjs/2.7.2/parsley.js"></script>
    <script type="text/javascript" src="webjars/datatables/1.10.15/media/js/jquery.dataTables.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.15/media/js/dataTables.bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/noty/2.4.1/js/noty/packaged/jquery.noty.packaged.min.js" defer></script>
    <script type="text/javascript" src="webjars/datetimepicker/2.5.4/build/jquery.datetimepicker.full.min.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/datetimepickers.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/jquery.ui.datepicker-en.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/jquery.ui.datepicker-ru.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/extensions/autocomplete/jquery.easy-autocomplete.min.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/scripts.js"></script>
</head>