<?php

	require_once "connect.php";
	
	if(!$con){
		echo "Database Connection Failed" ;
	} else{
		//if($_SERVER['HTTP_USER_AGENT']=="EatIt"){
		//	if($_SERVER["REQUEST_METHOD"]=="GET"){
				$dupe ="SELECT * FROM PRODUCT";
				$dupe_result = mysqli_query($con,$dupe);
				$name = null;
				$cat = null;
				$t = 1;
				if(mysqli_num_rows($dupe_result)>0){
					$emparray = array();
					$parray = array();
					while($row =mysqli_fetch_assoc($dupe_result))
					{
						$parray["fn"] = $row['Food Name'];
						$parray["cat"] = $row['Category'];
						$emparray[$t] = $parray;
						//$emparray = [$t => $parray];
						$t =$t+1;
						//echo $row['Food Name']," " ,$row['Category'],"<br>";
						//$emparray[] = $dupe_result;
					}
					echo json_encode($emparray);
				//$emparray["fn"] = $row['Food Name'];					
					//echo json_encode($emparray);
					//$outp = array();
					//$outp = $dupe_result->fetch_all(MYSQLI_ASSOC);
					//echo json_encode($output_string);
						
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