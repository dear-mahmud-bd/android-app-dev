<?php

header('Content-Type: application/json; charset=utf-8');
$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$patient_id = $_GET['paitent_id'];

$sql = "SELECT * FROM tests WHERE patient_id = '$patient_id'";
$result = mysqli_query($con, $sql);

$data = array();
foreach ($result as $item){
    $userInfo = array(
        "id" => $item['id'],
        "name" => $item['name'],
        "age" => $item['age'],
        "test_info" => $item['test_info'],
        "test_result" => $item['test_result'],
        "patient_id" => $item['patient_id']
    );
    array_push($data, $userInfo);
}
echo json_encode($data)

?>