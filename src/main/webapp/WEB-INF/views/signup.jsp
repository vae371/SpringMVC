<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>

<body>

	<script>
		function checkunique() {
			var username = document.getElementById("un").value;
			var xmlHttp;

			try // Firefox, Opera 8.0+, Safari
			{
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				try // Internet Explorer
				{
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
						alert("Your browser does not support AJAX!");
						return false;
					}
				}
			}

			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4) {
					if (xmlHttp.responseText == "true") {
						document.getElementById("checkUser").innerHTML = "Good Username:";
						document.getElementById("checkUser").style.color = "green";
						//document.getElementById("mySubmit").disabled = false;
					} else {
						document.getElementById("checkUser").innerHTML = "Username: Existed!";
						document.getElementById("checkUser").style.color = "red";
						//document.getElementById("mySubmit").disabled = true;
					}
				} 
				else {
					document.getElementById("checkUser").innerHTML = "AJAX NOT READY";
				}
			}

			xmlHttp.open("GET", "checkUser1.htm?username=" + username, true);
			xmlHttp.send();
		}
	</script>

	<div align="center">
		<h1>Welcome</h1>

		<c:if test="${!empty error}">
			<p style="color: red;">Duplicate ${error}</p>
		</c:if>
		<div id="checkUser">
			<label>unique</label>
			<p>
		</div>
		<table border="0" width="90%">
			<form:form action="signup" commandName="userForm">
				<tr>
					<td align="left" width="20%">Username:</td>
					<td align="left" width="40%"><form:input path="username"
							id="un" onkeyup="checkunique()" size="30" /></td>
					<td align="left"><form:errors path="username" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:password path="password" size="30" /></td>
					<td><form:errors path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td align="left" width="20%">Email:</td>
					<td align="left" width="40%"><form:input path="email"
							size="30" /></td>
					<td align="left"><form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td></td>
					<td align="center"><input type="submit" value="Signup"
						id="mySubmit" /></td>
					<td></td>
				</tr>
			</form:form>
		</table>
	</div>
</body>
</html>