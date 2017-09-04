/**
 * Created by Кира on 15.08.2017.
 */

var subBookingAjaxUrl = "/sub_bookings/";
var datatableApi;

function updateBookingsTable() {
    $.get(subBookingAjaxUrl + "getAll?bookingId=" + bookingId, updateSubBookingTableByData);
}

$(function () {
    datatableApi = $('#bookingsDatatable').DataTable(extendsSubBookingOpts({
        "columns": [
            {
                "data": "aptInDate"
            },
            {
                "data": "aptOutDate"
            },
            {
                "data": "sum"
            },
            {
                "data": "stringAptType"
            },
            {
                "data": "edited"
            },
            {
                "render": renderSubBookingEditBtn,
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
        "pageLength": 50
    }));
});