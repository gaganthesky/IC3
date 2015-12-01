<?php
 
/*
 * Following code will create a new users row
 * All users details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields    
if (isset($_GET['U_id'])) {

    $uid = $_GET['U_id'];
 
    // include db connect class
    require_once('db_connect.php');
	//require_once __DIR__. '/db_config.php';
 
    // connecting to db
    $db = new DB_CONNECT;
	
	$_list = mysqli_query($db->connect(),"SELECT EventList FROM Users WHERE U_id = '$uid' ");
    $_evtList = mysqli_fetch_row($_list);
    // $userEventsString = $_evtList[0];
    $userEventsString = trim($_evtList[0], ',');
    $userEventsArray = explode(",", $userEventsString);

    // //////
    
    // echo $_evtList[0], "\n"; // DEBUGGING
    // echo gettype($_evtList[0]), "\n"; // DEBUGGING
    // echo $userEventsString, "\n";      // DEBUGGING
    // echo $userEventsArray, "\n" ;       // DEBUGGING
    
    // /////

    $result = mysqli_query($db->connect(), "SELECT * FROM Events WHERE E_id IN (" . implode(',', $userEventsArray) . ")");
	
    // check for empty result
    if (mysqli_num_rows($result) > 0) 
    {
        // looping through all results
        // events node
        $response["events"] = array();
 
        while ($row = mysqli_fetch_array($result)) {
            // temp user array
            $event = array();
            $event["E_id"] = $row["E_id"];
            $event["E_name"] = $row["E_name"];
            $event["Description"] = $row["Description"];
            $event["Date"] = $row["EDate"];
            $event["Time"] = $row["Time"];
            $event["Count"] = $row["Count"];

            // push single events into final response array
            array_push($response["events"], $event);
        }
        // success
        $response["success"] = 1;
 
        // echoing JSON response
        echo json_encode($response);
    } 
    else 
    {
        // no events found
        $response["success"] = 0;
        $response["message"] = "No events found";
 
        // echo no users JSON
        echo json_encode($response);
    }
}
else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>