package com.app.FixIt.CONTROLLER.Boutique;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.FixIt.entities.Client;
import com.app.FixIt.entities.Commande;
import com.app.FixIt.entities.Panier;
import com.app.FixIt.service.Boutique.COmmandeService;

@Controller
@RequestMapping(path = "")
public class CommandeController {
    
    COmmandeService cOmmandeService ;
    
    public CommandeController(COmmandeService cOmmandeService) {
        this.cOmmandeService = cOmmandeService;
    }

    @PostMapping
public Commande ajouteretlirecommande(@RequestBody Panier panier){
      return cOmmandeService.ajoutCommande(panier) ;
}
    @GetMapping
    public ResponseEntity<String> lireCommande(@RequestBody Client client,Model model){
        Commande commande = cOmmandeService.lirCommande(client) ;
        model.addAttribute("Commande", commande) ;
        return ResponseEntity.ok("ok") ;
    }
}
