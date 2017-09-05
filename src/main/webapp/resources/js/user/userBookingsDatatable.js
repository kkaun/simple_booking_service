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
    datatableApi = $('#userBookingsDatatable').DataTable(extendsOpts({
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
                "data": "apartmentsNum"
            },
            {
                "data": "active",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        if (data) {
                            return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="deactivateUserBooking($(this),' + row.id + ');"/>';
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
        "pageLength": 20,
        "createdRow": function (row, data, dataIndex) {
            if (!data.active) {
                $(row).addClass("disabled");
            }
        }
    }));

});