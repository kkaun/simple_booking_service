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
                "render": renderExpandBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "data": "dateAdded"
            },
            {
                "data": "rate"
            },
            {
                "data": "hotelId"
            },
            {
                "data": "hotelName"
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