<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head_tags/restAdminHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container" style="margin-top: 20px;">
    <div class="row">

        <div class="col-md-3">
            <jsp:include page="fragments/sidebars/adminSidebar.jsp"/>
        </div>

        <div class="col-md-9">
            <jsp:include page="fragments/filters/adminFilters.jsp"/>

            <a id="adminAptTypeAddBtn" class="btn btn-primary" onclick="addAptType()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"> Add Apartment Type </span>
                <spring:message code="common.add"/>
            </a>

            <a id="adminHotelAddBtn" class="btn btn-primary" onclick="addHotel()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"> Add hotel </span>
                <spring:message code="common.add"/>
            </a>

            <a id="adminRegionAddBtn" class="btn btn-primary" onclick="addRegion()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"> Add Region </span>
                <spring:message code="common.add"/>
            </a>

            <a id="adminUserAddBtn" class="btn btn-primary" onclick="addUser()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"> Add User </span>
                <spring:message code="common.add"/>
            </a>

            <table class="table table-striped display" id="aptTypesDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="apt_types.personNum"/></th>
                    <th><spring:message code="apt_types.category"/></th>
                    <th><spring:message code="apt_types.bedsArrangement"/></th>
                    <th><spring:message code="apt_types.hotelsUsing"/></th>
                    <th><spring:message code="apt_types.apartmentsAppliedTo"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <table class="table table-striped display" id="hotelsDatatable">
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

            <table class="table table-striped display" id="regionsDatatable">
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

            <table class="table table-striped display" id="superBookingsDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="common.dateAdded"/></th>
                    <th><spring:message code="common.inDate"/></th>
                    <th><spring:message code="common.outDate"/></th>
                    <th><spring:message code="super_bookings.hotelId"/></th>
                    <th><spring:message code="super_bookings.hotelName"/></th>
                    <th><spring:message code="super_bookings.userId"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <table class="table table-striped display" id="usersDatatable">
                <thead>
                <tr>
                    <th><spring:message code="user.name"/></th>
                    <th><spring:message code="user.email"/></th>
                    <th><spring:message code="users.phone"/></th>
                    <th><spring:message code="users.roles"/></th>
                    <th><spring:message code="user.active"/></th>
                    <th><spring:message code="users.registered"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>

            <table class="table table-striped display" id="votesDatatable">
                <thead>
                <tr>
                    <th><spring:message code="common.id"/></th>
                    <th><spring:message code="common.dateAdded"/></th>
                    <th><spring:message code="common.rate"/></th>
                    <th><spring:message code="common.comment"/></th>
                    <th><spring:message code="common.bookerName"/></th>
                    <th><spring:message code="common.userId"/></th>
                    <th><spring:message code="common.hotelId"/></th>
                    <th><spring:message code="common.hotelName"/></th>
                </tr>
                </thead>
            </table>

        </div>
    </div>
</div>


<jsp:include page="fragments/superBookingEditModal.jsp"/>


<div class="modal fade" id="aptTypeEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="aptTypeModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="personNum" class="control-label col-xs-3"><spring:message
                                code="apt_types.personNum"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="personNum" name="personNum"
                                   placeholder="<spring:message code="apt_types.personNum"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="category" class="control-label col-xs-3"><spring:message
                                code="apt_types.category"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="category" name="category"
                                   placeholder="<spring:message code="apt_types.category"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bedsArrangement" class="control-label col-xs-3"><spring:message
                                code="apt_types.bedsArrangement"/></label>
                        <div class="col-xs-9">
                            <input class="form-control" id="bedsArrangement" name="bedsArrangement"
                                   placeholder="<spring:message code="apt_types.bedsArrangement"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveAptType()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
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
                        <label for="name" class="control-label col-xs-3"><spring:message
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
                        <label for="name" class="control-label col-xs-3"><spring:message
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

<div class="modal fade" id="userEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h2 class="modal-title" id="userModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal detailsForm">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="user.name"/></label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="user.name"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message code="user.email"/></label>
                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="<spring:message code="user.email"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="control-label col-xs-3"><spring:message code="users.phone"/></label>
                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="phone" name="phone" placeholder="<spring:message code="user.email"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><spring:message code="users.password"/></label>
                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code="users.password"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" onclick="saveUser()" class="btn btn-primary">
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

