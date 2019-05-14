<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>Редактировать счет</title>
</head>
<body>	
	
	<form:form action="update_acc" commandName="acc">		
		<table>
		  	<tr>
				<td><h3>Редактировать счет</h3></td>
			</tr>		
			<form:hidden path="id" />	
			<tr>
				<td>Номер : </td><td><form:input path="number" /><br></td>
			</tr>
			<tr>
				<td>Остаток : </td><td><form:input path="amount" /><br></td>
			</tr>
		</table>		
		<input type="submit" value="Изменить">
	</form:form>
</body>
</html>