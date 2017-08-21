/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/admin/regions/";
var datatableApi;


function updatePlacesTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_region",
        data: $("#placesAdminFilter").serialize(),
        success: updateTableByData
    });
}

function clearPlacesFilter() {
    $("#placesAdminFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#placesDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "name"
            },
            {
                "data": "countryName"
            },
            {
                "data": "description"
            },
            {
                "data": "hotelNum"
            },
            {
                "render": renderRegionEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderRegionDeleteBtn,
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


