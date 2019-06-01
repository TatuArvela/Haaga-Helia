<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pisteesi - H4 Pistekirjanpito</title>
<link rel="stylesheet" type="text/css" href="resources/styles/style.css">
</head>
<body>
	<h1><a href="login">H4 Pistekirjanpito</a> - Pisteesi</h1>
	<div>
		<table class="output">
			<thead>
				<tr>
					<th>Opiskelija</th>
					<th>Kurssi</th>
					<th>Tehtävä</th>
					<th>Pisteet</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pisteet}" var="pt">
					<tr>
						<td><c:out value="${pt.opiskelijatunnus} - ${pt.opiskelijanEtunimi} ${pt.opiskelijanSukunimi}" /></td>
						<td><c:out value="${pt.kurssi}" /></td>
						<td><c:out value="${pt.tehtava}" /></td>
						<td><c:out value="${pt.pisteet}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>