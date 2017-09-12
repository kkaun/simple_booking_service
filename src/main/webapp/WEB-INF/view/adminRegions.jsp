<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/datatablesUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/notifications.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_admin/adminAjaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_admin/regionsDatatable.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-responsive/2.1.1/js/dataTables.responsive.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
        </div>

        <div class="col-md-10">

            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default text-left">
                        <div class="panel-body text-left">
                            <form:form class="form-horizontal filter" id="placesAdminFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="region">
                                        <spring:message code="city.byRegion"/> <spring:message code="common.in_latin"/> :</label>
                                    <div class="col-sm-5">
                                        <input class="form-control" name="region" id="region">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearPlacesFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updatePlacesTable()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" style="height: 70px">
                <div class="col-md-10">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong><spring:message code="common.regions_list"/></strong></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                            style="margin: 0 auto;" onclick="addRegion()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table responsive table-striped table-bordered display" id="placesDatatable">
                            <thead>
                            <tr>
                                <th></th>
                                <th><spring:message code="common.id"/></th>
                                <th><spring:message code="common.name"/></th>
                                <th><spring:message code="common.country"/></th>
                                <th><spring:message code="common.description"/></th>
                                <th><spring:message code="regions.hotelNum"/></th>
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


<div class="modal fade" id="regionEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                </button>
                <h2 class="modal-title" id="regionModalTitle"><spring:message code="common.region_editing"/></h2>
            </div>
            <div class="modal-body">
                <h4><span><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span>
                    <spring:message code="common.all_active_inputs_required"/></h4>
                <hr>
                <h5><span><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span>
                    <spring:message code="common.latin_letters_req"/></h5>
                <hr>
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="regionName" class="control-label col-xs-3"><spring:message
                                code="common.placeName"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="regionName" name="name">
                        </div>
                    </div>
                    <div class="form-group currentCountryName">
                        <label for="currentCountryName" class="control-label col-xs-3"><spring:message
                                code="common.countryName"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="currentCountryName" name="countryName" readonly>
                        </div>
                    </div>

                    <div class="form-group countryNamesList">
                        <label for="countryNamesList" class="control-label col-xs-3"><spring:message
                                code="common.countryName"/></label>
                        <c:if test="${not empty countries}">
                            <div class="col-xs-9">
                                <select class="form-control" name="countryName" id="countryNamesList">
                                    <c:forEach items="${countries}" var="country">
                                        <option name="countryName" value="${country.name}">${country.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="regionDescription" class="control-label col-xs-3"><spring:message
                                code="common.description"/></label>
                        <div class="col-xs-9">
                            <textarea class="form-control" id="regionDescription" name="description">
                            </textarea>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <button class="btn btn-primary pull-right" type="button" onclick="saveRegion()">
                                <spring:message code="common.save"/>&nbsp;&nbsp;
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <div class="load-bar"></div>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="regionImageEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="regionImageModalTitle"><spring:message code="common.region_image_editing"/></h2>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <h4><spring:message code="common.image"/></h4>
                        <br>
                        <img src="" class="img-responsive" id="imgTag" alt="This item has no available image yet">
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <hr>
                <hr>
                <br>
                <form:form class="form-horizontal detailsForm" id="imgForm" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group text-center">
                        <label for="image" class="control-label col-md-6"><spring:message code="common.choose_image"/></label>
                        <div class="col-md-6">
                            <input name="image" id="image" type="file" style="margin-top: 6px"/><br/>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <button class="btn btn-primary pull-right" type="button" onclick="saveRegionImage()">
                                <spring:message code="common.save"/>&nbsp;&nbsp;
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <div class="load-bar"></div>
            </div>
        </div>
    </div>
</div>


</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>

<jsp:include page="fragments/footer.jsp"/>

