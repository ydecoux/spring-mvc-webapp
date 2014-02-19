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

	<spring:url var="saveUrl" value="/user/saveCommand.html"></spring:url>
	<form:form modelAttribute="command"  action="${saveUrl}">	
		firstname <form:input path="user.firstname"/><br/>
		lastname <form:input path="user.lastname"/><br/>
		age <form:input path="user.age"/><br/>
		<form:hidden path="version"/>
		<form:hidden path="entityId"/>
		Version : <c:out value="${command.version}"/>
		
		<input type="submit" value="save">
	</form:form>
<br/>
<spring:url value="/user/list.html" var="listUrl"/>
<a href="${listUrl}">Back to list</a>
</body>
</html>