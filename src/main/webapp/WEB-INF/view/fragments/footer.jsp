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
                $('#toTopHolder').fadeIn();
                $('#toTopBtn').fadeIn();
            } else {
                $('#toTopHolder').fadeOut();
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
        <div class="col-md-12">
            <ul class="nav nav-pills nav-justified" style="margin-bottom: 15px">
                <li>
                    <a href="#disclaimerModal" data-toggle="modal" data-target="#disclaimerModal">
                        <strong><spring:message code="common.licence"/></strong></a>
                </li>
                <li>
                    <a class="authorLink" href="https://kkaun.github.io/">
                    <img src="${pageContext.request.contextPath}/resources/suppl/author_logo_min.png"
                         style="width: 70px; height: 14px;"/></a>
                </li>
                <li>
                    <a href="mailto:kirkaun@gmail.com">
                        <strong><spring:message code="common.contact_author_mail"/></strong></a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="modal fade" id="disclaimerModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h2 class="modal-title" id="disclaimerModalTitle"><spring:message code="common.licence"/></h2>
            </div>
            <div class="modal-body">
                <p>This is a demo project, implementing basic functions of booking service, such as:</p>
                <ul>
                    <li> aggregating available placement options based on load factor of hotels(from this point on:
                        objects);
                    </li>
                    <li> booking of chosen apartment(s);</li>
                    <li> voting for featured objects;</li>
                    <li> managing user bookings;</li>
                    <li> managing own objects as hotel manager authority holder;</li>
                    <li> administrating the whole service as system admin role holder;</li>
                    <li> moderation as system admin role holder;</li>
                </ul>
                <p>At the moment CRUD/Admin/Manager parts are made as REST/ajax services. Business(*selling*) part
                    is less complex and made upon simple Spring MVC Controller processing, so I'm planning to transfer
                    this part on MVC/AngularJs tandem in nearest future.
                </p>
                <p>Current project stack: Maven/Spring(MVC, DataJPA, Security).
                </p>
                <br>
            </div>
            <div class="modal-footer text-center">
                <p>The project is published under MIT License (MIT)</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>

