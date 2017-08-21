/**
 * Created by Кира on 19.08.2017.
 */


/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/manager/objects/";
var datatableApi;

function updateManagerHotelsTable() {
    $.get(ajaxUrl, updateTableByData);
}



function addManagerHotel() {
    $('#ModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('#apartmentEditRow').modal();
}

function renderManagerHotelEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateManagerHotelRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function updateManagerHotelRow(id) {
    $('#apartmentModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#apartmentEditRow').modal();
    });
}

function saveManagerHotel() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#apartmentEditRow').modal('hide');
            updateManagerHotelsTable();
            successNoty('common.saved');
        }
    });
}

$(document).ready(function() {

    var table = document.getElementById('managerHotelsDatatable');

    $(table).find('tbody').on('click', 'tr', function () {
        var hotelId = table.row(this).data().id;
        return '<a href="/manage_object&id=' + hotelId + '"></a>';
    });
});



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