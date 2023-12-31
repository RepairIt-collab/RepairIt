package com.app.FixIt.DAO.Boutique;

import java.util.List;

import com.app.FixIt.ENTITIES.Boutique.Produit;



public interface ProduitDAO {
    public Produit saveProduit(Produit produit) ;
    public List<Produit> readProduit() ;
    public Produit findByNom(String nom) ;
    public String DeleteProduit(String nom) ;
}

