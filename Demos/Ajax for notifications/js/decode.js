/**
Ejemplo para peticiones ajax cada 5 segundos.
El demo realiza una peitción GET al API de Google Maps para obtener una dirección,
le envia dos parametros:
1) latitud y longitud
2) sensor (no important)

Al recibir el json de respuesta capturo la direccion y lo muestro en el html.
**/

//ejecuta el metodo showDirectcion()  cada 3 segundos
var time = 1000*3;
setInterval(function() {showDirectcion()}, time); 

//estas variables van a rotar en base al indicador para mostrar dos direciones distintas,
//cuando el indicador es par se muestra la direccion de la USMP Virtual
//cuando el indicador es impar se muerta la direccion de la FIA [estaba por al biblioteca creo]
var USMPVirtual = "-12.092084,-77.0373340";
var USMPFIA = "-12.072049,-76.94216";
var indicador = 1;

//metodo que hace la petición
//tambien se puede acceder al json con esta url:  
//http://maps.googleapis.com/maps/api/geocode/json?latlng=-12.092084,-77.0373340&sensor=true
function showDirectcion(){

	//determino la direccion a buscar en base al indicador
	var latlngTemp = indicador % 2 == 0 ? USMPVirtual : USMPFIA;

	//construyo el json de parametros a enviar
	var dataServicio = {
			"latlng"   : latlngTemp,//parametro latlng
			"sensor" : true //parametro sensor
		};

	$.ajax({
         url:   'http://maps.googleapis.com/maps/api/geocode/json',//url a pedir, este sería el servlet
         type:  'GET',			//tipo de peticion [GET, POST.DELETE,PUT]
         dataType: "json",		//el formato con el que trabajamos, hoy en día todo se hace con Json
         data: dataServicio,	//los parametros que creamos arriba
         
         //si la peticion es exitosa, se ejecuta el siguiente codigo
         success:  function (response) {
        	 
        	 //quito la data actual que se muestra en el html
        	 $( ".address" ).remove();

        	 //creo el html que voy poner en la web
        	 var html='<article class="address" >'+response.results[0].formatted_address+'</article>';
        	
       		 //inserto el html en la web				
        	 $("#text").append(html);

        	 //sumo el indicador para que cambie la direccion
        	 indicador++;
        },
		
		//si la peticion no es exitosa se ejecuta el siguiente codigo
		error: function(response){
			//muestro en la consola [f12] el mensaje de error
			console.log("damm");
		}	});
}