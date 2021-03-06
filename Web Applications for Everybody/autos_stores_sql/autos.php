<!--
Your name must be in the title tag of the HTML for all of the pages for this assignment.
Your program must be resistant to HTML Injection attempts. All data that comes from the users must be properly escaped using 
the htmlentities() function in PHP. You do not need to escape text that is generated by your program.
Your program must be resistant to SQL Injection attempts. This means that you should never concatenate user provided data with SQL to produce a query. 
You should always use a PDO prepared statement.
Please do not use HTML5 in-browser data validation (i.e. type="number") for the fields in this assignment as we want to make sure you can properly
do server side data validation. And in general, even when you do client-side data validation, you should still validate data on the server in case 
the user is using a non-HTML5 browser.
-->

<?php

require_once "pdo.php";
// Demand a GET parameter
if ( ! isset($_GET['name']) || strlen($_GET['name']) < 1  ) {
    die('Name parameter missing');
}

// If the user requested logout go back to index.php
if ( isset($_POST['logout']) ) {
    header('Location: index.php');
    return;
}

$failure = false;

if (isset($_POST['make']) && isset($_POST['year']) && isset($_POST['mileage'])) {
    if ( strlen($_POST['make']) < 1) {
        $failure = "Make is required";
    } 
	else if(!is_numeric($_POST['year']) || !is_numeric($_POST['mileage'])){
		$failure = "Mileage and year must be numeric";
	}
	else {
        $stmt = $pdo->prepare('INSERT INTO autos
			(make, year, mileage) VALUES ( :mk, :yr, :mi)');
		$stmt->execute(array(
			':mk' => htmlentities($_POST['make']),
			':yr' => htmlentities($_POST['year']),
			':mi' => htmlentities($_POST['mileage']))
		);
		$failure = true;
    }
}

?>
<!DOCTYPE html>
<html>
<head>
<title>Agustin Galindo Reyes 914a7f31</title>
<?php require_once "bootstrap.php"; ?>
</head>
<body>
<div class="container">
<?php
if ( isset($_REQUEST['name']) ) {
    echo "<h1>Tracking Autos for: ";
    echo htmlentities($_REQUEST['name']);
    echo "</h1>\n";
}
if( $failure === true){
	echo('<p style="color: green;">'."Record inserted"."</p>\n");
}
else if ( $failure !== false ) {
    // Look closely at the use of single and double quotes
    echo('<p style="color: red;">'.htmlentities($failure)."</p>\n");
}

?>
<form method="POST">
<label for="nam">Make:</label>
<input type="text" name="make" id="nam"><br/>
<label for="yea">Year:</label>
<input type="text" name="year" id="yea"><br/>
<label for="mil">Mileage:</label>
<input type="text" name="mileage" id="mil"><br/>
<input type="submit" value="Add">
<input type="submit" name="logout" value="Logout">
</form>

<?php
	echo "<h1>Automobiles</h1>";
	$stmt = $pdo->query("Select make, year, mileage from autos");
	echo "<ul>";
	while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
		echo "<li>";
	echo($row['year']." ".$row['make']."/".$row['mileage']);
		echo "</li>";
	}
	echo "</ul>";
?>

</div>
</body>
</html>