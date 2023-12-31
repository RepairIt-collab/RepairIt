package com.app.FixIt.DTO.Boutique;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Boutique.Produit;
import com.app.FixIt.ENTITIES.Maintenance.Client;



@Repository
public class PanierDTO {
    
    private Long id ;
    private List<Produit> produits;
    private Client client ;
    private int prixtotal ;
    
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    
    public int getPrixtotal() {
        return prixtotal;
    }
    public void setPrixtotal(int prix_total) {
        this.prixtotal = prix_total;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Produit> getProduit() {
        return produits;
    }
    public void setProduit(List<Produit> produits) {
        this.produits = produits;
    }
}
