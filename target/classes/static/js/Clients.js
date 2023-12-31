//Masquer et separer les grands menu de la page Client
document.getElementById("nav1").addEventListener("click", function () {

  var el1 = document.querySelector(".sous-menu");
  var el2 = document.querySelector(".maintenance");
  var el3 = document.querySelector(".equipements");
  //  var el4 = document.querySelector(".messagerie");
  var el5 = document.querySelector(".bienvenu");
  var el6 = document.querySelector(".action");

  el1.style.display = "block";
  el2.style.display = "block";
  el3.style.display = "none";
  //  el4.style.display = "none";
  el5.style.display = "none";
  el6.style.display = "block";
});

document.getElementById("nav2").addEventListener("click", function () {

  var el1 = document.querySelector(".sous-menu");
  var el2 = document.querySelector(".maintenance");
  var el3 = document.querySelector(".equipements");
  // var el4 = document.querySelector(".messagerie");
  var el5 = document.querySelector(".bienvenu");

  el1.style.display = "none";
  el2.style.display = "none";
  el3.style.display = "block";
  // el4.style.display = "none";
  el5.style.display = "none";

});

document.getElementById("nav3").addEventListener("click", function () {
  console.log("nav3");
  var el1 = document.querySelector(".sous-menu");
  var el2 = document.querySelector(".maintenance");
  var el3 = document.querySelector(".equipements");
  // var el4 = document.querySelector(".messagerie");
  var el5 = document.querySelector(".bienvenu");

  el1.style.display = "none";
  el2.style.display = "none";
  el3.style.display = "none";
  // el4.style.display = "block";
  el5.style.display = "none";

});

//// MASQUER LES MENU DES PAGES CLIENTS AU NIVEAU du block Maintenances

/*document.getElementById("a").addEventListener("click", function() {
    
  var el1 = document.querySelector(".sous-menu");
  var el2 = document.querySelector(".taches");
  var el3 = document.querySelector(".calendar");
  var el4 = document.querySelector(".messagerie2");
  var el5 = document.querySelector(".bienvenu");

  el1.style.display = "block";
  el2.style.display = "block";
  el3.style.display = "none";
  el4.style.display = "none";
  el5.style.display = "none";
  
});

document.getElementById("b").addEventListener("click", function() {
    
  var el1 = document.querySelector(".sous-menu");
  var el2 = document.querySelector(".taches");
  var el3 = document.querySelector(".calendar");
  var el4 = document.querySelector(".messagerie2");
  var el5 = document.querySelector(".bienvenu");

  el1.style.display = "none";
  el2.style.display = "none";
  el3.style.display = "block";
  el4.style.display = "none";
  el5.style.display = "none";
  
});

document.getElementById("c").addEventListener("click", function() {
    
  var el1 = document.querySelector(".sous-menu");
  var el2 = document.querySelector(".taches");
  var el3 = document.querySelector(".calendar");
  var el4 = document.querySelector(".messagerie2");
  var el5 = document.querySelector(".bienvenu");

  el1.style.display = "none";
  el2.style.display = "none";
  el3.style.display = "none";
  el4.style.display = "block";
  el5.style.display = "none";
  
});*/

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

function calender() {
  window.location.href = "/RepairIt/Utilisateur/Calendrier";
}

document.getElementById("ChoiceN").addEventListener('click', function () {
  var a = document.getElementById("todoN");
  var b = document.getElementById("todoC");
  var c = document.getElementById("todoF");
  a.style.display = "none";
  b.className = "col-md-6";
  c.className = "col-md-6";
  if (b.style.display == "none") {
    c.className = "col-md-8 me-4";
  }
  if (c.style.display == "none") {
    b.className = "col-md-8 me-4";
  }
})


document.getElementById("ChoiceC").addEventListener('click', function () {
  var b = document.getElementById("todoN");
  var a = document.getElementById("todoC");
  var c = document.getElementById("todoF");
  a.style.display = "none";
  b.className = "col-md-6";
  c.className = "col-md-6";
  if (b.style.display == "none") {
    c.className = "col-md-8 me-4";
  }
  if (c.style.display == "none") {
    b.className = "col-md-8 me-4";
  }
})


document.getElementById("ChoiceT").addEventListener('click', function () {
  var b = document.getElementById("todoN");
  var c = document.getElementById("todoC");
  var a = document.getElementById("todoF");
  a.style.display = "none";
  b.className = "col-md-6";
  c.className = "col-md-6";
  if (b.style.display == "none") {
    c.className = "col-md-8 me-4";
  }
  if (c.style.display == "none") {
    b.className = "col-md-8 me-4";
  }
})


document.getElementById("ChoiceD").addEventListener('click', function () {
  var b = document.getElementById("todoN");
  var c = document.getElementById("todoC");
  var a = document.getElementById("todoF");
  a.style.display = "block";
  b.style.display = "block";
  c.style.display = "block";
  b.className = "col-md-4";
  c.className = "col-md-4";
  a.className = "col-md-4";

})

//RATE US

function payementOk() {
  var popup = document.getElementById("ModalStar");
  popup.style.display = "block"
  popup.classList.add("show")
}

var selectedStars = 0;

function selectStars(stars) {
  selectedStars = stars;
  updateStars();
}

function updateStars() {
  var stars = document.getElementsByClassName("stars");
  for (var i = 0; i < stars.length; i++) {
    if (i < selectedStars) {
      stars[i].classList.add("filled");
    } else {
      stars[i].classList.remove("filled");
    }
  }
}

function validerRateUs() {
  const url = "https://127.0.0.1:9001/RateUs";
  const params = new URLSearchParams();
  params.append('avis', selectedStars);

  fetch(url, {
    method: 'POST',
    body: params
  })
    .then(response => {
      console.log("Données reçues pour avis utilisateur");

      if (response.ok) {


        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête avis utilisateur');
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