package com.app.FixIt.ENTITIES.Boutique;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Commande")
public class Commande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @OneToOne
    Panier panier ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    
}
