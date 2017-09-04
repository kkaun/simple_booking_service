/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/user/bookings/";
var datatableApi;

function updateUserBookingsTable() {
    $.get(ajaxUrl, updateTableByData);
}

function deactivateUserBooking(chkbox, id) {
    var enabled = chkbox.is(":checked");
    if(enabled) {
        $.ajax({
            url: ajaxUrl + id,
            type: 'POST',
            data: 'active=' + enabled,
            success: function () {
                chkbox.closest('tr').toggleClass('disabled');
                successNoty(enabled ? 'common.enabled' : 'common.disabled');
                updateUserBookingsTable();
            },
            error: function () {
                $(chkbox).prop("checked", !enabled);
            }
        });
    }
}

$(function () {
    datatableApi = $('#userBookingsDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "id",
                "render": function(data, type, row, meta){
                    if(type === 'display'){
                        return '<a href="/edit_booking?id=' + data + '">'
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
                "data": "apartmentsNum"
            },
            {
                "data": "active",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="deactivateUserBooking($(this),' + row.id + ');"/>';
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
        "pageLength": 20,
        "createdRow": function (row, data, dataIndex) {
            if (!data.active) {
                $(row).addClass("disabled");
            }
        }
    }));

});