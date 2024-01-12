package com.app.FixIt.BOUTIQUE.DTO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.FixIt.BOUTIQUE.entities.Produit;




@Repository
public class DomaineDTO {
    
    private Long id ;
    private String nom ;
    public List<Produit> produits ;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public List<Produit> getProduit() {
        return produits;
    }
    public void setProduit(List<Produit> produits) {
        this.produits = produits;
    }
}
