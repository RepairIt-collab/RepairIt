package com.app.FixIt.DTO.Boutique;

import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Boutique.Panier;



@Repository
public class CommandeDTO {
    
    private Long id ;
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
