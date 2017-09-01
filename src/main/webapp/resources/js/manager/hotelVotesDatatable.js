/**
 * Created by Кира on 15.08.2017.
 */


var ajaxUrl = "/hotel_manager/object/hotel_votes/";
var datatableApi;

// $(function() {
//     $.get(ajaxUrl, updateTableByData);
// });

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
                "data": "userName"
            },
            {
                "data": "userComment"
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