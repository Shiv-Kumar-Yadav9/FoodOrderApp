<?php

	require_once "connect.php";
	
	if(!$con){
		echo "Database Connection Failed" ;
	} else{
		if($_SERVER['HTTP_USER_AGENT']=="EatIt"){
			if($_SERVER["REQUEST_METHOD"]=="POST"){
				if(isset($_POST['email'])&&isset($_POST['password'])){
					$email = $_POST['email'];
					$password= $_POST['password'];
				
					$dupe ="SELECT confirmed FROM USERS WHERE email = '$email' and password = '$password'";
					$dupe_results = mysqli_query($con,$dupe);
					if(mysqli_num_rows($dupe_results)>0){
						
						$c = $dupe_results->fetch_assoc();         
						$res = $c['confirmed'] ;
						echo $res;
						if($res == 1)
						{
							echo "Confirmed";
						}else{
							echo "Please check email";
						}
					}else{
						echo "Sign up First";
					}
						
					mysqli_close($con);
				}else{
					echo "Missing required fields";
				}
			}else{
				echo "Improper Request Method";
			}
		}else{
			echo "Invalid Platform";
		}
	}

?>