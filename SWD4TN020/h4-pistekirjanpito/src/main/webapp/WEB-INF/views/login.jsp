<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sisäänkirjautuminen - H4 Pistekirjanpito</title>
<link rel="stylesheet" type="text/css" href="resources/styles/style.css">
</head>
<body>
	<h1><a href="login">H4 Pistekirjanpito</a></h1>
	<div>
		<form id="login" method="post">
			<h2>Sisäänkirjautuminen</h2>
			<c:if test="${not empty error}">
				<p id="error">Virhe: Käyttäjätunnus tai salasana on virheellinen</p>
			</c:if>
			<p>Käyttäjätunnus</p>
			<input type="text" name="kayttajatunnus" required><br/>
			<p>Salasana</p>
			<input type="password" name="salasana" required>
			<input type="submit" name="kirjaudu" value="Kirjaudu sisään">
		</form>
	</div>
</body>
</html>