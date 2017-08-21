/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/admin/super_bookings/";
var datatableApi;


function updateAdminSBTableByDatesAdded() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "between_dates",
        data: $("#superBookingsAdminDatesAddedFilter").serialize(),
        success: updateTableByData
    });
}
function updateAdminSBTableByInDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_in_date",
        data: $("#superBookingsAdminInDateFilter").serialize(),
        success: updateTableByData
    });
}
function updateAdminSBTableByOutDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_out_date",
        data: $("#superBookingsAdminOutDateFilter").serialize(),
        success: updateTableByData
    });
}
function updateAdminSBTableByUserId() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_user_id",
        data: $("#superBookingsAdminUserIdFilter").serialize(),
        success: updateTableByData
    });
}
function updateAdminSBTableByHotelId() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_hotel_id",
        data: $("#superBookingsAdminHotelIdFilter").serialize(),
        success: updateTableByData
    });
}

function clearSBDatesAddedAdminFilter() {
    $("#superBookingsAdminDatesAddedFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBInDateAdminFilter() {
    $("#superBookingsAdminInDateFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBOutDateAdminFilter() {
    $("#superBookingsAdminOutDateFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBUserIdAdminFilter() {
    $("#superBookingsAdminUserIdFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBHotelIdAdminFilter() {
    $("#superBookingsAdminHotelIdFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}



$(function () {
    datatableApi = $('#superBookingsDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "dateAdded"
            },
            {
                "data": "inDate"
            },
            {
                "data": "outDate"
            },
            {
                "data": "hotelId"
            },
            {
                "data": "hotelName"
            },
            {
                "data": "userId"
            },
            {
                "render": renderSuperBookingEditBtn,
                "defaultContent": "",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.active) {
                $(row).addClass("disabled");
            }
        }
    }));
});




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

        $.ajax({
            type: "POST",
            url: ajaxUrl,
            data: form.serialize(),
            success: function () {
                $('#superBookingEditRow').modal('hide');
                    updateAdminSBTableByDatesAdded();
                    updateAdminSBTableByInDate();
                    updateAdminSBTableByOutDate();
                    updateAdminSBTableByUserId();
                    updateAdminSBTableByHotelId();

                successNoty('common.saved');
            }
        });
}



/* ----------------------------------------------- Booking DT functions --------------------------------------------- */

//
// function updateBookingRow(id) {
//     $('#bookingModalTitle').html(i18n["editTitle"]);
//     $.get(ajaxUrl + id, function (data) {
//         $.each(data, function (key, value) {
//             form.find("input[name='" + key + "']").val(value);
//         });
//         $('#bookingEditRow').modal();
//     });
// }
//
// function renderBookingEditBtn(data, type, row) {
//     if (type === 'display') {
//         return '<a onclick="updateBookingRow(' + row.id + ');">' +
//             '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
//     }
// }
//
// function saveBooking() {
//     $.ajax({
//         type: "POST",
//         url: ajaxUrl + getSbId(),
//         data: form.serialize(),
//         success: function () {
//             $('#bookingEditRow').modal('hide');
//             updateBookingsTable();
//             successNoty('common.saved');
//         }
//     });
// }
//
