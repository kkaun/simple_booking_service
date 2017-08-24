/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/hotel_manager/object/apartments/";
var datatableApi;

function updateApartmentsTable() {
    $.get(ajaxUrl, updateTableByData);
}


$(function () {
    datatableApi = $('#apartmentsDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "stringAptType"
            },
            {
                "data": "price"
            },
            {
                "render": renderApartmentImageBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderApartmentEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderApartmentDeleteBtn,
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
