package com.app.FixIt.DTO.Maintenance;

import java.time.LocalDate;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Type;

@Repository
public class TacheDto {
    private Long id;
    private String nom;
    private byte[] photo;
    private Type type;
    private String description;
    private LocalDate date;
    private double longitude; 
    private double latitude; 
    private String clientUsername;
    private String selectedUsername;
    private Integer etat;




    public String getClientUsername() {
        return clientUsername;
    }
    public String getSelectedUsername() {
        return selectedUsername;
    }
    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
    public void setSelectedUsername(String selectedUsername) {
        this.selectedUsername = selectedUsername;
    }


    public Long getId1() {
        return id;
    }
    public void setId1(Long id1) {
        id = id1;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
  /*   public String getLocalisation() {
        return localisation;
    }*/
    public byte[] getPhoto() {
        return photo;
    } 
    public Type getType() {
        return type;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setDescription(String description) {
        this.description = description;
    }
   /*  public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }*/
   public void setPhoto(byte[] photo) {
        this.photo = photo;
    } 
    public void setType(Type type) {
        this.type = type;
    } 
      public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }
}
