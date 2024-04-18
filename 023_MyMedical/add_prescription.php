<?php

$con = mysqli_connect("localhost", "id21586349_my_medical", "*?35=ZZv^A5Wt#", "id21586349_my_medical");

$doctor_id = $_GET['doctor_id'];
$paitent_id = $_GET['paitent_id'];
$problem = $_GET['problem'];
$test = $_GET['test'];
$medicine = $_GET['medicine'];

if(mysqli_connect_errno()) {
    echo "Something Wrong! <br>" .mysqli_connect_error();
} else {
    // Fetch doctor details
    $doctorQuery = "SELECT name, degree, specility FROM doctor WHERE doctor_id = '$doctor_id'";
    $doctorResult = mysqli_query($con, $doctorQuery);
    $doctorData = mysqli_fetch_assoc($doctorResult);
    $dname = $doctorData['name'];
    $degree = $doctorData['degree'];
    $specility = $doctorData['specility'];
    // Fetch patient details
    $patientQuery = "SELECT name, birth FROM patient WHERE paitent_id = '$paitent_id'";
    $patientResult = mysqli_query($con, $patientQuery);
    $patientData = mysqli_fetch_assoc($patientResult);
    $pname = $patientData['name'];
    // Calculate age based on date of birth
    $birth = $patientData['birth'];
    $today = date("Y-m-d");
    $age = date_diff(date_create($birth), date_create($today))->y;    
    // Insert into prescription table
    $prescriptionQuery = "INSERT INTO prescription (doctor_name, degree, speciality, patient_name, age, medicine, tests, patient_id, problems) VALUES('$dname', '$degree', '$specility', '$pname', '$age', '$medicine', '$test', '$paitent_id', '$problem')";
    $prescriptionResult = mysqli_query($con, $prescriptionQuery);
    if($prescriptionResult) {
        echo "Prescription added successfully!";
    } else {
        echo "Something went wrong while adding the prescription.";
    }
}
?>
