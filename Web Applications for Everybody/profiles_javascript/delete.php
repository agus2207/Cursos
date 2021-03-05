<?php
	session_start();
	require_once "pdo.php";
	if ( ! isset($_SESSION['name']) || strlen($_SESSION['name']) < 1  ) {
		die("ACCESS DENIED");
	}
	
	if ( isset($_POST['cancel'] ) ) {
		// Redirect the browser to autos.php
		header("Location: index.php");
		return;
	}
	
	if(isset($_POST['delete']) && isset($_POST['profile_id'])){
		$stmt = $pdo->prepare('Select * from profile where profile_id = :p_id and user_id = :u_id');
		$stmt->execute(array('p_id' => htmlentities($_POST['profile_id']), ':u_id' => htmlentities($_SESSION['user_id'])));
		$row = $stmt->fetch(PDO::FETCH_ASSOC);
		if($row === false){
			$_SESSION['error'] = "Invalid user_id";
			header('Location: index.php');
			return;
		}
		else{
			$stmt = $pdo->prepare('delete from profile where profile_id = :zip and user_id = :uid');
			$stmt->execute(array(':zip' => htmlentities($_POST['profile_id']), ':uid' => htmlentities($_SESSION['user_id'])));
			$_SESSION['success'] = "Record deleted";
			header('Location: index.php');
			return;
		}
	}
	
	if(! isset($_GET['profile_id'])){
		$_SESSION['error'] = "Missing profile_id";
		header('Location: index.php');
		return;
	}
	
	$stmt = $pdo->prepare('Select * from profile where profile_id = :id');
	$stmt->execute(array(':id' => htmlentities($_GET['profile_id'])));
	$row = $stmt->fetch(PDO::FETCH_ASSOC);
	if($row === false){
		$_SESSION['error'] = "Bad value for profile_id";
		header('Location: index.php');
		return;
	}
	$m = htmlentities($row['first_name']);
	$n = htmlentities($row['last_name']);
	$o = htmlentities($row['profile_id']);
?>
<!DOCTYPE html>
<html>
<head>
<title>Agustin Galindo Reyes 914a7f31</title>
<?php require_once "bootstrap.php"; ?>
</head>
<body>
<div class="container">
<h1>Deleteing Profile</h1>
<p>First Name: <?= $m ?></p>
<p>Last Name: <?= $n ?></p>
<form method="POST">
<input type="hidden" name="profile_id" value="<?= $o ?>">
<input type="submit" name="delete" value="Delete">
<input type="submit" name="cancel" value="Cancel">
</form>

</div>
</body>