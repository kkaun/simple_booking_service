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
                "data": "id",
                "render": function(data, type, row, meta){
                    if(type === 'display'){
                        return '<a href="/edit_super_booking?id=' + data + '">'
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
        "pageLength": 50,
        "createdRow": function (row, data, dataIndex) {
            if (!data.active) {
                $(row).addClass("disabled");
            }
        }
    }));

});
