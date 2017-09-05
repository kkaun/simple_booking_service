<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>

<script type="text/javascript">
    $(document).ready(function () {
        $('body').append(
            '<div id="toTopBtn" class="btn btn-default">' +
            '<span class="glyphicon glyphicon-chevron-up"></span></div>');
        $(window).scroll(function () {
            if ($(this).scrollTop() !== 0) {
                $('#toTopBtn').fadeIn();
            } else {
                $('#toTopBtn').fadeOut();
            }
        });
        $('#toTopBtn').click(function () {
            $("html, body").animate({scrollTop: 0}, 600);
            return false;
        });
    });
</script>

<div class="container text-center" style="margin-bottom: 40px">
    <hr>
    <div class="row">
        <div class="col-lg-12">
            <ul class="nav nav-pills nav-justified" style="margin-bottom: 15px">
                <li><a href="#"><spring:message code="common.licence"/></a></li>
                <li><a class="authorLink" href="https://kkaun.github.io/">
                    <img src="/images/author_logo_min.png" style="width: 70px; height: 14px;"/></a></li>
                <li><a href="#"><spring:message code="common.contact_author_mail"/></a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>

