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
if (isset($_GET['eid']) && isset($_GET['uid'])) {
    $uid = $_GET['uid'];
    $eid = $_GET['eid'];
 
    // get detail from events table
    $result = mysqli_query($db->connect(),"SELECT * FROM Events WHERE E_id = '$eid' ");
    $userEvents = mysqli_query($db->connect(), "SELECT EventList FROM Users WHERE U_id = '$uid' ");
    $userEventString = mysqli_fetch_array($userEvents)[0];
    $userEventsList = explode(",", $userEventString);
    $isGoing = false;
    foreach ($userEventsList as $event)
    {
        if ($event == $eid)
        {
            $isGoing = true;
            break;
        }
    }
	// check for empty result
	if (mysqli_num_rows($result) > 0) 
    {
		// looping through all results
		// events node
		$response["events"] = array();
	 
		while ($row = mysqli_fetch_array($result)) {
 
            $event = array();
			$event["E_id"] = $row["E_id"];
			$event["Category"]=$row["Category"];
			$event["E_name"] = $row["E_name"];
			$event["Description"] = $row["Description"];
			$event["Venue"] = $row["Venue"];
			$event["EDate"] = $row["EDate"];
			$event["Time"] = $row["Time"];
			$event["Post_date"] = $row["Post_date"];
			$event["Post_time"] = $row["Post_time"];
			$event["Contact_Person"] = $row["Contact_Person"];
			$event["Organization"] = $row["Organization"];
			$event["Count"] = $row["Count"];
            if($isGoing)
            {
                $event["isGoing"] = "true"; 
            }
            else
            {
                $event["isGoing"] = "false";
            }
            // success
            $response["success"] = 1;
 
            array_push($response["events"], $event);
 
            // echoing JSON response
            echo json_encode($response);
        }
	}		
	else {
        // no event found
        $response["success"] = 0;
        $response["message"] = "No event found";
 
        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>