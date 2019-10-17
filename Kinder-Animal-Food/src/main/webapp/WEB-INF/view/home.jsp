<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>KINDER ANIMAL FOOD Home Page</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/test.css">
</head>

<body>
	<h2 class="test">KINDER ANIMAL FOOD Home Page</h2>
	<hr>
	
	<p>
	Welcome to the KINDER ANIMAL FOOD home page!
	</p>
	
	<hr>
	
	<!-- display user name and role -->
	
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
		<br><br>
	</p>
	
	
	<security:authorize access="hasRole('ADMIN')">  

		<!-- Add a link to point to /systems ... this is for the admins -->
		
		<p>
			<a href="${pageContext.request.contextPath}/systems/">Admin page</a>
		</p>
	
	</security:authorize>
	<a href="${pageContext.request.contextPath}/locate">Locate page</a>
	
	<hr>
	
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
</body>

</html>









