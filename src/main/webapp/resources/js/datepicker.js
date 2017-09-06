

$(function() {
    $('.out_date').datepicker({
        dateFormat: 'yy-mm-dd',
        onSelect: function() {
            $("#out_date").datepicker(navigator.userLanguage,
                $.datepicker.regional[$('#hdn_Locale').val()]);
        },
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
            $("#in_date").datepicker(navigator.userLanguage,
                $.datepicker.regional[$('#hdn_Locale').val()]);
        },
        onClose: function () {
            $(this).focus();
        }
    });
});



$(function() {
    $('.end_date').datepicker({
        dateFormat: 'yy-mm-dd',
        onSelect: function() {
            $("#end_date").datepicker(navigator.userLanguage,
                $.datepicker.regional[$('#hdn_Locale').val()]);
        },
        onClose: function() {
            $(this).focus();
        }
    });
    $('.start_date').datepicker({
        dateFormat: 'yy-mm-dd',
        onSelect: function () {
            $("#start_date").datepicker(navigator.userLanguage,
                $.datepicker.regional[$('#hdn_Locale').val()]);
        },
        onClose: function () {
            $(this).focus();
        }
    });
});



$('.in_time, .out_time').datetimepicker({
    datepicker: false,
    format: 'H:i'
});