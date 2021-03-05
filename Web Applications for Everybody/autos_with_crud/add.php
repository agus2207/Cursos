<?php
	session_start();
	require_once "pdo.php";
	if ( ! isset($_SESSION['email']) || strlen($_SESSION['email']) < 1  ) {
		die("ACCESS DENIED");
	}
	
	if ( isset($_POST['cancel'] ) ) {
		// Redirect the browser to autos.php
		header("Location: index.php");
		return;
	}
	
	if (isset($_POST['make']) && isset($_POST['year']) && isset($_POST['mileage']) && isset($_POST['model'])) {
		if ( strlen($_POST['make']) < 1 || strlen($_POST['model']) < 1 || strlen($_POST['year']) < 1 || strlen($_POST['mileage']) < 1) {
			$_SESSION["error"] = "All fields are required";
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
				(make, model, year, mileage) VALUES ( :mk, :md, :yr, :mi)');
			$stmt->execute(array(
				':mk' => htmlentities($_POST['make']),
				':md' => htmlentities($_POST['model']),
				':yr' => htmlentities($_POST['year']),
				':mi' => htmlentities($_POST['mileage']))
			);
			$_SESSION["success"] = "Record added";
			header('Location: index.php');
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
<p><label for="nam">Make:</label>
<input type="text" name="make" id="nam"></p>
<p><label for="mod">Model:</label>
<input type="text" name="model" id="mod"></p>
<p><label for="yea">Year:</label>
<input type="text" name="year" id="yea"></p>
<p><label for="mil">Mileage:</label>
<input type="text" name="mileage" id="mil"></p>
<input type="submit" value="Add">
<input type="submit" name="cancel" value="Cancel">
</form>

</div>
</body>