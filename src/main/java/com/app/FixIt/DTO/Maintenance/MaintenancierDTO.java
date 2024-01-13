package com.app.FixIt.DTO.Maintenance;

import org.springframework.stereotype.Repository;

@Repository
public class MaintenancierDTO {
    String nom_complet;
    String nom_utilisateur;
    String password;
    String mail;
    String sexe;
    Integer telephone;
    String specialite;
    double latitude;
    
    double longitude;

    public double getlatitude() {
        return latitude;
    }
    public void setlatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNom_complet() {
        return nom_complet;
    }
    public String getNom_utilisateur() {
        return nom_utilisateur;
    }
    public String getPassword() {
        return password;
    }
    public String getMail() {
        return mail;
    }
    public String getSexe() {
        return sexe;
    }
    public Integer getTelephone() {
        return telephone;
    }
    public String getSpecialite() {
        return specialite;
    }

    public void setNom_complet(String nom_complet) {
        this.nom_complet = nom_complet;
    }
    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }
}
