package com.app.FixIt.ENTITIES.Maintenance;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @OneToMany
    private List<Maintenancier> maintenanciers; 
    @OneToOne
    @JoinColumn(name="IdTaches")
    private Taches taches;
    private Long idMaintenancier;
    public Long getIdMaintenancier() {
        return idMaintenancier;
    }
    public void setIdMaintenancier(Long idMaintenancier) {
        this.idMaintenancier = idMaintenancier;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Maintenancier> getMaintenanciers() {
        return maintenanciers;
    }
    public void setMaintenanciers(List<Maintenancier> maintenanciers) {
        this.maintenanciers = maintenanciers;
    }
    public Taches getTaches() {
        return taches;
    }
    public void setTaches(Taches taches) {
        this.taches = taches;
    }

    


}
