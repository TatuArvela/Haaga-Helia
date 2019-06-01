<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>H6 Työtunnit</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="resources/css/custom.css">
<script src="resources/js/jquery-3.1.1.js"></script>
<script src="resources/js/moment.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
<script src="resources/js/fi.js"></script>
</head>
<body>
	<h1 class="titlebar">H6 Työtunnit</h1>
	<div class="container">
		<div class="col-sm-4 text-center">
			<h3>Lisää uusi työaikatieto</h3>
			<form method="post" class="borderbox" id="form">
				<div class="form-group">
					<p class="text-center bold">Aloitusaika</p>
					<div class="input-group date datetimepicker">
	                    <input type="text" class="form-control" name="aloitusaika" id="aloitusaika" required>
	                    <span class="input-group-addon">
	                        <span class="glyphicon glyphicon-calendar"></span>
	                    </span>
	                </div>
				</div>
				<div class="form-group">
					<p class="text-center bold">Lopetusaika</p>
					<div class="input-group date datetimepicker">
	                    <input type="text" class="form-control" name="lopetusaika" id="lopetusaika" required>
	                    <span class="input-group-addon">
	                        <span class="glyphicon glyphicon-calendar"></span>
	                    </span>
	                </div>
				</div>
				
				<div class="form-group row">
					<button type="submit" class="btn btn-default">Tallenna</button>
				</div>
			</form>
		</div>
		<div class="col-sm-8 text-center">
			<h3>Tallennetut työaikatiedot</h3>
			<div class="borderbox">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Aloitusaika</th>
							<th>Lopetusaika</th>
							<th>Kesto</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tyotunnit}" var="t">
							<tr data-role="rivi">
								<td data-role="aloitusaika" data-value="${t.aloitusaika}"><fmt:formatDate value="${t.aloitusaika}" pattern="dd.MM.yyyy HH.mm"/></td>
								<td data-role="lopetusaika" data-value="${t.lopetusaika}"><fmt:formatDate value="${t.lopetusaika}" pattern="dd.MM.yyyy HH.mm"/></td>
								<td data-role="kesto"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script>
	$(function () {
		$("#form").submit(function(event) {
		  if ($("#aloitusaika").val() > $("#lopetusaika").val()) {
			  alert("Tarkista aloitus- ja lopetusaika");
			  return false;
		  }
		});
	
	    $(".datetimepicker").datetimepicker({
	    	locale: 'fi'
	    });
	    
	    $("*[data-role='rivi']").each(function() {
	    	var aloitusaika = new Date($("[data-role='aloitusaika']", this).attr("data-value"));
	    	var lopetusaika = new Date($("[data-role='lopetusaika']", this).attr("data-value"));
	
	    	var minuutit = Math.floor((lopetusaika - aloitusaika)/1000/60);
	    	var tunnit = Math.floor(minuutit/60);
	    	minuutit = minuutit % 60;
	    	
	    	$("[data-role='kesto']", this).text(tunnit + "t " + minuutit + "m");
	    });
	});
	</script>
</body>
</html>
