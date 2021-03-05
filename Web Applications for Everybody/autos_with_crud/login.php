<?php
	session_start();
	if ( isset($_POST['cancel'] ) ) {
		// Redirect the browser to autos.php
		header("Location: index.php");
		return;
	}
	$salt = 'XyZzy12*_';
	$stored_hash = '1a52e17fa899cf40fb04cfc42e6352f1';  // Pw is php123
	if(isset($_POST["email"]) && isset($_POST["pass"])){
		unset($_SESSION["email"]);
		if ( strlen($_POST['email']) < 1 || strlen($_POST['pass']) < 1 ) {
			$_SESSION["error"] = "User name and password are required";
			header('Location: login.php');
			return;
		} 
		else if(strpos($_POST['email'], '@') === false){
			$_SESSION["error"] = "Email must have an at-sign (@)";
			header('Location: login.php');
			return;
		}
		else{
			$check = hash('md5', $salt.$_POST['pass']);
			if($check == $stored_hash){
				$_SESSION["email"] = $_POST["email"];
				$_SESSION["success"] = "Logged in.";
				error_log("Login success ".$_POST['email']);
				header('Location: index.php');
				return;
			}
			else{
				$_SESSION["error"] = "Incorrect password.";
				error_log("Login fail ".$_POST['email']." $check");
				header('Location: login.php');
				return;
			}
		}		
	}
?>
<!DOCTYPE html>
<html>
<head>
<?php require_once "bootstrap.php"; ?>
<title>Agustin Galindo Reyes 914a7f31</title>
</head>
<body>
<div class="container">
<h1>Please Log In</h1>
<?php
	if(isset($_SESSION["error"])){
		echo('<p style="color:red">'.htmlentities($_SESSION["error"])."</p>\n");
		unset($_SESSION["error"]);
	}
	if(isset($_SESSION["success"])){
		echo('<p style="color:green">'.htmlentities($_SESSION["success"])."</p>\n");
		unset($_SESSION["success"]);
	}
?>
<form method="POST">
<p>User Name: <input type="text" name="email" value=""></p>
<p>Password: <input type="text" name="pass" value=""></p>
<p><input type="submit" value="Log In">
<input type="submit" name="cancel" value="Cancel">
</form>
<p>
For a password hint, view source and find a password hint
in the HTML comments.
<!-- Hint: The password is the four character sound a cat
makes (all lower case) followed by 123. -->
</p>
</div>
</body>