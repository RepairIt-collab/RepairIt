function ValideClient()
{
   var Client={

     username:document.getElementById("user").value,
     password:document.getElementById("password").value,
     email:document.getElementById("mail").value,
     telephone:parseInt(document.getElementById("tel").value,10)
   }
   
   var jsonData = JSON.stringify(Client);
   const url = "https://127.0.0.1:9001/RepairIt/UpdateClient";

   fetch(url, {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
       },
       body: jsonData
   })
   .then(response => {
       console.log("Modifications reussie");
       if (response.ok) {
        window.location.href = "/RepairIt/Client"
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

// MODIFICATION D'UN MAINTENANCIER
function CreerMaintenancier()
{
   var Maintenancier={
     nom_utilisateur:document.getElementById("user2").value,
     password:document.getElementById("password2").value,
     mail:document.getElementById("mail2").value,
     telephone:parseInt(document.getElementById("tel2").value,10),
     specialite:document.getElementById("specialite").value,
   }
   
   var jsonData = JSON.stringify(Maintenancier);
   const url = "https://127.0.0.1:9001/RepairIt/UpdateMaintenancier";

   fetch(url, {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
       },
       body: jsonData
   })
   .then(response => {
      console.log("Modifications reussie");

      window.location.href = "/RepairIt/Maintenancier"
       if (response.ok) {
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