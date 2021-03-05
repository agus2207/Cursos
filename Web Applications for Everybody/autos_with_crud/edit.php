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
	
	if (isset($_POST['make']) && isset($_POST['year']) && isset($_POST['mileage']) && isset($_POST['model']) && isset($_POST['auto_id'])) {
		if ( strlen($_POST['make']) < 1 || strlen($_POST['model']) < 1 || strlen($_POST['year']) < 1 || strlen($_POST['mileage']) < 1) {
			$_SESSION["error"] = "All fields are required";
			header('Location: edit.php?autos_id='.$_POST['auto_id']);
			return;
		} 
		else if(!is_numeric($_POST['year']) || !is_numeric($_POST['mileage'])){
			$_SESSION["error"] = "Mileage and year must be numeric";
			header('Location: edit.php?autos_id='.$_POST['auto_id']);
			return;
		}
		else {
			$stmt = $pdo->prepare('Update autos set make = :mk, model = :md, year = :yr, mileage = :mi 
				where autos_id = :a_id');
			$stmt->execute(array(
				':mk' => htmlentities($_POST['make']),
				':md' => htmlentities($_POST['model']),
				':yr' => htmlentities($_POST['year']),
				':mi' => htmlentities($_POST['mileage']),
				':a_id' => htmlentities($_POST['auto_id']))
			);
			$_SESSION["success"] = "Record edited";
			header('Location: index.php');
			return;
		}
	}
	
	if(! isset($_GET['autos_id'])){
		$_SESSION['error'] = "Missing autos_id";
		header('Location: index.php');
		return;
	}
	
	$stmt = $pdo->prepare('Select * from autos where autos_id = :id');
	$stmt->execute(array(':id' => htmlentities($_GET['autos_id'])));
	$row = $stmt->fetch(PDO::FETCH_ASSOC);
	if($row === false){
		$_SESSION['error'] = "Bad value for autos_id";
		header('Location: index.php');
		return;
	}
	
	$m = htmlentities($row['make']);
	$n = htmlentities($row['model']);
	$o = htmlentities($row['year']);
	$p = htmlentities($row['mileage']);
	$q = htmlentities($row['autos_id']);
?>
<!DOCTYPE html>
<html>
<head>
<title>Agustin Galindo Reyes 914a7f31</title>
<?php require_once "bootstrap.php"; ?>
</head>
<body>
<div class="container">
<h1>Editing Automobile</h1> 
<?php
	if(isset($_SESSION["error"])){
		echo('<p style="color: red">'.htmlentities($_SESSION['error'])."</p>\n");
		unset($_SESSION['error']);
	}
	
?>
<form method="POST">
<p><label for="nam">Make:</label>
<input type="text" name="make" id="nam" value="<?= $m ?>"></p>
<p><label for="mod">Model:</label>
<input type="text" name="model" id="mod" value="<?= $n ?>"></p>
<p><label for="yea">Year:</label>
<input type="text" name="year" id="yea" value="<?= $o ?>"></p>
<p><label for="mil">Mileage:</label>
<input type="text" name="mileage" id="mil" value="<?= $p ?>"></p>
<input type="hidden" name="auto_id" value="<?= $q ?>">
<input type="submit" value="Save">
<input type="submit" name="cancel" value="Cancel">
</form>

</div>
</body>