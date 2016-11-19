<?php
header('Content-Type: application/json; charset=utf-8');

define('DB_SERVER', "localhost"); // db server
define('DB_USER', "root"); // db user
define('DB_PASSWORD', "emDXMYsGXatQyTMD"); // db password
define('DB_DATABASE', "somah2_web"); // database name

$con = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

// array for JSON response
$response = array();

$sql = "SELECT * FROM topic_tr";
$result = mysqli_query($con, $sql) or die(mysqli_error());

// looping through all results
while ($row = mysqli_fetch_array($result)) {
    // temp user array
    $element = array();
    $element["topic_id"] = $row["topic_id"];
    $element["language_code"] = $row["language_code"];
    $element["name"] = $row["name"];

    // push single product into final response array
    array_push($response, $element);
}

// echoing JSON response
echo json_encode($response);
