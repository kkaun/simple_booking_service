/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/user/data";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#userVotesDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "dateAdded"
            },
            {
                "data": "rate"
            },
            {
                "data": "userComment"
            },
            {
                "data": "hotel.id"
            },
            {
                "data": "hotel.name"
            },
            {
                "render": renderUserVoteEditBtn,
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