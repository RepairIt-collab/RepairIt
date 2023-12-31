package com.app.FixIt.DTO.Boutique;

import org.springframework.stereotype.Repository;

@Repository
public class DomaineDTO {

    private Long  id ;
    private String nom ;

    public DomaineDTO() {
    }

    public DomaineDTO(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

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

    
}
