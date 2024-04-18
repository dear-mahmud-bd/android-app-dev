<?php

header('Content-Type: application/json; charset=utf-8');
$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$sql = "SELECT id, name, email_id, password FROM patient WHERE pass_req IS NOT NULL";
$result = mysqli_query($con, $sql);

$data = array();
foreach ($result as $item){
    $userInfo = array(
        "id" => $item['id'],
        "name" => $item['name'],
        "email_id" => $item['email_id'],
        "password" => $item['password']
    );
    array_push($data, $userInfo);
}
echo json_encode($data);

?>
