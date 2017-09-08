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
        success: updateAdminTableByData
    });
}
function updateAdminSBTableByInDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_in_date",
        data: $("#bookingsAdminInDateFilter").serialize(),
        success: updateAdminTableByData
    });
}
function updateAdminSBTableByOutDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_out_date",
        data: $("#bookingsAdminOutDateFilter").serialize(),
        success: updateAdminTableByData
    });
}


function clearSBDatesAddedAdminFilter() {
    $("#bookingsAdminDatesAddedFilter")[0].reset();
    $.get(ajaxUrl, updateAdminTableByData);
}
function clearSBInDateAdminFilter() {
    $("#bookingsAdminInDateFilter")[0].reset();
    $.get(ajaxUrl, updateAdminTableByData);
}
function clearSBOutDateAdminFilter() {
    $("#bookingsAdminOutDateFilter")[0].reset();
    $.get(ajaxUrl, updateAdminTableByData);
}


function deactivateAdminSB(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + "deactivate" + "?id=" + id + "&active=" + !enabled,
        type: 'POST',
        success: function () {
            chkbox.closest('checkbox').attr('checked', false);
            successNoty(enabled ? 'common.booking_activated' : 'common.booking_deactivated');
            delayedUpdate();
        },
        error: function () {
            $(chkbox).prop("checked", enabled);
        }
    });
}


$(function () {
    datatableApi = $('#bookingsDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "render": renderExpandBtn,
                "defaultContent": "",
                "orderable": false
            },
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
                            return '<input type="checkbox" ' + (data ? 'checked' : '') +
                                ' onclick="deactivateAdminSB($(this),' + row.id + ');"/>';
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
