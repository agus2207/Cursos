<!--
In this assignment you will write a guessing game in PHP that takes GET parameters and plays the game.
-->

<html>
<head>
<title>Agustin Galindo Reyes 4d9c5f22</title>
</head>
<body>
<h1>Welcome to my guessing game</h1>
<p>
<?php
  if ( ! isset($_GET['guess']) ) { 
    echo("Missing guess parameter");
  } else if ( strlen($_GET['guess']) < 1 ) {
    echo("Your guess is too short");
  } else if ( ! is_numeric($_GET['guess']) ) {
    echo("Your guess is not a number");
  } else if ( $_GET['guess'] < 14 ) {
    echo("Your guess is too low");
  } else if ( $_GET['guess'] > 14 ) {
    echo("Your guess is too high");
  } else {
    echo("Congratulations - You are right");
  }
?>
</p>
</body>
</html>