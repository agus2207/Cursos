<?php
	session_start();
	require_once "pdo.php";
	require_once "util.php";
	
	$stmt = $pdo->query("Select first_name, last_name, headline, profile_id from profile");
	$row = $stmt->fetchAll(PDO::FETCH_ASSOC);
?>
<!DOCTYPE html>
<html>
<head>
<title>Agustin Galindo Reyes 914a7f31</title>
<?php require_once "head.php"; ?>
</head>
<body>
<div class="container">
<h1>Welcome to Profiles Views</h1>
<?php
	if(!isset($_SESSION["name"])){ ?>
		<p>
		<a href="login.php">Please log in</a>
		</p>
<?php
		$stmt = $pdo->query("Select first_name, last_name, headline, profile_id from profile");
		if($row = $stmt->fetch(PDO::FETCH_ASSOC) === false){
			echo "<p>No rows found</p>";
		}
		else{
			echo('<table border="1">'."\n");
			echo "<tr><td>Name</td><td>Headline</td></tr>";
			$stmt = $pdo->query("Select first_name, last_name, headline, profile_id from profile");
			while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
				echo "<tr><td>";
				echo('<a href="view.php?profile_id='.$row['profile_id'].'">'.htmlentities($row['first_name']).' '.htmlentities($row['last_name']).'</a>');
				echo "</td><td>";
				echo (htmlentities($row['headline']));
				echo "</td></tr>\n";
			}
			echo "</table>";
		}
	}
	else{
		flashMessages();
		$stmt = $pdo->query("Select first_name, last_name, headline, profile_id from profile");
		if($row = $stmt->fetch(PDO::FETCH_ASSOC) === false){
			echo "<p>No rows found</p>";
		}
		else{
			echo('<table border="1">'."\n");
			echo "<tr><td>Name</td><td>Headline</td><td>Actions</td></tr>";
			$stmt = $pdo->query("Select first_name, last_name, headline, profile_id from profile");
			while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
				echo "<tr><td>";
				echo('<a href="view.php?profile_id='.$row['profile_id'].'">'.htmlentities($row['first_name']).' '.htmlentities($row['last_name']).'</a>');
				echo "</td><td>";
				echo (htmlentities($row['headline']));
				echo "</td><td>";
				echo ('<a href="edit.php?profile_id='.$row['profile_id'].'">Edit</a>/ ');
				echo ('<a href="delete.php?profile_id='.$row['profile_id'].'">Delete</a> ');
				echo "</td></tr>\n";
			}
			echo "</table>";
		}
		echo ('<p><a href="add.php">Add New Entry</a></p>');
		echo ('<p><a href="logout.php">Logout</a></p>');
	}
?>
</div>
</body>