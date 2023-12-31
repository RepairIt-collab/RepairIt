package com.app.FixIt.DAO.Boutique;

import java.util.List;

import com.app.FixIt.ENTITIES.Boutique.Panier;
import com.app.FixIt.ENTITIES.Boutique.Produit;
import com.app.FixIt.ENTITIES.Maintenance.Client;



public interface PanierDAO {
    public Panier savePanier(List<Produit> produits,Client client ) ;
    public Panier supprimerDunProduit(String nom,Client client) ;
    public String supprimerPanier(Client client) ;
    public Panier recherchePanier(Client client) ;
    public List<Panier> lirePanier() ;
    public Panier ajoutProduit(Produit produit,Client client) ;
}
