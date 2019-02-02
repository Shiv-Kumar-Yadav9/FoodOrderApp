<?php

	require_once "connect.php";
	
	if(!$con){
		echo "Database Connection Failed" ;
	} else{
		if($_SERVER['HTTP_USER_AGENT']=="EatIt"){
			if($_SERVER["REQUEST_METHOD"]=="POST"){
				if(isset($_POST['name'])&&isset($_POST['email'])&&isset($_POST['password'])){
					$name= $_POST['name'];
					$email = $_POST['email'];
					$password= $_POST['password'];
					
					echo $name;
					$dupe ="SELECT * FROM USERS WHERE email = '$email'";
					$dupe_results = mysqli_query($con,$dupe);
					if(mysqli_num_rows($dupe_results)>0){
						echo " E-mail already exists!!!";
					}else{
						$code = rand();
						$sql = "INSERT INTO USERS (id,name,email,password,confirmed,code) VALUES ('','$name','$email','$password','false','$code')";
						
						if(mysqli_query($con,$sql)){
							$from = "From: DoNotRelpy@EatUp.com";
							$to = $email;
							$subj = "EAT IT Email Verification";
							$msg = "
								$name,
								
								Please click on the link below to verify your email and activate your account.
								If the link does not work then copy and paste the link in the address bar.
								https://ymohit2911.000webhostapp.com/connect/emailver.php?email=$email&code=$code
								
							";
							mail($to,$subj,$msg,$from);
							echo"Please check your email!!!";
						}else{
							echo"Error: " . $sql . "<br>" . mysqli_error($con);
						}
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