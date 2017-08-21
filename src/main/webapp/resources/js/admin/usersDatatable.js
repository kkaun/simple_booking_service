/**
 * Created by Кира on 15.08.2017.
 */
var ajaxUrl = "admin/users/";
var datatableApi;

function updateUsersTable() {
    $.get(ajaxUrl, updateTableByData);
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: 'enabled=' + enabled,
        success: function () {
            chkbox.closest('tr').toggleClass('disabled');
            successNoty(enabled ? 'common.enabled' : 'common.disabled');
        },
        error: function () {
            $(chkbox).prop("checked", !enabled);
        }
    });
}



function addUser() {
    $('#userCreateModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('#userCreateRow').modal();
}

function updateUserRow(id) {
    $('#userUpdateModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#userEditRow').modal();
    });
}

function renderUserEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateUserRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function saveUser() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#userEditRow').modal('hide');
            updateUsersTable();
            successNoty('common.saved');
        }
    });
}

function renderUserDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="deleteUserRow(' + row.id + ');" disabled="disabled">'+
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteUserRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateUsersTable();
            successNoty('common.deleted');
        }
    });
}





// $(document).ready(function () {
$(function () {
    datatableApi = $('#usersDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "name"
            },
            {
                "data": "email",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        return '<a href="mailto:' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            },
            {
                "data": "phone"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="enable($(this),' + row.id + ');"/>';
                    }
                    return data;
                }
            },
            {
                "data": "registered",
                "render": function (date, type, row) {
                    if (type === 'display') {
                        return '<span>' + date.substring(0, 10) + '</span>';
                    }
                    return date;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderUserEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderUserDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).addClass("disabled");
            }
        }
    }));
});