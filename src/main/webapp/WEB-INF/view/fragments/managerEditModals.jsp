<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body>

<div class="modal fade" id="apartmentEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="apartmentModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="detailsForm">

                <input type="hidden" id="id" name="id">

                <div class="form-group">
                    <label for="aptPrice" class="control-label col-xs-3"><spring:message
                            code="apartments.price"/></label>
                    <div class="col-xs-9">
                        <input class="form-control" id="aptPrice" name="price"
                               placeholder="<spring:message code="apartments.price"/>" readonly>
                    </div>
                </div>

                <div class="form-group">
                    <label for="apartmentPersonNum" class="control-label col-xs-3"><spring:message
                            code="apt_types.personNum"/></label>
                    <div class="col-xs-9">
                        <input class="form-control" id="apartmentPersonNum" name="personNum"
                               placeholder="<spring:message code="apt_types.personNum"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="apartmentCategory" class="control-label col-xs-3"><spring:message
                            code="apt_types.category"/></label>
                    <div class="col-xs-9">
                        <input class="form-control" id="apartmentCategory" name="category"
                               placeholder="<spring:message code="apt_types.category"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="apartmentBedsArrangement" class="control-label col-xs-3"><spring:message
                            code="apt_types.bedsArrangement"/></label>
                    <div class="col-xs-9">
                        <input class="form-control" id="apartmentBedsArrangement" name="bedsArrangement"
                               placeholder="<spring:message code="apt_types.bedsArrangement"/>">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <button class="btn btn-primary" type="button" onclick="save()">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                    </button>
                </div>
            </div>
            </form:form>
        </div>
    </div>
</div>


<div class="modal fade" id="hotelVotesEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="hotelVotesModalTitle"></h2>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label for="voteDateAdded" class="control-label col-xs-3"><spring:message
                            code="common.dateAdded"/></label>
                    <div class="col-xs-9">
                        <input class="form-control" id="voteDateAdded" name="dateAdded"
                               placeholder="<spring:message code="common.dateAdded"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="voteRate" class="control-label col-xs-3"><spring:message
                            code="common.rate"/></label>
                    <div class="col-xs-9">
                        <input class="form-control" id="voteRate" name="rate"
                               placeholder="<spring:message code="common.rate"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="voteUserComment" class="control-label col-xs-3"><spring:message
                            code="common.comment"/></label>
                    <div class="col-xs-9">
                        <input class="form-control" id="voteUserComment" name="userComment"
                               placeholder="<spring:message code="common.comment"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="voteUName" class="control-label col-xs-3"><spring:message
                            code="common.bookerName"/></label>
                    <div class="col-xs-9">
                        <input class="form-control" id="voteUName" name="user.name"
                               placeholder="<spring:message code="common.bookerName"/>">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>