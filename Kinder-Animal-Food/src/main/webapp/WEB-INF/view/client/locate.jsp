<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
  <head>
	  <meta name="viewport" content="initial-scale=1.0, width=device-width" />
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-core.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-service.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-mapevents.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-ui.js" type="text/javascript" charset="utf-8"></script>
	  <link rel="stylesheet" type="text/css" href="https://js.api.here.com/v3/3.1/mapsjs-ui.css" />
	  <script src="http://code.jquery.com/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
  </head>
  <body>
  <div class="left-panel" >
  	<form action="${pageContext.request.contextPath}/client/locate">
  		<div class="error-container">
			<div id="error"></div>
		</div>
		<!-- Brand name -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-text" >Brand</span> 
			<input placeholder="Enter a brand name" class="form-control" id ="input-brand-name" />
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
  
  <div style="width: 640px; height: 480px" id="mapContainer"></div>
  
  
  
  <script>
  	//alert("${location}");
  	
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
		MAP EVENT CONTROLLER
	******************************/
	
	var mapEvents = new H.mapevents.MapEvents(map);

	// Add event listener:
	map.addEventListener('tap', function(evt) {
	   // Log 'tap' and 'mouse' events:
	   console.log(evt.type, evt.currentPointer.type); 
	});
    
	var behavior = new H.mapevents.Behavior(mapEvents);
	
	
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