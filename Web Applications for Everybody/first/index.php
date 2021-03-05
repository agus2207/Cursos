<!--
In this next assignment, your application will be a single index.php file with some elements in HTML and some elements in PHP. 
This also verifies that you can do basic HTML and know how to use the 'pre' tag as well as understand how to switch between PHP and HTML 
when writing a PHP script.

Your name must be in the <title> tag like 'Charles Severance PHP'
There should be an <h1> tag with your name and text like 'Charles Severance PHP'
You should use a <pre> tag to create ASCII art of the first letter of your name four spaces in from the left margin
Your code should use PHP to compute the SHA256 of your name and print it out like the sample application. You must compute this in your index.php file. 
The PHP code to achieve this for 'Charles Severance' is:
print hash('sha256', 'Charles Severance');
-->

<!DOCTYPE html>
<html>
	<head>
	  <title>Agustin Galindo Reyes PHP</title>
	</head>
	<body>
		<h1>Agustin Galindo Reyes PHP</h1>
		<pre>ASCII ART:
	      ***
	     ** **
	    **   **
	   *********
	  ***********
	 **         **
	**           **
		</pre>
		<?php
			echo "The SHA256 hash of Agustin Galindo Reyes is ";
			print hash('sha256', 'Agustin Galindo Reyes');
		?>
		<br/>
		<a href="check.php">Click here to check the error setting</a>
		<br/>
		<a href="fail.php">Click here to cause a traceback</a>
	</body>
</html>