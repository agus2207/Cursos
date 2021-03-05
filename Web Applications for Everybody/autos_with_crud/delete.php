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
	
	if(isset($_POST['delete']) && isset($_POST['auto_id'])){
		$stmt = $pdo->prepare('delete from autos where autos_id = :zip');
		$stmt->execute(array(':zip' => $_POST['auto_id']));
		$_SESSION['success'] = "Record deleted";
		header('Location: index.php');
		return;
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
	$n = htmlentities($row['autos_id']);
?>
<!DOCTYPE html>
<html>
<head>
<title>Agustin Galindo Reyes 914a7f31</title>
<?php require_once "bootstrap.php"; ?>
</head>
<body>
<div class="container">
<p>Confirm: Deleting <?= $m ?></p>

<form method="POST">
<input type="hidden" name="auto_id" value="<?= $n ?>">
<input type="submit" name="delete" value="Delete">
<input type="submit" name="cancel" value="Cancel">
</form>

</div>
</body>