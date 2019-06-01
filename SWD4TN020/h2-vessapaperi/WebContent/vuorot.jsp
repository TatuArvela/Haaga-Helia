<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/style.css">
<title>Vessapaperi 2016 Professional</title>
</head>
<body>
	<div data-role="page">
		<form action="vuorot" method="post">
			<div class="header">
				<h1>Vessapaperi 2016 Professional</h1>
			</div>
			
			<h2>Tämä sovellus näyttää ostovuorossa olevan asukkaan</h2>
			<table>
				<thead>
					<tr>
						<td class="id">ID</td>
						<td>Nimi</td>
						<td></td>
						<td class="maksetutvuorot center">Esimaksetut vuorot</td>
						<td></td>
					</tr>
				</thead>
				<c:forEach items="${asukkaat}" var="asukas">
					<tr <c:if test="${asukas.vuoro == true}">class="ostovuorossa"</c:if>>
						<td><c:out value="${asukas.id}"/></td>
						<td><c:out value="${asukas.nimi}"/></td>
						<td class="tdbutton center"><button name="poistavuoro" value="<c:out value="${asukas.id}"/>">➖</button></td>
						<td class="center"><c:out value="${asukas.maksetutvuorot}"/></td>
						<td class="tdbutton center"><button name="maksavuoro" value="<c:out value="${asukas.id}"/>">➕</button></td>
					</tr>
				</c:forEach>
			</table>
			<h2>1 vuoro = 6 rullaa<br/>
			Jos ostat suuremman pakkauksen kerralla, voit kuitata seuraavia vuorojasi etukäteen</h2>
		</form>
	</div>
</body>
</html>