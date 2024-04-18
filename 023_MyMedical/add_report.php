<?php

$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$paitent_id = $_GET['paitent_id'];
$test_info = $_GET['test_info'];
$test_result = $_GET['test_result'];

if(mysqli_connect_errno()) {
    echo "Something Wrong! <br>" .mysqli_connect_error();
} else {
    // Fetch patient details
    $patientQuery = "SELECT name, birth FROM patient WHERE paitent_id = '$paitent_id'";
    $patientResult = mysqli_query($con, $patientQuery);
    $patientData = mysqli_fetch_assoc($patientResult);
    $name = $patientData['name'];
    // Calculate age based on date of birth
    $birth = $patientData['birth'];
    $today = date("Y-m-d");
    $age = date_diff(date_create($birth), date_create($today))->y;

    // Insert into report table
    $reportQuery = "INSERT INTO tests (name, age, test_info, test_result, patient_id) VALUES('$name', '$age', '$test_info', '$test_result', '$paitent_id')";
    $reportResult = mysqli_query($con, $reportQuery);
    if($reportResult) {
        echo "Report added successfully!";
    } else {
        echo "Something went wrong while adding the report.";
    }
}

?>