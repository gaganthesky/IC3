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
	
	$result = mysqli_query($db->connect(),"SELECT EventList FROM Users WHERE U_id = '$uid' ");
	
	$_evtList = mysqli_fetch_row($result);
	$comma = implode("",$_evtList);
	$myArrary = explode(',',$comma);
	
	
	$response["success"] = 1;
	$response["uid"]=$uid;
	$response["result"]=$result;
	$response["eventlist"] = $_evtList;
	$response["eventSplit"] = $myArray;
	echo json_encode($response);
}
else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>