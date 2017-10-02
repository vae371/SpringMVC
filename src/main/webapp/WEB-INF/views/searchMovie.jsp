
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>
	

	<h1>Search Movie</h1>
	
	<form action='searchMovie' method='post'>
		Movie Title: <input type='text' name='title' id="un"
			 required><br />
		<br /> <input type="submit" value="Search">
	</form>


</body>
</html>
