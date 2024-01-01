package com.app.FixIt.ENTITIES.Maintenance;

import jakarta.persistence.*;

@Entity
@Table(name="Equipement")
public class Equipements { // prend le nom de l'equipement sa photo et son etats
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="IdClient")
    private Client client;


    private String nom;

    // recuperer la photo
    private byte[] photo;

    private Integer etats ; // 0-gate 1-en cours de reparation 2-repare

    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public byte[] getPhoto() {
        return photo;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Integer getEtats(){
        return etats;
    }
    public void setEtats(Integer etats){
         this.etats=etats;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
   
}
