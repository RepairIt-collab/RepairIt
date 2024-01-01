document.getElementById("a").addEventListener("click", function () {

  var el1 = document.querySelector(".action");
  var el2 = document.querySelector(".taches");
  var el3 = document.querySelector(".calendar1");
  var el4 = document.querySelector(".messagerie2");
  var el5 = document.querySelector(".bienvenu");
  var el7 = document.querySelector(".evaluations");

  el1.style.display = "block";
  el2.style.display = "block";
  el3.style.display = "none";
  el4.style.display = "none";
  el5.style.display = "none";
  el7.style.display = "none";

});

// document.getElementById("b").addEventListener("click", function () {

//   var el1 = document.querySelector(".action");
//   var el2 = document.querySelector(".taches");
//   var el3 = document.querySelector(".calendar1");
//   var el4 = document.querySelector(".messagerie2");
//   var el5 = document.querySelector(".bienvenu");
//   var el6 = document.querySelector(".sous-menu");

//   el1.style.display = "none";
//   el2.style.display = "none";
//   el3.style.display = "block";
//   el4.style.display = "none";
//   el5.style.display = "none";
//   el6.style.display = "none";
// });

// document.getElementById("c").addEventListener("click", function () {

//   var el1 = document.querySelector(".action");
//   var el2 = document.querySelector(".taches");
//   var el3 = document.querySelector(".calendar1");
//   var el4 = document.querySelector(".messagerie2");
//   var el5 = document.querySelector(".bienvenu");


//   el1.style.display = "none";
//   el2.style.display = "none";
//   el3.style.display = "none";
//   el4.style.display = "block";
//   el5.style.display = "none";
//   el6.style.display = "none";
// });

document.getElementById("f").addEventListener("click", function () {

  var el1 = document.querySelector(".action");
  var el2 = document.querySelector(".taches");
  var el3 = document.querySelector(".calendar1");
  var el4 = document.querySelector(".messagerie2");
  var el5 = document.querySelector(".bienvenu");
  var el6 = document.querySelector(".sous-menu");
  var el7 = document.querySelector(".evaluations");

  el1.style.display = "none";
  el2.style.display = "none";
  el3.style.display = "none";
  el4.style.display = "none";
  el5.style.display = "none";
  el6.style.display = "none";
  el7.style.display = "block";
  heureEval()
  setInterval(heureEval, 1000 * 60);
});




//CODE POUR GERER LA RECEPTION DE NOTIFFICATION VIA LES WEBSOCKETS

var stompClient = null;

function connectM() {
  var socket = new SockJS('/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    var span = document.getElementById("mainmain").textContent;
    console.log(span)
    stompClient.subscribe('/topic/notification/' + span, function (message) {
      var notification = message.body;
      console.log(notification)
      sendNotificationN(notification);
    });


  });
}

function sendNotificationN(notification) {
  // Vérifier si le navigateur prend en charge les notifications
  if ('Notification' in window) {
    Notification.requestPermission()
    if (Notification.permission === 'granted') {
      // Créer une nouvelle notification
      var options = {
        body: notification,
        icon: 'public/images/Outil.png' // Spécifiez le chemin vers l'icône de la notification
      };
      var notification = new Notification('Nouvelle taches', options);


      // Gérer les événements de la notification (clic, fermeture, etc.)
      notification.onclick = function (event) {
        document.getElementById("btnNotification").click()
      };

      notification.onclose = function (event) {

        // Gérer la fermeture de la notification
        //ajouter le truc bleu sur l'icone 
      };

      // ...
    }
  }
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  console.log("Disconnected");
}


function showNotification(message) {
  var notificationsDiv = document.getElementById("notifications");
  notificationsDiv.innerHTML += "<p>" + message + "</p>";
}



connectM();

function onLoadM() {
  var idMaintenancier = document.querySelectorAll("#idMaintenancier-disabled")
  if (idMaintenancier.value != null) {
    var parent = idMaintenancier.parentNode
    var iN = parent.querySelectorAll("i")
    for (let j = 0; j < iN.length; j++) {
      iN[j].disabled = true
    }
    // document.querySelectorAll("i")
  }
}

