/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/user/votes/";
var datatableApi;

function updateUserVotesTable() {
    $.get(ajaxUrl, updateTableByData);
}


function addUserVote() {
    $('#userVoteModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('#userVoteEditRow').modal();
}

function renderUserVoteEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateUserVoteRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function updateUserVoteRow(id) {
    $('#userVoteModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#userVoteEditRow').modal();
    });
}

function saveUserVote() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#apartmentEditRow').modal('hide');
            updateUserVotesTable();
            successNoty('common.saved');
        }
    });
}

function renderUserVoteDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="deleteUserVoteRow(' + row.id + ');" disabled="disabled">'+
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteUserVoteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateUserVotesTable();
            successNoty('common.deleted');
        }
    });
}




$(function () {
    datatableApi = $('#userVotesDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "dateAdded"
            },
            {
                "data": "rate"
            },
            {
                "data": "userComment"
            },
            {
                "data": "hotel.id"
            },
            {
                "data": "hotel.name"
            },
            {
                "render": renderUserVoteEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderUserVoteDeleteBtn,
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