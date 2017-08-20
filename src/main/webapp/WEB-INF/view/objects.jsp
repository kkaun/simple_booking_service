<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restObjectsHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-12">

            <a id="adminAptTypeAddBtn" class="btn btn-primary" onclick="addManagerHotel()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"><spring:message code="common.add"/>
                <spring:message code="common.object"/></span>
            </a>

            <table class="table table-striped display" id="managerHotelsDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="common.name"/></th>
                    <th><spring:message code="hotels.rating"/></th>
                    <th><spring:message code="hotels.stars"/></th>
                    <th><spring:message code="common.description"/></th>
                    <th><spring:message code="hotels.votesNum"/></th>
                    <th><spring:message code="hotels.checkIn"/></th>
                    <th><spring:message code="hotels.checkOut"/></th>
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



</body>
<jsp:include page="fragments/i18nUtil.jsp"/>
</html>
<jsp:include page="fragments/footer.jsp"/>