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
	
	if (isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['email']) && isset($_POST['headline']) && isset($_POST['summary'])
			&& isset($_SESSION['user_id'])) {
				
		if ( strlen($_POST['first_name']) < 1 || strlen($_POST['last_name']) < 1 || strlen($_POST['email']) < 1 || strlen($_POST['headline']) < 1 ||
				strlen($_POST['summary']) < 1) {
					
			$_SESSION["error"] = "All fields are required";
			header('Location: add.php');
			return;
		}
		else if(strpos($_POST['email'], '@') === false){
			$_SESSION["error"] = "Email must have an at-sign (@)";
			header('Location: add.php');
			return;
		}
		else {
			$stmt = $pdo->prepare('INSERT INTO Profile
			  (user_id, first_name, last_name, email, headline, summary)
			  VALUES ( :uid, :fn, :ln, :em, :he, :su)');

			$stmt->execute(array(
			  ':uid' => htmlentities($_SESSION['user_id']),
			  ':fn' => htmlentities($_POST['first_name']),
			  ':ln' => htmlentities($_POST['last_name']),
			  ':em' => htmlentities($_POST['email']),
			  ':he' => htmlentities($_POST['headline']),
			  ':su' => htmlentities($_POST['summary']))
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
	if (isset($_SESSION['name'])) {
		echo "<h1>Adding Profile for: ";
		echo htmlentities($_SESSION['name']);
		echo "</h1>\n";
	}
	if(isset($_SESSION["error"])){
		echo('<p style="color: red">'.htmlentities($_SESSION['error'])."</p>\n");
		unset($_SESSION['error']);
	}
	
?>

<form method="POST">
<p><label for="fn">First Name:</label>
<input type="text" name="first_name" id="fn"></p>
<p><label for="ln">Last Name:</label>
<input type="text" name="last_name" id="ln"></p>
<p><label for="em">Email:</label>
<input type="text" name="email" id="em"></p>
<p><label for="hl">Headline:</label>
<input type="text" name="headline" id="hl"></p>
<p><label for="sm">Summary:</label>
<textarea rows="4" cols="50" name="summary" id="sm"></textarea></p>
<input type="submit" value="Add">
<input type="submit" name="cancel" value="Cancel">
</form>

</div>
</body>