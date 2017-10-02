
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>
	

	<h1>To ${to}</h1>
	
	<form action='reply' method='post'>
		Movie Title: <input type='text' name='message' id="un"
			 required><br />
		<br /> <input type="submit" value="Reply">
	</form>


</body>
</html>
