package com.app.FixIt.BOUTIQUE.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.FixIt.BOUTIQUE.DTO.ProduitDTO;
import com.app.FixIt.BOUTIQUE.entities.Commande;
import com.app.FixIt.BOUTIQUE.entities.Domaine;
import com.app.FixIt.BOUTIQUE.entities.Panier;
import com.app.FixIt.BOUTIQUE.entities.Produit;
import com.app.FixIt.BOUTIQUE.repository.CommandeRepository;
import com.app.FixIt.BOUTIQUE.repository.PanierRepository;
import com.app.FixIt.BOUTIQUE.repository.ProduitRepository;
import com.app.FixIt.BOUTIQUE.services.DomaineServices;
import com.app.FixIt.BOUTIQUE.services.ProduitServices;


import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;


@Controller
@RequestMapping("/RepairIt")
public class Boutique {

     @Autowired
    private ProduitRepository produitRepository ;
    @Autowired 
    private PanierRepository panierRepository;
     @Autowired 
    private CommandeRepository commandeRepository;
    @Autowired
    DomaineServices domaineServices ;


    @GetMapping("/Boutique")
    public String getPage(Model model,HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        if(id!=null)
        {
            System.out.println(id);
            List<Produit> p1 = produitRepository.findAll();
            model.addAttribute("p1", p1);
            Panier panier = panierRepository.findByUserId(id);
            Commande commande = commandeRepository.findByUserId(id);
            if(panier!=null)
            {
                List<Produit> existingProduits = panier.getProduit();
                 if(existingProduits!=null)
               {
                model.addAttribute("panier", existingProduits);
               } 
            }   
            if(commande!=null)
            {
                List<Produit> existingProducts = commande.getProduit();
                 if(existingProducts!=null)
               {
                model.addAttribute("commande", existingProducts);
               } 
            }    
            return "HTML/Shop";
        }
        else{
            return "HTML/Client";
        }

    }


        









    // ##### PRODUIT ######
          //Ajout d'un produit
    @PostMapping(value = "/Boutique/CreerProduit")
    public ResponseEntity<String> CreerProduit(@RequestBody ProduitDTO[] produitDTOs){
        
        ProduitServices produitServices = new ProduitServices(produitRepository) ;
        for(ProduitDTO produitDTO : produitDTOs){
            Produit produit = new Produit() ;
            produit.setPhoto(produitDTO.getPhoto());
            produit.setNom(produitDTO.getNom());
            produit.setCaracteristique(produitDTO.getCaracteristique());
            produit.setPrix(produitDTO.getPrix());
            produit.setOccupe(false);
            produit.setEstcommande(false);
            produit.setQuantite(produitDTO.getQuantite());
            Domaine domaine = domaineServices.findByName(produitDTO.getDomaine());
            produit.setDomaine(domaine);
            produit = produitServices.saveProduit(produit) ;
        }

        return ResponseEntity.ok("l'enregistrement en bd marche") ;
    }  


