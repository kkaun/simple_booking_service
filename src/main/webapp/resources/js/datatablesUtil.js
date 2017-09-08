/**
 * Created by Кира on 07.09.2017.
 */


function makeEditable() {
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


function renderExpandBtn(data, type, row) {
    if (type === 'display') {
        return '<span><i class="fa fa-bars" aria-hidden="true" ' +
            'title="Show hidden Data/Buttons of this row" style="cursor: pointer;"></i></span>';
    }
}