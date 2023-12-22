package com.app.FixIt.DTO.Maintenance;

import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Type;

@Repository
public class EquipementDTO {
    private String nom;
    private byte[] photo;
    private Integer etats;
    private Long Id;
    private Type type;

    public Integer getEtats() {
        return etats;
    }
    public Long getId() {
        return Id;
    }
    public String getNom() {
        return nom;
    }
    public byte[] getPhoto() {
        return photo;
    }
    public Type getType() {
        return type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    public void setEtats(Integer etats) {
        this.etats = etats;
    }
    public void setId(Long id) {
        Id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}
