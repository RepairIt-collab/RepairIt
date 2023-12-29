let latitude;
let longitud;
var coo
function requestLocationPermission() {
    if ('geolocation' in navigator) {
        navigator.geolocation.getCurrentPosition(
            successCallback,
            errorCallback,
            { enableHighAccuracy: true }
        );
        // return coo;
        
    } else {
        console.log('La géolocalisation n\'est pas prise en charge par ce navigateur.');
    }

 
  }

 
function successCallback(position) {
// Coordonnées de la position à afficher
 latitude = position.coords.latitude;
 longitud = position.coords.longitude;

 console.log("Latitude : " + latitude + "\nLongitude : " + longitud);
//  coo = latitude+"/"+longitud

    // Appeler une autre fonction ou effectuer d'autres opérations nécessitant latitude et longitude
    // autreFonction();
    
}

function autreFonction() {
    // Utiliser les variables latitude et longitude ici
    console.log("Utilisation de latitude et longitude : " + latitude + ", " + longitud);
}

function errorCallback(error) {
    // Ici, vous pouvez gérer les erreurs liées à la géolocalisation
    alert('Erreur de géolocalisation : ' + error.message);
  }


/*  function initMap(EndLat,EndLon) {
    requestLocationPermission()
    const directionsRenderer = new google.maps.DirectionsRenderer();
    const directionsService = new google.maps.DirectionsService();
    const mapM = new google.maps.Map(document.getElementById("map-maintenacier"), {
        zoom: 17,
        center: { lat: latitude, lng: longitud },
        disableDefaultUI: true,
    });

    directionsRenderer.setMap(mapM);
    directionsRenderer.setPanel(document.getElementById("sidebar-maintenacier"));

    // const control = document.getElementById("floating-panel");

    // mapM.controls[google.maps.ControlPosition.TOP_CENTER].push(control);

    const start = new google.maps.LatLng(latitude, longitud);
    const end = new google.maps.LatLng(EndLat, EndLon);
    calculateAndDisplayRoute(directionsService, directionsRenderer, start, end);

}

function calculateAndDisplayRoute(directionsService, directionsRenderer, start, end) {

    directionsService
        .route({
            origin: start,
            destination: end,
            travelMode: google.maps.TravelMode.DRIVING,
        })
        .then((response) => {
            directionsRenderer.setDirections(response);
        })
        .catch((e) => window.alert("Directions request failed due to " + status));
}*/