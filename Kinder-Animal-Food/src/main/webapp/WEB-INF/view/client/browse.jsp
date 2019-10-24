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
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/browse.css" rel="stylesheet">
	  
  </head>
  <body>
	  <!-- Navigation -->
	  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav" style = "position:relative">
	    <div class="container">
	      <a class="navbar-brand js-scroll-trigger" href="#page-top">Kinder-Animal-Food</a>
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
	          <form:form action="${pageContext.request.contextPath}/logout" class="hidden" method="POST" id="logout-form">
		          	<input type = "submit"  id="logout-button" value = "Logout">
			  </form:form>
	        </ul>
	      </div>
	    </div>
	  </nav>
	  <div class="body-main">
		  <div class = "body-main-left-panel">
		  	<form action="${pageContext.request.contextPath}/client/browse">
		  		<div class="error-container">
					<div id="error"></div>
				</div>
				<!-- Brand name -->
				<div style="margin-bottom: 25px;" class="input-group">
					<span class="input-group-text" >Brand</span> 
					<input placeholder="Enter a brand name" name="brandName" class="form-control" id ="input-brand-name" autocomplete="off"/>
					<div id = "input-brand-name_autocomplete-list" class="autocomplete-items"></div>
				</div>
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-text" >Category</span> 
					<input placeholder="Enter Category" name="category" class="form-control"/>
				</div>
				
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-text">Rating</span> 
					<select class = "from-control" id = "brandRating" name="rating">
						<option value = "Best">Best</option>
						<option value = "Good">Good</option>
						<option value = "Avoid">Avoid</option>
					</select>
				</div>
				
				<div style="margin-top: 10px" class="form-group">						
					<div class="col-sm-6 controls">
							<button id = "submitButton" class="btn btn-primary">Search</button>
					</div>
				</div>
		  	</form>
		  </div>
		  <div class = "body-main-middle-panel">
		  	<%! int i = 0; %>
		  	<c:forEach var = "temp" items = "${brand}">
				<div class="brand-card">
					<div class = "brand-name">${temp.brand_name}</div>
					<div class = "brand-category">${temp.brand_category}</div>
					<img class = "brand-image" src="${temp.image}" alt="${temp.brand_name}" style="width:100%">
				</div>
			</c:forEach>
		  </div>
	  </div>
  </body>
</html>