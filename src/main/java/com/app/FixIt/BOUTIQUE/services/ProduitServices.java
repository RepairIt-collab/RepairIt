package com.app.FixIt.BOUTIQUE.services;

import org.springframework.stereotype.Service;

import com.app.FixIt.BOUTIQUE.DAO.ProduitDAO;
import com.app.FixIt.BOUTIQUE.entities.Produit;
import com.app.FixIt.BOUTIQUE.repository.ProduitRepository;


@Service
public class ProduitServices implements ProduitDAO{

    ProduitRepository ProduitRepository ;
    public ProduitServices(ProduitRepository ProduitRepository){
        this.ProduitRepository = ProduitRepository ;
    }

    @Override
   public Produit saveProduit(Produit produit){
    produit = ProduitRepository.save(produit) ;
    return produit ;
   } 
   @Override
   public Produit findByName(String nom){
    Produit produit = ProduitRepository.findByNom(nom) ;
    return produit ;
   }

@Override
public String DeleteProduit(String nom) {
    Produit produit = ProduitRepository.findByNom(nom) ;
    ProduitRepository.delete(produit); ;
    return "Suppression realiser";
}
}
