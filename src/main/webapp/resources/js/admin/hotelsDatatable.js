/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/admin/hotels/";
var datatableApi;

function updateHotelsTableByCity() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_city",
        data: $("#hotelsByCityAdminFilter").serialize(),
        success: updateTableByData
    });
}

function updateHotelsTableByRating() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "between_ratings",
        data: $("#hotelsByRatingAdminFilter").serialize(),
        success: updateTableByData
    });
}

function clearHotelsByCityFilter() {
    $("#hotelsByCityAdminFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

function clearHotelsByRatingFilter() {
    $("#hotelsByCityAdminFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}


$(function () {
    datatableApi = $('#hotelsDatatable').DataTable(extendsOpts({
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
                "render": renderHotelEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderHotelDeleteBtn,
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
