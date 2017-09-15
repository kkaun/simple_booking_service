/**
 * Created by Кира on 17.08.2017.
 */

var ajaxUrl = "/user/hotels/";
var datatableApi;

function updateUserHotelsTable() {
    $.get(ajaxUrl, updateUserTableByData);
}

$(function () {
    datatableApi = $('#userHotelsDatatable').DataTable(extendsUserOpts({
        "columns": [
            {
                "render": renderExpandBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "data": "imgPath",
                "render": function(data, type, row, meta) {
                    if (type === 'display') {
                        return '<img class="media-object img-rounded img-responsive" alt="No img available yet" ' +
                            'style="max-height: 300px; max-width: 200px;" src="' + data + '" />';
                    }
                },
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderGetUserVoteByHotelBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "data": "name"
            },
            {
                "data": "cityName"
            },
            {
                "data": "countryName"
            },
            {
                "data": "id"
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