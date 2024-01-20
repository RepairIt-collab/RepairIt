package com.app.FixIt.BOUTIQUE.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Produit")
public class Produit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String photo ;
    private String nom ;
    private String caracteristique ;
    private int prix ;
    private int quantite ;
    private Boolean occupe;
    private Boolean estcommande;


    @ManyToOne
    public Domaine domaine ;
    

    public Boolean getEstcommande() {
        return estcommande;
    }
    public void setEstcommande(Boolean estcommande) {
        this.estcommande = estcommande;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public long getId() {
        return id;
    }
    public Produit(){

    }
    public Produit(String nom,String caracteristique ,int prix ,int quantite,String photo,Domaine domaine){
        this.nom = nom ;
        this.caracteristique = caracteristique ;
        this.prix = prix ;
        this.quantite = quantite ;
        this.photo = photo ;
        this.domaine = domaine ;

    } 
    public void setId(long id) {
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
    public Domaine getDomaine() {
        return domaine;
    }
    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public Boolean getOccupe() {
        return occupe;
    }
    public void setOccupe(Boolean occupe) {
        this.occupe = occupe;
    }
}
