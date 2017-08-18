<%@page contentType="text/html" pageEncoding="UTF-8" %>

<ul class="navbar-nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<b class="caret"></b></a>
        <ul class="dropdown-menu">
            <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">En</a></li>
            <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Ru</a></li>
        </ul>
    </li>
</ul>
<script type="text/javascript">
    var localeCode = "${pageContext.response.locale}";
</script>
