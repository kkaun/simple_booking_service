/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/manager/apartments";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#apartmentsDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "stringAptType"
            },
            {
                "data": "price"
            },
            {
                "render": renderApartmentEditBtn,
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