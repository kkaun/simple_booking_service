<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/citiesDataLoad.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/datatablesUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util/notifications.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_manager/managerObjectsAjaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dt_manager/managerObjectsDatatable.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-responsive/2.1.1/js/dataTables.responsive.js" defer></script>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-12">

            <div class="row" style="height: 70px">
                <div class="col-md-11">
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding: 10px;">
                            <h4 class="pull-left"><strong><spring:message code="common.my_objects"/> </strong></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-1">
                    <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                            style="margin: 0 auto;" onclick="addManagerHotel()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table responsive table-striped table-bordered hover display" id="managerHotelsDatatable">
                            <thead>
                            <tr>
                                <th></th>
                                <th><spring:message code="common.id"/><spring:message code="common.proceed"/></th>
                                <th><spring:message code="common.name"/></th>
                                <th><spring:message code="hotels.rating"/></th>
                                <th><spring:message code="hotels.votesNum"/></th>
                                <th><spring:message code="hotels.city"/></th>
                                <th><spring:message code="common.country"/></th>
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

<div class="modal fade" id="managerHotelEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="hotelModalTitle"><spring:message code="common.edit_object"/> </h2>
            </div>
            <div class="modal-body">
                <h4><span><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span>
                    <spring:message code="common.all_active_inputs_required"/></h4>
                <hr>
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="cityId" name="cityId">
                    <input type="hidden" id="managerId" name="managerId">

                    <div class="form-group countryNameForm">
                        <label for="countryNamesList" class="control-label col-xs-4">
                            <spring:message code="common.countryName"/>
                        </label>
                        <div class="col-xs-7">
                            <input class="form-control" name="countryName" id="countryNamesList"
                                   placeholder="<spring:message code="common.form_in_latin"/>">
                        </div>
                    </div>

                    <div class="form-group cityNameForm">
                        <label for="cityNamesList" class="control-label col-xs-4">
                            <spring:message code="common.placeName"/>
                        </label>
                        <div class="col-xs-7">
                            <input class="form-control" name="cityName" id="cityNamesList"
                                   placeholder="<spring:message code="common.form_in_latin"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="hotelName" class="control-label col-xs-4"><spring:message
                                code="common.hotelName"/></label>
                        <div class="col-xs-7">
                            <input class="form-control" type="text" id="hotelName" name="name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="hotelDescription" class="control-label col-xs-4"><spring:message
                                code="hotels.description"/></label>
                        <div class="col-xs-7">
                            <textarea class="form-control" id="hotelDescription" name="description">
                            </textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="address" class="control-label col-xs-4"><spring:message
                                code="hotels.address"/></label>
                        <div class="col-xs-7">
                            <input class="form-control" type="text" id="address" name="address">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="hotelPhone" class="control-label col-xs-4"><spring:message
                                code="hotels.phone"/></label>

                        <div class="col-xs-7">
                            <input class="form-control" type="text" id="hotelPhone" name="phone">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="checkIn" class="control-label col-xs-4"><spring:message
                                code="hotels.checkIn"/></label>
                        <div class="col-xs-3">
                            <input class="form-control in_time" id="checkIn" name="checkIn">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="checkOut" class="control-label col-xs-4"><spring:message
                                code="hotels.checkOut"/></label>
                        <div class="col-xs-3">
                            <input class="form-control out_time" id="checkOut" name="checkOut">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="stars" class="control-label col-xs-4"><spring:message
                                code="common.hotelStars"/><spring:message code="common.stars_req"/></label>
                        <div class="col-xs-2">
                            <input class="form-control" id="stars" type="text" name="stars">
                        </div>
                    </div>

                    <hr>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <button class="btn btn-primary pull-right" type="button" onclick="saveManagerHotel()">
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


<div class="modal fade" id="managerHotelImageEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="managerHotelImageModalTitle"><spring:message code="common.edit_object_image"/></h2>
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
                            <input name="image" id="image" type="file" /><br/>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <button class="btn btn-primary pull-right" type="button" onclick="saveManagerHotelImage()">
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