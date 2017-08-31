/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/bookings/";
var datatableApi;

function updateBookingsTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#bookingsDatatable').DataTable(extendsOpts({
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