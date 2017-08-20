/**
 * Created by Кира on 19.08.2017.
 */



/* ----------------------------------------------- Other Views Display functions ------------------------------------ */


function viewBookings() {

}


/* -----------------------------------------------  SuperBooking DT functions --------------------------------------- */

var superBookingId;

function updateSuperBookingRow(id) {
    superBookingId = id;
    $('#superBookingModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#superBookingEditRow').modal();
    });
}

function getSbId() {
    return superBookingId;
}

function renderSuperBookingEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateSuperBookingRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function saveSuperBooking() {
    if ($('#superBookingsDatatable').length > 0
        || $('#hotelSuperBookingsDatatable').length > 0
        || $('#userSuperBookingsDatatable').length > 0) {

        $.ajax({
            type: "POST",
            url: ajaxUrl,
            data: form.serialize(),
            success: function () {
                $('#superBookingEditRow').modal('hide');

                if ($('#superBookingsDatatable').length > 0) {
                    updateAdminSBTableByDatesAdded();
                    updateAdminSBTableByInDate();
                    updateAdminSBTableByOutDate();
                    updateAdminSBTableByUserId();
                    updateAdminSBTableByHotelId();
                }

                if ($('#hotelSuperBookingsDatatable').length > 0) {
                    updateManagerSBTableByDatesAdded();
                    updateManagerSBTableByInDate();
                    updateManagerSBTableByOutDate();
                    updateManagerSBTableByUserId();
                }

                if ($('#userSuperBookingsDatatable').length > 0) {
                    updateUserSuperBookingsTable();
                }

                successNoty('common.saved');
            }
        });
    }
}



/* ----------------------------------------------- Booking DT functions --------------------------------------------- */


function updateBookingRow(id) {
    $('#bookingModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#bookingEditRow').modal();
    });
}

function renderBookingEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateBookingRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function saveBooking() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + getSbId(),
        data: form.serialize(),
        success: function () {
            $('#bookingEditRow').modal('hide');
            updateBookingsTable();
            successNoty('common.saved');
        }
    });
}

