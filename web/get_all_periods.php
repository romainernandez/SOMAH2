
<?php
header('Content-Type: application/json');

define('DB_SERVER', "localhost"); // db server
define('DB_USER', "root"); // db user
define('DB_PASSWORD', "emDXMYsGXatQyTMD"); // db password
define('DB_DATABASE', "somah2_web"); // database name

$con = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

/*
 * Following code will list all the periods
 */

// array for JSON response
$response = array();

// get all periods from period table
$sql = "SELECT * FROM period";
$result = mysqli_query($con, $sql) or die(mysqli_error());

// looping through all results
// products node
while ($row = mysqli_fetch_array($result)) {
    // temp user array
    $period = array();
    $period["id"] = $row["id"];
    $period["image"] = base64_encode($row["image"]);

    // push single product into final response array
    array_push($response, $period);
}

// echoing JSON response
echo json_encode($response);
