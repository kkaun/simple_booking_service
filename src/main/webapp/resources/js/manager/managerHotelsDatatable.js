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
    datatableApi = $('#managerHotelsDatatable').DataTable(extendsOpts({
        "columns": [
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
                "render": renderManagerHotelEditBtn,
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