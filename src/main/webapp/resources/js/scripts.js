


$(function() {
    $('.out_date').datepicker({
        onSelect: function() {},
        onClose: function() {
            $(this).focus();
        }
    });
    $('.in_date').datepicker({
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