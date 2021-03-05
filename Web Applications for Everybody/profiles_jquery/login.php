<?php
	session_start();
	require_once "pdo.php";
	require_once "util.php";
	if ( isset($_POST['cancel'] ) ) {
		// Redirect the browser to autos.php
		header("Location: index.php");
		return;
	}
	$salt = 'XyZzy12*_';
	
	if(isset($_POST["email"]) && isset($_POST["pass"])){
		unset($_SESSION["name"]);
		if ( strlen($_POST['email']) == 0 || strlen($_POST['pass']) == 0) {
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
			$stmt = $pdo->prepare('SELECT user_id, name FROM users WHERE email = :em AND password = :pw');
			$stmt->execute(array( ':em' => $_POST['email'], ':pw' => $check));
			$row = $stmt->fetch(PDO::FETCH_ASSOC);
			
			if ( $row !== false ) {
				$_SESSION['name'] = $row['name'];
				$_SESSION['user_id'] = $row['user_id'];
				$_SESSION["success"] = "Logged in.";
				// Redirect the browser to index.php
				header("Location: index.php");
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Agustin Galindo Reyes 914a7f31</title>
</head>
<body>
<div class="container">
<h1>Please Log In</h1>
<?php
	flashMessages();
?>
<form method="POST">
<p>User Name: <input type="text" name="email" id="email" value=""></p>
<p>Password: <input type="password" name="pass" id="pass" value=""></p>
<p><input type="submit" onclick="return doValidate();" value="Log In">
<input type="submit" name="cancel" value="Cancel">
</form>
<p>
For a password hint, view source and find a password hint
in the HTML comments.
<!-- Hint: The password is the four character sound a cat
makes (all lower case) followed by 123. -->
</p>
<script>
function doValidate() {
    console.log('Validating...');
    try {
        addr = document.getElementById('email').value;
        pw = document.getElementById('pass').value;
        console.log("Validating addr="+addr+" pw="+pw);
        if (addr == null || addr == "" || pw == null || pw == "") {
            alert("Both fields must be filled out");
            return false;
        }
        if (addr.indexOf('@') == -1) {
            alert("Invalid email address");
            return false;
        }
        return true;
    } catch(e) {
        return false;
    }
    return false;
}
</script>
</div>
</body>