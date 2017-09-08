/**
 * Created by Кира on 15.08.2017.
 */
var ajaxUrl = "/admin/apt_types/";
var datatableApi;

function updateAptTypesTable() {
    $.get(ajaxUrl, updateAdminTableByData);
}

$(function () {
    datatableApi = $('#aptTypesDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "render": renderExpandBtn,
                "defaultContent": "",
                "orderable": false
            },
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
                "render": renderAptTypeDeleteBtn,
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
