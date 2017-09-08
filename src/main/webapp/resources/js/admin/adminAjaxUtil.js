/**
 * Created by Кира on 15.08.2017.
 */

var form;

function extendsOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
            "autoWidth": false,
            responsive: true,
            "paging": true,
            "info": true,
            "language": {
                "search": i18n["common.search"],
                "processing": i18n["common.processing"],
                "info": i18n["common.table_info"],
                "lengthMenu":    i18n["common.menu_length"],
                "loadingRecords": i18n["common.loading_records"],
                "zeroRecords":    i18n["common.zero_records"],
                "emptyTable":     i18n["common.empty_table"],
                "paginate": {
                    "first":      i18n["common.paging_first"],
                    "previous":   i18n["common.paging_previous"],
                    "next":       i18n["common.paging_next"],
                    "last":       i18n["common.paging_last"]
                }
            },
            "initComplete": makeEditable
        }
    );
    return opts;
}


function updateAdminTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}


function getDelayedTable() {
    $.get(ajaxUrl, updateAdminTableByData);
}

function delayedUpdate() {
    setTimeout(getDelayedTable, 2000);
}






function addAptType() {
    $('#aptTypeModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('.load-bar').hide();
    $('#aptTypeEditRow').modal();
}

function updateAptTypeRow(id) {
    $('#aptTypeModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#aptTypeEditRow').modal();
    });
}

function renderAptTypeEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateAptTypeRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function saveAptType() {
    $('.load-bar').show();
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
        return '<a class="btn btn-danger" onclick="deleteAptTypeRow(' + row.id + ');">' +
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



function addHotel() {
    $('#hotelModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    form.find("textarea[name='" + 'description' + "']").val("");
    form.find("input[name='" + 'managerId' + "']").val('100003');
    $('.cityNameForm :input').removeAttr('readonly');
    $('.countryNameForm :input').removeAttr('readonly');
    $('.load-bar').hide();
    $('#hotelEditRow').modal();
}

function updateHotelRow(id) {
    $('#hotelModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            form.find("textarea[name='" + key + "']").val(value);
            $('.cityNameForm :input').attr('readonly','readonly');
            $('.countryNameForm :input').attr('readonly','readonly');
        });
        $('.load-bar').hide();
        $('#hotelEditRow').modal();
    });
}

function renderHotelEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateHotelRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function saveHotel() {
    $('.load-bar').show();
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
        return '<a class="btn btn-danger" onclick="deleteHotelRow(' + row.id + ');">' +
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




function addRegion() {
    $('#regionModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    form.find("textarea[name='" + 'description' + "']").val("");
    $('.currentCountryName').hide();
    $('.countryNamesList').show();
    $('.load-bar').hide();
    $('#regionEditRow').modal();
}

function updateRegionRow(id) {
    $('#regionModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("textarea[name='" + key + "']").val(value);
            form.find("input[name='" + key + "']").val(value);
            $('.countryNamesList').hide();
            $('.currentCountryName').show();
        });
        $('.load-bar').hide();
        $('#regionEditRow').modal();
    });
}

function renderRegionEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateRegionRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function renderRegionImageBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-default" onclick="updateRegionImage(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span></a>';
    }
}

function saveRegion() {
    $('.load-bar').show();
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

function updateRegionImage(id) {
    $('#imgTag').attr('src', '');
    $('#regionImageModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            if (key === 'imgPath') {
                $('#imgTag').attr('src', value);
            }
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#regionImageEditRow').modal();
    });
}


function saveRegionImage() {
    $('.load-bar').show();
    var objFormData = new FormData(document.getElementById("imgForm"));
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'set_image',
        data: objFormData,
        enctype: 'multipart/form-data',
        contentType: false,
        processData: false,
        //data: form.serialize(),
        success: function () {
            $('#regionImageEditRow').modal('hide');
            updatePlacesTable();
            successNoty('common.saved');
        }
    });
}

function renderRegionDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteRegionRow(' + row.id + ');">' +
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



function addUser() {
    $('#userUpdateModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('.userRoleInput').show();
    $('.load-bar').hide();
    $('#enabled').remove();
    $('#userEditRow').modal();
}

function updateUserRow(id) {
    $('#userUpdateModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        var userForm = $(".detailsForm");
        if(!$('#enabled').length) {
            userForm.append('<input type="hidden" id="enabled" name="enabled">');
        }
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            $('.userRoleInput').hide();
        });
        $('.load-bar').hide();
        $('#userEditRow').modal();
    });
}

function renderUserEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateUserRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function saveUser() {
    $('.load-bar').show();
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
        return '<a class="btn btn-danger" onclick="deleteUserRow(' + row.id + ');">' +
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




function renderDeleteVoteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteVoteRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteVoteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateVotesTable();
            successNoty('common.deleted');
        }
    });
}




