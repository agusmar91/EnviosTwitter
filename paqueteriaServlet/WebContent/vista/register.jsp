<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div align="center">

		<h2>Introduce los datos</h2>

		<form action="RegistrarController" method="post">
		
			Fecha de entrega: <input type="date" name="fecha"/> <br>

			Tamaño: <select name="paquete">
				<option value="pequeño">Pequeño</option>
				<option value="mediano">Mediano</option>
				<option value="grande">Grande</option>
			</select> <br> 
			Origen: <input id="origin-input" class="controls" size="40"
				type="text" placeholder="Introduce una localización de origen"	name="origen"> <br> 
				
			Destino:<input id="destination-input" class="controls" type="text" size="40"
				placeholder="Introduce una localización de destino" name="destino"><br>
			<br>
			Transportista: <input type="text" name="transportista"/> <br>
			Remitente: <input type="text" name="remitente"/> <br>
			Precio: <input type="text" name="precio"/> <br>
			
			 <input type="submit" value="Confirmar">

			<div id="map"></div>

		</form>

	</div> 
        
 <script type="text/javascript">
		function initMap() {
			var map = new google.maps.Map(document.getElementById('map'), {
				mapTypeControl : false,
				center : {
					lat : 40.428462,
					lng : -3.704267
				},
				zoom : 13
			});
			new AutocompleteDirectionsHandler(map);
		}
		/**
		 * @constructor
		 */
		function AutocompleteDirectionsHandler(map) {
			this.map = map;
			this.originPlaceId = null;
			this.destinationPlaceId = null;
			this.travelMode = 'WALKING';
			var originInput = document.getElementById('origin-input');
			var destinationInput = document.getElementById('destination-input');
			var modeSelector = document.getElementById('mode-selector');
			this.directionsService = new google.maps.DirectionsService;
			this.directionsDisplay = new google.maps.DirectionsRenderer;
			this.directionsDisplay.setMap(map);
			var originAutocomplete = new google.maps.places.Autocomplete(
					originInput, {
						placeIdOnly : true
					});
			var destinationAutocomplete = new google.maps.places.Autocomplete(
					destinationInput, {
						placeIdOnly : true
					});
			this.setupClickListener('changemode-walking', 'WALKING');
			this.setupClickListener('changemode-transit', 'TRANSIT');
			this.setupClickListener('changemode-driving', 'DRIVING');
			this.setupPlaceChangedListener(originAutocomplete, 'ORIG');
			this.setupPlaceChangedListener(destinationAutocomplete, 'DEST');
			this.map.controls[google.maps.ControlPosition.TOP_LEFT]
					.push(originInput);
			this.map.controls[google.maps.ControlPosition.TOP_LEFT]
					.push(destinationInput);
			this.map.controls[google.maps.ControlPosition.TOP_LEFT]
					.push(modeSelector);
		}
		// Sets a listener on a radio button to change the filter type on Places
		// Autocomplete.
		AutocompleteDirectionsHandler.prototype.setupClickListener = function(
				id, mode) {
			var radioButton = document.getElementById(id);
			var me = this;
			radioButton.addEventListener('click', function() {
				me.travelMode = mode;
				me.route();
			});
		};
		AutocompleteDirectionsHandler.prototype.setupPlaceChangedListener = function(
				autocomplete, mode) {
			var me = this;
			autocomplete.bindTo('bounds', this.map);
			autocomplete
					.addListener(
							'place_changed',
							function() {
								var place = autocomplete.getPlace();
								if (!place.place_id) {
									window
											.alert("Por favor, seleccione una opción de la lista desplegable.");
									return;
								}
								if (mode === 'ORIG') {
									me.originPlaceId = place.place_id;
								} else {
									me.destinationPlaceId = place.place_id;
								}
								me.route();
							});
		};
		AutocompleteDirectionsHandler.prototype.route = function() {
			if (!this.originPlaceId || !this.destinationPlaceId) {
				return;
			}
			var me = this;
			this.directionsService.route({
				origin : {
					'placeId' : this.originPlaceId
				},
				destination : {
					'placeId' : this.destinationPlaceId
				},
				travelMode : this.travelMode
			}, function(response, status) {
				if (status === 'OK') {
					me.directionsDisplay.setDirections(response);
				} else {
					window.alert('Solicitud de dirección fallido debido a '
							+ status);
				}
			});
		};
	</script>

 <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB29iPuIgyP_AyzGThitYhQjiLmHARo5HQ&libraries=places&callback=initMap"
        async defer></script>
</body>
</html>