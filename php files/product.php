<?php

	require_once "connect.php";
	
	if(!$con){
		echo "Database Connection Failed" ;
	} else{
		//if($_SERVER['HTTP_USER_AGENT']=="EatIt"){
		//	if($_SERVER["REQUEST_METHOD"]=="GET"){
				$dupe ="SELECT * FROM PRODUCT";
				$dupe_result = mysqli_query($con,$dupe);
				if(mysqli_num_rows($dupe_result)>0){
					$emparray = array();
					while($row =mysqli_fetch_assoc($result))
					{
						$emparray[] = $row;
					}	
					echo json_encode($emparray);
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