function ValiderTacheId(idF,lat,long) {

  idM = document.getElementById("mainmain").textContent;
  const url = "https://127.0.0.1:9001/afficherFilleul";

  const params = new URLSearchParams();
  params.append('id', idM);

  fetch(url, {
    method: 'POST',
    body: params
  })
    .then(response => {
      console.log("Données reçues pour creerTaches");
      if (response.ok) {

        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête creerTaches');
      }
    })
    .then(data => {
      if(data != null){
      //   const modalContent = data.map(item => `
      //   <div class="mb-3">
      //     <input type="checkbox" >
      //     <label for="filleuls" class="form-label">${item.username}</label>
      //     <p id="filleuldistance"></p>
      //     <p id="filleullatitude" style="display: none;">${item.latitude}</p>
      //     <p id="filleullongitude" style="display: none;">${item.longitude}</p>
      //   </div>
      // `).join('');
      const modalContent = data.map(item => {
        const latitude = item.latitude; // Convertit la latitude en nombre
        const longitude = item.longitude; // Convertit la longitude en nombre
        const distance = calculateDistance(latitude, longitude,lat,long); // Appelle la fonction pour calculer la distance
      
        return `
          <div class="mb-3">
            <input type="checkbox">
            <label for="filleuls" class="form-label">${item.username}</label>
            <p id="filleuldistance">${distance}</p>
          </div>
        `;
      }).join('');
      
      
      const modalHTML = `
        <div class="modal fade" id="ModalParrain" tabindex="-1" aria-labelledby="ModalParrainLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h2 class="modal-title me-5" id="ModalParrainLabel">Selectionner les filleuls</h2>
                <button type="button" class="btn-close bg-primary" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <form>
                  ${modalContent}
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary bg-danger" data-bs-dismiss="modal">Fermer</button>
                <button type="button" id="creer" onclick="ValiderTache(${idF})" data-bs-dismiss="modal" class="btn btn-primary bg-success">Soumetre</button>
              </div>
            </div>
          </div>
        </div>
      `;

//       const modalContainer = document.createElement('div');
// modalContainer.innerHTML = modalHTML;
// document.body.appendChild(document.getElementById("ModalParrain"));
const modalContainer = document.createElement('div');
modalContainer.innerHTML = modalHTML;

// Accéder à l'élément enfant du modalContainer
const modalElement = modalContainer.firstChild;
// console.log(modalElement)

// Ajouter le modalElement au document
document.body.appendChild(modalContainer);

console.log(document.getElementById("ModalParrain"))

      var modal = new bootstrap.Modal(document.getElementById("ModalParrain"));
      modal.show();
      }
      // Utilisez le corps de la réponse ici
      console.log("***********" + data);
    })
    .catch(error => {
      // Gestion des erreurs
      console.error(error);
    });
}

function calculateDistance(lat1, lon1, lat2, lon2) {
  console.log(lat1,lat2,lon1,lon2)
  const earthRadius = 6371; // Rayon approximatif de la Terre en kilomètres

  // Conversion des latitudes et longitudes en radians
  const lat1Rad = degToRad(lat1);
  const lon1Rad = degToRad(lon1);
  const lat2Rad = degToRad(lat2);
  const lon2Rad = degToRad(lon2);

  // Calcul des différences de latitudes et de longitudes
  const diffLat = lat2Rad - lat1Rad;
  const diffLon = lon2Rad - lon1Rad;

  // Calcul de la distance en utilisant la formule de la grande ellipse
  const a = Math.sin(diffLat / 2) ** 2 + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(diffLon / 2) ** 2;
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const distance = earthRadius * c;
  var dist = "A "+distance +"Km du client..."

  return dist;
}

// Fonction auxiliaire pour convertir des degrés en radians
function degToRad(degrees) {
  return degrees * (Math.PI / 180);
}

function ValiderTache(idNotif) {
  idM = document.getElementById("mainmain").textContent;
  const url = "https://127.0.0.1:9001/RepairIt/Client/Maintenance/validerTache";

  const params = new URLSearchParams();
  params.append('idM', idM);
  params.append('idNotif', idNotif);

  fetch(url, {
    method: 'POST',
    body: params
  })
    .then(response => {
      console.log("Données reçues pour creerTaches");
      window.location.reload()
      if (response.ok) {

        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête creerTaches');
      }
    })
    .then(responseBody => {
      // Utilisez le corps de la réponse ici
      console.log("***********" + responseBody);
    })
    .catch(error => {
      // Gestion des erreurs
      console.error(error);
    });
}

