document.getElementById("cart").addEventListener('click',function()
{
    document.getElementById("product").classList.remove("activer");
    document.getElementById("cart").classList.add("activer");
    document.getElementById("command").classList.remove("activer");
    document.getElementById("produit").style.display="none";
    document.getElementById("panier").style.display="block";
    document.getElementById("choix1").style.display="none";
    document.getElementById("choix2").style.display="none";
    document.getElementById("found").style.display="none";
    document.getElementById("commander").style.display="none";
});

document.getElementById("product").addEventListener('click',function()
{
    document.getElementById("cart").classList.remove("activer");
    document.getElementById("product").classList.add("activer");
    document.getElementById("command").classList.remove("activer");
    document.getElementById("produit").style.display="block";
    document.getElementById("panier").style.display="none";
    document.getElementById("choix1").style.display="block";
    document.getElementById("choix2").style.display="block";
    document.getElementById("found").style.display="none";
    document.getElementById("commander").style.display="none";
});

document.getElementById("command").addEventListener('click',function()
{
    document.getElementById("cart").classList.remove("activer");
    document.getElementById("command").classList.add("activer");
    document.getElementById("product").classList.remove("activer");
    document.getElementById("produit").style.display="none";
    document.getElementById("panier").style.display="none";
    document.getElementById("choix1").style.display="none";
    document.getElementById("choix2").style.display="none";
    document.getElementById("found").style.display="none";
    document.getElementById("commander").style.display="block";

});

document.getElementById("Found").addEventListener('click',function()
{
   document.getElementById("produit").style.display="none";
    document.getElementById("panier").style.display="none";
    document.getElementById("choix1").style.display="block";
    document.getElementById("found").style.display="block";
    document.getElementById("choix2").style.display="none";
    document.getElementById("commander").style.display="none";
});

document.getElementById("Me1").addEventListener('click',function()
{
   document.getElementById("Mecani").style.display="none";
    document.getElementById("produit").style.display="block";
    document.getElementById("Tele").style.display="none";
    document.getElementById("Ordin").style.display="none";
    document.getElementById("outi").style.display="none";
    document.getElementById("Electro").style.display="block";
    var element=document.querySelectorAll('.nav-item');
    for(var i=0;i<element.length;i++)
    {
        element[i].classList.remove("active");
    }
    document.getElementById("Me1").classList.add("active");
});

document.getElementById("Me2").addEventListener('click',function()
{
   document.getElementById("Mecani").style.display="block";
    document.getElementById("produit").style.display="block";
    document.getElementById("Tele").style.display="none";
    document.getElementById("Ordin").style.display="none";
    document.getElementById("outi").style.display="none";
    document.getElementById("Electro").style.display="none";
    var element=document.querySelectorAll('.nav-item');
    for(var i=0;i< element.length;i++)
    {
        element[i].classList.remove("active");
    }
    document.getElementById("Me2").classList.add("active");
});

document.getElementById("Me3").addEventListener('click',function()
{
   document.getElementById("Mecani").style.display="none";
    document.getElementById("produit").style.display="block";
    document.getElementById("Tele").style.display="block";
    document.getElementById("Ordin").style.display="none";
    document.getElementById("outi").style.display="none";
    document.getElementById("Electro").style.display="none";
    var element=document.querySelectorAll('.nav-item');
    for(var i=0;i< element.length;i++)
    {
        element[i].classList.remove("active");
    }
    document.getElementById("Me3").classList.add("active");
});

document.getElementById("Me4").addEventListener('click',function()
{
   document.getElementById("Mecani").style.display="none";
    document.getElementById("produit").style.display="block";
    document.getElementById("Tele").style.display="none";
    document.getElementById("Ordin").style.display="block";
    document.getElementById("outi").style.display="none";
    document.getElementById("Electro").style.display="none";
    var element=document.querySelectorAll('.nav-item');
    for(var i=0;i< element.length;i++)
    {
        element[i].classList.remove("active");
    }
    document.getElementById("Me4").classList.add("active");
});

document.getElementById("Me5").addEventListener('click',function()
{
   document.getElementById("Mecani").style.display="none";
    document.getElementById("produit").style.display="block";
    document.getElementById("Tele").style.display="none";
    document.getElementById("Ordin").style.display="none";
    document.getElementById("outi").style.display="block";
    document.getElementById("Electro").style.display="none";
    var element=document.querySelectorAll('.nav-item');
    for(var i=0;i< element.length;i++)
    {
        element[i].classList.remove("active");
    }
    document.getElementById("Me5").classList.add("active");
});

// INCREMENTER ET DECREMENTER
function increment(index) {
    var elementId = "valeur";
    var number = parseInt(index,10);
    var concatenatedText = elementId + number;
    var valeurInput = document.getElementById(concatenatedText);
    var valeur = parseInt(valeurInput.value);
    valeurInput.value = valeur + 1;
  }
  
  function decrement(index) {
    var elementId = "valeur";
    var number = parseInt(index,10);
    var concatenatedText = elementId + number;
    var valeurInput = document.getElementById(concatenatedText);
    var valeur = parseInt(valeurInput.value);
    if (valeur > 0) {
      valeurInput.value = valeur - 1;
    }
  }

  function ValiderAdmin(){
   
     var password=document.getElementById("passadmin").value;

     var xhr = new XMLHttpRequest();
     var url = `https://127.0.0.1:9001/RepairIt/Boutique/Administrator/${password}`;
     xhr.open("POST", url, true);
     xhr.onreadystatechange = function() {
       if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
         console.log(xhr.responseText);

         document.getElementById("Admin").style.display="block";
         document.getElementById("Menu").style.display="none";
         document.getElementById("produit").style.display="none";
       }
     };
     xhr.setRequestHeader("Cache-Control", "no-cache"); 
     xhr.send();

  }

  //RECHERCHE DE PRODUIT

    function filtrerProduits() {
        var recherche = document.getElementById('recherche').value.toLowerCase();
        var form=document.getElementById("found")
        var produits = form.document.getElementsByClassName('col-md-3');

        for (var i = 0; i < produits.length; i++) {
            var produit = produits[i];
            var nomProduit = produit.querySelector('.card-title').textContent.toLowerCase();

            if (nomProduit.includes(recherche)) {
                produit.style.display = 'block';
            } else {
                produit.style.display = 'none';
            }
        }
        form.style.display="block";
    }