/**
 * Created by Кира on 15.08.2017.
 */

var subBookingAjaxUrl = "/bookings/";
var datatableApi;

function updateBookingsTable() {
    $.get(subBookingAjaxUrl + "getAll?superBookingId=" + sbId, updateSubBookingTableByData);
}

$(function () {
    datatableApi = $('#bookingsDatatable').DataTable(extendsSubBookingOpts({
        "columns": [
            {
                "data": "id"
            },
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
                "render": renderBookingEditBtn,
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