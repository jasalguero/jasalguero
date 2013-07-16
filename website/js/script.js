/*********************************
 *      GLOBAL VARS
 *********************************/

var cv_parent_elem = $('#cvList');
var form = $('#contactform');
var name = $('#contactName');
var email = $('#contactEmail');
var text = $('#contactComment');
var MAIL_SERVER_URL =  "scripts/sendMail.php";
var CAPTCHA_VERIFY_URL = "scripts/verify.php";

/*********************************
 *      INIT FUNCTION
 *********************************/

$(function() {
    cv_parent_elem.expList();

    Recaptcha.create("6LdKl-QSAAAAANEmWQCYY9qUFQTOZjXBVaNfFTYu",
        "recaptcha",
        {
            theme: "white"
        }
    );

    $("#captchaSubmit").on('click', verifyCaptcha);
});

/*********************************
 *      FUNCTION DECLARATIONS
 *********************************/
var sendMail = function(){
    var postData = {
        "posName": $('#contactName').val(),
        "posText": $('#contactComment').val(),
        "posEmail": $('#contactEmail').val()
    };
    var jqxhr = $.ajax({
        url: MAIL_SERVER_URL,
        type: 'post',
        contentType: "application/x-www-form-urlencoded",
        data: postData
    });
    jqxhr.done(function() { alert("Message sent! Thanks for contacting, I'll try to come back to you as soon as possible!"); })
    jqxhr.fail(function() { alert("Ouch, there was an error sending the message, please try again or later :("); })
};


var verifyCaptcha = function(){
    var postData = {
        "recaptcha_challenge_field": Recaptcha.get_challenge(),
        "recaptcha_response_field": Recaptcha.get_response()
    }

    var jqxhr = $.ajax({
        url: CAPTCHA_VERIFY_URL,
        type: 'post',
        contentType: "application/x-www-form-urlencoded",
        data: postData
    });
    jqxhr.done(function(data) {
        var json = JSON.parse(data);
        if (json.result === "ok"){
            Recaptcha.destroy();
            $('#captchaContainer').remove();
            enableSubmitButton();
        }else{
            handleCaptchaFail(data);
        }

    });
    jqxhr.fail(handleCaptchaFail);
}

var handleCaptchaFail = function(data){
    Recaptcha.reload();
    $('#captchaSubmit').html('mmm, please try again!');
}

var enableSubmitButton = function(){
    var submitButton = $('#formSubmit');
    submitButton.removeClass('disabled');
    submitButton.removeAttr('disabled');
    submitButton.on('click', function(event){
        sendMail();
        event.preventDefault();
        document.getElementById("contactform").reset();
    });
}