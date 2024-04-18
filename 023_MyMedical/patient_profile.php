<?php

header('Content-Type: application/json; charset=utf-8');
$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$paitent_id = $_GET['paitent_id'];

if(mysqli_connect_errno()) {
    echo json_encode(array("error" => "Database connection error"));
} else {
    $sql = "SELECT * FROM patient WHERE paitent_id = '$paitent_id'";
    $result = mysqli_query($con, $sql);

    $data = array();
    $row = mysqli_fetch_assoc($result);

    if ($row) {
        $userInfo = array(
            "id" => $row['id'],
            "name" => $row['name'],
            "birth" => $row['birth'],
            "email" => $row['email_id'],
            "blood" => $row['blood'],
            "family_diseas" => $row['family_diseas'],
        );
        $data = $userInfo;
    } else {
        $data['error'] = 'No data found for the given patient_id';
    }
    echo json_encode($data);
}

?>