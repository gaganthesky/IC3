<?php
 
/*
 * Following code will create a new users row
 * All users details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['name']) && isset($_POST['email'])) {
 
    $name = $_POST['name'];
    $email = $_POST['email'];
 
    // include db connect class
    require_once('db_connect.php');
	//require_once __DIR__. '/db_config.php';
 
    // connecting to db
    $db = new DB_CONNECT;
	
	//$con = mysqli_connect("mysql-gatorevents-db.ufgatorevents.com", "gatorevents_db", "ic3_ZVSGCA", "gatorevents_db")
	//	or die(mysqli_connect_error());
 
    // mysql inserting a new row
    $result = mysqli_query($db->connect(), "INSERT INTO Users (Name, Email) VALUES ('$name', '$email')");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Users successfully created.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
		
		//check the status
		//$response["name"]=$name;
		//$response["email"]=$email;
		//$response["connection"]=$con;
		//$response["result"]=$result;
		
        // echoing JSON response
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