    // ##### PANIER ######
           //Ajout d'un produit dans le panier
    @PostMapping("/Boutique/AjouterPanier/{numero}/{quantite}")
    public ResponseEntity<String> ajouterProduitPanier(@PathVariable Long numero, @PathVariable Integer quantite, HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        Boolean compteur=false;
        Panier panier = panierRepository.findByUserId(id);

        Produit produit = produitRepository.findById(numero).orElse(null);
        if (produit == null) {
            return ResponseEntity.ok("Produit non trouvé");
        }
    
        if (produit.getQuantite() < quantite) {
            Commande commande = commandeRepository.findById(id).orElse(null);
            List<Produit> existingProduits =commande.getProduit();
        
                for(Produit P : existingProduits)
                    {
                        if(P.getPhoto().equals(produit.getPhoto()))
                        {
                            int surplus = quantite - produit.getQuantite();
                            P.setQuantite(P.getQuantite() + surplus);
                            produit.setQuantite(0);
                            existingProduits.remove(P);
                            existingProduits.add(P);
                            Produit Comproduit=produitRepository.findByPhotoAndEstcommande(produit.getPhoto(), true);
                            Comproduit.setQuantite(P.getQuantite() + surplus);
                             produitRepository.save(Comproduit);
                            produitRepository.save(produit);
                            commande.setProduit(existingProduits);
                            commandeRepository.save(commande);
                            
                            compteur = true;
                            break;
                        }
                    }
                    if(compteur==false)
                    {
                       Produit existingProduit = new Produit();

                       
                        existingProduit.setPhoto(produit.getPhoto());
                        existingProduit.setCaracteristique(produit.getCaracteristique());
                        existingProduit.setDomaine(produit.getDomaine());
                        existingProduit.setQuantite(quantite-produit.getQuantite());
                        existingProduit.setOccupe(true);
                        existingProduit.setEstcommande(true);
                        existingProduit.setNom(produit.getNom());
                        existingProduit.setPrix(produit.getPrix());

                        produitRepository.save(existingProduit);

                        existingProduits.add(existingProduit);
                        commande.setProduit(existingProduits);
                        commandeRepository.save(commande);

                        Produit Propanier=produitRepository.findByPhotoAndOccupeAndEstcommande(produit.getPhoto(), true,false );
                        Propanier.setQuantite(Propanier.getQuantite()+quantite-produit.getQuantite());
                        produitRepository.save(Propanier);

                        List<Produit> existingProducts =panier.getProduit();

                        for(Produit pan : existingProducts)
                        {
                            if(pan.getPhoto().equals(produit.getPhoto()))
                            {
                                pan.setQuantite(pan.getQuantite()+produit.getQuantite()-quantite+produit.getQuantite());
                                existingProducts.remove(pan);
                                existingProducts.add(pan);
                            }
                        }
                        produit.setQuantite(0);
                        produitRepository.save(produit);
                    }
                   
            return ResponseEntity.ok("Quantité insuffisante ajout dans les commande");
        }
        else {
             produit.setQuantite(produit.getQuantite() - quantite);
             produit.setOccupe(false);
             produit.setEstcommande(false);
             produitRepository.save(produit);
    
           
            if (panier != null) {
                List<Produit> existingProduits = panier.getProduit();
                if(produit!=null)
                {
                      for(Produit P : existingProduits)
                    {
                        if(P.getPhoto().equals(produit.getPhoto()))
                        {
                            P.setQuantite(P.getQuantite()+quantite);
                            existingProduits.remove(P);
                            existingProduits.add(P);
                            Produit Propanier=produitRepository.findByPhotoAndOccupeAndEstcommande(produit.getPhoto(), true,false );
                            Propanier.setQuantite(Propanier.getQuantite()+quantite);
                            produitRepository.save(P);
                            panier.setProduit(existingProduits);
                            panierRepository.save(panier);

                            compteur=true;
                            break;
                        }
                    }
                    
                   if(compteur==false)
                    {
                        Produit NoexistingProduit = new Produit();
                        NoexistingProduit.setPhoto(produit.getPhoto());
                        NoexistingProduit.setCaracteristique(produit.getCaracteristique());
                        NoexistingProduit.setDomaine(produit.getDomaine());
                        NoexistingProduit.setQuantite(quantite);
                        NoexistingProduit.setOccupe(true);
                        NoexistingProduit.setEstcommande(false);
                        NoexistingProduit.setNom(produit.getNom());
                        NoexistingProduit.setPrix(produit.getPrix());   

                        produitRepository.save(NoexistingProduit);
                        existingProduits.add(NoexistingProduit);
                        panier.setProduit(existingProduits);
                        panierRepository.save(panier);
                    }
                }
               
            } else {
                return ResponseEntity.ok("Erreur : Panier non trouvé");
            }
        }
          
        return ResponseEntity.ok("Panier ajouté");
    }
    
