<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.min.js"></script>
<script src="_script.js"></script>
<link rel="stylesheet" type="text/css" href="_style.css">
<title>Uusi vuokraus - VuokrausOhjelma</title>
</head>

<body>
	<nav><a href="index.jsp">&#9664; Takaisin</a></nav>
	<div id="main">
		<h1>Lisää vuokraus</h1>
		<div>
			<form method="post" action="VuokrausOhjelma" name="uusivuokraus" onsubmit="return varmistaLomake()">
				<table>
					<tr>
						<td class="break">Vuokraus</td>
					</tr>
					<tr>
						<td class="header">Vuokrataan ajaksi</td>
						<td><input type="text" name="vuokrauspvm" class="date" id="vuokrauspvm" spellcheck="false" placeholder="Valitse alkupäivä"/> - 
						<input type="text" name="paattymispvm" class="date" id="paattymispvm" spellcheck="false" placeholder="Valitse päättymispäivä"/></td>
					</tr>
					<tr>
						<td class="header">Kokonaishinta</td>
						<td><input type="text" name="kokonaishinta"	class="readonly" id="kokonaishinta" readonly></td>
					</tr>
					<tr>
						<td class="header">Maksettu</td>
						<td><input type="checkbox" name="maksettu" class="checkbox" id="maksettu"><input type="text" name="maksupvm" id="maksupvm" spellcheck="false" disabled/></td>
					</tr>
					<tr>
						<td class="break">Vuokraaja</td>
					</tr>
					<tr>
						<td class="header">Tunnus</td>
						<td><input type="text" name="asiakastunnus" class="readonly" id="asiakastunnus" readonly></td>
					</tr>
					<tr>
						<td class="header">Nimi</td>
						<td><select name="asiakas" id="asiakas">
							<option disabled selected>Valitse asiakas</option>
							<c:forEach items="${asiakkaat}" var="asiakas">
								<option value="${asiakas.id},${asiakas.osoite},${asiakas.posti.postinro},${asiakas.posti.postitmp}">
									<c:out value="${asiakas.etunimi} ${asiakas.sukunimi}"/>
								</option>
			 				</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td class="header">Osoite</td>
						<td><input type="text" class="readonly" id="osoite" readonly value=""></td>
					</tr>
					<tr>
						<td class="header">Postinumero ja toimipaikka</td>
						<td><input type="text" class="readonly" id="posti" readonly	value=""></td>
					</tr>
					<tr>
						<td class="break">Ajoneuvo</td>
					</tr>
					<tr>
						<td class="header">Rekisterinumero</td>
						<td><input type="text" name="rekno" class="readonly" id="rekno" readonly/></td>
					</tr>
					<tr>
						<td class="header">Merkki ja malli</td>
						<td><select id="ajoneuvo">
							<option disabled selected>Valitse ajoneuvo</option>
							<c:forEach items="${autot}" var="auto">
								<option value="${auto.rekno},${auto.vrkhinta},${auto.huoltopvm}">
									<c:out value="${auto.malli} ${auto.merkki}"/>
								</option>
			 				</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td class="header">Vuorokausihinta</td>
						<td><input type="text" class="readonly" id="vrkhinta" readonly></td>
					</tr>
					<tr>
						<td class="header">Auto on huollettu viimeksi</td>
						<td><input type="text" class="readonly" id="huoltopvm" readonly></td>
					</tr>
				</table>
				<input type="submit" name="action" class="button" value="Tee vuokraus"/><p id="alert">Korjaa lomakkeen tiedot</p>
			</form>
		</div>
	</div>
	<footer><p>Tatu Arvela, Hieu Bui, Nico Ersüs | SWD1TN002, Haaga-Helia, 2016</p></footer>
</body>
</html>