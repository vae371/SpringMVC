
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enter Book Details</title>
</head>
<body>
	<jsp:include page="menu2.jsp" />
	<h1>
		Welcome
		<c:out value="${sessionScope.username}"></c:out>
	</h1>
	<table border="1">
		<tr>
			<th>Title</th>
			<th>Lead Actor</th>
			<th>Lead Actress</th>
			<th>Year</th>
		</tr>

		<tr>
			<td>${movie.title}</td>
			<td>${movie.leadActor}</td>
			<td>${movie.leadActress}</td>
			<td>${movie.year}</td>
		</tr>

	</table>
	<p>
	<h2>Comments:</h2>
	<p>
		<form action="addComment" id="usrform" method="post">
		<input type="text" name="comment" required>		
		<input type="submit" value="submit">
		</form>
	<br>
	
<p>
	<table border='1'>
		<tr>
			<th>From</th>
			<th>Message</th>
			<th>To</th>			
			<th></th>
		</tr>
		<c:forEach var="comment" items="${list}">
			<tr>
				<td><c:out value="${comment.from1}"></c:out></td>
				<td><c:out value="${comment.content}"></c:out></td>
				<td><c:out value="${comment.to1}" default="original"></c:out>						
				<td><a href="reply?to=${comment.from1}">Reply</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
