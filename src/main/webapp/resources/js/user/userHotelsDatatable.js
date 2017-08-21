/**
 * Created by Кира on 17.08.2017.
 */

var ajaxUrl = "/user/hotels/";
var datatableApi;

$(function () {
    datatableApi = $('#userHotelsDatatable').DataTable(extendsOpts({
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