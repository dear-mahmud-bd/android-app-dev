<?php

$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$id = $_GET['id'];
$paitent_id = $_GET['paitent_id'];
$family = $_GET['family'];


$sql = "UPDATE patient SET paitent_id='$paitent_id', family_diseas='$family' WHERE id='$id' ";
$result = mysqli_query($con, $sql);

if($result){
    echo "Authorized Successfull";
} else {
    echo "Somthing Error!!";
}

?>