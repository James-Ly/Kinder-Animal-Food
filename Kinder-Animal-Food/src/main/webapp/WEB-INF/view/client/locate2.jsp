<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
  <head>
	  <meta name="viewport" content="initial-scale=1.0, width=device-width" />
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-core.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-service.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-mapevents.js" type="text/javascript" charset="utf-8"></script>
	  <link rel="stylesheet" type="text/css" href="https://js.api.here.com/v3/3.1/mapsjs-ui.css" />
	  <script src="http://code.jquery.com/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
  </head>
  <body>
  <div class="left-panel" >
  	<form:form action="${pageContext.request.contextPath}/client/locate-brand" modelAttribute="crmLocate">
  		<div class="error-container">
			<form:errors path="brandName" class="error" />
			<form:errors path="location" class="error" />
		</div>
		
		
		<!-- Brand name -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-text">Brand</span> 
			<form:input path="brandName" placeholder="Enter a brand name" class="form-control" />
		</div>
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-text">Location</span> 
			<form:input path="location" placeholder="Enter a location" class="form-control" id="store_address" />
		</div>
		
		<div style="margin-top: 10px" class="form-group">						
			<div class="col-sm-6 controls">
					<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</div>
  	</form:form>
  </div>
  
  <div style="width: 640px; height: 480px" id="mapContainer"></div>
  
  
  
  <script>
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