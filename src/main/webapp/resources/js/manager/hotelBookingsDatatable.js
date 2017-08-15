/**
 * Created by Кира on 15.08.2017.
 */


var ajaxUrl = "/manager/bookings";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#datatable').DataTable(extendsOpts({
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
                "render": renderEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderDeleteBtn,
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