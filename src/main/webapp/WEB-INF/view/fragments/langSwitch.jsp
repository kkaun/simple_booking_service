<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="navbar-nav navbar-left">
    <ul class="list-unstyled btn navbar-btn btn-default" style="margin-bottom: 10px">
        <li class="dropdown">
            <a href="#" style="color:#000000 !important; text-decoration:none; text-transform: capitalize;"
               class="dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale} <b class="caret"></b></a>
            <ul class="dropdown-menu list-unstyled">
                <li>
                    <a href="index?lang=en">
                    En
                    </a>
                </li>
                <li>
                    <a href="index?lang=ru">
                    Ru
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</div>


<script type="text/javascript">
    var localeCode = "${pageContext.response.locale}";
</script>
