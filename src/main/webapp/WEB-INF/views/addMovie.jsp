<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
 <jsp:include page="menu1.jsp"/>
	<c:if test="${!empty error}">
		<p style="color: red;">Duplicate ${error}</p>
	</c:if>

	How many movies do you want to add?
	<p>
		<input type='number' value='1' min='1' id='myText'>
		<button onclick="myFunction()">Submit</button>
		<script>
			function myFunction() {
				var x = document.getElementById("myText").value;
				document.write('<form action="movieAdded" method="post">');
				document
						.write('<input type="hidden" value='+x+' name="num"' + '/> ');
				document
						.write("<table border='2'><tr><th>Title</th><th>Lead Actor</th><th>Lead Actress</th><th>Year</th></tr>");
				for (var i = 1; i <= x; i++) {
					document
							.write('<tr><td><input type=\"text\" name=\"title\" required/> </td><td><input type=\"text\" name=\"actor\" required/></td>');
					document
							.write('<td><input type=\"text\" name=\"actress\" required/> </td>');
					document
							.write('<td><input type=\"number\" name=\"year\"  min="1888"   required/> </td></tr><p>');
				}
				document.write('<input type=\"submit\" value=\"Add movies\" >');
				document.write('</form>');
			}
		</script>
</body>
</html>
