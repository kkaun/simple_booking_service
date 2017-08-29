/**
 * Created by Кира on 15.08.2017.
 */


var ajaxUrl = "/hotel_manager/object/super_bookings/";
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


function deactivateManagerSB(chkbox, id) {
    var enabled = chkbox.is(":checked");
    if(enabled){
        $.ajax({
            url: ajaxUrl + id,
            type: 'POST',
            data: 'active=' + enabled,
            success: function () {
                chkbox.closest('tr').toggleClass('disabled');
                successNoty(enabled ? 'common.enabled' : 'common.disabled');
            },
            error: function () {
                $(chkbox).prop("checked", !enabled);
            }
        });
    }
}


$(function () {
    datatableApi = $('#hotelSuperBookingsDatatable').DataTable(extendsOpts({
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
                "data": "userName"
            },
            {
                "data": "userEmail"
            },
            {
                "data": "userPhone"
            },
            {
                "data": "active",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="deactivateManagerSB($(this),' + row.id + ');"/>';
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



