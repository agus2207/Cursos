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
<h1>Welcome to Autos Database</h1>
<?php
	if(!isset($_SESSION["email"])){ ?>
		<p>
		<a href="login.php">Please log in</a>
		</p>
		<p>
		Attempt to go to 
		<a href="add.php">add.php</a> without logging in - it should fail with an error message.</p>
<?php	
	}
	else{
		if(isset($_SESSION["error"])){
			echo('<p style="color:red">'.htmlentities($_SESSION["error"])."</p>\n");
			unset($_SESSION["error"]);
		}
		if(isset($_SESSION["success"])){
			echo('<p style="color:green">'.htmlentities($_SESSION["success"])."</p>\n");
			unset($_SESSION["success"]);
		}
		$stmt = $pdo->query("Select make, model, year, mileage, autos_id from autos");
		if($row = $stmt->fetch(PDO::FETCH_ASSOC) === false){
			echo "<p>No rows found</p>";
		}
		else{
			echo('<table border="1">'."\n");
			echo "<tr><td>Make</td><td>Model</td><td>Year</td><td>Mileage</td><td></td></tr>";
			$stmt = $pdo->query("Select make, model, year, mileage, autos_id from autos");
			while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
				echo "<tr><td>";
				echo(htmlentities($row['make']));
				echo "</td><td>";
				echo (htmlentities($row['model']));
				echo "</td><td>";
				echo (htmlentities($row['year']));
				echo "</td><td>";
				echo (htmlentities($row['mileage']));
				echo "</td><td>";
				echo ('<a href="edit.php?autos_id='.$row['autos_id'].'">Edit</a>/ ');
				echo ('<a href="delete.php?autos_id='.$row['autos_id'].'">Delete</a> ');
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
