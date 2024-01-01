package com.app.FixIt.BOUTIQUE.DAO;

import java.util.List;

import com.app.FixIt.BOUTIQUE.entities.Commande;
import com.app.FixIt.BOUTIQUE.entities.Produit;

public interface CommandeDAO{
    public Commande saveCommande(Commande commande) ;
    public List<Produit> supprimerDeLaCommande(String nom,List<Produit> produits) ;
}
