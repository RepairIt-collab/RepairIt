package com.app.FixIt.ENTITIES.Maintenance;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "taches")
public class Taches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="IdMaintenancier")
    private Maintenancier maintenancier;

    @ManyToOne
    @JoinColumn(name="IdClient")
    private Client client;

    @OneToOne
    @JoinColumn(name="IdEquipement")
    private Equipements equipements;

    // @OneToMany
    // private List<Produit> produits;//produits de boutique

    private Integer cout;

    private LocalDate date;

    private Integer etat;  // 0-noveau 1-en cours 2-termine

    private Type type;

    private String description;

    private byte[] image;

    private double latitude;

    private double longitude;

    private String imgString;
    

    
    public Taches() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Maintenancier getMaintenancier() {
        return maintenancier;
    }
    public void setMaintenancier(Maintenancier maintenancier) {
        this.maintenancier = maintenancier;
    }
    public Client getClient(){
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Equipements getEquipements(){
        return equipements;
    }
    public void setEquipements(Equipements equipements){
        this.equipements=equipements;
    }

    public Integer getCout() {
        return cout;
    }
    public void setCout(Integer cout) {
        this.cout = cout;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Integer getEtat() {
        return etat;
    }
    public void setEtat(Integer etat) {
        this.etat = etat;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public byte[] getImage() {
        return image;
    }
    
    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImgString() {
        return imgString;
    }
    public void setImgString(String imgString) {
        this.imgString = imgString;
    }
    // public List<Produit> getProduits() {
    //     return produits;
    // }
    // public void setProduits(List<Produit> produits) {
    //     this.produits = produits;
    // }

}
