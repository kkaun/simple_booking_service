/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/admin/bookings/";
var datatableApi;


function updateAdminSBTableByDatesAdded() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "between_dates",
        data: $("#bookingsAdminDatesAddedFilter").serialize(),
        success: updateTableByData
    });
}
function updateAdminSBTableByInDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_in_date",
        data: $("#bookingsAdminInDateFilter").serialize(),
        success: updateTableByData
    });
}
function updateAdminSBTableByOutDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_out_date",
        data: $("#bookingsAdminOutDateFilter").serialize(),
        success: updateTableByData
    });
}
function updateAdminSBTableByUserId() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_user_id",
        data: $("#bookingsAdminUserIdFilter").serialize(),
        success: updateTableByData
    });
}
function updateAdminSBTableByHotelId() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_hotel_id",
        data: $("#bookingsAdminHotelIdFilter").serialize(),
        success: updateTableByData
    });
}


function clearSBDatesAddedAdminFilter() {
    $("#bookingsAdminDatesAddedFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBInDateAdminFilter() {
    $("#bookingsAdminInDateFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBOutDateAdminFilter() {
    $("#bookingsAdminOutDateFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBUserIdAdminFilter() {
    $("#bookingsAdminUserIdFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBHotelIdAdminFilter() {
    $("#bookingsAdminHotelIdFilter")[0].reset();

}


function deactivateAdminSB(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: 'active=' + enabled,
        success: function () {
            chkbox.closest('tr').toggleClass('disabled');
            successNoty(enabled ? 'common.booking_activated' : 'common.booking_deactivated');
            delayedUpdate();
        },
        error: function () {
            $(chkbox).prop("checked", !enabled);
        }
    });
}


$(function () {
    datatableApi = $('#bookingsDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "id",
                "render": function(data, type, row, meta){
                    if(type === 'display'){
                        return '<a class="btn btn-default" href="/edit_booking?id=' + data + '">'
                            + '<span class="glyphicon glyphicon-share" aria-hidden="true"></span>' +
                            '&nbsp;&nbsp;&nbsp;' + data + '</a>';
                    }
                    return data;
                }
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
                "data": "active",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        if (data) {
                            return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="deactivateAdminSB($(this),' + row.id + ');"/>';
                        } else {
                            return '<input type="checkbox" ' + ' disabled readonly' + '/>';

                        }
                    }
                    return data;
                }
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "pageLength": 50,
        "createdRow": function (row, data, dataIndex) {
            if (!data.active) {
                $(row).addClass("disabled");
            }
        }
    }));

});
