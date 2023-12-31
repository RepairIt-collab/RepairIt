//VALIDATION DU FORMULAIRE CLIENT
var clients;
var mains;


$(document).ready(function() {
  $.ajax({
      url: "/RepairIt/SignUp/loadClient",
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
    
    // Sélectionnez le bouton "Submit" ou "Valider"
    var submit = document.getElementById('valid1');
    var caseClient = document.getElementById("check1");
    
    // Sélectionnez tous les éléments avec la classe "form-control"
    var formControlElements = formulaire.getElementsByClassName('form-control');
    submit.disabled = true;
    // Parcourez tous les éléments sélectionnés
    for (var i = 0; i < formControlElements.length; i++) {
      var j=0;
      // Ajoutez un gestionnaire d'événements pour l'événement "input"
      formControlElements[i].addEventListener('change', function() {
        // Vérifiez si la classe "is-valid" est présente sur l'élément actuel
        var isValid = this.classList.contains('is-valid');
     
        
        // Faites quelque chose en fonction de la validité
        if (isValid) {
          console.log('La classe "is-valid" est présente.');
          j=j+1;
        } 
        else {

          console.log('La classe "is-valid" est absente.');
          if(j>=5)
          {
            j=j-1;
          }
        }
        console.log(j);
        caseClient.addEventListener("change", function() {
          if (caseClient.checked && j==5) {
            submit.disabled = false;
          } 
          if (!caseClient.checked) {
            submit.disabled = true;
          } 
        });
      });
     
    }
  
   

},
error: function(xhr, status, error) {
    console.error(error);
}
});
});






