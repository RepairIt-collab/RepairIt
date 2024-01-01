package com.app.FixIt.BOUTIQUE.entities;

import java.util.List;

import com.app.FixIt.ENTITIES.Maintenance.Equipements;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Domaine")
public class Domaine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    
    public String domaine ;

    @OneToMany
    @JoinColumn(name = "Produit")
    private List<Produit> Produit ;

    @OneToMany
    private List<Equipements> equipements ;

    public Long getId() {
        return id;
    }
                            
    public Domaine() {
    }

    public Domaine(String domaine) {
        this.domaine = domaine;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getDomaine() {
        return domaine;
    }
    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }


    public List<Equipements> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<Equipements> equipements) {
        this.equipements = equipements;
    }

    public List<Produit> getProduit() {
        return Produit;
    }

    public void setProduit(List<Produit> produit) {
        Produit = produit;
    }

    
}
