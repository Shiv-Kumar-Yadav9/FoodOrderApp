<?php

	require_once "connect.php";
	
	if(!$con){
		echo "Database Connection Failed" ;
	} else{
		//if($_SERVER['HTTP_USER_AGENT']=="EatIt"){
		//	if($_SERVER["REQUEST_METHOD"]=="GET"){
				$dupe ="SELECT * FROM MAIN_COURSE";
				$dupe_results = mysqli_query($con,$dupe);
					$name = null;
					$shop = null;
					$cost = null;
					$t = 1;
					if(mysqli_num_rows($dupe_results)>0){
						$emparray = array();
						$parray = array();
						while($row =mysqli_fetch_assoc($dupe_results))
						{
							$parray["name"] = $row['Food_Name'];
							$parray["shop"] = $row['Shop'];
							$parray["cost"] = $row['Cost'];
							
							$emparray[$t] = $parray;
							//$emparray = [$t => $parray];
							$t =$t+1;
							//echo $row['Food Name']," " ,$row['Category'],"<br>";
							//$emparray[] = $dupe_result;
						}
						echo json_encode($emparray);						
				}else{
					echo "FOODS table currently empty";
				}
				mysqli_close($con);
				
			}/*else{
				echo "Improper Request Method";
			}
		}else{
			echo "Invalid Platform";
		}*/
	//}
		
?>