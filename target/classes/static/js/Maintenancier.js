document.getElementById("a").addEventListener("click", function () {
  onLoadM()

  var el1 = document.querySelector(".action");
  var el2 = document.querySelector(".taches");
  var el3 = document.querySelector(".calendar1");
  var el4 = document.querySelector(".messagerie2");
  var el6 = document.querySelector(".sous-menu");
  var el5 = document.querySelector(".bienvenu");
  var el7 = document.querySelector(".evaluations");
  var elt7 = document.querySelector(".notificationMenu");

  el1.style.display = "block";
  el2.style.display = "block";
  el3.style.display = "none";
  el4.style.display = "none";
  el5.style.display = "none";
  el7.style.display = "none";
  el6.style.display = "block"
  elt7.style.display = "none";

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
  var elt8 = document.querySelector("#MaRebours")
  var elt7 = document.querySelector(".notificationMenu");

  el1.style.display = "none";
  // el2.style.display = "none";
  // el3.style.display = "none";
  // el4.style.display = "none";
  el5.style.display = "none";
  el6.style.display = "none";
  el7.style.display = "block";
  elt8.style.display = "none";
  elt7.style.display = "none";
  heureEval()
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
      var notification = new Notification('Notification', options);


      // Gérer les événements de la notification (clic, fermeture, etc.)
      notification.onclick = function (event) {
        document.getElementById("btnNotification").click()
      };

      notification.onclose = function (event) {
        var messageIcon = document.querySelector(".message-icon");
        messageIcon.classList.toggle("has-badge");
        // Gérer la fermeture de la notification
        //ajouter le truc bleu sur l'icone 
      };

      // ...
    }
  }
}

