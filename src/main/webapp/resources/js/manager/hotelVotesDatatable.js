/**
 * Created by Кира on 15.08.2017.
 */


var ajaxUrl = "/manager/hotel_votes/";
var datatableApi;

function updateHotelVotesTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#hotelVotesDatatable').DataTable(extendsOpts({
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
                "data": "user.name"
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