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

	<table>
		<tr>
			<th>Id</th>
			<th>Firstname</th>
			<th>Lastname</th>
			<th>Nickname</th>
			<th>action</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<spring:url var="editUrl"  value="/user/${user.id}/edit.html"/>
			<tr>
				<td><c:out value="${user.id}" /></td>
				<td><c:out value="${user.firstname}" /></td>
				<td><c:out value="${user.lastname}" /></td>
				<td><c:out value="${user.nickname}" /></td>
				<td><a href="${editUrl}">edit</a> </td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>