/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/user/bookings/";
var datatableApi;


function updateUserBookingsTable() {
    $.get(ajaxUrl, updateUserTableByData);
}


function deactivateUserBooking(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + "deactivate" + "?id=" + id + "&active=" + !enabled,
        type: 'POST',
        success: function () {
            chkbox.closest('checkbox').attr('checked', false);
            successNoty(enabled ? 'common.booking_activated' : 'common.booking_deactivated');
            updateUserBookingsTable();
        },
        error: function () {
            $(chkbox).prop("checked", enabled);
        }
    });
}

$(function () {
    datatableApi = $('#userBookingsDatatable').DataTable(extendsUserOpts({
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
                "data": "active",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        if (data) {
                            return '<input type="checkbox" ' + (data ? 'checked' : '') +
                                ' onclick="deactivateUserBooking($(this),' + row.id + ');"/>';
                        } else {
                            return '<input type="checkbox" ' + ' disabled readonly' + '/>';

                        }
                    }
                    return data;
                }
            },
            {
                "data": "inDate"
            },
            {
                "data": "outDate"
            },
            {
                "data": "hotelName"
            },
            {
                "data": "apartmentsNum"
            },
            {
                "data": "overallSum"
            },
            {
                "data": "hotelId"
            },
            {
                "data": "dateAdded"
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