          //retrait du produit dans le panier
    @PostMapping("/Boutique/RetirerPanier/{numero}")
    public ResponseEntity<String> RetirerProduitPanier(@PathVariable Long numero, HttpSession session)
    {
        Long id = (Long) session.getAttribute("id");
        List<Produit> produit=new ArrayList<>();
        Panier panier = panierRepository.findByUserId(id);
        produit=produitRepository.findAll();
        Produit delproduct = produitRepository.findById(numero).orElse(null);
        System.out.println(numero);
        System.out.println(delproduct);
      
        for(Produit p : produit)
        {
            System.out.println(p.getNom());
            if(p.getPhoto().equals(delproduct.getPhoto()) && !p.getOccupe().equals(delproduct.getOccupe()) )
            {
                p.setQuantite(p.getQuantite()+delproduct.getQuantite());
                produitRepository.save(p);
                List<Produit> existingProduits = panier.getProduit();
                 System.out.println("OK ++");
                existingProduits.remove(delproduct);
                panier.setProduit(existingProduits);

                panierRepository.save(panier);
                produitRepository.delete(delproduct);

                return ResponseEntity.ok("Objet retire");
            }
        }
        
          return ResponseEntity.ok("Erreurs");
    }
  
    
    // ##### COMMANDER ######
        //Ajouter d'un produit les commandes
    @PostMapping("/Boutique/AjouterCommande/{numero}/{quantite}")
    public ResponseEntity<String> ajouterProduitCommande(@PathVariable Long numero, @PathVariable Integer quantite, HttpSession session) {
          Long id = (Long) session.getAttribute("id");
          Boolean compteur=false;
    
        Produit produit = produitRepository.findById(numero).orElse(null);
        if (produit == null) {
            return ResponseEntity.ok("Produit non trouvé");
        }

         Commande commande = commandeRepository.findByUserId(id);

           if (commande != null) {
                List<Produit> existingProduits = commande.getProduit();
                if(produit!=null)
                {
                        for(Produit P : existingProduits)
                    {
                        if(P.getPhoto().equals(produit.getPhoto()))
                        {
                            P.setQuantite(P.getQuantite()+quantite);
                            existingProduits.remove(P);
                            existingProduits.add(P);
                            produitRepository.save(P);
                            commande.setProduit(existingProduits);
                            commandeRepository.save(commande);

                            compteur=true;
                            break;
                        }
                    }
                    if(compteur==false)
                    {
                       Produit existingProduit = new Produit();
                        existingProduit.setPhoto(produit.getPhoto());
                        existingProduit.setCaracteristique(produit.getCaracteristique());
                        existingProduit.setDomaine(produit.getDomaine());
                        existingProduit.setQuantite(quantite);
                        existingProduit.setOccupe(true);
                        existingProduit.setEstcommande(true);
                        existingProduit.setNom(produit.getNom());
                        existingProduit.setPrix(produit.getPrix());
                        
                        produitRepository.save(existingProduit);
                        existingProduits.add(existingProduit);
                        commande.setProduit(existingProduits);
                        commandeRepository.save(commande);
                    }
                }       
          }
        else {
                return ResponseEntity.ok("Erreur : Boite de commande non trouve");
        }  


     return ResponseEntity.ok("produit ajouté au commande");
}

       //retrait du produit dan les commandes
     @PostMapping("/Boutique/RetirerCommande/{numero}")   
     public ResponseEntity<String> RetirerProduitCommande(@PathVariable Long numero, HttpSession session)
    {
        Long id = (Long) session.getAttribute("id");
        Commande commande = commandeRepository.findByUserId(id);
        Produit delproduct = produitRepository.findById(numero).orElse(null);
        System.out.println(numero);
        System.out.println(delproduct);
       
        List<Produit> existingProduits = commande.getProduit();
        System.out.println("OK ++");
        existingProduits.remove(delproduct);
        commande.setProduit(existingProduits);

        commandeRepository.save(commande);
        produitRepository.delete(delproduct);
     
        return ResponseEntity.ok("Retrait effectue");
    }


}