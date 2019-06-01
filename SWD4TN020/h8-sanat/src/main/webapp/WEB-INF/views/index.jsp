<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>H8 Sanat</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/custom.css">
<script src="resources/js/jquery-3.1.1.js"></script>
<script src="resources/js/bootstrap.js"></script>
</head>
<body>
	<h1 class="titlebar">H8 Sanat</h1>
	<div class="container">
		<div class="col-sm-12 text-center">
			<h3>Opeteltavat sanat</h3>
			<div class="borderbox">
				<div class="table table-striped">
					<div class="thead">
						<div class="tr">
							<div class="th">Sana</div>
							<div class="th">Seloste</div>
							<div class="th">Kieli</div>
							<div class="th">Lisääjä</div>
							<div class="th"></div>
						</div>
					</div>
					<div class="tbody">
						<c:forEach items="${sanat}" var="s">
							<div class="tr">
								<div class="td">${s.sana}</div>
								<div class="td">${s.seloste}</div>
								<div class="td">${s.kieli}</div>
								<div class="td">${s.lisaajaEtunimi} ${s.lisaajaSukunimi} (${s.lisaajaTunnus})</div>
								<div class="td">
									<c:if test="${pageContext.request.userPrincipal.name == s.lisaajaTunnus}">
										<form method="post">
											<input type="text" name="id" value="<c:out value="${s.id}"/>" hidden="true">
											<input type="text" name="lisaajaTunnus" value="<c:out value="${s.lisaajaTunnus}"/>" hidden="true">
											<button type="submit" class="btn btn-danger poista" name="poista" title="Poista sana">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</form>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		
		<sec:authorize access="isAnonymous()">
			<div class="col-sm-4 text-center">
				<h3>Kirjaudu sisään</h3>
				<form class="borderbox" action="j_spring_security_check" method="post">
					<div class="form-group">
						<p class="text-center bold">Käyttäjätunnus: </p>
						<input type="text" class="form-control" name="j_username" required>
					</div>
					<div class="form-group">
						<p class="text-center bold">Salasana: </p>
						<input type="password" class="form-control" name="j_password" required>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">Kirjaudu sisään</button>
					</div>
					<c:if test="${not empty loginerror}">
						<p class="error">Sisäänkirjautuminen epäonnistui. Käyttäjätunnus tai salasana on syötetty väärin.</p>
					</c:if>
					<c:if test="${not empty loggedout}">
						<p class="success">Uloskirjautuminen onnistui</p>
					</c:if>
				</form>
			</div>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
			<div class="col-sm-4 text-center">
				<h3>Lisää uusi opeteltava sana</h3>
				
				<form:form modelAttribute="sana" method="post" class="borderbox">
					<div class="form-group">
						<a class="btn btn-default" href="j_spring_security_logout">Kirjaa ulos käyttäjä <sec:authentication property="principal.username"/></a>
						<hr class="divider">
					</div>
					<div class="form-group">
						<p class="text-center bold">Sana</p>
						<input type="text" class="form-control" name="sana" id="sana" required>
						<form:errors path="sana" class="error"/>
					</div>
					<div class="form-group">
						<p class="text-center bold">Seloste</p>
						<textarea class="form-control" rows="5" name="seloste" id="seloste" required></textarea>
					</div>
					<div class="form-group">
						<p class="text-center bold">Kieli</p>
						<input type="text" class="form-control" name="kieli" id="kieli" required>
						<form:errors path="kieli" class="error"/>
					</div>
					<div class="form-group">
						<p class="text-center bold">Lisääjä</p>
						<input type="text" class="form-control" name="lisaajaTunnus" id="lisaajaTunnus" value="<sec:authentication property="principal.username"/>" readonly required>
					</div>
					<div class="form-group">
						<hr class="divider">
						<button type="submit" class="btn btn-default" id="lisaa" name="lisaa">Lisää</button>
					</div>
					
					<c:if test="${not empty posterror}">
						<p class="error">Sanan lisääminen epäonnistui:
						<c:if test="${posterror == 'A'}">
							Käyttäjätietojasi ei voitu varmentaa.
						</c:if>
						<c:if test="${posterror == 'B'}">
							Sana on virheellinen.
						</c:if>
						</p>
					</c:if>
				</form:form>
			</div>
		</sec:authorize>
		
		<div class="col-sm-8 text-center">
			<h3>Tilastot</h3>
			<div class="borderbox">
				<div class="table table-striped">
					<div class="thead">
						<div class="tr">
							<div class="th">Käyttäjä</div>
							<div class="th">Lisätyt sanat</div>
						</div>
					</div>
					<div class="tbody">
						<c:forEach items="${lisaajat}" var="l">
							<div class="tr">
								<div class="td">${l.etunimi} ${l.sukunimi} (${l.tunnus})</div>
								<div class="td">${l.lisatyt}</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
