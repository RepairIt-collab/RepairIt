
//MAPS DU MAINTENANCIER
var malatitude, malongitude,latClient,lonClient;
lonClient=malongitude
latClient=malatitude
const moyen="DRIVING";
if (navigator.geolocation) {
  navigator.geolocation.getCurrentPosition(function(position) {
    malatitude = position.coords.latitude;
    malongitude = position.coords.longitude;
  });
} else {
  console.log("La géolocalisation n'est pas supportée par votre navigateur.");
}

function receiveMap(EndLat,EndLon,mode){
  latClient=EndLat
  lonClient = EndLon
  console.log(mode)
  initMap()
  var modal = new bootstrap.Modal(document.getElementById("ModalMap"));
modal.show();
}
function initMap() {
  console.log(latClient)
  console.log(lonClient)
    document.getElementById("latitudecache").value=latClient
    document.getElementById("longitudecache").value=lonClient
    requestLocationPermission()
    const directionsRenderer = new google.maps.DirectionsRenderer();
    const directionsService = new google.maps.DirectionsService();
    // console.log(document.getElementById("map-maintenacier"))
    const mapM = new google.maps.Map(document.getElementById("map-maintenacier"), {
        zoom: 17,
        center: { lat: malatitude, lng: malongitude },
        disableDefaultUI: true,
    });

    directionsRenderer.setMap(mapM);
    // directionsRenderer.setPanel(document.getElementById("sidebar-maintenacier"));

    console.log(document.getElementById("map-maintenacier"))
    const control = document.getElementById("floating-panel");

    mapM.controls[google.maps.ControlPosition.TOP_CENTER].push(control);

    // const control = document.getElementById("floating-panel");

    // mapM.controls[google.maps.ControlPosition.TOP_CENTER].push(control);
    console.log("ok")
    const start = new google.maps.LatLng(malatitude, malongitude);
    const end = new google.maps.LatLng(latClient, lonClient);
    console.log("okok")
    calculateAndDisplayRoute(directionsService, directionsRenderer, start, end, moyen);

}

function changeMode(mode){
    var EndLat= document.getElementById("latitudecache")
    var EndLon=document.getElementById("longitudecache")
    initMap(EndLat,EndLon,mode)
}

function calculateAndDisplayRoute(directionsService, directionsRenderer, start, end, moyen) {

    directionsService
        .route({
            origin: start,
            destination: end,
            travelMode: google.maps.TravelMode[moyen],
        })
        .then((response) => {
            directionsRenderer.setDirections(response);
        })
        .catch((e) => console.log("Directions request failed due to " + status));
}

$('#ModalMap').on('shown.bs.modal', function () {
  initMap();
});

window.initMap = initMap;

/**
 *  function initMap() {
          const directionsRenderer = new google.maps.DirectionsRenderer();
          const directionsService = new google.maps.DirectionsService();
          const mapM = new google.maps.Map(document.getElementById("map"), {
            zoom: 17,
            center: { lat: 41.85, lng: -87.65 },
            disableDefaultUI: true,
        });

  directionsRenderer.setMap(mapM);
  directionsRenderer.setPanel(document.getElementById("sidebar"));

  const control = document.getElementById("floating-panel");

  mapM.controls[google.maps.ControlPosition.TOP_CENTER].push(control);

  // const onChangeHandler = function () {
    const start = new google.maps.LatLng(3.857, 11.503);//document.getElementById("start").value;
  const end = new google.maps.LatLng(3.78, 11.50);
    calculateAndDisplayRoute(directionsService, directionsRenderer,start,end);
  // };

  //document.getElementById("start").addEventListener("change", onChangeHandler() //);
  // document.getElementById("end").addEventListener("change", onChangeHandler);
}

function calculateAndDisplayRoute(directionsService, directionsRenderer,start,end) {
   //document.getElementById("end").value;

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
}

window.initMap = initMap;

 */

