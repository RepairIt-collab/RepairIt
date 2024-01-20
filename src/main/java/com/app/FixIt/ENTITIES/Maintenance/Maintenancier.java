package com.app.FixIt.ENTITIES.Maintenance;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="Maintenancier")
public class Maintenancier extends User {
    
    //private byte[] cv;
    private String specialite;
    @OneToMany
    private List<Taches> taches;
    private String nom_complet;
    private Boolean status;
    private Integer test;// 0- en attende d'evaluation 1-a passer en attente  d'etre valider par un parrain
                        //2- est certifier maintenancier
    private String num_compte; 
    private String adresse; 
    private double latitude;
    
    private double longitude;
    private String sexe;
    private double notes;
    private List<Long> idfilleuls;
    
    
    public List<Long> getIdfilleuls() {
        return idfilleuls;
    }
    public void setIdfilleuls(List<Long> idfilleuls) {
        this.idfilleuls = idfilleuls;
    }
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
    
   
   /*  public byte[] getCv() {
        return cv;
    }
    public void setCv(byte[] cv) {
        this.cv = cv;
    }*/
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public String getNom_complet() {
        return nom_complet;
    }
    public void setNom_complet(String nom_complet) {
        this.nom_complet = nom_complet;
    }
    public String getSpecialite() {
        return specialite;
    }
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    public List<Taches> getTaches() {
        return taches;
    }
    public void setTaches(List<Taches> taches) {
        this.taches = taches;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public String getNum_compte() {
        return num_compte;
    }
    public void setNum_compte(String num_compte) {
        this.num_compte = num_compte;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public Integer getTest() {
        return test;
    }
    public void setTest(Integer test) {
        this.test = test;
    }
    public double getNotes() {
        return notes;
    }
    public void setNotes(double notes) {
        this.notes = notes;
    }
    
}
