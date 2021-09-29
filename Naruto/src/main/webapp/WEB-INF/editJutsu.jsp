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
<title>Edit Jutsu</title>
    <style>
    body{
    background-image:url("./img/background.jpg");
    background-repeat: no-repeat;
    background-size: 120%;
    }
    </style>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
<h1>Edit your Jutsu</h1>
<a href="/dashboard">Dashboard</a>
<form:form action="/update/${jutsu.id}" method="post" modelAttribute="jutsu">

		<form:hidden path="user" value="${jutsu.user.id}"/>

	<p>
		<form:label path="type">Jutsu Type</form:label>
		<form:errors path="type"/>
		<form:input path="type"/>
	</p>
	<p>
		<form:label path="description">Description </form:label>
		<form:errors path="description"/>
		<form:input path="description" />
	</p>
	<input type="submit" value="submit"/>
</form:form>
    </div> <!-- End of Container -->
</body>
</html>