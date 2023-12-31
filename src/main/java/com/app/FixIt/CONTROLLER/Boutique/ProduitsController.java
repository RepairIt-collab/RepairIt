package com.app.FixIt.CONTROLLER.Boutique;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.FixIt.DTO.Boutique.ProduitDTO;
import com.app.FixIt.ENTITIES.Boutique.Domaine;
import com.app.FixIt.ENTITIES.Boutique.Produit;
import com.app.FixIt.REPOSITORY.Boutique.PanierRepository;
import com.app.FixIt.REPOSITORY.Boutique.ProduitRepository;
import com.app.FixIt.SERVICE.Boutique.DomaineService;
import com.app.FixIt.SERVICE.Boutique.ProduitService;




@Controller
@RequestMapping(value = "/Boutique")

public class ProduitsController {

    @Autowired
    private  ProduitRepository  ProduitRepository ;
    @Autowired 
    private ProduitService produitServices ;
    @Autowired
    DomaineService domaineServices ;
    // @GetMapping(value = "/CreerProduit")
    // public String CreerProduit(){
    //     return "HTML/ajout.html" ;
    // }
    
    @PostMapping(value = "/CreerProduit")
    public ResponseEntity<String> CreerProduit(@RequestBody ProduitDTO[] produitDTOs){
        
        ProduitService produitServices = new ProduitService(ProduitRepository) ;
        for(ProduitDTO produitDTO : produitDTOs){
            Produit produit = new Produit() ;
            produit.setPhoto(produitDTO.getPhoto());
            produit.setNom(produitDTO.getNom());
            produit.setdescription(produitDTO.getdescription());
            produit.setPrix(produitDTO.getPrix());
            produit.setQuantite(produitDTO.getQuantite());
            Domaine domaine = domaineServices.rechercherDomaine(produitDTO.getDomaine());
            produit.setDomaine(domaine);
            produit = produitServices.saveProduit(produit) ;
        }

        return ResponseEntity.ok("l'enregistrement en bd marche") ;
    }


    @GetMapping(value="/Boutique")
    public String lireProduit(Model model){
        List<Produit> produits = produitServices.readProduit() ;
        model.addAttribute("p1", produits) ;
        System.out.println(produits);

        return  "HTML/Shop";
    }
    
    
}
