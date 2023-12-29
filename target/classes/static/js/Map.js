function OpenMap() {
    document.getElementById('map').style.display = "block";
    // requestLocationPermission()
    requestLocationPermission()
    initMapC()
    console.log("Utilisation de latitude et longitude : " + latitude + ", " + longitud);

    console.log("Latitude : " + latitude + "\nLongitude : " + longitud);
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
    latitude = latitude
    longitud = longitude
}

// Initialisation de la carte Google Maps
function initMapC() {
    // requestLocationPermission()
    // console.log("Latitude : " + latitude + "\nLongitude : " + longitud);
    var mapOptions = {
        center: { lat: 3.857, lng: 11.503 },
        zoom: 12
    };
    map = new google.maps.Map(document.getElementById("map"), mapOptions);

    // Ajout d'un écouteur d'événement pour le clic sur la carte
    google.maps.event.addListener(map, "click", handleMapClick);
}





//MAPS DU MAINTENANCIER

function initMap(EndLat,EndLon,mode) {
    requestLocationPermission()
    console.log(position)
    const directionsRenderer = new google.maps.DirectionsRenderer();
    const directionsService = new google.maps.DirectionsService();
    console.log(document.getElementById("map-maintenacier"))
    console.log(coo)
    const mapM = new google.maps.Map(document.getElementById("map-maintenacier"), {
        zoom: 17,
        center: { lat: latitude, lng: longitud },
        disableDefaultUI: true,
    });

    directionsRenderer.setMap(mapM);
    directionsRenderer.setPanel(document.getElementById("sidebar-maintenacier"));

    console.log(document.getElementById("map-maintenacier"))
    const control = document.getElementById("floating-panel");

    mapM.controls[google.maps.ControlPosition.TOP_CENTER].push(control);

    // const control = document.getElementById("floating-panel");

    // mapM.controls[google.maps.ControlPosition.TOP_CENTER].push(control);

    const start = new google.maps.LatLng(latitude, longitud);
    const end = new google.maps.LatLng(EndLat, EndLon);
    calculateAndDisplayRoute(directionsService, directionsRenderer, start, end,mode);

}

function calculateAndDisplayRoute(directionsService, directionsRenderer, start, end, mode) {

    directionsService
        .route({
            origin: start,
            destination: end,
            travelMode: google.maps.TravelMode.mode,
        })
        .then((response) => {
            directionsRenderer.setDirections(response);
        })
        .catch((e) => console.log("Directions request failed due to " + status));
}

// window.initMap = initMap;

