<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="_style.css">
<title>Hakutulokset - VuokrausOhjelma</title>
</head>
<body>
	<nav><a href="index.jsp">&#9664; Takaisin</a></nav>
	<div id="main">
		<h1>Hakutulokset</h1>
		<c:forEach items="${vuokraukset}" var="vuokraus">
			<div>
				<table>
					<tr>
						<td class="break">Vuokraus</td>
					</tr>
					<tr>
						<td class="header">Numero</td>
						<td>#<c:out value="${vuokraus.numero}"/></td>
					</tr>
					<tr>
						<td class="header">Vuokrattu ajaksi</td>
						<td><c:out value="${vuokraus.vuokrauspvm}"/> - <c:out value="${vuokraus.paattymispvm}"/></td>
					</tr>
					<tr>
						<td class="header">Kokonaishinta</td>
						<td><c:out value="${vuokraus.kokonaishinta}"/>0 EUR</td>
					</tr>
					<tr>
						<td class="header"><c:out value="Maksettu"/></td>
						<c:choose>
							<c:when test="${vuokraus.maksupvm != null }">
								<td><c:out value="${vuokraus.maksupvm}"/></td>
							</c:when>
							<c:otherwise>
								<td>Vuokrausta ei ole vielä maksettu</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="break">Vuokraaja</td>
					</tr>
					<tr>
						<td class="header">Tunnus</td>
						<td>#<c:out value="${vuokraus.vuokraaja.id}"/></td>
					</tr>
					<tr>
						<td class="header">Nimi</td>
						<td><c:out value="${vuokraus.vuokraaja.etunimi}"/> <c:out value="${vuokraus.vuokraaja.sukunimi}"/></td>
					</tr>
					<tr>
						<td class="header">Osoite</td>
						<td><c:out value="${vuokraus.vuokraaja.osoite}"/></td>
					</tr>
					<tr>
						<td class="header">Postinumero ja toimipaikka</td>
						<td><c:out value="${vuokraus.vuokraaja.posti.postinro}"/> <c:out value="${vuokraus.vuokraaja.posti.postitmp}"/></td>
					</tr>
					<tr>
						<td class="break">Ajoneuvo</td>
					</tr>
					<tr>
						<td class="header">Rekisteritunnus</td>
						<td><c:out value="${vuokraus.vuokrakohde.rekno}"/></td>
					</tr>
					<tr>
						<td class="header">Malli</td>
						<td><c:out value="${vuokraus.vuokrakohde.malli}"/> <c:out value="${vuokraus.vuokrakohde.merkki}"/></td>
					</tr>
					<tr>
						<td class="header">Vuorokausihinta</td>
						<td><c:out value="${vuokraus.vuokrakohde.vrkhinta}"/>0 EUR</td>
					</tr>
					<tr>
						<td class="header">Auto on huollettu viimeksi</td>
						<c:choose>
							<c:when test="${vuokraus.vuokrakohde.huoltopvm != null}">
									<td><c:out value="${vuokraus.vuokrakohde.huoltopvm}"/></td>
							</c:when>
							<c:otherwise>
									<td>Auton huoltoa ei ole rekisteröity tietokantaan</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
			</div>
		</c:forEach>
		<c:if test="${empty vuokraukset}">
		   <p>Päivämäärältä ei löytynyt alkaneita vuokrauksia.<p>
		</c:if>
	</div>
	<footer><p>Tatu Arvela, Hieu Bui, Nico Ersüs | SWD1TN002, Haaga-Helia, 2016</p></footer>
</body>
</html>