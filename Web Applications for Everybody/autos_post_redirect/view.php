<?php
	session_start();
	require_once "pdo.php";
	if ( ! isset($_SESSION['email']) || strlen($_SESSION['email']) < 1  ) {
		die('Not logged in');
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
	if (isset($_SESSION['email'])) {
		echo "<h1>Tracking Autos for: ";
		echo htmlentities($_SESSION['email']);
		echo "</h1>\n";
	}
	if(isset($_SESSION['success'])){
		echo('<p style="color: green">'.htmlentities($_SESSION['success'])."</p>\n");
		unset($_SESSION['success']);
	}
	
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

<p><a href="add.php">Add New</a>|<a href="logout.php">Logout</a></p>

</div>
</body>
</html>