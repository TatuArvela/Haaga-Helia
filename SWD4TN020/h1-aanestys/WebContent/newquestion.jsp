<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Vaihda kysymystä - H1 Äänestys</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script>
	// Redirection to servlet page
		var url = window.location.href;
		var lastPart = url.substr(url.lastIndexOf('/') + 1);
		if (lastPart === "newquestion.jsp") {
			window.location.href = 'newquestion'; 
		}
		
		function empty() {
		    var x;
		    x = document.getElementById("question").value;
		    if (x == "") {
				var q = document.getElementById("question");
				q.className += " invalid";
		        return false;
		    } else {
		    	document.getElementById('newquestion').submit();
		    }
		}
		
		function removealert() {
			document.getElementById("question").className = "";
		}
		

	</script>
</head>
<body>
	<div class="header">
		<h1>H1 - Äänestys</h1>
	</div>

	<form method="post" action="newquestion" name="newquestion" id="newquestion">
		<input type=text placeholder="Kirjoita tähän kysymyksesi" name="question" id="question" onclick="removealert()"/>
	</form>
	<p class="disabled"><span class="big">Kyllä</span><br/>
	(0 ääntä)</p>
	<p class="disabled"><span class="big">Ei</span><br/>
	(0 ääntä)</p>
	
	<br/>
	<br/>
	<div class="header"></div>
	<br/>
	
	<a href="voting">Palaa takaisin</a>
	<button onclick="empty()">Aseta kysymys</button>
</body>
</html>