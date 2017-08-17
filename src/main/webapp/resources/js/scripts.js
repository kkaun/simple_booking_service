/**
 * Created by Кира on 15.08.2017.
 */


function goBack() {
    window.history.back();
}


$('.in_time, .out_time').datetimepicker({
    datepicker: false,
    format: 'H:i'
});


