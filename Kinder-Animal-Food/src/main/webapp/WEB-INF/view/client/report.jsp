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
	          	<a class = "user-NameAnchor" id="user-NameAnchor" style="color:rgba(255,255,255,.5);">
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
	  	</div>
	  	<div class = "body-main-middle-panel">
	  		<div style="margin-bottom: 25px;" class="input-group">
				<span class="input-group-text" >Brand</span>
				<input placeholder="Enter a brand name" name="brandName" class="form-control" id ="input-brandName" value="${brandName}" data-userName="principal.username" autocomplete="off"/>
				<div id = "input-brand-name_autocomplete-list" class="autocomplete-items"></div>
			</div>
			<div style="margin-bottom: 25px;" class="input-group">
				<span class="input-group-text" >Store name</span>
				<input placeholder="Enter the store name" name="storeName" class="form-control" id ="input-storeName" autocomplete="off"/>
				<div id = "input-brand-name_autocomplete-list" class="autocomplete-items"></div>
			</div>
			<div style="margin-bottom: 25px;" class="input-group">
				<span class="input-group-text" >Store address</span>
				<input placeholder="Enter the store address" name="storeAddress" class="form-control" id="input-storeAddress" autocomplete="off"/>
				<div id = "input-brand-name_autocomplete-list" class="autocomplete-items"></div>
			</div>
			<div style="margin-bottom: 25px;" class="input-group">
				<span class="input-group-text" >Store postcode</span>
				<input placeholder="Enter the postcode" name="storePostCode" class="form-control" id="input-storePostCode" autocomplete="off"/>
				<div id = "input-brand-name_autocomplete-list" class="autocomplete-items"></div>
			</div>
			<div style="margin-bottom: 25px;" class="input-group">
				<span class="input-group-text" >Store state</span>
				<input placeholder="Enter the state" name="storeState" class="form-control" id="input-storeState" autocomplete="off"/>
				<div id = "input-brand-name_autocomplete-list" class="autocomplete-items"></div>
			</div>
			<div style="margin-top: 10px" class="form-group">						
				<div class="col-sm-6 controls">
					<button id = "submitButton" class="btn btn-primary">Submit</button>
				</div>
			</div>
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
	
	/******************************
			SUBMIT THE REPORT
	******************************/
	$("#submitButton").click(function(){
		var brandName = $("#input-brandName").val();
		var storeName = $("#input-storeName").val();
		var storeAddress = $("#input-storeAddress").val();
		var storePostCode = $("#input-storePostCode").val();
		var storeState = $("#input-storeState").val();
		var username = $("#user-NameAnchor").html().trim();
		console.log(username);
		if(!required(storeName)){
			return;
		}
		params = {
				brandName: brandName,
				storeName: storeName,
				storeAddress:storeAddress,
				storePostCode:storePostCode,
				storeState:storeState,
				username:username
		}
		post("http://localhost:8080/Kinder-Animal-Food/client/reportastore",params,"get");
	})
	
	function post(path, params, method) {
		method = method || "get"; // Set method to post by default if not specified.

        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        var form = document.createElement("form");
        form.setAttribute("method", method);
        form.setAttribute("action", path);
        for (var key in params) {
	        if (params.hasOwnProperty(key)) {
    	        var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", key);
                hiddenField.setAttribute("value", params[key]);
                form.appendChild(hiddenField);
            }
        }
        document.body.appendChild(form);
        form.submit();
        };
     function required(inputtx) 
        {
          if (inputtx.length == 0)
           { 
              alert("Please fill out all fields");  	
              return false; 
           }  	
           return true; 
         } 
	</script>
  </body>
</html>