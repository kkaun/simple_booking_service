<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restHeadTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/hotelsDatatable.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
        </div>

        <div class="col-md-10">
            
            <h3><spring:message code="hotels.title"/></h3>

            <div class="row">
                <div class="col-sm-6">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="hotelsByRatingAdminFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-3" for="minRating"><spring:message
                                            code="hotels.minRating"/>:</label>
                                    <div class="col-sm-3">
                                        <input class="form-control" name="minRating" id="minRating">
                                    </div>
                                    <label class="control-label col-sm-3" for="maxRating"><spring:message
                                            code="hotels.maxRating"/>:</label>

                                    <div class="col-sm-3">
                                        <input class="form-control" name="maxRating" id="maxRating">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-danger" type="button" onclick="clearHotelsByRatingFilter()">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateHotelsTableByRating()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="hotelsByCityAdminFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="cityName"><spring:message
                                            code="hotels.byCity"/>:</label>
                                    <c:if test="${not empty cities}">
                                        <div class="form-group">
                                            <div class="col-xs-7">
                                                <select class="form-control" name="cityName" id="cityName">
                                                    <c:forEach items="${cities}" var="city">
                                                        <option value="${city.name}">${city.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-danger" type="button" onclick="clearHotelsByCityFilter()">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateHotelsTableByCity()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" style="height: 70px">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong>Hotels Data:</strong></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                            style="margin: 0 auto;" onclick="addHotel()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped display" id="hotelsDatatable">
                            <thead>
                            <tr>
                                <th><spring:message code="common.id"/></th>
                                <th><spring:message code="common.name"/></th>
                                <th><spring:message code="hotels.rating"/></th>
                                <th><spring:message code="hotels.stars"/></th>
                                <th><spring:message code="common.description"/></th>
                                <th><spring:message code="hotels.votesNum"/></th>
                                <th><spring:message code="hotels.city"/></th>
                                <th><spring:message code="common.country"/></th>
                                <th></th>
                                <th></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<div class="modal fade" id="hotelEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="hotelModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
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
                            <input class="form-control" id="stars" type="number" name="stars"
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
                            <button class="btn btn-primary" type="button" onclick="saveHotel()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>

<jsp:include page="fragments/footer.jsp"/>

