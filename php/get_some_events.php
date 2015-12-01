<?php
 
/*
 * Following code will get single event details
 * A event is identified by event id (pid)
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once('db_connect.php');
 
// connecting to db
$db = new DB_CONNECT;
 
// check for post data
if (isset($_GET['TYPE'])) 
{
    $type = $_GET['TYPE'];
    $date = "";
    $category = "";
    $uid = "";
    $result = NONE;
    if ($type == "DATE_SEARCH")
    {
        $date = $_GET["DATE"];
        $result = mysqli_query($db->connect(), "SELECT * FROM Events WHERE EDate = '$date' "); 
    }
    else if ($type == "CATEGORY_SEARCH")
    {
        $category = $_GET["CATEGORY"];
        $result = mysqli_query($db->connect(), "SELECT * FROM Events WHERE Category = '$category' ");
    }

    // else if ($type == "MYEVENTS_SEARCH");
    // {
    //     $uid = $_GET["U_id"];
    //     $_list = mysqli_query($db->connect(),"SELECT EventList FROM Users WHERE U_id = '$uid' ");
	// 	$_evtList = mysqli_fetch_row($_list);
    //     $userEventsArray = $_evtList[0];
    //     $result = mysqli_query($db->connect(), "SELECT * FROM Events WHERE E_id IN ({implode(',', $userEventsArray)})");
	// var_dump($result);
    // }
     
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
else 
{
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>
