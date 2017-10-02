
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
    </head>
    <body >
    <jsp:include page="menu2.jsp" />
        <h1>Welcome <c:out value="${sessionScope.username}"></c:out></h1>
        
            <table border='1'>
                <tr>
                    <th>From</th>
                    <th>Movie Title</th>
                    <th>Content</th>
                    <th>To</th>
                    <th></th>
                </tr>    
            <c:forEach var ="message" items="${sessionScope.list}">
                <tr>
                    <td><c:out value="${message.from1}"></c:out></td>
                    <td><c:out value="${message.title}"></c:out></td>
                    <td><c:out value="${message.content}" ></c:out></td>
                    <td><c:out value="${message.to1}" default="original"></c:out></td>
                    <td><a href ="deleteMessage?id=${message.messageId}">Delete</a></td>
                    <td><a  href="showMovie?title=${message.title}">Enter</a></td>
                    </tr>        
            </c:forEach>
        </table>   
    </body>
</html>
