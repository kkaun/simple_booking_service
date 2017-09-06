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
                "url": subBookingAjaxUrl + "getAll?bookingId=" + bookingId,
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
            "initComplete": makeSubBookingEditable
        }
    );
    return opts;
}


function renderExpandBtn(data, type, row) {
    if (type === 'display') {
        return '<span><i class="fa fa-bars" aria-hidden="true"></i></span>';
    }
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


function updateSubBookingRow(id) {
    $('#bookingModalTitle').html(i18n["editTitle"]);
    $.get(subBookingAjaxUrl + "get?bookingId=" + bookingId + "&subId=" + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#bookingEditRow').modal();
    });
}

function renderSubBookingEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateSubBookingRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function saveSubBooking() {
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: subBookingAjaxUrl + "crud?bookingId=" + bookingId,
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

