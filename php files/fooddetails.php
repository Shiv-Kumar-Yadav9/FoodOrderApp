<?php

	require_once "connect.php";
	
	if(!$con){
		echo "Database Connection Failed" ;
	} else{
		//if($_SERVER['HTTP_USER_AGENT']=="EatIt"){
			//if($_SERVER["REQUEST_METHOD"]=="POST"){
				//if(isset($_GET['fn'])){
					$fname = $_GET['fn'];
					
					$dupe ="SELECT * FROM DETAILS WHERE Food_Name = 'Vada Pav'";// '".$fname."'";//
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
						//$emparray["fn"] = $row['Food Name'];					
						//echo json_encode($emparray);
						//$outp = array();
						//$outp = $dupe_result->fetch_all(MYSQLI_ASSOC);
						//echo json_encode($output_string);
						
					}else{
						echo "DETAILS table currently empty";
					}
					mysqli_close($con);
				
				//}else{
					//echo "Missing required fields";
			//	}
			//}else{
			//	echo "Improper Request Method";
			//}
	//	}else{
		//	echo "Invalid Platform";
	//	}
	}

?>