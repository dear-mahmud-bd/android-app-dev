<?php

$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$id = $_GET['id'];
$table_name = $_GET['table_name'];

$sql = "DELETE FROM $table_name WHERE id='$id'";
$result = mysqli_query($con, $sql);

if ($result) {
    echo "Deletion Successful";
} else {
    echo "Something Error!!";
}

?>
