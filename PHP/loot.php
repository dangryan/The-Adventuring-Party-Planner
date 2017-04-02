<?php

//Created by Josh Richardson 4/2/17

$con = $con = mysql_connect("db.soic.indiana.edu", "caps16_team39", "chocolatehamburgers12");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }
mysql_select_db("caps16_team39", $con);

$rarity = urldecode($_GET['rarity']);



if ($rarity != "null"){

	$statement = "SELECT * FROM MagicItem WHERE rarity= '".$rarity."'";

}
else{

	$statement = "SELECT * FROM MagicItem";
}

$statement .= " ORDER BY RAND() LIMIT 1";


$result = mysql_query($statement);
while($row = mysql_fetch_assoc($result))
  {
	$output[]=$row;
  }
print(json_encode($output));
mysql_close($con);
?>
