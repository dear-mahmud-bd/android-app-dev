<?php

$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$paitent_id = $_GET['paitent_id'];

if(mysqli_connect_errno()) {
    echo "Error";
} else {
    $sql = "SELECT paitent_id FROM patient WHERE paitent_id = '$paitent_id'";
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
}

?>