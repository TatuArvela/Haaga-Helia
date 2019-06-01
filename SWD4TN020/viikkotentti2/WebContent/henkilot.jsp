<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/henkilot.css">
<title>Henkilöt</title>
</head>
<body>

	<form action="henkilot" method="post">
		<table>
			<caption>Henkilöt</caption>
			<thead>
				<tr>
					<td>ID</td>
					<td>ETUNIMI</td>
					<td>SUKUNIMI</td>
					<td>+/-</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${henkilot}" var="henk">
					<tr>
						<td><c:out value="${henk.id}" /></td>
						<td><c:out value="${henk.etunimi}" /></td>
						<td><c:out value="${henk.sukunimi}" /></td>
						<td><button type="submit" name="poista" value="${henk.id}"><b>-</b></button></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td>LISÄÄ</td>
					<td><input type="text" name="etunimi" placeholder="etunimi..."/></td>
					<td><input type="text" name="sukunimi" placeholder="sukunimi..."/></td>
					<td><button type="submit"><b>+</b></button></td>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>