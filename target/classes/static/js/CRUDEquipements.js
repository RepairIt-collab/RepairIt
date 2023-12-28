// ICI NOUS IMPLEMENTONS LA CREATION D'UN EQUIPEMENTS
function openFilePicker2() {
    document.getElementById('file-picker2').click();
}
  
document.getElementById('file-picker2').addEventListener('change', function (event) {
    var input = event.target;
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function (e) {
        document.getElementById('preview-image2').setAttribute('src', e.target.result);
        document.getElementById('preview-image2').style.display = 'block';
      };
      reader.readAsDataURL(input.files[0]);
    }0
});

function creerEquipement(jsonData) {
    const url = "https://127.0.0.1:9001/RepairIt/Client/Equipement/CreerEquipement";

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: jsonData
    })
    .then(response => {
        console.log("Données reçues pour creerEquipement");
        if (response.ok) {
            return response.json(); // Renvoyer la réponse JSON
        } else {
            throw new Error('Erreur de la requête creerEquipement');
        }
    })
    .then(data => {
        const div = document.getElementById('ListeEquip');
        const messageId = `EQU${data.id}`; // Id de chaque Accordion
        const messageId2 = `E${data.id}`
        const message = document.createElement("div");
        message.className = "accordion"
        message.id = messageId2;
        message.innerHTML = `
           <div class="accordion-item">
              <h2 class="accordion-header">
                  <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#${messageId}" aria-expanded="true" aria-controls="${messageId}">
                   ${data.nom}
                  </button>
               </h2>
               <div id="${messageId}" class="accordion-collapse collapse" data-bs-parent="#${messageId2}">
                 <div class="accordion-body">
                    <div class="row">
                       <div class="col-md-4">
                         <img src="data:image/png;base64,${data.photo}" class="my-1 ms-2" alt="icon" style="width: 140px; height: 100px;">
                       </div>
                       <div class="col-md-4">
                         <li style="font-size: 2rem;">Type : ${data.type}</li>
                         <br>
                         <div class="row">
                            <div class="col-md-3">
                                 <i onclick="SupprimerEquipement(${data.id})" class="fas fa-trash-alt fa-2x" style="color:#ad0808;"></i>   <!-- Icône de poubelle -->
                            </div>
                            <div class="col-md-3">
                                <i class="fas fa-edit fa-2x" style="color:#044602 ;"></i> <!-- Icône de modification -->
                            </div>
                            <div class="col-md-3">
                                <i class="fas fa-wrench fa-2x" style="color:#00b2ff;"></i>
                            </div>
                         </div>
                       </div>
                    </div>
                 </div>
              </div>
           </div>
        `;
        
        div.appendChild(message);
        // div.innerHTML = message;
        div.appendChild(message); 
        // Traitement des données renvoyées par le serveur
        console.log(data);
    })
    .catch(error => {
        // Gestion des erreurs
        console.error(error);
    });
}

function CEquipement() {
    var fileInput = document.getElementById("file-picker2");
    var file = fileInput.files[0]; // Récupérer la photo
  
    var reader = new FileReader(); // Créer un objet FileReader
    reader.onload = function(event) {
      var arrayBuffer = event.target.result; // Récupérer les données de la photo sous forme d'ArrayBuffer
      var byteArray = new Uint8Array(arrayBuffer); // Convertir l'ArrayBuffer en tableau de bytes
  
      var photoData = Array.from(byteArray); // Convertir le tableau de bytes en un tableau JavaScript standard
  
      var Equipement = {
        nom: document.getElementById("equipement1").value,
        photo: photoData, // Utiliser le tableau de bytes comme valeur de la propriété "photo"
        type: document.getElementById("type-appareil2").value
      };
  
      // Envoyer les données JSON au serveur
      var jsonData = JSON.stringify(Equipement);
      console.log(jsonData);
      creerEquipement(jsonData);
    };
  
    reader.readAsArrayBuffer(file); // Lire la photo en tant qu'ArrayBuffer
}

document.getElementById("creerEquip").addEventListener('click',CEquipement);

// SUPPRESSION D'UNE TACHE
function SupprimerEquipement(id) {
    const url = "https://127.0.0.1:9001/Client/Equpement/SuprimerEquipements/" + id;
  
    fetch(url, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            console.log("Suppression réussie");
            return response.text(); // Renvoyer la réponse en tant que texte
        } else {
            throw new Error('Erreur de la requête Supprimer');
        }
    })
    .then(data => {
        console.log(data);
        supprimerEquipDOM(id);
    })
    .catch(error => {
        // Gestion des erreurs
        console.error(error);
    });
  }
  
  function supprimerEquipDOM(Id1) {
      const divId = "E"+Id1;
      const div = document.getElementById(divId);
      if (div) {
          div.remove();
      }
  }

// MODIFICATION D'UNE TACHE
