/**
 * Created by Кира on 15.08.2017.
 */

var form;

function makeEditable() {
    form = $('.detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });
    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({ cache: false });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

// https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
function extendsOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "language": {
                "search": i18n["common.search"]
            },
            "initComplete": makeEditable
        }
    );
    return opts;
}



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



/* -----------------------------------------------  Manager Util Display functions ---------------------------------- */


function showManagerTable(table){
    var tables =['apartmentsDatatable', 'hotelSuperBookingsDatatable',
        'hotelVotesDatatable', 'ganttTable'];
    for(var i=0;i<4;i++){
        document.getElementById(tables[i]).style.display = "none";
    }
    document.getElementById(table).style.display = "block";
}

function hideManagerFilters() {
    var filters =['superBookingsAdminDatesAddedFilter', 'superBookingsAdminInDateFilter', 'superBookingsAdminOutDateFilter',
        'superBookingsAdminUserIdFilter'];
    for(var i=0;i<4;i++){
        document.getElementById(filters[i]).style.display = "none";
    }
}

function hideManagerButton() {
    var button = 'managerApartmentAddBtn';
    document.getElementById(button).style.display = "none";
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



/* ----------------------------------------------- Manager View Display functions ----------------------------------- */


function showManagerApartments() {
    showManagerTable('apartmentsDatatable');
    hideManagerFilters();
}

function showHotelChart() {
    showManagerTable('ganttTable');
    hideManagerFilters();
    hideManagerButton();
}

function showManagerSuperBookings(){
    showManagerTable('hotelSuperBookingsDatatable');
    hideManagerButton();
}

function showManagerHotelVotes() {
    showManagerTable('hotelVotesDatatable');
    hideManagerButton();
}




/* ----------------------------------------------- Other Views Display functions ------------------------------------ */


function ShowBookings() {}


/* -----------------------------------------------  SuperBooking DT functions --------------------------------------- */

var superBookingId;

function updateSuperBookingRow(id) {
    superBookingId = id;
    $('#superBookingModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#superBookingEditRow').modal();
    });
}

function getSbId() {
    return superBookingId;
}

function renderSuperBookingEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateSuperBookingRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function saveSuperBooking() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#superBookingEditRow').modal('hide');
            updateAdminSBTableByDatesAdded();
            updateAdminSBTableByInDate();
            updateAdminSBTableByOutDate();
            updateAdminSBTableByUserId();
            updateAdminSBTableByHotelId();

            updateManagerSBTableByDatesAdded();
            updateManagerSBTableByInDate();
            updateManagerSBTableByOutDate();
            updateManagerSBTableByUserId();

            successNoty('common.saved');
        }
    });
}



/* ----------------------------------------------- Booking DT functions --------------------------------------------- */


function updateBookingRow(id) {
    $('#bookingModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#bookingEditRow').modal();
    });
}

function renderBookingEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateBookingRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function saveBooking() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + getSbId(),
        data: form.serialize(),
        success: function () {
            $('#bookingEditRow').modal('hide');
            updateBookingsTable();
            successNoty('common.saved');
        }
    });
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






/* -----------------------------------------------  Manager DT functions -------------------------------------------- */


function addApartment() {
    $('#apartmentModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('#apartmentEditRow').modal();
}

function renderApartmentEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateApartmentRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function updateApartmentRow(id) {
    $('#apartmentModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#apartmentEditRow').modal();
    });
}

function saveApartment() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#apartmentEditRow').modal('hide');
            updateApartmentsTable();
            successNoty('common.saved');
        }
    });
}






/* -------------------------------------------------  User DT functions --------------------------------------------- */


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






/* -------------------------------------------------  Util functions --------------------------------------------- */



function renderDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="deleteRow(' + row.id + ');" disabled="disabled">'+
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}


function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('common.deleted');
        }
    });
}


function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}


var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    noty({
        text: i18n[key],
        type: 'success',
        layout: 'bottomRight',
        timeout: 1500
    });
}

function failNoty(jqXHR) {
    closeNoty();
    var errorInfo = $.parseJSON(jqXHR.responseText);
    failedNote = noty({
        text: i18n['common.errorStatus'] + ': ' + jqXHR.status + '<br>'+ errorInfo.cause + '<br>' + errorInfo.details.join("<br>"),
        type: 'error',
        layout: 'bottomRight'
    });
}




