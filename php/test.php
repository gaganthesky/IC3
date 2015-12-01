<?php
	//echo "Welcome, I am connecting Android";
	
	// include db connect class
    //require_once('db_connect.php');
 
    // connecting to db
    //$db = new DB_CONNECT();
	
	//echo "Finish";
	//$con = mysqli_connect("mysql-gatorevents-db.ufgatorevents.com", "gatorevents_db", "ic3_ZVSGCA", "gatorevents_db")
	//	or die(mysqli_connect_error());
		
	//mysqli_query($con, "INSERT INTO Users (Name, Email) VALUES ('rr', 'aa')");
	
	$t="1";

	if ($t=="1") {
	  echo "Have a good morning!";
	} elseif ($t!="1") {
	  echo "Have a good day!";
	}
		
?>