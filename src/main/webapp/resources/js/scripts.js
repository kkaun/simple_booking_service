
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




// Since confModal is essentially a nested modal it's enforceFocus method
// must be no-op'd or the following error results
// "Uncaught RangeError: Maximum call stack size exceeded"
// But then when the nested modal is hidden we reset modal.enforceFocus
