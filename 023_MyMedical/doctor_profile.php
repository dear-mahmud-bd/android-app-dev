<?php

header('Content-Type: application/json; charset=utf-8');
$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$doctor_id = $_GET['doctor_id'];

if(mysqli_connect_errno()) {
    echo json_encode(array("error" => "Database connection error"));
} else {
    $sql = "SELECT * FROM doctor WHERE doctor_id = '$doctor_id'";
    $result = mysqli_query($con, $sql);
    $data = array();
    $row = mysqli_fetch_assoc($result);

    if ($row) {
        $userInfo = array(
            "id" => $row['id'],
            "name" => $row['name'],
            "birth" => $row['birth'],
            "degree" => $row['degree'],
            "specility" => $row['specility'],
        );
        $data = $userInfo;
    } else {
        $data['error'] = 'No data found for the given doctor_id';
    }
    echo json_encode($data);
}

?>