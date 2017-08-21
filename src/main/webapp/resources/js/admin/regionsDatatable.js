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



function addRegion() {
    $('#regionModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('#regionEditRow').modal();
}

function updateRegionRow(id) {
    $('#regionModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#regionEditRow').modal();
    });
}

function renderRegionEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateRegionRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function saveRegion() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#regionEditRow').modal('hide');
            updatePlacesTable();
            successNoty('common.saved');
        }
    });
}

function renderRegionDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="deleteRegionRow(' + row.id + ');" disabled="disabled">'+
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteRegionRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updatePlacesTable();
            successNoty('common.deleted');
        }
    });
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


