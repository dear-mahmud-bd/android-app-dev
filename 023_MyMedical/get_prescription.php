<?php

header('Content-Type: application/json; charset=utf-8');
$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$patient_id = $_GET['paitent_id'];

$sql = "SELECT * FROM prescription WHERE patient_id = '$patient_id'";
$result = mysqli_query($con, $sql);

$data = array();
foreach ($result as $item){
    $userInfo = array(
        "id" => $item['id'],
        "doctor_name" => $item['doctor_name'],
        "degree" => $item['degree'],
        "speciality" =>  $item['speciality'],
        "patient_name" => $item['patient_name'],
        "age" => $item['age'],
        "problems" => $item['problems'],
        "medicine" => $item['medicine'],
        "tests" => $item['tests'],
        "patient_id" => $item['patient_id']
    );
    array_push($data, $userInfo);
}
echo json_encode($data)

?>