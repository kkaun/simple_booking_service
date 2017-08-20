/**
 * Created by Кира on 19.08.2017.
 */



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

function renderApartmentDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="deleteApartmentRow(' + row.id + ');" disabled="disabled">'+
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteApartmentRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateApartmentsTable();
            successNoty('common.deleted');
        }
    });
}


/* -----------------------------------------------  Manager DT functions -------------------------------------------- */


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


