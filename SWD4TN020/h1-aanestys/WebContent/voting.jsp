<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><c:out value="${question}"/> - H1 ��nestys</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script>
	// Redirection to servlet page
		var url = window.location.href;
		var lastPart = url.substr(url.lastIndexOf('/') + 1);
		if (lastPart === "voting.jsp") {
			window.location.href = 'voting'; 
		}
	</script>
</head>
<body>
	<div class="header">
		<h1>H1 - ��nestys</h1>
	</div>
	
	<c:choose>
	<%-- If there are no questions, show a placeholder --%>
		<c:when test="${(question == null)}">
			<h2>Ei kysymyksi�</h2>
			<p class="big">Virhe: Yhteytt� tietokantaan ei voitu muodostaa, taulua ei ole tai se on tyhj�.<br/>
			Voit kokeilla uuden kysymyksen lis��mist�.</p>
		
			<br/>
			<br/>
			<div class="header"></div>
			<br/>
			
			<a href="newquestion.jsp">Lis�� kysymys</a>
		</c:when>		
	<%-- Otherwise load the question and answers --%>
		<c:otherwise>
			<h2><c:out value="${question}"/></h2>
		
			<%-- YES --%>
				<c:choose>
				<%-- Voting --%>
				    <c:when test="${!(not empty param.vote)}">
				        <a href="voting?vote=yes" onclick="parentNode.submit();">
				        	<span class="big">Kyll�</span><br/>
				        	<c:choose>
				        		<c:when test="${yes == 1}">
				        			(<c:out value="${yes}"/> ��ni)
				        		</c:when>
				        		<c:otherwise>
				        			(<c:out value="${yes}"/> ��nt�)
				        		</c:otherwise>
				        	</c:choose>
				        </a>
				    </c:when>
	    		<%-- Results --%>
				    <c:otherwise>
					    <c:choose>
					    	<c:when test="${(param.vote == 'yes')}"><p class="selected"></c:when>
					    	<c:otherwise><p></c:otherwise>
					    </c:choose>
				    		<span class="big">Kyll�</span><br/>
				    		<c:choose>
				        		<c:when test="${yes == 1}">
				        			(<c:out value="${yes}"/> ��ni)
				        		</c:when>
				        		<c:otherwise>
				        			(<c:out value="${yes}"/> ��nt�)
				        		</c:otherwise>
				        	</c:choose>
				    	</p>
				    </c:otherwise>
				</c:choose>
			
			<%-- NO --%>
				<c:choose>
				<%-- Voting --%>
				    <c:when test="${!(not empty param.vote)}">
				        <a href="voting?vote=no" onclick="parentNode.submit();">
				        	<span class="big">Ei</span><br/>
				        	<c:choose>
				        		<c:when test="${no == 1}">
				        			(<c:out value="${no}"/> ��ni)
				        		</c:when>
				        		<c:otherwise>
				        			(<c:out value="${no}"/> ��nt�)
				        		</c:otherwise>
				        	</c:choose>
				        </a>
				    </c:when>
		    	<%-- Results --%>
				    <c:otherwise>
					   	<c:choose>
					    	<c:when test="${(param.vote == 'no')}"><p class="selected"></c:when>
					    	<c:otherwise><p></c:otherwise>
					    </c:choose>
				    		<span class="big">Ei</span><br/>
				    		<c:choose>
				        		<c:when test="${no == 1}">
				        			(<c:out value="${no}"/> ��ni)
				        		</c:when>
				        		<c:otherwise>
				        			(<c:out value="${no}"/> ��nt�)
				        		</c:otherwise>
				        	</c:choose>
				    	</p>
				    </c:otherwise>
				</c:choose>
				<input type="hidden"/>
			
			<br/>
			<br/>
			<div class="header"></div>
			<br/>
			
			<c:choose>
			    <c:when test="${(not empty param.vote)}">
			        <a href="voting">��nest� uudelleen</a>
			    </c:when>
			</c:choose>
			<a href="newquestion">Vaihda kysymyst�</a>
		</c:otherwise>
	</c:choose>
</body>
</html>