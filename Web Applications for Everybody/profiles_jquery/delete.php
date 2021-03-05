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
	
	if(!isset($_REQUEST['profile_id'])){
		$_SESSION["error"] = "Missing profile_id";
		header('Location: index.php');
		return;
	}
	
	$stmt = $pdo->prepare('Select * from profile where profile_id = :p_id and user_id = :u_id');
	$stmt->execute(array('p_id' => htmlentities($_REQUEST['profile_id']), ':u_id' => htmlentities($_SESSION['user_id'])));
	$profile = $stmt->fetch(PDO::FETCH_ASSOC);
	if($profile === false){
		$_SESSION["error"] = "Could not find the profile";
		header('Location: index.php');
		return;
	}
	
	if(isset($_POST['delete']) && isset($_POST['profile_id'])){		
		$stmt = $pdo->prepare('delete from profile where profile_id = :zip and user_id = :uid');
		$stmt->execute(array(':zip' => htmlentities($_POST['profile_id']), ':uid' => htmlentities($_SESSION['user_id'])));
		$_SESSION['success'] = "Record deleted";
		header('Location: index.php');
		return;
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
<h1>Deleteing Profile</h1>
<p>First Name: <?= htmlentities($profile['first_name']) ?></p>
<p>Last Name: <?= htmlentities($profile['last_name']) ?></p>
<form method="POST">
<input type="hidden" name="profile_id" value="<?= htmlentities($_GET['profile_id']) ?>">
<input type="submit" name="delete" value="Delete">
<input type="submit" name="cancel" value="Cancel">
</form>

</div>
</body>