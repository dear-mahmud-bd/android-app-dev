<?php

$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$usertype = $_GET['usertype'];
$email = $_GET['email'];
$id = $_GET['id']; // pas

if(mysqli_connect_errno()) {
    echo "Error";
} else {
    if (strcasecmp($usertype, 'patient') === 0) {
        $sql = "SELECT paitent_id FROM patient WHERE email_id = '$email' AND password = '$id'";
        $result = mysqli_query($con, $sql);
        $rowCount = mysqli_num_rows($result);
        if($rowCount==0){
            echo "0";
        } else if ($result) {
            $row = mysqli_fetch_assoc($result);
            if (isset($row['paitent_id'])) echo $row['paitent_id'];
            else echo "NULL";
        } else {
            echo "Error";
        }
    } else if (strcasecmp($usertype, 'doctor') === 0) {
        $sql = "SELECT doctor_id FROM doctor WHERE email_id = '$email' AND password = '$id'";
        $result = mysqli_query($con, $sql);
        $rowCount = mysqli_num_rows($result);
        if($rowCount==0){
            echo "0";
        } else if ($result) {
            $row = mysqli_fetch_assoc($result);
            if (isset($row['doctor_id'])) echo $row['doctor_id'];
            else echo "NULL";
        } else {
            echo "Error";
        }
    } else {
        echo "Error";
    }
}

?>