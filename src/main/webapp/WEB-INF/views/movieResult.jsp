
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter Book Details</title>
    </head>
    <body >
        <jsp:include page="menu2.jsp"/>
         <c:if test="${empty movieList}">
            <p style="color:red;">No result found</p>
        </c:if>
            <table>
            <tr>
                <th>Title</th>
                <th>Lead Actor</th>
                <th> Lead Actress</th>
                <th>Year</th>                
            </tr>
            
            <c:forEach var="item" items="${movieList}">
            <tr>
                <td>${item.title}</td>
                <td>${item.leadActor}</td>
                <td>${item.leadActress}</td>
                <td>${item.year}</td>               
                <td><a  href="showMovie?title=${item.title}">Enter</a> </td>                
            </tr>
        </c:forEach>
        </table>           
    </body>
</html>
