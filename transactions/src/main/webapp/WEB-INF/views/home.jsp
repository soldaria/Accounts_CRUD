<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">	
	<title>Список пользователей</title>	
	<link href="<c:url value="/resources/css/modal_form.css" />" rel="stylesheet">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script type="text/javascript">
		<%@include file="/resources/js/modal_form.js"%>	
	</script>
</head>
<body>

<a href="out">Выход</a> 
 
<c:if test="${!empty userList}">
	<h3>Список пользователей</h3>
	<table class="data">
		<tr>
			<th>Имя</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.name}</td>
				<td><a href="edit_user/${user.id}">Изменить</a></td> 
				<td><a href="show_accs/${user.id}">Счета</a></td> 
			</tr>
		</c:forEach>
	</table>
</c:if>


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

<a href="#" id="go">Добавить счет</a><br>
<a href="transaction_page">Перевод средств между счетами</a>

<div id="modal_form">	
	<span id="modal_close">X</span>	
	<form:form method="post" action="add-acc" commandName="acc">	
		<h3>Добавить счет</h3>	
		Номер : <br>
		<form:input path="number"/><br>
		Остаток : <br>
		<form:input path="amount"/><br>		
		<input type="submit" name="in" value="Добавить">
	</form:form>	
</div>
<div id="overlay"></div>

</body>
</html>