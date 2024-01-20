var malatitude, malongitude;

if (navigator.geolocation) {
  navigator.geolocation.getCurrentPosition(function(position) {
    malatitude = position.coords.latitude;
    malongitude = position.coords.longitude;

  });
} else {
  console.log("La géolocalisation n'est pas supportée par votre navigateur.");
}


function OpenMap() {
    document.getElementById('map').style.display = "block";
    // requestLocationPermission()
    requestLocationPermission()
    initMapC()
    console.log("Utilisation de latitude et longitude : " + malatitude + ", " + malongitude);

    console.log("Latitude : " + malatitude + "\nLongitude : " + malongitude);
}

var map;

// Fonction appelée lorsque l'utilisateur clique sur la carte
function handleMapClick(event) {
    var latitude = event.latLng.lat();
    var longitude = event.latLng.lng();

    // Création d'un marqueur pour l'endroit cliqué
    var marker = new google.maps.Marker({
        position: { lat: latitude, lng: longitude },
        map: map
    });

    // Affichage des coordonnées dans une boîte de dialogue d'alerte
    console.log("Latitude : " + latitude + "\nLongitude : " + longitude);
    malatitude = latitude
    malongitude = longitude
}

// Initialisation de la carte Google Maps
function initMapC() {
    // requestLocationPermission()
    // console.log("Latitude : " + latitude + "\nLongitude : " + longitud);
    var mapOptions = {
        center: { lat: malatitude, lng: malongitude },
        zoom: 12
    };
    map = new google.maps.Map(document.getElementById("map"), mapOptions);

    // Ajout d'un écouteur d'événement pour le clic sur la carte
    google.maps.event.addListener(map, "click", handleMapClick);
}




