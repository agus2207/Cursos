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
	
	if (isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['email']) && isset($_POST['headline']) && isset($_POST['summary']) &&
		 isset($_POST['profile_id']) && isset($_SESSION['user_id'])) {
				
		if ( strlen($_POST['first_name']) < 1 || strlen($_POST['last_name']) < 1 || strlen($_POST['email']) < 1 || strlen($_POST['headline']) < 1 ||
				strlen($_POST['summary']) < 1) {
					
			$_SESSION["error"] = "All fields are required";
			header('Location: edit.php?profile_id='.$_POST['profile_id']);
			return;
		} 
		else if(strpos($_POST['email'], '@') === false){
			$_SESSION["error"] = "Email must have an at-sign (@)";
			header('Location: edit.php?profile_id='.$_POST['profile_id']);
			return;
		}
		else {
			$stmt = $pdo->prepare('Select * from profile where profile_id = :p_id and user_id = :u_id');
			$stmt->execute(array('p_id' => htmlentities($_POST['profile_id']), ':u_id' => htmlentities($_SESSION['user_id'])));
			$row = $stmt->fetch(PDO::FETCH_ASSOC);
			if($row === false){
				$_SESSION["error"] = "Invalid user_id";
				header('Location: index.php');
				return;
			}
			else{
				$stmt = $pdo->prepare('Update profile set first_name = :fn, last_name = :ln, email = :em, headline = :hl, summary = :sm
					where profile_id = :a_id');
				$stmt->execute(array(
					':fn' => htmlentities($_POST['first_name']),
					':ln' => htmlentities($_POST['last_name']),
					':em' => htmlentities($_POST['email']),
					':hl' => htmlentities($_POST['headline']),
					':sm' => htmlentities($_POST['summary']),
					':a_id' => htmlentities($_POST['profile_id']))
				);
				$_SESSION["success"] = "Record edited";
				header('Location: index.php');
				return;
			}
			/*$stmt = $pdo->prepare('Select user_id from profile where profile_id = :p_id');
			$stmt->execute(array('p_id' => htmlentities($_POST['profile_id']));
			$row = $stmt->fetch(PDO::FETCH_ASSOC);
			if($row['user_id'] == htmlentities($_SESSION['user_id'])){
				$stmt = $pdo->prepare('Update profile set first_name = :fn, last_name = :ln, email = :em, headline = :hl, summary = :sm
					where profile_id = :a_id');
				$stmt->execute(array(
					':fn' => htmlentities($_POST['first_name']),
					':ln' => htmlentities($_POST['last_name']),
					':em' => htmlentities($_POST['email']),
					':hl' => htmlentities($_POST['headline']),
					':sm' => htmlentities($_POST['summary'])),
					':a_id' => htmlentities($_POST['profile_id']))
				);
				$_SESSION["success"] = "Record edited";
				header('Location: index.php');
				return;
			}
			else{
				$_SESSION["error"] = "Invalid user_id";
				header('Location: index.php');
				return;
			}*/
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
	
	$fn = htmlentities($row['first_name']);
	$ln = htmlentities($row['last_name']);
	$em = htmlentities($row['email']);
	$hl = htmlentities($row['headline']);
	$sm = htmlentities($row['summary']);
	$a_id = htmlentities($row['profile_id']);
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
		echo "<h1>Editing Profile for for: ";
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
<input type="text" name="first_name" id="fn" value="<?= $fn ?>"></p>
<p><label for="ln">Last Name:</label>
<input type="text" name="last_name" id="ln" value="<?= $ln ?>"></p>
<p><label for="em">Email:</label>
<input type="text" name="email" id="em" value="<?= $em ?>"></p>
<p><label for="hl">Headline:</label>
<input type="text" name="headline" id="hl" value="<?= $hl ?>"></p>
<p><label for="sm">Summary:</label>
<textarea rows="4" cols="50" name="summary" id="sm"><?= $sm ?></textarea></p>
<input type="hidden" name="profile_id" value="<?= $a_id ?>">
<input type="submit" value="Save">
<input type="submit" name="cancel" value="Cancel">
</form>

</div>
</body>