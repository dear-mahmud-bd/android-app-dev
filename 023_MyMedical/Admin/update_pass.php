<?php

header('Content-Type: application/json; charset=utf-8');
$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$table_name = $_GET['table_name'];
$email_id = $_GET['email_id'];
$password = $_GET['password'];

$updateSql = "UPDATE $table_name SET password = '$password', pass_req = NULL WHERE email_id = '$email_id'";

$result = mysqli_query($con, $updateSql);
if($result){
    echo "Operation Successfull";
} else {
    echo "Somthing Error!!";
}

?>
