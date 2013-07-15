/*********************************
 *      GLOBAL VARS
 *********************************/

var cv_parent_elem = $('#cvList');
var form = $('#contactform');
var name = $('#contactName');
var email = $('#contactEmail');
var text = $('#contactComment');
var MAIL_SERVER_URL =  "../scripts/sendMail.php"
//var MAIL_SERVER_URL =  "http://localhost:8087/sendMail.php"
/*********************************
 *      INIT FUNCTION
 *********************************/

$(function() {
    cv_parent_elem.expList();

    form.on('submit', function(event){
        sendMail();
        event.preventDefault();
        document.getElementById("contactform").reset();
    });
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