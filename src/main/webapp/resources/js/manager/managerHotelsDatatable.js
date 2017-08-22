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
                "data": "id",
                "render": function(data, type, row, meta){
                    if(type === 'display'){
                        return '<a href="/hotel_manager/manage_object&id=' + data + '">'
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

});


$('#managerHotelsDatatable').find('tbody').on( 'click', 'button', function () {
    var data = datatableApi.row( $(this).parents('tr') ).data();
    $.get("/hotel_manager/manage_object&id=" + data[0]);
});







// function hotelRowHrefs ( oSettings ) {
//     //On click in row, redirect to page Product of ID
//     $(oTable.fnGetNodes()).click(function () {
//         var iPos = oTable.fnGetPosition(this);
//         var aData = oSettings.aoData[iPos]._aData;
//         //redirect
//         document.location.href = "/hotel_manager/manage_object&id=" + aData.id;
//     });
// }

// $('#managerHotelsDatatable').delegate('tbody > tr > td', 'click', function ()
// {
//     var table = document.getElementById('managerHotelsDatatable');
//
//     $(table).find('tbody').on('click', 'tr', function () {
//         var hotelId = table.row(this).data().id;
//         return '<a href="/hotel_manager/manage_object&id=' + hotelId + '"></a>';
//     });
// });


