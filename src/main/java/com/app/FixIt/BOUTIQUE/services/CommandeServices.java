package com.app.FixIt.BOUTIQUE.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.FixIt.BOUTIQUE.DAO.CommandeDAO;
import com.app.FixIt.BOUTIQUE.entities.Commande;
import com.app.FixIt.BOUTIQUE.entities.Produit;
import com.app.FixIt.BOUTIQUE.repository.CommandeRepository;
import com.app.FixIt.BOUTIQUE.repository.ProduitRepository;


@Service
public class CommandeServices implements CommandeDAO {

    private final ProduitRepository produitRepository;
    private final CommandeRepository commandeRepository;

    public CommandeServices(ProduitRepository produitRepository,CommandeRepository commandeRepository) {
        this.produitRepository = produitRepository;
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public List<Produit> supprimerDeLaCommande(String nom, List<Produit> produits) {
        Produit produit = produitRepository.findByNom(nom);
        // Faites ce que vous souhaitez avec le produit récupéré
        return produits;
    }


    public Commande findByClientId(Long clientId) {
        return commandeRepository.findByUserId(clientId);
    }
}
