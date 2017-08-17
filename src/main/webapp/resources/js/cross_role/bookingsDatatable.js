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
                "data": "inDate"
            },
            {
                "data": "outDate"
            },
            {
                "data": "sum"
            },
            {
                "data": "aptPersonNum"
            },
            {
                "data": "aptCategory"
            },
            {
                "data": "aptArrangement"
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
        ]
    }));
});