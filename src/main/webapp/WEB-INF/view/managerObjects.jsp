<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manager/managerHotelsDatatable.js" defer></script>
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
                        <table class="table table-striped table-bordered hover display" id="managerHotelsDatatable">
                            <thead>
                            <tr>
                                <th><spring:message code="common.id"/></th>
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
                <h2 class="modal-title" id="managerHotelModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="cityId" name="cityId">
                    <input type="hidden" id="managerId" name="managerId">

                    <div class="form-group countryNameForm">
                        <label for="countryName" class="control-label col-xs-3">
                            <spring:message code="common.countryName"/>
                        </label>
                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="countryName" name="countryName">
                        </div>
                    </div>

                    <div class="form-group cityNameForm">
                        <label for="cityName" class="control-label col-xs-3">
                            <spring:message code="common.placeName"/>
                        </label>
                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="cityName" name="cityName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="hotelName" class="control-label col-xs-3"><spring:message
                                code="common.hotelName"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="hotelName" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="stars" class="control-label col-xs-3"><spring:message
                                code="common.hotelStars"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="stars" min="1" max="5" name="stars">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="hotelDescription" class="control-label col-xs-3"><spring:message
                                code="hotels.description"/></label>
                        <div class="col-xs-9">
                            <textarea class="form-control" id="hotelDescription" name="description">
                            </textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="checkIn" class="control-label col-xs-3"><spring:message
                                code="hotels.checkIn"/></label>
                        <div class="col-xs-9">
                            <input class="form-control in_time" id="checkIn" name="checkIn">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="checkOut" class="control-label col-xs-3"><spring:message
                                code="hotels.checkOut"/></label>
                        <div class="col-xs-9">
                            <input class="form-control out_time" id="checkOut" name="checkOut">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="control-label col-xs-3"><spring:message
                                code="hotels.address"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="address" name="address">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="hotelPhone" class="control-label col-xs-3"><spring:message
                                code="hotels.phone"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="hotelPhone" name="phone">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveManagerHotel()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="managerHotelImageEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="managerHotelImageModalTitle"><spring:message code="common.edit_object"/></h2>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <h4><spring:message code="common.image"/></h4>
                        <br>
                        <img src="imgPath" class="img-responsive imgTag" alt="This item has no available image yet">
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
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveManagerHotelImage()">
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