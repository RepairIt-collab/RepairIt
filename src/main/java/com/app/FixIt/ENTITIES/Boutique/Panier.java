package com.app.FixIt.ENTITIES.Boutique;
import java.util.List;

import com.app.FixIt.ENTITIES.Maintenance.Client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Panier {

    @OneToMany
    private List<Produit> produits;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private Client client ;
    private int prix_total ;

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public int getPrix_total() {
        return prix_total;
    }
    public void setPrix_total(int prix_total) {
        this.prix_total = prix_total;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setProduits(List<Produit> Produits) {
        this.produits = Produits;
    }
    public List<Produit> getProduits(){
        return produits;
    }

}
