<?php

// change the 4 variables below
$yourName = 'Jose A Salguero';
$yourEmail = 'contact@jasalguero.com';
$yourSubject = 'contactForm';
$referringPage = 'http://www.jasalguero.com/contact.html';
// no need to change the rest unless you want to. You could add more error checking but I'm gonna do that later in the official release


function cleanPosUrl ($str) {
	$nStr = $str;
	$nStr = str_replace("**am**","&",$nStr);
	$nStr = str_replace("**pl**","+",$nStr);
	$nStr = str_replace("**eq**","=",$nStr);
	echo $nStr;
	return stripslashes($nStr);
}
if ( $_GET['contact'] == true && $_GET['xml'] == true && isset($_POST['posText']) ) {
$to = $yourName.' <'.$yourEmail.'>';
$subject = 'About: '.cleanPosUrl($_POST['posRegard']);
$message = cleanPosUrl($_POST['posText']);
$headers = "From: ".cleanPosUrl($_POST['posName'])." <".cleanPosUrl($_POST['posEmail']).">\r\n";
//$headers .= 'To: '.$yourName.' <'.$yourEmail.'>'."\r\n";
$result = mail($to,$subject,$message,$headers);		
	if ( @$result )			
	{ 

		header('Content-Type: application/json');
		echo json_encode("OK"); 
	}
	else
	{ 
		header('HTTP/1.1 500 Internal Server Booboo');
		header('Content-Type: application/json');
		die('ERROR');
	 }
	
?> 