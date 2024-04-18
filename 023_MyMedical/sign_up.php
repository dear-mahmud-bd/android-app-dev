<?php

$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$usertype = $_GET['usertype'];
$name = $_GET['name'];
$dob = $_GET['dob'];
$blood = $_GET['blood'];
$email = $_GET['email'];
$id = $_GET['id']; // pas

if(mysqli_connect_errno()) echo "Something Wrong! <br>" .mysqli_connect_error();
// else echo "Conected DB " .$usertype;

if (strcasecmp($usertype, 'patient') === 0) {
    $sql = "INSERT INTO patient (name, email_id, birth, blood, password) VALUES('$name', '$email', '$dob', '$blood', '$id') ";
    $result = mysqli_query($con, $sql);
    if($result) echo "Check your mail and follow the instructions to complete the next step to log in to your account.";
    else echo "Somthing Error!! patient ";

} else if (strcasecmp($usertype, 'doctor') === 0){
    $sql = "INSERT INTO doctor (name, email_id, birth, blood, password) VALUES('$name', '$email', '$dob', '$blood', '$id') ";
    $result = mysqli_query($con, $sql);
    if($result) echo "Check your mail and follow the instructions to complete the next step to log in to your account.";
    else echo "Somthing Error!! doctor";
} else {
    echo "Somthing Error!! ";
}


?>