<?php
$con = $con = mysql_connect("db.soic.indiana.edu", "caps16_team39", "chocolatehamburgers12");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }
mysql_select_db("caps16_team39", $con);





$statement = "SELECT DISTINCT cr FROM Enemy ORDER BY cr desc";






$result = mysql_query($statement);
while($row = mysql_fetch_assoc($result))
  {
	$output[]=$row;
  }
print(json_encode($output));
mysql_close($con);
?>