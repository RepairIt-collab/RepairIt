package com.app.FixIt.BOUTIQUE.DAO;

import com.app.FixIt.BOUTIQUE.entities.Produit;

public interface ProduitDAO {
    public Produit saveProduit(Produit produit) ;
    public Produit findByName(String nom) ;
    public String DeleteProduit(String nom) ;
}

