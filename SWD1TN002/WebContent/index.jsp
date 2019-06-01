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
<title>Autovuokraus</title>
</head>
<body>
	<nav><p>Autovuokraus</p></nav>
	<div id="main">
		<h1>Autojen vuokraukset</h1>
		<div>
			<form method="get" action="VuokrausOhjelma">
				<p>&#128270;</p> <input type="submit" name="action"	value="Hae kaikki vuokraukset" class="button"/>
			</form>
		</div>
		<div>
			<form name="haePäivältä" method="get" action="VuokrausOhjelma" onsubmit="return varmistaLomake()">
				<p>&#128270;</p> <input type="submit" name="action"	value="Hae vuokraukset päivältä" class="button"/>
				<input type="text" name="date" class="date"	placeholder="Valitse päivä" spellcheck="false"/>
			</form>
		</div>
		<div>
			<form method="get" action="VuokrausOhjelma">
				<p>&#10133;</p> <input type="submit" name="action" value="Lisää vuokraus" class="button"/>
			</form>
		</div>
	</div>
	<footer><p>Tatu Arvela, Hieu Bui, Nico Ersüs | SWD1TN002, Haaga-Helia, 2016</p></footer>
</body>
</html>