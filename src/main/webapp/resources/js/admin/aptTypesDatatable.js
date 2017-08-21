/**
 * Created by Кира on 15.08.2017.
 */
var ajaxUrl = "/admin/apt_types/";
var datatableApi;

function updateAptTypesTable() {
    $.get(ajaxUrl, updateTableByData);
}



function addAptType() {
    $('#aptTypeModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('#aptTypeEditRow').modal();
}

function updateAptTypeRow(id) {
    $('#aptTypeModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#aptTypeEditRow').modal();
    });
}

function renderAptTypeEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateAptTypeRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function saveAptType() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#aptTypeEditRow').modal('hide');
            updateAptTypesTable();
            successNoty('common.saved');
        }
    });
}

function renderAptTypeDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="deleteAptTypeRow(' + row.id + ');" disabled="disabled">'+
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteAptTypeRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateAptTypesTable();
            successNoty('common.deleted');
        }
    });
}



$(function () {
    datatableApi = $('#aptTypesDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "personNum"
            },
            {
                "data": "category"
            },
            {
                "data": "bedsArrangement"
            },
            {
                "data": "hotelsUsing"
            },
            {
                "data": "apartmentsAppliedTo"
            },
            {
                "render": renderAptTypeEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderAptTypeDeleteBtn,
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