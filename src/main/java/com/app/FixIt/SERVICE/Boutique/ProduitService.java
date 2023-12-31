package com.app.FixIt.SERVICE.Boutique;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.FixIt.DAO.Boutique.ProduitDAO;
import com.app.FixIt.ENTITIES.Boutique.Produit;
import com.app.FixIt.REPOSITORY.Boutique.ProduitRepository;


@Service
public class ProduitService implements ProduitDAO{

    ProduitRepository produitRepository;
    public ProduitService(ProduitRepository produitRepository){
        this.produitRepository = produitRepository ;
    }

    @Override
    //savegarder un produit
   public Produit saveProduit(Produit produit){
    produit = produitRepository.save(produit) ;
    return produit ;
   } 
   @Override
   //rechercher un produit en fonction de son nom 
   public Produit findByNom(String nom){
    Produit produit = produitRepository.findByNom(nom) ;
    return produit ;
   }
    @Override
    //supprimer un produit en fonction de son nom
    public String DeleteProduit(String nom) {
        Produit produit = produitRepository.findByNom(nom) ;
        produitRepository.delete(produit); ;
        return "Suppression realiser";
    }

    @Override
    //liste de tous les produits
    public List<Produit> readProduit() {
        List<Produit> produits = produitRepository.findAll() ;
        return produits ;
    }

}
