<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajaxUtil.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/hotelsDatatable.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-responsive/2.1.1/js/dataTables.responsive.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px; min-height: 580px">
    <div class="row">

        <div class="col-md-2">
            <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
        </div>

        <div class="col-md-10">

            <div class="row">
                <div class="col-sm-7">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="hotelsByRatingAdminFilter">
                                <div class="form-group">
                                    <label class="control-label col-sm-3" for="minRating">
                                        <spring:message code="common.filter_title"/> <spring:message code="hotels.minRating"/>:</label>
                                    <div class="col-sm-3">
                                        <input class="form-control" name="minRating" id="minRating">
                                    </div>
                                    <label class="control-label col-sm-3" for="maxRating">
                                        <spring:message code="common.filter_title"/> <spring:message code="hotels.maxRating"/>:</label>

                                    <div class="col-sm-3">
                                        <input class="form-control" name="maxRating" id="maxRating">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearHotelsByRatingFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateHotelsTableByRating()">
                                <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-5">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal filter" id="hotelsByCityAdminFilter">
                                <div class="form-group">
                                    <label for="regionName" class="control-label col-xs-5">
                                        <spring:message code="common.filter_title"/> <spring:message code="common.filter_by_location"/></label>
                                    <div class="col-xs-7">
                                        <input class="form-control" type="text" id="regionName" name="regionName">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel-footer text-right">
                            <a class="btn btn-default" type="button" onclick="clearHotelsByCityFilter()">
                                <span><i class="fa fa-eraser" aria-hidden="true"></i></span>
                            </a>
                            <a class="btn btn-primary" type="button" onclick="updateHotelsTableByCity()">
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
                            <h4 class="pull-left"><strong><spring:message code="common.hotels_list"/></strong></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <button id="adminUserAddBtn" class="btn btn-lg btn-primary pull-right"
                            style="margin: 0 auto;" onclick="addHotel()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table responsive table-striped table-bordered display" id="hotelsDatatable">
                            <thead>
                            <tr>
                                <th><spring:message code="common.id"/></th>
                                <th><spring:message code="common.name"/></th>
                                <th><spring:message code="hotels.rating"/></th>
                                <th><spring:message code="hotels.stars"/></th>
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
                <h2 class="modal-title" id="hotelModalTitle"><spring:message code="common.edit_object"/> </h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="cityId" name="cityId">

                    <div class="form-group managerIdForm">
                        <label for="managerId" class="control-label col-xs-3">
                            <spring:message code="app.manager"/> <spring:message code="common.id"/>
                            <spring:message code="common.def_managerId"/></label>
                        <div class="col-xs-5">
                            <input class="form-control" type="text" id="managerId" name="managerId" readonly>
                        </div>
                    </div>

                    <div class="form-group countryNameForm">
                        <label for="countryName" class="control-label col-xs-3">
                            <spring:message code="common.countryName"/>
                        </label>
                        <div class="col-xs-8">
                            <input class="form-control" type="text" id="countryName" name="countryName">
                        </div>
                    </div>

                    <div class="form-group cityNameForm">
                        <label for="cityName" class="control-label col-xs-3">
                            <spring:message code="common.placeName"/></label>
                        <div class="col-xs-8">
                            <input class="form-control" type="text" id="cityName" name="cityName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="hotelName" class="control-label col-xs-3"><spring:message
                                code="common.hotelName"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="hotelName" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="stars" class="control-label col-xs-3"><spring:message
                                code="common.hotelStars"/></label>
                        <div class="col-xs-2">
                            <input class="form-control" id="stars" type="text" name="stars">
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
                            <input class="form-control" type="text" id="address" name="address">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="hotelPhone" class="control-label col-xs-3"><spring:message
                                code="hotels.phone"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="hotelPhone" name="phone">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <button class="btn btn-primary pull-right" type="button" onclick="saveHotel()">
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

