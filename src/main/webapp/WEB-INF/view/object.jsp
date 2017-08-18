<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/restHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <sec:authorize access="hasRole('ROLE_MANAGER')">

            <div class="col-md-3">
                <jsp:include page="fragments/managerSidebar.jsp"/>
            </div>

            <div class="col-md-9">
                <div class="panel panel-default">
                    <div class="col-md-6 pull-left">
                        <div class="panel panel-default">
                            <form:form modelAttribute="hotelTo" class="form-horizontal" method="post"
                                       action="${new_object ? 'create_object' : 'update_object'}"
                                       charset="utf-8" accept-charset="UTF-8">>
                                <input type="hidden" id="id" name="id">
                                <div class="form-group">
                                    <label for="hotelName" class="control-label col-xs-3"><spring:message
                                            code="common.hotelName"/></label>
                                    <div class="col-xs-9">
                                        <input class="form-control" id="hotelName" name="name"
                                               placeholder="<spring:message code="common.hotelName"/>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="stars" class="control-label col-xs-3"><spring:message
                                            code="common.hotelStars"/></label>
                                    <div class="col-xs-9">
                                        <input class="form-control" id="stars" min="1" max="5" name="stars"
                                               placeholder="<spring:message code="common.hotelStars"/>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="hotelDescription" class="control-label col-xs-3"><spring:message
                                            code="hotels.description"/></label>
                                    <div class="col-xs-9">
                                        <input class="form-control" id="hotelDescription" name="description"
                                               placeholder="<spring:message code="hotels.description"/>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="checkIn" class="control-label col-xs-3"><spring:message
                                            code="hotels.checkIn"/></label>
                                    <div class="col-xs-9">
                                        <input class="form-control in_time" id="checkIn" name="checkIn"
                                               placeholder="<spring:message code="hotels.checkIn"/>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="checkOut" class="control-label col-xs-3"><spring:message
                                            code="hotels.checkOut"/></label>
                                    <div class="col-xs-9">
                                        <input class="form-control out_time" id="checkOut" name="checkOut"
                                               placeholder="<spring:message code="hotels.checkOut"/>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="address" class="control-label col-xs-3"><spring:message
                                            code="hotels.address"/></label>
                                    <div class="col-xs-9">
                                        <input class="form-control" id="address" name="address"
                                               placeholder="<spring:message code="hotels.address"/>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="hotelPhone" class="control-label col-xs-3"><spring:message
                                            code="hotels.phone"/></label>

                                    <div class="col-xs-9">
                                        <input class="form-control" id="hotelPhone" name="phone"
                                               placeholder="<spring:message code="hotels.phone"/>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-offset-3 col-xs-9">
                                        <button class="btn btn-primary" type="submit">
                                            <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                        </button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>

                </div>
            </div>

        </sec:authorize>

    </div>
</div>

</body>
</html>
<jsp:include page="fragments/footer.jsp"/>
