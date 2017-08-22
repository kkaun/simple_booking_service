<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restHeadTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/regionsDatatable.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
        </div>

        <div class="col-md-10">

            <h3><spring:message code="cities.title"/></h3>

            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="citiesAdminFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="region"><spring:message
                                            code="city.byRegion"/>:</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" name="region" id="region">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-danger" type="button" onclick="clearPlacesFilter()">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updatePlacesTable()">
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
                            <h4 class="pull-left"><strong>Regions/Places Data:</strong></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                            style="margin: 0 auto;" onclick="addRegion()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped display" id="placesDatatable">
                            <thead>
                            <tr>
                                <th><spring:message code="common.id"/></th>
                                <th><spring:message code="common.name"/></th>
                                <th><spring:message code="common.country"/></th>
                                <th><spring:message code="common.description"/></th>
                                <th><spring:message code="regions.hotelNum"/></th>
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

<div class="modal fade" id="regionEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                </button>
                <h2 class="modal-title" id="regionModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="regionName" class="control-label col-xs-3"><spring:message
                                code="common.placeName"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="regionName" name="name"
                                   placeholder="<spring:message code="common.placeName"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="countryName" class="control-label col-xs-3"><spring:message
                                code="common.countryName"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="countryName" name="countryName"
                                   placeholder="<spring:message code="common.countryName"/>" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="regionDescription" class="control-label col-xs-3"><spring:message
                                code="common.description"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="regionDescription" name="description"
                                   placeholder="<spring:message code="common.description"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveRegion()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="regionCreateRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                </button>
                <h2 class="modal-title" id="regionCreateModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="createdRegionName" class="control-label col-xs-3"><spring:message
                                code="common.placeName"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="createdRegionName" name="name"
                                   placeholder="<spring:message code="common.placeName"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="countryName" class="control-label col-xs-3"><spring:message
                                code="common.countryName"/></label>
                        <c:if test="${not empty countries}">
                            <div class="col-xs-9">
                                <select class="form-control" name="countryName" id="createdRegionCountryName">
                                    <c:forEach items="${countries}" var="country">
                                        <option value="${country.name}">${country.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="regionDescription" class="control-label col-xs-3"><spring:message
                                code="common.description"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="createdRegionDescription" name="description"
                                   placeholder="<spring:message code="common.description"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveRegion()">
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

