package com.app.FixIt.BOUTIQUE.DAO;

import java.util.List;

import com.app.FixIt.BOUTIQUE.entities.Panier;
import com.app.FixIt.BOUTIQUE.entities.Produit;

public interface PanierDAO {
    public Panier savePanier(Panier Panier) ;
    public List<Produit> supprimerDuPanier(String nom,List<Produit> produits) ;
}
