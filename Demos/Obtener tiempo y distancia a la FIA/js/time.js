var directionsService = new google.maps.DirectionsService();

function calcRoute() {

  var start = new google.maps.LatLng(
    document.getElementById("latitud").value,
    document.getElementById("longitud").value);
  
  var end = new google.maps.LatLng(-12.072049,-76.94216);

  var request = {
      origin:start,
      destination:end,
      travelMode: google.maps.TravelMode.DRIVING
  };

  directionsService.route(request, function(response, status) {
    
    if (status == google.maps.DirectionsStatus.OK) {

      document.getElementById("tiempo").innerHTML = "El tiempo promedio es de: "+
      "<strong>"+ response.routes[0].legs[0].duration.text+"</strong>";

      document.getElementById("distancia").innerHTML = "La distancia es de: "+
      "<strong>"+response.routes[0].legs[0].distance.text+"</strong>";
      
    }else{
      document.getElementById("tiempo").innerHTML = "Error al ingresar datos";
      document.getElementById("distancia").innerHTML = "";
      
    }
  });
}

