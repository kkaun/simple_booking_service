/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/hotel_manager/objects/";
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
        ],
        "pageLength": 50,
        responsive: true,
        "autoWidth": false
    }));

    $(document).ready(function () {

        var table = document.getElementById('managerHotelsDatatable');

        $(table).find('tbody').on('click', 'tr', function () {
            var hotelId = table.row(this).data().id;
            return '<a href="/manage_object&id=' + hotelId + '"></a>';
        });
    });

});



