<?php
	session_start();
	require_once "pdo.php";
	if ( ! isset($_SESSION['email']) || strlen($_SESSION['email']) < 1  ) {
		die('Not logged in');
	}
	
	if ( isset($_POST['cancel'] ) ) {
		// Redirect the browser to autos.php
		header("Location: view.php");
		return;
	}
	
	if (isset($_POST['make']) && isset($_POST['year']) && isset($_POST['mileage'])) {
		if ( strlen($_POST['make']) < 1) {
			$_SESSION["error"] = "Make is required";
			header('Location: add.php');
			return;
		} 
		else if(!is_numeric($_POST['year']) || !is_numeric($_POST['mileage'])){
			$_SESSION["error"] = "Mileage and year must be numeric";
			header('Location: add.php');
			return;
		}
		else {
			$stmt = $pdo->prepare('INSERT INTO autos
				(make, year, mileage) VALUES ( :mk, :yr, :mi)');
			$stmt->execute(array(
				':mk' => htmlentities($_POST['make']),
				':yr' => htmlentities($_POST['year']),
				':mi' => htmlentities($_POST['mileage']))
			);
			$_SESSION["success"] = "Record inserted";
			header('Location: view.php');
			return;
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
	if (isset($_SESSION['email'])) {
		echo "<h1>Tracking Autos for: ";
		echo htmlentities($_SESSION['email']);
		echo "</h1>\n";
	}
	if(isset($_SESSION["error"])){
		echo('<p style="color: red">'.htmlentities($_SESSION['error'])."</p>\n");
		unset($_SESSION['error']);
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
<input type="submit" name="cancel" value="Cancel">
</form>

</div>
</body>