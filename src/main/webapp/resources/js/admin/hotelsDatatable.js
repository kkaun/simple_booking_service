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
        success: updateAdminTableByData
    });
}

function updateHotelsTableByRating() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "between_ratings",
        data: $("#hotelsByRatingAdminFilter").serialize(),
        success: updateAdminTableByData
    });
}

function clearHotelsByCityFilter() {
    $("#hotelsByCityAdminFilter")[0].reset();
    $.get(ajaxUrl, updateAdminTableByData);
}

function clearHotelsByRatingFilter() {
    $("#hotelsByCityAdminFilter")[0].reset();
    $.get(ajaxUrl, updateAdminTableByData);
}


$(function () {
    datatableApi = $('#hotelsDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "render": renderExpandBtn,
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
                "data": "stars"
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
        ],
        "pageLength": 50
    }));
});
