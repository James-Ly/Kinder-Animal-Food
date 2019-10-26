<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html>
  <head>
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	  <script src="http://code.jquery.com/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
	  <!-- Bootstrap core CSS -->
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
	  <!-- CSS for this page -->
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/brandDetail.css" rel="stylesheet">
	  
  </head>
  <body>
	  <!-- Navigation -->
	  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav" style = "position:relative">
	    <div class="container">
	      <a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath}">Kinder-Animal-Food</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	      <div class="collapse navbar-collapse" id="navbarResponsive">
	        <ul class="navbar-nav ml-auto">
	        <security:authorize access="hasRole('ADMIN')">  
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/systems/">Admin Page</a>
	          </li>
	         </security:authorize>
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}">Browse</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/client/locate">Locate</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="/" id = "logout-button">Logout</a>
	          </li>
	          <li class = "nav-item user-Name">
	          	<a class = "user-NameAnchor" style="color:rgba(255,255,255,.5);">
	          		<security:authentication property="principal.username" />
	          	</a>
	          </li>
	          <form:form action="${pageContext.request.contextPath}/logout" class="hidden" method="POST" id="logout-form">
		          	<input type = "submit"  id="logout-button" value = "Logout">
			  </form:form>
	        </ul>
	      </div>
	    </div>
	  </nav>
	  <div class="body-main">
	  	<div class = "body-main-left-panel">
	  		<a class = "body-main-left-back" href="${pageContext.request.contextPath}">Back</a>
	  		<a class = "body-main-left-report" href="${pageContext.request.contextPath}/client/report?brand=${brand.brand_name}">Recommend location</a>
	  		<a class = "body-main-left-locate" href="${pageContext.request.contextPath}/client/locate">Locate this brand</a>

	  	</div>
	  	<div class = "body-main-middle-panel">
	  		<div class = "brand-name">Brand name: ${brand.brand_name}</div>
	  		<div class = "brand-category">Brand category: ${brand.brand_category}</div>
	  		<div class = "accreditation-container">
	  			<div class = "accreditation-explain">
	  				In the section below, more information on the the rating that different organizations have on this particular product will be disclosed.
	  				<br>
	  				<br>
	  				As the name indicates, "Best" and "Good" ratings suggest that the animal was raised in a decent environment before further processed into our foods.
	  				<br>
	  				<br>
	  				"Avoid" illustrates that the animal living condition did not satisfy standards enforced by Animal Welfare organizations and should be avoided.
	  			</div>
	  			<table class = "accreditation-wrapper">
	  				<tr>
	  					<th colspan = "2">Accreditation</th>
	  					<th class = "table-col-1">Rating</th>
	  				</tr>
	  				<c:if test="${number[0] > 0 }">
	  					<c:forEach var = "temp0" items = "${accBest}">
	  						<tr>
		  						<td colspan = "2">${temp0.accreditation_name}</td>
		  						<td class = "table-col-1">${temp0.rating}</td>
		  					</tr>
	  					</c:forEach>
	  				</c:if>
	  				<c:if test="${number[1] > 0 }">
	  					<c:forEach var = "temp1" items = "${accGood}">
	  						<tr>
		  						<td colspan = "2">${temp1.accreditation_name}</td>
		  						<td class = "table-col-1">${temp1.rating}</td>
	  						</tr>
	  					</c:forEach>
	  				</c:if>
	  				<c:if test="${number[2] > 0 }">
	  					<c:forEach var = "temp2" items = "${accAvoid}">
	  						<tr>
		  						<td colspan = "2">${temp2.accreditation_name}</td>
		  						<td class = "table-col-1">${temp2.rating}</td>
		  					</tr>
	  					</c:forEach>
	  				</c:if>
	  				<tfoot>
		  				<tr>
		  					<th class = "table-col-1">Best Rating: ${number[0]}</th>
		  					<th class = "table-col-1">Good Rating: ${number[1]}</th>
		  					<th class = "table-col-1">Avoid Rating: ${number[2]}</th>
		  				</tr>
	  			</tfoot>
	  			</table>
	  			
	  		</div>
	  	</div>
	  	<div class = "body-main-right-panel">
	  		<img class = "brand-image" src="${brand.image}" alt="${brand.brand_name}">
	  	</div>
	  	
	  	
	  
	  </div>
	<script>	
	/******************************
			LOG OUT BUTTON
	******************************/
	$("#logout-button").click(function(event){
		event.preventDefault();
		$("#logout-form").submit();
	})
	
	</script>
  </body>
</html>