<?php
 
/* Check if there is such a user called $name
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['name'])) {
 
    $name = $_POST['name'];
 
    // include db connect class
    require_once('db_connect.php');
	//require_once __DIR__. '/db_config.php';
 
    // connecting to db
    $db = new DB_CONNECT;
	
	//$con = mysqli_connect("mysql-gatorevents-db.ufgatorevents.com", "gatorevents_db", "ic3_ZVSGCA", "gatorevents_db")
	//	or die(mysqli_connect_error());
 
    // check 
    $result = mysqli_query($db->connect(), "SELECT COUNT(*) FROM Users WHERE Name = '$name' ");
	$getRes = mysqli_query($db->connect(),"SELECT U_id FROM Users WHERE Name = '$name' ");
	$getID = mysqli_fetch_row($getRes);
 
	if($result){
		$row = mysqli_fetch_row($result);
		if($row[0] == '1'){
			$response["success"]=1;
			$response["U_id"]=$getID[0];
			$response["message"]="Login Success!";
			
			echo json_encode($response);
		}
		else{
			$response["success"]=0;
			$response["message"]="No such a user!";
			
			echo json_encode($response);
		}
	}
	else{
		$response["success"] = 0;
		$response["message"] = "Operation Error!";
	 
		// echo no users JSON
		echo json_encode($response);
	}
}
else{
	$response["success"] = 0;
	$response["message"] = "POST error: No name column";
	 
	// echo no users JSON
	echo json_encode($response);
}
?>