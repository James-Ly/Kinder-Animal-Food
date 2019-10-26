<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Product Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
  <head>
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-core.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-service.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-mapevents.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-ui.js" type="text/javascript" charset="utf-8"></script>
	  <link rel="stylesheet" type="text/css" href="https://js.api.here.com/v3/3.1/mapsjs-ui.css" />
	  <script src="http://code.jquery.com/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
	  <!-- Bootstrap core CSS -->
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
	  <!-- CSS for this page -->
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/locate.css" rel="stylesheet">
	  
	  
  </head>
  
  <body>
<style>
.item1 { grid-area: header;     border: 1px solid #dfdfdf; text-align: center;  padding: 10px;}
a{
color:#000;
}
.item2 { grid-area: image; width:50$; border: 1px solid #dfdfdf; text-align: center;}
.item3 { grid-area: description;  border: 1px solid #dfdfdf; text-align: center;  padding: 10%;}
.brand-rating { grid-area: rating;  border: 1px solid #dfdfdf; text-align: center;  padding: 10%;}
.item4 { grid-area: store; border: 1px solid #dfdfdf; display: grid; text-align: center;  padding: 10px;}
.grid-container > div {

  text-align: center;

  font-size: 30px;
}

.grid-container {
  display: grid;
  grid-template-areas:
    'image header'
    'image description'
    'image rating'
    'store store';
  grid-gap: 10px;
  grid-template-columns: 50% 50%;
  grid-template-rows: 20% 20%% 55%;
width:80%; margin: auto;
  padding: 10px;
}
table, th, td {
  border: 1px solid #dfdfdf;  
  font-size: 20px;
}
th, td {
width:50%}
}
table{
height:100%;  padding: 10px;
}
</style>

    <!-- Navigation -->
	  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav" style = "position:relative">
	    <div class="container">
	      <a class="navbar-brand js-scroll-trigger" href="#page-top">Kinder-Animal-Food</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	      <div class="collapse navbar-collapse" id="navbarResponsive">
	        <ul class="navbar-nav ml-auto">
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
  
  <div class = "product">
  
  <div class="grid-container">

<div class="item2">
  <img src="${product.image}" alt="${product.image}" style="width:90%">
  </div>
  <div class="item1">
  <h1><a href= "${pageContext.request.contextPath}/products/${product.brand_id}"> ${product.brand_name}</a></h1>
	</div>
	<div class="item3">
	<div class = "category" style ="margin: auto; ">
	<h1>Category</h1>
 	</div>
 <p>${product.brand_category}<p>
 </div>
	<div class="brand-rating">
	<h1>Accreditation</h1>
						<div>Best Rating: ${bestRating}</div>
						<div>Good Rating: ${goodRating}</div>
						<div>Avoid Rating: ${avoidRating}</div>
					</div>
 	<div class="item4">
 	 <h1>Rating</h1>

<table>
  <tr>
    <th>Accreditation</th>
    <th>Rating</th>
  </tr>
    	<c:forEach items="${accreditation}" var="accreditation">
		<tr>
    <td>${accreditation.accreditation_name}</td>
    <td>${accreditation.rating}</td>
  </tr>

</c:forEach>

  </tr>
</table>

	</div>
  </div>
 </div>
  

  
  
  
  
  
  
  
  
  
  
  </body>
</html>