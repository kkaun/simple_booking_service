/**
 * Created by Кира on 15.08.2017.
 */


var ajaxUrl = "/hotel_manager/object/bookings/";
var datatableApi;


function updateManagerBookingTableByDatesAdded() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "between_dates?hotelId=" + objectId,
        data: $("#bookingsManagerDatesAddedFilter").serialize(),
        success: updateObjectChildrenTableByData
    });
}
function updateManagerBookingTableByInDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_in_date?hotelId=" + objectId,
        data: $("#bookingsManagerInDateFilter").serialize(),
        success: updateObjectChildrenTableByData
    });
}
function updateManagerBookingTableByOutDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_out_date?hotelId=" + objectId,
        data: $("#bookingsManagerOutDateFilter").serialize(),
        success: updateObjectChildrenTableByData
    });
}
function updateManagerBookingTableByUserId() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_user_id?hotelId=" + objectId,
        data: $("#bookingsManagerUserIdFilter").serialize(),
        success: updateObjectChildrenTableByData
    });
}

function clearBookingDatesAddedManagerFilter() {
    $("#bookingsManagerDatesAddedFilter")[0].reset();
    $.get(ajaxUrl, updateObjectChildrenTableByData);
}
function clearBookingInDateManagerFilter() {
    $("#bookingsManagerInDateFilter")[0].reset();
    $.get(ajaxUrl, updateObjectChildrenTableByData);
}
function clearBookingOutDateManagerFilter() {
    $("#bookingsManagerOutDateFilter")[0].reset();
    $.get(ajaxUrl, updateObjectChildrenTableByData);
}
function clearBookingUserIdManagerFilter() {
    $("#bookingsManagerUserIdFilter")[0].reset();
    $.get(ajaxUrl, updateObjectChildrenTableByData);
}



$(function () {
    datatableApi = $('#hotelBookingsDatatable').DataTable(extendsObjectChildrenOpts({
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
                        if(data) {
                            return '<input type="checkbox" ' +
                                'checked disabled readonly title="Manager cannot activate/deactivate bookings of his own object"' + '/>';
                        } else {
                            return '<input type="checkbox" ' +
                                ' disabled readonly title="Manager cannot activate/deactivate bookings of his own object"' + '/>';

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



