<?php
	session_start();
?>
<!DOCTYPE html>
<html>
<head>
<title>Agustin Galindo Reyes 914a7f31</title>
<?php require_once "bootstrap.php"; ?>
</head>
<body>
<div class="container">
<h1>Welcome to Autos Database</h1>
<?php
	if(!isset($_SESSION["email"])){ ?>
		<p>
		<a href="login.php">Please Log In</a>
		</p>
<?php	
	}
	else{
		header("Location: view.php");
		return;
	}
?>
<p>
Attempt to go to 
<a href="view.php">view.php</a> without logging in - it should fail with an error message.</p>
<p>
Attempt to go to 
<a href="add.php">add.php</a> without logging in - it should fail with an error message.</p>
</div>
</body>
