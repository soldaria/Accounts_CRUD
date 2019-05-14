<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/entry_style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/modal_form.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Вход в систему</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript">
	<%@include file="/resources/js/modal_form.js"%>
</script>
</head>

<body>	
	 <div style="text-align: right;padding:5px;margin:5px 0px;background:#ccc;">
       <a href="${pageContext.request.contextPath}/en/login">EN</a>
       &nbsp;&nbsp;
       <a href="${pageContext.request.contextPath}/ru/login">RU</a>
       &nbsp;&nbsp;
    </div>
	<div id="container">	
		<h2><center>Вход в систему</center></h2>
			<form:form method="POST" commandName="user" action="check-user">				
	
				<label for="login">Логин:</label>
				<form:input path="login" type="name" name="login"/>
				
				<label for="password">Пароль:</label>						
				<form:password path="password"/>
				<%--<h style="font-style: italic; color: red; align:center;">${message}</h>	--%>				
				
				<div id="lower">						
					<input type="submit" value="Войти" tabindex="4"> 
					<h3><a href="#" id="go">Регистрация</a></h3>
				</div>
			</form:form>
	</div>
	
	<!-- Модальное окно -->
	<div id="modal_form">
		<span id="modal_close">X</span>
		<form:form method="post" action="add" commandName="user">		
			<h3>Форма регистрации</h3>
			Введите имя : <br>
			<form:input path="name" name="name"/><br>
			Введите логин : <br>
			<form:input path="login" name="login"/><br>
			<form:errors path="login" cssClass="error" />
			Введите пароль : <br>
			<form:input path="password" name="password"/><br>	
			<form:errors path="password" cssClass="error" />	
			<input type="submit" name="in" value="Регистрация"></p>
		</form:form>
	</div>
	<div id="overlay"></div>
	
</body>
</html>
