/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/admin/votes/";
var datatableApi;

function updateVotesTable() {
    $.get(ajaxUrl, updateAdminTableByData);
}

$(function () {
    datatableApi = $('#votesDatatable').DataTable(extendsOpts({
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
                "data": "dateAdded"
            },
            {
                "data": "rate"
            },
            {
                "data": "userId"
            },
            {
                "data": "userName"
            },
            {
                "data": "hotelId"
            },
            {
                "data": "hotelName"
            },
            {
                "data": "userComment"
            },
            {
                "render": renderDeleteVoteBtn,
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
