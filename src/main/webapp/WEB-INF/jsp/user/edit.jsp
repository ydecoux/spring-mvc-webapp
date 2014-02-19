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
	<h2>User</h2>
	<spring:url var="saveUrl"
		value="/user/save.html?user=${user.id}"></spring:url>
	<form:form modelAttribute="user" action="${saveUrl}">	
		firstname <form:input path="firstname" />
		<br />
		lastname <form:label path="lastname" for="lastname" />
		<form:input path="lastname" />
		<br />
		age <form:label path="age" for="age" />
		<form:input path="age" />
		<br />
		<form:hidden path="version" />
		Version : <c:out value="${user.version}" />

		<input type="submit" value="save">
	</form:form>
	<br />
	<spring:url value="/user/list.html" var="listUrl" />
	<a href="${listUrl}">Back to list</a>
	<h2>Addresses</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Street</th>
			<th>Version</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${user.addresses}" var="address">
			<spring:url var="editAddressUrl"
				value="/address/edit.html?address=${address.id}&user=${user.id}"></spring:url>
			<spring:url var="deleteAddressUrl"
				value="/address/delete.html?address=${address.id}&user=${user.id}"></spring:url>
			<tr>
				<td>${address.id}</td>
				<td>${address.street}</td>
				<td>${address.version}</td>
				<td><a href="${editAddressUrl}">edit</a>/ <a
					href="${deleteAddressUrl}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<h2>Add an address</h2>
	<spring:url var="addAddressUrl"
		value="/address/add.html?user=${user.id}"></spring:url>

	<form:form modelAttribute="address" action="${addAddressUrl}">	
		street <form:input path="street" />
		<br />
		<form:hidden path="version" />
		Version : <c:out value="${address.version}" />

		<input type="submit" value="save">
	</form:form>

</body>
</html>