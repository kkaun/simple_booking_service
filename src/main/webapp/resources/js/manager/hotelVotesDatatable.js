/**
 * Created by Кира on 15.08.2017.
 */


var ajaxUrl = "/hotel_manager/object/hotel_votes/";
var datatableApi;

$(function () {
    datatableApi = $('#hotelVotesDatatable').DataTable(extendsObjectChildrenOpts({
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