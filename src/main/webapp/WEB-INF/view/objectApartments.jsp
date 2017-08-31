<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manager/apartmentsDatatable.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/managerSidebar.jsp"/>
        </div>

        <div class="col-md-10">

            <div class="row" style="height: 70px">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong><spring:message code="common.obj_apartments_list"/></strong></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                            style="margin: 0 auto;" onclick="addApartment()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered display" id="apartmentsDatatable">
                            <thead>
                            <tr>
                                <th><spring:message code="apt_types.personNum"/>
                                    , <spring:message code="apt_types.category"/>
                                    , <spring:message code="apt_types.bedsArrangement"/>
                                </th>
                                <th><spring:message code="common.pricePerNight"/></th>
                                <th></th>
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
                    <input type="hidden" id="hotelId" name="hotelId">

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
                                            ; <i class="fa fa-btc"> ${objectApartment.price}0<spring:message code="common.per_night"/>
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


<div class="modal fade" id="apartmentImageEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="apartmentImageModalTitle"></h2>
            </div>
            <div class="modal-body">
                <div class="col-md-1"></div>
                <div class="col-md-10 text-center">
                    <h4><spring:message code="common.image"/></h4>
                    <br>
                    <img src="imgPath" class="img-responsive imgTag" alt="This item has no available image yet">
                </div>
                <div class="col-md-1"></div>
                <form:form class="form-horizontal detailsForm" id="imgForm" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group text-center">
                        <label for="image" class="control-label col-md-6"><spring:message code="common.choose_image"/></label>
                        <div class="col-md-6">
                            <input name="image" id="image" type="file" /><br/>
                        </div>
                    </div>
                    <div class="form-group text-center">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveApartmentImage()">
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