function validerTacheTache(id) {
  document.getElementById("idPaiement").textContent = id
  return id;
}

function path(imageBytes, id) {
  console.log(imageBytes)
  const mimeType = determineImageMimeType(imageBytes);
  var ing = imageBytes.replace(/[\[\]']+/g, '');
  const imageUrl = `data:${mimeType};base64,${ing}`;
  console.log(document.getElementById(id))
  document.getElementById(id).src = imageUrl;
  console.log(imageBytes.length)
  console.log("Chemin d'accès de l'image :", imageUrl);
}

function determineImageMimeType(base64String) {
  const marker = base64String.substring(0, 4); // Obtient les 4 premiers caractères de la chaîne Base64

  let mimeType = '';

  switch (marker) {
    case 'iVBOR': // PNG
      mimeType = 'image/png';
      break;
    case 'R0lG': // GIF
      mimeType = 'image/gif';
      break;
    case '/9j/': // JPEG/JPG
      mimeType = 'image/jpeg';
      break;
    default:
      mimeType = 'image/jpeg'; // Défaut à JPEG si le type MIME est inconnu
      break;
  }

  return mimeType;
}


//CODE POUR L'EVALUATION

function updateCountdown() {
  var dateDOM = document.getElementById("DateArebours");
  if (dateDOM != null) {
    var dateEntree = new Date(document.getElementById("DateArebours").textContent);
    var currentDate = new Date();
    var timeDifference = dateEntree - currentDate;

    // Vérifier si la date d'arrivée est dépassée
    if (timeDifference <= 0) {
      clearInterval(countdownInterval);
      eval()
      console.log('Compte à rebours terminé !');
      return;
    }

    // Calculer les jours, heures, minutes et secondes restantes
    var days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
    var hours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);

    var date = days + " jours ," + hours + ":" + minutes + ":" + seconds + " restants";
    document.getElementById("CaRebours").textContent = date
  }
}
var countdownInterval = setInterval(updateCountdown, 1000);


function eval() {
  document.getElementById("composition").style.display = ""

  const alertButton = document.getElementById('alertButton');
  alertButton.style.display = "block"
  alertButton.classList.add('alert-button');
  alertButton.addEventListener("click", function () {
    document.getElementById("e").click
  })
}

function validerEval() {
  var evaluation = document.querySelectorAll("#evaluation")
  const questions = [];
  var idM = document.getElementById("mainmain").textContent
  console.log(idM)
  for (let j = 0; j < evaluation.length; j++) {
    var formulaire = evaluation[j].querySelector("form")

    const selectedOption = formulaire.querySelector('input[name="proposition"]:checked');
    if (selectedOption) {
      questions.push({
        id: extractNumber(formulaire.id),
        correct: selectedOption.value
      })
    }
  }
  var question = JSON.stringify(questions)
  sendResult(idM, question)
}
var minutes = 20
function heureEval() {

  input = document.getElementById("EvalHour");

  input.textContent = minutes + " min"
  minutes--
  console.log(input)
  if (minutes == 0) {
    validerEval()
  }
}


function extractNumber(questionString) {
  // Vérifier si la chaîne commence par "question" suivie d'un numéro
  const regex = /^question(\d+)$/i;
  const match = questionString.match(regex);

  if (match && match[1]) {
    // Extraire et renvoyer le numéro de la question
    return parseInt(match[1]);
  } else {
    // La chaîne ne correspond pas au format attendu
    return null;
  }
}

function sendResult(idM, question) {
  console.log(question)
  const url = "https://127.0.0.1:9001/validerEval/" + idM;

  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: question
  })
    .then(response => {
      console.log("Données reçues pour creerTaches");
      if (response.ok) {

        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête creerTaches');
      }
    })
    .then(data => {
      if (data.test == 0) {
        console.log("desole");
        console.log(data.notes)
      } else if (data.test == 1) {

        console.log(data.notes)
        findParrain(data.id)
        console.log("tu as passer")
        // sendNotification()

      }
      // Traitement des données renvoyées par le serveur

    })
    .then(responseBody => {
      // Utilisez le corps de la réponse ici
      console.log("***********" + responseBody);
    })
    .catch(error => {
      // Gestion des erreurs
      console.error(error);
    });
}

//CODE POUR LE PARRAINAGE

function parrainage() {

}

