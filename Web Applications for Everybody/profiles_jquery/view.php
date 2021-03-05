<?php
	session_start();
	require_once "pdo.php";
?>
<!DOCTYPE html>
<html>
<head>
<title>Agustin Galindo Reyes 914a7f31</title>
<?php require_once "bootstrap.php"; ?>
</head>
<body>
<div class="container">
<h1>Profile information</h1>
<?php
	$stmt = $pdo->prepare('Select * from profile where profile_id = :p_id');
	$stmt->execute(array(':p_id' => htmlentities($_GET['profile_id'])));
	$row = $stmt->fetch(PDO::FETCH_ASSOC);
	if($row === false){
		$_SESSION["error"] = "Could not load profile";
		header("Location: index.php");
		return;
	}
	echo('<p>First Name:'.htmlentities($row['first_name']).'</p>');
	echo('<p>Last Name:'.htmlentities($row['last_name']).'</p>');
	echo('<p>Email:'.htmlentities($row['email']).'</p>');
	echo('<p>Headline:'.htmlentities($row['headline']).'</p>');
	echo('<p>Summary:'.htmlentities($row['summary']).'</p>');
	echo('<p>Position</p>');
	
	$stmt = $pdo->prepare('Select year, description from position where profile_id = :p_id');
	$stmt->execute(array(':p_id' => htmlentities($_GET['profile_id'])));
	echo "<ul>";
	while($positions = $stmt->fetch(PDO::FETCH_ASSOC)){
		echo "<li>";
		echo($positions['year'].": ".$positions['description']);
		echo "</li>";
	}
	echo "</ul>";
	echo('<a href="index.php">Done</a>');
?>
</div>
</body>