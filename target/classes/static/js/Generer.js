function creation(){
    var jsonData = [
      //mecanique json
      { "photo":'/Imgshop/voiture/phare.jpeg', "nom": "phare", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "mecanique"},
      { "photo":'/Imgshop/voiture/roue.jpeg', "nom": "roue", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "mecanique"},
      { "photo":'/Imgshop/voiture/siege.jpeg', "nom": "siege", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "mecanique"},
      { "photo":'/Imgshop/voiture/radiateur.jpeg', "nom": "radiateur", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "mecanique"},
      { "photo":'/Imgshop/voiture/parebrise.jpeg', "nom": "parebrise", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "mecanique"},
      { "photo":'/Imgshop/voiture/moteur.png', "nom": "moteur", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "mecanique"},
      { "photo":'/Imgshop/voiture/essuieglasse.jpeg', "nom": "essuie_glasse", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "mecanique"},
      { "photo":'/Imgshop/voiture/disquedefrein.jpeg', "nom": "disque_de_frein", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "mecanique"},
      { "photo":'/Imgshop/voiture/capot.jpeg', "nom": "capot", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "mecanique"},
      { "photo":'/Imgshop/voiture/batterie.jpeg', "nom": "batterie", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "mecanique"},

      //ordinateur
      { "photo":'/Imgshop/ordinateur/cartemere.jpeg', "nom": "carte_mere", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "ordinateur"},
      { "photo":'/Imgshop/ordinateur/chargeur.jpeg', "nom": "chargeur", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "ordinateur"},
      { "photo":'/Imgshop/ordinateur/charniere.jpeg', "nom": "charniere", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "ordinateur"},
      { "photo":'/Imgshop/ordinateur/clavier.png', "nom": "clavier", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "ordinateur"},
      { "photo":'/Imgshop/ordinateur/disquedur.jpeg', "nom": "disque_dur", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "ordinateur"},
      { "photo":'/Imgshop/ordinateur/hautparleur.jpeg', "nom": "haut_parleur", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "ordinateur"},
      { "photo":'/Imgshop/ordinateur/lecteurcd.jpeg', "nom": "lecteur_cd", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "ordinateur"},
      { "photo":'/Imgshop/ordinateur/processeur.jpeg', "nom": "processeur", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "ordinateur"},
      { "photo":'/Imgshop/ordinateur/ram.jpeg', "nom": "ram", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "ordinateur"},
      { "photo":'/Imgshop/ordinateur/souris.jpeg', "nom": "souris", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "ordinateur"},
      
      //telephone
      { "photo":'/Imgshop/telephone/afficheur.jpeg', "nom": "afficheur", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "telephone"},
      { "photo":'/Imgshop/telephone/batterie.jpeg', "nom": "batterie", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "telephone"},
      { "photo":'/Imgshop/telephone/cartemere.jpeg', "nom": "carte_mere", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "telephone"},
      { "photo":'/Imgshop/telephone/circuitdecharge.jpeg', "nom": "circuit_de_charge", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "telephone"},
      { "photo":'/Imgshop/telephone/glace.jpeg', "nom": "glace", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "telephone"},
      { "photo":'/Imgshop/telephone/pochette.jpeg', "nom": "pochette", "caracteristique": "nouvelle edition", "prix": parseInt(35000), "quantite": parseInt(50) ,"domaine" : "telephone"},

    ]
     

    var xhr = new XMLHttpRequest();
    xhr.overrideMimeType("application/json");
    xhr.open("POST", "https://127.0.0.1:9001/RepairIt/Boutique/CreerProduit", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var response = xhr.responseText;
        // Traiter la réponse du contrôleur
        console.log(response);
      }
    };
    xhr.send(JSON.stringify(jsonData));
  }