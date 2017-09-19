<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript">
    var objectId = '${objectId}';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/datatablesUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/notifications.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_manager/managerObjectChildrenAjaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_manager/apartmentsDatatable.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-responsive/2.1.1/js/dataTables.responsive.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/managerSidebar.jsp"/>
        </div>

        <div class="col-md-10">

            <div class="row" style="height: 70px">
                <div class="col-md-10">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong><spring:message code="common.obj_apartments_list"/> #${objectId}:</strong></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                            style="margin: 0 auto;" onclick="addApartment()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table responsive table-striped table-bordered display" id="apartmentsDatatable">
                            <thead>
                            <tr>
                                <th></th>
                                <th><spring:message code="apt_types.person_num_short"/>
                                    - <spring:message code="apt_types.category"/>
                                    - <spring:message code="apt_types.bedsArrangement"/>
                                </th>
                                <th><spring:message code="common.pricePerNight"/>, <i class="fa fa-btc"></i></th>
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
                <h2 class="modal-title" id="apartmentModalTitle"><spring:message code="common.edit_apartment"/></h2>
            </div>
            <div class="modal-body">
                <h4><span><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span>
                    <spring:message code="common.all_active_inputs_required"/></h4>
                <hr>
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="hotelId" name="hotelId">

                    <div class="form-group">
                        <label for="aptPrice" class="control-label col-xs-3"><spring:message
                                code="apartments.price"/>, <i class="fa fa-btc"></i></label>
                        <div class="col-xs-3">
                            <input class="form-control" type="text" id="aptPrice" name="price">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-group">
                            <label for="apartmentType" class="control-label col-xs-3"><spring:message
                                    code="apt_types.personNum"/> - <spring:message
                                    code="apt_types.category"/> - <spring:message
                                    code="apt_types.bedsArrangement"/></label>
                            <div class="col-xs-8">
                                <c:if test="${not empty aptTypes}">
                                    <select class="form-control" name="stringAptType" id="apartmentType">
                                        <c:forEach items="${aptTypes}" var="aptType">
                                            <option value="${aptType}">${aptType}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <button class="btn btn-primary pull-right" type="button" onclick="saveApartment()">
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


<div class="modal fade" id="apartmentImageEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="apartmentImageModalTitle"><spring:message code="common.edit_apartment_image"/></h2>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <h5><span><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span>
                            <spring:message code="common.image_upload_tip"/></h5>
                        <hr>
                        <h4><spring:message code="common.image"/></h4>
                        <hr>
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
                            <button class="btn btn-primary pull-right" type="button" onclick="saveApartmentImage()">
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


