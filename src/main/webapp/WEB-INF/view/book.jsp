<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container">
    <c:if test="${not empty placement}">

        <div class="row" style="text-align: center">
            <c:forEach items="${placement.option}" var="apartment">
                <div class="col-md-8">
                    <div class="well">
                        ${apartment.key.personNum}-person
                        ${apartment.key.category} with ${apartment.key.bedsArrangement}
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="well">
                        <table class="table-responsive">
                            <tr>
                                <td>x<strong>${fn:length(apartment.value.size)}</strong></td>
                            </tr>
                            <tr>
                                <td>RUB <strong>${apartment.value[0].price}</strong> / night</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="row">
            <div class="well">
                <div class="col-md-6">
                    <h4>Booker Form</h4>
                    <form class="form-horizontal" method="post" action="check_booking">
                        <fieldset>
                            <input type="hidden" name="bookingHotelId" value="${hotel.id}">
                            <input type="hidden" name="bookingPlacementId" value="${placement.id}">
                            <input type="hidden" name="bookingSum" value="${placementSum}">
                            <input type="hidden" name="bookingPersonNum" value="${placementPersonNum}">
                            <input type="hidden" name="bookingApartmentNum" value="${placementApartmentNum}">
                            <input type="hidden" name="bookingInDate" value="${placementInDate}">
                            <input type="hidden" name="bookingOutDate" value="${placementOutDate}">



                            <button type="submit" class="btn btn-default btn-lg btn-primary"> Book Now</button>
                        </fieldset>
                    </form>

                </div>
            </div>

            <div class="col-md-6">
                <div class="well">
                    <form class="form-horizontal">
                        <fieldset>
                            <legend>Check Your Booking:</legend>


                        </fieldset>
                    </form>

                    <table class="table table-responsive">
                        <caption>Summary</caption>
                        <tr>
                            <td>
                                Hotel:
                            </td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                Apt. category:
                            </td>
                        </tr>
                        <tr>
                            <td>

                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

    </c:if>
</div>

</body>
</html>

<jsp:include page="fragments/footer.jsp"/>
