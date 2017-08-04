<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container">
    <div class="row" style="text-align: center">




        <div class="well">
            <form class="form-horizontal">
                <fieldset>
                    <legend>Booking Form</legend>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="name">Name</label>
                        <div class="col-md-8">
                            <input id="name" name="userName" type="text"
                                   placeholder="Name" class="form-control input-md" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="phone">Email</label>
                        <div class="col-md-8">
                            <input id="email" name="userEmail" type="email" placeholder="Email" class="form-control input-md" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="phone">Phone</label>
                        <div class="col-md-8">
                            <input id="phone" name="userPhone" pattern="[0-9]{5,18}"
                                   type="text" placeholder="Phone" class="form-control input-md" required>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="col-md-5">
            @RequestParam ("hotelId") String hotelId,
            @RequestParam ("apartmentId") String apartmentId,
            @RequestParam ("inDate") String inDate,
            @RequestParam ("outDate") String outDate,
            @RequestParam ("personNum") String personNum,
            @RequestParam ("extraBeds") String extraBeds,
            <div class="well">
                <table class="table table-responsive">
                    <jsp:useBean id=""
                    <caption>Summary</caption>
                    <tr>
                        <td>
                            Hotel:
                        </td>
                        <td>
                            ${hotel.name}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Apt. category:
                        </td>
                    </tr>
                    <tr>
                        <td>
                            ${apa}
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
