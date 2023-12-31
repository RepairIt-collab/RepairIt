package com.app.FixIt.SERVICE.Boutique;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.FixIt.DAO.PanierDAO;
import com.app.FixIt.entities.Client;
import com.app.FixIt.entities.Panier;
import com.app.FixIt.entities.Produit;
import com.app.FixIt.repository.PanierRepository;
import com.app.FixIt.repository.ProduitRepository;


@Service
public class PanierService implements PanierDAO{
        
    public PanierRepository panierRepository ;
    public PanierService(PanierRepository panierRepository){
        this.panierRepository = panierRepository ;
    }
    @Autowired
    public ProduitRepository produitRepository ;

    @Override
    //savegarder un panier en fonction
    public Panier savePanier(List<Produit> produits,Client client ) {
        Panier panier = new Panier() ;
        panier.setClient(client);
        panier.setProduits(produits);
        int sum = 0 ;
        for(Produit pro :produits){
            sum = sum + pro.getPrix() ;
        }
        panier.setPrix_total(sum);
        panier = panierRepository.save(panier) ;
        return panier ;
    }

    @Override
    //supprimer d un produit d un panier
    public Panier supprimerDunProduit(String nom, Client client) {
        Produit produit = produitRepository.findByNom(nom) ;
        Panier panier = panierRepository.findByClient(client) ;
        List<Produit> produits = panier.getProduits() ;
        for(Produit pro : produits){
            if(pro == produit){
                produitRepository.delete(pro);
                break ;
            }
        }
        panier.setProduits(produits);

        return panier ;
    }

    @Override
    //supprimer un panier 
    public String supprimerPanier(Client client) {
        Panier panier = panierRepository.findByClient(client) ;
        panierRepository.delete(panier);

        return "suppression effectuer" ;
    }

    @Override
    public Panier recherchePanier(Client client) {
        Panier panier = panierRepository.findByClient(client) ;
        return panier ;
    }

    @Override
    public List<Panier> lirePanier() {
        return panierRepository.findAll() ;
    }

    @Override
    public Panier ajoutProduit(Produit produit, Client client) {
        Panier panier = panierRepository.findByClient(client) ;
        List<Produit> produits = panier.getProduits() ;
        produits.add(produit) ;
        panier.setProduits(produits);
        return panier ;
    }
    
}
