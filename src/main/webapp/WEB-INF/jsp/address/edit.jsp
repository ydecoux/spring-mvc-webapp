<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title></title>

</head>

<body>
<h2>Address</h2>
	<spring:url var="saveUrl" value="/address/save.html?address=${address.id}&user=${userId}"></spring:url>
	<form:form modelAttribute="address"  action="${saveUrl}">	
		street <form:input path="street"/><br/>
		<form:hidden path="version"/>
		Version : <c:out value="${user.version}"/>
		
		<input type="submit" value="save">
	</form:form>
<br/>


</body>
</html>