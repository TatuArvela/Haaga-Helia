<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Muokkaa pistetietoja - H4 Pistekirjanpito</title>
<link rel="stylesheet" type="text/css" href="resources/styles/style.css">
</head>
<body>
	<h1><a href="login">H4 Pistekirjanpito</a> - Muokkaa pistetietoja</h1>
	<div>
			<table>
				<thead>
					<tr>
						<th>Opiskelija</th>
						<th>Kurssi</th>
						<th>Tehtävä</th>
						<th>Pisteet</th>
						<th></th>
					</tr>
				</thead>
			</table>
		
		<c:forEach items="${pisteet}" var="pt"><form id="muuta" method="post">
			<table>
				<tbody>
					<tr>
						<td><select name="opiskelijatunnus" disabled>
							<option value="${o.tunnus}"><c:out value="${pt.opiskelijatunnus} - ${pt.opiskelijanEtunimi} ${pt.opiskelijanSukunimi}"/></option>
						</select></td>
						<td><input type="text" name="kurssi" placeholder="Syötä kurssi" required value="<c:out value="${pt.kurssi}"/>"></td>
						<td><input type="text" name="tehtava" placeholder="Syötä tehtävän nimi" required value="<c:out value="${pt.tehtava}"/>"></td>
						<td><input type="text" name="pisteet" placeholder="Syötä pisteet" required value="<c:out value="${pt.pisteet}"/>"></td>
						<td>
							<input type="text" name="id" value="${pt.id}" style="display:none;">
							<button type="submit" name="muuta">Muuta</button>
							<button type="submit" name="poista" value="${pt.id}">Poista</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form></c:forEach>
		
		<form id="lisaa" method="post">
			<table>
				<tbody>
					<tr>
						<td><select name="opiskelijatunnus" required>
							<option value="placeholder" disabled selected>Valitse opiskelija</option>
							<c:forEach items="${opiskelijat}" var="o">
							<option value="${o.tunnus}"><c:out value="${o.tunnus} - ${o.etunimi} ${o.sukunimi}" /></option>
							</c:forEach>
						</select></td>
						<td><input type="text" name="kurssi" placeholder="Syötä kurssi" required/></td>
						<td><input type="text" name="tehtava" placeholder="Syötä tehtävän nimi" required/></td>
						<td><input type="text" name="pisteet" placeholder="Syötä pisteet" required/></td>
						<td><button type="submit" name="lisaa">Lisää</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>