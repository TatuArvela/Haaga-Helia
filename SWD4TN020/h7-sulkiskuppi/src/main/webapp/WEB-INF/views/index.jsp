<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>H7 Sulkiskuppi</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="resources/css/custom.css">
<script src="resources/js/jquery-3.1.1.js"></script>
<script src="resources/js/moment.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
<script src="resources/js/fi.js"></script>
<script>
$(function () {
    $(".datetimepicker").datetimepicker({
    	locale: 'fi'
    });
    $("select").click(function() {
    	$('#virhe').remove();
    });
    $("select").focus(function() {
    	$('#virhe').remove();
    });
});

function tarkista() {
	var valitut = $( "select" )
		.map(function() {
			return this.value;
		})
		.get()
		.join();
	valitut = valitut.split(",");
	valitut = valitut.filter(function(e) { return e !== '0' });
	if (valitut.length === new Set(valitut).size) {
		return true;
	}
	else {
		$("#tallenna").before( "<p id='virhe' style='color: red;'>Kunkin pelaajan voi valita vain kerran</p>" );
		return false;
	}
}
</script>
</head>
<body>
	<h1 class="titlebar">H7 Sulkiskuppi</h1>
	<div class="container">
		<div class="col-sm-4 text-center">
			<h3>Lis‰‰ uusi ottelutieto</h3>
			<form method="post" class="borderbox" onsubmit="return tarkista();">
				<div class="form-group">
					<p class="text-center bold">Ottelup‰iv‰- ja aika</p>
					<div class="input-group date datetimepicker">
	                    <input type="text" class="form-control" name="pvm" id="pvm" required>
	                    <span class="input-group-addon">
	                        <span class="glyphicon glyphicon-calendar"></span>
	                    </span>
	                </div>
				</div>
				<div class="form-group">
					<p class="text-center bold">Joukkue 1</p>
	                <select class="form-control" name="joukkue1_pelaaja1" id="joukkue1_pelaaja1" required>
	                	<c:forEach items="${pelaajat}" var="p">
							<option value="${p.id}">${p.etunimi} ${p.sukunimi}</option>
						</c:forEach>
	                </select>
	                <select class="form-control" name="joukkue1_pelaaja2" id="joukkue1_pelaaja2">
	                	<option value="1"></option>
	                	<c:forEach items="${pelaajat}" var="p">
							<option value="${p.id}">${p.etunimi} ${p.sukunimi}</option>
						</c:forEach>
	                </select>
	                
				</div>
				<div class="form-group">
					<p class="text-center bold">Joukkue 2</p>
	                <select class="form-control" name="joukkue2_pelaaja1" id="joukkue2_pelaaja1" required>
	                	<c:forEach items="${pelaajat}" var="p">
							<option value="${p.id}">${p.etunimi} ${p.sukunimi}</option>
						</c:forEach>
	                </select>
	                <select class="form-control" name="joukkue2_pelaaja2" id="joukkue2_pelaaja2">
	                	<option value="1"></option>
	                	<c:forEach items="${pelaajat}" var="p">
							<option value="${p.id}">${p.etunimi} ${p.sukunimi}</option>
						</c:forEach>
	                </select>
				</div>
				<div class="form-group row">
					<p class="text-center bold">Pisteet</p>
					<div class="col-xs-6">
	                	<input type="number" class="form-control" size="3" name="joukkue1_pisteet" id="joukkue1_pisteet" required min="0">
	                </div>
	                <div class="col-xs-6">
	                	<input type="number" class="form-control" size="3" name="joukkue2_pisteet" id="joukkue2_pisteet" required min="0">
	                </div>
				</div>
				<div class="form-group">
					<hr class="divider">
					<button type="submit" class="btn btn-default" id="tallenna">Tallenna</button>
				</div>
			</form>
		</div>
		<div class="col-sm-8 text-center">
			<h3>K‰ydyt ottelut</h3>
			<div class="borderbox">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Ottelup‰iv‰- ja aika</th>
							<th>Joukkue 1</th>
							<th>Joukkue 2</th>
							<th>Pisteet</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ottelut}" var="o">
							<tr>
								<td><fmt:formatDate value="${o.pvm}" pattern="dd.MM.yyyy HH.mm"/></td>
								<td>${o.joukkue1_pelaaja1.etunimi} ${o.joukkue1_pelaaja1.sukunimi}
								<c:if test="${not empty o.joukkue1_pelaaja2}">
									<br/>${o.joukkue1_pelaaja2.etunimi} ${o.joukkue1_pelaaja2.sukunimi}
								</c:if>
								</td>
								<td>${o.joukkue2_pelaaja1.etunimi} ${o.joukkue2_pelaaja1.sukunimi}
								<c:if test="${not empty o.joukkue2_pelaaja2}">
									<br/>${o.joukkue2_pelaaja2.etunimi} ${o.joukkue2_pelaaja2.sukunimi}
								</c:if>
								</td>
								<td>${o.joukkue1_pisteet} - ${o.joukkue2_pisteet}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
