/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/hotel_manager/objects/";
var datatableApi;


function updateManagerHotelsTable() {
    $.get(ajaxUrl, updateManagerObjectTableByData);
}


$(function () {
    datatableApi = $('#managerHotelsDatatable').DataTable(extendsManagerObjectOpts({
        "columns": [
            {
                "render": renderExpandBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "data": "id",
                "render": function(data, type, row, meta){
                    if(type === 'display'){
                        return '<a class="btn btn-default" href="/hotel_manager/manage_object?objectId=' + data + '">'
                            + '<span class="glyphicon glyphicon-share" aria-hidden="true"></span>' +
                            '&nbsp;&nbsp;&nbsp;' + data + '</a>';
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
                "data": "votesNum"
            },
            {
                "data": "cityName"
            },
            {
                "data": "countryName"
            },
            {
                "render": renderManagerHotelImageBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderManagerHotelEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderManagerHotelDeleteBtn,
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
        "pageLength": 50,
        responsive: true,
        "autoWidth": false
    }));

});


