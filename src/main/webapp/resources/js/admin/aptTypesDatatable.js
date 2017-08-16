/**
 * Created by Кира on 15.08.2017.
 */
var ajaxUrl = "/admin/apt_types";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#aptTypesDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "personNum"
            },
            {
                "data": "category"
            },
            {
                "data": "bedsArrangement"
            },
            {
                "data": "hotelsUsing"
            },
            {
                "data": "apartmentsAppliedTo"
            },
            {
                "render": renderAptTypeEditBtn,
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