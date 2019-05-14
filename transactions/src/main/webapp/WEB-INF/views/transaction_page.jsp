<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">
	<title>Перевод средств</title>
</head>
<body>
		
	<form:form action="transact">		
		<table>
		  	<tr>
				<td><h3>Перевод средств</h3></td>
			</tr>	
			<tr>
				<td>Счет списания : </td>
				<td>
					<select name="debitedAcc">
    					<c:if test="${!empty accList}">
	    					<c:forEach items="${accList}" var="acc">
	        					<option value="${acc.id}">номер: ${acc.number}, остаток: ${acc.amount}</option>
	    					</c:forEach>
	    				</c:if>
					</select><br>
				</td>
			</tr><br><br>
			<tr>
				<td>Счет зачисления : </td>
				<td>
					<input type="text" name="proceedsAcc" /><br>
				</td>
			</tr><br><br>
			<tr>
				<td>Сумма : </td>
				<td>
					<input type="text" name="amount" /><br>
				</td>
			</tr><br><br>
		</table>		
		<input type="submit" value="Перевести">
	</form:form>
	
</body>
</html>