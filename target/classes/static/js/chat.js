alert("Hello chat");
// Au click sur le boutton chat d une tache
// La requete envoye devra contenir l id de la tache


// ----------------------------------------------------
// On va recuperer les info de la tache (nom, id , description, la Photo, leS username de l utilisateur, le username du maintenancier)
// On va le mettre dans le model puis retourner la page

// On va fetch tout les messages de avec (le client username et le maintenancier username) et les afficher dans la page
// On va se connecter directement apres (le chargement de la page) a la web WebSocket.
// On va definir les fonctions de reception de message et d'envoie de message

// Au chargement de la page des taches on va 
// Recupere les ids de toutes les taches
// pour chacune des taches on va executer une requete back qui va chercher
// Le username du client , Le username du maintenancier associer a la tache 
// On va ensuit executer une fonction pour savoir si il existe un message en bd qui correspond aux deux users (client et maintenancier )
// la fonction retourne un true si il en existe un
const messageInput = document.querySelector("#monMessage");
const messageButton = document.querySelector("#envoi");
const chatArea = document.querySelector("#chat");


let TaskId = null;
let stompClient1 = null;
let clientUsername = null;
let selectedUsername = null;


var Taches = document.querySelector("#taskContact");
fetch('/username').then((response)=>{return response.text()}).then((data)=>{clientUsername = data});
console.log(clientUsername);

// Je veux charger les taches dans la div 
function fetchTask(){
    var url = "/loadTask"
    fetch(url, {method: 'GET'})
    .then(response => {
        console.log("Données reçues pour lister les chat encours");
        return response.json(); /* Renvoyer la réponse JSON*/
    })
    .then(data => {
        // Traitement des données renvoyées par le serveur
        console.log(data);
        DisplayChatContact(data);
    })
    .catch(error => {
        // Gestion des erreurs
        console.error(error);
    });
}
// Je veux afficher les taches dans la zone de contact
function DisplayChatContact(data){
    data.forEach(task =>{
        var divChat = document.createElement("div");
        // divChat.classList.add("chatContent");
        divChat.innerHTML = `<div id="TA${task.id1}" class="chatContent" style="border-radius:15px;">
                                    <div>
                                        <h4> TACHE ${task.id1}</h4>
                                        <li>${task.type}</li>
                                        <li>${task.date}</li>
                                        <li>${task.description}</li>
                                    </div>
                                    <div id="other${task.id1}" style="visibility: hidden;">
                                        ${task.selectedUsername}
                                    </div>
                                </div>`;
        divChat.addEventListener("click", ()=>{
                                                TaskId = task.id1;
                                                selectedUsername = task.selectedUsername;
                                                console.log(TaskId + clientUsername + selectedUsername);
                                                fetchAndDisplayUserChat();
                                            }
                                )
        Taches.appendChild(divChat);
    })
   
}

//Je veux charger et afficher les messages relatifs a une tache
async function fetchAndDisplayUserChat(){

    const userChatResponse = await fetch(`/messages/${clientUsername}/${selectedUsername}/${TaskId}`);
    const userChat = await userChatResponse.json();
    console.log(userChat);
    chatArea.innerHTML = "";
    if(userChat.length != 0){
        userChat.forEach(chat => {
            displayMessage(chat.senderId, chat.content, chat.timestamp);
        });
        chatArea.scrollTop = chatArea.scrollHeight;
    }
    else{
        console.log("no message yet");
    }
}
//Je veux envoyer un message
function sendMessage(event){
    date = Date();
    const messageContent = messageInput.value.trim();
    if (messageContent && stompClient1){
        const chatMessage = {
            senderId:  clientUsername,
            recipientId: selectedUsername,
            content:messageInput.value.trim(),
            taskId: TaskId,
            timestamp: new Date()
        };
        stompClient1.send("/app/chat", {}, JSON.stringify(chatMessage));
        displayMessage(clientUsername, messageInput.value.trim(), date);
        messageInput.value = '';
    }
    chatArea.scrollTop = chatArea.scrollHeight;
    event.preventDefault();
}

function connect(){
   
    const socket = new SockJS('/websocket');
    stompClient1 = Stomp.over(socket);
    stompClient1.connect({}, onConnected, onError);

}

function onConnected(){
    stompClient1.subscribe(`/user/${clientUsername}/queue/messages`, onMessageReceived);

}

function onMessageReceived(payload){
    
    console.log("Message recu des websocket");
    console.log('Message received', payload);
    const message = JSON.parse(payload.body);
    if(selectedUsername === message.senderId){
        displayMessage(message.senderId, message.content);
        chatArea.scrollTop = chatArea.scrollHeight;
    }
    else{
        const jsonData = JSON.stringify(message);
        // On va sauvegarder la notification pour l'afficher au chargement de la page des taches
        fetch('/saveNotification', {method:'POST', headers: {'Content-Type': 'application/json'}, body: jsonData})
    }
}

