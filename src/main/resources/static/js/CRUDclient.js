// CREATION D'UN CLIENT
function ValideClient()
{
   var Client={

     username:document.getElementById("user").value,
     password:document.getElementById("password").value,
     email:document.getElementById("mail").value,
     telephone:parseInt(document.getElementById("tel").value,10)
   }
   
   var jsonData = JSON.stringify(Client);
   const url = "https://127.0.0.1:9001/RepairIt/SignUp/newClient";

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
        window.location.href = '/RepairIt/Client';
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

document.getElementById("valid1").addEventListener('click',ValideClient);