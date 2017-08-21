/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/user/super_bookings/";
var datatableApi;

function updateUserSuperBookingsTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#userSuperBookingsDatatable').DataTable(extendsOpts({
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
                "data": "apartmentsNum"
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

            updateUserSuperBookingsTable();

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
