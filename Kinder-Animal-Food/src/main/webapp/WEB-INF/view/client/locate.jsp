<html>
  <head>
	  <meta name="viewport" content="initial-scale=1.0, width=device-width" />
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-core.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-service.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-mapevents.js" type="text/javascript" charset="utf-8"></script>
	  <script src="https://js.api.here.com/v3/3.1/mapsjs-ui.js" type="text/javascript" charset="utf-8"></script>
	  <link rel="stylesheet" type="text/css" href="https://js.api.here.com/v3/3.1/mapsjs-ui.css" />
  </head>
  <body>
  <img src="${pageContext.request.contextPath}/resources/img/visit.png" alt="Italian Trulli">
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

    // Create the default UI:
    var ui = H.ui.UI.createDefault(map, defaultLayers);
    
    var mapEvents = new H.mapevents.MapEvents(map);

    // Add info Bubble for markers
	function addInfoBubble(map) {
		  var group = new H.map.Group();

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

		  addMarkerToGroup(group, {lat: -33.9121888,lng:151.1157381},
		    '<div><a href=\'http://www.mcfc.co.uk\' >Manchester City</a>' +
		    '</div><div >City of Manchester Stadium<br>Capacity: 48,000</div>');
	}
	
	function addMarkerToGroup(group, coordinate, html) {
		var icon = new H.map.Icon('${pageContext.request.contextPath}/resources/img/visit.png');
		var marker = new H.map.Marker(coordinate,{icon:icon});
		// add custom data to the marker
		marker.setData(html);
		group.addObject(marker);
	}
	
	addInfoBubble(map);
	
  </script>
  </body>
</html>