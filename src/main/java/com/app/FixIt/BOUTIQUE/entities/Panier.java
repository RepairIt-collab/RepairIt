package com.app.FixIt.BOUTIQUE.entities;
import java.util.List;

import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Panier {
    @OneToMany
    private List<Produit> Produit;

    @OneToOne
    private User user ;

 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private long id;

    private int prix_total ;

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
    public void setProduit(List<Produit> Produit) {
        this.Produit = Produit;
    }
    public List<Produit> getProduit(){
        return Produit;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
