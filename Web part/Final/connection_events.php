<?php


$servername = 	"mysql-gatorevents-db.ufgatorevents.com";
$username = 	"gatorevents_db";
$password = 	"ic3_ZVSGCA";

// Create connection
$conn = mysql_connect($servername, $username, $password);

// Check connection
if (!$conn) {
    die("Connection failed: " .  $conn->connect_error);
} 
echo "Connected successfully";

//define('db_name, 'gatorevents_db');
$db_selected = mysql_select_db(gatorevents_db, $conn);

if(!$db_selected)
{
	die('Can not use' . gatorevents_db . ': ' . mysql_errno());
}

$value1 = $_POST['events'];
$value2 = $_POST['date'];
$value3 = $_POST['organization'];
$value4 = $_POST['location'];
$value5 = $_POST['event_description'];


$sql = "INSERT INTO Events (E_name, Date, Organization, Venue, Description) VALUES ('$value1', '$value2', '$value3', '$value4', '$value5')";
//$sql = "INSERT INTO events_temp (date, organization, location, event_details) VALUES ('$value2', '$value3', '$value4', '$value5')";
//$sql = "INSERT INTO events_temp (event_name, date, organization, location, event_details) VALUES ('Gator Event', '2015-10-10', 'UFL', 'CISE E-213', 'A weekly workshop ')";

if(!mysql_query($sql))
{
	die('Error: '. mysql_error());
}

mysql_close();


?>







