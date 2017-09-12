/**
 * Created by Кира on 07.09.2017.
 */

var form;

function extendsObjectChildrenOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "url": ajaxUrl + "getAll?objectId=" + objectId,
                "dataSrc": ""
            },
            "autoWidth": false,
            "responsive": true,
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


function updateObjectChildrenTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}




function addApartment() {
    $('#apartmentModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('.load-bar').hide();
    $('#apartmentEditRow').modal();
}

function renderApartmentEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateApartmentRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function updateApartmentRow(id) {
    $('#apartmentModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#apartmentEditRow').modal();
    });
}

function saveApartment() {
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: ajaxUrl + "create_update?objectId=" + objectId,
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
        return '<a class="btn btn-danger" onclick="deleteApartmentRow(' + row.id + ');">' +
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


function renderApartmentImageBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-default" onclick="updateApartmentImage(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span></a>';
    }
}

function updateApartmentImage(id) {
    $('#imgTag').attr('src', '');
    $('#apartmentImageModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            if (key === 'imgPath') {
                $('#imgTag').attr('src', value);
            }
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#apartmentImageEditRow').modal();
    });
}

function saveApartmentImage() {
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
            $('#apartmentImageEditRow').modal('hide');
            updateApartmentsTable();
            successNoty('common.saved');
        }
    });
}