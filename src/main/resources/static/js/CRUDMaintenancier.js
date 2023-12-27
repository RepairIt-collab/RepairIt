// CREATION D'UN MAINTENANCIER
function CreerMaintenancier()
{
   var Maintenancier={
     nom_complet:document.getElementById("nom").value,
     nom_utilisateur:document.getElementById("user2").value,
     password:document.getElementById("password2").value,
     mail:document.getElementById("mail2").value,
     sexe:document.getElementById("sexe").value,
     latitude:latitude,
     longitude:longitud,
     telephone:parseInt(document.getElementById("tel2").value,10),
     specialite:document.getElementById("specialite").value,
   }
   
   var jsonData = JSON.stringify(Maintenancier);
   const url = "https://127.0.0.1:9001/RepairIt/SignUp/newMaintenancier";

   fetch(url, {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
       },
       body: jsonData
   })
   .then(response => {
       console.log("Données reçues pour creerTaches");
       if (response.ok) {
        window.location.href = '/RepairIt/Maintenancier';
           return response.json(); // Renvoyer la réponse JSON
       } else {
           throw new Error('Erreur de la requête creerTaches');
       }
   })
   .then(data => {
       // Traitement des données renvoyées par le serveur
        console.log(data);
   })
   .catch(error => {
       // Gestion des erreurs
       console.error(error);
   });
   console.log(jsonData);
}

function updateLocation() {
    requestLocationPermission()
    // Récupérer la position actuelle du navigateur
    updateUserLocation(latitude, longitud);
  }
  
  // Fonction pour envoyer les nouvelles coordonnées de l'utilisateur au serveur
  function updateUserLocation(latitude, longitude) {
    var id = document.getElementById("mainmain").textContent
    var user = {
        telephone:id,
        latitude: latitude,
        longitude: longitude
    };
  
    // Envoyer les données utilisateur au serveur
    const url = "https://127.0.0.1:9001/update-location";
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    }).then(function(response) {
        if (response.ok) {
            console.log("Coordonnées utilisateur mises à jour avec succès !");
        } else {
            console.log("Une erreur s'est produite lors de la mise à jour des coordonnées de l'utilisateur.");
        }
    }).catch(function(error) {
        console.log(error);
    });
  }
  // Planifier la mise à jour des coordonnées toutes les 5 heures (en millisecondes)e
  var countdownInterval = setInterval(updateLocation, 5 * 60 * 60 * 1000);

  document.getElementById("valid2").addEventListener('click',CreerMaintenancier)