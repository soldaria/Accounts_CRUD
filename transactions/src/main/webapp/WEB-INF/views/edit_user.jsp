<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>Редактировать данные</title>
</head>
<body>
	<form:form action="update_user" commandName="u">		
		<table>
		  	<tr>
				<td><h3>Редактировать данные</h3></td>
			</tr>		
			<form:hidden path="id" />		
			<form:hidden path="admin" />	
			<tr>
				<td>Имя : </td><td><form:input path="name" /><br></td>
			</tr>
			<tr>
				<td>Логин : </td><td><form:input path="login" /><br></td>
			</tr>
			<tr>
				<td>Пароль : </td><td><form:input path="password"/><br></td>
			</tr>
		</table>		
		<input type="submit" value="Изменить">
	</form:form>
</body>
</html>