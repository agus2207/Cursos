<?php
	session_start();
	require_once "pdo.php";
	require_once "util.php";
	if ( ! isset($_SESSION['name']) || strlen($_SESSION['name']) == 0) {
		die("ACCESS DENIED");
	}
	
	if ( isset($_POST['cancel'] ) ) {
		// Redirect the browser to autos.php
		header("Location: index.php");
		return;
	}
	
	if (isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['email']) && isset($_POST['headline']) && isset($_POST['summary'])
		&& isset($_SESSION['user_id'])) {
				
		$msg = validateProfile();
		if(is_string($msg)){
			$_SESSION["error"] = $msg;
			header('Location: add.php');
			return;
		}
		
		$msg = validatePos();
		if(is_string($msg)){
			$_SESSION["error"] = $msg;
			header('Location: add.php');
			return;
		}
		
		$msg = validateEdu();
		if(is_string($msg)){
			$_SESSION["error"] = $msg;
			header('Location: add.php');
			return;
		}
		
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
		$profile_id = $pdo->lastInsertId();
		
		insertPositions($pdo, $profile_id);
		insertEducations($pdo, $profile_id);
		
		$_SESSION["success"] = "Record added";
		header('Location: index.php');
		return;
	}
?>
<!DOCTYPE html>
<html>
<head>
<title>Agustin Galindo Reyes 914a7f31</title>
<?php require_once "head.php"; ?>
</head>
<body>
<div class="container">
<?php
	if (isset($_SESSION['name'])) {
		echo "<h1>Adding Profile for: ";
		echo htmlentities($_SESSION['name']);
		echo "</h1>\n";
	}
	
	flashMessages();
	
?>

<form method="POST">
<p>First Name:
<input type="text" name="first_name" size="60"/></p>
<p>Last Name:
<input type="text" name="last_name" size="60"/></p>
<p>Email:
<input type="text" name="email" size="30"/></p>
<p>Headline:<br/>
<input type="text" name="headline" size="80"/></p>
<p>Summary:<br/>
<textarea name="summary" rows="8" cols="80"></textarea>
<p>
Education: <input type="submit" id="addEdu" value="+">
<div id="edu_fields">
</div>
</p>
<p>
Position: <input type="submit" id="addPos" value="+">
<div id="position_fields">
</div>
</p>
<p>
<input type="submit" value="Add">
<input type="submit" name="cancel" value="Cancel">
</p>
</form>
<script>
countPos = 0;
countEdu = 0;

// http://stackoverflow.com/questions/17650776/add-remove-html-inside-div-using-javascript
$(document).ready(function(){
    window.console && console.log('Document ready called');
    $('#addPos').click(function(event){
        // http://api.jquery.com/event.preventdefault/
        event.preventDefault();
        if ( countPos >= 9 ) {
            alert("Maximum of nine position entries exceeded");
            return;
        }
        countPos++;
        window.console && console.log("Adding position "+countPos);
        $('#position_fields').append(
            '<div id="position'+countPos+'"> \
            <p>Year: <input type="text" name="year'+countPos+'" value="" /> \
            <input type="button" value="-" \
                onclick="$(\'#position'+countPos+'\').remove();return false;"></p> \
            <textarea name="desc'+countPos+'" rows="8" cols="80"></textarea>\
            </div>');
    });
	    $('#addEdu').click(function(event){
        event.preventDefault();
        if ( countEdu >= 9 ) {
            alert("Maximum of nine education entries exceeded");
            return;
        }
        countEdu++;
        window.console && console.log("Adding education "+countEdu);

        $('#edu_fields').append(
            '<div id="edu'+countEdu+'"> \
            <p>Year: <input type="text" name="edu_year'+countEdu+'" value="" /> \
            <input type="button" value="-" onclick="$(\'#edu'+countEdu+'\').remove();return false;"><br>\
            <p>School: <input type="text" size="80" name="edu_school'+countEdu+'" class="school" value="" />\
            </p></div>'
        );

        $('.school').autocomplete({
            source: "school.php"
        });

    });
});
</script>
</div>
</body>