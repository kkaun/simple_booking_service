/**
 * Created by Кира on 19.08.2017.
 */


/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/manager/hotels/";
var datatableApi;

function updateManagerHotelsTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#hotelsDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "id",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        return '<a href="/object/' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            },
            {
                "data": "name"
            },
            {
                "data": "rating"
            },
            {
                "data": "stars"
            },
            {
                "data": "description"
            },
            {
                "data": "votesNum"
            },
            {
                "data": "checkIn"
            },
            {
                "data": "checkOut"
            },
            {
                "data": "cityName"
            },
            {
                "data": "countryName"
            },
            {
                "render": renderHotelEditBtn,
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