<?php

$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$email = $_GET['email'];
$tableName = $_GET['table_name'];

$sql = "SELECT * FROM $tableName WHERE email_id = '$email'";
$result = mysqli_query($con, $sql);
$rowCount = mysqli_num_rows($result);

if ($rowCount > 0) echo "yes";
else echo "no";

?>
