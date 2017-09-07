/**
 * Created by Кира on 07.09.2017.
 */


var form;

function extendsManagerObjectOpts(opts) {
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


function updateManagerObjectTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function renderExpandBtn(data, type, row) {
    if (type === 'display') {
        return '<span><i class="fa fa-bars" aria-hidden="true"></i></span>';
    }
}





function addManagerHotel() {
    $('#managerHotelModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    form.find("textarea[name='" + 'description' + "']").val("");
    $('.cityNameForm :input').removeAttr('readonly');
    $('.countryNameForm :input').removeAttr('readonly');
    $('.load-bar').hide();
    $('#managerHotelEditRow').modal();
}

function renderManagerHotelEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateManagerHotelRow(' + row.id + ');">' +
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
        $('.load-bar').hide();
        $('#managerHotelEditRow').modal();
    });
}

function renderManagerHotelImageBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-default" onclick="updateManagerHotelImage(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span></a>';
    }
}

function updateManagerHotelImage(id) {
    $('#imgTag').attr('src', '');
    $('#managerHotelImageModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            if (key === 'imgPath') {
                $('#imgTag').attr('src', value);
            }
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#managerHotelImageEditRow').modal();
    });
}

function saveManagerHotel() {
    $('.load-bar').show();
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
            $('#managerHotelImageEditRow').modal('hide');
            updateManagerHotelsTable();
            successNoty('common.saved');
        }
    });
}

function renderManagerHotelDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteManagerHotelRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteManagerHotelRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateManagerHotelsTable();
            successNoty('common.deleted');
        }
    });
}