function onError(){
    console.log('WebSocket Error');
}
//fin
function displayMessage(senderId, content, date=null){
    const messageContainer = document.createElement('div');
    if(!date){
        date = Date();
    }
    if(senderId == clientUsername){
        messageContainer.classList.add('message');
    }
    else{
        messageContainer.classList.add('receiver');
    }
    const message = document.createElement('li');
    const horaire = document.createElement('h5');

    message.textContent += content;
    horaire.textContent += date;

    messageContainer.appendChild(message);
    messageContainer.appendChild(horaire);

    if (senderId == clientUsername) {
        messageContainer.classList.add('message');
        messageContainer.classList.add('align-right');
      } else {
        messageContainer.classList.add('receiver');
        messageContainer.classList.add('align-left');
      }

      messageContainer.style.marginBottom = '20px';

    chatArea.appendChild(messageContainer);
}

fetchTask();
connect();
messageButton.addEventListener("click", sendMessage);


















// function connect(e){

//     TaskId = getMessageDiv(e.target).id;
//     const url = `task/${TaskId}`;

//     fetch(url, {method: 'GET'})
//         .then(response => {
//             console.log("Données reçues pour creerTaches");
//             if (response.ok) {return response.json(); /* Renvoyer la réponse JSON*/}
//             else {throw new Error('Erreur de la requête creerTaches');}
//         })
//         .then(data => {
//             // Traitement des données renvoyées par le serveur
//             console.log(data);
//             clientUsername = data.clientUsername;
//             selectedUsername = data.selectedUsername;
//         })
//         .catch(error => {
//             // Gestion des erreurs
//             console.error(error);
//         });


//     const socket = new SockJs('/ws');
//     stompClient1 = stomp.over(socket);
//     stompClient1.connect({}, onConnected, onError);

//     e.preventDefault();
// }

// function onConnected(){
//     fetchAndDisplayUserChat();
//     stompClient1.suscribe(`/user/${clientUsername}/queue/messages`, onMessageReceived);

// }

// async function fetchAndDisplayUserChat(){
//     const userChatResponse = await fetch(`/messages/${clientUsername}/${selectedUsername}`);
//     const userChat = await userChatResponse.json();
//     chatArea.innerHTML = "";
//     userChat.forEach(chat => {
//         displayMessage(chat.senderId, chat.content);
//     });
//     chatArea.scrollTop = chatArea.scrollHeight;
// }

// function onMessageReceived(payload){
//     console.log('Message received', payload);
//     const message = JSON.parse(payload.body);
//     if(selectedUserId === message.senderId){
//         displayMessage(message.senderId, message.content);
//         chatArea.scrollTop = chatArea.scrollHeight;
//     }
//     else{
//         const jsonData = JSON.stringify(message);
//         // On va sauvegarder la notification pour l'afficher au chargement de la page des taches
//         fetch('/saveNotification', {method:'POST', headers: {'Content-Type': 'application/json'}, body: jsonData})
//     }
// }

// function displayMessage(senderId, content){
//     const messageContainer = document.createElement('div');
//     messageContainer.classList.add('message');
//     if(senderId == nickname){
//         messageContainer.classList.add('message');
//     }
//     else{
//         messageContainer.classList.add('receiver');
//     }
//     const message = document.createElement('p');
//     message.textContent = content;
//     messageContainer.appendChild(message);
//     chatArea.appendChild(messageContainer);
// }

// function sendMessage(event){
//     const messageContent = messageInput.ariaValueMax.trim();
//     if (messageContent && stompClient1){
//         const chatMessage = {
//             senderId:  clientUsername,
//             recipientID: selectedUsername,
//             content:messageInput.value.trim(),
//             taskId: TaskId,
//             timestamp: new Date()
//         };
//         stompClient1.send("/app/chat", {}, JSON.stringify(chatMessage));
//         displayMessage(clientUsername, messageInput.value.trim());
//         messageInput.value = '';
//     }
//     chatArea.scrollTop = chatArea.scrollHeight;
//     event.preventDefault();
// }


// function getMessageDiv(buttonElement) {
//     var div = buttonElement.closest('.accordion-collapse');
//     if (div) {
//       return div;
//     }
//     return null;
// }

// console.log("Hello");
// console.log(Taches.length);
// for(let i = 0 ; i < Taches.length; i++){
//     console.log("Hello");
//     alert("alert");
//     let Chat = Taches[i].querySelector("#tacheButton");
//     console.log(Chat[0]);
//     Chat[0].addEventListener("click", connect);
// }
// // Taches = Object.values(taches);
// // taches.forEach(Tache=>{
// //     Chat.addEventListener("click", connect);
// // })
// messageForm.addEventListener('submit', sendMessage, true);






