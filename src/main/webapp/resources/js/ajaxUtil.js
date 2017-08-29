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

function extendsOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
            "paging": true,
            "info": true,
            "language": {
                "search": i18n["common.search"]
            },
            "initComplete": makeEditable
        }
    );
    return opts;
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





//    ADMIN    -------------------------------------------------------------------------------------------------------



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
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
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
        return '<a onclick="deleteAptTypeRow(' + row.id + ');" disabled="disabled">' +
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
    $('#regionModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    form.find("textarea[name='" + 'description' + "']").val("");
    $('.cityNameForm :input').removeAttr('readonly');
    $('.countryNameForm :input').removeAttr('readonly');
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
        $('#hotelEditRow').modal();
    });
}

function renderHotelEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateHotelRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
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
        return '<a onclick="deleteHotelRow(' + row.id + ');" disabled="disabled">' +
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
        $('#regionEditRow').modal();
    });
}

function renderRegionEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateRegionRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function renderRegionImageBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateRegionImage(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span></a>';
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

function updateRegionImage(id) {
    $('#regionImageModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            $('.imgTag').each(function () {
                var curSrc = $(this).attr('src');
                if ( curSrc === key ) {
                    $(this).attr('src', value);
                }
            });
            form.find("input[name='" + key + "']").val(value);
        });
        $('#regionImageEditRow').modal();
    });
}


function saveRegionImage() {
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
        return '<a onclick="deleteRegionRow(' + row.id + ');" disabled="disabled">' +
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
    $('#userEditRow').modal();
}

function updateUserRow(id) {
    $('#userUpdateModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            $('.userRoleInput').hide();
        });
        $('#userEditRow').modal();
    });
}

function renderUserEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateUserRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
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
        return '<a onclick="deleteUserRow(' + row.id + ');" disabled="disabled">' +
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




//    MANAGER -------------------------------------------------------------------------------------------------------



function addApartment() {
    $('#apartmentModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('#apartmentEditRow').modal();
}

function renderApartmentEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateApartmentRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
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

function renderApartmentImageBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateApartmentImage(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span></a>';
    }
}

function updateApartmentImage(id) {
    $('#apartmentImageModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            $('.imgTag').each(function () {
                var curSrc = $(this).attr('src');
                if ( curSrc === key ) {
                    $(this).attr('src', value);
                }
            });
            form.find("input[name='" + key + "']").val(value);
        });
        $('#apartmentImageEditRow').modal();
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

function saveApartmentImage() {
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
            $('#apartmentImageEditRow').modal('hide');
            updateApartmentsTable();
            successNoty('common.saved');
        }
    });
}

function renderApartmentDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="deleteApartmentRow(' + row.id + ');" disabled="disabled">' +
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




function addManagerHotel() {
    $('#managerHotelModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    form.find("textarea[name='" + 'description' + "']").val("");
    $('.cityNameForm :input').removeAttr('readonly');
    $('.countryNameForm :input').removeAttr('readonly');
    $('#managerHotelEditRow').modal();
}

function renderManagerHotelEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateManagerHotelRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function updateManagerHotelRow(id) {
    $('#managerHotelModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            form.find("textarea[name='" + key + "']").val(value);
            $('.cityNameForm :input').attr('readonly','readonly');
            $('.countryNameForm :input').attr('readonly','readonly');
        });
        $('#managerHotelEditRow').modal();
    });
}

function renderManagerHotelImageBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateManagerHotelImage(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span></a>';
    }
}

function updateManagerHotelImage(id) {
    $('#managerHotelImageModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            $('.imgTag').each(function () {
                var curSrc = $(this).attr('src');
                if ( curSrc === key ) {
                    $(this).attr('src', value);
                }
            });
            form.find("input[name='" + key + "']").val(value);
        });
        $('#managerHotelImageEditRow').modal();
    });
}

function saveManagerHotel() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#managerHotelEditRow').modal('hide');
            updateManagerHotelsTable();
            successNoty('common.saved');
        }
    });
}

function saveManagerHotelImage() {
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
            $('#managerHotelImageEditRow').modal('hide');
            updateManagerHotelsTable();
            successNoty('common.saved');
        }
    });
}


// USER  ---------------------------------------------------------------------------------------------------------------



function addUserVote() {
    $('#userVoteModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('#userVoteEditRow').modal();
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


function renderUserVoteEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateUserVoteRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
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





// BOOKINGS            -----------------------------------------------------------------------------------------------------


function updateSuperBookingRow(id) {
    $('#superBookingModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#superBookingEditRow').modal();
    });
}


function renderSuperBookingEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a onclick="updateSuperBookingRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}


function saveSuperBooking() {
    if ($('#superBookingsDatatable').length > 0
        || $('#hotelSuperBookingsDatatable').length > 0
        || $('#userSuperBookingsDatatable').length > 0) {

        $.ajax({
            type: "POST",
            url: ajaxUrl,
            data: form.serialize(),
            success: function () {
                $('#superBookingEditRow').modal('hide');

                if ($('#superBookingsDatatable').length > 0) {
                    updateAdminSBTableByDatesAdded();
                    updateAdminSBTableByInDate();
                    updateAdminSBTableByOutDate();
                    updateAdminSBTableByUserId();
                    updateAdminSBTableByHotelId();
                }

                if ($('#hotelSuperBookingsDatatable').length > 0) {
                    updateManagerSBTableByDatesAdded();
                    updateManagerSBTableByInDate();
                    updateManagerSBTableByOutDate();
                    updateManagerSBTableByUserId();
                }

                if ($('#userSuperBookingsDatatable').length > 0) {
                    updateUserSuperBookingsTable();
                }

                successNoty('common.saved');
            }
        });
    }
}

/* ----------------------------------------------- Booking DT functions --------------------------------------------- */



function bookingsExtendsOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "url": ajaxUrl +  + '&sbId=' + getSbId(),
                "dataSrc": ""
            },
            "paging": true,
            "info": true,
            "language": {
                "search": i18n["common.search"]
            },
            "initComplete": makeEditable
        }
    );
    return opts;
}


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
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
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


// $(function () {
//     $("#viewBookingsBtn").click(function () {
//         var table = $('<table></table>').addClass('bookingsDatatable');
//         var thead = $('<thead></thead>');
//         var row = $('<tr></tr>');
//
//         row.append('<th><spring:message code="common.id"/></th>');
//         row.append('<th><spring:message code="common.inDate"/></th>');
//         row.append('<th><spring:message code="common.outDate"/></th>');
//         row.append('<th><spring:message code="common.sum"/></th>');
//         row.append('<th><spring:message code="apt_types.personNum"/>, ' +
//             '<spring:message code="apt_types.category"/>, ' +
//             '<spring:message code="apt_types.bedsArrangement"/></th>');
//         row.append('<th></th>');
//
//         thead.append(row);
//         table.append(thead);
//
//         $('.bookingsTableHolder').append(table);
//     });
// });