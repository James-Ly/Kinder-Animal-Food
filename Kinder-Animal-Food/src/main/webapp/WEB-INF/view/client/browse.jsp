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
					<select class = "from-control" id = "brandCategory" name="category">
						<option value = "All">All</option>
						<c:forEach var = "temp" items = "${categoryList}">
						<option value = "${temp}">${temp}</option>
						</c:forEach>
					</select>
				</div>
				
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-text">Rating</span> 
					<select class = "from-control" id = "brandRating" name="rating">
						<option value = "All">All</option>
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
					<div class = "brand-name">${temp.brand.brand_name}</div>
					<div class = "brand-category">Category: ${temp.brand.brand_category}</div>
					<img class = "brand-image" src="${temp.brand.image}" alt="${temp.brand.brand_name}" style="width:100%">
					<div class="brand-rating">
						<div>Best Rating: ${temp.bestRating}</div>
						<div>Good Rating: ${temp.goodRating}</div>
						<div>Avoid Rating: ${temp.avoidRating}</div>
					</div>
				</div>
			</c:forEach>
		  </div>
	  </div>
	<script>
	/**********************************
  	DROP DOWN LIST FOR THE BRAND NAME 
  	***********************************/
  	function autocomplete(inp){
		var currentFocus;
		inp.addEventListener("input",function(e){
			var a,b,i,val = this.value;
			closeAllLists();
			if(!val){
				return false;
			}
			currentFocus = -1;
			var parameters = {"query" : val};
			var jqxhr = $.get("http://localhost:8080/Kinder-Animal-Food/api/brandName",parameters)
			jqxhr.done(function(result){
				closeAllLists();
				var myBrand = result;
				a = document.getElementById("input-brand-name_autocomplete-list");
				a.style.position = "absolute";
				a.style.width = '100%';
				a.style.boxShadow = '0px 8px 16px 0px rgba(0,0,0,0.2)';
				for(var i =0; i < myBrand.length ;i++){
					b=document.createElement("A");
					b.setAttribute('title',myBrand[i].brand_name);
					b.innerHTML += myBrand[i].brand_name;	

					b.className = "myInput__autocomplete-item";
					b.style.display='block';
					b.style.background = 'white';
					b.style.cursor = 'pointer';
					b.style.padding = '2.5% .5%';
					b.addEventListener("mouseover",function(e){
						this.style.background = 'rgb(245,245,250)';
					})
					b.addEventListener("mouseout",function(e){
						this.style.background = 'white';
					})

					b.addEventListener("click",function(e){
						$("#input-brand-name").val(this.innerHTML);
					});
					a.appendChild(b);
			}
			});
			jqxhr.fail(function(jqxhr){
				console.log(jqxhr.status);
			});
			
		});
	
		function addActive(x){
			if(!x){
				return false;
			}
			removeActive(x);
			if(currentFocus >= x.length){
				currentFocus = 0;
			}
			if (currentFocus < 0){
				currentFocus = (x.length - 1);
			}
			x[currentFocus].classLists.add("autocomplete-active");
		}

		function removeActive(x){
			for(var i = 0 ; i < x.length ; i++){
				x[i].classList.remove("autocomplete-active");
			}
		}
		function closeAllLists(){
			var x = document.getElementById("input-brand-name_autocomplete-list");
			var fc = x.firstChild;
			while(fc){
				x.removeChild(x.firstChild);
				fc = x.firstChild;
			}
		}
		document.addEventListener("click",function(e){
			closeAllLists(e.target);
		})
	}

	/*initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:*/
	autocomplete(document.getElementById("input-brand-name"));
	
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