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
.band {
  width: 90%;
  max-width: 1240px;
  margin: 0 auto;
  
  display: grid;
  
  grid-template-columns: 1fr;
  grid-template-rows: auto;
  grid-gap: 20px;
}

@media only screen and (min-width: 500px) {
  .band {
    grid-template-columns: 1fr 1fr;
  }  
}

@media only screen and (min-width: 850px) {
  .band {
    grid-template-columns: 1fr 1fr 1fr 1fr;
  }
}


.card {
  background: white;
  text-decoration: none;
  color: #444;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
  display: flex;
  flex-direction: column;
  min-height: 100%;
  
  position: relative;
  top: 0;
  transition: all .1s ease-in;
}
.price {
  color: grey;
  font-size: 22px;
}

.card button {
  border: none;
  outline: 0;
  padding: 12px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
  font-size: 18px;
  bottom:0px;
}

.card button:hover {
  opacity: 0.7;
}
.card article {
  padding: 20px;
}

.card .thumb {
  padding-bottom: 60%;
  background-size: cover;
  background-position: center center;
}

/* typography */
.card h1 {
  font-size: 20px;
  margin: 0;
  color: #333;
}

.card p { 
  line-height: 1.4;
}

.card span {
  font-size: 12px;
  font-weight: bold;
  color: #999;
  text-transform: uppercase;
  letter-spacing: .05em;
  margin: 2em 0 0 0;
}
.title {
 justify-content: center;
  text-align: center;
}
.pagination {
 justify-content: center;
  text-align: center;
}
.pagination a {
  display: block;
  width: 30px;
  height: 30px;
  float: left;
  margin-left: 3px;
  background: url(/images/structure/pagination-button.png);
}
.pagination a.last {
  width: 90px;
  background: url(/images/structure/pagination-button-last.png);
}
.pagination a.first {
  width: 60px;
  background: url(/images/structure/pagination-button-first.png);
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

<form method="get" action="${pageContext.request.contextPath}/search/">
       <div class="small-9 columns" style="width: 50%; margin: 0 auto;">
          <input type="text" id ="txt" name="search-term" style="width: 90%;">

          <button id="button-id" type="submit">Search</button>
       </div>

    </form>
<br>
<h3 class = "title ">Product List</h3>

<div class="band">
<c:if test="${!empty listProducts}">

	<c:forEach items="${listProducts}" var="product" varStatus="loop">
		<div class="card">
  			<img src="${product.image}" alt="${product.image}" style="width:100%">
  			<h1><a href= "${pageContext.request.contextPath}/products/${product.brand_id}"> ${product.brand_name}</a></h1>
			<p></p>
  			<p><a href= "${pageContext.request.contextPath}/products/${product.brand_id}"><button>View Details</button></a></p>
		</div>
		
	</c:forEach>

</c:if>
</div>

<div class='pagination'>
<c:forEach begin="0" end="${numPages}" varStatus="loop">

    <h1><a href= "${pageContext.request.contextPath}/browse/${loop.index+1}"> ${loop.index+1}</a></h1>
</c:forEach>
</div>
</body>
</html>