<?php
	function flashMessages(){
		if(isset($_SESSION['error'])){
			echo('<p style="color:red">'.htmlentities($_SESSION["error"])."</p>\n");
			unset($_SESSION["error"]);
		}
		if(isset($_SESSION["success"])){
			echo('<p style="color:green">'.htmlentities($_SESSION["success"])."</p>\n");
			unset($_SESSION["success"]);
		}
	}
	
	function validateProfile(){
		if (strlen($_POST['first_name']) == 0 || strlen($_POST['last_name']) == 0 || strlen($_POST['email']) == 0 || strlen($_POST['headline']) == 0 ||
			strlen($_POST['summary']) == 0) {
					
			return "All fields are required";
			
		}
		else if(strpos($_POST['email'], '@') === false){
			return "Email must have an at-sign (@)";
		}
		return true;
	}
	
	function validatePos(){
		for($i = 1; $i <= 9; $i++){
			if(!isset($_POST['year'.$i])) continue;
			if(!isset($_POST['desc'.$i])) continue;
			$year = $_POST['year'.$i];
			$desc = $_POST['desc'.$i];
			if(strlen($year) == 0 || strlen($desc) == 0){
				return "All fields are required";
			}
			if(!is_numeric($year)){
				return "Year must be numeric";
			}
		}
		return true;
	}
	
	function validateEdu(){
		for($i = 1; $i <= 9; $i++){
			if(!isset($_POST['edu_year'.$i])) continue;
			if(!isset($_POST['edu_school'.$i])) continue;
			$year = $_POST['edu_year'.$i];
			$school = $_POST['edu_school'.$i];
			if(strlen($year) == 0 || strlen($school) == 0){
				return "All fields are required";
			}
			if(!is_numeric($year)){
				return "Year must be numeric";
			}
		}
		return true;
	}
	
	function loadPos($pdo, $profile_id){
		$stmt = $pdo->prepare('Select * from position where profile_id = :prof order by rank');
		$stmt->execute(array(':prof' => $profile_id));
		$positions = $stmt->fetchAll(PDO::FETCH_ASSOC);
		return $positions;
	}
	
	function loadEdu($pdo, $profile_id){
		$stmt = $pdo->prepare('SELECT year, name FROM education JOIN institution
			ON education.institution_id = institution.institution_id WHERE 
			profile_id = :prof ORDER BY rank');
		$stmt->execute(array(':prof' => $profile_id));
		$educations = $stmt->fetchAll(PDO::FETCH_ASSOC);
		return $educations;
	}
	
	function insertPositions($pdo, $profile_id){
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
				':pid' => htmlentities($profile_id),
				':rank' => htmlentities($rank),
				'year' => htmlentities($year),
				'desc' => htmlentities($desc)
			));
			$rank++;
		}
	}
	
	function insertEducations($pdo, $profile_id){
		$rank = 1;
		for($i = 1; $i <= 9; $i++){
			if(!isset($_POST['edu_year'.$i])) continue;
			if(!isset($_POST['edu_school'.$i])) continue;
			$year = $_POST['edu_year'.$i];
			$school = $_POST['edu_school'.$i];
			
			$institucion_id = false;
			$stmt = $pdo->prepare('SELECT institution_id FROM institution
				WHERE name = :name');
			$stmt->execute(array(':name' => $school));
			$row = $stmt->fetch(PDO::FETCH_ASSOC);
			if($row !== false){
				$institucion_id = $row['institution_id'];
			}
			if($institucion_id === false){
				$stmt = $pdo->prepare('INSERT INTO institution (name)
					VALUES (:name)');
				$stmt->execute(array(':name' => $school));
				$institucion_id = $pdo->lastInsertId();
			}
			
			$stmt = $pdo->prepare('INSERT INTO education (profile_id, rank, year, institution_id)
				VALUES (:pid, :rank, :year, :iid)');
			$stmt->execute(array(
				':pid' => htmlentities($profile_id),
				':rank' => htmlentities($rank),
				'year' => htmlentities($year),
				'iid' => htmlentities($institucion_id)
			));
			$rank++;
		}
	}
	
	