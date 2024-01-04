var boutons = document.querySelectorAll('.btn.btn-primary');

boutons.forEach(function(bouton) {
  bouton.addEventListener('click', function() {
    var id = bouton.id;
    var numero = parseInt(id.substring(3), 10);
    var quantite = parseInt(document.getElementById("valeur" + numero).value);

    var xhr = new XMLHttpRequest();
    var url = `https://127.0.0.1:9001/RepairIt/Boutique/AjouterPanier/${numero}/${quantite}`;
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
        console.log(xhr.responseText);
        
        document.getElementById("AnimePa").style.display="block";

        anime({
          targets: '#panierplane',
          translateX: 600,
          direction: 'alternate',
          loop: true,
          easing: 'steps(5)'
        });

        setTimeout(function() {
          document.getElementById("SendSuccs").style.display = "block";
        }, 3000);
        
        // Recharger la page après que l'animation soit terminée
        setTimeout(function() {
        
          document.getElementById("AnimePa").style.display = "none";

          window.location.reload();
        }, 5000);
      }
    };
    xhr.setRequestHeader("Cache-Control", "no-cache");
    xhr.send();
  });
});

var btn = document.querySelectorAll('.btn.btn-danger');

btn.forEach(function(bouton) {
  bouton.addEventListener('click', function() {
    var id = bouton.id;
    var numero = parseInt(id.substring(3), 10);

    var xhr = new XMLHttpRequest();
    var url = `https://127.0.0.1:9001/RepairIt/Boutique/RetirerPanier/${numero}`;
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
        console.log(xhr.responseText);
      }
    };
    xhr.setRequestHeader("Cache-Control", "no-cache"); 
    xhr.send();
  });
});

var botn = document.querySelectorAll('.btn.btn-warning');

botn.forEach(function(bouton) {
  bouton.addEventListener('click', function() {
    var id = bouton.id;
    var numero = parseInt(id.substring(3), 10);
    var quantite = parseInt(document.getElementById("valeur" + numero).value);

    var xhr = new XMLHttpRequest();
    var url = `https://127.0.0.1:9001/RepairIt/Boutique/AjouterCommande/${numero}/${quantite}`;
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
        console.log(xhr.responseText);
      }
    };
    xhr.setRequestHeader("Cache-Control", "no-cache"); 
    xhr.send();
  });
});

var bton = document.querySelectorAll('.btn.btn-danger.SUPCMD');

bton.forEach(function(bouton) {
  bouton.addEventListener('click', function() {
    var id = bouton.id;
    var numero = parseInt(id.substring(3), 10);

    var xhr = new XMLHttpRequest();
    var url = `https://127.0.0.1:9001/RepairIt/Boutique/RetirerCommande/${numero}`;
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
        console.log(xhr.responseText);
      }
    };
    xhr.setRequestHeader("Cache-Control", "no-cache"); 
    xhr.send();
  });
});







// var boutons = document.querySelectorAll('.btn.btn-primary');

// boutons.forEach(function(bouton) {
//   bouton.addEventListener('click', function() {
//     var id = bouton.id;
//     var numero = parseInt(id.substring(3), 10);// Supprime les 3 premiers caractères (la partie "btn") et convertis en entier
//     var quantite=parseInt(document.getElementById("valeur"+numero).value);

//     const url = `https://127.0.0.1:9001/RepairIt/Boutique/AjouterPanier/${numero}/${quantite}`;

//     fetch(url, {
//       method: 'POST'
//     })
//     .then(function(response) {
//       return response.text();
//     })
//     .then(function(data) {
//       console.log(data);
//     })
//     .catch(function(error) {
//       console.error('Erreur:', error);
//     });
//   });
// });

// var btn = document.querySelectorAll('.btn.btn-danger');

// btn.forEach(function(bouton) {
//   bouton.addEventListener('click', function() {
//     var id = bouton.id;
//     var numero = parseInt(id.substring(3), 10);// Supprime les 3 premiers caractères (la partie "btn") et convertis en entier
   
//     const url = `https://127.0.0.1:9001/RepairIt/Boutique/RetirerPanier/${numero}`;

//     fetch(url, {
//       method: 'POST'
//     })
//     .then(function(response) {
//       return response.text();
//     })
//     .then(function(data) {
//       console.log(data);
//     })
//     .catch(function(error) {
//       console.error('Erreur:', error);
//     });
//   });
// });

// var botn = document.querySelectorAll('.btn.btn-warning');
// botn.forEach(function(bouton) {
//   bouton.addEventListener('click', function() {
//     var id = bouton.id;
//     var numero = parseInt(id.substring(3), 10);
//     var quantite=parseInt(document.getElementById("valeur"+numero).value);

//     const url = `https://127.0.0.1:9001/RepairIt/Boutique/AjouterCommande/${numero}/${quantite}`;

//     fetch(url, {
//       method: 'POST'
//     })
//     .then(function(response) {
//       return response.text();
//     })
//     .then(function(data) {
//       console.log(data);
//     })
//     .catch(function(error) {
//       console.error('Erreur:', error);
//     });
//   });
// });

// var bton = document.querySelectorAll('.btn.btn-danger.SUPCMD');

// bton.forEach(function(bouton) {
//   bouton.addEventListener('click', function() {
//     var id = bouton.id;
//     var numero = parseInt(id.substring(3), 10);// Supprime les 3 premiers caractères (la partie "btn") et convertis en entier
   
//     const url = `https://127.0.0.1:9001/RepairIt/Boutique/RetirerCommande/${numero}`;

//     fetch(url, {
//       method: 'POST'
//     })
//     .then(function(response) {
//       return response.text();
//     })
//     .then(function(data) {
//       console.log(data);
//     })
//     .catch(function(error) {
//       console.error('Erreur:', error);
//     });
//   });
// });