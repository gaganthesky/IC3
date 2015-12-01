<?php
 
/*
 * Following code will list all the events
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once('db_connect.php');
 
// connecting to db
$db = new DB_CONNECT;
 
// get all events from Events table
$result = mysqli_query($db->connect(),"SELECT * FROM Events");
// or die(mysql_error());
 
// check for empty result
if (mysqli_num_rows($result) > 0) {
    // looping through all results
    // events node
    $response["events"] = array();
 
    while ($row = mysqli_fetch_array($result)) {
        // temp user array
        $event = array();
        $event["E_id"] = $row["E_id"];
        $event["E_name"] = $row["E_name"];
        $event["Description"] = $row["Description"];
        //$event["Venue"] = $row["Venue"];
        $event["Date"] = $row["EDate"];
        $event["Time"] = $row["Time"];
        //$event["Contact_Person"] = $row["Contact_Person"];
        //$event["Organization"] = $row["Organization"];
        $event["Count"] = $row["Count"];

        // push single events into final response array
        array_push($response["events"], $event);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no events found
    $response["success"] = 0;
    $response["message"] = "No events found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>
