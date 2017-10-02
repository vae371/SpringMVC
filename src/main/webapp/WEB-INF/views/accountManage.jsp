
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<script>
	function check() {
		var pwd = document.getElementById("pwd").value;
		var repwd = document.getElementById("repwd").value;
		if (pwd == repwd) {
			document.getElementById("reenter").innerHTML = "Consistent:";
			document.getElementById("reenter").style.color = "green";
			document.getElementById("mySubmit").disabled = false;
		} else {
			document.getElementById("reenter").innerHTML = "Not Match!";
			document.getElementById("reenter").style.color = "red";
			document.getElementById("mySubmit").disabled = true;
		}
	}
</script>

	<h1>Change Password</h1>

	<form action='changePassword' method='post'>
		Old Password : <br><input type='text' name='old' required><br />
		<br /> New Password :<br /> <input type='password' name='new' id='pwd' required><br />
		<br />
		<div><label id="reenter">Confirmed :</label> </div>  <input type='password' name='confirm' id="repwd"
		onkeyup="check()" required><br />
		<br /> <input type="submit" value="Submit" id="mySubmit">
	</form>


</body>
</html>
