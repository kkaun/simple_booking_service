
//datetimepicker
$(function() {
    $('.out_date').datepicker({
        dateFormat: 'yy-mm-dd',
        onSelect: function() {},
        onClose: function() {
            $(this).focus();
        }
    });
    $('.in_date').datepicker({
        dateFormat: 'yy-mm-dd',
        minDate: 'today',
        maxDate: '+1y +10m',
        onSelect: function (dateText, inst) {
            var nyd = new Date(dateText);
            nyd.setDate(nyd.getDate() + 31);
            $('.out_date').datepicker("option", {
                minDate: new Date(dateText),
                maxDate: nyd
            });
        },
        onClose: function () {
            $(this).focus();
        }
    });
});




//
// $.datetimepicker.setLocale(localeCode);
//
// //  http://xdsoft.net/jqplugins/datetimepicker/
// var startDate = $('#startDate');
// var endDate = $('#endDate');
// startDate.datetimepicker({
//     timepicker: false,
//     format: 'Y-m-d',
//     formatDate: 'Y-m-d',
//     onShow: function (ct) {
//         this.setOptions({
//             maxDate: endDate.val() ? endDate.val() : false
//         })
//     }
// });
// endDate.datetimepicker({
//     timepicker: false,
//     format: 'Y-m-d',
//     formatDate: 'Y-m-d',
//     onShow: function (ct) {
//         this.setOptions({
//             minDate: startDate.val() ? startDate.val() : false
//         })
//     }
// });
//
// $('#startTime, #endTime').datetimepicker({
//     datepicker: false,
//     format: 'H:i'
// });
//
// $('#dateTime').datetimepicker({
//     format: 'Y-m-d H:i'
// });