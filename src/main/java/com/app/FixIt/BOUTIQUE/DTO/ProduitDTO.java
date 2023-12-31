package com.app.FixIt.BOUTIQUE.DTO;

import org.springframework.stereotype.Repository;

@Repository
public class ProduitDTO {

    private Long id ;
    private String photo ;
    private String nom ;
    private String caracteristique ;
    private int prix ;
    private int quantite ;
    private String domaine ;

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
    public String getCaracteristique() {
        return caracteristique;
    }
    public void setCaracteristique(String caracteristique) {
        this.caracteristique = caracteristique;
    }
    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getDomaine() {
        return domaine;
    }
    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }   
}
