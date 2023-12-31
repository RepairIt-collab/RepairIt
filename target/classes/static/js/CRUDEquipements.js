// ICI NOUS IMPLEMENTONS LA CREATION D'UN EQUIPEMENTS
function openFilePicker2() {
    document.getElementById('file-picker2').click();
}

document.getElementById('file-picker2').addEventListener('change', function (event) {
    var input = event.target;
    var img = document.getElementById('file-picker2');
    var file = img.files[0]
    var idDOM = "type-appareil2"
    console.log(file, idDOM)
    cnn(file, idDOM)
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById('preview-image2').setAttribute('src', e.target.result);
            document.getElementById('preview-image2').style.display = 'block';
        };
        reader.readAsDataURL(input.files[0]);
    } 0
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
            const idP = `imgEquip${data.id}`
            message.innerHTML = `
               <div class="accordion-item">
                  <h2 class="accordion-header">
                      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#${messageId}" aria-expanded="true" aria-controls="${messageId}" style="color: #08ad2e;">
                       ${data.nom} Bon etat...
                      </button>
                   </h2>
                   <div id="${messageId}" class="accordion-collapse collapse" data-bs-parent="#${messageId2}">
                     <div class="accordion-body">
                        <div class="row">
                           <div class="col-md-4">
                           <img src="data:image/png;base64,${data.photo}" class="my-1 ms-2" alt="icon" id="${idP}" style="width: 140px; height: 100px;">
                           </div>
                           <div class="col-md-4">
                             <li style="font-size: 2rem;">Type : ${data.type}</li>
                             <br>
                             <div class="row">
                                <div class="col-md-3">
                                     <i onclick="SupprimerEquipement(${data.id})" class="fas fa-trash-alt fa-2x" style="color:#ad0808;"></i>   <!-- Icône de poubelle -->
                                </div>
                                <div class="col-md-3">
                                    <i  class="fas fa-edit fa-2x" style="color:#044602 ;"></i> <!-- Icône de modification -->
                                </div>
                                <div class="col-md-3">
                                <i onclick="Reparer(${data.type},${data.nom},${idP},${data.id})" class="fas fa-wrench fa-2x" style="color:#00b2ff;"></i>
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
    reader.onload = function (event) {
        var arrayBuffer = event.target.result; // Récupérer les données de la photo sous forme d'ArrayBuffer
        var byteArray = new Uint8Array(arrayBuffer); // Convertir l'ArrayBuffer en tableau de bytes

        var photoData = Array.from(byteArray); // Convertir le tableau de bytes en un tableau JavaScript standard

        var Equipement = {
            nom: document.getElementById("equipement1").value,
            photo: photoData, // Utiliser le tableau de bytes comme valeur de la propriété "photo"
            type: document.getElementById("type-appareil2").textContent
        };
        // Envoyer les données JSON au serveur
        var jsonData = JSON.stringify(Equipement);
        console.log(jsonData);
        creerEquipement(jsonData);
    };

    reader.readAsArrayBuffer(file); // Lire la photo en tant qu'ArrayBuffer
}

document.getElementById("creerEquip").addEventListener('click', CEquipement);

// SUPPRESSION D'UNE TACHE
function SupprimerEquipement(id) {
    const url = "https://127.0.0.1:9001/RepairIt/Client/Equipement/SuprimerEquipements/" + id;

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
    const divId = "E" + Id1;
    const div = document.getElementById(divId);
    if (div) {
        div.remove();
    }
}

// CREATION D'UNE TACHE A L'AIDE DE L'EQUIPEMENT

function Reparer(type, nom, id, idE) {
    // var popup =document.getElementById("Modal1");
    // popup.style.display = "block"
    // popup.classList.add("show")
    // var img = document.getElementById(id).src
    // var photo = img.split("base64,")[1]
    // path(photo,"preview-image")
    // document.getElementById('preview-image').style.display = 'block';
    // document.getElementById("type-appareil").textContent=type
    // document.getElementById("photo-frame").style.display='none';

    //Reparer("telephone","my self","imgEquip5")
    var todoTache = {
        photo: photo, 
        type: type,
        description: "appareil " + nom + " deffectueux",
        latitude: latitude,
        longitude: longitud,
        nom: idE
    };

    var jsonData = JSON.stringify(todoTache);
        creerTache(jsonData);

}



//AFFICHER LES EQUIPEMENT AU CHARGEMENT
function loadEquipement() {
    // alert("ok")
    const url = "https://127.0.0.1:9001/RepairIt/Client/Equipement/loadEquipement";

    fetch(url, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                console.log("affichage réussie");
                return response.json(); // Renvoyer la réponse en tant que texte
            } else {
                throw new Error('Erreur de la requête Supprimer');
            }
        })
        .then(data => {
            console.log(data)
            for (var i = 0; i < data.length; i++) {
                const div = document.getElementById('ListeEquip');
                const messageId = `EQU${data[i].id}`; // Id de chaque Accordion
                const messageId2 = `E${data[i].id}`
                const message = document.createElement("div");
                message.className = "accordion"
                message.id = messageId2;
                if (data[i].etats == 0) {
                    message.innerHTML = `
           <div class="accordion-item">
              <h2 class="accordion-header">
                  <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#${messageId}" aria-expanded="true" aria-controls="${messageId}" style="color: #ad0808;">
                   ${data[i].nom}  En panne...
                  </button>
               </h2>
               <div id="${messageId}" class="accordion-collapse collapse" data-bs-parent="#${messageId2}">
                 <div class="accordion-body">
                    <div class="row">
                       <div class="col-md-4">
                         <img src="data:image/png;base64,${data[i].photo}" class="my-1 ms-2" alt="icon" style="width: 140px; height: 100px;">
                       </div>
                       <div class="col-md-4">
                         <li style="font-size: 2rem;">Type : ${data[i].type}</li>
                         <br>
                         <div class="row">
                            <div class="col-md-3">
                                 <i onclick="SupprimerEquipement(${data[i].id})" class="fas fa-trash-alt fa-2x" style="color:#ad0808;"></i>   <!-- Icône de poubelle -->
                            </div>
                            <div class="col-md-3">
                                <i  class="fas fa-edit fa-2x" style="color:#044602 ;"></i> <!-- Icône de modification -->
                            </div>
                         </div>
                       </div>
                    </div>
                 </div>
              </div>
           </div>
        `;
                }
                if (data[i].etats == 1) {
                    message.innerHTML = `
           <div class="accordion-item">
              <h2 class="accordion-header">
                  <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#${messageId}" aria-expanded="true" aria-controls="${messageId}" style="color: #365bf0;">
                   ${data[i].nom}  Reparation en cours...
                  </button>
               </h2>
               <div id="${messageId}" class="accordion-collapse collapse" data-bs-parent="#${messageId2}">
                 <div class="accordion-body">
                    <div class="row">
                       <div class="col-md-4">
                         <img src="data:image/png;base64,${data[i].photo}" class="my-1 ms-2" alt="icon" style="width: 140px; height: 100px;">
                       </div>
                       <div class="col-md-4">
                         <li style="font-size: 2rem;">Type : ${data[i].type}</li>
                         <br>
                         <div class="row">
                            <div class="col-md-3">
                                <i  class="fas fa-edit fa-2x" style="color:#044602 ;"></i> <!-- Icône de modification -->
                            </div>
                            
                         </div>
                       </div>
                    </div>
                 </div>
              </div>
           </div>
        `;
                }
                if (data[i].etats == 2) {
                    const idP = `imgEquip${data[i].id}`
                    message.innerHTML = `
           <div class="accordion-item">
              <h2 class="accordion-header">
                  <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#${messageId}" aria-expanded="true" aria-controls="${messageId}" style="color: #08ad2e;">
                   ${data[i].nom} Bon etat...
                  </button>
               </h2>
               <div id="${messageId}" class="accordion-collapse collapse" data-bs-parent="#${messageId2}">
                 <div class="accordion-body">
                    <div class="row">
                       <div class="col-md-4">
                         <img src="data:image/png;base64,${data[i].photo}" class="my-1 ms-2" alt="icon" id="${idP}" style="width: 140px; height: 100px;">
                       </div>
                       <div class="col-md-4">
                         <li style="font-size: 2rem;">Type : ${data[i].type}</li>
                         <br>
                         <div class="row">
                            <div class="col-md-3">
                                 <i onclick="SupprimerEquipement(${data[i].id})" class="fas fa-trash-alt fa-2x" style="color:#ad0808;"></i>   <!-- Icône de poubelle -->
                            </div>
                            <div class="col-md-3">
                                <i  class="fas fa-edit fa-2x" style="color:#044602 ;"></i> <!-- Icône de modification -->
                            </div>
                            <div class="col-md-3">
                                <i onclick="Reparer(${data[i].type},${data[i].nom},${idP})" class="fas fa-wrench fa-2x" style="color:#00b2ff;"></i>
                            </div>
                         </div>
                       </div>
                    </div>
                 </div>
              </div>
           </div>
        `;
                }

                div.appendChild(message);
                // div.innerHTML = message;
                div.appendChild(message);
            }
            // Traitement des données renvoyées par le serveur
            // console.log(data);
        })
        .catch(error => {
            // Gestion des erreurs
            console.error(error);
        });
}



//REPARER UN EQUIPEMENT




window.onload(loadEquipement())



/**
 * <div class="accordion accordion-flush" id="accordionFlushExample">
                            <div class="accordion-item" th:each="equipement: ${equipements}">
                                <h2 class="accordion-header" th:if="${equipement.etats == 0}">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        th:data-bs-target="'#flush-collapse'+ ${equipement.id}" aria-expanded="false"
                                        aria-controls="flush-collapseOne"
                                        th:attr="onclick='path(\'' + ${equipement.photo} + '\', \'equipementElementsss' + ${equipement.id} + '\')'"
                                        style="color: #ad0808;"> Gater

                                    </button>
                                </h2>
                                <h2 class="accordion-header" th:if="${equipement.etats == 1}">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        th:data-bs-target="'#flush-collapse'+ ${equipement.id}" aria-expanded="false"
                                        aria-controls="flush-collapseOne"
                                        th:attr="onclick='path(\'' + ${equipement.photo} + '\', \'equipementElementsss' + ${equipement.id} + '\')'"
                                        style="color: #365bf0;">Reparation en cours...

                                    </button>
                                </h2>
                                <h2 class="accordion-header" th:if="${equipement.etats == 2}">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        th:data-bs-target="'#flush-collapse'+ ${equipement.id}" aria-expanded="false"
                                        aria-controls="flush-collapseOne"
                                        th:attr="onclick='path(\'' + ${equipement.photo} + '\', \'equipementElementsss' + ${equipement.id} + '\')'"
                                        style="color: #08ad2e;">Reparer

                                    </button>
                                </h2>
                                <div th:id="'flush-collapse' + ${equipement.id}" class="accordion-collapse collapse"
                                    data-bs-parent="#accordionFlushExample">
                                    <div class="accordion-body">
                                        <div class="card mb-3">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="" class="img-fluid rounded-start" alt="image-icone"
                                                        th:id="'equipementElementsss'+ ${equipement.id}"
                                                        style="width: 100%; height: 100%;">
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="card-body lola">
                                                        <!-- <div th:if="${equipement.type == null}">
                                                            <p class="card-text" th:text="${equipement.nom}"></p>
                                                        </div> -->
                                                        <!-- <div th:if="{equipement.type != null}"> -->
                                                        <h5 class="card-title" th:text="${equipement.type.value}"></h5>
                                                        <p class="card-text" th:text="${equipement.nom}"></p>
                                                        <i class="fas fa-trash-alt fa-2x" style="color:#983232;"
                                                            th:attr="onclick='reparer(\'' + ${equipement.id} + '\',\'' + ${equipement.nom} + '\',\'' + ${equipement.photo} + '\')'"></i>
                                                        <!-- </div> -->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
 */

