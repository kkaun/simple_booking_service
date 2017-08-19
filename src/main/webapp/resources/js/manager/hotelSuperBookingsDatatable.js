/**
 * Created by Кира on 15.08.2017.
 */


var ajaxUrl = "/object/super_bookings/";
var datatableApi;

function updateManagerSBTableByDatesAdded() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "between_dates",
        data: $("#superBookingsManagerDatesAddedFilter").serialize(),
        success: updateTableByData
    });
}
function updateManagerSBTableByInDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_in_date",
        data: $("#superBookingsManagerInDateFilter").serialize(),
        success: updateTableByData
    });
}
function updateManagerSBTableByOutDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_out_date",
        data: $("#superBookingsManagerOutDateFilter").serialize(),
        success: updateTableByData
    });
}
function updateManagerSBTableByUserId() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_user_id",
        data: $("#superBookingsManagerUserIdFilter").serialize(),
        success: updateTableByData
    });
}

function clearSBDatesAddedManagerFilter() {
    $("#superBookingsManagerDatesAddedFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBInDateManagerFilter() {
    $("#superBookingsManagerInDateFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBOutDateManagerFilter() {
    $("#superBookingsManagerOutDateFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearSBUserIdManagerFilter() {
    $("#superBookingsManagerUserIdFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}


$(function () {
    datatableApi = $('#hotelSuperBookingsDatatable').DataTable(extendsOpts({
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
                "data": "userName"
            },
            {
                "data": "userEmail"
            },
            {
                "data": "userPhone"
            },
            {
                "render": renderHotelSuperBookingsEditBtn,
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