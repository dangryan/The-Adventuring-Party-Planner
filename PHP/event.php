<?php

//Created by Josh Richardson 4/4/17

$con = $con = mysql_connect("db.soic.indiana.edu", "caps16_team39", "chocolatehamburgers12");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }
mysql_select_db("caps16_team39", $con);

$type = $_GET['type'];

// Make if statements to decide what the statement will be based on type


if ($type == "Dungeon"){

	$statement = "SELECT (SELECT type FROM Dungeon WHERE type IS NOT NULL ORDER BY RAND() LIMIT 1) AS type,
	(SELECT origin FROM Dungeon WHERE origin IS NOT NULL ORDER BY RAND() LIMIT 1) AS origin,
	(SELECT location FROM Dungeon WHERE location IS NOT NULL ORDER BY RAND() LIMIT 1) AS location,
	(SELECT occupants FROM Dungeon WHERE occupants IS NOT NULL ORDER BY RAND() LIMIT 1) AS occupants,
	(SELECT additional_occupants FROM Dungeon WHERE additional_occupants IS NOT NULL ORDER BY RAND() LIMIT 1) AS additional_occupants,
	(SELECT challenge FROM Dungeon WHERE challenge IS NOT NULL ORDER BY RAND() LIMIT 1) AS challenge,
	(SELECT loot FROM Dungeon WHERE loot IS NOT NULL ORDER BY RAND() LIMIT 1) AS loot;"
}

elseif ($type == "Rumor"){
	
	$statement = "SELECT (SELECT time FROM Rumor WHERE time IS NOT NULL ORDER BY RAND() LIMIT 1) AS time,
	(SELECT subject1 FROM Rumor WHERE subject1 IS NOT NULL ORDER BY RAND() LIMIT 1) AS subject1,
	(SELECT subject2 FROM Rumor WHERE subject2 IS NOT NULL ORDER BY RAND() LIMIT 1) AS subject2,
	(SELECT location FROM Rumor WHERE location IS NOT NULL ORDER BY RAND() LIMIT 1) AS location,
	(SELECT subject3 FROM Rumor WHERE subject3 IS NOT NULL ORDER BY RAND() LIMIT 1) AS subject3,
	(SELECT source FROM Rumor WHERE source IS NOT NULL ORDER BY RAND() LIMIT 1) AS source,
	(SELECT veracity FROM Rumor WHERE veracity IS NOT NULL ORDER BY RAND() LIMIT 1) AS veracity;"
	
}

elseif ($type == "Stranger"){
	
	$statement = "SELECT (SELECT age FROM Stranger WHERE age IS NOT NULL ORDER BY RAND() LIMIT 1) AS age,
	(SELECT sex FROM Stranger WHERE sex IS NOT NULL ORDER BY RAND() LIMIT 1) AS sex,
	(SELECT hair_type FROM Stranger WHERE hair_type IS NOT NULL ORDER BY RAND() LIMIT 1) AS hair_type,
	(SELECT hair_style FROM Stranger WHERE hair_style IS NOT NULL ORDER BY RAND() LIMIT 1) AS hair_style,
	(SELECT mark FROM Stranger WHERE mark IS NOT NULL ORDER BY RAND() LIMIT 1) AS mark,
	(SELECT home FROM Stranger WHERE home IS NOT NULL ORDER BY RAND() LIMIT 1) AS home,
	(SELECT home_location FROM Stranger WHERE home_location IS NOT NULL ORDER BY RAND() LIMIT 1) AS home_location,
	(SELECT occupation FROM Stranger WHERE occupation IS NOT NULL ORDER BY RAND() LIMIT 1) AS occupation,
	(SELECT renown FROM Stranger WHERE renown IS NOT NULL ORDER BY RAND() LIMIT 1) AS renown,
	(SELECT build FROM Stranger WHERE build IS NOT NULL ORDER BY RAND() LIMIT 1) AS build,
	(SELECT descriptor FROM Stranger WHERE descriptor IS NOT NULL ORDER BY RAND() LIMIT 1) AS descriptor,
	(SELECT speech FROM Stranger WHERE speech IS NOT NULL ORDER BY RAND() LIMIT 1) AS speech,
	(SELECT motive FROM Stranger WHERE motive IS NOT NULL ORDER BY RAND() LIMIT 1) AS motive;"
	
}

