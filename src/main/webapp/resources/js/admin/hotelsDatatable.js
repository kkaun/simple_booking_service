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




function addHotel() {
    $('#regionModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('#hotelEditRow').modal();
}

function updateHotelRow(id) {
    $('#hotelModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#hotelEditRow').modal();
    });
}

function renderHotelEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateHotelRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function saveHotel() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#hotelEditRow').modal('hide');
            updateHotelsTableByRating();
            updateHotelsTableByCity();
            successNoty('common.saved');
        }
    });
}

function renderHotelDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="deleteHotelRow(' + row.id + ');" disabled="disabled">'+
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteHotelRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateHotelsTableByCity();
            updateHotelsTableByRating();
            successNoty('common.deleted');
        }
    });
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