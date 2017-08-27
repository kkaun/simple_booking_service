<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/head_tags/jspHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">

    <sec:authorize access="isAnonymous()">
        <c:if test="${not empty registerUserAction}">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div class="panel panel-default text-center">
                        <div class="panel-heading">
                            <h2><spring:message code="app.register_user"/></h2>
                        </div>
                        <div class="panel-body">

                            <div class="col-md-2"></div>

                            <div class="col-md-8">

                                <div class="panel panel-warning hidden" style="padding: 15px; margin-bottom: 20px">
                                    <h4>Oh snap!</h4>
                                    <p>The form seems to be invalid in some place :(</p>
                                </div>

                                <div class="panel panel-info hidden" style="padding: 15px; margin-bottom: 20px">
                                    <h4>Nice!</h4>
                                    <p>Everything seems to be ok!</p>
                                </div>

                                <form:form modelAttribute="userTo" class="form-horizontal registration_form" method="post"
                                           action="register_user"
                                           charset="utf-8" accept-charset="UTF-8">

                                    <div class="form-group">
                                        <div class="input-group">
                                            <input id="user_name" type="text" class="form-control"
                                                   name="name"
                                                   data-parsley-pattern="^[a-zA-Z]+$"
                                                   data-parsley-required="true"
                                                   data-parsley-length="[3, 44]"
                                                   placeholder="Name" required>
                                            <span class="input-group-addon"><i
                                                    class="glyphicon glyphicon-user"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input id="user_phone" type="text" class="form-control"
                                                   name="phone"
                                                   data-parsley-required="true"
                                                   data-parsley-pattern="^[0-9\-\+\s\(\)]*$"
                                                   data-parsley-length="[5, 20]"
                                                   value="" placeholder="Phone" required>
                                            <span class="input-group-addon"><i
                                                    class="glyphicon glyphicon-earphone"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input id="user_email" type="email" class="form-control"
                                                   name="email"
                                                   data-parsley-required="true"
                                                   data-parsley-type="email"
                                                   value="" placeholder="Email" required>
                                            <span class="input-group-addon"><i
                                                    class="glyphicon glyphicon-envelope"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input id="user_password" type="password" class="form-control"
                                                   name="password"
                                                   data-parsley-required="true"
                                                   data-parsley-length="[6, 18]"
                                                   placeholder="Password" required>
                                            <span class="input-group-addon"><i
                                                    class="glyphicon glyphicon-lock"></i></span>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-3"></div>
                                        <div class="col-md-6">
                                            <button type="submit"
                                                    class="btn btn-success btn-lg btn-primary">
                                                Register
                                            </button>
                                        </div>
                                        <div class="col-md-3"></div>
                                    </div>
                                </form:form>

                            </div>
                            <div class="col-md-2"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>
        </c:if>
    </sec:authorize>

    <sec:authorize access="isAnonymous()">
        <c:if test="${not empty registerManagerAction}">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div class="panel panel-default text-center">
                        <div class="panel-heading">
                            <h2><spring:message code="app.register_manager"/></h2>
                        </div>
                        <div class="panel-body">

                            <div class="col-md-2"></div>

                            <div class="col-md-8">

                                <div class="panel panel-warning hidden" style="padding: 15px; margin-bottom: 20px">
                                    <h4>Oh snap!</h4>
                                    <p>The form seems to be invalid in some place :(</p>
                                </div>

                                <div class="panel panel-info hidden" style="padding: 15px; margin-bottom: 20px">
                                    <h4>Nice!</h4>
                                    <p>Everything seems to be ok!</p>
                                </div>

                                <form:form modelAttribute="userTo" class="form-horizontal registration_form" method="post"
                                           action="registerUser"
                                           charset="utf-8" accept-charset="UTF-8">

                                    <div class="form-group">
                                        <div class="input-group">
                                            <input id="manager_name" type="text" class="form-control"
                                                   name="name"
                                                   data-parsley-pattern="^[a-zA-Z]+$"
                                                   data-parsley-required="true"
                                                   data-parsley-length="[3, 44]"
                                                   placeholder="Name" required>
                                            <span class="input-group-addon"><i
                                                    class="glyphicon glyphicon-user"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input id="manager_phone" type="text" class="form-control"
                                                   name="phone"
                                                   data-parsley-required="true"
                                                   data-parsley-pattern="^[0-9\-\+\s\(\)]*$"
                                                   data-parsley-length="[5, 20]"
                                                   value="" placeholder="Phone" required>
                                            <span class="input-group-addon"><i
                                                    class="glyphicon glyphicon-earphone"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input id="manager_email" type="email" class="form-control"
                                                   name="email"
                                                   data-parsley-required="true"
                                                   data-parsley-type="email"
                                                   value="" placeholder="Email" required>
                                            <span class="input-group-addon"><i
                                                    class="glyphicon glyphicon-envelope"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input id="manager_password" type="password" class="form-control"
                                                   name="password"
                                                   data-parsley-required="true"
                                                   data-parsley-length="[6, 18]"
                                                   placeholder="Password" required>
                                            <span class="input-group-addon"><i
                                                    class="glyphicon glyphicon-lock"></i></span>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-3"></div>
                                        <div class="col-md-6">
                                            <button type="submit"
                                                    class="btn btn-success btn-lg btn-primary">
                                                Register
                                            </button>
                                        </div>
                                        <div class="col-md-3"></div>
                                    </div>
                                </form:form>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>
        </c:if>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <sec:authorize access="!hasRole('ROLE_ADMIN')">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div class="panel panel-default text-center">
                        <div class="panel-heading">
                            <h2><spring:message code="app.profile"/> ${userTo.name}</h2>
                        </div>
                        <div class="panel-body">
                            <form:form modelAttribute="userTo" class="form-horizontal" method="post"
                                       action="profile'"
                                       charset="utf-8" accept-charset="UTF-8">

                                <spring:message code="user.name" var="userName"/>
                                <custom:inputField label='${userName}' name="name"/>

                                <spring:message code="user.email" var="userEmail"/>
                                <custom:inputField label='${userEmail}' name="email"/>

                                <spring:message code="users.phone" var="userPhone"/>
                                <custom:inputField label='${userPhone}' name="phone" inputType="text"/>

                                <spring:message code="users.password" var="userPassword"/>
                                <custom:inputField label='${userPassword}' name="password" inputType="password"/>

                                <div class="form-group">
                                    <div class="col-xs-offset-2 col-xs-10">
                                        <button type="submit" class="btn btn-primary">
                                            <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                        </button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>
        </sec:authorize>
    </sec:authorize>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('.registration_form').parsley().on('field:validated', function() {
            var ok = $('.parsley-error').length === 0;
            $('.panel-info').toggleClass('hidden', !ok);
            $('.panel-warning').toggleClass('hidden', ok);
        })
            .on('form:submit', function() {
                return true;
            });
    });
</script>

</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>
