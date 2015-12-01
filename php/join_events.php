<?php
 
/*
 * Following code will create a new users row
 * All users details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields    
if (isset($_GET['U_id']) && isset($_GET['E_id'])) {

    $uid = $_GET['U_id'];
	$eid = $_GET['E_id'];
 
    // include db connect class
    require_once('db_connect.php');
	//require_once __DIR__. '/db_config.php';
 
    // connecting to db
    $db = new DB_CONNECT;
	
	//$con = mysqli_connect("mysql-gatorevents-db.ufgatorevents.com", "gatorevents_db", "ic3_ZVSGCA", "gatorevents_db")
	//	or die(mysqli_connect_error());
	
	
	$_count = mysqli_query($db->connect(), "SELECT Count FROM Events WHERE E_id = '$eid' ");
	$c = mysqli_fetch_array($_count);
	$c[0]++;
	
    // mysql inserting a new row
    $result = mysqli_query($db->connect(), "UPDATE Events SET Count = '$c[0]' WHERE E_id = '$eid' ");
	
	//$resultU = mysqli_query($db->connect(), "INSERT ")
 
    // check if row inserted or not
    if ($result) {
		
		//my events
		$_list = mysqli_query($db->connect(),"SELECT EventList FROM Users WHERE U_id = '$uid' ");
		$_evtList = mysqli_fetch_row($_list);
		$eArray = array($eid);
		$_evtList = array_merge($_evtList,$eArray);
		$response["uid"]=$uid;
		$response["eid"]=$eid;
		$comma = implode(",",$_evtList);
		$att = mysqli_query($db->connect(),"UPDATE Users SET EventList = '$comma' WHERE U_id = '$uid' ");
		
		
		
        // successfully inserted into database
        $response["success"] = 1;
		//$response["c"]=$c[0];
		$response["eventlist"]=$_evtList;
        $response["message"] = "Join successfully";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
		
        // echoing JSON response
        echo json_encode($response);
    }
	
	//$response["count"] = $c[0];
	//echo json_encode($response);
	
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>