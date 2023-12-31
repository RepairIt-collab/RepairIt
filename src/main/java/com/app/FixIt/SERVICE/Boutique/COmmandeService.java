package com.app.FixIt.SERVICE.Boutique;

import org.springframework.stereotype.Service;

import com.app.FixIt.DAO.Boutique.CommandeDAO;
import com.app.FixIt.ENTITIES.Boutique.Commande;
import com.app.FixIt.ENTITIES.Boutique.Panier;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.REPOSITORY.Boutique.CommandeRepository;



@Service
public class COmmandeService implements CommandeDAO{
    
    CommandeRepository commandeRepository ;
    
    public COmmandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Commande ajoutCommande(Panier panier){
       Commande commande = new Commande() ;
       commande.setPanier(panier);
       commande = commandeRepository.save(commande) ;
       return commande ;
    }

    @Override
    public Commande lirCommande(Client client) {
        Commande commande = commandeRepository.findByclient(client) ;
        return commande ;
    }

    

}
