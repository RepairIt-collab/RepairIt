package com.app.FixIt.CONTROLLER.Boutique;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.app.FixIt.entities.Client;
import com.app.FixIt.entities.Panier;
import com.app.FixIt.entities.Produit;
import com.app.FixIt.repository.ProduitRepository;
import com.app.FixIt.service.Boutique.PanierService;
import com.app.FixIt.service.Boutique.ProduitService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(path = "/Panier")
public class PanierController {
    
    PanierService panierService ;
    public PanierController(PanierService panierService) {
        this.panierService = panierService ;
    }
    @Autowired 
    ProduitService produitService ;

    @Autowired
    ProduitRepository produitRepository ;

    @PostMapping(value="")
    // ajouter un nouveau panier ou ajouter un nouveau produit dans le panier 
    public Panier savePanier(@RequestBody Produit produit,Client client) {
        List<Produit> produits = new ArrayList<>() ;
        produits.add(produit) ;
        Panier panier = panierService.recherchePanier(client) ;
        if(panier != null) {
            panier = panierService.ajoutProduit(produit, client) ;
            return panier ;
        }else{
            Panier pan = panierService.savePanier(produits, client) ;
            return pan ;
        }
    }

    @GetMapping(path = "")
    //afficher le client d'un client 
    public Panier afficherPanier(@RequestBody Client client,Model model) {
        
    Panier panier = panierService.recherchePanier(client) ;
    model.addAttribute("panier", panier) ;
        return panier;
    }

    @DeleteMapping(path = "")
    //supprimer un panier de la bd 
    public ResponseEntity<String> SupprimerPanier(@RequestBody Client client){
        String temp = panierService.supprimerPanier(client ) ;
        return ResponseEntity.ok(temp) ;
    }

    @DeleteMapping(path = "")
    // supprimer un produit dans un panier 
    public Panier supprimerProduit(@RequestBody Produit produit, Client client){

        Panier panier = panierService.recherchePanier(client) ;
        for(Produit prod: panier.getProduits()){
            if (prod == produit){
                ProduitService produitService = new ProduitService(produitRepository) ;
                produitService.DeleteProduit(prod.getNom()) ;
                break ;
            }
        }
        return panier ;
    }
    
    
}