elseif ($type == "Villain"){
	
	$statement = "SELECT (SELECT origin FROM Villain WHERE origin IS NOT NULL ORDER BY RAND() LIMIT 1) AS origin,
	(SELECT time_turned FROM Villain WHERE time_turned IS NOT NULL ORDER BY RAND() LIMIT 1) AS time_turned,
	(SELECT time_to_turn FROM Villain WHERE time_to_turn IS NOT NULL ORDER BY RAND() LIMIT 1) AS time_to_turn,
	(SELECT reason_turned FROM Villain WHERE reason_turned IS NOT NULL ORDER BY RAND() LIMIT 1) AS reason_turned,
	(SELECT unique_trait FROM Villain WHERE unique_trait IS NOT NULL ORDER BY RAND() LIMIT 1) AS unique_trait,
	(SELECT goal FROM Villain WHERE goal IS NOT NULL ORDER BY RAND() LIMIT 1) AS goal,
	(SELECT opposition FROM Villain WHERE opposition IS NOT NULL ORDER BY RAND() LIMIT 1) AS opposition,
	(SELECT race FROM Villain WHERE race IS NOT NULL ORDER BY RAND() LIMIT 1) AS race,
	(SELECT leadership_quality FROM Villain WHERE leadership_quality IS NOT NULL ORDER BY RAND() LIMIT 1) AS leadership_quality,
	(SELECT minions FROM Villain WHERE minions IS NOT NULL ORDER BY RAND() LIMIT 1) AS minions,
	(SELECT redeeming_quality FROM Villain WHERE redeeming_quality IS NOT NULL ORDER BY RAND() LIMIT 1) AS redeeming_quality;"
	
}

elseif ($type == "Quest"){
	
	$statement = "SELECT (SELECT location FROM Quest WHERE location IS NOT NULL ORDER BY RAND() LIMIT 1) AS location,
	(SELECT task FROM Quest WHERE task IS NOT NULL ORDER BY RAND() LIMIT 1) AS task,
	(SELECT task_focus FROM Quest WHERE task_focus IS NOT NULL ORDER BY RAND() LIMIT 1) AS task_focus,
	(SELECT guarded_by FROM Quest WHERE guarded_by IS NOT NULL ORDER BY RAND() LIMIT 1) AS guarded_by,
	(SELECT alertness FROM Quest WHERE alertness IS NOT NULL ORDER BY RAND() LIMIT 1) AS alertness,
	(SELECT time_limit FROM Quest WHERE time_limit IS NOT NULL ORDER BY RAND() LIMIT 1) AS time_limit,
	(SELECT twist FROM Quest WHERE twist IS NOT NULL ORDER BY RAND() LIMIT 1) AS twist,
	(SELECT consequence FROM Quest WHERE consequence IS NOT NULL ORDER BY RAND() LIMIT 1) AS consequence;"
	
}

else{

	$statement = "SELECT (SELECT subject FROM VillageTask WHERE subject IS NOT NULL ORDER BY RAND() LIMIT 1) AS subject,
	(SELECT problem FROM VillageTask WHERE problem IS NOT NULL ORDER BY RAND() LIMIT 1) AS problem,
	(SELECT location FROM VillageTask WHERE location IS NOT NULL ORDER BY RAND() LIMIT 1) AS location,
	(SELECT clue FROM VillageTask WHERE clue IS NOT NULL ORDER BY RAND() LIMIT 1) AS clue,
	(SELECT suspect_it_involves FROM VillageTask WHERE suspect_it_involves IS NOT NULL ORDER BY RAND() LIMIT 1) AS suspect_it_involves,
	(SELECT impeder FROM VillageTask WHERE impeder IS NOT NULL ORDER BY RAND() LIMIT 1) AS impeder,
	(SELECT attempt_to_help FROM VillageTask WHERE attempt_to_help IS NOT NULL ORDER BY RAND() LIMIT 1) AS attempt_to_help,
	(SELECT reward FROM VillageTask WHERE reward IS NOT NULL ORDER BY RAND() LIMIT 1) AS reward;"
}

$result = mysql_query($statement);
while($row = mysql_fetch_assoc($result))
  {
	$output[]=$row;
  }
print(json_encode($output));
mysql_close($con);
?>