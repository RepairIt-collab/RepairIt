document.getElementById("editPhoto").addEventListener('click',function(){
    console.log("helle")
    document.getElementById("inputeditPhoto").click()
})

document.getElementById('inputeditPhoto').addEventListener('change', function (event) {
    var img =document.getElementById('inputeditPhoto');
    var file = img.files[0]
    var type =file.name.split('.')[1] 
    console.log(type)
    var id = document.getElementById("userId").textContent
    console.log(id)
    PhotoProfile(file,id)
    
});
function PhotoProfile(image,id){
    var photoProfile = document.getElementById('photoProfile')

    const url = "https://127.0.0.1:9001/photoProfile";
    const form = new FormData();
    form.append('image',image);
    form.append('id',id)
  
    fetch(url, {
      method: 'POST',
      body: form
  })
    .then(response => {
        if (response.ok) {
            console.log("Image envoyer");
            
            
            return response.text(); // Renvoyer la réponse en tant que texte
        } else {
            throw new Error('Erreur de la requête SupprimerTache');
        }
    })
    .then(data => {
        var srce = "/images/profile/"+data 
        console.log(document.getElementById('photoprofil'))
        document.getElementById('photoprofil').src=srce;
        location.reload()
    })
    .catch(error => {
        // Gestion des erreurs
        console.error(error);
    });
  }

  function goBack() {
    history.back();
  }