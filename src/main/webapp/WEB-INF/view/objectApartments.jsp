<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restObjectHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-3">
            <jsp:include page="fragments/sidebars/managerSidebar.jsp"/>
        </div>

        <div class="col-md-9">

            <div class="panel panel-default">
                <a id="managerApartmentAddBtn" class="btn btn-primary" onclick="addApartment()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"> Add Apartment</span>
                    <spring:message code="common.add"/>
                </a>
            </div>

            <table class="table table-striped display" id="apartmentsDatatable">
                <thead>
                <tr>
                    <th><spring:message code="apt_types.personNum"/>
                        , <spring:message code="apt_types.category"/>
                        , <spring:message code="apt_types.bedsArrangement"/>
                    </th>
                    <th><spring:message code="common.pricePerNight"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

        </div>
    </div>
</div>


<div class="modal fade" id="apartmentEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="apartmentModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="aptPrice" class="control-label col-xs-3"><spring:message
                                code="apartments.price"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" type="number" step="any" min="1" id="aptPrice" name="price"
                                   placeholder="<spring:message code="apartments.price"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-group">
                            <label for="apartmentType" class="control-label col-xs-3"><spring:message
                                    code="apt_types.personNum"/> - <spring:message
                                    code="apt_types.category"/> - <spring:message
                                    code="apt_types.bedsArrangement"/></label>
                            <div class="col-xs-9">
                                <select class="form-control" name="stringAptType" id="apartmentType">
                                    <c:if test="${not empty objectApartments}">
                                    <c:forEach items="${objectApartments}" var="objectApartment">
                                        <option value="${objectApartment.stringAptType}">${objectApartment.stringAptType}
                                            ; ${objectApartment.price}0 $ / night
                                        </option>
                                    </c:forEach>
                                </select>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveApartment()">
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


