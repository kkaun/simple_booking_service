/**
 * Created by Кира on 17.08.2017.
 */

var ajaxUrl = "/user/hotels/";
var datatableApi;


$(function () {
    datatableApi = $('#userHotelsDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "imgPath",
                "render": function(data, type, row, meta) {
                    if (type === 'display') {
                        return '<img class="media-object img-rounded img-responsive" alt="No img available yet" ' +
                            'style="max-height: 320px; max-width: 240px;" src="' + data + '" />';
                    }
                },
                "defaultContent": "",
                "orderable": false
            },
            {
                "data": "id"
            },
            {
                "data": "name"
            },
            {
                "data": "rating"
            },
            {
                "data": "votesNum"
            },
            {
                "data": "cityName"
            },
            {
                "data": "countryName"
            },
            {
                "render": renderGetUserVoteByHotelBtn,
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