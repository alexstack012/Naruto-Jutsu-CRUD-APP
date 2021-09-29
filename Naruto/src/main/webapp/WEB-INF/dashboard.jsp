<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
    <style>
    body{
    background-image:url("./img/background.jpg");
    background-repeat: no-repeat;
    background-size: 110%;
    }
    .logout{
    text-align: right;
    margin-top: 10px;
    }
    footer{
    height: 64px;
    background-color: darkgrey;
    display: flex;
    justify-content: center;
    margin-top: 485px;
    align-content: center;
    align-items: center;
    }
    </style>
</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
        <div class="logout">
        <form action="/logout">
        <button class="btn-danger">LogOut</button>       
        </form>
        </div>
    <h1>Welcome ${user.firstName}</h1>
        <a href="/newJutsu">New Jutsu</a>
        <h1>Need a Jutsu?</h1>
        
        <table class="table">
        	<thead>
        		<tr>
        			<th scope="col">Type</th>
        			<th scope="col">Ninja</th>
        			<th scope="col">Description</th>
        			<th scope="col">Actions</th>
        		</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${allJutsus}" var="i">
        		<tr>
        				<td> <a href="/oneJutsu/${i.id}">${i.type}</a> </td>
        				
        				<td> <c:out value="${i.user.firstName}"/> </td>
        				
        				<td><c:out value="${i.description}"/></td>
        			<c:choose>
        				<c:when test="${i.user.id == user.id}">
        				<td>
        					<a href="/editJutsu/${i.id}">Edit</a>
        					<a href="/jutsu/${i.id}/delete">Delete</a>
        				</td>
        				</c:when>
        				<c:otherwise>
        				<td> Sorry - you dont own this jutsu!</td>
        				</c:otherwise>
        			</c:choose>
        		</tr>
        	</c:forEach>
        	</tbody>        
        </table>        
    </div> <!-- End of Container -->
</body>
<footer>
<h3>Project done By: Alex Stack in Java SpringBoot with MySQL DB.</h3>
</footer>
</html>