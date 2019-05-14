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

	<h3>Список счетов ${accList.size()}</h3>
	<c:if test="${!empty accList}">
		<table class="acc_data">
			<tr>
				<th>Номер</th>
				<th>Остаток средств</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${accList}" var="acc">
				<tr>
					<td>${acc.number}</td>
					<td>${acc.amount}</td>
					<td><a href="edit_acc/${acc.id}">Изменить</a></td>
					<td><a href="delete_acc/${acc.id}">Удалить</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>