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
        success: updateAdminTableByData
    });
}

function clearPlacesFilter() {
    $("#placesAdminFilter")[0].reset();
    $.get(ajaxUrl, updateAdminTableByData);
}

$(function () {
    datatableApi = $('#placesDatatable').DataTable(extendsOpts({
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
                "data": "countryName"
            },
            {
                "data": "description"
            },
            {
                "data": "hotelNum"
            },
            {
                "render": renderRegionImageBtn,
                "defaultContent": "",
                "orderable": false
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
        ],
        "pageLength": 50
    }));
});

