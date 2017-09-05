<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var i18n = [];
    <c:forEach var='key' items='<%=new String[]{"common.deleted",
                                                "common.saved",
                                                "common.enabled",
                                                "common.booking_activated",
                                                "common.booking_deactivated",
                                                "common.disabled",
                                                "common.errorStatus",
                                                "common.search",
                                                "common.processing",
                                                "common.table_info",
                                                "common.menu_length",
                                                "common.loading_records",
                                                "common.zero_records",
                                                "common.empty_table",
                                                "common.paging_first",
                                                "common.paging_previous",
                                                "common.paging_next",
                                                "common.paging_last"}%>'>
    i18n['${key}'] = '<spring:message code="${key}"/>';
    </c:forEach>
</script>