//GESTION DU MASQUAGE DES ELEMENT DE LA CONNECTION
document.getElementById("bt1").addEventListener("click", function() {
    var elements = document.querySelectorAll(".row.my-2");
    
    for (var i = 0; i < elements.length; i++) {
      elements[i].style.display = "none";
    }
    document.getElementById("connect").style.display="none";
    for (var i = 0; i < elements.length; i++) {
      if(elements[i].id =="one")
      elements[i].style.display ="flex";
    }
  
    });
  
    document.getElementById("bt2").addEventListener("click", function() {
      var elements = document.querySelectorAll(".row.my-2");
  
      for (var i = 0; i < elements.length; i++) {
        elements[i].style.display = "none";
      }
      document.getElementById("connect").style.display="none";
      for (var i = 0; i < elements.length; i++) {
        if(elements[i].id =="two")
        elements[i].style.display = "flex";
      }
    
      });
  
      document.getElementById("bt3").addEventListener("click", function() {
        var elements = document.querySelectorAll(".row.my-2");
    
        for (var i = 0; i < elements.length; i++) {
          elements[i].style.display = "none";
        }
        document.getElementById("connect").style.display="none";
        for (var i = 0; i < elements.length; i++) {
          if(elements[i].id =="three")
          elements[i].style.display = "flex";
        }
      
        });



// VALIDER LA CONNECTION LORS DU LOGIN
function ValideConnection(){

          username=document.getElementById("UTILISATEUR").value;
          password=document.getElementById("PASSWORD").value;


    console.log(username +""+password);
    $.ajax({
      url: "/RepairIt/Login/Client", // Endpoint de votre fonction Java
      type: "POST",
      data:{
        username:username,
        password:password,
      },
      success: function (response) {
        console.log(response)
        // window.location.reload();
         window.location.href = '/RepairIt/Client';
      },
      error: function () {
        console.log("Erreur lors de la requête AJAX !");
      }
    });
}

function ValideConnection2(){

  username=document.getElementById("UTILISATEUR2").value;
  password=document.getElementById("PASSWORD2").value;


        console.log(username +""+password);
        $.ajax({
        url: "/RepairIt/Login/Maintenancier", // Endpoint de votre fonction Java
        type: "POST",
        data:{
        username:username,
        password:password,
        },
        success: function (response) {
        console.log(response)
        // window.location.reload();
        window.location.href = '/RepairIt/Maintenancier';
        },
        error: function () {
        console.log("Erreur lors de la requête AJAX !");
        }
        });
}

//AFFICHER LES LICENCES CLIENT,MAINTENANCIER,...
function LicenceC(){
  document.getElementById("Lic-Client").style.display="block";
  document.getElementById("Logo-Client").style.display="none";
}

document.addEventListener("DOMContentLoaded", function() {
  var caseClient = document.getElementById("check1");
  var validElement = document.getElementById("valid1");

  caseClient.addEventListener("change", function() {
    if (caseClient.checked) {
      validElement.disabled = false;
    } else {
      validElement.disabled = true;
    }
  });
});

function LicenceM(){
  
  document.getElementById("Lic-Main").style.display="block";
  document.getElementById("Logo-Main").style.display="none";
}

document.addEventListener("DOMContentLoaded", function() {
  var caseClient = document.getElementById("check2");
  var validElement = document.getElementById("valid2");

  caseClient.addEventListener("click",function(){
    requestLocationPermission()
  })
  caseClient.addEventListener("change", function() {
    if (caseClient.checked) {
      validElement.disabled = false;
    } else {
      validElement.disabled = true;
    }
  });
});