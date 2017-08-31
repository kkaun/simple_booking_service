/**
 * Created by Кира on 31.08.2017.
 */



var form;

function makeSubBookingEditable() {
    form = $('.detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    $.ajaxSetup({ cache: false });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}


function extendsSubBookingOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "url": subBookingAjaxUrl + "getAll?superBookingId=" + sbId,
                "dataSrc": ""
            },
            "paging": true,
            "info": true,
            "language": {
                "search": i18n["common.search"]
            },
            "initComplete": makeSubBookingEditable
        }
    );
    return opts;
}



function updateSubBookingTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}


function addSubBooking() {
    $('#bookingModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('.load-bar').hide();
    $('#bookingEditRow').modal();
}


function updateBookingRow(id) {
    $('#bookingModalTitle').html(i18n["editTitle"]);
    $.get(subBookingAjaxUrl + "get?superBookingId=" + sbId + "&subId=" + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
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
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: subBookingAjaxUrl + "crud?superBookingId=" + sbId,
        data: form.serialize(),
        success: function () {
            $('#bookingEditRow').modal('hide');
            updateBookingsTable();
            successNoty('common.saved');
        }
    });
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

