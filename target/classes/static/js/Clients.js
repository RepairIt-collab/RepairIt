//Masquer et separer les grands menu de la page Client
document.getElementById("nav1").addEventListener("click", function() {
  
  var el1 = document.querySelector(".sous-menu");
  var el2 = document.querySelector(".maintenance");
  var el3 = document.querySelector(".equipements");
  var el4 = document.querySelector(".messagerie");
  var el5 = document.querySelector(".bienvenu");
  var el6 = document.querySelector(".action");

  el1.style.display = "block";
  el2.style.display = "block";
  el3.style.display = "none";
  el4.style.display = "none";
  el5.style.display = "none";
  el6.style.display = "block";
});

document.getElementById("nav2").addEventListener("click", function() {
    
  var el1 = document.querySelector(".sous-menu");
  var el2 = document.querySelector(".maintenance");
  var el3 = document.querySelector(".equipements");
  var el4 = document.querySelector(".messagerie");
  var el5 = document.querySelector(".bienvenu");

  el1.style.display = "none";
  el2.style.display = "none";
  el3.style.display = "block";
  el4.style.display = "none";
  el5.style.display = "none";
  
});

document.getElementById("nav3").addEventListener("click", function() {
  console.log("nav3");
  var el1 = document.querySelector(".sous-menu");
  var el2 = document.querySelector(".maintenance");
  var el3 = document.querySelector(".equipements");
  var el4 = document.querySelector(".messagerie");
  var el5 = document.querySelector(".bienvenu");

  el1.style.display = "none";
  el2.style.display = "none";
  el3.style.display = "none";
  el4.style.display = "block";
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
document.getElementById("btncompte").addEventListener('click',function(){
  document.getElementById('PROF').style.display="block";
  document.getElementById("btncompte").style.display="none";
  document.getElementById('CLTS').classList.replace('col-md-12', 'col-md-10');
  document.getElementById('ButtonSelec').style.display="none";
});

document.getElementById("return").addEventListener('click',function(){
  document.getElementById('PROF').style.display="none";
  document.getElementById("btncompte").style.display="block";
  document.getElementById('CLTS').classList.replace('col-md-10', 'col-md-12');
  document.getElementById('ButtonSelec').style.display="block";
});