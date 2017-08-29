<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/head_tags/jspHeadTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <c:if test="${not empty placement}">

    <div class="row">
        <div class="col-md-12 text-center">
            <h2>Reservation page</h2>
            <br>
        </div>
    </div>

    <div class="well">
        <div class="row">
            <div class="col-md-6">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4>Fake Booker Info</h4>
                        </div>
                        <div class="panel-body">
                            <h5>Please fill in your own fake information needed for fake booking confirmation
                                and being informed about any (im)possible changes</h5>
                            <hr>
                            <div class="col-md-12">
                                <div class="col-md-1"></div>
                                <div class="col-md-10">

                                    <div class="panel panel-warning hidden" style="padding: 15px; margin-bottom: 20px">
                                        <h4>Oh snap!</h4>
                                        <p>The form seems to be invalid in some place :(</p>
                                    </div>

                                    <div class="panel panel-info hidden" style="padding: 15px; margin-bottom: 20px">
                                        <h4>Nice!</h4>
                                        <p>Everything seems to be ok!</p>
                                    </div>

                                    <sec:authorize access="isAnonymous()">
                                        <form:form class="form-horizontal" id="anon_booking_form" role="form" action="confirm_anonymous_booking"
                                                   method="post" data-parsley-validate="">
                                            <fieldset>
                                                <input type="hidden" name="bookingHotelId" value="${hotel.id}">
                                                <input type="hidden" name="bookingPlacementId" value="${placement.id}">
                                                <input type="hidden" name="bookingSum" value="${placementSum}">
                                                <input type="hidden" name="bookingPersonNum"
                                                       value="${placementPersonNum}">
                                                <input type="hidden" name="bookingApartmentNum"
                                                       value="${placementApartmentNum}">
                                                <input type="hidden" name="bookingInDate" value="${placementInDate}">
                                                <input type="hidden" name="bookingOutDate" value="${placementOutDate}">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <input id="user_name" type="text" class="form-control"
                                                               name="userName"
                                                               data-parsley-pattern="^[a-zA-Z]+$"
                                                               data-parsley-required="true"
                                                               data-parsley-length="[3, 44]"
                                                               placeholder="Name" required>
                                                        <span class="input-group-addon"><i
                                                                class="glyphicon glyphicon-user"></i></span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <input id="user_phone" type="text" class="form-control"
                                                               name="userPhone"
                                                               data-parsley-required="true"
                                                               data-parsley-pattern="^[0-9\-\+\s\(\)]*$"
                                                               data-parsley-length="[5, 20]"
                                                               value="" placeholder="Phone" required>
                                                        <span class="input-group-addon"><i
                                                                class="glyphicon glyphicon-earphone"></i></span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <input id="user_email" type="email" class="form-control"
                                                               name="userEmail"
                                                               data-parsley-required="true"
                                                               data-parsley-type="email"
                                                               value="" placeholder="Email" required>
                                                        <span class="input-group-addon"><i
                                                                class="glyphicon glyphicon-envelope"></i></span>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-md-3"></div>
                                                    <div class="col-md-6">
                                                        <button type="submit"
                                                                class="btn btn-success btn-lg btn-primary">
                                                            Confirm Booking
                                                        </button>
                                                    </div>
                                                    <div class="col-md-3"></div>
                                                </div>
                                            </fieldset>
                                        </form:form>
                                    </sec:authorize>

                                    <sec:authorize access="isAuthenticated()">
                                    <sec:authorize access="hasAuthority('ROLE_USER')">
                                        <form:form class="form-horizontal" role="form" action="confirm_registered_booking"
                                                   method="post">
                                            <fieldset>
                                                <input type="hidden" name="bookingHotelId" value="${hotel.id}">
                                                <input type="hidden" name="bookingPlacementId" value="${placement.id}">
                                                <input type="hidden" name="bookingSum" value="${placementSum}">
                                                <input type="hidden" name="bookingPersonNum"
                                                       value="${placementPersonNum}">
                                                <input type="hidden" name="bookingApartmentNum"
                                                       value="${placementApartmentNum}">
                                                <input type="hidden" name="bookingInDate" value="${placementInDate}">
                                                <input type="hidden" name="bookingOutDate" value="${placementOutDate}">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <input id="reg_user_name" type="text" class="form-control"
                                                               name="userName" value="${regUserName}" readonly>
                                                        <span class="input-group-addon"><i
                                                                class="glyphicon glyphicon-user"></i></span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <input id="reg_user_phone" type="text" class="form-control"
                                                               name="userPhone" value="${regUserPhone}" readonly>
                                                        <span class="input-group-addon"><i
                                                                class="glyphicon glyphicon-earphone"></i></span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <input id="reg_user_email" type="email" class="form-control"
                                                               name="userEmail" value="${regUserEmail}" readonly>
                                                        <span class="input-group-addon"><i
                                                                class="glyphicon glyphicon-envelope"></i></span>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-md-3"></div>
                                                    <div class="col-md-6">
                                                        <button type="submit"
                                                                class="btn btn-success btn-lg btn-primary">
                                                            Confirm Booking
                                                        </button>
                                                    </div>
                                                    <div class="col-md-3"></div>
                                                </div>
                                            </fieldset>
                                        </form:form>
                                    </sec:authorize>
                                    </sec:authorize>

                                </div>
                                <div class="col-md-1"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3>Summary</h3>
                        </div>
                        <div class="panel-body">
                            <h4>${hotel.name}</h4>
                            <br>
                            <table class="table table-responsive">
                                <tr>
                                    <td>No. of person</td>
                                    <td>${placementPersonNum}</td>
                                </tr>
                                <tr>
                                    <td>No. of rooms</td>
                                    <td>${placementApartmentNum}</td>
                                </tr>
                                <tr>
                                    <td>Check-in</td>
                                    <td>${placementInDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${hotel.checkIn}</td>
                                </tr>
                                <tr>
                                    <td>Check-out</td>
                                    <td>${placementOutDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${hotel.checkOut}</td>
                                </tr>
                                <tr>
                                    <td>Total sum</td>
                                    <td>${placementSum}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="row" style="text-align: center">
                    <c:if test="${not empty options}">
                        <c:forEach items="${options}" var="optionList">
                            <c:forEach items="${optionList}" var="option">
                                <div class="row>">
                                    <div class="row">
                                        <div class="col-md-12 text-center">
                                            <h4>Your apartments:</h4>
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="panel-group">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <div class="row">
                                                        <div class="media col-md-5">
                                                            <figure class="pull-left">
                                                                <img class="media-object img-rounded img-responsive"
                                                                     src="http://placehold.it/350x250"
                                                                     alt="placehold.it/350x250">
                                                            </figure>
                                                        </div>
                                                        <div class="col-md-7">
                                                            <h5>
                                                                <c:out value="${option.type.personNum}"/>-person
                                                                <c:out value="${option.type.category}"/> with <c:out
                                                                    value="${option.type.bedsArrangement}"/>
                                                            </h5>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="panel-group">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <table class="table-responsive" style="margin: 0 auto">
                                                        <tr>
                                                            <td class="text-center">x<strong><c:out
                                                                    value="${fn:length(optionList)}"/></strong>
                                                                <br>
                                                                <br></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="text-center"><i class="fa fa-btc"></i>
                                                                <strong><c:out value="${option.price}"/></strong> /
                                                                night
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
        </c:if>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#anon_booking_form').parsley().on('field:validated', function() {
            var ok = $('.parsley-error').length === 0;
            $('.panel-info').toggleClass('hidden', !ok);
            $('.panel-warning').toggleClass('hidden', ok);
        })
            .on('form:submit', function() {
                return true;
            });
    });
</script>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>
