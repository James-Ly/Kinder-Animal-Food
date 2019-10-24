<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

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
		  	<form action="${pageContext.request.contextPath}/client/locate">
		  		<div class="error-container">
					<div id="error"></div>
				</div>
				<!-- Brand name -->
				<div style="margin-bottom: 25px;" class="input-group">
					<span class="input-group-text" >Brand</span> 
					<input placeholder="Enter a brand name" class="form-control" id ="input-brand-name" autocomplete="off"/>
					<div id = "input-brand-name_autocomplete-list" class="autocomplete-items"></div>
				</div>
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-text" >Location</span> 
					<input placeholder="Enter a location" class="form-control" id="store_address"/>
				</div>
				
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-text">Search radius</span> 
					<select class = "from-control" id = "search_radius">
						<option value = "2500">2.5KM</option>
						<option value = "5000">5KM</option>
						<option value = "7500">7.5KM</option>
					</select>
				</div>
				
				<div style="margin-top: 10px" class="form-group">						
					<div class="col-sm-6 controls">
							<button id = "submitButton" class="btn btn-primary">Submit</button>
					</div>
				</div>
		  	</form>
		  </div>
		  <div class = "body-main-middle-panel">
		  	<div style="width: 100%; height: 100%; display:block-inline" id="mapContainer"></div>
		  </div>
		  <div class = "body-main-right-panel" id = "body-main-right-panel">
		  	
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
  	
  	
  	/***************************
  		INITIALIZE HERE MAP
  	***************************/
  	
    // Initialize the platform object:
    var platform = new H.service.Platform({
    'apikey': '7DzcWV9z5XZoj4Ii3-jKSOB8udT9rHXwHhrIdN6B85c'
    });

 // Get the default map types from the Platform object:
    var defaultLayers = platform.createDefaultLayers();

    // Instantiate the map:
    var map = new H.Map(
      document.getElementById('mapContainer'),
      defaultLayers.vector.normal.map,
      {
        zoom: 10,
        center: { lng:151.1157381,lat: -33.9121888 }
      });

    // Create the default UI:
    var ui = H.ui.UI.createDefault(map, defaultLayers);
    
    var group = new H.map.Group();
    
    $("#submitButton").click(function(event){
  		var brandName = $("#input-brand-name").val();
  		var location = $("#store_address")[0].value;
  		var searchRadius = $("#search_radius").val();
  		var search = {
  				"brandName":brandName,
  				"location":location,
  				"searchRadius":searchRadius
  		};
  		console.log(search);
  		$.ajax({
  		  type: "GET",
  		  url: "http://localhost:8080/Kinder-Animal-Food/api/locate",
  		  data: search,
  		  cache: false,
  		  success: function(data){
  		    console.log(data);
  		  	addInfoBubble(map,data,group);
  		  	addDataToRightPanel(data.storeList);
  		  },
  		  error: function(xhr, error){
  			  group.removeAll();
  		  }
  		});
  		event.preventDefault();
  	})

    // Add info Bubble for markers
	function addInfoBubble(map,data,group) {
		  group.removeAll();
		  
		  map.addObject(group);
	
		  // add 'tap' event listener, that opens info bubble, to the group
		  group.addEventListener('tap', function (evt) {
			  // event target is the marker itself, group is a parent event target
			  // for all objects that it contains
			  var bubble =  new H.ui.InfoBubble(evt.target.getGeometry(), {
			  // read custom data
			  content: evt.target.getData()
			  });
			  // show info bubble
			  ui.addBubble(bubble);
		  }, false);
		  var storeList = data.storeList;
		  for(i = 0;i < storeList.length ; i++){
			  addMarkerToGroup(group, {lat: storeList[i].store_latitude,lng:storeList[i].store_longitude},
					    '<div>'+storeList[i].store_name +
					    '</div><div >'+storeList[i].store_address+'</div>');
		  }
		  var circle = new H.map.Circle({lat: data.lat,lng:data.lng}, data.radius);
		  group.addObject(circle);
		  
		  map.setCenter({lat: data.lat,lng:data.lng});
		  map.setZoom(14);
		  /**addMarkerToGroup(group, {lat: -33.9121888,lng:151.1157381},
		    '<div><a href=\'http://www.mcfc.co.uk\' >Manchester City</a>' +
		    '</div><div >City of Manchester Stadium<br>Capacity: 48,000</div>'); **/
	}
	
	function addMarkerToGroup(group, coordinate, html) {
		var icon = new H.map.Icon('${pageContext.request.contextPath}/resources/img/visit.png');
		var marker = new H.map.Marker(coordinate,{icon:icon});
		// add custom data to the marker
		marker.setData(html);
		group.addObject(marker);
	}
	
	/******************************
		LOG OUT BUTTON
	******************************/
	$("#logout-button").click(function(event){
		event.preventDefault();
		$("#logout-form").submit();
	})
	
	/******************************
		MAP EVENT CONTROLLER
	******************************/
	
	var mapEvents = new H.mapevents.MapEvents(map);

	// Add event listener:
	map.addEventListener('tap', function(evt) {
	   // Log 'tap' and 'mouse' events:
	   console.log(evt.type, evt.currentPointer.type); 
	});
    
	var behavior = new H.mapevents.Behavior(mapEvents);
	
	/*******************************************
		APPEND DIV ELEMENT TO THE RIGHT PANEL
	*******************************************/
	
	function addDataToRightPanel(storeList){
		$("#body-main-right-panel").empty();
		var numberInList = 8;
		var currentPage = 1;
		var numberOfPage = Math.floor(storeList.length/numberInList);
		for(i = 0 ; i < storeList.length && i < numberInList ; i++){
			$("#body-main-right-panel").append($('<div class = "store-display">' +
			  		'<div class = "store-display-storeName">'+storeList[i].store_name+'</div>' +
			  		'<div class = "store-display-storeAddress">'+storeList[i].store_address+'</div></div>'));
		}
		if(storeList.length >= numberInList){
			$("#body-main-right-panel").append('<div class = "store-display-pagination">'+
			  		'<div class = "align-vertical disabled" id = "pagination-allleft"> << </div>' +
					'<div class = "align-vertical disabled" id = "pagination-left"> < </div>' +
					'<div class = "align-vertical active" id = "pagination-right">  > </div>'+
					'<div class = "align-vertical active" id = "pagination-allright"> >> </div></div>');	
		}
		document.getElementById("pagination-right").addEventListener("click",movingRight);
		document.getElementById("pagination-left").addEventListener("click",movingLeft);
		document.getElementById("pagination-allright").addEventListener("click",movingAllRight);
		document.getElementById("pagination-allleft").addEventListener("click",movingAllLeft);
		function movingRight(){
			var direction = "right";
			currentPage = movingPagination(direction,storeList,currentPage,numberInList);
		}
		function movingLeft(){
			var direction = "left";
			currentPage = movingPagination(direction,storeList,currentPage,numberInList);
		}
		function movingAllRight(){
			var direction = "allright";
			currentPage = movingPagination(direction,storeList,currentPage,numberInList);
		}
		function movingAllLeft(){
			var direction = "allleft";
			currentPage = movingPagination(direction,storeList,currentPage,numberInList);
		}
		
	}
	
	function movingPagination(direction,storeList,oldCurrentPage,numberInList){
		$("#body-main-right-panel").empty();
		var currentPage = oldCurrentPage;
		if(direction === "right"){
			currentPage = currentPage + 1;	
		}
		if(direction === "left"){
			currentPage = currentPage - 1;
		}
		if(direction === "allright"){
			currentPage = Math.floor(storeList.length/numberInList);
		}
		if(direction === "allleft"){
			currentPage = 1;
		}
		for(i = (currentPage-1)*numberInList ; i < storeList.length && i < currentPage*numberInList ; i++){
			$("#body-main-right-panel").append($('<div class = "store-display">' +
				  	'<div class = "store-display-storeName">'+storeList[i].store_name+'</div>' +
				  	'<div class = "store-display-storeAddress">'+storeList[i].store_address+'</div></div>'));
		}
		if(currentPage == 1){
			$("#body-main-right-panel").append('<div class = "store-display-pagination">'+
			  		'<div class = "align-vertical disabled" id = "pagination-allleft"> << </div>' +
					'<div class = "align-vertical disabled" id = "pagination-left"> < </div>' +
					'<div class = "align-vertical active" id = "pagination-right">  > </div>'+
					'<div class = "align-vertical active" id = "pagination-allright"> >> </div></div>');
			document.getElementById("pagination-right").addEventListener("click",movingRight);
			document.getElementById("pagination-allright").addEventListener("click",movingAllRight);
		} else if (currentPage == Math.floor(storeList.length/numberInList)){
			$("#body-main-right-panel").append('<div class = "store-display-pagination">'+
			  		'<div class = "align-vertical active" id = "pagination-allleft"> << </div>' +
					'<div class = "align-vertical active" id = "pagination-left"> < </div>' +
					'<div class = "align-vertical disabled" id = "pagination-right">  > </div>'+
					'<div class = "align-vertical disabled" id = "pagination-allright"> >> </div></div>');
			document.getElementById("pagination-left").addEventListener("click",movingLeft);
			document.getElementById("pagination-allleft").addEventListener("click",movingAllLeft);
		} else {
			$("#body-main-right-panel").append('<div class = "store-display-pagination">'+
			  		'<div class = "align-vertical active" id = "pagination-allleft"> << </div>' +
					'<div class = "align-vertical active" id = "pagination-left"> < </div>' +
					'<div class = "align-vertical active" id = "pagination-right">  > </div>'+
					'<div class = "align-vertical active" id = "pagination-allright"> >> </div></div>');
			document.getElementById("pagination-right").addEventListener("click",movingRight);
			document.getElementById("pagination-left").addEventListener("click",movingLeft);
			document.getElementById("pagination-allright").addEventListener("click",movingAllRight);
			document.getElementById("pagination-allleft").addEventListener("click",movingAllLeft);
		}
		
		function movingRight(){
			var direction = "right";
			currentPage = movingPagination(direction,storeList,currentPage,numberInList);
		}
		function movingLeft(){
			var direction = "left";
			currentPage = movingPagination(direction,storeList,currentPage,numberInList);
		}
		function movingAllRight(){
			var direction = "allright";
			currentPage = movingPagination(direction,storeList,currentPage,numberInList);
		}
		function movingAllLeft(){
			var direction = "allleft";
			currentPage = movingPagination(direction,storeList,currentPage,numberInList);
		}
		return currentPage;
	}
	
	/*******************************
		AUTO ADDRESS SEARCH
	********************************/
	function addressSearch() {
		// key for test
		var api_key = "RK7X3GFB9DVQEL6PNJ8U";
		// key for submit
		//var api_key = "U9GBLPQND8YEFC4TR6HJ";
		var widget, initAF = function() {
			widget = new AddressFinder.Widget($("#store_address")[0], api_key,
					'AU', {
						"address_params" : {
							"post_box" : "0"
						}
					});

			widget.on("result:select", function(fullAddress, metaData) {
				console.log("widge is selected");
				$('#store_address')[0].value = metaData.full_address;
				$('#store_state')[0].value = metaData.state_territory;
				$('#store_postcode')[0].value = metaData.postcode;
			});
		};

		$(document).ready(
				function() {
					$.getScript(
							'https://api.addressfinder.io/assets/v3/widget.js',
							initAF);
				});

	};
	addressSearch();
	
  </script>
  </body>
</html>