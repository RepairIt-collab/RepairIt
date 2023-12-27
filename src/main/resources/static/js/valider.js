//VALIDATION DU FORMULAIRE CLIENT
var clients;



$(document).ready(function() {
  $.ajax({
      url: "/loadClient",
      type: "GET",
      success: function(data) {
          clients = data; // Assigner les données à la constante 
          console.log(clients);
  

    var ChampC1=document.getElementById("user");
    ChampC1.addEventListener("input",function() {
      var invFeedback = document.getElementsByClassName("invalid-feedback");
      var ChampC1=document.getElementById("user");
      clients.forEach(client =>{
        if (ChampC1.value.length==0) {
          invFeedback[0].style.display="none";
          ChampC1.classList.remove("is-valid");
          ChampC1.classList.add("is-invalid");
          invFeedback[1].style.display="block";
          
        } 
        if (client.username==ChampC1.value) {
          invFeedback[1].style.display="none";
          ChampC1.classList.remove("is-valid");
          ChampC1.classList.add("is-invalid");
          invFeedback[0].style.display="block";
          
        } 
        if (client.username!=ChampC1.value && ChampC1.value.length!=0) {
          invFeedback[0].style.display="none";
          invFeedback[1].style.display="none";
          ChampC1.classList.add("is-valid");
          ChampC1.classList.remove("is-invalid");
                  }
      })
    });

    var ChampC2=document.getElementById("password");
    ChampC2.addEventListener("input",function() {
      var ChampC2=document.getElementById("password");
      var regex = /[0-9!@#$%^&*()]/g; // Expression régulière pour correspondre à un chiffre ou un caractère spécial
      var correspondances = ChampC2.value.match(regex);
      
      if (ChampC2.value.length>=8 && correspondances && correspondances.length >= 2) {
        ChampC2.classList.remove("is-invalid");
        ChampC2.classList.add("is-valid");
              } else {
        ChampC2.classList.add("is-invalid");
        ChampC2.classList.remove("is-valid");
        
      }
    });

    var ChampC02=document.getElementById("password01");
    ChampC02.addEventListener("input",function() {
      var ChampC02=document.getElementById("password01");
      var ChampC2=document.getElementById("password");
    
      if (ChampC2.value == ChampC02.value) {
        ChampC02.classList.remove("is-invalid");
        ChampC02.classList.add("is-valid");
              } else {
        ChampC02.classList.add("is-invalid");
        ChampC02.classList.remove("is-valid");
        
      }
    });

    var ChampC3=document.getElementById("mail");
    ChampC3.addEventListener("input",function() {
      var ChampC3=document.getElementById("mail");
      var regex = /^[\w-]+(\.[\w-]+)*@gmail.com$/;
      var invFeedback = document.getElementsByClassName("invalid-feedback");

      clients.forEach(client =>{
        if ( regex.test(ChampC3.value) && client.email!=ChampC3.value){
          invFeedback[4].style.display="none";
          ChampC3.classList.remove("is-invalid");
          ChampC3.classList.add("is-valid");
          invFeedback[5].style.display="none";
                  }
        if ( regex.test(ChampC3.value) && client.email==ChampC3.value){
          invFeedback[5].style.display="none";
          ChampC3.classList.add("is-invalid");
          ChampC3.classList.remove("is-valid");
          invFeedback[4].style.display="block";
          
        }
        if ( !regex.test(ChampC3.value)){
          ChampC3.classList.add("is-invalid");
          ChampC3.classList.remove("is-valid");
          invFeedback[5].style.display="block"; 
          invFeedback[4].style.display="none";
          
        }
      })
    
    });

    var ChampC4=document.getElementById("tel");
    ChampC4.addEventListener("input",function() {
      var ChampC4=document.getElementById("tel");
      var regex = /[0-9]/g; // Expression régulière pour correspondre à des chiffres 
      var correspondances = ChampC4.value.match(regex);
      var invFeedback = document.getElementsByClassName("invalid-feedback");
      
      clients.forEach(client =>{
        if (ChampC4.value.length==9 && correspondances && correspondances.length == 9 && (ChampC4.value.substring(0,2)=="62" || ChampC4.value.substring(0,2)=="65" || ChampC4.value.substring(0,2)=="67" || ChampC4.value.substring(0,2)=="69")){
           if(client.telephone!=ChampC4.value)
           {
            ChampC4.classList.remove("is-invalid");
            ChampC4.classList.add("is-valid");
            invFeedback[7].style.display="none";
            invFeedback[6].style.display="none";
                       }
           else
           {
            ChampC4.classList.add("is-invalid");
            ChampC4.classList.remove("is-valid");
            invFeedback[7].style.display="none";
            invFeedback[6].style.display="block";
            
           }      
        }
        else{
          if(client.telephone==ChampC4.value)
          {
            ChampC4.classList.add("is-invalid");
              ChampC4.classList.remove("is-valid");
              invFeedback[7].style.display="block";
              invFeedback[6].style.display="block";     
          }
          else{
            ChampC4.classList.add("is-invalid");
              ChampC4.classList.remove("is-valid");
              invFeedback[7].style.display="block";
              invFeedback[6].style.display="none";  
          }
        }
      })

    });

    // Sélectionnez tous les éléments avec la classe "form-control"
    var formulaire = document.getElementById("form1");
    var formControls = formulaire.querySelectorAll('.form-control');
    
    // Sélectionnez le bouton "Submit" ou "Valider"
    var submit = document.getElementById('valid1');
    var caseClient = document.getElementById("check1");
    
    submit.disabled = false;
    // Ajoutez un écouteur d'événement pour chaque champ de formulaire
    formControls.forEach(element =>{
      element.addEventListener('input', function () {
          if (!element.checkValidity()) {
            submit.disabled = true;
          }
        });
    
        if (!caseClient.checked) {
          submit.disabled = true;
        }
      });

},
error: function(xhr, status, error) {
    console.error(error);
}
});
});






//VALIDATION DU FORMULAIRE MAINTENANCIER



var C1=document.getElementById("nom");
C1.addEventListener("input",function() {
  var ChampC1=document.getElementById("nom");
  var regex = /[!@#$%^&*();><./|=]/g; // Expression régulière pour un caractère spécial
  var correspondances = ChampC1.value.match(regex);
  if (ChampC1.value.length==0 || correspondances) {
    ChampC1.classList.remove("is-valid");
    ChampC1.classList.add("is-invalid");
  } else {
    ChampC1.classList.add("is-valid");
    ChampC1.classList.remove("is-invalid");
  }
});

var C2=document.getElementById("user2");
C2.addEventListener("input",function() {
  var ChampC1=document.getElementById("user2");
  if (ChampC1.value.length==0 ) {
    ChampC1.classList.remove("is-valid");
    ChampC1.classList.add("is-invalid");
  } else {
    ChampC1.classList.add("is-valid");
    ChampC1.classList.remove("is-invalid");
  }
});

var C3=document.getElementById("password2");
C3.addEventListener("input",function() {
  var ChampC2=document.getElementById("password2");
  var regex = /[0-9!@#$%^&*()]/g; // Expression régulière pour correspondre à un chiffre ou un caractère spécial
  var correspondances = ChampC2.value.match(regex);

  if (ChampC2.value.length>=8 && correspondances && correspondances.length >= 2) {
    ChampC2.classList.remove("is-invalid");
    ChampC2.classList.add("is-valid");
  } else {
    ChampC2.classList.add("is-invalid");
    ChampC2.classList.remove("is-valid");
  }
});

var C4=document.getElementById("password02");
C4.addEventListener("input",function() {
  var ChampC02=document.getElementById("password02");
  var ChampC2=document.getElementById("password2");
 
  if (ChampC2.value == ChampC02.value) {
    ChampC02.classList.remove("is-invalid");
    ChampC02.classList.add("is-valid");
  } else {
    ChampC02.classList.add("is-invalid");
    ChampC02.classList.remove("is-valid");
  }
});

var C5=document.getElementById("mail2");
C5.addEventListener("input",function() {
  var ChampC3=document.getElementById("mail2");
  var regex = /^[\w-]+(\.[\w-]+)*@gmail.com$/;

  if ( regex.test(ChampC3.value) ){
    ChampC3.classList.remove("is-invalid");
    ChampC3.classList.add("is-valid");
  } else {
    ChampC3.classList.add("is-invalid");
    ChampC3.classList.remove("is-valid");
  }
});

var C6=document.getElementById("tel2");
C6.addEventListener("input",function() {
  var ChampC4=document.getElementById("tel2");
  var regex = /[0-9]/g; // Expression régulière pour correspondre à des chiffres 
  var correspondances = ChampC4.value.match(regex);

  if (ChampC4.value.length==9 && correspondances && correspondances.length == 9 && (ChampC4.value.substring(0,2)=="62" || ChampC4.value.substring(0,2)=="65" || ChampC4.value.substring(0,2)=="67" || ChampC4.value.substring(0,2)=="69") ){
    ChampC4.classList.remove("is-invalid");
    ChampC4.classList.add("is-valid");
  } else {
    ChampC4.classList.add("is-invalid");
    ChampC4.classList.remove("is-valid");
  }
});

var C7=document.getElementById("sexe");
C7.addEventListener("input",function() {
  var ChampC1=document.getElementById("sexe");
  if (ChampC1.value.length==0) {
    ChampC1.classList.remove("is-valid");
    ChampC1.classList.add("is-invalid");
  } else {
    ChampC1.classList.add("is-valid");
    ChampC1.classList.remove("is-invalid");
  }
});

var C8=document.getElementById("specialite");
C8.addEventListener("input",function() {
  var ChampC1=document.getElementById("specialite");
  if (ChampC1.value.length==0) {
    ChampC1.classList.remove("is-valid");
    ChampC1.classList.add("is-invalid");
  } else {
    ChampC1.classList.add("is-valid");
    ChampC1.classList.remove("is-invalid");
  }
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