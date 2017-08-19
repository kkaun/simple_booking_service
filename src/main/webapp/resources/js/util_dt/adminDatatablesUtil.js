/**
 * Created by Кира on 19.08.2017.
 */



/* -----------------------------------------------  Admin Util Display functions ------------------------------------ */


function showAdminTable(table){
    var tables =['aptTypesDatatable', 'hotelsDatatable', 'regionsDatatable', 'superBookingsDatatable',
        'usersDatatable', 'votesDatatable'];
    for(var i=0;i<6;i++){
        document.getElementById(tables[i]).style.display = "none";
    }
    document.getElementById(table).style.display = "block";
}

function showAdminFilters(filterArr) {
    var filters = ['superBookingsAdminDatesAddedFilter', 'superBookingsAdminInDateFilter', 'superBookingsAdminOutDateFilter',
        'superBookingsAdminUserIdFilter', 'superBookingsAdminHotelIdFilter', 'hotelsByRatingAdminFilter',
        'hotelsByCityAdminFilter', 'citiesAdminFilter'];
    for (var i = 0; i < 8; i++) {
        document.getElementById(filters[i]).style.display = "none";
    }
    for (var j = 0; j < filterArr.length; j++) {
        document.getElementById(filterArr[i]).style.display = "block";
    }
}
function hideAdminFilters() {
    var filters = ['superBookingsAdminDatesAddedFilter', 'superBookingsAdminInDateFilter', 'superBookingsAdminOutDateFilter',
        'superBookingsAdminUserIdFilter', 'superBookingsAdminHotelIdFilter', 'hotelsByRatingAdminFilter',
        'hotelsByCityAdminFilter', 'citiesAdminFilter'];
    for (var i = 0; i < filters.length; i++) {
        document.getElementById(filters[i]).style.display = "none";
    }
}

function showAdminAddBtn(btn) {
    var buttons = ['adminAptTypeAddBtn', 'adminHotelAddBtn', 'adminRegionAddBtn', 'adminUserAddBtn'];
    for (var i = 0; i < 4; i++) {
        document.getElementById(buttons[i]).style.display = "none";
    }
    document.getElementById(btn).style.display = "block";
}

function hideAdminAddBtns() {
    var buttons = ['adminAptTypeAddBtn', 'adminHotelAddBtn', 'adminRegionAddBtn', 'adminUserAddBtn'];
    for (var i = 0; i < buttons.length; i++) {
        document.getElementById(buttons[i]).style.display = "none";
    }
}





/* -----------------------------------------------  Admin View Display functions ------------------------------------ */


function showAdminAptTypes() {
    showAdminTable('aptTypesDataTable');
    hideAdminFilters();
    hideAdminAddBtns();
}

function showAdminHotels() {
    showAdminTable('hotelsDatatable');
    showAdminFilters(['hotelsByRatingAdminFilter', 'hotelsByCityAdminFilter']);
    showAdminAddBtn('adminHotelAddBtn');
}

function showAdminRegions() {
    showAdminTable('regionsDatatable');
    showAdminFilters('citiesAdminFilter');
    showAdminAddBtn('adminRegionAddBtn');
}

function showAdminSuperBookings() {
    showAdminTable('superBookingsDatatable');
    showAdminFilters(['superBookingsAdminDatesAddedFilter', 'superBookingsAdminInDateFilter',
        'superBookingsAdminOutDateFilter', 'superBookingsAdminUserIdFilter', 'superBookingsAdminHotelIdFilter']);
    hideAdminAddBtns();
}

function showAdminUsers() {
    showAdminTable('usersDatatable');
    hideAdminFilters();
    showAdminAddBtn('adminUserAddBtn');
}

function showAdminVotes() {
    showAdminTable('votesDatatable');
    hideAdminFilters();
    hideAdminAddBtns();
}






/* -----------------------------------------------  Admin DT functions ---------------------------------------------- */

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

/* -----------------  Admin DT functions ------------------- */

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
            updateTableByData();
            successNoty('common.deleted');
        }
    });
}

/* -----------------  Admin DT functions ------------------- */

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

/* -----------------  Admin DT functions ------------------- */


function addUser() {
    $('#userModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('#userEditRow').modal();
}

function updateUserRow(id) {
    $('#userModalTitle').html(i18n["editTitle"]);
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