//VALIDATION DU FORMULAIRE MAINTENANCIER
$(document).ready(function() {
  $.ajax({
      url: "/RepairIt/SignUp/loadMaintenancier",
      type: "GET",
      success: function(data) {
          mains = data; // Assigner les données à la constante 
          console.log(mains);

      var forms=document.getElementById("form2")
      var invFeedback = forms.getElementsByClassName("invalid-feedback");
       console.log(invFeedback)
      
        var C1=document.getElementById("nom");
        C1.addEventListener("input",function() {
          var ChampC1=document.getElementById("nom");
          var regex = /[!@#$%^&*();><./|=]/g; // Expression régulière pour un caractère spécial
          var correspondances = ChampC1.value.match(regex);
          mains.forEach(main =>{
          if (ChampC1.value.length==0 || correspondances) {
            ChampC1.classList.remove("is-valid");
            ChampC1.classList.add("is-invalid");
            invFeedback[1].style.display="block";
          } else {
            if(ChampC1.value==main.nom_complet)
            {
              console.log("manu");
              invFeedback[1].style.display="none"; 
              ChampC1.classList.add("is-invalid");
              ChampC1.classList.remove("is-valid");
              invFeedback[0].style.display="block";
            }
            if(ChampC1.value!=main.nom_complet){
              console.log("mapas");
              invFeedback[1].style.display="none"; 
              ChampC1.classList.add("is-valid");
              ChampC1.classList.remove("is-invalid");
              invFeedback[0].style.display="none";
            }
  
          }
        });

      });
    
      var C2=document.getElementById("user2");
      C2.addEventListener("input",function() {
          var ChampC1=document.getElementById("user2");
          mains.forEach(main =>{
            if (ChampC1.value.length==0 ) {
              if(ChampC1.value==main.username)
              {
                invFeedback[3].style.display="block"; 
                ChampC1.classList.remove("is-valid");
                ChampC1.classList.add("is-invalid");
                invFeedback[2].style.display="block";
              }
              else{
                invFeedback[3].style.display="block"; 
              ChampC1.classList.remove("is-valid");
              ChampC1.classList.add("is-invalid");
              invFeedback[2].style.display="none";
              }
              
            } else 
            {
              if(ChampC1.value==main.username)
              {
                invFeedback[3].style.display="none"; 
                ChampC1.classList.remove("is-valid");
                ChampC1.classList.add("is-invalid");
                invFeedback[2].style.display="block";
              }
              else{
                invFeedback[3].style.display="none"; 
                ChampC1.classList.add("is-valid");
                ChampC1.classList.remove("is-invalid");
                invFeedback[2].style.display="none";
              }
            }
          })
       
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

      var C5=document.getElementById("mail2");
      C5.addEventListener("input",function() {
        var ChampC3=document.getElementById("mail2");
        var regex = /^[\w-]+(\.[\w-]+)*@gmail.com$/;
        
        mains.forEach(main =>{
          if ( regex.test(ChampC3.value) ){
            if(ChampC3.value==main.email)
            {
               invFeedback[5].style.display="none"; 
                ChampC3.classList.remove("is-valid");
                ChampC3.classList.add("is-invalid");
                invFeedback[4].style.display="block";
            }
            else{
              invFeedback[5].style.display="none"; 
                ChampC3.classList.remove("is-invalid");
                ChampC3.classList.add("is-valid");
                invFeedback[4].style.display="none";
            }
          } else {
                  if(ChampC3.value==main.email)
            {
               invFeedback[5].style.display="none"; 
                ChampC3.classList.remove("is-valid");
                ChampC3.classList.add("is-invalid");
                invFeedback[4].style.display="block";
            }
            else{
              invFeedback[5].style.display="block"; 
              ChampC3.classList.remove("is-valid");
              ChampC3.classList.add("is-invalid");
                invFeedback[4].style.display="none";
            }
          }
        })
       
      });

      var C6=document.getElementById("tel2");
      C6.addEventListener("input",function() {
        var ChampC4=document.getElementById("tel2");
        var regex = /[0-9]/g; // Expression régulière pour correspondre à des chiffres 
        var correspondances = ChampC4.value.match(regex);
        
        mains.forEach(main =>{
          if (ChampC4.value.length==9 && correspondances && correspondances.length == 9 && (ChampC4.value.substring(0,2)=="62" || ChampC4.value.substring(0,2)=="65" || ChampC4.value.substring(0,2)=="67" || ChampC4.value.substring(0,2)=="69") ){
            if(ChampC4.value==main.telephone)
            {
              invFeedback[11].style.display="none"; 
                ChampC4.classList.remove("is-valid");
                ChampC4.classList.add("is-invalid");
                invFeedback[10].style.display="block";
            }
            else{
              invFeedback[11].style.display="none"; 
              ChampC4.classList.remove("is-invalid");
              ChampC4.classList.add("is-valid");
              invFeedback[10].style.display="none";
            }
          } else {
            if(ChampC4.value==main.telephone)
            {
              invFeedback[10].style.display="block"; 
                ChampC4.classList.remove("is-valid");
                ChampC4.classList.add("is-invalid");
                invFeedback[11].style.display="block";
            }
            else{
              invFeedback[10].style.display="none"; 
                ChampC4.classList.remove("is-valid");
                ChampC4.classList.add("is-invalid");
                invFeedback[11].style.display="block";
            }
          }
        })
     
      });


       // Sélectionnez tous les éléments avec la classe "form-control"
    var formulaire = document.getElementById("form2");
    
    // Sélectionnez le bouton "Submit" ou "Valider"
    var submit = document.getElementById('valid2');
    var caseClient = document.getElementById("check2");
    
    // Sélectionnez tous les éléments avec la classe "form-control"
    var formControlElements = formulaire.getElementsByClassName('form-control');
    submit.disabled = true;
    // Parcourez tous les éléments sélectionnés
    for (var i = 0; i < formControlElements.length; i++) {
      var j=0;
      // Ajoutez un gestionnaire d'événements pour l'événement "input"
      formControlElements[i].addEventListener('change', function() {
        // Vérifiez si la classe "is-valid" est présente sur l'élément actuel
        var isValid = this.classList.contains('is-valid');
     
        
        // Faites quelque chose en fonction de la validité
        if (isValid) {
          console.log('La classe "is-valid" est présente.');
          j=j+1;
        } 
        else {

          console.log('La classe "is-valid" est absente.');
          if(j>=8)
          {
            j=j-1;
          }
        }
        console.log(j);
        caseClient.addEventListener("change", function() {
          if (caseClient.checked && j==8) {
            submit.disabled = false;
          } 
          if (!caseClient.checked) {
            submit.disabled = true;
          } 
        });
      });
     
    }
      },
      error: function(xhr, status, error) {
        console.error(error);
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