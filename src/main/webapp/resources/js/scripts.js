/**
 * Created by Кира on 15.08.2017.
 */



function goBack() {
    window.history.back();
}


//Login effects

$(function () {

    $("#phoneLoginForm").hide();
    $('#login-email-link').on("click", function(){
        $("#emailLoginForm, #phoneLoginForm").toggle();
    });
    $('#login-email-link').click(function (e) {
        $('#emailLoginForm').delay(100).fadeIn(100);
        $('#registerForm').fadeOut(100);
        $('#phoneLoginForm').fadeOut(100);
        $('#login-phone-link').removeClass('active');
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#login-phone-link').click(function (e) {
        $('#phoneLoginForm').delay(100).fadeIn(100);
        $('#registerForm').fadeOut(100);
        $('#emailLoginForm').fadeOut(100);
        $('#login-email-link').removeClass('active');
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $('#registerFailForm').delay(100).fadeIn(100);
        $('#registerForm').delay(100).fadeIn(100);
        $('#emailLoginForm').fadeOut(100);
        $("#phoneLoginForm").fadeOut(100);
        $('#login-email-link').removeClass('active');
        $('#login-phone-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});


