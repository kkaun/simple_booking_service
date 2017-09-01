/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/user/votes/";
var datatableApi;

function updateUserVotesTable() {
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
                "render": renderUserVoteDeleteBtn,
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
        "pageLength": 20
    }));

});