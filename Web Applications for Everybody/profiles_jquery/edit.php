<?php
	session_start();
	require_once "pdo.php";
	require_once "util.php";
	
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
		$_SESSION["error"] = "Could not load the profile";
		header('Location: index.php');
		return;
	}
	
	if (isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['email']) && isset($_POST['headline']) && isset($_POST['summary'])) {
				
		$msg = validateProfile();
		if(is_string($msg)){
			$_SESSION["error"] = $msg;
			header('Location: edit.php?profile_id='.$_REQUEST['profile_id']);
			return;
		}
		
		$msg = validatePos();
		if(is_string($msg)){
			$_SESSION["error"] = $msg;
			header('Location: edit.php?profile_id='.$_REQUEST['profile_id']);
			return;
		}
		
		$stmt = $pdo->prepare('Update profile set first_name = :fn, last_name = :ln, email = :em, headline = :hl, summary = :sm
			where profile_id = :a_id and user_id = :uid');
		$stmt->execute(array(
			':fn' => htmlentities($_POST['first_name']),
			':ln' => htmlentities($_POST['last_name']),
			':em' => htmlentities($_POST['email']),
			':hl' => htmlentities($_POST['headline']),
			':sm' => htmlentities($_POST['summary']),
			':a_id' => htmlentities($_REQUEST['profile_id']),
			'uid' => htmlentities($_SESSION['user_id']))
		);
		
		$stmt = $pdo->prepare('DELETE FROM position where profile_id = :pid');
		$stmt->execute(array(':pid' => htmlentities($_REQUEST['profile_id'])));
		
		$rank = 1;
		for($i = 1; $i <= 9; $i++){
			if(!isset($_POST['year'.$i])) continue;
			if(!isset($_POST['desc'.$i])) continue;
			$year = $_POST['year'.$i];
			$desc = $_POST['desc'.$i];
			
			$stmt = $pdo->prepare('INSERT INTO Position
				(profile_id, rank, year, description)
				VALUES (:pid, :rank, :year, :desc)');
			$stmt->execute(array(
				':pid' => htmlentities($_REQUEST['profile_id']),
				':rank' => htmlentities($rank),
				'year' => htmlentities($year),
				'desc' => htmlentities($desc)
			));
			$rank++;
		}
		
		$_SESSION["success"] = "Profile Updated";
		header('Location: index.php');
		return;
	}
	$positions = loadPos($pdo, $_REQUEST['profile_id']);
?>
<!DOCTYPE html>
<html>
<head>
<title>Agustin Galindo Reyes 914a7f31</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	
	flashMessages();
	
?>
<form method="POST" action="edit.php">
<input type="hidden" name="profile_id" value="<?= htmlentities($_GET['profile_id']) ?>"/>
<p>First Name:
<input type="text" name="first_name" size="60" value="<?= htmlentities($profile['first_name']) ?>"/></p>
<p>Last Name:
<input type="text" name="last_name" size="60" value="<?= htmlentities($profile['last_name']) ?>"/></p>
<p>Email:
<input type="text" name="email" size="30" value="<?= htmlentities($profile['email']) ?>"/></p>
<p>Headline:<br/>
<input type="text" name="headline" size="80" value="<?= htmlentities($profile['headline']) ?>"/></p>
<p>Summary:<br/>
<textarea name="summary" rows="8" cols="80"><?= htmlentities($profile['summary']) ?></textarea></p>

<?php
	$pos = 0;
	echo('<p>Position: <input type="submit" id="addPos" value="+">'."\n");
	echo('<div id="position_fields">'."\n");
	foreach($positions as $position){
		$pos++;
		echo('<div id="position'.$pos.'">'."\n");
		echo('<p>Year: <input type="text" name="year'.$pos.'"');
		echo(' value="'.$position['year'].'" />'."\n");
		echo('<input type="button" value="-" ');
		echo('onclick="$(\'#position'.$pos.'\').remove();return false;">'."\n");
		echo("</p>\n");
		echo('<textarea name="desc'.$pos.'" rows="8" cols="80">'."\n");
		echo(htmlentities($position['description'])."\n");
		echo("\n</textarea>\n</div>\n");
	}
	echo("</div></p>\n");
?>
<p>
<input type="submit" value="Save">
<input type="submit" name="cancel" value="Cancel">
</p>
</form>
<script>
countPos = <?= $pos ?>;

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
});
</script>
</div>
</body>
</html>