
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
   
    <body>
        <h1>Welcome</h1>
        <c:if test="${!empty error}">
            <p style="color:red;">Please enter valid credentials and login</p>
        </c:if>
        
                <form action='login' method='post'>
                UserName : <input type ='text' name ='username' value='${cookie.username.value}'><br /><br />
                Password : <input type ='password' name ='password' value='${cookie.password.value}'><br /><br />
                <input type='checkbox' name='rememberMe' value="rememberMe" checked> Remember Me <br /><br />
                <input type="submit" value="Submit"> 
                <input type="hidden" name="action" value="Login" />
        		</form>       
        <a  href="signup">Signup</a>        
        
    </body>
</html>
