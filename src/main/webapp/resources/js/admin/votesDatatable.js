/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/admin/votes/";
var datatableApi;

// $(function() {
//     $.get(ajaxUrl, updateTableByData);
// });

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
                "render": renderViewUserVoteBtn,
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