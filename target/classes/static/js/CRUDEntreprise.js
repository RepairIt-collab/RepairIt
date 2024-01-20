function ValideEntreprise()
{
   var Entreprise={

     Entreprise:document.getElementById("user3").value,
     password:document.getElementById("password3").value,
     Domaine:document.getElementById("Domaine").value,
     email:document.getElementById("mail3").value,
     telephone:document.getElementById("tel3").value,
   }
   
   var jsonData = JSON.stringify(Entreprise);

   console.log(jsonData);

}