<?php

$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$id = $_GET['id'];
$doctor_id = $_GET['doctor_id'];
$degree = $_GET['degree'];
$speciality = $_GET['speciality'];


$sql = "UPDATE doctor SET doctor_id='$doctor_id', degree='$degree', specility='$speciality' WHERE id='$id' ";
$result = mysqli_query($con, $sql);

if($result){
    echo "Authorized Successfull";
} else {
    echo "Somthing Error!!";
}

?>