package com.app.FixIt.BOUTIQUE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.FixIt.BOUTIQUE.DAO.PanierDAO;
import com.app.FixIt.BOUTIQUE.entities.Panier;
import com.app.FixIt.BOUTIQUE.entities.Produit;
import com.app.FixIt.BOUTIQUE.repository.PanierRepository;
import com.app.FixIt.BOUTIQUE.repository.ProduitRepository;


@Service
public class PanierServices implements PanierDAO {

    private final ProduitRepository produitRepository;
    private final PanierRepository panierRepository;

    public PanierServices(ProduitRepository produitRepository, PanierRepository panierRepository) {
        this.produitRepository = produitRepository;
        this.panierRepository = panierRepository;
    }

    @Override
    public Panier savePanier(Panier panier) {
        return panierRepository.save(panier);
    }

    @Override
    public List<Produit> supprimerDuPanier(String nom, List<Produit> produits) {
        Produit produit = produitRepository.findByNom(nom);
        // Faites ce que vous souhaitez avec le produit récupéré
        return produits;
    }


    public Panier findByClientId(Long clientId) {
        return panierRepository.findByUserId(clientId);
    }
}
