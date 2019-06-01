<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista</title>
<style>
body {
	width: 100%;
	margin: 69px auto auto auto;
	padding: 0px;
	font-family: sans-serif;
	background-color:#E9EBEE;
}

h1 {
	position: fixed;
	top: 0px;
	left: 0px;
	right: 0px;
	font-size: 22px;
	text-align: left;
	padding-left: 70px;
    display: block;
    height: 64px;
    width: calc(100% - 64px);
    margin: 0px;
    line-height: 64px;
    color: #F9F9F9;
    background: #3F51B5;
    text-shadow: 0px 0px 2px rgba(0, 0, 0, 0.26);
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.26);
    z-index: 1000;
}

body > div {
	display: block;
	width: 300px;
	padding: 20px;
	margin: 5px auto 5px auto;
	border-radius:3px;
	background: #F9F9F9;
	border-left:5px solid #3F51B5;
	box-shadow:0 2px 2px 0 rgba(0,0,0,0.14),
	0 3px 1px -2px rgba(0,0,0,0.2),
	0 1px 5px 0 rgba(0,0,0,0.12);
}

body > div > .label {
	display: inline-block;
	width: 90px;
	margin: 0px;
	text-align: right;
}

body > div > .value {
	display: inline-block;
	width: calc(300px - 64px - 50px);
	border: none;
    border-bottom: 1px dotted #3F51B5;
    background: none;
    margin: 0px 14px 0px 0px;
    font-size: 17px;
}

</style>
</head>
<body>
	<h1>5. viikkotentti</h1>
	<c:forEach items="${henkilot}" var="henkilo"><div>
			<p class="label">Etunimi</p>
			<p class="value"><c:out value="${henkilo.etunimi}"/></p>
			<p class="label">Sukunimi</p>
			<p class="value"><c:out value="${henkilo.sukunimi}"/></p>
	</div></c:forEach>
</body>
</html>
