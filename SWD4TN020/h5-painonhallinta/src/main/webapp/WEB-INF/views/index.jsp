<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H5 Painonhallinta</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/custom.css">
<script src="resources/js/jquery-3.1.1.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/chart.js"></script>
</head>
<body>
	<h1 class="titlebar">H5 Painonhallinta</h1>
	<div class="container">
		<div class="row text-center margintop64px">
			<form method="post" class="table" id="form">
				<label for="paino">Painosi:</label>
				<input type="number" name="paino" id="paino" min="0" max="999.99" step="0.01" required/>
				<button type="submit" class="btn btn-default">Kirjaa paino</button>
			</form>
		</div>
		<div class="row">
			<div class="col-md-6">
				<canvas id="painokaavio" width="400" height="400"></canvas>
			</div>
			<div class="col-md-6">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Paino</th>
							<th>Päiväys ja aika</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${painot}" var="p">
							<tr class="paino" data-paino="${p.paino}" data-pvm="${p.pvm}">
								<td><c:out value="${p.paino}" /></td>
								<td><c:out value="${p.pvm}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script>
	$(function() {
		var painoarray = new Array();
		var pvmarray = new Array();
		
		$(".paino").each(function(){
			painoarray.push(parseFloat($( this ).attr("data-paino")));
			pvmarray.push($( this ).attr("data-pvm"));
		});
		
		painoarray.reverse();
		pvmarray.reverse();
		
		var painokaavio = new Chart($("#painokaavio"), {
			type: 'line',
		    data: {
		    	labels: pvmarray,
		        datasets: [
		            {
		                label: "Painosi",
		                fill: false,
		                lineTension: 0.1,
		                backgroundColor: "rgba(75,192,192,0.4)",
		                borderColor: "rgba(75,192,192,1)",
		                borderJoinStyle: 'miter',
		                pointRadius: 1,
		                pointHitRadius: 10,
		                data: painoarray,
		            }
		        ]
		    },
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                	stepSize: 5,
		                }
		            }],
		            xAxes: [{
		                display: false
		            }]
		        }
		    }
		});
	});
	</script>
</body>
</html>
