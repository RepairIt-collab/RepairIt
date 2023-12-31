package com.app.FixIt.DAO.Boutique;

import com.app.FixIt.ENTITIES.Boutique.Commande;
import com.app.FixIt.ENTITIES.Boutique.Panier;
import com.app.FixIt.ENTITIES.Maintenance.Client;

public interface CommandeDAO {
    public Commande ajoutCommande(Panier panier) ;
    public Commande lirCommande(Client client) ;
}
