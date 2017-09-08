/**
 * Created by Кира on 15.08.2017.
 */

function goBack() {
    window.history.back();
}

$(document).ready(function() {
    $(".dropdown-toggle").dropdown();
});


$(function(){

    $('#slide-submenu').on('click',function() {
        $(this).closest('.list-group').fadeOut('slide',function(){
            $('.mini-submenu').fadeIn();
        });
    });

    $('.mini-submenu').on('click',function(){
        $(this).next('.list-group').toggle('slide');
        $('.mini-submenu').hide();
    })
});
