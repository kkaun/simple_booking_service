/**
 * Created by Кира on 15.08.2017.
 */


var ajaxUrl = "/object/hotel_votes/";
var datatableApi;

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
                "render": renderHotelVotesViewBtn,
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