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

	<spring:url var="saveUrl" value="/user/save.html?user=${user.id}&fakeUserParam=${user.id}"></spring:url>
	<form:form modelAttribute="user"  action="${saveUrl}">	
		firstname <form:input path="firstname"/><br/>
		lastname <form:label path="lastname" for="lastname"/><form:input path="lastname"/><br/>
		age <form:label path="age" for="age"/><form:input path="age"/><br/>
		<form:hidden path="version"/>
		Version : <c:out value="${user.version}"/>
		
		<input type="submit" value="save">
	</form:form>
<br/>
<spring:url value="/user/list.html" var="listUrl"/>
<a href="${listUrl}">Back to list</a>
</body>
</html>