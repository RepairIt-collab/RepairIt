package com.app.FixIt.DTO.Maintenance;

import java.time.LocalDate;

import com.app.FixIt.ENTITIES.Maintenance.Type;

public class FullCalendar {
    private String nom;
    private LocalDate date;
    private Type type;
    private String etat;
    private String description;
   

    public FullCalendar() {
     
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
}
