<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H3 Korttiohjelmisto</title>
<link rel="stylesheet" type="text/css" href="resources/styles/style.css">
<script>
function init() {
	var lisaa = document.getElementById("lisaa");
	lisaa.addEventListener("click", function() {lisaa.style.opacity = "1";});
}
</script>
</head>
<body onload="init();">
	<h1>H3 Korttiohjelmisto</h1>
	<div>
		<form id="lisaa" method="post">
			<fieldset>
				<p>Etunimi</p>
				<input type="text" name="etunimi"><br/>
				<p>Sukunimi</p>
				<input type="text" name="sukunimi">
			</fieldset>
			<fieldset>
				<p>Osoite</p>
				<input type="text" name="osoite"><br/>
				<p>Postinumero</p>
				<input type="text" name="postinumero"><br/>
				<p>Postitoimipaikka</p>
				<input type="text" name="postitoimipaikka">
			</fieldset>
			<fieldset>
				<p>Puhelinnumero</p>
				<input type="text" name="puhelin"><br/>
				<p>Sähköposti</p>
				<input type="text" name="email">
			</fieldset>
			<fieldset>
				<button name="lisaa">Lisää uusi yhteystieto</button>
			</fieldset>
		</form>
		
		<c:forEach items="${henkilot}" var="henkilo"><form method="post">
			<input type="text" name="id" value="<c:out value="${henkilo.id}"/>" style="display: none;">
			<fieldset>
				<p>Etunimi</p>
				<input type="text" name="etunimi" value="<c:out value="${henkilo.etunimi}"/>"><br/>
				<p>Sukunimi</p>
				<input type="text" name="sukunimi" value="<c:out value="${henkilo.sukunimi}"/>">
			</fieldset>
			<fieldset>
				<p>Osoite</p>
				<input type="text" name="osoite" value="<c:out value="${henkilo.osoite}"/>"><br/>
				<p>Postinumero</p>
				<input type="text" name="postinumero" value="<c:out value="${henkilo.postinumero}"/>"><br/>
				<p>Postitoimipaikka</p>
				<input type="text" name="postitoimipaikka" value="<c:out value="${henkilo.postitoimipaikka}"/>">
			</fieldset>
			<fieldset>
				<p>Puhelinnumero</p>
				<input type="text" name="puhelin" value="<c:out value="${henkilo.puhelin}"/>"><br/>
				<p>Sähköposti</p>
				<input type="text" name="email" value="<c:out value="${henkilo.email}"/>">
			</fieldset>
			<fieldset>
				<button name="muuta" value="<c:out value="${henkilo.id}"/>">Tallenna muutokset</button>
				<button name="poista" value="<c:out value="${henkilo.id}"/>">Poista osoitetiedot</button>
			</fieldset>
		</form></c:forEach>
	</div>
</body>
</html>