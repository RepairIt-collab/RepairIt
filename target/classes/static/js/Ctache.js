// ICI NOUS IMPLEMENTONS LA CREATION D'UNE TACHE
function openFilePicker() {
  document.getElementById('file-picker').click();
}

document.getElementById('file-picker').addEventListener('change', function (event) {
  var input = event.target;
  var img = document.getElementById('file-picker');
  var file = img.files[0]
  var idDOM = "type-appareil";
  console.log(file, idDOM)
  cnn(file, idDOM)
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      document.getElementById('preview-image').setAttribute('src', e.target.result);
      document.getElementById('preview-image').style.display = 'block';
    };
    reader.readAsDataURL(input.files[0]);
  }
});


function creerTache(jsonData) {
  const url = "https://127.0.0.1:9001/RepairIt/Client/Maintenance/CreerTache";

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
        window.location.reload();

        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête creerTaches');
      }
    })
    .then(data => {
      // Traitement des données renvoyées par le serveur
      console.log(data);
      var maintenancier = data.maintenanciers
      for (let i = 0; i < maintenancier.length; i++) {
        console.log(maintenancier.message)
        sendNotification(maintenancier[i].id, data.message)
        console.log(data.message)
        var filleul = maintenancier[i].idfilleuls
        if (filleul != null) {
          for (let j = 0; j < filleul.length; j++) {
            var message = data.message
            message = message + " tache confier a votre parrain"
            sendNotification(filleul[j], message)
          }
        }
        console.log("okkk")
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

function newNotif() {
  const url = "https://127.0.0.1:9001newNotif";
}

function cnn(image, id) {

  const url = "https://127.0.0.1:9001/flask";
  const form = new FormData();
  form.append('image', image);

  fetch(url, {
    method: 'POST',
    body: form
  })
    .then(response => {
      if (response.ok) {
        console.log("Image envoyer");
        return response.text(); // Renvoyer la réponse en tant que texte
      } else {
        throw new Error('Erreur de la requête SupprimerTache');
      }
    })
    .then(data => {
      console.log(data);
      console.log(id)
      console.log(document.getElementById(id))
      document.getElementById(id).textContent = data
    })
    .catch(error => {
      // Gestion des erreurs
      console.error(error);
    });
}



// function CTache() {
//     var fileInput = document.getElementById("file-picker");
//     var file = fileInput.files[0]; // Récupérer la photo

//     // cnn(file)
//     var reader = new FileReader(); // Créer un objet FileReader
//     reader.onload = function(event) {
//       var arrayBuffer = event.target.result; 
//       var photoData = event.result;// Récupérer les données de la photo sous forme d'ArrayBuffer
//       console.log(photoData)
//       var byteArray = new Uint8Array(arrayBuffer); // Convertir l'ArrayBuffer en tableau de bytes
//     // var base64String = btoa(String.fromCharCode.apply(null, byteArray));

//     // console.log(base64String);
//     //   var photoData = Array.from(byteArray); // Convertir le tableau de bytes en un tableau JavaScript standard
//       console.log(photoData)
//     //   const base64Image = photoData.split(',')[1];
//       var todoTache = {
//         nom: document.getElementById("equipement").value,
//         // photo:base64String, // Utiliser le tableau de bytes comme valeur de la propriété "photo"
//         // type: document.getElementById("type-appareil").textContent,
//         description: document.getElementById("description").value,
//         date: document.getElementById("date").value,
//         latitude:latitude,
//         longitude:longitud,

//       };

//       // Envoyer les données JSON au serveur
//       var jsonData = JSON.stringify(todoTache);
//       console.log(jsonData.latitude);
//       creerTache(jsonData);
//     };

//     reader.readAsArrayBuffer(file); // Lire la photo en tant qu'ArrayBuffer
// }
function CTache() {
  var fileInput = document.getElementById("file-picker");
  var file = fileInput.files[0]; // Récupérer la photo


  var reader = new FileReader(); // Créer un objet FileReader
  reader.onload = function (event) {
    var arrayBuffer = event.target.result;
    console.log(arrayBuffer)
    var photoData = event.result;// Récupérer les données de la photo sous forme d'ArrayBuffer
    var byteArray = new Uint8Array(arrayBuffer);
    console.log(byteArray)// Convertir l'ArrayBuffer en tableau de bytes
    var base64String = btoa(String.fromCharCode.apply(null, byteArray));

    console.log(base64String);
    //   var photoData = Array.from(byteArray); // Convertir le tableau de bytes en un tableau JavaScript standard
    console.log(photoData)
    //   const base64Image = photoData.split(',')[1];

    var todoTache = {
      photo: base64String, // Utiliser le tableau de bytes comme valeur de la propriété "photo"
      type: document.getElementById("type-appareil").textContent,
      description: document.getElementById("description").value,
      latitude: latitude,
      longitude: longitud,

    };

    // Envoyer les données JSON au serveur
    var jsonData = JSON.stringify(todoTache);
    console.log(jsonData.latitude);
    creerTache(jsonData);
  };

  reader.readAsArrayBuffer(file); // Lire la photo en tant qu'ArrayBuffer
}

document.getElementById("creer").addEventListener('click',CTache);




// SUPPRESSION D'UNE TACHE
function SupprimerTache(id) {
  const url = "https://127.0.0.1:9001/RepairIt/Client/Maintenance/SuprimerTache/" + id;

  fetch(url, {
    method: 'POST'
  })
    .then(response => {
      if (response.ok) {
        console.log("Suppression réussie");
        // return response.text(); // Renvoyer la réponse en tant que texte
      } else {
        throw new Error('Erreur de la requête SupprimerTache');
      }
    })
    .then(data => {
      console.log(data);
      supprimerTacheDuDOM(id);
    })
    .catch(error => {
      // Gestion des erreurs
      console.error(error);
    });
  // window.location.reload()
}

function supprimerTacheDuDOM(Id1) {
  const divId = "T" + Id1;
  const div = document.getElementById(divId);
  if (div) {
    div.remove();
  }
}

//NOM DE LA TACHE SUR LA PAGE

function getName() {
  var button = document.querySelectorAll(".accordion-header")
}

// AFFICHER LES DETAILS D'UNE TACHE

var nav1 = document.querySelector("button#nav1");
console.log(nav1)
nav1.addEventListener("click", function () {
  // const url = "https://127.0.0.1:9001/RepairIt/Client/Maintenance/details";

  // fetch(url, {
  //     method: 'GET',
  // })
  // .then(response => {
  //     console.log("Données reçues pour creerTaches");
  //     if (response.ok) {
  //         console.log(response)
  //         return response.json(); // Renvoyer la réponse JSON
  //     } else {
  //         throw new Error('Erreur de la requête creerTaches');
  //     }
  // })
  $.ajax({
    url: "/RepairIt/Client/Maintenance/details", // Endpoint de votre fonction Java
    type: "GET",
    success: function (response) {
      console.log(response);
      var responseHtml = $(response); // Convertir la réponse en objet jQuery

      // Utilisez les sélecteurs jQuery pour extraire les éléments souhaités
      var selectElement1 = responseHtml.find("div#accordionFlushExample");
      var extractedHtml1 = selectElement1.html(); // Obtenez le contenu HTML de l'élément extrait

      var targetElement1 = document.querySelectorAll("div#accordionFlushExample")[0];
      targetElement1.innerHTML = extractedHtml1; // Mettez à jour le contenu de l'élément cible

      // --------------------------------
      var selectElement2 = responseHtml.find("div#accordionFlushExample").eq(1);
      var extractedHtml2 = selectElement2.html(); // Obtenez le contenu HTML de l'élément extrait

      var targetElement2 = document.querySelectorAll("div#accordionFlushExample")[1];
      targetElement2.innerHTML = extractedHtml2; // Mettez à jour le contenu de l'élément cible
      // -----------------------------------------------
      var selectElement3 = responseHtml.find("div#accordionFlushExample").eq(2);
      var extractedHtml3 = selectElement3.html(); // Obtenez le contenu HTML de l'élément extrait

      var targetElement3 = document.querySelectorAll("div#accordionFlushExample")[2];
      targetElement3.innerHTML = extractedHtml3; // Mettez à jour le contenu de l'élément cible
      console.log(selectElement1)
    },
    error: function () {
      console.log("Erreur lors de la requête AJAX !");
    }
  });
})

//DETERMINER LE CHEMIN D'ACCES D'UNE IMAGE


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

// UPDATE UNE TACHE DONT L'ETAT EST ENCORE 1 
function UTaches(id, json) {
  const url = "https://127.0.0.1:9001/RepairIt/Client/Maintenance/ModifierTache/" + id;

  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: json
  })
    .then(response => {
      console.log("Données reçues pour creerTaches");
      if (response.ok) {
        window.location.reload();

        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête creerTaches');
      }
    })
    .then(data => {
      // Traitement des données renvoyées par le serveur
      console.log(data);
      var maintenancier = data.maintenanciers
      for (let i = 0; i < maintenancier.length; i++) {
        console.log(maintenancier.message)
        sendNotification(maintenancier[i].id, data.message)
        console.log(data.message)
        var filleul = maintenancier[i].idfilleuls
        if (filleul != null) {
          for (let j = 0; j < filleul.length; j++) {
            var message = data.message
            message = message + " tache confier a votre parrain"
            sendNotification(filleul[j], message)
          }
        }
        console.log("okkk")
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

function UpdateTache(idT, id, typeA, desc) {
  var popup = document.getElementById("Modal1");
  var modal = new bootstrap.Modal(document.getElementById("Modal1"));
  modal.show();
  var img1 = document.getElementById(id).src
  console.log(img1)

  var photo = img1.split("base64,")[1]
  path(photo, "preview-image")
  document.getElementById('preview-image').style.display = 'block';
  document.getElementById("type-appareil").textContent = typeA
  document.getElementById('description').value = desc;
  var cree = popup.querySelector("#creer")
  if (document.getElementById('file-picker').click()) {
    cree.addEventListener('click', function (e) {
      e.preventDefault();
      MTache(idT)
    })
  } else {
    cree.addEventListener('click', function (e) {
      e.preventDefault();
      var todoTache = {
        photo: photo,
        type: typeA,
        description: document.getElementById('description').value,
        latitude: latitude,
        longitude: longitud,
      };
      var jsonData = JSON.stringify(todoTache);
      UTaches(idT, jsonData)
    })
  }

}


function MTache(idT) {
  var fileInput = document.getElementById("file-picker");
  var file = fileInput.files[0]; // Récupérer la photo


  var reader = new FileReader(); // Créer un objet FileReader
  reader.onload = function (event) {
    var arrayBuffer = event.target.result;
    console.log(arrayBuffer)
    var photoData = event.result;// Récupérer les données de la photo sous forme d'ArrayBuffer
    var byteArray = new Uint8Array(arrayBuffer);
    console.log(byteArray)// Convertir l'ArrayBuffer en tableau de bytes
    var base64String = btoa(String.fromCharCode.apply(null, byteArray));

    console.log(base64String);
    //   var photoData = Array.from(byteArray); // Convertir le tableau de bytes en un tableau JavaScript standard
    console.log(photoData)
    //   const base64Image = photoData.split(',')[1];

    var todoTache = {
      photo: base64String, // Utiliser le tableau de bytes comme valeur de la propriété "photo"
      type: document.getElementById("type-appareil").textContent,
      description: document.getElementById("description").value,
      latitude: latitude,
      longitude: longitud,

    };

    // Envoyer les données JSON au serveur
    var jsonData = JSON.stringify(todoTache);
    console.log(jsonData.latitude);
    UTaches(idT, jsonData);
  };

  reader.readAsArrayBuffer(file); // Lire la photo en tant qu'ArrayBuffer
}
