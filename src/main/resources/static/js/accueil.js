document.addEventListener('DOMContentLoaded', function() {
            var menuItems = document.querySelectorAll('.nav-link');
            menuItems.forEach(function(item) {
            item.addEventListener('click', function() {
            menuItems.forEach(function(item) {
            item.classList.remove('active');
            });
             this.classList.add('active');
           });
         });
  });


  function handleClick(event) {
    event.preventDefault(); // Empêche le comportement de redirection par défaut
    document.getElementById('loading').style.display = 'flex';
  
    setTimeout(function() {
      window.location.href = event.target.href; // Redirige vers la page choisie après un délai
    }, 2000); // Temps de chargement simulé (2 secondes)
  }

// Gestion des choix des informations

  document.getElementById("B0").addEventListener("click", function() {
    
    var elements = document.querySelectorAll(".container");
    
    for (var i = 0; i < elements.length; i++) {
      elements[i].style.display = "none";
    }
  
    for (var i = 0; i < elements.length; i++) {
      if(elements[i].id =="contenu")
      elements[i].style.display ="block";
    }
  
    });
  
  document.getElementById("B1").addEventListener("click", function() {
      var elements = document.querySelectorAll(".container");
      
      for (var i = 0; i < elements.length; i++) {
        elements[i].style.display = "none";
      }
    
      for (var i = 0; i < elements.length; i++) {
        if(elements[i].id =="contenu1")
        elements[i].style.display ="block";
      }
    
      });

  document.getElementById("B2").addEventListener("click", function() {
        var elements = document.querySelectorAll(".container");
        
        for (var i = 0; i < elements.length; i++) {
          elements[i].style.display = "none";
        }
      
        for (var i = 0; i < elements.length; i++) {
          if(elements[i].id =="contenu2")
          elements[i].style.display ="block";
        }
      
        });
  
  document.getElementById("B3").addEventListener("click", function() {
          var elements = document.querySelectorAll(".container");
          
          for (var i = 0; i < elements.length; i++) {
            elements[i].style.display = "none";
          }
        
          for (var i = 0; i < elements.length; i++) {
            if(elements[i].id =="contenu3")
            elements[i].style.display ="block";
          }
        
          });
      
  document.getElementById("B4").addEventListener("click", function() {
    var elements = document.querySelectorAll(".container");
    
    for (var i = 0; i < elements.length; i++) {
      elements[i].style.display = "none";
    }
  
    for (var i = 0; i < elements.length; i++) {
      if(elements[i].id =="contenu4")
      elements[i].style.display ="block";
    }
  
    });

  function openPopup() {
    $('#myModal').modal('show');
  }

  function client() {
    $('#client').modal('show');
  }

  function openPopup1() {
    var popup =document.getElementById("myModal");
    var popup1=document.querySelector("#myModal").querySelector(".justmodal")
    var p=document.createElement("p")
    var mot = "veuillez remplir les champs correctement"
    p.textContent=mot
    p.style.color="red"
    popup1.appendChild(p);

    popup.style.display = "block"
    popup.classList.add("show")
    document.getElementById("close1").addEventListener('click',()=>{
      var popup=document.getElementById("myModal");
      if(popup.classList.contains("show"))
      {
        popup.classList.remove("show");
        window.location.href = '/RepairIt/Accueil';
      }
    });
    console.log(popup)
  }

  function openPopup2() {
    var popup =document.getElementById("myModal2");
    var popup1=document.querySelector("#myModal2").querySelector(".justmodal2")
    var p=document.createElement("p")
    var mot = "veuillez remplir les champs correctement"
    p.textContent=mot
    p.style.color="red"
    popup1.appendChild(p);

    popup.style.display = "block"
    popup.classList.add("show")
    
    document.getElementById("close2").addEventListener('click',()=>{
      var popup=document.getElementById("myModal2");
      if(popup.classList.contains("show"))
      {
        popup.classList.remove("show");
        window.location.href = '/RepairIt/Accueil';
      }
    });
    console.log(popup)
  }


//ENVOI D'EMAIL EN CAS DE D'OUBLIE DE MOT DE PASSE



function sendmail()
{
  const emailData = {
    to: document.getElementById("sendmail").value,
    subject: 'Sujet de l\'e-mail',
    text: 'Contenu du message'
  };

  fetch('https://127.0.0.1:9001/sendemail', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(emailData)
   
  })
    .then(response => {
      if (response.ok) {
        console.log(response);
        alert('E-mail envoyé avec succès !');
      } else {
       alert('Une erreur s\'est produite lors de l\'envoi de l\'e-mail.');
      }
    })
    .catch(error => {
      alert('Une erreur s\'est produite :', error);
    });
}

function MOpen() {
  document.getElementById("modalcon1").style.display="hidden";
  document.getElementById("modalcon2").style.display="block";
}

 
function isModalOpen() {
  document.getElementById("modalcon2").style.display="hidden";
  document.getElementById("modalcon1").style.display="block";
} 

document.getElementById("close1").addEventListener('click',isModalOpen);


