
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
            var min = new Date(dateText);
            var max = new Date(dateText);
            min.setDate(min.getDate() + 1);
            max.setDate(max.getDate() + 31);
            $('.out_date').datepicker("option", {
                minDate: min,
                maxDate: max
            });
        },
        onClose: function () {
            $(this).focus();
        }
    });
});



$(function() {
    $('.from_date').datepicker({
        dateFormat: 'yy-mm-dd',
        onSelect: function() {},
        onClose: function() {
            $(this).focus();
        }
    });
    $('.to_date').datepicker({
        dateFormat: 'yy-mm-dd',
        onSelect: function () {},
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