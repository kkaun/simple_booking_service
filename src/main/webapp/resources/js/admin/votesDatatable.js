/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/admin/votes/";
var datatableApi;

function updateVotesTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#votesDatatable').DataTable(extendsOpts({
        "columns": [
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
                "data": "userComment"
            },
            {
                "data": "user.name"
            },
            {
                "data": "user.id"
            },
            {
                "data": "hotel.id"
            },
            {
                "data": "hotel.name"
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