function sendNotification(userId,message) {
  var destination = '/app/notification/' + userId;
  // var message = 'Notification pour l\'utilisateur ' + userId;

  stompClient.send(destination, {}, message);
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
      iN[j].remove()
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
      const modalContent = data.map(item => {
        const latitude = item.latitude; // Convertit la latitude en nombre
        const longitude = item.longitude; // Convertit la longitude en nombre
        const distance = calculateDistance(latitude, longitude,lat,long); // Appelle la fonction pour calculer la distance

      
        return `
          <div class="mb-3">
            <input name="mesfilleuls" type="checkbox">
            <p style="display: none;" id="mesfilleuls">${item.id}</p>
            <label for="mesfilleuls" class="form-label">${item.username}+''+${distance}</label>
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

const modalContainer = document.createElement('div');
modalContainer.innerHTML = modalHTML;



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
  var dist = " A "+distance +"Km du client..."

  return dist;
}

// Fonction auxiliaire pour convertir des degrés en radians
function degToRad(degrees) {
  return degrees * (Math.PI / 180);
}

function ValiderTache(idNotif) {
  var mesfilleuls = document.querySelector("#ModalParrain").querySelector("form")
  var inputcheck = mesfilleuls.querySelector('input[name="mesfilleuls"]:checked');
  var tabId= []
  if(inputcheck){
    tabId.push(mesfilleuls.querySelector("#mesfilleuls").textContent)
  }
  console.log(tabId)
  idM = document.getElementById("mainmain").textContent;
  const url = "https://127.0.0.1:9001/RepairIt/Client/Maintenance/validerTache";

  const params = new URLSearchParams();
  params.append('idM', idM);
  params.append('idNotif', idNotif);
  params.append('idFils',tabId)

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
    document.getElementById("f").click()
  })
}

function validerEval() {
  var evaluation = document.querySelectorAll("#evaluation")
  const questions = [];
  var idM = document.getElementById("mainmain").textContent
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

function heureEval() {

  input = document.getElementById("EvalHour");

  

  var minutes = 10; // Nombre de minutes du compte à rebours
  var seconds = 0; // Nombre de secondes du compte à rebours

  var countdownInterval = setInterval(function() {
    // Afficher les minutes et les secondes
    input.textContent = minutes + " min" + seconds + " sec"

    // Décrémenter les secondes
    seconds--;

    // Si les secondes atteignent 0, décrémenter les minutes
    if (seconds < 0) {
      seconds = 59;
      minutes--;
    }

    // Si le compte à rebours est terminé
    if (minutes === 0 && seconds === 0) {
      clearInterval(countdownInterval);
      validerEval()
    }
  }, 1000); // Répéter toutes les 1 seconde (1000 millisecondes)

 
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
      document.getElementById("composition").style.display = "none";
var modalContent;
var modalHTML;

if (data.test == 0) {
  modalContent = `
    <div class="mb-3">
      <h4>😓😓😓Desole ${data.username} vous n'avez pas passer l'evaluation</h4>
    </div>
  `;
} else if (data.test == 1) {
  findParrain(data.id);
  modalContent = `
    <div class="mb-3">
      <p> 🥳 🥳 🥳Felicitation ${data.username}</p>
      <p>Vous venez de passer le test avec une note de ${data.notes}/20 </p>
      <p>Vous serez assigner a un parrain avec qui vous effectuerez ensemble ces prochaines taches jusqu'a ce qu'il valide votre test</p>
      <p>Toutes les taches que vous verez seront celles de votre parrain</p>
      <p>Il pourra decider que  vous l'accompagner ou non</p>
    </div>
  `;
} else if (data.test == 2) {
  modalContent = `
    <div class="mb-3">
      <p>😱 😱Waouh 🥳 🥳 🥳Felicitation ${data.username}</p>
      <p>Vous venez de passer le test haut la main avec une note de ${data.notes}/20 </p>
      <p>Vous etes desormais l'un de nos maintenancier et vous pouvez commmencer a recevoir des demandes de service en toute surete</p>
    </div>
  `;
}

modalHTML = `
  <div class="modal fade" id="ModalResult" tabindex="-1" aria-labelledby="ModalResultLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h2 class="modal-title me-5" id="ModalResultLabel">Resultat de l'evaluation</h2>
        </div>
        <div class="modal-body">
          <form>
            ${modalContent}
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" id="creerR" onclick="window.location.href = '/RepairIt/Maintenancier';" data-bs-dismiss="modal" class="btn btn-primary bg-success">Valider</button>
        </div>
      </div>
    </div>
  </div>
`;

if (data.test == 0) {
  var modalContainerE = document.createElement('div');
  modalContainerE.innerHTML = modalHTML;
  document.body.appendChild(modalContainerE);
} else if (data.test == 1) {
  var modalContainerP = document.createElement('div');
  modalContainerP.innerHTML = modalHTML;
  document.body.appendChild(modalContainerP);
} else if (data.test == 2) {
  var modalContainerW = document.createElement('div');
  modalContainerW.innerHTML = modalHTML;
  document.body.appendChild(modalContainerW);

 idM = document.getElementById("mainmain").textContent;
}

// Traitement des données renvoyées par le serveur
var modal = new bootstrap.Modal(document.getElementById("ModalResult"));
modal.show();
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

      console.log(data);
      var maintenancier = data.maintenanciers
      for (let i = 0; i < maintenancier.length; i++) {
        var message = maintenancier.message.split("///")[0]
        sendNotification(maintenancier[i].id, message)
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
      var maintenancier = data.maintenanciers
      for (let i = 0; i < maintenancier.length; i++) {
        var message = maintenancier.message
        sendNotification(maintenancier[i].id, message)
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


function validerFilleul(idM, idF) {
  var modal = new bootstrap.Modal(document.getElementById("ModalFilleul"));
      modal.show();
      var validFils = document.getElementById("validFils")
      validFils.addEventListener('click',function(){
  const url = "https://127.0.0.1:9001/validerFilleul";
  const params = new URLSearchParams();
  params.append('idM', idM);
  params.append('idF', idF);

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
      var maintenancier = data.maintenanciers
      for (let i = 0; i < maintenancier.length; i++) {
        var message = maintenancier.message
        sendNotification(maintenancier[i].id, message)
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
  })
}




//PAYEMENT

function soumettrePrix() {
  const url = "https://127.0.0.1:9001/soumettrePrix";
  var idM = document.getElementById("mainmain").textContent
  var prix = document.getElementById("montant").value;
  var idT = document.getElementById("idPaiement").textContent
  var message = `<p style="font-size: 16px; color: #333;">Vous allez valider la tache d'id ${idT}  : <a href="http://localhost:4242/checkout.html?prix=${prix}&idT=${idT}&idM=${idM}" style="text-decoration: none; color: #0099ff;">Cliquez ici</a> pour valider.</p>`
  const params = new URLSearchParams();
  params.append('idT', idT);
  params.append('message',message);

  fetch(url, {
    method: 'POST',
    body: params
  })
    .then(response => {
      if (response.ok) {

        console.log("Données reçues pour paiement");
          return response.json(); // Renvoyer la réponse JSON
      } else {
          throw new Error('Erreur de la requête creerTaches');
      }
    })
    .then(data => {
      console.log("sendEmail")
      terminerTacheSendEmail(data)

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

function terminerTacheSendEmail(emailDTO) {
  console.log(emailDTO)
  const url = "https://127.0.0.1:9001/sendemail"
  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(emailDTO)
   
  })
    .then(response => {
      if (response.ok) {
      } else {
       alert('Une erreur s\'est produite lors de l\'envoi de l\'e-mail.');
      }
    })
    .catch(error => {
      alert('Une erreur s\'est produite :', error);
    });
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
  var notif = elt7.querySelectorAll(".notification")
  notif.forEach(not =>{
    var messageElement = not.querySelector('.message');
    var taches = messageElement.textContent.split("///")
    var m = taches.length
    messageElement.textContent=taches[0]
    var tache = taches[m-1]
    if(m > 1){
      idM = document.getElementById("mainmain").textContent;
      var content = not.querySelector(".content").querySelector(".buttonNotif")
      var message=`
      <button class="my-4" onclick="validerParrainage(${idM},${tache})">Envoyer</button>
      `
      content.innerHTML=message
    }
  })


});

function calender() {
  window.location.href = "/RepairIt/Utilisateur/Calendrier";
}