function findParrain(id) {
  const url = "https://127.0.0.1:9001/findParrain";
  const params = new URLSearchParams();
  params.append('id', id);

  fetch(url, {
    method: 'GET',

    body: params
  })
    .then(response => {
      console.log("Données reçues pour creerTaches");
      if (response.ok) {

        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête creerTaches');
      }
    })
    .then(data => {

      console.log(data);
      var maintenancier = data.maintenanciers
      for (let i = 0; i < maintenancier.length; i++) {
        sendNotification(maintenancier[i].id, maintenancier.message)
        console.log("okkk")
      }
      // Traitement des données renvoyées par le serveur

    })
    .then(responseBody => {
      // Utilisez le corps de la réponse ici
      console.log("***********" + responseBody);
    })
    .catch(error => {
      // Gestion des erreurs
      console.error(error);
    });
}

function validerParrainage(idM, idF) {
  const url = "https://127.0.0.1:9001/validerParrainage";
  const params = new URLSearchParams();
  params.append('idM', idM);
  params.append('idF', idF);

  fetch(url, {
    method: 'GET',
    body: params
  })
    .then(response => {
      console.log("Données reçues pour creerTaches");
      if (response.ok) {

        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête creerTaches');
      }
    })
    .then(data => {

      // Traitement des données renvoyées par le serveur

    })
    .then(responseBody => {
      // Utilisez le corps de la réponse ici
      console.log("***********" + responseBody);
    })
    .catch(error => {
      // Gestion des erreurs
      console.error(error);
    });
}


function validerFilleul(idM, idF) {
  const url = "https://127.0.0.1:9001/validerFilleul";
  const params = new URLSearchParams();
  params.append('idM', idM);
  params.append('idF', idF);

  fetch(url, {
    method: 'GET',
    body: params
  })
    .then(response => {
      console.log("Données reçues pour creerTaches");
      if (response.ok) {

        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête creerTaches');
      }
    })
    .then(data => {

      // Traitement des données renvoyées par le serveur

    })
    .then(responseBody => {
      // Utilisez le corps de la réponse ici
      console.log("***********" + responseBody);
    })
    .catch(error => {
      // Gestion des erreurs
      console.error(error);
    });
}




//PAYEMENT

function soumettrePrix() {
  const url = "https://127.0.0.1:9001/soumettrePrix";
  var prix = document.getElementById("montant").value;
  var idT = document.getElementById("idPaiement").textContent
  const params = new URLSearchParams();
  params.append('prix', prix);
  params.append('idT', idT);

  fetch(url, {
    method: 'POST',
    body: params
  })
    .then(response => {
      console.log("Données reçues pour paiement");

      if (response.ok) {


        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête paiement');
      }
    })
    .then(data => {

      // Traitement des données renvoyées par le serveur

    })
    .then(responseBody => {
      // Utilisez le corps de la réponse ici
      console.log("***********" + responseBody);
    })
    .catch(error => {
      // Gestion des erreurs
      console.error(error);
    });
}

function terminerTache() {

}

// GERE LE PROFIL
document.getElementById("btncompte").addEventListener('click', function () {
  document.getElementById('PROF').style.display = "block";
  document.getElementById("btncompte").style.display = "none";
  document.getElementById('PROF').classList.add("active");
  document.getElementById("content").style.pointerEvents = "none";
});

document.getElementById("return").addEventListener('click', function () {
  document.getElementById('PROF').style.display = "none";
  document.getElementById("btncompte").style.display = "block";
  document.getElementById('PROF').classList.remove("active");
  document.getElementById("content").style.pointerEvents = "auto";;
});

//GERER LE CLIC SUR L'ICONE DE NOTIFICATION

document.getElementById("btnNotification").addEventListener("click", function () {

  var el1 = document.querySelector(".action");
  var el2 = document.querySelector(".taches");
  var el3 = document.querySelector(".calendar1");
  var el4 = document.querySelector(".messagerie2");
  var el5 = document.querySelector(".bienvenu");
  var el6 = document.querySelector(".sous-menu");
  var elt7 = document.querySelector(".notificationMenu");
  console.log(elt7)

  el1.style.display = "none";
  el2.style.display = "none";
  el3.style.display = "none";
  el4.style.display = "none";
  el5.style.display = "none";
  el6.style.display = "none";
  elt7.style.display = "block";
});

function calender() {
  window.location.href = "/RepairIt/Utilisateur/Calendrier";
}